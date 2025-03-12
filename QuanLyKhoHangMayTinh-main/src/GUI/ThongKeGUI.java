package GUI;

import GUI.Dialog.Chart.LoaiSanPhamChart;
import GUI.Dialog.Chart.LineChart;
import BUS.ThongKeBUS;
import DTO.DateRangeDTO;
import DTO.NguoiDungDTO;
import DTO.ThongKe.*;
import GUI.Dialog.ChiTietLoaiSanPhamDialog;
import GUI.Dialog.ChiTietSanPhamNhapDialog;
import GUI.Dialog.SelectDateDialog;
import helper.CustomTableCellRenderer;
import helper.DateHelper;
import helper.FileHelper;
import helper.LoaiSanPhamTableModel;
import helper.NumberHelper;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeGUI extends javax.swing.JInternalFrame {
    private final ThongKeBUS tkBUS = new ThongKeBUS();
    private ChiTietTongTonKhoDTO dataTonKho;
    private ChiTietDoanhThuDTO dataDoanhThu;
    private ChiTietTongSanPhamDTO dataSanPham;
    private ChiTietLSPXuatDTO dataLoaiSanPham;
    private DefaultTableModel dtmTonKho;
    private DefaultTableModel dtmDoanhThu;
    private DefaultTableModel dtmSanPham;
    private LoaiSanPhamTableModel tmLoaiSanPham;
    
    private final LocalDateTime defaultOldestDate = LocalDateTime.of(2019, 1, 1, 0, 0);
    private LocalDateTime oldestDate;
    private final LocalDateTime nowDateTime = LocalDateTime.now();
    private final LocalDateTime lastMonthDateTime = nowDateTime.minusMonths(1);
    private final LocalDateTime last2MonthDateTime = nowDateTime.minusMonths(2);
    private final int curYear = nowDateTime.getYear();
    private final int curMonth = nowDateTime.getMonthValue();
    
    private final String CB_VALUE_LAST_7_DAYS = "7 ngày qua";
    private final String CB_VALUE_LAST_30_DAYS = "30 ngày qua";
    private final String CB_VALUE_LAST_90_DAYS = "90 ngày qua";
    private final String CB_VALUE_LAST_365_DAYS = "365 ngày qua";
    public static final String CB_VALUE_LIFETIME = "Toàn thời gian";
    private final String CB_VALUE_CURRENT_YEAR = Integer.toString(curYear);
    private final String CB_VALUE_LAST_YEAR = Integer.toString(curYear - 1);
    private final String CB_VALUE_CURRENT_MONTH = "Tháng " + curMonth;
    private final String CB_VALUE_LAST_MONTH = "Tháng " + lastMonthDateTime.getMonthValue() + (lastMonthDateTime.getYear() == curYear ? "" : "/" + lastMonthDateTime.getYear());
    private final String CB_VALUE_LAST_2_MONTHS = "Tháng " + last2MonthDateTime.getMonthValue() + (last2MonthDateTime.getYear() == curYear ? "" : "/" + last2MonthDateTime.getYear());
    private final String CB_VALUE_CUSTOM = "Tuỳ chỉnh";
    
    public static final String CB_VALUE_GROUP_BY_DATE = "Ngày";
    public static final String CB_VALUE_GROUP_BY_MONTH = "Tháng";
    public static final String CB_VALUE_GROUP_BY_YEAR = "Năm";
    private final int DT_QUERY_MAX_DATES = 90;
    
    private DateRangeDTO drTonKho;
    private DateRangeDTO drDoanhThu;
    private DateRangeDTO drSanPham;
    private DateRangeDTO drLoaiSanPham;
    
    private String queryTonKho;
    private String querySanPham;
    private String queryLoaiSanPham;
    
    private boolean isLoadingTonKho;
    private boolean isLoadingDoanhThu;
    private boolean isLoadingSanPham;
    private boolean isLoadingLoaiSanPham;
    
    private String doanhThuGroupBy;
    
    public ThongKeGUI(NguoiDungDTO user) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initList();
        initTable();
        setToolTip();
        getOldestDate();
        initDoanhThuOption();
        initDateRange();
        setModelComboBox();
        initLoadingState();
        initQueryString();
        thongKeTonKho(drTonKho, queryTonKho);
        thongKeDoanhThu(drDoanhThu, doanhThuGroupBy);
        thongKeSanPham(drSanPham, querySanPham);
        thongKeLoaiSanPham(drLoaiSanPham, queryLoaiSanPham);
        // Authorize
        // ...
    }
    
    public ThongKeGUI() {
        initComponents();
    }
    
    public boolean isIsLoadingTonKho() {
        return isLoadingTonKho;
    }

    public void setIsLoadingTonKho(boolean isLoadingTonKho) {
        this.isLoadingTonKho = isLoadingTonKho;
    }

    public String getQueryTonKho() {
        return queryTonKho;
    }

    public void setQueryTonKho(String queryTonKho) {
        this.queryTonKho = queryTonKho;
    }

    public boolean isIsLoadingDoanhThu() {
        return isLoadingDoanhThu;
    }

    public void setIsLoadingDoanhThu(boolean isLoadingDoanhThu) {
        this.isLoadingDoanhThu = isLoadingDoanhThu;
    }
    
    public String getQuerySanPham() {
        return querySanPham;
    }

    public void setQuerySanPham(String querySanPham) {
        this.querySanPham = querySanPham;
    }

    public boolean isIsLoadingSanPham() {
        return isLoadingSanPham;
    }

    public void setIsLoadingSanPham(boolean isLoadingSanPham) {
        this.isLoadingSanPham = isLoadingSanPham;
    }

    public String getQueryLoaiSanPham() {
        return queryLoaiSanPham;
    }

    public void setQueryLoaiSanPham(String queryLoaiSanPham) {
        this.queryLoaiSanPham = queryLoaiSanPham;
    }

    public boolean isIsLoadingLoaiSanPham() {
        return isLoadingLoaiSanPham;
    }

    public void setIsLoadingLoaiSanPham(boolean isLoadingLoaiSanPham) {
        this.isLoadingLoaiSanPham = isLoadingLoaiSanPham;
    }
    
    public String getDoanhThuGroupBy() {
        return doanhThuGroupBy;
    }

    public void setDoanhThuGroupBy(String doanhThuGroupBy) {
        this.doanhThuGroupBy = doanhThuGroupBy;
    }
    
    private void initList() {
        this.dataLoaiSanPham = new ChiTietLSPXuatDTO();
        this.dataLoaiSanPham.setList(new ArrayList<>());
    }
    
    private void initTable() {
        dtmTonKho = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tồn đầu kỳ", "Nhập trong kỳ", "Xuất trong kỳ", "Tồn cuối kỳ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        tbTonKho.setModel(dtmTonKho);
        tbTonKho.getColumnModel().getColumn(1).setPreferredWidth(400);
        
        dtmDoanhThu = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[]", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        tbDoanhThu.setModel(dtmDoanhThu);
        
        dtmSanPham = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tbSanPham.setModel(dtmSanPham);
        tbSanPham.getColumnModel().getColumn(2).setPreferredWidth(400);

        String[] tbLoaiSanPhamColumnNames = {"Mã", "Loại sản phẩm", "Số lượng xuất"};
        Object[] loaiSanPhamTotalRow = {"", "Tổng", ""};
        this.tmLoaiSanPham = new LoaiSanPhamTableModel(this.dataLoaiSanPham.getList(), tbLoaiSanPhamColumnNames, loaiSanPhamTotalRow);
        tbLoaiSanPham.setModel(tmLoaiSanPham);
        tbLoaiSanPham.getColumnModel().getColumn(0).setMinWidth(0);
        tbLoaiSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
        tbLoaiSanPham.getColumnModel().getColumn(0).setWidth(0);
        tbLoaiSanPham.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    private void initDateRange() {
        // Set fromDate to 7 days ago, toDate to today
        LocalDateTime fromDate = LocalDateTime.now().minusDays(6);
        drTonKho = new DateRangeDTO(fromDate, LocalDateTime.now());
        drDoanhThu = new DateRangeDTO(fromDate, LocalDateTime.now());
        drSanPham = new DateRangeDTO(fromDate, LocalDateTime.now());
        drLoaiSanPham = new DateRangeDTO(fromDate, LocalDateTime.now());
    }
    
    private void setModelComboBox() {
        javax.swing.JComboBox[] comboBox = {cbTonKhoDate, cbDoanhThuDate, cbSanPhamDate, cbLoaiSanPhamDate};
        for (javax.swing.JComboBox cb : comboBox) {
            cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { CB_VALUE_LAST_7_DAYS, CB_VALUE_LAST_30_DAYS, CB_VALUE_LAST_90_DAYS, 
                CB_VALUE_LAST_365_DAYS, CB_VALUE_LIFETIME, CB_VALUE_CURRENT_YEAR, CB_VALUE_LAST_YEAR, CB_VALUE_CURRENT_MONTH, CB_VALUE_LAST_MONTH, 
                CB_VALUE_LAST_2_MONTHS, CB_VALUE_CUSTOM }));
            cb.setSelectedIndex(0);
        }
        
        cbDoanhThuQueryGroupBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { CB_VALUE_GROUP_BY_DATE, CB_VALUE_GROUP_BY_MONTH, 
            CB_VALUE_GROUP_BY_YEAR }));
        cbDoanhThuQueryGroupBy.setSelectedIndex(0);
    }

    private void initLoadingState() {
        setIsLoadingTonKho(false);
        setIsLoadingDoanhThu(false);
        setIsLoadingSanPham(false);
        setIsLoadingLoaiSanPham(false);
    }
    
    private void initQueryString() {
        setQueryTonKho("");
        setQuerySanPham("");
        setQueryLoaiSanPham("");
    }
    
    private void setToolTip() {
        String groupByToolTipText = "<html>Khi thống kê trong khoảng thời gian trên " + DT_QUERY_MAX_DATES + " ngày,<br>kết quả thống kê mặc định sẽ được nhóm theo tháng.</html>";
        iconInfo.setToolTipText(groupByToolTipText);
        ToolTipManager.sharedInstance().registerComponent(iconInfo);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
    }
    
    private void initDoanhThuOption() {
        setDoanhThuGroupBy("date");
    }
    
    private boolean handleDoanhThuOptionChanged() {
        boolean modifiedUserOption = false;
        
        if (drDoanhThu.getFromDate() != null && drDoanhThu.getToDate() != null) {
            long differenceInDays = ChronoUnit.DAYS.between(drDoanhThu.getFromDate(), drDoanhThu.getToDate());
            if (differenceInDays <= DT_QUERY_MAX_DATES)
                return modifiedUserOption;
        }
        
        switch (String.valueOf(cbDoanhThuQueryGroupBy.getSelectedItem())) {
            case CB_VALUE_GROUP_BY_DATE:
                cbDoanhThuQueryGroupBy.setSelectedIndex(1);
                setDoanhThuGroupBy("month");
                modifiedUserOption = true;
                break;
            default:
        }

        return modifiedUserOption;
    }
    
    public void setDateRange(String dateRangeName, DateRangeDTO dateRange) {
        if (dateRangeName.equals("tonkho")) {
            drTonKho = dateRange;
            displayDateRangeToLabel(drTonKho, lblTonKhoDate);
        } else if (dateRangeName.equals("sanpham")) {
            drSanPham = dateRange;
            displayDateRangeToLabel(drSanPham, lblSanPhamDate);
        } else if (dateRangeName.equals("loaisanpham")) {
            drLoaiSanPham = dateRange;
            displayDateRangeToLabel(drLoaiSanPham, lblLoaiSanPhamDate);
        } else if (dateRangeName.equals("doanhthu")) {
            drDoanhThu = dateRange;
            displayDateRangeToLabel(drDoanhThu, lblDoanhThuDate);
        }
    }
    
    public void setIsLoading(String name, boolean value) {
        if (name.equals("tonkho")) {
            setIsLoadingTonKho(value);
        } else if (name.equals("doanhthu")) {
            setIsLoadingDoanhThu(value);
        } else if (name.equals("sanpham")) {
            setIsLoadingSanPham(value);
        } else if (name.equals("loaisanpham")) {
            setIsLoadingLoaiSanPham(value);
        }
    }
    
    private void displayDateRangeToLabel(DateRangeDTO dateRange, javax.swing.JLabel label) {
        if (dateRange.getFromDate() == null && dateRange.getToDate() == null) {
            label.setText(CB_VALUE_LIFETIME); 
        } else {
            label.setText(DateHelper.dateRangeToString(dateRange, DateHelper.DATE_FORMATTER, " - "));
        }
    }

    private void handleComboBoxChanged(String name, String value, DateRangeDTO dateRange, javax.swing.JLabel label) {
        if (value.equals(CB_VALUE_CUSTOM)) {
            SelectDateDialog selectDate = new SelectDateDialog(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, name, dateRange, 0, false, defaultOldestDate);
            selectDate.setVisible(true);
            return;
        }
        if (value.equals(CB_VALUE_LAST_7_DAYS)) {
            dateRange.setFromDate(nowDateTime.minusDays(6));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LAST_30_DAYS)) {
            dateRange.setFromDate(nowDateTime.minusDays(29));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LAST_90_DAYS)) {
            dateRange.setFromDate(nowDateTime.minusDays(89));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LAST_365_DAYS)) {
            dateRange.setFromDate(nowDateTime.minusDays(364));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LIFETIME)) {
            dateRange.setFromDate(oldestDate);
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_CURRENT_YEAR)) {
            dateRange.setFromDate(LocalDateTime.of(curYear, 1, 1, 0, 0));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LAST_YEAR)) {
            LocalDateTime firstDayOfCurYear = LocalDateTime.of(curYear, 1, 1, 0, 0, 0);
            LocalDateTime lastDayOfLastYear = firstDayOfCurYear.minusDays(1);
            dateRange.setFromDate(LocalDateTime.of(curYear - 1, 1, 1, 0, 0));
            dateRange.setToDate(lastDayOfLastYear);
        } else if (value.equals(CB_VALUE_CURRENT_MONTH)) {
            dateRange.setFromDate(LocalDateTime.of(curYear, nowDateTime.getMonthValue(), 1, 0, 0));
            dateRange.setToDate(nowDateTime);
        } else if (value.equals(CB_VALUE_LAST_MONTH)) {
            LocalDateTime firstDayOfCurMonth = LocalDateTime.of(curYear, nowDateTime.getMonthValue(), 1, 0, 0);
            LocalDateTime lastDayOfLastMonth = firstDayOfCurMonth.minusDays(1);
            dateRange.setFromDate(LocalDateTime.of(lastMonthDateTime.getYear(), lastMonthDateTime.getMonthValue(), 1, 0, 0));
            dateRange.setToDate(lastDayOfLastMonth);
        } else if (value.equals(CB_VALUE_LAST_2_MONTHS)) {
            LocalDateTime firstDayOfLastMonth = LocalDateTime.of(lastMonthDateTime.getYear(), lastMonthDateTime.getMonthValue(), 1, 0, 0);
            LocalDateTime lastDayOfLast2Months = firstDayOfLastMonth.minusDays(1);
            dateRange.setFromDate(LocalDateTime.of(last2MonthDateTime.getYear(), last2MonthDateTime.getMonthValue(), 1, 0, 0));
            dateRange.setToDate(lastDayOfLast2Months);
        }
        
        setDateRange(name, dateRange);
        setIsLoading(name, true);
    }
    
    private void handleReload(javax.swing.JButton btnReload, String name, DateRangeDTO dateRange, String query) {
        // Disable button
        btnReload.setEnabled(false);
        
        thongKe(name, dateRange, query);
        
        // Enable button after 2 seconds
        int timeoutMs = 2000;
        Timer timer = new Timer(timeoutMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReload.setEnabled(true);
                btnReload.requestFocus();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void handleViewProductDetail(int row) {
        int maSanPham = Integer.parseInt(tbSanPham.getValueAt(row, 0).toString());
        String loaiSanPham = tbSanPham.getValueAt(row, 1).toString();
        String tenSanPham = tbSanPham.getValueAt(row, 2).toString();
        int soLuongNhap = Integer.parseInt(tbSanPham.getValueAt(row, 3).toString());
        ThongKeSanPhamDTO product = new ThongKeSanPhamDTO(maSanPham, loaiSanPham, tenSanPham, soLuongNhap);
        ChiTietSanPhamNhapDialog detailDialog = new ChiTietSanPhamNhapDialog(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, drSanPham, product);
        detailDialog.setVisible(true);
    }
    
    private void handleViewProductTypeDetail(int row) {
        int maLoaiSanPham = Integer.parseInt(tbLoaiSanPham.getValueAt(row, 0).toString());
        String tenLoaiSanPham = tbLoaiSanPham.getValueAt(row, 1).toString();
        int soLuong = Integer.parseInt(tbLoaiSanPham.getValueAt(row, 2).toString());
        ThongKeLoaiSanPhamDTO productType = new ThongKeLoaiSanPhamDTO(maLoaiSanPham, tenLoaiSanPham, soLuong);
        ChiTietLoaiSanPhamDialog detailDialog = new ChiTietLoaiSanPhamDialog(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, drLoaiSanPham, productType);
        detailDialog.setVisible(true);
    }
    
    // For lifetime date range
    private void getOldestDate() {
        LocalDateTime result = null;
        try {
            result = tkBUS.getOldestDate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.oldestDate = result == null ? defaultOldestDate : result;
    }
    
    private void thongKeTonKho(DateRangeDTO dateRange, String productName) {
        ChiTietTongTonKhoDTO data = null;
        try {
            data = tkBUS.thongKeTonKho(dateRange, productName);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.dataTonKho = data;
        dtmTonKho.setRowCount(0);
        
        // Add total row
        Object[] totalRow = {"Tổng", "", data.getTongTonDauKy(), data.getTongNhapTrongKy(), data.getTongXuatTrongKy(), data.getTongTonCuoiKy()};
        dtmTonKho.addRow(totalRow);
        
        ArrayList<ThongKeTonKhoDTO> arr = data.getList();
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeTonKhoDTO tktkDTO = arr.get(i);
            int maSanPham = tktkDTO.getMaSanPham();
            String tenSanPham = tktkDTO.getTenSanPham();
            int tonDauKy = tktkDTO.getTonDauKy();
            int nhapTrongKy = tktkDTO.getNhapTrongKy();
            int xuatTrongKy = tktkDTO.getXuatTrongKy();
            int tonCuoiKy = tktkDTO.getTonCuoiKy();
            Object [] row = {maSanPham, tenSanPham, tonDauKy, nhapTrongKy, xuatTrongKy, tonCuoiKy};
            dtmTonKho.addRow(row);
        }
        for (int i = 0; i < tbTonKho.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                    tbTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.CENTER);
                    break;
                case 1:
                    tbTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.LEFT);
                    break;
                default:
                    tbTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
            }
        }
        setIsLoadingTonKho(false);
    }
    
    private void thongKeSanPham(DateRangeDTO dateRange, String productName) {
        ChiTietTongSanPhamDTO data = null;
        try {
            data = tkBUS.thongKeSanPham(dateRange, productName);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.dataSanPham = data;
        
        dtmSanPham.setRowCount(0);
        // Add total row
        Object[] totalRow = {"Tổng", "", "", data.getTongSoLuongNhap()};
        dtmSanPham.addRow(totalRow);
        
        ArrayList<ThongKeSanPhamDTO> arr = data.getList();
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeSanPhamDTO tkspDTO = arr.get(i);
            int maSanPham = tkspDTO.getMaSanPham();
            String tenLoaiSanPham = tkspDTO.getTenLoaiSanPham();
            String tenSanPham = tkspDTO.getTenSanPham();
            int soLuongNhap = tkspDTO.getSoLuongNhap();
            Object [] row = {maSanPham, tenLoaiSanPham, tenSanPham, soLuongNhap};
            dtmSanPham.addRow(row);
        }
        for (int i = 0; i < tbSanPham.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                    tbSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.CENTER);
                    break;
                case 1:
                case 2:
                    tbSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.LEFT);
                    break;
                case 3:
                    tbSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
                    break;
            }
        }
        setIsLoadingSanPham(false);
    }
    
    private void thongKe(String name, DateRangeDTO dateRange, String query) {
        if (name.equals("tonkho")) {
            thongKeTonKho(dateRange, query);
        } else if (name.equals("doanhthu")) {
            btnThongKeDoanhThuActionPerformed(null);
        } else if (name.equals("sanpham")) {
            thongKeSanPham(dateRange, query);
        } else if (name.equals("loaisanpham")) {
            thongKeLoaiSanPham(dateRange, query);
        }
    }
    
    private void thongKeLoaiSanPham(DateRangeDTO dateRange, String productType) {
        ChiTietLSPXuatDTO data = null;
        try {
            data = tkBUS.thongKeLoaiSanPham(dateRange, productType);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        Object[] totalRow = {"", "Tổng", data.getTongSoLuongXuat()};
        this.dataLoaiSanPham = data;
        this.tmLoaiSanPham.setDataAndTotalRow(data.getList(), totalRow);

        for (int i = 0; i < tbLoaiSanPham.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                    tbLoaiSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.CENTER);
                    break;
                case 1:
                    tbLoaiSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.LEFT);
                    break;
                case 2:
                    tbLoaiSanPham.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
                    break;
            }
        }
        setIsLoadingLoaiSanPham(false);
    }
    
    private void thongKeDoanhThu(DateRangeDTO dateRange, String groupBy) {
        // dateRange: get data from start_date to end_date
        // filter: filter out dates with no expense and income
        // groupBy: group results by date/month/year
        ChiTietDoanhThuDTO data = null;
        try {
            data = tkBUS.thongKeDoanhThu(dateRange, groupBy);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.dataDoanhThu = data;
        String timelineColumnName = "";
        switch (groupBy) {
            case "date":
                timelineColumnName = CB_VALUE_GROUP_BY_DATE;
                break;
            case "month":
                timelineColumnName = CB_VALUE_GROUP_BY_MONTH;
                break;
            case "year":
                timelineColumnName = CB_VALUE_GROUP_BY_YEAR;
                break;
        }
        
        // Change column name
        tbDoanhThu.getColumnModel().getColumn(0).setHeaderValue(timelineColumnName);
        tbDoanhThu.getTableHeader().repaint();
        
        dtmDoanhThu.setRowCount(0);
        
        // Add total row
        String totalExpenseString = NumberHelper.appendVND(NumberHelper.commafy(data.getTotalExpense()));
        String totalIncomeString = NumberHelper.appendVND(NumberHelper.commafy(data.getTotalIncome()));
        String totalProfitString = NumberHelper.appendVND(NumberHelper.commafy(data.getTotalProfit()));
        Object[] totalRow = {"Tổng", totalExpenseString, totalIncomeString, totalProfitString};
        dtmDoanhThu.addRow(totalRow);
        
        ArrayList<ThongKeDoanhThuDTO> arr = data.getList();
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeDoanhThuDTO tkdtDTO = arr.get(i);
            
            Date timeline = tkdtDTO.getTimeline();
            LocalDateTime localDateTime = DateHelper.convertDateObjToLDT(timeline);
            String expense = NumberHelper.appendVND(NumberHelper.commafy(tkdtDTO.getExpense()));
            String income = NumberHelper.appendVND(NumberHelper.commafy(tkdtDTO.getIncome()));
            String profit = NumberHelper.appendVND(NumberHelper.commafy(tkdtDTO.getProfit()));
            String strTimeline = "";
            switch (groupBy) {
                case "date":
                    strTimeline = localDateTime.format(DateHelper.DATE_FORMATTER);
                    break;
                case "month":
                    strTimeline = localDateTime.format(DateHelper.MONTH_FORMATTER);
                    break;
                case "year":
                    strTimeline = localDateTime.format(DateHelper.YEAR_FORMATTER);
                    break;
            }
            Object [] row = {strTimeline, expense, income, profit};
            dtmDoanhThu.addRow(row);
        }
        
        for (int i = 0; i < tbDoanhThu.getColumnCount(); ++i) {
            tbDoanhThu.getColumnModel().getColumn(i).setCellRenderer(i != 0 ? CustomTableCellRenderer.RIGHT : CustomTableCellRenderer.CENTER);
        }
        setIsLoadingDoanhThu(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pContainer = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        pTonKho = new javax.swing.JPanel();
        pFilterTonKho = new javax.swing.JPanel();
        inputTonKho = new javax.swing.JTextField();
        cbTonKhoDate = new javax.swing.JComboBox<>();
        lblSearchTonKho = new javax.swing.JLabel();
        btnReloadTonKho = new javax.swing.JButton();
        btnExportExcelTonKho = new javax.swing.JButton();
        lblTonKhoDate = new javax.swing.JLabel();
        scrollPane2 = new javax.swing.JScrollPane();
        tbTonKho = new javax.swing.JTable();
        pDoanhThu = new javax.swing.JPanel();
        pFilterDoanhThu = new javax.swing.JPanel();
        lblDoanhThuDate = new javax.swing.JLabel();
        cbDoanhThuDate = new javax.swing.JComboBox<>();
        lblDoanhThuQueryGroupBy = new javax.swing.JLabel();
        cbDoanhThuQueryGroupBy = new javax.swing.JComboBox<>();
        btnThongKeDoanhThu = new javax.swing.JButton();
        btnOpenChartDoanhThu = new javax.swing.JButton();
        btnReloadDoanhThu = new javax.swing.JButton();
        btnExportExcelDoanhThu = new javax.swing.JButton();
        spMessageOptionChanged = new javax.swing.JScrollPane();
        taOptionChanged = new javax.swing.JTextArea();
        iconInfo = new javax.swing.JLabel();
        scrollPane3 = new javax.swing.JScrollPane();
        tbDoanhThu = new javax.swing.JTable();
        pSanPham = new javax.swing.JPanel();
        pFilterSanPham = new javax.swing.JPanel();
        lblSearchSanPham = new javax.swing.JLabel();
        inputSanPham = new javax.swing.JTextField();
        lblSanPhamDate = new javax.swing.JLabel();
        cbSanPhamDate = new javax.swing.JComboBox<>();
        btnChiTietSanPham = new javax.swing.JButton();
        btnExportExcelSanPham = new javax.swing.JButton();
        btnReloadSanPham = new javax.swing.JButton();
        scrollPane4 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        pLoaiSanPham = new javax.swing.JPanel();
        pFilterLoaiSanPham = new javax.swing.JPanel();
        lblSearchLoaiSanPham = new javax.swing.JLabel();
        inputLoaiSanPham = new javax.swing.JTextField();
        lblLoaiSanPhamDate = new javax.swing.JLabel();
        cbLoaiSanPhamDate = new javax.swing.JComboBox<>();
        btnChiTietLoaiSanPham = new javax.swing.JButton();
        btnExportExcelLoaiSanPham = new javax.swing.JButton();
        btnReloadLoaiSanPham = new javax.swing.JButton();
        btnOpenChartLoaiSanPham = new javax.swing.JButton();
        scrollPane5 = new javax.swing.JScrollPane();
        tbLoaiSanPham = new javax.swing.JTable();

        setBorder(null);

        pTonKho.setBackground(new java.awt.Color(255, 255, 255));

        pFilterTonKho.setBackground(new java.awt.Color(255, 255, 255));
        pFilterTonKho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pFilterTonKho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputTonKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inputTonKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTonKhoKeyPressed(evt);
            }
        });
        pFilterTonKho.add(inputTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 40));

        cbTonKhoDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTonKhoDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTonKhoDateActionPerformed(evt);
            }
        });
        pFilterTonKho.add(cbTonKhoDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 280, -1));

        lblSearchTonKho.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSearchTonKho.setText("Tìm kiếm sản phẩm");
        pFilterTonKho.add(lblSearchTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnReloadTonKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReloadTonKho.setText("Làm mới");
        btnReloadTonKho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReloadTonKho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReloadTonKho.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReloadTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadTonKhoActionPerformed(evt);
            }
        });
        pFilterTonKho.add(btnReloadTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btnExportExcelTonKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportExcelTonKho.setText("Xuất Excel");
        btnExportExcelTonKho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportExcelTonKho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportExcelTonKho.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportExcelTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelTonKhoActionPerformed(evt);
            }
        });
        pFilterTonKho.add(btnExportExcelTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        lblTonKhoDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTonKhoDate.setText("dd/mm/yyyy - dd/mm/yyyy");
        pFilterTonKho.add(lblTonKhoDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        tbTonKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tồn đầu kỳ", "Nhập trong kỳ", "Xuất trong kỳ", "Tồn cuối kỳ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane2.setViewportView(tbTonKho);
        if (tbTonKho.getColumnModel().getColumnCount() > 0) {
            tbTonKho.getColumnModel().getColumn(0).setResizable(false);
            tbTonKho.getColumnModel().getColumn(1).setResizable(false);
            tbTonKho.getColumnModel().getColumn(2).setResizable(false);
            tbTonKho.getColumnModel().getColumn(3).setResizable(false);
            tbTonKho.getColumnModel().getColumn(4).setResizable(false);
            tbTonKho.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout pTonKhoLayout = new javax.swing.GroupLayout(pTonKho);
        pTonKho.setLayout(pTonKhoLayout);
        pTonKhoLayout.setHorizontalGroup(
            pTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTonKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFilterTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                .addContainerGap())
        );
        pTonKhoLayout.setVerticalGroup(
            pTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTonKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTonKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pFilterTonKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Tồn kho", pTonKho);

        pDoanhThu.setBackground(new java.awt.Color(255, 255, 255));

        pFilterDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        pFilterDoanhThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pFilterDoanhThu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDoanhThuDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDoanhThuDate.setText("dd/mm/yyyy - dd/mm/yyyy");
        pFilterDoanhThu.add(lblDoanhThuDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbDoanhThuDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbDoanhThuDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDoanhThuDateActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(cbDoanhThuDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, -1));

        lblDoanhThuQueryGroupBy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDoanhThuQueryGroupBy.setText("Nhóm các kết quả theo");
        pFilterDoanhThu.add(lblDoanhThuQueryGroupBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        cbDoanhThuQueryGroupBy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbDoanhThuQueryGroupBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDoanhThuQueryGroupByActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(cbDoanhThuQueryGroupBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, -1));

        btnThongKeDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThongKeDoanhThu.setText("Thống kê");
        btnThongKeDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThongKeDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKeDoanhThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongKeDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeDoanhThuActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(btnThongKeDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 130, -1));

        btnOpenChartDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOpenChartDoanhThu.setText("Xem biểu đồ");
        btnOpenChartDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpenChartDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenChartDoanhThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenChartDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenChartDoanhThuActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(btnOpenChartDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 140, -1));

        btnReloadDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReloadDoanhThu.setText("Làm mới");
        btnReloadDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReloadDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReloadDoanhThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReloadDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadDoanhThuActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(btnReloadDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 130, -1));

        btnExportExcelDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportExcelDoanhThu.setText("Xuất Excel");
        btnExportExcelDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportExcelDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportExcelDoanhThu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportExcelDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelDoanhThuActionPerformed(evt);
            }
        });
        pFilterDoanhThu.add(btnExportExcelDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 140, -1));

        spMessageOptionChanged.setBackground(new java.awt.Color(255, 255, 255));
        spMessageOptionChanged.setBorder(null);
        spMessageOptionChanged.setForeground(new java.awt.Color(255, 255, 255));
        spMessageOptionChanged.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spMessageOptionChanged.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        spMessageOptionChanged.setOpaque(false);

        taOptionChanged.setEditable(false);
        taOptionChanged.setBackground(new java.awt.Color(255, 255, 255));
        taOptionChanged.setColumns(20);
        taOptionChanged.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        taOptionChanged.setLineWrap(true);
        taOptionChanged.setRows(5);
        taOptionChanged.setText("Do thống kê theo khoảng thời gian dài, kết quả thống kê mặc định sẽ được nhóm theo tháng.");
        taOptionChanged.setWrapStyleWord(true);
        taOptionChanged.setFocusable(false);
        taOptionChanged.setMargin(new java.awt.Insets(0, 0, 0, 0));
        taOptionChanged.setOpaque(false);
        spMessageOptionChanged.setViewportView(taOptionChanged);

        pFilterDoanhThu.add(spMessageOptionChanged, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 280, 60));
        spMessageOptionChanged.getViewport().setOpaque(false);
        spMessageOptionChanged.setVisible(false);

        ImageIcon imageIcon = new ImageIcon("/icon/icon-info-2.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        iconInfo.setIcon(imageIcon);
        iconInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-info.png"))); // NOI18N
        pFilterDoanhThu.add(iconInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        tbDoanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[]", "Chi phí", "Doanh thu", "Lợi nhuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane3.setViewportView(tbDoanhThu);
        if (tbDoanhThu.getColumnModel().getColumnCount() > 0) {
            tbDoanhThu.getColumnModel().getColumn(0).setResizable(false);
            tbDoanhThu.getColumnModel().getColumn(1).setResizable(false);
            tbDoanhThu.getColumnModel().getColumn(2).setResizable(false);
            tbDoanhThu.getColumnModel().getColumn(3).setResizable(false);
            tbDoanhThu.getColumnModel().getColumn(3).setHeaderValue("Lợi nhuận");
        }

        javax.swing.GroupLayout pDoanhThuLayout = new javax.swing.GroupLayout(pDoanhThu);
        pDoanhThu.setLayout(pDoanhThuLayout);
        pDoanhThuLayout.setHorizontalGroup(
            pDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFilterDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                .addContainerGap())
        );
        pDoanhThuLayout.setVerticalGroup(
            pDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pFilterDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Doanh thu", pDoanhThu);

        pSanPham.setBackground(new java.awt.Color(255, 255, 255));

        pFilterSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pFilterSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pFilterSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSearchSanPham.setText("Tìm kiếm sản phẩm");
        pFilterSanPham.add(lblSearchSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        inputSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSanPhamKeyPressed(evt);
            }
        });
        pFilterSanPham.add(inputSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 40));

        lblSanPhamDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSanPhamDate.setText("dd/mm/yyyy - dd/mm/yyyy");
        pFilterSanPham.add(lblSanPhamDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        cbSanPhamDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbSanPhamDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSanPhamDateActionPerformed(evt);
            }
        });
        pFilterSanPham.add(cbSanPhamDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 280, -1));

        btnChiTietSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChiTietSanPham.setText("Chi tiết");
        btnChiTietSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChiTietSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChiTietSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSanPhamActionPerformed(evt);
            }
        });
        pFilterSanPham.add(btnChiTietSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btnExportExcelSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportExcelSanPham.setText("Xuất Excel");
        btnExportExcelSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportExcelSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportExcelSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportExcelSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelSanPhamActionPerformed(evt);
            }
        });
        pFilterSanPham.add(btnExportExcelSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        btnReloadSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReloadSanPham.setText("Làm mới");
        btnReloadSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReloadSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReloadSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReloadSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadSanPhamActionPerformed(evt);
            }
        });
        pFilterSanPham.add(btnReloadSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        tbSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        scrollPane4.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(0).setResizable(false);
            tbSanPham.getColumnModel().getColumn(1).setResizable(false);
            tbSanPham.getColumnModel().getColumn(2).setResizable(false);
            tbSanPham.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pSanPhamLayout = new javax.swing.GroupLayout(pSanPham);
        pSanPham.setLayout(pSanPhamLayout);
        pSanPhamLayout.setHorizontalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFilterSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                .addContainerGap())
        );
        pSanPhamLayout.setVerticalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pFilterSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Sản phẩm", pSanPham);

        pLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));

        pFilterLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pFilterLoaiSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pFilterLoaiSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSearchLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSearchLoaiSanPham.setText("Tìm kiếm loại sản phẩm");
        pFilterLoaiSanPham.add(lblSearchLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        inputLoaiSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputLoaiSanPhamKeyPressed(evt);
            }
        });
        pFilterLoaiSanPham.add(inputLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 40));

        lblLoaiSanPhamDate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblLoaiSanPhamDate.setText("dd/mm/yyyy - dd/mm/yyyy");
        pFilterLoaiSanPham.add(lblLoaiSanPhamDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        cbLoaiSanPhamDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbLoaiSanPhamDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiSanPhamDateActionPerformed(evt);
            }
        });
        pFilterLoaiSanPham.add(cbLoaiSanPhamDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 280, -1));

        btnChiTietLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChiTietLoaiSanPham.setText("Chi tiết");
        btnChiTietLoaiSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChiTietLoaiSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChiTietLoaiSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChiTietLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietLoaiSanPhamActionPerformed(evt);
            }
        });
        pFilterLoaiSanPham.add(btnChiTietLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 130, -1));

        btnExportExcelLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportExcelLoaiSanPham.setText("Xuất Excel");
        btnExportExcelLoaiSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportExcelLoaiSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportExcelLoaiSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportExcelLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelLoaiSanPhamActionPerformed(evt);
            }
        });
        pFilterLoaiSanPham.add(btnExportExcelLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 130, -1));

        btnReloadLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReloadLoaiSanPham.setText("Làm mới");
        btnReloadLoaiSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReloadLoaiSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReloadLoaiSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReloadLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadLoaiSanPhamActionPerformed(evt);
            }
        });
        pFilterLoaiSanPham.add(btnReloadLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, -1));

        btnOpenChartLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOpenChartLoaiSanPham.setText("Xem biểu đồ");
        btnOpenChartLoaiSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpenChartLoaiSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenChartLoaiSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenChartLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenChartLoaiSanPhamActionPerformed(evt);
            }
        });
        pFilterLoaiSanPham.add(btnOpenChartLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, -1));

        tbLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Loại sản phẩm", "Số lượng xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLoaiSanPhamMouseClicked(evt);
            }
        });
        scrollPane5.setViewportView(tbLoaiSanPham);
        if (tbLoaiSanPham.getColumnModel().getColumnCount() > 0) {
            tbLoaiSanPham.getColumnModel().getColumn(0).setResizable(false);
            tbLoaiSanPham.getColumnModel().getColumn(1).setResizable(false);
            tbLoaiSanPham.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pLoaiSanPhamLayout = new javax.swing.GroupLayout(pLoaiSanPham);
        pLoaiSanPham.setLayout(pLoaiSanPhamLayout);
        pLoaiSanPhamLayout.setHorizontalGroup(
            pLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFilterLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(568, Short.MAX_VALUE))
        );
        pLoaiSanPhamLayout.setVerticalGroup(
            pLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addComponent(pFilterLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane.addTab("Loại sản phẩm", pLoaiSanPham);

        javax.swing.GroupLayout pContainerLayout = new javax.swing.GroupLayout(pContainer);
        pContainer.setLayout(pContainerLayout);
        pContainerLayout.setHorizontalGroup(
            pContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pContainerLayout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pContainerLayout.setVerticalGroup(
            pContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTonKhoDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTonKhoDateActionPerformed
        // TODO add your handling code here:
        handleComboBoxChanged("tonkho", String.valueOf(cbTonKhoDate.getSelectedItem()), drTonKho, lblTonKhoDate);
        if (isLoadingTonKho) {
            thongKeTonKho(drTonKho, queryTonKho);
        }
    }//GEN-LAST:event_cbTonKhoDateActionPerformed

    private void btnReloadTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadTonKhoActionPerformed
        // TODO add your handling code here:
        handleReload(btnReloadTonKho, "tonkho", drTonKho, queryTonKho);
    }//GEN-LAST:event_btnReloadTonKhoActionPerformed

    private void inputTonKhoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTonKhoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inputTonKho.getText().trim().toLowerCase().equals(queryTonKho.toLowerCase()))
                return;
            setQueryTonKho(inputTonKho.getText().trim());
            thongKeTonKho(drTonKho, queryTonKho);
        }
    }//GEN-LAST:event_inputTonKhoKeyPressed
    
    private void btnChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSanPhamActionPerformed
        // TODO add your handling code here:
        int row = tbSanPham.getSelectedRow();
        if (row < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
            return;
        }
        handleViewProductDetail(row);
    }//GEN-LAST:event_btnChiTietSanPhamActionPerformed

    private void btnReloadSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadSanPhamActionPerformed
        // TODO add your handling code here:
        handleReload(btnReloadSanPham, "sanpham", drSanPham, querySanPham);
    }//GEN-LAST:event_btnReloadSanPhamActionPerformed

    private void cbSanPhamDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSanPhamDateActionPerformed
        // TODO add your handling code here:
        handleComboBoxChanged("sanpham", String.valueOf(cbSanPhamDate.getSelectedItem()), drSanPham, lblSanPhamDate);
        if (isLoadingSanPham) {
            thongKeSanPham(drSanPham, querySanPham);
        }
    }//GEN-LAST:event_cbSanPhamDateActionPerformed

    private void inputSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSanPhamKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inputSanPham.getText().trim().toLowerCase().equals(querySanPham.toLowerCase()))
                return;
            setQuerySanPham(inputSanPham.getText().trim());
            thongKeSanPham(drSanPham, querySanPham);
        }
    }//GEN-LAST:event_inputSanPhamKeyPressed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2 && tbSanPham.getSelectedRow() > 0) {
            int row = tbSanPham.getSelectedRow();
            handleViewProductDetail(row);
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void inputLoaiSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputLoaiSanPhamKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inputLoaiSanPham.getText().trim().toLowerCase().equals(queryLoaiSanPham.toLowerCase()))
                return;
            setQueryLoaiSanPham(inputLoaiSanPham.getText().trim());
            thongKeLoaiSanPham(drLoaiSanPham, queryLoaiSanPham);
        }
    }//GEN-LAST:event_inputLoaiSanPhamKeyPressed

    private void cbLoaiSanPhamDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiSanPhamDateActionPerformed
        // TODO add your handling code here:
        handleComboBoxChanged("loaisanpham", String.valueOf(cbLoaiSanPhamDate.getSelectedItem()), drLoaiSanPham, lblLoaiSanPhamDate);
        if (isLoadingLoaiSanPham) {
            thongKeLoaiSanPham(drLoaiSanPham, queryLoaiSanPham);
        }
    }//GEN-LAST:event_cbLoaiSanPhamDateActionPerformed

    private void btnChiTietLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietLoaiSanPhamActionPerformed
        // TODO add your handling code here:
        int row = tbLoaiSanPham.getSelectedRow();
        if (row < 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm");
            return;
        }
        handleViewProductTypeDetail(row);
    }//GEN-LAST:event_btnChiTietLoaiSanPhamActionPerformed

    private void btnReloadLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadLoaiSanPhamActionPerformed
        // TODO add your handling code here:
        handleReload(btnReloadLoaiSanPham, "loaisanpham", drLoaiSanPham, queryLoaiSanPham);
    }//GEN-LAST:event_btnReloadLoaiSanPhamActionPerformed

    private void tbLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && tbLoaiSanPham.getSelectedRow() > 0) {
            int row = tbLoaiSanPham.getSelectedRow();
            handleViewProductTypeDetail(row);
        }
    }//GEN-LAST:event_tbLoaiSanPhamMouseClicked

    private void cbDoanhThuDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDoanhThuDateActionPerformed
        // TODO add your handling code here:
        spMessageOptionChanged.setVisible(false);
        handleComboBoxChanged("doanhthu", String.valueOf(cbDoanhThuDate.getSelectedItem()), drDoanhThu, lblDoanhThuDate);
    }//GEN-LAST:event_cbDoanhThuDateActionPerformed

    private void btnReloadDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadDoanhThuActionPerformed
        // TODO add your handling code here:
        handleReload(btnReloadDoanhThu, "doanhthu", null, null);
    }//GEN-LAST:event_btnReloadDoanhThuActionPerformed

    private void cbDoanhThuQueryGroupByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDoanhThuQueryGroupByActionPerformed
        // TODO add your handling code here:
        spMessageOptionChanged.setVisible(false);
        switch (String.valueOf(cbDoanhThuQueryGroupBy.getSelectedItem())) {
            case CB_VALUE_GROUP_BY_DATE:
                setDoanhThuGroupBy("date");
                break;
            case CB_VALUE_GROUP_BY_MONTH:
                setDoanhThuGroupBy("month");
                break;
            case CB_VALUE_GROUP_BY_YEAR:
                setDoanhThuGroupBy("year");
                break;
            default:
        }
    }//GEN-LAST:event_cbDoanhThuQueryGroupByActionPerformed

    private void btnThongKeDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeDoanhThuActionPerformed
        // TODO add your handling code here:
        if (handleDoanhThuOptionChanged()) {
            spMessageOptionChanged.setVisible(true);
        }
        thongKeDoanhThu(drDoanhThu, doanhThuGroupBy);
    }//GEN-LAST:event_btnThongKeDoanhThuActionPerformed

    private void btnOpenChartDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenChartDoanhThuActionPerformed
        // TODO add your handling code here:
        ArrayList<ThongKeDoanhThuDTO> arrDoanhThu = dataDoanhThu.getList();
        if (arrDoanhThu.isEmpty()) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không có dữ liệu");
        } else {
            new LineChart(arrDoanhThu, this.doanhThuGroupBy).setVisible(true);
        }
    }//GEN-LAST:event_btnOpenChartDoanhThuActionPerformed

    private void btnOpenChartLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenChartLoaiSanPhamActionPerformed
        // TODO add your handling code here:
        if (this.dataLoaiSanPham.getList().isEmpty()) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không có dữ liệu");
        } else {
            new LoaiSanPhamChart(this.dataLoaiSanPham.getList(), this.drLoaiSanPham).setVisible(true);
        }
    }//GEN-LAST:event_btnOpenChartLoaiSanPhamActionPerformed

    private void btnExportExcelDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelDoanhThuActionPerformed
        // TODO add your handling code here:
        File file = FileHelper.createExcelFile(this);
        if (file == null) {
            return;
        }
        XSSFWorkbook wbDoanhThu = new XSSFWorkbook();
        try {
            XSSFSheet sheetDoanhThu = tkBUS.exportDataDoanhThu(wbDoanhThu, dataDoanhThu, doanhThuGroupBy);
            
            FileHelper.writeToExcelFile(file, wbDoanhThu);
            FileHelper.openFile(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không thể lưu file. Vui lòng chọn một đường dẫn lưu file hợp lệ.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Ghi vào file không thành công, vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                wbDoanhThu.close();
            } catch (IOException e) {
                Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btnExportExcelDoanhThuActionPerformed

    private void btnExportExcelTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelTonKhoActionPerformed
        // TODO add your handling code here:
        File file = FileHelper.createExcelFile(this);
        if (file == null) {
            return;
        }
        XSSFWorkbook wbTonKho = new XSSFWorkbook();
        try {
            XSSFSheet sheetTonKho = tkBUS.exportDataTonKho(wbTonKho, dataTonKho);
            
            FileHelper.writeToExcelFile(file, wbTonKho);
            FileHelper.openFile(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không thể lưu file. Vui lòng chọn một đường dẫn lưu file hợp lệ.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Ghi vào file không thành công, vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                wbTonKho.close();
            } catch (IOException e) {
                Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btnExportExcelTonKhoActionPerformed

    private void btnExportExcelSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelSanPhamActionPerformed
        // TODO add your handling code here:
        if (dataSanPham.getList().isEmpty()) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không có dữ liệu");
            return;
        }
        
        File file = FileHelper.createExcelFile(this);
        if (file == null) {
            return;
        }
        XSSFWorkbook wbSanPham = new XSSFWorkbook();
        try {
            XSSFSheet sheetSanPham = tkBUS.exportDataSanPham(wbSanPham, dataSanPham);
            
            FileHelper.writeToExcelFile(file, wbSanPham);
            FileHelper.openFile(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không thể lưu file. Vui lòng chọn một đường dẫn lưu file hợp lệ.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Ghi vào file không thành công, vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                wbSanPham.close();
            } catch (IOException e) {
                Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btnExportExcelSanPhamActionPerformed

    private void btnExportExcelLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelLoaiSanPhamActionPerformed
        // TODO add your handling code here:
        if (dataLoaiSanPham.getList().isEmpty()) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không có dữ liệu");
            return;
        }
        
        File file = FileHelper.createExcelFile(this);
        if (file == null) {
            return;
        }
        XSSFWorkbook wbLoaiSanPham = new XSSFWorkbook();
        try {
            XSSFSheet sheetLoaiSanPham = tkBUS.exportDataLoaiSanPham(wbLoaiSanPham, dataLoaiSanPham);
            
            FileHelper.writeToExcelFile(file, wbLoaiSanPham);
            FileHelper.openFile(file);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Không thể lưu file. Vui lòng chọn một đường dẫn lưu file hợp lệ.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, "Ghi vào file không thành công, vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ThongKeGUI.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                wbLoaiSanPham.close();
            } catch (IOException e) {
                Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btnExportExcelLoaiSanPhamActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietLoaiSanPham;
    private javax.swing.JButton btnChiTietSanPham;
    private javax.swing.JButton btnExportExcelDoanhThu;
    private javax.swing.JButton btnExportExcelLoaiSanPham;
    private javax.swing.JButton btnExportExcelSanPham;
    private javax.swing.JButton btnExportExcelTonKho;
    private javax.swing.JButton btnOpenChartDoanhThu;
    private javax.swing.JButton btnOpenChartLoaiSanPham;
    private javax.swing.JButton btnReloadDoanhThu;
    private javax.swing.JButton btnReloadLoaiSanPham;
    private javax.swing.JButton btnReloadSanPham;
    private javax.swing.JButton btnReloadTonKho;
    private javax.swing.JButton btnThongKeDoanhThu;
    private javax.swing.JComboBox<String> cbDoanhThuDate;
    private javax.swing.JComboBox<String> cbDoanhThuQueryGroupBy;
    private javax.swing.JComboBox<String> cbLoaiSanPhamDate;
    private javax.swing.JComboBox<String> cbSanPhamDate;
    private javax.swing.JComboBox<String> cbTonKhoDate;
    private javax.swing.JLabel iconInfo;
    private javax.swing.JTextField inputLoaiSanPham;
    private javax.swing.JTextField inputSanPham;
    private javax.swing.JTextField inputTonKho;
    private javax.swing.JLabel lblDoanhThuDate;
    private javax.swing.JLabel lblDoanhThuQueryGroupBy;
    private javax.swing.JLabel lblLoaiSanPhamDate;
    private javax.swing.JLabel lblSanPhamDate;
    private javax.swing.JLabel lblSearchLoaiSanPham;
    private javax.swing.JLabel lblSearchSanPham;
    private javax.swing.JLabel lblSearchTonKho;
    private javax.swing.JLabel lblTonKhoDate;
    private javax.swing.JPanel pContainer;
    private javax.swing.JPanel pDoanhThu;
    private javax.swing.JPanel pFilterDoanhThu;
    private javax.swing.JPanel pFilterLoaiSanPham;
    private javax.swing.JPanel pFilterSanPham;
    private javax.swing.JPanel pFilterTonKho;
    private javax.swing.JPanel pLoaiSanPham;
    private javax.swing.JPanel pSanPham;
    private javax.swing.JPanel pTonKho;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JScrollPane scrollPane4;
    private javax.swing.JScrollPane scrollPane5;
    private javax.swing.JScrollPane spMessageOptionChanged;
    private javax.swing.JTextArea taOptionChanged;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tbDoanhThu;
    private javax.swing.JTable tbLoaiSanPham;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbTonKho;
    // End of variables declaration//GEN-END:variables
}
