package BUS;

import DTO.SanPhamDTO;
import DAO.SanPhamDAO;
import java.util.ArrayList;

public class SanPhamBUS {
    SanPhamDAO spDAO = new SanPhamDAO();
    
    public ArrayList<SanPhamDTO> getlistProducts(){
        return spDAO.getlistProduct();
    }
    
    public String addProduct(SanPhamDTO sp){
        if(spDAO.addProduct(sp))
            return "Thêm sản phẩm thành công!";
        return "Thêm sản phẩm thất bại!";
    }
    
    public String deleteProduct(int id){
        if(spDAO.deleteProduct(id))
            return "Sản phẩm đã ngừng hoạt động!";
        return "Ngừng hoạt động sản phẩm không thành công!";
    }
    
    public String updateProduct(SanPhamDTO sp){
        if(spDAO.updateProduct(sp))
            return "Sửa sản phẩm thành công!";
        return "Sửa sản phẩm thất bại!";
    }
    
    public String reuseProduct(int id){
        if(spDAO.reuseProduct(id))
            return "Sản phẩm đã sẵn sàng để kinh doanh!";
        return "Không thể mở hoạt động cho sản phẩm này!";
    }
}
