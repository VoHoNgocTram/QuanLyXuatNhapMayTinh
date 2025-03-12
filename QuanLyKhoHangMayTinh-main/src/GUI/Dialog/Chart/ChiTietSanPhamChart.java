package GUI.Dialog.Chart;

import DTO.DateRangeDTO;
import DTO.ThongKe.ChiTietSanPhamNhapDTO;
import helper.DateHelper;
import java.util.ArrayList;
import javax.swing.JDialog;
import org.knowm.xchart.PieChart;

public class ChiTietSanPhamChart extends PieChartGUI {
    private ArrayList<ChiTietSanPhamNhapDTO> arr;
    
    public ChiTietSanPhamChart(JDialog parent, String title, ArrayList<ChiTietSanPhamNhapDTO> arr, DateRangeDTO dateRange) {
        super(parent, title);
        this.arr = arr;
        String chartTitle = title + " (" + DateHelper.dateRangeToString(dateRange, DateHelper.DATE_FORMATTER, " - ") + ")";
        super.displayChart(chartTitle);
    }

    @Override
    public void addSeries(PieChart chart) {
        boolean paintAll = arr.size() <= 7;
        int lastElementToPrintAll = paintAll ? arr.size() - 1 : 6;
        if (!paintAll) {
            int valueOther = 0;
            for (int i = 7; i < arr.size(); ++i) {
                valueOther += arr.get(i).getTongSoLuongNhap();
            }
            chart.addSeries("Khác", valueOther);
        }
        for (int i = lastElementToPrintAll; i >= 0; --i) {
            chart.addSeries(arr.get(i).getTenNhaCungCap(), arr.get(i).getTongSoLuongNhap());
        }
    }
}
