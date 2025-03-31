/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.loaiSanPhamDAO;
import DTO.loaiSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author trant
 */

public class LoaiSanPhamBUS {
    loaiSanPhamDAO lspDAO = new loaiSanPhamDAO();
    
    public ArrayList<loaiSanPhamDTO> getTypeOfProduct(){
        return lspDAO.getTypeOfProduct();
    }
    
    public String addTypeOfProduct(String name){
        if(lspDAO.addTypeOfProduct(name))
            return "Thêm thương hiệu mới thành công!";
        return "Thêm thương hiệu mới thất bại!";
    }
    
    public String updateTypeOfProduct(String name, int id){
        if(lspDAO.updateTypeOfProduct(name, id))
            return "Sửa thương hiệu thành công!";
        return "Sửa thương hiệu thất bại!";
    }
    
    public String deleteTypeOfProduct(int id){
        if(lspDAO.deleteTypeOfProduct(id))
            return "Xóa thương hiệu thành công!";
        return "Xóa thương hiệu thất bại!";
    }
}
