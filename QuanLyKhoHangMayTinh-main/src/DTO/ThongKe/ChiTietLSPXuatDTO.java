package DTO.ThongKe;

import DTO.DateRangeDTO;
import java.util.ArrayList;

public class ChiTietLSPXuatDTO {
    private DateRangeDTO dateRange;
    private String query;
    private ArrayList<ThongKeLoaiSanPhamDTO> list;
    private int tongSoLuongXuat;

    public ChiTietLSPXuatDTO() {
    }
    
    public ChiTietLSPXuatDTO(DateRangeDTO dateRange, String query, ArrayList<ThongKeLoaiSanPhamDTO> list, int tongSoLuongXuat) {
        this.dateRange = dateRange;
        this.query = query;
        this.list = list;
        this.tongSoLuongXuat = tongSoLuongXuat;
    }

    public DateRangeDTO getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRangeDTO dateRange) {
        this.dateRange = dateRange;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    public ArrayList<ThongKeLoaiSanPhamDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ThongKeLoaiSanPhamDTO> list) {
        this.list = list;
    }

    public int getTongSoLuongXuat() {
        return tongSoLuongXuat;
    }

    public void setTongSoLuongXuat(int tongSoLuongXuat) {
        this.tongSoLuongXuat = tongSoLuongXuat;
    }
}
