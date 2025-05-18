package DAO;

import DTO.NhomQuyenDTO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhomQuyenDAO {
    public NhomQuyenDAO() {
    }
    public ArrayList<NhomQuyenDTO> getAllNhomQuyen() {
        ArrayList<NhomQuyenDTO> dsNhomQuyen = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM nhomquyen";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                NhomQuyenDTO nq = new NhomQuyenDTO(
                    rs.getInt("manhomquyen"),
                    rs.getString("tennhomquyen"),
                    rs.getInt("douutien"),
                    rs.getInt("trangthai")
                );
                dsNhomQuyen.add(nq);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhomQuyen;
    }
    public NhomQuyenDTO getPermissionById(int maNhomQuyen) throws SQLException {
        NhomQuyenDTO info = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM nhomquyen WHERE manhomquyen = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, maNhomQuyen);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tenNhomQuyen = rs.getString("tennhomquyen");
                int trangThai = rs.getInt("trangthai");
                info = new NhomQuyenDTO(maNhomQuyen, tenNhomQuyen, trangThai);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return info;
    }
    
    public ArrayList<NhomQuyenDTO> getListRoleBelowPriority(int priority) throws SQLException {
        ArrayList<NhomQuyenDTO> result = new ArrayList<>();
        String query = "SELECT * FROM nhomquyen WHERE trangthai = ? AND douutien < ?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setInt(2, priority);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhomQuyen = rs.getInt("manhomquyen");
                String tenNhomQuyen = rs.getString("tennhomquyen");
                int doUuTien = rs.getInt("douutien");
                result.add(new NhomQuyenDTO(maNhomQuyen, tenNhomQuyen, doUuTien, 1));
            }
            ps.close();
            rs.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
