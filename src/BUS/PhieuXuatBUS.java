package BUS;

import DAO.PhieuXuatDAO;
import DTO.PhieuXuatDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuXuatBUS {
    PhieuXuatDAO dao = new PhieuXuatDAO();
    
    public PhieuXuatBUS() {
    }

    public ArrayList<PhieuXuatDTO> getList() throws SQLException {
        return dao.selectAll();
    }
    
    public String getNguoiTao(int maPhieuXuat) throws SQLException {
        return dao.getNguoiTao(maPhieuXuat);
    }
}
