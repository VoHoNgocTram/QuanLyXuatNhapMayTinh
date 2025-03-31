package DTO.ThongKe;

import DTO.DateRangeDTO;
import java.util.ArrayList;

public class ChiTietTongTonKhoDTO {
    private DateRangeDTO dateRange;
    private String query;
    private ArrayList<ThongKeTonKhoDTO> list;
    private Long tongTonDauKy, tongNhapTrongKy, tongXuatTrongKy, tongTonCuoiKy;

    public ChiTietTongTonKhoDTO(DateRangeDTO dateRange, String query, ArrayList<ThongKeTonKhoDTO> list, Long tongTonDauKy, Long tongNhapTrongKy, Long tongXuatTrongKy, Long tongTonCuoiKy) {
        this.dateRange = dateRange;
        this.query = query;
        this.list = list;
        this.tongTonDauKy = tongTonDauKy;
        this.tongNhapTrongKy = tongNhapTrongKy;
        this.tongXuatTrongKy = tongXuatTrongKy;
        this.tongTonCuoiKy = tongTonCuoiKy;
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
    
    public ArrayList<ThongKeTonKhoDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ThongKeTonKhoDTO> list) {
        this.list = list;
    }

    public Long getTongTonDauKy() {
        return tongTonDauKy;
    }

    public void setTongTonDauKy(Long tongTonDauKy) {
        this.tongTonDauKy = tongTonDauKy;
    }

    public Long getTongNhapTrongKy() {
        return tongNhapTrongKy;
    }

    public void setTongNhapTrongKy(Long tongNhapTrongKy) {
        this.tongNhapTrongKy = tongNhapTrongKy;
    }

    public Long getTongXuatTrongKy() {
        return tongXuatTrongKy;
    }

    public void setTongXuatTrongKy(Long tongXuatTrongKy) {
        this.tongXuatTrongKy = tongXuatTrongKy;
    }

    public Long getTongTonCuoiKy() {
        return tongTonCuoiKy;
    }

    public void setTongTonCuoiKy(Long tongTonCuoiKy) {
        this.tongTonCuoiKy = tongTonCuoiKy;
    }
}
