/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.khoDAO;
import DTO.KhoDTO;
import java.util.ArrayList;

/**
 *
 * @author trant
 */
public class KhoBUS {
    
    khoDAO khDAO = new khoDAO();
    
    public ArrayList<KhoDTO> getListWareHouse() {
        return khDAO.getListWareHouse();
    }
    
    public String addWareHouse(KhoDTO kho){
        if(khDAO.addWareHouse(kho))
            return "Thêm kho mới thành công!";
        return "Thêm kho mới thất bại!";
    }
    
    public String updateWareHouse(KhoDTO kho){
        if(khDAO.updateWareHouse(kho))
            return "Cập nhật kho mới thành công!";
        return "Cập nhật kho mới thất bại!";
    }
    
    public String deleteWareHouse(int makho){
        if(khDAO.deleteWareHouse(makho))
            return "Xóa kho thành công!";
        return "Xóa kho thất bại!";
    }
}
