package helper;

import DTO.ChiTietTonKhoDTO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TonKhoSanPhamTableModel extends AbstractTableModel {
    private ArrayList<ChiTietTonKhoDTO> data;
    private String[] columnNames;

    public TonKhoSanPhamTableModel(ArrayList<ChiTietTonKhoDTO> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChiTietTonKhoDTO rowData = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowData.getMaKho();
            case 1:
                return rowData.getTenKho();
            case 2:
                return rowData.getMaPhieuNhap();
            case 3:
                return rowData.getThoiGianTao().format(DateHelper.DATE_TIME_FORMATTER);
            case 4:
                return rowData.getMaNhaCungCap();
            case 5:
                return rowData.getTenNhaCungCap();
            case 6:
                Long donGia = rowData.getDonGia();
                return donGia == -1 ? "" : NumberHelper.appendVND(NumberHelper.commafy(donGia));
            case 7:
                return rowData.getSoLuongTonKho();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<ChiTietTonKhoDTO> getData() {
        return data;
    }

    public void setData(ArrayList<ChiTietTonKhoDTO> data) {
        this.data = data;
    }
}
