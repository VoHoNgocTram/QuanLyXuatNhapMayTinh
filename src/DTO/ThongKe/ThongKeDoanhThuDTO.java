package DTO.ThongKe;

import java.util.Date;

public class ThongKeDoanhThuDTO {
    private Date timeline;
    private Long expense;
    private Long income;
    private Long profit;

    public ThongKeDoanhThuDTO(Date timeline, Long expense, Long income, Long profit) {
        this.timeline = timeline;
        this.expense = expense;
        this.income = income;
        this.profit = profit;
    }

    public Date getTimeline() {
        return timeline;
    }

    public void setTimeline(Date timeline) {
        this.timeline = timeline;
    }

    public Long getExpense() {
        return expense;
    }

    public void setExpense(Long expense) {
        this.expense = expense;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }
}
