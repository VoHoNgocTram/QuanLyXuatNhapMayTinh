/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import database.JDBCUtil;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author trant
 */
public class ChiTietPhieuNhapDAO {
    
    public static ChiTietPhieuNhapDAO getInstance(){
        return new ChiTietPhieuNhapDAO();
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getDetailPhieuNhap(int maPhieuNhap) throws SQLException{
        ArrayList<ChiTietPhieuNhapDTO> ctpnList = new ArrayList<>();
        try{
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM chitietphieunhap WHERE maphieunhap = " + maPhieuNhap;
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhapDTO ctpn =  new ChiTietPhieuNhapDTO();
                ctpn.setMaPhieuNhap(maPhieuNhap);
                ctpn.setDonGia(rs.getInt("dongia"));
                ctpn.setMaNhaCungCap(rs.getInt("manhacungcap"));
                ctpn.setMaSanPham(rs.getInt("masanpham"));
                ctpn.setSoLuongNhap(rs.getInt("soluongnhap"));
                ctpn.setSoLuongTonKho(rs.getInt("soluongtonkho"));
                ctpnList.add(ctpn);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return ctpnList;
    }
    
    public boolean updateCTPN(ChiTietPhieuNhapDTO ctpn, int maNCC){
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE chitietphieunhap SET manhacungcap = " + maNCC + " , soluongnhap = " + ctpn.getSoLuongNhap() + "  , dongia = " + ctpn.getDonGia() + "  WHERE maphieunhap = " + ctpn.getMaPhieuNhap() + " and masanpham = " + ctpn.getMaSanPham() + " and manhacungcap = " + ctpn.getMaNhaCungCap();      
            Statement stmt = con.createStatement();
            if(stmt.executeUpdate(sql) > 0){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    

    public boolean  updateSoluongThucTe(ChiTietPhieuNhapDTO ctpn){
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE chitietphieunhap SET soluongthucte = ?, soluongtonkho = ? WHERE maphieunhap = ? and manhacungcap = ? and masanpham = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ctpn.getSoLuongThucTe());
            stmt.setInt(2, ctpn.getSoLuongThucTe());
            stmt.setInt(3, ctpn.getMaPhieuNhap());
            stmt.setInt(4, ctpn.getMaNhaCungCap());
            stmt.setInt(5, ctpn.getMaSanPham());
           
            if(stmt.executeUpdate() >= 1){
                result = true ;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
