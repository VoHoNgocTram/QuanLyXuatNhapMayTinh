package BUS;

import DAO.NhomQuyenDAO;
import DTO.NhomQuyenDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhomQuyenBUS {
    private final NhomQuyenDAO nqDAO = new NhomQuyenDAO();

    public NhomQuyenBUS() {
    }
    public ArrayList<NhomQuyenDTO> getAllDSNhomQuyen() {
        return nqDAO.getAllNhomQuyen();
    }
    
    public NhomQuyenDTO getPermissionById(int maNhomQuyen) throws SQLException {
        return nqDAO.getPermissionById(maNhomQuyen);
    }
    
    public ArrayList<NhomQuyenDTO> getListRoleBelowPriority(int priority) throws SQLException {
        return nqDAO.getListRoleBelowPriority(priority);
    }
}
