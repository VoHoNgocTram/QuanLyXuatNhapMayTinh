/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DonNghiDAO;
import DTO.DonNghiDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author downny
 */
public class SearchDonNghi {
    public static SearchDonNghi getInstance(){
        return new SearchDonNghi();
    }
    
    public ArrayList<DonNghiDTO> searchTatCa(String text) {
        ArrayList<DonNghiDTO> result = new ArrayList<>();
        ArrayList<DonNghiDTO> armt = DonNghiDAO.getInstance().getAllDanhSachDonNghi();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (var dn : armt) {
            String ngayNopDonStr = dateFormat.format(dn.getNgayNopDon());
            if (text.toLowerCase().contains(Integer.toString(dn.getMaDonNghi()))
                    || dn.getHoTen().toLowerCase().contains(text.toLowerCase())
                    || dn.getLoaiNghi().toLowerCase().contains(text.toLowerCase())
                    || ngayNopDonStr.toLowerCase().contains(text.toLowerCase()))  {
                result.add(dn);
            }
        }
        return result;
    }
}
