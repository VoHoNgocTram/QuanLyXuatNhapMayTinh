/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucVuDTO;
import DTO.NguoiDungDTO;
import DTO.lsChucVuDTO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class NhanVienDAO {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }
    public ArrayList<NguoiDungDTO> getAllDSNhanVien() {
        ArrayList<NguoiDungDTO> dsNhanVien = new ArrayList();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM nguoidung ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                NguoiDungDTO nv = new NguoiDungDTO(
                        rs.getString("taiKhoan"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getInt("maChucVu"),
                        rs.getDate("ngayVaoLam"),
                        rs.getInt("soNgayPhep"),
                        rs.getInt("luongCoBan"),
                        rs.getInt("trangThai")
                );
                dsNhanVien.add(nv);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return dsNhanVien;
    }
    
    public boolean addNhanVien(NguoiDungDTO nv) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO nguoidung(taikhoan, matkhau, hoten, email, manhomquyen, ngaysinh, gioitinh, diachi, sdt, machucvu, tenchucvu, ngayvaolam, songayphep, luongcoban, trangthai) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement presttm = con.prepareStatement(sql);
            presttm.setString(1, nv.getTaiKhoan());
            presttm.setString(2, nv.getMatKhau()); // bcrypt hash nếu có
            presttm.setString(3, nv.getHoTen());
            presttm.setString(4, nv.getEmail());
            presttm.setInt(5, nv.getMaNhomQuyen());
            presttm.setDate(6, new java.sql.Date(nv.getNgaySinh().getTime()));
            presttm.setString(7, nv.getGioiTinh());
            presttm.setString(8, nv.getDiaChi());
            presttm.setString(9, nv.getSdt()); // sdt là varchar(50)
            presttm.setInt(10, nv.getMaChucVu());
            presttm.setString(11, nv.getTenChucvu());
            presttm.setDate(12, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            presttm.setInt(13, nv.getSoNgayPhep());
            presttm.setInt(14, nv.getLuongCoBan());
            presttm.setInt(15, nv.getTrangThai());

            if (presttm.executeUpdate() >= 1) {
                result = true;
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean delete(String tk) {
        boolean ketQua = false;
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            // Đặt trạng thái của người dùng thành 0 để đánh dấu là đã bị xóa
            String deleteSql = "UPDATE nguoidung SET trangthai = 0 WHERE taikhoan = ? and trangthai = 1";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
            deleteStmt.setString(1, tk);
            if (deleteStmt.executeUpdate() >= 1) {
                ketQua = true;
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public NguoiDungDTO getNhanVienByTaiKhoan(String taikhoan) {
        NguoiDungDTO nv = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT nv.*, cv.tenchucvu " +
                        "FROM nguoidung nv " +
                        "JOIN chucvu cv ON nv.machucvu = cv.machucvu " +
                        "WHERE nv.taikhoan = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, taikhoan);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                nv = new NguoiDungDTO(
                        rs.getString("taiKhoan"),
                        rs.getString("hoTen"),
                        rs.getDate("ngaySinh"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getInt("maChucVu"),
                        rs.getDate("ngayVaoLam"),
                        rs.getInt("soNgayPhep"),
                        rs.getInt("luongCoBan"),
                        rs.getInt("trangThai")
                );
                nv.setTenChucvu(rs.getString("tenchucvu"));
            }

            rs.close();
            st.close();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
    
    public ArrayList<ChucVuDTO> getAllDSChucVu() {
        ArrayList<ChucVuDTO> dsChucvu = new ArrayList();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM chucvu ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ChucVuDTO cv = new ChucVuDTO(
                        rs.getInt("machucvu"),
                        rs.getString("tenchucvu"),
                        rs.getInt("luongcoban"),
                        rs.getInt("trangthai")
                );
                dsChucvu.add(cv);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return dsChucvu;
    }
    public ArrayList<lsChucVuDTO> getAllLichSuChucVu() {
        ArrayList<lsChucVuDTO> dsLichSu = new ArrayList<>();
        String sql = "SELECT * FROM lichsuchucvu"; // sửa tên bảng theo đúng bạn dùng

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lsChucVuDTO ls = new lsChucVuDTO(
                        rs.getInt("mals"),
                        rs.getString("taikhoan"),
                        rs.getString("hoten"),
                        rs.getInt("machucvu"),
                        rs.getString("tenchucvu"),
                        rs.getDate("ngaythaydoi"),
                        rs.getInt("luongcoban"),
                        rs.getInt("trangthai")
                );
                dsLichSu.add(ls);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsLichSu;
    }
    public ArrayList<lsChucVuDTO> getLichSuChucVuByTaiKhoan(String taikhoan) {
        ArrayList<lsChucVuDTO> dsLichSu = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM lichsuchucvu WHERE taikhoan = ? ORDER BY ngaythaydoi DESC";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, taikhoan);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lsChucVuDTO ls = new lsChucVuDTO(
                        rs.getInt("mals"),
                        rs.getString("taikhoan"),
                        rs.getString("hoten"),
                        rs.getInt("machucvu"),
                        rs.getString("tenchucvu"),
                        rs.getDate("ngaythaydoi"),
                        rs.getInt("luongcoban"),
                        rs.getInt("trangthai")
                );
                dsLichSu.add(ls);
            }

            rs.close();
            st.close();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsLichSu;
    }
    public boolean addLichSuChucVu(lsChucVuDTO lscv) {
        boolean result = false;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO lichsuchucvu(mals, taikhoan, hoten, machucvu, tenchucvu, ngaythaydoi, luongcoban, trangthai) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement presttm = con.prepareStatement(sql);
            presttm.setInt(1, lscv.getMals());
            presttm.setString(2, lscv.getTaikhoan());
            presttm.setString(3, lscv.getHoten());
            presttm.setInt(4, lscv.getMachucvu());
            presttm.setString(5, lscv.getTenchucvu());
            presttm.setDate(6, new java.sql.Date(lscv.getNgaythaydoi().getTime()));
            presttm.setInt(7, lscv.getLuongcoban());
            presttm.setInt(8, lscv.getTrangthai()); // thường là 1 khi thêm mới

            if (presttm.executeUpdate() >= 1) {
                result = true;
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean capNhatChucVuNguoiDung(String taiKhoan, int maChucVuMoi, Date ngayThayDoi, lsChucVuDTO lscv) {
        boolean result = false;

        try {
            Connection con = JDBCUtil.getConnection();
            con.setAutoCommit(false); // Bắt đầu transaction

            // 1. Cập nhật trạng thái của chức vụ cũ trong lichsuchucvu
            String sqlUpdateLS = "UPDATE lichsuchucvu SET trangthai = 0, ngaythaydoi = ? " +
                                 "WHERE taikhoan = ? AND trangthai = 1";
            PreparedStatement st1 = con.prepareStatement(sqlUpdateLS);
            st1.setDate(1, new java.sql.Date(ngayThayDoi.getTime()));
            st1.setString(2, taiKhoan);
            st1.executeUpdate();

            // 2. Thêm bản ghi chức vụ mới vào lichsuchucvu
            String sqlInsertLS = "INSERT INTO lichsuchucvu (mals, taikhoan, hoten, machucvu, tenchucvu, ngaythaydoi, luongcoban, trangthai) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st2 = con.prepareStatement(sqlInsertLS);
            st2.setInt(1, lscv.getMals());
            st2.setString(2, lscv.getTaikhoan());
            st2.setString(3, lscv.getHoten());
            st2.setInt(4, lscv.getMachucvu());
            st2.setString(5, lscv.getTenchucvu());
            st2.setDate(6, new java.sql.Date(lscv.getNgaythaydoi().getTime()));
            st2.setInt(7, lscv.getLuongcoban());
            st2.setInt(8, lscv.getTrangthai()); // thường là 1 khi thêm mới
            st2.executeUpdate();

            // 3. Cập nhật chức vụ hiện tại trong bảng nguoidung
            String sqlUpdateNguoiDung = "UPDATE nguoidung SET machucvu = ? WHERE taikhoan = ?";
            PreparedStatement st3 = con.prepareStatement(sqlUpdateNguoiDung);
            st3.setInt(1, maChucVuMoi);
            st3.setString(2, taiKhoan);
            st3.executeUpdate();

            // Nếu không lỗi thì commit
            con.commit();
            result = true;

            // Đóng statement
            st1.close();
            st2.close();
            st3.close();
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}