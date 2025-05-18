/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BUS.BangLuongBUS;
import BUS.ChiTietQuyenBUS;
import DTO.BangLuongDTO;
import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author downny
 */
public class BangLuongForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form BangLuongForm
     */
    private final ChiTietQuyenBUS ctqBUS = new ChiTietQuyenBUS();
    private DefaultTableModel tblModel;
    BangLuongBUS blBUS = new BangLuongBUS();
    public BangLuongForm(NguoiDungDTO user) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initComponents();
        initTable();
        loadDataToTable();
        cbxThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBangLuongTheoThangVaNam();
            }
        });

        cbxNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBangLuongTheoThangVaNam();
            }
        });
        tblLuong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getDetailBL();
                }
            }
        });
        
          // Authorization
        javax.swing.JButton[] buttons = {btnAdd, btnDelete, btnEdit};
        disableAllButtons(buttons);
        authorizeAction(user);
    }
    
    private void disableAllButtons(javax.swing.JButton[] buttons) {
        for (javax.swing.JButton btn : buttons) {
            btn.setEnabled(false);
        }
    }
    
    private void authorizeAction(NguoiDungDTO user) {
        // Get all allowed actions in this functionality
        List<ChiTietQuyenDTO> allowedActions = new ArrayList<>();
        try {
            allowedActions = ctqBUS.getAllowedActions(user.getMaNhomQuyen(), "bangluong");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(BangLuongForm.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(BangLuongForm.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        for (ChiTietQuyenDTO ctq : allowedActions) {
            if (ctq.getHanhDong().equals("create")) {
                btnAdd.setEnabled(true);
                continue;
            }
            if (ctq.getHanhDong().equals("update")) {
                btnEdit.setEnabled(true);
                continue;
            }
            if (ctq.getHanhDong().equals("delete")) {
                btnDelete.setEnabled(true);
                continue;
            }
        }
 
    }
    public final void initTable() {
        // Tiêu đề các cột
        String[] headerTbl = new String[]{
            "Mã BL", "Tài khoản", "Họ Tên", "Lương cơ bản", "Lương trừ", "Tổng lương", "Ngày tính lương", "Trạng thái"
        };

        // Tạo model không cho sửa
        tblModel = new DefaultTableModel(null, headerTbl) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLuong.setModel(tblModel);

        // Căn chỉnh độ rộng cột
        tblLuong.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblLuong.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblLuong.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblLuong.getColumnModel().getColumn(2).setPreferredWidth(2);
        tblLuong.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblLuong.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblLuong.getColumnModel().getColumn(5).setPreferredWidth(200);
        tblLuong.getColumnModel().getColumn(6).setPreferredWidth(150);

        // Căn giữa nội dung bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblLuong.getColumnCount(); i++) {
            tblLuong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Nếu có dữ liệu thì chọn dòng đầu tiên
        if (tblModel.getRowCount() > 0) {
            tblLuong.setRowSelectionInterval(0, 0);
        }
    }
    public void loadDataToTable() {
        try {
            ArrayList<BangLuongDTO> bl = new ArrayList<BangLuongDTO>();
            bl = blBUS.selectAll();
            cbxThang.removeAllItems();
            cbxNam.removeAllItems();
            tblModel.setRowCount(0);
            Set<Integer> setThang = new TreeSet<>();
            Set<Integer> setNam = new TreeSet<>();
            for (BangLuongDTO luong : bl) {
                setThang.add(luong.getThang());
                setNam.add(luong.getNam());
            }
            for (Integer t : setThang) {
                cbxThang.addItem(String.valueOf(t));
            }
            for (Integer n : setNam) {
                cbxNam.addItem(String.valueOf(n));
            }

            // ✅ Lấy tháng & năm đang được chọn từ combobox
            int thang = Integer.parseInt(cbxThang.getSelectedItem().toString());
            int nam = Integer.parseInt(cbxNam.getSelectedItem().toString());
            for (BangLuongDTO i : bl) {
                if (i.getThang() == thang && i.getNam() == nam) {
                    tblModel.addRow(new Object[]{
                        i.getMabl(), i.getTaikhoan(), i.getHoten(), i.getLuongcoban(), i.getTruluong(), i.getTongluong(), i.getNgaytinhluong(),i.getTrangthai()
                    });
                }
            }
            
            // Auto chọn dòng đầu tiên
            if (tblModel.getRowCount() > 0) {
                tblLuong.setRowSelectionInterval(0, 0);
                getDetailBL();
            }
        } catch (Exception e) {
        }
    }
    
    public void filterBangLuongTheoThangVaNam() {
        if (cbxThang.getSelectedItem() == null || cbxNam.getSelectedItem() == null) return;

        int thang = Integer.parseInt(cbxThang.getSelectedItem().toString());
        int nam = Integer.parseInt(cbxNam.getSelectedItem().toString());

        ArrayList<BangLuongDTO> bl = blBUS.selectAll();
        tblModel.setRowCount(0);

        for (BangLuongDTO i : bl) {
            if (i.getThang() == thang && i.getNam() == nam) {
                tblModel.addRow(new Object[]{
                    i.getMabl(),
                    i.getTaikhoan(),
                    i.getHoten(),
                    i.getLuongcoban(),
                    i.getTruluong(),
                    i.getTongluong(),
                    i.getNgaytinhluong(),
                    i.getTrangthai()
                });
            }
        }

        if (tblModel.getRowCount() > 0) {
            tblLuong.setRowSelectionInterval(0, 0);
            getDetailBL();
        }
    }
    public void getDetailBL(){
        int selectedRow = tblLuong.getSelectedRow();
        if (selectedRow < 0) return;
        int mabl = Integer.parseInt(tblLuong.getValueAt(selectedRow, 0).toString());
        BangLuongDTO bl=blBUS.getBangLuongByMa(mabl);
        if (bl != null) {
            jLabel1.setText(bl.getHoten());
            jLabel2.setText(String.valueOf(bl.getLuongcoban()));
            jLabel3.setText(String.valueOf(bl.getThuong()));
            jLabel4.setText(String.valueOf(bl.getSongaycong()));
            jLabel5.setText(String.valueOf(bl.getTruluong()));
            jLabel6.setText(String.valueOf(bl.getTongluong()));
            jLabel7.setText(String.valueOf(bl.getTrangthai()));
            jLabel25.setText(String.valueOf(bl.getNgaytinhluong()));
        }
//        System.out.println(mabl);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuong = new javax.swing.JTable();
        lblHoten = new javax.swing.JLabel();
        lblLuongcb = new javax.swing.JLabel();
        lblThuong = new javax.swing.JLabel();
        lblSnc = new javax.swing.JLabel();
        lblLuongtru = new javax.swing.JLabel();
        lblTrangthai = new javax.swing.JLabel();
        lblTongluong = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxThang = new javax.swing.JComboBox<>();
        cbxNam = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblNgaytl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã BL", "Tài khoản", "Họ tên", "Lương cơ bản", "Lương trừ", "Tổng lương", "Ngày tính lương", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tblLuong);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1160, 310));

        lblHoten.setText("Họ tên: ");
        jPanel2.add(lblHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        lblLuongcb.setText("Lương cơ bản:");
        jPanel2.add(lblLuongcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        lblThuong.setText("Thưởng:");
        jPanel2.add(lblThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        lblSnc.setText("Số ngày công: ");
        jPanel2.add(lblSnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        lblLuongtru.setText("Lương trừ: ");
        jPanel2.add(lblLuongtru, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        lblTrangthai.setText("Trạng thái: ");
        jPanel2.add(lblTrangthai, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        lblTongluong.setText("Tổng lương: ");
        jPanel2.add(lblTongluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jLabel8.setText("Cách tính lương:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jLabel9.setText("Lương ngày = ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        jLabel10.setText("Lương cơ bản");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, -1, -1));

        jLabel11.setText(":");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, -1, -1));

        jLabel12.setText("(Số ngày trong tháng - 4)");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        jLabel13.setText("Lương trừ =");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, -1));

        jLabel14.setText("Lương ngày");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, -1, -1));

        jLabel15.setText("x");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, -1));

        jLabel16.setText("Số ngày nghỉ");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, -1, -1));

        jLabel17.setText("Tổng lương = ");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, -1));

        jLabel18.setText("Lương cơ bản ");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, -1, -1));

        jLabel19.setText("+");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel20.setText("Thưởng");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, -1));

        jLabel21.setText("-");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        jLabel22.setText("Lương trừ");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        cbxThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 240, -1, -1));

        cbxNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 240, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Tháng: ");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 240, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Năm: ");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 240, -1, -1));

        lblNgaytl.setText("Ngày tính lương:");
        jPanel2.add(lblNgaytl, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabel5.setText("jLabel5");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        jLabel6.setText("jLabel6");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jLabel7.setText("jLabel7");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, -1));

        jLabel25.setText("jLabel25");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        btnReset.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel2.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 120, 40));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnAdd.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_40px.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAdd);

        btnEdit.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_40px.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);

        btnDelete.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_40px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(654, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblLuong.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.");
                    return;
                }

                // Lấy dữ liệu từ bảng
                String maBL = tblLuong.getValueAt(selectedRow, 0).toString();
                String taiKhoan = tblLuong.getValueAt(selectedRow, 1).toString();
                String hoTen = tblLuong.getValueAt(selectedRow, 2).toString();
                int luongCoBan = Integer.parseInt(tblLuong.getValueAt(selectedRow, 3).toString());
                int luongTru = Integer.parseInt(tblLuong.getValueAt(selectedRow, 4).toString());
                int tongLuong = Integer.parseInt(tblLuong.getValueAt(selectedRow, 5).toString());
                String ngayTinhLuong = tblLuong.getValueAt(selectedRow, 6).toString();
                int SNC = Integer.valueOf(jLabel4.getText());
                int LTh = Integer.valueOf(jLabel3.getText());
                int trangThai = Integer.parseInt(tblLuong.getValueAt(selectedRow, 7).toString());

                // Mở frame sửa và truyền dữ liệu
                UpdateLuong suaFrame = new UpdateLuong(maBL, taiKhoan, hoTen, luongCoBan, luongTru, tongLuong, ngayTinhLuong, SNC, trangThai, LTh);
                suaFrame.setVisible(true);
            }
        });

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        EmployeeSalary es = new EmployeeSalary();
        es.setVisible(true);
        es.setLocationRelativeTo(null);
        es.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cbxNam;
    private javax.swing.JComboBox<String> cbxThang;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblHoten;
    private javax.swing.JLabel lblLuongcb;
    private javax.swing.JLabel lblLuongtru;
    private javax.swing.JLabel lblNgaytl;
    private javax.swing.JLabel lblSnc;
    private javax.swing.JLabel lblThuong;
    private javax.swing.JLabel lblTongluong;
    private javax.swing.JLabel lblTrangthai;
    private javax.swing.JTable tblLuong;
    // End of variables declaration//GEN-END:variables
}
