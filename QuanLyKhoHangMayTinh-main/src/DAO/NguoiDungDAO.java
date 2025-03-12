package DAO;

import DTO.NguoiDungDTO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NguoiDungDAO {
    public NguoiDungDAO() {
    }
    public static NguoiDungDAO getInstance() {
        return new NguoiDungDAO();
    }
    
    public NguoiDungDTO verifyLogin(String username) throws SQLException {
        NguoiDungDTO user = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM nguoidung WHERE taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String taiKhoan = rs.getString("taikhoan");
                String matKhau = rs.getString("matkhau");
                String hoTen = rs.getString("hoten");
                String email = rs.getString("email");
                int maNhomQuyen = rs.getInt("maNhomQuyen");
                int trangThai = rs.getInt("trangThai");
                user = new NguoiDungDTO(taiKhoan, matKhau, hoTen, email, maNhomQuyen, trangThai);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }
    
    public NguoiDungDTO getInfoByEmail(String email) throws SQLException {
        NguoiDungDTO user = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT taikhoan, email, manhomquyen, trangthai FROM nguoidung WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String taiKhoan = rs.getString("taikhoan");
                email = rs.getString("email");
                int maNhomQuyen = rs.getInt("manhomquyen");
                int trangThai = rs.getInt("trangThai");
                user = new NguoiDungDTO(taiKhoan, null, null, email, maNhomQuyen, trangThai);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }
    
    //@Override
//    public int update(NguoiDungDTO t) {
//        int ketQua = 0;
//        try {
//            Connection con = JDBCUtil.getConnection();
//            String sql = "UPDATE nguoidung SET matkhau=?, hoten=?, email=?, manhomquen=?, trangthai=? WHERE userName=?";
//            PreparedStatement pst = con.prepareStatement(sql);          
//            pst.setString(1, t.getMatKhau());
//            pst.setString(2, t.getHoTen());
//            pst.setString(3, t.getEmail());
//            pst.setInt(4, t.getMaNhomQuyen());
//            pst.setInt(5, t.getTrangThai());
//            pst.setString(6, t.getTaiKhoan());
//            ketQua = pst.executeUpdate();
//            JDBCUtil.closeConnection(con);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    
    public int changePassword(NguoiDungDTO user, String newPassword) throws SQLException {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE nguoidung SET matkhau = ? WHERE taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, user.getTaiKhoan());
            result = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int changeEmail(NguoiDungDTO user, String newEmail) throws SQLException {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE nguoidung SET email = ? WHERE taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newEmail);
            ps.setString(2, user.getTaiKhoan());
            result = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int getUserPriority(NguoiDungDTO user) throws SQLException {
        int priority = -1;
        String query = "SELECT douutien FROM nguoidung ND, nhomquyen NQ WHERE ND.manhomquyen = NQ.manhomquyen AND taikhoan = ?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getTaiKhoan());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                priority = rs.getInt("douutien");
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return priority;
    }
    
    public ArrayList<NguoiDungDTO> getUserList(String query, int priority) throws SQLException {
        ArrayList<NguoiDungDTO> result = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
                            SELECT taikhoan, hoten, email, ND.manhomquyen, tennhomquyen, douutien
                            FROM nguoidung ND, nhomquyen NQ
                            WHERE ND.manhomquyen = NQ.manhomquyen AND
                                ND.trangthai = ? AND NQ.trangthai = ? AND
                                douutien <= ? AND 
                                (taikhoan LIKE ? OR hoten LIKE ? OR email LIKE ?)
                            """);
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setInt(3, priority);
            ps.setString(4, "%" + query + "%");
            ps.setString(5, "%" + query + "%");
            ps.setString(6, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String taiKhoan = rs.getString("taikhoan");
                String hoTen = rs.getString("hoten");
                String email = rs.getString("email");
                int maNhomQuyen = rs.getInt("manhomquyen");
                String tenNhomQuyen = rs.getString("tennhomquyen");
                int doUuTien = rs.getInt("douutien");
                result.add(new NguoiDungDTO(taiKhoan, hoTen, email, maNhomQuyen, tenNhomQuyen, doUuTien));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int lockAccount(NguoiDungDTO user) throws SQLException {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE nguoidung SET trangthai = ? WHERE taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, user.getTaiKhoan());
            result = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int createUser(NguoiDungDTO user) throws SQLException {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO nguoidung(taikhoan, matkhau, hoten, email, manhomquyen, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getTaiKhoan());
            ps.setString(2, user.getMatKhau());
            ps.setString(3, user.getHoTen());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getMaNhomQuyen());
            ps.setInt(6, user.getTrangThai());
            result = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public boolean checkUserAlreadyExisted(NguoiDungDTO user) throws SQLException {
        boolean result = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT taikhoan FROM nguoidung WHERE taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getTaiKhoan());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int updateUser(NguoiDungDTO user) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE nguoidung SET hoten = ?, email = ?");
        if (!user.getMatKhau().isEmpty()) {
            queryBuilder.append(", matkhau = ?");
        }
        queryBuilder.append(" WHERE taikhoan = ?");
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            ps.setString(paramIndex++, user.getHoTen());
            ps.setString(paramIndex++, user.getEmail());
            if (!user.getMatKhau().isEmpty()) {
                ps.setString(paramIndex++, user.getMatKhau());
            }
            ps.setString(paramIndex, user.getTaiKhoan());
            result = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
