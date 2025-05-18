/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BangLuongDTO;
import DTO.NguoiDungDTO;
import database.JDBCUtil;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Acer
 */
public class BangLuongDAO {
    public static BangLuongDAO getInstance() {
        return new BangLuongDAO();
    }
    public ArrayList<BangLuongDTO> selectAll() {
        ArrayList<BangLuongDTO> ketQua = new ArrayList<>();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM bangluong WHERE trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                BangLuongDTO bl = new BangLuongDTO();
                bl.setMabl(rs.getInt("mabl"));
                bl.setTaikhoan(rs.getString("taikhoan"));
                bl.setHoten(rs.getString("hoten"));
                bl.setThang(rs.getInt("thang"));
                bl.setNam(rs.getInt("nam"));
                bl.setLuongcoban(rs.getInt("luongcoban"));
                bl.setThuong(rs.getInt("thuong"));
                bl.setTruluong(rs.getInt("truluong"));
                bl.setTongluong(rs.getInt("tongluong"));
                bl.setNgaytinhluong(rs.getDate("ngaytinhluong"));
                bl.setSongaycong(rs.getInt("songaycong"));
                bl.setTrangthai(rs.getInt("trangthai"));

                ketQua.add(bl);
            }

            rs.close();
            pst.close();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    public BangLuongDTO getBangLuongByMa(int mabl) {
        BangLuongDTO bl = null;
        try {
            java.sql.Connection con = JDBCUtil.getConnection();

            String sql = "SELECT bl.*" +
                         "FROM bangluong bl " +
                         "WHERE bl.mabl = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, mabl);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                bl = new BangLuongDTO(
                    rs.getInt("mabl"),
                    rs.getString("taikhoan"),
                    rs.getString("hoten"),
                    rs.getInt("thang"),
                    rs.getInt("nam"),
                    rs.getInt("luongcoban"),
                    rs.getInt("thuong"),
                    rs.getInt("truluong"),
                    rs.getInt("tongluong"),
                    rs.getDate("ngaytinhluong"),
                    rs.getInt("songaycong"),
                    rs.getInt("trangthai")
                );

                // Nếu DTO có thêm chức vụ, bạn có thể thêm
                // bl.setTenchucvu(rs.getString("tenchucvu"));
            }

            rs.close();
            st.close();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }
    public boolean insertSalary(BangLuongDTO salary) {
        String sql = "INSERT INTO bangluong (mabl, taikhoan, hoten, thang, nam, luongcoban, thuong, truluong, tongluong, ngaytinhluong, songaycong, trangthai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            java.sql.Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setInt(1, salary.getMabl());
            stmt.setString(2, salary.getTaikhoan());
            stmt.setString(3, salary.getHoten());
            stmt.setInt(4, salary.getThang());
            stmt.setInt(5, salary.getNam());
            stmt.setInt(6, salary.getLuongcoban());
            stmt.setInt(7, salary.getThuong());
            stmt.setInt(8, salary.getTruluong());
            stmt.setInt(9, salary.getTongluong());
            stmt.setDate(10, new java.sql.Date(salary.getNgaytinhluong().getTime()));
            stmt.setInt(11, salary.getSongaycong());
            stmt.setInt(12, salary.getTrangthai());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<NguoiDungDTO> getDanhSachNhanVienChuaCoLuongThang(int thang, int nam) {
        List<NguoiDungDTO> ds = new ArrayList<>();

        String sql = "SELECT * FROM nguoidung nv " +
                     "WHERE nv.trangthai = 1 AND NOT EXISTS (" +
                     "    SELECT 1 FROM bangluong l " +
                     "    WHERE l.taikhoan = nv.taikhoan " +
                     "      AND l.thang = ? AND l.nam = ? AND l.trangthai = 1" +
                     ")";

        try (
            java.sql.Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, thang);
            stmt.setInt(2, nam);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NguoiDungDTO nv = new NguoiDungDTO();
                nv.setTaiKhoan(rs.getString("taikhoan"));
                nv.setHoTen(rs.getString("hoten"));
                nv.setEmail(rs.getString("email"));
                nv.setMaNhomQuyen(rs.getInt("manhomquyen"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setGioiTinh(rs.getString("gioitinh"));
                nv.setDiaChi(rs.getString("diachi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setMaChucVu(rs.getInt("machucvu"));
                nv.setTenChucvu(rs.getString("tenchucvu"));
                nv.setNgayVaoLam(rs.getDate("ngayvaolam"));
                nv.setSoNgayPhep(rs.getInt("songayphep"));
                nv.setLuongCoBan(rs.getInt("luongcoban"));
                nv.setTrangThai(rs.getInt("trangthai"));

                ds.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean updateLuong(BangLuongDTO bl) {
        boolean result = false;

        String sql = "UPDATE bangluong SET taikhoan = ?, hoten = ?, thang = ?, nam = ?, luongcoban = ?, thuong = ?, " +
                     "truluong = ?, tongluong = ?, ngaytinhluong = ?, songaycong = ?, trangthai = ? " +
                     "WHERE mabl = ?";

        try (
            java.sql.Connection con = JDBCUtil.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, bl.getTaikhoan());
            stmt.setString(2, bl.getHoten());
            stmt.setInt(3, bl.getThang());
            stmt.setInt(4, bl.getNam());
            stmt.setInt(5, bl.getLuongcoban());
            stmt.setInt(6, bl.getThuong());
            stmt.setInt(7, bl.getTruluong());
            stmt.setInt(8, bl.getTongluong());
            stmt.setDate(9, new java.sql.Date(bl.getNgaytinhluong().getTime())); // hoặc Date.valueOf(LocalDate)
            stmt.setInt(10, bl.getSongaycong());
            stmt.setInt(11, bl.getTrangthai());
            stmt.setInt(12, bl.getMabl()); // điều kiện WHERE theo mã bảng lương

            result = stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
