/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucVuDTO;
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
public class ChucVuDAO {
    public static ChucVuDAO getInstance() {
        return new ChucVuDAO();
    }
    
    public ArrayList<ChucVuDTO> getAllDanhSachChucVu() {
        ArrayList<ChucVuDTO> ketQua = new ArrayList<ChucVuDTO>();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chucvu WHERE trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {   
                ChucVuDTO cv = new ChucVuDTO();
                cv.setMaChucVu(rs.getInt("machucvu"));
                cv.setTenChucVu(rs.getString("tenchucvu"));
                cv.setLuongCoBan(rs.getInt("luongcoban"));
                ketQua.add(cv);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean insert (ChucVuDTO cv) {
        boolean ketQua = false;
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO chucvu ( `tenchucvu`, `luongcoban`)  VALUES (?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cv.getTenChucVu());
            pst.setInt(2, cv.getLuongCoBan());
            if(pst.executeUpdate() >= 1){
                ketQua = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean update(ChucVuDTO t) {
        boolean ketQua = false;
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE chucvu SET tenchucvu = ?, luongcoban = ? WHERE machucvu = ? AND trangthai = 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenChucVu());
            pst.setInt(2, t.getLuongCoBan());
            pst.setInt(3, t.getMaChucVu());
            if(pst.executeUpdate() >= 1){
                ketQua = true;
            }
//            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean hasName(String name){
        boolean result = false;
        
            try {
                java.sql.Connection con = JDBCUtil.getConnection();
                String sql = "SELECT tenchucvu FROM chucvu WHERE trangthai = 1 and tenchucvu = '" + name + "'";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                result = rs.next();
                JDBCUtil.closeConnection(con);
            } catch (Exception e) {
                System.out.println(e);
            } 
        return result;
    }
}
