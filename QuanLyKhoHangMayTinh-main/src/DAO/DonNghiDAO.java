/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DonNghiDTO;
import database.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Date;
/**
 *
 * @author downny
 */
public class DonNghiDAO {
    public static DonNghiDAO getInstance() {
        return new DonNghiDAO();
    }
    
    
    public ArrayList<DonNghiDTO> getAllDanhSachDonNghi(){
        ArrayList<DonNghiDTO> ketQua = new ArrayList<DonNghiDTO>();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM donnghi";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maDonNghi = rs.getInt("madn");
                String hoTen = rs.getString("hoten");
                String loaiNghi = rs.getString("loai_nghi");
                Date ngayNopDon = rs.getDate("ngaynopdon");
                Date ngayBatDau = rs.getDate("ngaybatdau");
                Date ngayKetThuc = rs.getDate("ngayketthuc");
                String ghiChu = rs.getString("ghichu");
                int trangThai = rs.getInt("trangthai");
                DonNghiDTO donNghi = new DonNghiDTO(maDonNghi, hoTen, loaiNghi, ngayNopDon, ngayBatDau, ngayKetThuc, ghiChu, trangThai);
                ketQua.add(donNghi);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    
    public ArrayList<DonNghiDTO> danhSachDonNghiTheoTrangThai (int trangThai){
        ArrayList<DonNghiDTO> dsDonNghi = new ArrayList();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM donnghi WHERE trangthai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, trangThai);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                
                int maDonNghi = rs.getInt("madn");
                String hoTen = rs.getString("hoten");
                String loaiNghi = rs.getString("loai_nghi");
                Date ngayNopDon = rs.getDate("ngaynopdon");
                Date ngayBatDau = rs.getDate("ngaybatdau");
                Date ngayKetThuc = rs.getDate("ngayketthuc");
                String ghiChu = rs.getString("ghichu");
                int status = rs.getInt("trangthai");
                
                DonNghiDTO donNghi = new DonNghiDTO(maDonNghi, hoTen, loaiNghi, ngayNopDon, ngayBatDau, ngayKetThuc, ghiChu, trangThai);
                dsDonNghi.add(donNghi);
            }
            JDBCUtil.closeConnection(con);
         } catch (SQLException e) {
                e.printStackTrace();
        }
        return dsDonNghi;  
    }
    
    
    
}
