package DTO.ThongKe;

import DTO.DateRangeDTO;
import java.util.ArrayList;

public class ChiTietTongSanPhamDTO {
    private DateRangeDTO dateRange;
    private String query;
    private ArrayList<ThongKeSanPhamDTO> list;
    private int tongSoLuongNhap;

    public ChiTietTongSanPhamDTO(DateRangeDTO dateRange, String query, ArrayList<ThongKeSanPhamDTO> list, int tongSoLuongNhap) {
        this.dateRange = dateRange;
        this.query = query;
        this.list = list;
        this.tongSoLuongNhap = tongSoLuongNhap;
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
    
    public ArrayList<ThongKeSanPhamDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ThongKeSanPhamDTO> list) {
        this.list = list;
    }

    public int getTongSoLuongNhap() {
        return tongSoLuongNhap;
    }

    public void setTongSoLuongNhap(int tongSoLuongNhap) {
        this.tongSoLuongNhap = tongSoLuongNhap;
    }
}
