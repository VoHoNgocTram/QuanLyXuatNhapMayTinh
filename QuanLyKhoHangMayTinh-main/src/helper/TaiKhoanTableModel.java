package helper;

import DTO.NguoiDungDTO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TaiKhoanTableModel extends AbstractTableModel {
    private ArrayList<NguoiDungDTO> data;
    private String[] columnNames;

    public TaiKhoanTableModel(ArrayList<NguoiDungDTO> data, String[] columnNames) {
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
        NguoiDungDTO rowData = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowData.getTaiKhoan();
            case 1:
                return rowData.getHoTen();
            case 2:
                return rowData.getEmail();
            case 3:
                return rowData.getMaNhomQuyen();
            case 4:
                return rowData.getTenNhomQuyen();
            case 5:
                return rowData.getDoUuTien();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<NguoiDungDTO> getData() {
        return data;
    }

    public void setData(ArrayList<NguoiDungDTO> data) {
        this.data = data;
    }
}
