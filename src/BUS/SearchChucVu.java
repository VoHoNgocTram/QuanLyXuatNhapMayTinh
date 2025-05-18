/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import java.util.ArrayList;
import DTO.ChucVuDTO;
import DAO.ChucVuDAO;
/**
 *
 * @author downny
 */
public class SearchChucVu {
    public static SearchChucVu getInstance() {
        return new SearchChucVu();
    }
    
    public ArrayList<ChucVuDTO> searchTatCa(String text) {
        ArrayList<ChucVuDTO> result = new ArrayList<>();
        ArrayList<ChucVuDTO> armt = ChucVuDAO.getInstance().getAllDanhSachChucVu();
        for (var cv : armt){
            if (text.toLowerCase().contains(Integer.toString(cv.getMaChucVu()))
                    || cv.getTenChucVu().toLowerCase().contains(text.toLowerCase())) {
                result.add(cv);
            }
        }
        return result;
    }
    
    public ArrayList<ChucVuDTO> searchTenChucVu(String text){
        ArrayList<ChucVuDTO> result = new ArrayList<>();
        ArrayList<ChucVuDTO> armt = ChucVuDAO.getInstance().getAllDanhSachChucVu();
        for (var cv : armt) {
            if (cv.getTenChucVu().toLowerCase().contains(text.toLowerCase())) {
                result.add(cv);
            }
        }
        return result;
    }
    
    public ArrayList<ChucVuDTO> searchMaChucVu(String text){
        ArrayList<ChucVuDTO> result = new ArrayList<>();
        ArrayList<ChucVuDTO> armt = ChucVuDAO.getInstance().getAllDanhSachChucVu();
        for (var cv : armt) {
            if (text.toLowerCase().contains(Integer.toString(cv.getMaChucVu()))){
                result.add(cv);
            }
        }
        return result;
    }
    
}
