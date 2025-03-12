/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NguoiDungDTO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {

    public static DAO.TaiKhoanDAO getInstance() {
        return new DAO.TaiKhoanDAO();
    }

    public ArrayList<NguoiDungDTO> selectAll() {
        ArrayList<NguoiDungDTO> list = new ArrayList<NguoiDungDTO>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM nguoidung where trangthai = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String taikhoan = rs.getString("taikhoan");
                String matkhau = rs.getString("matkhau");
                String hoten = rs.getString("hoten");
                String email = rs.getString("email");
                int manhomquyen = rs.getInt("manhomquyen");
                int trangthai = rs.getInt("trangthai");
                NguoiDungDTO phieuxuat = new NguoiDungDTO(taikhoan, matkhau, hoten, email, manhomquyen, trangthai);
                list.add(phieuxuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteAccount(String tk) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE nguoidung SET trangthai = 0 WHERE taikhoan = '" + tk + "'";
//            System.out.print(sql);
            Statement stmt = con.createStatement();
            if (stmt.executeUpdate(sql) >= 1) {
                result = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean addAccount(String fullName, String user, String password, String role, String email, Integer trangthai) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO nguoidung(taikhoan, matkhau, hoten, email , manhomquyen, trangthai) VALUES"
                    + "('" + fullName + "','" + password + "','" + user + "','" + email + "'," + 1 + "," + trangthai + ")";
   

            Statement stmt = con.createStatement();
            if (stmt.executeUpdate(sql) >= 1) {
                result = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }

    public boolean updateAccount(String fullName, String user, String password, String role, String email) {
        boolean result = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE nguoidung SET hoten  = '" + user + "',matkhau  = '" + password + "'WHERE taikhoan = " + "'"+ fullName+ "'";
                     System.out.print(sql);
            Statement stmt = con.createStatement();
            if (stmt.executeUpdate(sql) >= 1) {
                result = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
