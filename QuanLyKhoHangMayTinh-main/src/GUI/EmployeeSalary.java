/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.BangLuongBUS;
import BUS.NhanVienBUS;
import DTO.BangLuongDTO;
import DTO.NguoiDungDTO;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
/**
 *
 * @author Acer
 */
public class EmployeeSalary extends javax.swing.JFrame {

    /**
     * Creates new form EmployeeSalary
     */
    private DefaultTableModel tblModel;
    NhanVienBUS nvBUS = new NhanVienBUS();
    BangLuongBUS blBUS = new BangLuongBUS();
    private ArrayList<BangLuongDTO> bl = new ArrayList<>();
    LocalDate TGT = LocalDate.now();
    int thang = TGT.getMonthValue(); 
    int nam = TGT.getYear(); 
    public EmployeeSalary() {
        initComponents();
//        LocalDate TGT = LocalDate.now();

        lblTGT.setText(String.valueOf(TGT));
        initTable();
        loadDataToTable(thang, nam);
        tblNV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getNhanVienByTaiKhoan();
                }
            }
        });
        setZeroOnEmpty(txtLTU);
        setZeroOnEmpty(txtPCTS);
        setZeroOnEmpty(txtLTh);
        setZeroOnEmpty(txtNL);
        setZeroOnEmpty(txtNP);
        setZeroOnEmpty(txtNKL);

        // Sau khi khởi tạo component
        setupInputRestrictions();
        addKeyListeners();
        updateSalaryInfo();
    }
    public final void initTable() {
        // Tiêu đề các cột
        String[] headerTbl = new String[]{
            "Tài Khoản", "Họ Tên", "Giới tính", "Ngày Sinh", "Số điện thoại", "Địa chỉ", 
        };

        // Tạo model không cho sửa
        tblModel = new DefaultTableModel(null, headerTbl) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNV.setModel(tblModel);

        // Căn chỉnh độ rộng cột
        tblNV.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblNV.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblNV.getColumnModel().getColumn(2).setPreferredWidth(2);
        tblNV.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblNV.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblNV.getColumnModel().getColumn(5).setPreferredWidth(200);

        // Căn giữa nội dung bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblNV.getColumnCount(); i++) {
            tblNV.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Nếu có dữ liệu thì chọn dòng đầu tiên
        if (tblModel.getRowCount() > 0) {
            tblNV.setRowSelectionInterval(0, 0);
        }
    }
    public void loadDataToTable(int thang, int nam) {
        try {
            List<NguoiDungDTO> nv ;
            nv = blBUS.getDanhSachNhanVienChuaCoLuongThang(thang, nam);
            tblModel.setRowCount(0);
            for (NguoiDungDTO i : nv) {
                if(i.getTrangThai()==1){
                    tblModel.addRow(new Object[]{
                        i.getTaiKhoan(), i.getHoTen(), i.getGioiTinh(), i.getNgaySinh(), i.getSdt(), i.getDiaChi(),i.getTrangThai()
                    });
                }
            }
            // Auto chọn dòng đầu tiên
            if (tblModel.getRowCount() > 0) {
                tblNV.setRowSelectionInterval(0, 0);
                getNhanVienByTaiKhoan();
            }
        } catch (Exception e) {
        }
    }
    public void getNhanVienByTaiKhoan(){
        int selectedRow = tblNV.getSelectedRow();
        if (selectedRow < 0) return;
        String taikhoan = tblNV.getValueAt(selectedRow, 0).toString();
        NguoiDungDTO nv=nvBUS.getNhanVienByTaiKhoan(taikhoan);
        if (nv != null) {
            lblTaikhoan.setText(String.valueOf(nv.getTaiKhoan()));
            lblHoten.setText(nv.getHoTen());
            lblLCB.setText(String.valueOf(nv.getLuongCoBan()));
        }
    }
    // Lọc chỉ cho nhập số và không vượt quá maxValue
    public class NumberOnlyFilter extends DocumentFilter {
        private final int maxValue;

        public NumberOnlyFilter(int maxValue) {
            this.maxValue = maxValue;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (isValidInput(fb, string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (isValidInput(fb, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isValidInput(FilterBypass fb, String text) throws BadLocationException {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            StringBuilder sb = new StringBuilder(currentText);
            sb.insert(fb.getDocument().getLength(), text);

            try {
                if (sb.toString().isEmpty()) return true;
                int value = Integer.parseInt(sb.toString());
                return value <= maxValue;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public void setupNumericField(JTextField field, int maxValue) {
        AbstractDocument doc = (AbstractDocument) field.getDocument();
        doc.setDocumentFilter(new NumberOnlyFilter(maxValue));
    }

    public void setupInputRestrictions() {
        setupNumericField(txtNP, Integer.MAX_VALUE);
        setupNumericField(txtNKL, Integer.MAX_VALUE);
        setupNumericField(txtNL, Integer.MAX_VALUE);
        setupNumericField(txtLTh, Integer.MAX_VALUE);
        setupNumericField(txtPCTS, 5_000_000);  // max 5 triệu
        setupNumericField(txtLTU, 5_000_000);   // max 5 triệu
    }

    private void enforceMaxValue(JTextField field, int maxValue) {
        try {
            String text = field.getText().trim();
            if (!text.isEmpty()) {
                int value = Integer.parseInt(text);
                if (value > maxValue) {
                    field.setText(String.valueOf(maxValue));
                }
            }
        } catch (NumberFormatException e) {
            field.setText("");
        }
    }

    private void addKeyListeners() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                enforceMaxValue(txtPCTS, 5_000_000);
                enforceMaxValue(txtLTU, 5_000_000);
                updateSalaryInfo();
            }
        };

        txtNP.addKeyListener(keyAdapter);
        txtNKL.addKeyListener(keyAdapter);
        txtNL.addKeyListener(keyAdapter);
        txtPCTS.addKeyListener(keyAdapter);
        txtLTU.addKeyListener(keyAdapter);
        txtLTh.addKeyListener(keyAdapter);
    }
    
    private int parseIntOrZero(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Tính khấu trừ nghỉ không lương
    public int calculateUnpaidLeave(int baseSalary) {
        int standardDays = Integer.parseInt(lblNCC.getText());
        int paidLeave = parseIntOrZero(txtNP.getText());
        int unpaidLeave = parseIntOrZero(txtNKL.getText());
        int holidays = parseIntOrZero(txtNL.getText());

        int totalLeave = paidLeave + unpaidLeave + holidays;
        int actualDays = standardDays - totalLeave;
        lblNCTT.setText(String.valueOf(actualDays));

        int dailySalary = baseSalary / standardDays;
        return dailySalary * unpaidLeave;
    }

    // Tính phụ cấp
    public int calculateAllowance() {
        int fuel = Integer.parseInt(lblPCXX.getText());
        int meal = Integer.parseInt(lblPCCT.getText());
        int maternity = parseIntOrZero(txtPCTS.getText());
        return fuel + meal + maternity;
    }

    // Tính các khoản trừ
    public int calculateDeductions() {
        int compulsory = Integer.parseInt(lblBHBB.getText());
        int social = Integer.parseInt(lblBHXH.getText());
        int health = Integer.parseInt(lblBHYT.getText());
        int advance = parseIntOrZero(txtLTU.getText());
        return compulsory + social + health + advance;
    }

    // Tính lương cuối cùng
    public void calculateSalary(int baseSalary) {
        int unpaidDeduct = calculateUnpaidLeave(baseSalary);
        int allowance = calculateAllowance();
        int deductions = calculateDeductions();
        int bonus = parseIntOrZero(txtLTh.getText());

        int totalDeductions = unpaidDeduct + deductions;
        lblLT.setText(String.valueOf(totalDeductions));

        int netSalary = baseSalary - totalDeductions + allowance + bonus;
        lblTL.setText(String.valueOf(netSalary));
    }
    
    private void updateSalaryInfo() {
        try {
            int baseSalary = Integer.parseInt(lblLCB.getText().trim());

            boolean hasLeave = !txtNP.getText().trim().isEmpty()
                            || !txtNKL.getText().trim().isEmpty()
                            || !txtNL.getText().trim().isEmpty();

            boolean hasInputs = !txtPCTS.getText().trim().isEmpty()
                             || !txtLTU.getText().trim().isEmpty()
                             || !txtLTh.getText().trim().isEmpty();

            if (hasLeave || hasInputs) {
                calculateSalary(baseSalary);
            }

        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }
    
    // Hàm xử lý tự động set lại "0" nếu nội dung bị xóa
    private void setZeroOnEmpty(JTextField textField) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText("0");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNCC = new javax.swing.JLabel();
        txtNP = new javax.swing.JTextField();
        txtNKL = new javax.swing.JTextField();
        txtNL = new javax.swing.JTextField();
        lblNCTT = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblLCB = new javax.swing.JLabel();
        lblTL = new javax.swing.JLabel();
        txtLTh = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lblLT = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblPCXX = new javax.swing.JLabel();
        lblPCCT = new javax.swing.JLabel();
        txtPCTS = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblBHBB = new javax.swing.JLabel();
        lblBHXH = new javax.swing.JLabel();
        lblBHYT = new javax.swing.JLabel();
        txtLTU = new javax.swing.JTextField();
        lblLTU = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblTaikhoan = new javax.swing.JLabel();
        lblHoten = new javax.swing.JLabel();
        btnLuuSl = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTGT = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnLuu.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnLuu.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblNV);

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LƯƠNG NHÂN VIÊN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setText("Tài khoản: ");

        jLabel4.setText("Họ tên");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày công"));

        jLabel5.setText("Số ngày công chuẩn :");

        jLabel6.setText("Nghỉ phép : ");

        jLabel7.setText("Nghỉ không lương :");

        jLabel8.setText("Nghỉ lễ : ");

        jLabel9.setText("Ngày công thực tế: ");

        lblNCC.setText("26");

        txtNP.setText("0");
        txtNP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNPActionPerformed(evt);
            }
        });

        txtNKL.setText("0");

        txtNL.setText("0");
        txtNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNLActionPerformed(evt);
            }
        });

        lblNCTT.setText("26");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNP)
                        .addComponent(txtNKL)
                        .addComponent(txtNL, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                    .addComponent(lblNCTT, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNCC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtNKL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblNCTT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lương"));

        jLabel10.setText("Lương cơ bản : ");

        jLabel11.setText("Lương thưởng : ");

        jLabel12.setText("Tổng lương : ");

        lblLCB.setText("jLabel1");

        lblTL.setText("0");

        txtLTh.setText("0");
        txtLTh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLThActionPerformed(evt);
            }
        });

        jLabel21.setText("Lương trừ : ");

        lblLT.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLTh, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(lblLT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblLCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblLT))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtLTh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTL))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Phụ cấp"));

        jLabel13.setText("Phụ cấp xăng xe : ");

        jLabel14.setText("Phụ cấp cơm tháng : ");

        jLabel15.setText("Phụ cấp thai sản : ");

        lblPCXX.setText("600000");

        lblPCCT.setText("600000");

        txtPCTS.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPCTS, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(lblPCXX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPCCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblPCXX))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblPCCT))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPCTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Khoản trừ"));

        jLabel16.setText("Bảo hiểm bắt buộc :  ");

        jLabel17.setText("Bảo hiểm xã hội : ");

        jLabel18.setText("Bảo hiểm y tế : ");

        jLabel19.setText("Lương tạm ứng : ");

        lblBHBB.setText("1000000");

        lblBHXH.setText("1000000");

        lblBHYT.setText("1000000");

        txtLTU.setText("0");
        txtLTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLTUActionPerformed(evt);
            }
        });

        lblLTU.setText("(Đã tạm ứng)");

        jLabel20.setText("(Tạm ứng lớn nhất 5000000)");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblBHYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBHXH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBHBB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(txtLTU, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblLTU, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblBHBB))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblBHXH))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblBHYT))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtLTU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLTU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTaikhoan.setText("jLabel1");

        lblHoten.setText("jLabel1");

        btnLuuSl.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnLuuSl.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnLuuSl.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuSl.setText("Lưu");
        btnLuuSl.setBorder(null);
        btnLuuSl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuuSl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSlActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 0, 51));
        btnHuy.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Thoát");
        btnHuy.setBorder(null);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel1.setText("Thời gian tính: ");

        lblTGT.setText("jLabel20");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTGT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLuuSl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblTaikhoan)
                    .addComponent(lblHoten)
                    .addComponent(jLabel1)
                    .addComponent(lblTGT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuSl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:

        

    }//GEN-LAST:event_btnLuuActionPerformed
    private void resetFields() {
        // Ngày công
        txtNP.setText("0");
        txtNKL.setText("0");
        txtNL.setText("0");
        lblNCTT.setText("0");

        // Phụ cấp
        txtPCTS.setText("0");

        // Khoản trừ
        txtLTU.setText("0");

        // Lương
        lblLT.setText("0");
        txtLTh.setText("0");
        lblTL.setText("0");

        
    }
    private void btnLuuSlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSlActionPerformed
        // TODO add your handling code here:
        bl = blBUS.selectAll();
        int maCuoi = bl.get(bl.size() - 1).getMabl(); 
        int maMoi = maCuoi + 1;
        int mabl = maMoi;
        String taikhoan = lblTaikhoan.getText();
        String hoten = lblHoten.getText();
                
        int LCB = Integer.valueOf(lblLCB.getText());
        int LTh = Integer.valueOf(txtLTh.getText());
        int LT = Integer.valueOf(lblLT.getText());
        int TL = Integer.valueOf(lblTL.getText());
        Date timeL = Date.from(TGT.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int SNC = Integer.valueOf(lblNCTT.getText());
        int Trangthai = 1;
        
        BangLuongDTO bl = new BangLuongDTO(mabl, taikhoan, hoten, thang, nam, LCB, LTh, LT, TL, timeL, SNC, Trangthai);
        boolean result = blBUS.insertSalary(bl);
        if (result) {
            JOptionPane.showMessageDialog(this, "Đã lưu vào CSDL");
            resetFields();
            loadDataToTable(thang, nam);
        } else {
            JOptionPane.showMessageDialog(this, "Chưa thể lưu");
        }
    }//GEN-LAST:event_btnLuuSlActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtNPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNPActionPerformed

    private void txtLTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLTUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLTUActionPerformed

    private void txtNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNLActionPerformed

    private void txtLThActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLThActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLThActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeSalary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeSalary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuuSl;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBHBB;
    private javax.swing.JLabel lblBHXH;
    private javax.swing.JLabel lblBHYT;
    private javax.swing.JLabel lblHoten;
    private javax.swing.JLabel lblLCB;
    private javax.swing.JLabel lblLT;
    private javax.swing.JLabel lblLTU;
    private javax.swing.JLabel lblNCC;
    private javax.swing.JLabel lblNCTT;
    private javax.swing.JLabel lblPCCT;
    private javax.swing.JLabel lblPCXX;
    private javax.swing.JLabel lblTGT;
    private javax.swing.JLabel lblTL;
    private javax.swing.JLabel lblTaikhoan;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtLTU;
    private javax.swing.JTextField txtLTh;
    private javax.swing.JTextField txtNKL;
    private javax.swing.JTextField txtNL;
    private javax.swing.JTextField txtNP;
    private javax.swing.JTextField txtPCTS;
    // End of variables declaration//GEN-END:variables
}
