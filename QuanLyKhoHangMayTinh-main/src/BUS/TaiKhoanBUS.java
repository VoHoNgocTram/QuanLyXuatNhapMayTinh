/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.NguoiDungDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanBUS {
    
       TaiKhoanDAO dao = new TaiKhoanDAO();

       public ArrayList<NguoiDungDTO> getList() throws SQLException {
        return dao.selectAll();
    } 
}
