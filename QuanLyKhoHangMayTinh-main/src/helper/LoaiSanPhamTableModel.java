package helper;

import DTO.ThongKe.ThongKeLoaiSanPhamDTO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LoaiSanPhamTableModel extends AbstractTableModel {
    private ArrayList<ThongKeLoaiSanPhamDTO> data;
    private String[] columnNames;
    private Object[] totalRow;

    public LoaiSanPhamTableModel(ArrayList<ThongKeLoaiSanPhamDTO> data, String[] columnNames, Object[] totalRow) {
        this.data = data;
        this.columnNames = columnNames;
        this.totalRow = totalRow;
    }
    
    @Override
    public int getRowCount() {
        return data.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0 && totalRow != null) {
            return totalRow[columnIndex];
        }
        ThongKeLoaiSanPhamDTO lsp = data.get(rowIndex - 1);
        switch(columnIndex) {
            case 0:
                return lsp.getMaLoaiSanPham();
            case 1:
                return lsp.getTenLoaiSanPham();
            case 2:
                return lsp.getSoLuong();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<ThongKeLoaiSanPhamDTO> getData() {
        return data;
    }
    
    public void setDataAndTotalRow(ArrayList<ThongKeLoaiSanPhamDTO> data, Object[] totalRow) {
        this.totalRow = totalRow;
        this.data = data;
        fireTableDataChanged();
    }
}
