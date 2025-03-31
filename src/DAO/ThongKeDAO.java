package DAO;

import DTO.DateRangeDTO;
import DTO.ThongKe.*;
import database.JDBCUtil;
import helper.DateHelper;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

public class ThongKeDAO {
    public ThongKeDAO() {
    }
    
    public ChiTietTongTonKhoDTO thongKeTonKho(DateRangeDTO dateRange, String productName) throws SQLException {
        ArrayList<ThongKeTonKhoDTO> result = new ArrayList<>();
        String fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        String toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        Long tongTonDauKy = 0L, tongNhapTrongKy = 0L, tongXuatTrongKy = 0L, tongTonCuoiKy = 0L;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = """
                           WITH nhaptrongky AS (
                           	SELECT masanpham, SUM(soluongthucte) AS soluongnhap
                           	FROM phieunhap PN JOIN chitietphieunhap CTPN ON PN.maphieunhap = CTPN.maphieunhap JOIN trangthaiphieunhap TTPN ON PN.trangthai = TTPN.matrangthai
                           	WHERE DATE(PN.thoigiantao) BETWEEN ? AND ? AND tentrangthai LIKE '%delivered%'
                           	GROUP BY masanpham
                           ), xuattrongky AS (
                           	SELECT masanpham, SUM(soluong) AS soluongxuat
                           	FROM phieuxuat PX JOIN chitietphieuxuat CTPX ON PX.maphieuxuat = CTPX.maphieuxuat
                           	WHERE DATE(PX.thoigiantao) BETWEEN ? AND ? AND PX.trangthai = 1
                           	GROUP BY masanpham
                           ), nhapdauky AS (
                           	SELECT masanpham, SUM(soluongthucte) AS soluongnhap
                           	FROM phieunhap PN JOIN chitietphieunhap CTPN ON PN.maphieunhap = CTPN.maphieunhap
                           	WHERE DATE(PN.thoigiantao) < ?
                           	GROUP BY masanpham
                           ), xuatdauky AS (
                           	SELECT masanpham, SUM(soluong) AS soluongxuat
                           	FROM phieuxuat PX JOIN chitietphieuxuat CTPX ON PX.maphieuxuat = CTPX.maphieuxuat
                           	WHERE DATE(PX.thoigiantao) < ?
                           	GROUP BY masanpham
                           ), dauky AS (
                           	SELECT SP.masanpham, COALESCE(NDK.soluongnhap, 0) - COALESCE(XDK.soluongxuat, 0) AS tondauky
                           	FROM sanpham SP
                               LEFT JOIN chitietcungcap CTCC ON SP.masanpham = CTCC.masanpham
                           	LEFT JOIN nhapdauky NDK ON CTCC.masanpham = NDK.masanpham
                           	LEFT JOIN xuatdauky XDK ON CTCC.masanpham = XDK.masanpham
                           ), tonkho AS (
                           	SELECT SP.masanpham, tensanpham, tondauky, COALESCE(NTK.soluongnhap, 0) AS nhaptrongky, COALESCE(XTK.soluongxuat, 0) AS xuattrongky, (tondauky + COALESCE(NTK.soluongnhap, 0) - COALESCE(XTK.soluongxuat, 0)) AS toncuoiky
                           	FROM dauky DK
                           	LEFT JOIN nhaptrongky NTK ON DK.masanpham = NTK.masanpham
                           	LEFT JOIN xuattrongky XTK ON DK.masanpham = XTK.masanpham
                           	JOIN sanpham SP ON SP.masanpham = DK.masanpham
                           )
                           SELECT DISTINCT * FROM tonkho
                           WHERE tensanpham LIKE ?
                           ORDER BY toncuoiky DESC, tondauky DESC, xuattrongky DESC, nhaptrongky DESC, masanpham DESC
                           """;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.setString(5, fromDate);
            ps.setString(6, fromDate);
            ps.setString(7, "%" + productName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("masanpham");
                String tenSanPham = rs.getString("tensanpham");
                int tonDauKy = rs.getInt("tondauky");
                int nhapTrongKy = rs.getInt("nhaptrongky");
                int xuatTrongKy = rs.getInt("xuattrongky");
                int tonCuoiKy = rs.getInt("toncuoiky");
                result.add(new ThongKeTonKhoDTO(maSanPham, tenSanPham, tonDauKy, nhapTrongKy, xuatTrongKy, tonCuoiKy));
                tongTonDauKy += tonDauKy;
                tongNhapTrongKy += nhapTrongKy;
                tongXuatTrongKy += xuatTrongKy;
                tongTonCuoiKy += tonCuoiKy;
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return new ChiTietTongTonKhoDTO(dateRange, productName, result, tongTonDauKy, tongNhapTrongKy, tongXuatTrongKy, tongTonCuoiKy);
    }
    
    public ChiTietTongSanPhamDTO thongKeSanPham(DateRangeDTO dateRange, String productName) throws SQLException {
        ArrayList<ThongKeSanPhamDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;
        int tongSoLuongNhap = 0;

        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
            WITH slnhap AS (
                SELECT masanpham, SUM(soluongthucte) AS soluongnhap
                FROM phieunhap PN
                JOIN chitietphieunhap CTPN ON PN.maphieunhap = CTPN.maphieunhap
                JOIN trangthaiphieunhap TTPN ON TTPN.matrangthai = PN.trangthai
                WHERE tentrangthai LIKE '%delivered%'""");

        if (!lifetime) {
            queryBuilder.append(" AND DATE(PN.thoigiantao) BETWEEN ? AND ?");
        }

        queryBuilder.append("""
            GROUP BY masanpham
            ), temp_table AS (
                SELECT SP.masanpham, tenloaisanpham, tensanpham, COALESCE(soluongnhap, 0) AS soluongnhap
                FROM sanpham SP JOIN slnhap SLN ON SP.masanpham = SLN.masanpham
                JOIN loaisanpham LSP ON SP.maloaisanpham = LSP.maloaisanpham
            )
            SELECT * FROM temp_table
            WHERE tensanpham LIKE ?
            ORDER BY soluongnhap DESC, masanpham""");
        
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;

            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex++, toDate);
            }

            ps.setString(paramIndex, "%" + productName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maSanPham = rs.getInt("masanpham");
                String tenLoaiSanPham = rs.getString("tenloaisanpham");
                String tenSanPham = rs.getString("tensanpham");
                int soLuongNhap = rs.getInt("soluongnhap");
                result.add(new ThongKeSanPhamDTO(maSanPham, tenLoaiSanPham, tenSanPham, soLuongNhap));
                tongSoLuongNhap += soLuongNhap;
            }

            JDBCUtil.closeConnection(conn);
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }

        return new ChiTietTongSanPhamDTO(dateRange, productName, result, tongSoLuongNhap);
    }
    
    public ArrayList<ChiTietSanPhamNhapDTO> thongKeChiTietSanPhamNhap(DateRangeDTO dateRange, int productId) throws SQLException {
        ArrayList<ChiTietSanPhamNhapDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;

        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                                WITH tongnhap AS (
                                    SELECT manhacungcap, SUM(soluongthucte) AS tongsoluongnhap
                                    FROM phieunhap PN
                                    JOIN chitietphieunhap CTPN ON PN.maphieunhap = CTPN.maphieunhap
                                    JOIN trangthaiphieunhap TTPN ON TTPN.matrangthai = PN.trangthai
                                    WHERE tentrangthai LIKE '%delivered%' AND masanpham = ?
                            """);
        
        if (!lifetime) {
            queryBuilder.append("AND DATE(PN.thoigiantao) BETWEEN ? AND ?");
        }
        
        queryBuilder.append("""
                                    GROUP BY manhacungcap
                                    ORDER BY tongsoluongnhap
                                ), temp_table AS (
                                        SELECT TN.manhacungcap, tennhacungcap, tongsoluongnhap
                                    FROM tongnhap TN JOIN nhacungcap NCC ON NCC.manhacungcap = TN.manhacungcap
                                )
                                SELECT * FROM temp_table
                                ORDER BY tongsoluongnhap DESC, manhacungcap
                                    """);
        
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            ps.setInt(paramIndex++, productId);
            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex, toDate);
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhaCungCap = rs.getInt("manhacungcap");
                String tenNhaCungCap = rs.getString("tennhacungcap");
                int tongSoLuongNhap = rs.getInt("tongsoluongnhap");
                result.add(new ChiTietSanPhamNhapDTO(maNhaCungCap, tenNhaCungCap, tongSoLuongNhap));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public ArrayList<ChiTietGiaNhapNCCDTO> chiTietGiaNhapNCC (int productId, int providerId, DateRangeDTO dateRange) throws SQLException {
        ArrayList<ChiTietGiaNhapNCCDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;
        
        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            SELECT CTPN.maphieunhap, PN.thoigiantao, soluongthucte AS soluongnhap, dongia AS dongianhap
                            FROM phieunhap PN
                            JOIN chitietphieunhap CTPN ON PN.maphieunhap = CTPN.maphieunhap
                            JOIN trangthaiphieunhap TTPN ON TTPN.matrangthai = PN.trangthai
                            WHERE tentrangthai LIKE '%delivered%' AND masanpham = ? AND CTPN.manhacungcap = ?
                            """);
        if (!lifetime) {
            queryBuilder.append("AND DATE(PN.thoigiantao) BETWEEN ? AND ?");
        }
        
        queryBuilder.append("ORDER BY thoigiantao DESC, soluongnhap DESC, dongia");
        
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            ps.setInt(paramIndex++, productId);
            ps.setInt(paramIndex++, providerId);
            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex, toDate);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maPhieuNhap = rs.getInt("maphieunhap");
                Timestamp timestamp = rs.getTimestamp("thoigiantao");
                LocalDateTime thoiGianTao = timestamp.toLocalDateTime();
                int soLuongNhap = rs.getInt("soluongnhap");
                Long donGiaNhap = rs.getLong("dongianhap");
                result.add(new ChiTietGiaNhapNCCDTO(maPhieuNhap, thoiGianTao, soLuongNhap, donGiaNhap));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public ChiTietLSPXuatDTO thongKeLoaiSanPham(DateRangeDTO dateRange, String productType) throws SQLException {
        ArrayList<ThongKeLoaiSanPhamDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;
        int tongSoLuongXuat = 0;
        
        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            SELECT LSP.maloaisanpham, tenloaisanpham, SUM(CTPX.soluong) AS soluong
                            FROM chitietphieuxuat CTPX
                            JOIN phieuxuat PX ON CTPX.maphieuxuat = PX.maphieuxuat
                            JOIN sanpham SP ON CTPX.masanpham = SP.masanpham
                            JOIN loaisanpham LSP ON LSP.maloaisanpham = SP.maloaisanpham
                            WHERE PX.trangthai = 1 AND tenloaisanpham LIKE ?
                            """);
        if (!lifetime) {
            queryBuilder.append("AND DATE(PX.thoigiantao) BETWEEN ? AND ?");
        }
        queryBuilder.append("""
                            GROUP BY LSP.maloaisanpham
                            ORDER BY soluong DESC, maloaisanpham
                            """);
        try {
            Connection conn = JDBCUtil.getConnection();
            int paramIndex = 1;
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            ps.setString(paramIndex++, "%" + productType + "%");
            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex, toDate);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maLoaiSanPham = rs.getInt("maloaisanpham");
                String tenLoaiSanPham = rs.getString("tenloaisanpham");
                int soLuong = rs.getInt("soluong");
                result.add(new ThongKeLoaiSanPhamDTO(maLoaiSanPham, tenLoaiSanPham, soLuong));
                tongSoLuongXuat += soLuong;
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return new ChiTietLSPXuatDTO(dateRange, productType, result, tongSoLuongXuat);
    }
    
    public ArrayList<ChiTietLoaiSanPhamDTO> chiTietLoaiSanPham(DateRangeDTO dateRange, int productTypeId) throws SQLException {
        ArrayList<ChiTietLoaiSanPhamDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;
        
        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            SELECT CTPX.masanpham, tensanpham, SUM(CTPX.soluong) AS soluong
                            FROM chitietphieuxuat CTPX
                            JOIN phieuxuat PX ON CTPX.maphieuxuat = PX.maphieuxuat
                            JOIN sanpham SP ON SP.masanpham = CTPX.masanpham
                            JOIN loaisanpham LSP ON LSP.maloaisanpham = SP.maloaisanpham
                            WHERE PX.trangthai = 1 AND LSP.maloaisanpham = ?
                            """);
        if (!lifetime) {
            queryBuilder.append(" AND DATE(PX.thoigiantao) BETWEEN ? AND ?");
        }
        queryBuilder.append("""
                            GROUP BY CTPX.masanpham
                            ORDER BY soluong DESC, masanpham DESC
                            """);
        try {
            Connection conn = JDBCUtil.getConnection();
            int paramIndex = 1;
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            
            ps.setInt(paramIndex++, productTypeId);
            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex, toDate);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("masanpham");
                String tenSanPham = rs.getString("tensanpham");
                int soLuong = rs.getInt("soluong");
                result.add(new ChiTietLoaiSanPhamDTO(maSanPham, tenSanPham, soLuong));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public ArrayList<ChiTietGiaXuatSPDTO> chiTietGiaXuatSanPham (int productId, DateRangeDTO dateRange) throws SQLException {
        ArrayList<ChiTietGiaXuatSPDTO> result = new ArrayList<>();
        String fromDate = null;
        String toDate = null;
        boolean lifetime = dateRange.getFromDate() == null && dateRange.getToDate() == null;
        
        if (!lifetime) {
            fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
            toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            SELECT CTPX.maphieuxuat, thoigiantao, soluong AS soluongxuat, dongia AS dongiaxuat
                            FROM chitietphieuxuat CTPX
                            JOIN phieuxuat PX ON CTPX.maphieuxuat = PX.maphieuxuat
                            WHERE PX.trangthai = 1 AND masanpham = ?
                            """);
        if (!lifetime) {
            queryBuilder.append(" AND DATE(PX.thoigiantao) BETWEEN ? AND ?");
        }
        
        queryBuilder.append("""
                            ORDER BY thoigiantao DESC, soluongxuat DESC, dongiaxuat
                            """);
        try {
            Connection conn = JDBCUtil.getConnection();
            int paramIndex = 1;
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            ps.setInt(paramIndex++, productId);
            if (!lifetime) {
                ps.setString(paramIndex++, fromDate);
                ps.setString(paramIndex++, toDate);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maPhieuXuat = rs.getInt("maphieuxuat");
                Timestamp timestamp = rs.getTimestamp("thoigiantao");
                LocalDateTime thoiGianTao = timestamp.toLocalDateTime();
                int soLuongXuat = rs.getInt("soluongxuat");
                Long donGiaXuat = rs.getLong("dongiaxuat");
                result.add(new ChiTietGiaXuatSPDTO(maPhieuXuat, thoiGianTao, soLuongXuat, donGiaXuat));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public ChiTietDoanhThuDTO thongKeDoanhThu(DateRangeDTO dateRange, String groupBy) throws SQLException, ParseException {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        String queryTimeline = "";
        String fromDate = dateRange.getFromDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        String toDate = dateRange.getToDate().format(DateHelper.SQL_ROW_DATE_FORMATTER);
        Long totalExpense = 0L;
        Long totalIncome = 0L;
        Long totalProfit = 0L;

        switch (groupBy) {
            case "date":
                queryTimeline = "DR.date";
                break;
            case "month":
                queryTimeline = "DATE_FORMAT(DR.date, '" + DateHelper.SQL_QUERY_MONTH_FORMAT + "')";
                break;
            case "year":
                queryTimeline = "DATE_FORMAT(DR.date, '" + DateHelper.SQL_QUERY_YEAR_FORMAT + "')";
                break;
            default:
                // throw new exception
        }
        
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            WITH RECURSIVE date_range AS (
                                SELECT DATE(?) AS date
                                UNION ALL
                                SELECT DATE_ADD(date, INTERVAL 1 DAY)
                                FROM date_range
                                WHERE DATE_ADD(date, INTERVAL 1 DAY) <= ?
                            ),
                            temp_table AS (
                                SELECT
                            """);
        queryBuilder.append(queryTimeline);
        queryBuilder.append(" AS ");
        queryBuilder.append(groupBy);
        queryBuilder.append("""
                                    ,
                                    COALESCE(SUM(PN.tongtien), 0) AS expense,
                                    COALESCE(SUM(PX.tongtien), 0) AS income
                                FROM date_range DR
                                LEFT JOIN phieunhap PN ON DR.date = DATE(PN.thoigiantao)
                                LEFT JOIN phieuxuat PX ON DR.date = DATE(PX.thoigiantao)
                                LEFT JOIN trangthaiphieunhap TTPN ON PN.trangthai = TTPN.matrangthai
                                WHERE (TTPN.tentrangthai LIKE '%delivered%') OR (TTPN.matrangthai IS NULL)
                                GROUP BY
                            """);
        queryBuilder.append(queryTimeline);
        queryBuilder.append(" ORDER BY ");
        queryBuilder.append(queryTimeline);
        queryBuilder.append("""
                             DESC
                            )
                            SELECT * FROM temp_table
                            """);
        
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date timeline = null;
                switch (groupBy) {
                    case "date":
                        timeline = rs.getDate(groupBy);
                        break;
                    case "month":
                        String timelineMonth = rs.getString(groupBy);
                        timeline = DateHelper.SQL_ROW_MONTH_FORMATTER.parse(timelineMonth);
                        break;
                    case "year":
                        String timelineYear = rs.getString(groupBy);
                        timeline = DateHelper.SQL_ROW_YEAR_FORMATTER.parse(timelineYear);
                        break;
                    default:
                        // throw new exception
                }
                Long expense = rs.getLong("expense");
                Long income = rs.getLong("income");
                Long profit = income - expense;
                result.add(new ThongKeDoanhThuDTO(timeline, expense, income, profit));
                totalExpense += expense;
                totalIncome += income;
            }
            ps.close();
            rs.close();
            
            totalProfit = totalIncome - totalExpense;
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return new ChiTietDoanhThuDTO(dateRange, result, totalExpense, totalIncome, totalProfit);
    }
    
    public LocalDateTime getOldestDate() throws SQLException {
        LocalDateTime oldestDate = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT DATE(MIN(thoigiantao)) AS oldest_date FROM (SELECT thoigiantao FROM phieunhap UNION SELECT thoigiantao FROM phieuxuat) AS combined";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Date result = rs.getDate("oldest_date");
                oldestDate = DateHelper.convertDateObjToLDT(result);
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return oldestDate;
    }
}
