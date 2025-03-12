package BUS;

import DAO.ThongKeDAO;
import DTO.DateRangeDTO;
import DTO.ThongKe.*;
import static GUI.ThongKeGUI.CB_VALUE_GROUP_BY_DATE;
import static GUI.ThongKeGUI.CB_VALUE_GROUP_BY_MONTH;
import static GUI.ThongKeGUI.CB_VALUE_GROUP_BY_YEAR;
import helper.DateHelper;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThongKeBUS {
    private final ThongKeDAO tkDAO = new ThongKeDAO();

    public ThongKeBUS() {
    }
    
    public ChiTietTongTonKhoDTO thongKeTonKho(DateRangeDTO dateRange, String productName) throws SQLException {
        return tkDAO.thongKeTonKho(dateRange, productName);
    }
    
    public ChiTietTongSanPhamDTO thongKeSanPham(DateRangeDTO dateRange, String productName) throws SQLException {
        return tkDAO.thongKeSanPham(dateRange, productName);
    }
    
    public ArrayList<ChiTietSanPhamNhapDTO> thongKeChiTietSanPhamNhap(DateRangeDTO dateRange, int productId) throws SQLException {
        return tkDAO.thongKeChiTietSanPhamNhap(dateRange, productId);
    }
    
    public ArrayList<ChiTietGiaNhapNCCDTO> chiTietGiaNhapNCC (int productId, int providerId, DateRangeDTO dateRange) throws SQLException {
        return tkDAO.chiTietGiaNhapNCC(productId, providerId, dateRange);
    }
    
    public ChiTietLSPXuatDTO thongKeLoaiSanPham(DateRangeDTO dateRange, String productType) throws SQLException {
        return tkDAO.thongKeLoaiSanPham(dateRange, productType);
    }
    
    public ArrayList<ChiTietLoaiSanPhamDTO> chiTietLoaiSanPham(DateRangeDTO dateRange, int productTypeId) throws SQLException {
        return tkDAO.chiTietLoaiSanPham(dateRange, productTypeId);
    }
    
    public ArrayList<ChiTietGiaXuatSPDTO> chiTietGiaXuatSanPham (int productId, DateRangeDTO dateRange) throws SQLException {
        return tkDAO.chiTietGiaXuatSanPham(productId, dateRange);
    }
    
    public ChiTietDoanhThuDTO thongKeDoanhThu(DateRangeDTO dateRange, String groupBy) throws SQLException, ParseException {
        return tkDAO.thongKeDoanhThu(dateRange, groupBy);
    }
    
    public LocalDateTime getOldestDate() throws SQLException {
        return tkDAO.getOldestDate();
    }
    
    public XSSFSheet exportDataDoanhThu(XSSFWorkbook workbook, ChiTietDoanhThuDTO data, String groupBy) throws Exception {
        ArrayList<ThongKeDoanhThuDTO> arr = data.getList();
        if (arr.isEmpty())
            throw new Exception("Không có dữ liệu");
        
        DateRangeDTO dateRange = data.getDateRange();
        
        // Create workbook with 1 sheet
        XSSFSheet sheet = workbook.createSheet(DateHelper.dateRangeToString(dateRange, DateHelper.EXCEL_SHEET_NAME_DATE_FORMATTER, "_"));
        
        // Get first column name
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
        
        int columns = 4;
        
        // Create first row: column name
        XSSFRow columnNameRow = sheet.createRow(0);
        XSSFCell[] columnNameCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            columnNameCell[i] = columnNameRow.createCell(i);
        }
        columnNameCell[0].setCellValue(timelineColumnName);
        columnNameCell[1].setCellValue("Chi phí (VND)");
        columnNameCell[2].setCellValue("Doanh thu (VND)");
        columnNameCell[3].setCellValue("Lợi nhuận (VND)");
        
        // Create summary row, contains total values
        XSSFRow summaryRow = sheet.createRow(1);
        XSSFCell[] summaryCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            summaryCell[i] = summaryRow.createCell(i);
        }
        summaryCell[0].setCellValue("Tổng");
        summaryCell[1].setCellValue(data.getTotalExpense());
        summaryCell[2].setCellValue(data.getTotalIncome());
        summaryCell[3].setCellValue(data.getTotalProfit());
        
        // Set date format
        XSSFCellStyle timelineStyle = workbook.createCellStyle();
        switch (groupBy) {
            case "date":
                timelineStyle.setDataFormat(workbook.createDataFormat().getFormat(DateHelper.DATE_PATTERN));
                break;
            case "month":
                timelineStyle.setDataFormat(workbook.createDataFormat().getFormat(DateHelper.MONTH_PATTERN));
                break;
            case "year":
                timelineStyle.setDataFormat(workbook.createDataFormat().getFormat(DateHelper.YEAR_PATTERN));
                break;
        }
        
        // Add data to cells
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeDoanhThuDTO item = arr.get(i);
            XSSFRow row = sheet.createRow(i + 2);
            
            XSSFCell[] cell = new XSSFCell[columns];
            
            for (int j = 0; j < columns; ++j) {
                cell[j] = row.createCell(j);
            }
            
            cell[0].setCellValue(item.getTimeline());
            cell[0].setCellStyle(timelineStyle);
            cell[1].setCellValue(item.getExpense());
            cell[2].setCellValue(item.getIncome());
            cell[3].setCellValue(item.getProfit());
        }
        
        // Resize columns' width to fit the contents
        for (int columnIndex = 0; columnIndex < sheet.getRow(0).getLastCellNum(); columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        
        return sheet;
    }
    
    public XSSFSheet exportDataTonKho(XSSFWorkbook workbook, ChiTietTongTonKhoDTO data) throws Exception {
        ArrayList<ThongKeTonKhoDTO> arr = data.getList();
        if (arr.isEmpty())
            throw new Exception("Không có dữ liệu");
        
        DateRangeDTO dateRange = data.getDateRange();
        
        String productName = data.getQuery();
        String sheetNamePostfix = productName.isEmpty() ? "All" : productName;
        
        // Create workbook with 1 sheet
        XSSFSheet sheet = workbook.createSheet(DateHelper.dateRangeToString(dateRange, DateHelper.EXCEL_SHEET_NAME_DATE_FORMATTER, "_") + "_" + sheetNamePostfix);
        int columns = 6;
        
        // Create first row: column name
        String[] columnName = new String [] {"Mã sản phẩm", "Tên sản phẩm", "Tồn đầu kỳ", "Nhập trong kỳ", "Xuất trong kỳ", "Tồn cuối kỳ"};
        XSSFRow columnNameRow = sheet.createRow(0);
        XSSFCell[] columnNameCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            columnNameCell[i] = columnNameRow.createCell(i);
            columnNameCell[i].setCellValue(columnName[i]);
        }
        
        // Create summary row, contains total values
        Long[] summaryData = new Long[] {data.getTongTonDauKy(), data.getTongNhapTrongKy(), data.getTongXuatTrongKy(), data.getTongTonCuoiKy()};
        XSSFRow summaryRow = sheet.createRow(1);
        XSSFCell[] summaryCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            summaryCell[i] = summaryRow.createCell(i);
        }
        summaryCell[0].setCellValue("Tổng");
        summaryCell[2].setCellValue(summaryData[0]);
        summaryCell[3].setCellValue(summaryData[1]);
        summaryCell[4].setCellValue(summaryData[2]);
        summaryCell[5].setCellValue(summaryData[3]);
        
        // Add data to cells
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeTonKhoDTO item = arr.get(i);
            XSSFRow row = sheet.createRow(i + 2);
            
            XSSFCell[] cell = new XSSFCell[columns];
            
            for (int j = 0; j < columns; ++j) {
                cell[j] = row.createCell(j);
            }
            
            cell[0].setCellValue(item.getMaSanPham());
            cell[1].setCellValue(item.getTenSanPham());
            cell[2].setCellValue(item.getTonDauKy());
            cell[3].setCellValue(item.getNhapTrongKy());
            cell[4].setCellValue(item.getXuatTrongKy());
            cell[5].setCellValue(item.getTonCuoiKy());
        }
        
        // Resize columns' width to fit the contents
        for (int columnIndex = 0; columnIndex < sheet.getRow(0).getLastCellNum(); columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        
        return sheet;
    }
    
    public XSSFSheet exportDataSanPham(XSSFWorkbook workbook, ChiTietTongSanPhamDTO data) throws Exception {
        ArrayList<ThongKeSanPhamDTO> arr = data.getList();
        if (arr.isEmpty())
            throw new Exception("Không có dữ liệu");
        
        DateRangeDTO dateRange = data.getDateRange();
        
        String productName = data.getQuery();
        String sheetNamePostfix = productName.isEmpty() ? "All" : productName;
        
        // Create workbook with 1 sheet
        XSSFSheet sheet = workbook.createSheet(DateHelper.dateRangeToString(dateRange, DateHelper.EXCEL_SHEET_NAME_DATE_FORMATTER, "_") + "_" + sheetNamePostfix);
        int columns = 4;
    
        // Create first row: column name
        String[] columnName = new String [] {"Mã sản phẩm", "Tên loại sản phẩm", "Tên sản phẩm", "Số lượng nhập"};
        XSSFRow columnNameRow = sheet.createRow(0);
        XSSFCell[] columnNameCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            columnNameCell[i] = columnNameRow.createCell(i);
            columnNameCell[i].setCellValue(columnName[i]);
        }
        
        // Create summary row, contains total values
        XSSFRow summaryRow = sheet.createRow(1);
        XSSFCell[] summaryCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            summaryCell[i] = summaryRow.createCell(i);
        }
        summaryCell[0].setCellValue("Tổng");
        summaryCell[3].setCellValue(data.getTongSoLuongNhap());
        
        // Add data to cells
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeSanPhamDTO item = arr.get(i);
            XSSFRow row = sheet.createRow(i + 2);
            
            XSSFCell[] cell = new XSSFCell[columns];
            
            for (int j = 0; j < columns; ++j) {
                cell[j] = row.createCell(j);
            }
            
            cell[0].setCellValue(item.getMaSanPham());
            cell[1].setCellValue(item.getTenLoaiSanPham());
            cell[2].setCellValue(item.getTenSanPham());
            cell[3].setCellValue(item.getSoLuongNhap());
        }
        
        // Resize columns' width to fit the contents
        for (int columnIndex = 0; columnIndex < sheet.getRow(0).getLastCellNum(); columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        
        return sheet;
    }
    
    public XSSFSheet exportDataLoaiSanPham(XSSFWorkbook workbook, ChiTietLSPXuatDTO data) throws Exception {
        ArrayList<ThongKeLoaiSanPhamDTO> arr = data.getList();
        if (arr.isEmpty())
            throw new Exception("Không có dữ liệu");
        
        DateRangeDTO dateRange = data.getDateRange();
        
        String productType = data.getQuery();
    
        String sheetNamePostfix = productType.isEmpty() ? "All" : productType;
        
        // Create workbook with 1 sheet
        XSSFSheet sheet = workbook.createSheet(DateHelper.dateRangeToString(dateRange, DateHelper.EXCEL_SHEET_NAME_DATE_FORMATTER, "_") + "_" + sheetNamePostfix);
        int columns = 2;
    
        // Create first row: column name
        String[] columnName = new String [] {"Tên loại sản phẩm", "Số lượng xuất"};
        XSSFRow columnNameRow = sheet.createRow(0);
        XSSFCell[] columnNameCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            columnNameCell[i] = columnNameRow.createCell(i);
            columnNameCell[i].setCellValue(columnName[i]);
        }
        
        // Create summary row, contains total values
        XSSFRow summaryRow = sheet.createRow(1);
        XSSFCell[] summaryCell = new XSSFCell[columns];
        for (int i = 0; i < columns; ++i) {
            summaryCell[i] = summaryRow.createCell(i);
        }
        summaryCell[0].setCellValue("Tổng");
        summaryCell[1].setCellValue(data.getTongSoLuongXuat());
        
        // Add data to cells
        for (int i = 0; i < arr.size(); ++i) {
            ThongKeLoaiSanPhamDTO item = arr.get(i);
            XSSFRow row = sheet.createRow(i + 2);
            
            XSSFCell[] cell = new XSSFCell[columns];
            
            for (int j = 0; j < columns; ++j) {
                cell[j] = row.createCell(j);
            }
            
            cell[0].setCellValue(item.getTenLoaiSanPham());
            cell[1].setCellValue(item.getSoLuong());
        }
        
        // Resize columns' width to fit the contents
        for (int columnIndex = 0; columnIndex < sheet.getRow(0).getLastCellNum(); columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        
        return sheet;
    }
}
