/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVuDTO;
import java.util.ArrayList;

/**
 *
 * @author downny
 */
public class ChucVuBUS {
     ChucVuDAO cvDAO = new ChucVuDAO();
    
    public ArrayList<ChucVuDTO> getAllDanhSachChucVu(){
        return cvDAO.getAllDanhSachChucVu();
    }
    
        public String addChucVu(ChucVuDTO cv){
        if(cvDAO.insert(cv))
            return "Thêm thành công !"; 
        return "Thêm thất bại !"; 
    }

    public String updateChucVu(ChucVuDTO cv){
        if(cvDAO.update(cv))
           return "Cập nhật thành công!";
        return "Cập nhật thất bại!";
    }
}
