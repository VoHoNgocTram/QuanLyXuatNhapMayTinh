package DTO.ThongKe;

import DTO.DateRangeDTO;
import java.util.ArrayList;

public class ChiTietDoanhThuDTO {
    private DateRangeDTO dateRange;
    private ArrayList<ThongKeDoanhThuDTO> list;
    private Long totalExpense, totalIncome, totalProfit;

    public ChiTietDoanhThuDTO() {
    }
    
    public ChiTietDoanhThuDTO(DateRangeDTO dateRange, ArrayList<ThongKeDoanhThuDTO> list, Long totalExpense, Long totalIncome, Long totalProfit) {
        this.dateRange = dateRange;
        this.list = list;
        this.totalExpense = totalExpense;
        this.totalIncome = totalIncome;
        this.totalProfit = totalProfit;
    }

    public DateRangeDTO getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRangeDTO dateRange) {
        this.dateRange = dateRange;
    }

    public ArrayList<ThongKeDoanhThuDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ThongKeDoanhThuDTO> list) {
        this.list = list;
    }

    public Long getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Long totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Long getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Long totalProfit) {
        this.totalProfit = totalProfit;
    }
}
