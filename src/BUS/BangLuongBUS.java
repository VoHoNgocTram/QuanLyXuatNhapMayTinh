/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BangLuongDAO;
import DTO.BangLuongDTO;
import DTO.NguoiDungDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class BangLuongBUS {
    BangLuongDAO blDAO = new BangLuongDAO();
    public ArrayList<BangLuongDTO> selectAll(){
        return blDAO.selectAll();
    }
    public BangLuongDTO getBangLuongByMa(int mabl){
        return blDAO.getBangLuongByMa(mabl);
    }
    public boolean insertSalary(BangLuongDTO salary){
        return blDAO.insertSalary(salary);
    }
    public List<NguoiDungDTO> getDanhSachNhanVienChuaCoLuongThang(int thang, int nam){
        return blDAO.getDanhSachNhanVienChuaCoLuongThang(thang, nam);
    }
    public boolean updateLuong(BangLuongDTO bl) {
        return blDAO.updateLuong(bl);
    }
}
