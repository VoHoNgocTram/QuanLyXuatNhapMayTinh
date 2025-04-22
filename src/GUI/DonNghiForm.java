/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BUS.ChiTietQuyenBUS;
import DTO.ChiTietQuyenDTO;
import DTO.DonNghiDTO;
import DTO.NguoiDungDTO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author downny
 */
public class DonNghiForm extends javax.swing.JInternalFrame {
    
    private final ChiTietQuyenBUS ctqBUS = new ChiTietQuyenBUS();
    /**
     * Creates new form DonNghiForm
     */
    private ArrayList<DonNghiDTO> dsDonNghi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DefaultTableCellRenderer renderer;
    public DonNghiForm(NguoiDungDTO user) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initComponents();
        
          // Authorization
        javax.swing.JButton[] buttons = {btnAdd, btnDelete, btnEdit};
        disableAllButtons(buttons);
        authorizeAction(user);
        
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblDonNghi.setDefaultRenderer(String.class, renderer);
        tblDonNghi.getTableHeader().setDefaultRenderer(renderer);
        tblDonNghi.setDefaultEditor(Object.class, null);
        // 👉 Gán dữ liệu cho dsDonNghi từ DAO
        dsDonNghi = DAO.DonNghiDAO.getInstance().getAllDanhSachDonNghi();
        loadTable(dsDonNghi);
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
            allowedActions = ctqBUS.getAllowedActions(user.getMaNhomQuyen(), "nhanvien");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(DonNghiForm.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(DonNghiForm.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    /*public void loadTable(ArrayList<PhieuNhapDTO> listPhieuNhap)(ArrayList{
       
        if(listPhieuNhap != null) {
            DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
            tblPhieuNhap.setDefaultEditor(Object.class, null);
            model.setRowCount(0);
            int stt = 0;
            for (int i = listPhieuNhap.size() - 1; i >= 0; i--) {
                PhieuNhapDTO phieuNhap = listPhieuNhap.get(i);
                String tenKho = khoDAO.getInstance().getWareHouseByID(phieuNhap.getMaKho());
                
                String trangThai;
                switch (phieuNhap.getTrangThai()) {
                    case 1:
                        trangThai = "Chờ xác nhận";
                        break;
                    case 2:
                        trangThai = "Đã hủy";
                        break;
                    case 3:
                        trangThai = "Đã xác nhận";
                        break;
                    case 4:
                        trangThai = "Đã giao hàng";
                        break;
                    default:
                        throw new AssertionError();
                }
                stt ++;
                Object[] row = {stt , phieuNhap.getMaPhieuNhap(), 
                    phieuNhap.getThoiGianTao(), tenKho, 
                    formatter.format( phieuNhap.getTongTien()), 
                    trangThai};
                model.addRow(row);
            }
            for(int i = 0; i < tblPhieuNhap.getColumnCount(); i++){
                tblPhieuNhap.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        }
        else {
           return;
        }
    }*/
    
    public void loadTable(ArrayList<DonNghiDTO> dsDonNghi){
        if (dsDonNghi != null){
            DefaultTableModel model = (DefaultTableModel) tblDonNghi.getModel();
            tblDonNghi.setDefaultEditor(Object.class, null);
            model.setRowCount(0);
            for (int i = dsDonNghi.size() - 1; i >= 0; i--){
                DonNghiDTO donNghi = dsDonNghi.get(i);
                
                String trangThai;
                switch (donNghi.getTrangThai()) {
                    case 1:
                        trangThai = "Chờ duyệt";
                        break;
                    case 2:
                        trangThai = "Đã duyệt";
                        break;
                    case 3:
                        trangThai = "Từ chối";
                        break;
                    default:
                        throw new AssertionError();
                }
                Object[] row = {donNghi.getMaDonNghi(), donNghi.getHoTen(), donNghi.getLoaiNghi(),
                                sdf.format(donNghi.getNgayNopDon()), sdf.format(donNghi.getNgayBatDau()), sdf.format(donNghi.getNgayKetThuc()),
                                donNghi.getGhiChu(), trangThai};
                model.addRow(row);
            }
            for(int i = 0; i < tblDonNghi.getColumnCount(); i++){
                tblDonNghi.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        }
        else{
            return;
        }
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
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbxlLuaChon = new javax.swing.JComboBox<>();
        txtSearchForm = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonNghi = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        btnDelete.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_40px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

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

        jPanel2.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 90));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxlLuaChon.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        cbxlLuaChon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ" }));
        jPanel3.add(cbxlLuaChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 40));

        txtSearchForm.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        txtSearchForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchFormActionPerformed(evt);
            }
        });
        txtSearchForm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFormKeyReleased(evt);
            }
        });
        jPanel3.add(txtSearchForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 400, 40));

        btnReset.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel3.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 120, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 760, 90));

        jScrollPane1.setBorder(null);

        tblDonNghi.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        tblDonNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã đơn nghỉ", "Họ tên", "Loại nghỉ", "Ngày nộp đơn", "Ngày bắt đầu nghỉ", "Ngày kết thúc nghỉ", "Ghi chú", "Trạng thái"
            }
        ));
        tblDonNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDonNghiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonNghi);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1160, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1180, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFormActionPerformed

    private void txtSearchFormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFormKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFormKeyPressed

    private void txtSearchFormKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFormKeyReleased

    }//GEN-LAST:event_txtSearchFormKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    }//GEN-LAST:event_btnResetActionPerformed

    private void tblDonNghiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonNghiMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblDonNghiMousePressed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cbxlLuaChon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblDonNghi;
    private javax.swing.JTextField txtSearchForm;
    // End of variables declaration//GEN-END:variables
}
