package BUS;


import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author trant
 */
public class ChiTietPhieuNhapBUS {
    
    ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
    
    public String updateChiTietPhieuNhap(ChiTietPhieuNhapDTO ctpn, int maNCC){
        if(ctpnDAO.getInstance().updateCTPN(ctpn , maNCC))
            return "Cập nhật phiếu nhập thành công! ";
        return "Cập nhật phiếu nhập thất bại!";
    }
}
