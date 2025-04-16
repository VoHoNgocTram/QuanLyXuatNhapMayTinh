/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;

/**
 *
 * @author Acer
 */
public class NhanVienDAO {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }
    public ArrayList<NhanVienDTO> getAllDSNhanVien() {
        ArrayList<NhanVienDTO> dsNhanVien = new ArrayList();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM nhanvien ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                        rs.getInt("manv"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getInt("machucvu"),
                        rs.getDate("ngayvaolam"),
                        rs.getInt("songayphep"),
                        rs.getInt("luongcoban"),
                        rs.getString("trangthai"),
                        rs.getString("taikhoan")
                );
                dsNhanVien.add(nv);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return dsNhanVien;
    }
    
    public boolean addNhanVien(NhanVienDTO nv) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO nhanvien(manv, hoten, ngaysinh, gioitinh, diachi, sdt, email, machucvu, ngayvaolam, songayphep, luongcoban, trangthai, taikhoan) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement presttm = con.prepareStatement(sql);
            presttm.setInt(1, nv.getManv());
            presttm.setString(2, nv.getHoten());
            presttm.setDate(3, nv.getNgaysinh());
            presttm.setString(4, nv.getGioitinh());
            presttm.setString(5, nv.getDiachi());
            presttm.setString(6, nv.getSdt());
            presttm.setString(7, nv.getEmail());
            presttm.setInt(8, nv.getMachucvu());
            presttm.setDate(9, nv.getNgayvaolam());
            presttm.setInt(10, nv.getSongayphep());
            presttm.setInt(11, nv.getLuongcoban());
            presttm.setString(12, nv.getTrangthai());
            presttm.setString(13, nv.getTaikhoan());

            if (presttm.executeUpdate() >= 1) {
                result = true;
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        NhanVienDTO nv = new NhanVienDTO();
//
//        System.out.print("Nhập mã nhân viên (int): ");
//        nv.setManv(Integer.parseInt(sc.nextLine()));
//
//        System.out.print("Nhập họ tên: ");
//        nv.setHoten(sc.nextLine());
//
//        System.out.print("Nhập ngày sinh (yyyy-mm-dd): ");
//        nv.setNgaysinh(Date.valueOf(LocalDate.parse(sc.nextLine())));
//
//        System.out.print("Nhập giới tính (Nam/Nữ): ");
//        nv.setGioitinh(sc.nextLine());
//
//        System.out.print("Nhập địa chỉ: ");
//        nv.setDiachi(sc.nextLine());
//
//        System.out.print("Nhập số điện thoại: ");
//        nv.setSdt(sc.nextLine());
//
//        System.out.print("Nhập email (có thể bỏ trống): ");
//        String email = sc.nextLine();
//        nv.setEmail(email.isEmpty() ? null : email);
//
//        System.out.print("Nhập mã chức vụ (int): ");
//        nv.setMachucvu(Integer.parseInt(sc.nextLine()));
//
//        System.out.print("Nhập ngày vào làm (yyyy-mm-dd): ");
//        nv.setNgayvaolam(Date.valueOf(LocalDate.parse(sc.nextLine())));
//
//        System.out.print("Nhập số ngày phép (int, enter nếu để mặc định): ");
//        String songayphep = sc.nextLine();
//        nv.setSongayphep(songayphep.isEmpty() ? 12 : Integer.parseInt(songayphep));
//
//        System.out.print("Nhập lương cơ bản (int): ");
//        nv.setLuongcoban(Integer.parseInt(sc.nextLine()));
//
//        System.out.print("Nhập trạng thái (Đang làm/Nghỉ việc): ");
//        String trangThai = sc.nextLine();
//        nv.setTrangthai(trangThai.isEmpty() ? "Đang làm" : trangThai);
//
//        System.out.print("Nhập tài khoản (có thể bỏ trống): ");
//        String taiKhoan = sc.nextLine();
//        nv.setTaikhoan(taiKhoan.isEmpty() ? null : taiKhoan);
//
//        // Gọi DAO để thêm vào CSDL
//        NhanVienDAO dao = new NhanVienDAO();
//        boolean result = dao.addNhanVien(nv);
//
//        if (result) {
//            System.out.println("✅ Thêm nhân viên thành công!");
//        } else {
//            System.out.println("❌ Thêm nhân viên thất bại!");
//        }
//
//        sc.close();
//    }
    public NhanVienDTO getNhanVienByMa(int maNV) {
        NhanVienDTO nv = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT nv.*, cv.tenchucvu " +
                        "FROM nhanvien nv " +
                        "JOIN chucvu cv ON nv.machucvu = cv.machucvu " +
                        "WHERE nv.manv = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, maNV);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                nv = new NhanVienDTO(
                        rs.getInt("manv"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getInt("machucvu"),
                        rs.getDate("ngayvaolam"),
                        rs.getInt("songayphep"),
                        rs.getInt("luongcoban"),
                        rs.getString("trangthai"),
                        rs.getString("taikhoan")
                );
                nv.setTenchucvu(rs.getString("tenchucvu"));
            }

            rs.close();
            st.close();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
}