/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class NhanVienBUS {
    NhanVienDAO nvDAO = new NhanVienDAO();
    public ArrayList<NhanVienDTO> getAllDSNhanVien() {
        return nvDAO.getAllDSNhanVien();
    }
    public NhanVienDTO getNhanVienByMa(int maNV){
        return nvDAO.getNhanVienByMa(maNV);
    }
}
