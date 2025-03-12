package BUS;

import DAO.tonKhoDAO;
import DTO.ChiTietTonKhoDTO;
import DTO.SanPhamTonKhoDTO;
import helper.Exception.EmptyFieldException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TonKhoBUS {
    public TonKhoBUS() {
    }
    
    public ArrayList<ChiTietTonKhoDTO> getDetailTonKho(int masanpham, int makho, boolean restrictGiaNhap) throws SQLException {
        return tonKhoDAO.getInstance().getDetailTonKho(masanpham, makho, restrictGiaNhap);
    }
    
    public ArrayList<SanPhamTonKhoDTO> getInventoryProduct(String productName) throws SQLException {
        return tonKhoDAO.getInstance().getInventoryProduct(productName);
    }
    
    public ArrayList<ChiTietTonKhoDTO> getInventoryProductDetail(int productId, boolean restrictGiaNhap) throws SQLException {
        return tonKhoDAO.getInstance().getInventoryProductDetail(productId, restrictGiaNhap);
    }
    
    public int handleXuatKho(ChiTietTonKhoDTO product, String inputQuantity) throws EmptyFieldException, SQLException {
        if (inputQuantity.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập số lượng", "");
        }
        int quantity;
        try {
            quantity = Integer.parseInt(inputQuantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số lượng không hợp lệ");
        }
        if (quantity < 1 || quantity > product.getSoLuongTonKho()) {
            throw new IllegalArgumentException("Số lượng không hợp lệ");
        }
        return tonKhoDAO.getInstance().updateXuatKho(product, quantity);
    }
}
