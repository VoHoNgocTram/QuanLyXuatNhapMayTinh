/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.NhomQuyenBUS;
import DTO.ChucVuDTO;
import DTO.NhomQuyenDTO;
import DTO.NguoiDungDTO;
import DTO.lsChucVuDTO;
import helper.Exception.EmptyFieldException;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.SQLException;
/**
 *
 * @author Acer
 */
public class AddNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form AddNhanVien
     */
    NhanVienBUS nvBUS = new NhanVienBUS();
    NhomQuyenBUS nqBUS = new NhomQuyenBUS();
    
    private File selectedImageFile = null;
    
    private ArrayList<ChucVuDTO> danhSachChucVu = new ArrayList<>();
    private ArrayList<NhomQuyenDTO> danhSachNhomQuyen = new ArrayList<>();
    private ArrayList<lsChucVuDTO> lsChucVu = new ArrayList<>();
    
    public AddNhanVien() {
        initComponents();
        buttonGroup1.add(rbtnNam);
        buttonGroup1.add(rbtnNu);
        txtSgp.setEditable(false);
        txtTrangthai.setEditable(false);
        loadDataToPanel();
    }

    AddNhanVien(NhanVienForm aThis, JFrame jFrame, boolean rootPaneCheckingEnabled) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    public void loadDataToPanel() {
        try {
            // Lưu danh sách chức vụ vào biến toàn cục
            danhSachChucVu = nvBUS.getAllDSChucVu();
            danhSachNhomQuyen = nqBUS.getAllDSNhomQuyen();

            cbxChucvu.removeAllItems();
            cbxVaitro.removeAllItems();

            // Load chức vụ vào combobox
            for (ChucVuDTO i : danhSachChucVu) {
                cbxChucvu.addItem(i.getMaChucVu() + " - " + i.getTenChucVu());
            }

            // Load tài khoản
            for (NhomQuyenDTO i : danhSachNhomQuyen) {
                cbxVaitro.addItem(i.getMaNhomQuyen() + " - " + i.getTenNhomQuyen());
            }

            // Gán sự kiện chọn chức vụ để cập nhật lương tương ứng
            cbxChucvu.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        int selectedIndex = cbxChucvu.getSelectedIndex();
                        if (selectedIndex >= 0 && selectedIndex < danhSachChucVu.size()) {
                            int luong = danhSachChucVu.get(selectedIndex).getLuongCoBan();
                            txtLuong.removeAll();
                            txtLuong.setText(String.valueOf(luong));
                            txtLuong.setEditable(false);
                        }
                    }
                }
            });
            
            if (!danhSachChucVu.isEmpty()) {
            int luongDauTien = danhSachChucVu.get(0).getLuongCoBan();
            txtLuong.setText(String.valueOf(luongDauTien));
            txtLuong.setEditable(false);
}
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra cho dễ debug
        }
    }
    public boolean isValidPhoneNumber(String sdt) {
        return sdt != null && sdt.matches("^\\d{10}$");
    }
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
    public boolean isNgayVaoLamHopLe(java.util.Date ngaysinh, java.util.Date ngayvaolam) {
        return ngayvaolam.after(ngaysinh);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        txtTaikhoan = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        rbtnNu = new javax.swing.JRadioButton();
        rbtnNam = new javax.swing.JRadioButton();
        txtSdt = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cbxChucvu = new javax.swing.JComboBox<>();
        txtTrangthai = new javax.swing.JTextField();
        txtSgp = new javax.swing.JTextField();
        cbxVaitro = new javax.swing.JComboBox<>();
        dcNgaysinh = new com.toedter.calendar.JDateChooser();
        dcNgayvaolam = new com.toedter.calendar.JDateChooser();
        txtLuong = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("THÊM NHÂN VIÊN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(467, 467, 467))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        lblAvatar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAvatar.setText("Ảnh nhân viên");
        lblAvatar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tài khoản:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Họ tên: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giới tính: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày sinh: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại: ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ: ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Chức vụ: ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ngày vào làm: ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Trạng thái: ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Số giấy phép: ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Lương cơ bản: ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Vai trò: ");

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        txtTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaikhoanActionPerformed(evt);
            }
        });

        rbtnNu.setText("Nữ");
        rbtnNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNuActionPerformed(evt);
            }
        });

        rbtnNam.setSelected(true);
        rbtnNam.setText("Nam");

        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        txtDiachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiachiActionPerformed(evt);
            }
        });

        cbxChucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxChucvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxChucvuItemStateChanged(evt);
            }
        });

        txtTrangthai.setText("Đang làm");
        txtTrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangthaiActionPerformed(evt);
            }
        });

        txtSgp.setText("0");

        cbxVaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnHuy.setBackground(new java.awt.Color(255, 0, 51));
        btnHuy.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.setBorder(null);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThem.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnThem.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSgp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(222, 222, 222)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(rbtnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(rbtnNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(dcNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxChucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcNgayvaolam, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnChonAnh)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(txtTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(rbtnNam)
                            .addComponent(rbtnNu)
                            .addComponent(cbxChucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel11))
                            .addComponent(dcNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcNgayvaolam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonAnh)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtSgp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaikhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaikhoanActionPerformed

    private void rbtnNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnNuActionPerformed

    private void txtDiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiachiActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh");

        // Chỉ cho phép chọn ảnh (JPEG, PNG, GIF)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif");
        fileChooser.setFileFilter(filter);

        // Mở cửa sổ chọn file
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy file ảnh đã chọn
            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println(selectedFile);
            // Tạo ImageIcon từ file đã chọn
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

            // Lấy kích thước của JLabel
            int labelWidth = lblAvatar.getWidth();
            int labelHeight = lblAvatar.getHeight();

            // Scale ảnh cho vừa với JLabel
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã scale
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Gán ImageIcon vào JLabel
            lblAvatar.setIcon(scaledIcon);
            
            selectedImageFile = fileChooser.getSelectedFile();
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void cbxChucvuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxChucvuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxChucvuItemStateChanged

    private void txtTrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangthaiActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed
    private void clearFormInputs() {
        txtTaikhoan.setText("");
        txtHoten.setText("");
        txtDiachi.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        dcNgaysinh.setDate(null);         // Nếu dùng JDateChooser
        dcNgayvaolam.setDate(null);       // Nếu dùng JDateChooser
        txtSgp.setText("0");

        rbtnNam.setSelected(true);        // Chọn lại mặc định là Nam
        cbxChucvu.setSelectedIndex(0);   // Reset về item đầu tiên
        cbxVaitro.setSelectedIndex(0);

        lblAvatar.setIcon(null);              // Xóa ảnh nếu có
//        duongDanAnh = null;               // Xóa đường dẫn ảnh nếu có
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String taikhoan = txtTaikhoan.getText();
        String Hoten = txtHoten.getText().trim();
        
        String Gioitinh = null;
        if (rbtnNam.isSelected()) {
            Gioitinh = "Nam";
        } else if (rbtnNu.isSelected()) {
            Gioitinh = "Nữ";
        }
        
        Date Ngaysinh = dcNgaysinh.getDate();
        String Sdt = txtSdt.getText();
        String Diachi = txtDiachi.getText().trim();
        String Email = txtEmail.getText().trim();
        Date Ngayvaolam = dcNgayvaolam.getDate();
        int Sgp = Integer.valueOf(txtSgp.getText());
        
        String Chucvu = (String) cbxChucvu.getSelectedItem();
        int Luongcoban = Integer.valueOf(txtLuong.getText());
        String Vaitro = (String) cbxVaitro.getSelectedItem();
        String[] parts = Chucvu.split(" - ");
        String[] parts1 = Vaitro.split(" - ");
        int maChucVu = Integer.parseInt(parts[0]);
        String tenchucvu = parts[1];
        
        int manhomquyen= Integer.parseInt(parts1[0]);
        int Trangthai = 1;
        
        lsChucVu = nvBUS.getAllLichSuChucVu();
        int maCuoi = lsChucVu.get(lsChucVu.size() - 1).getMals(); 
        int maMoi = maCuoi + 1;
        int mals = maMoi;

        // --- XỬ LÝ LƯU ẢNH ---
        if (selectedImageFile != null) {
            try {
                String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
                String newFileName = taikhoan + extension;
                File destDir = new File("src/imageAvatar");
                if (!destDir.exists()) destDir.mkdirs();
                File destFile = new File(destDir, newFileName);
                Files.copy(selectedImageFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu ảnh: " + ex.getMessage());
            }
        } 

    NguoiDungDTO newUser = new NguoiDungDTO(taikhoan, Hoten, Ngaysinh, Gioitinh, Diachi, Sdt, Email, maChucVu, Ngayvaolam, Sgp, Luongcoban, Trangthai, tenchucvu, manhomquyen);
    lsChucVuDTO newChucVu = new lsChucVuDTO(mals, taikhoan, Hoten, maChucVu, tenchucvu, Ngayvaolam, Luongcoban, Trangthai);
    boolean result;
        try {
            result = nvBUS.handleCreateNewUser(newUser, newChucVu);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(AddNhanVien.this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            if (e.getFieldName().equalsIgnoreCase("Taikhoan")) {
                txtTaikhoan.requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("Hoten")) {
                txtHoten.requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("email")) {
                txtEmail.requestFocus();
            } else if (e.getFieldName().equalsIgnoreCase("Diachi")) {
                txtDiachi.requestFocus();
            }else if (e.getFieldName().equalsIgnoreCase("sdt")) {
                txtSdt.requestFocus();
            }else if (e.getFieldName().equalsIgnoreCase("Ngaysinh")) {
                dcNgaysinh.requestFocus();
            }else if (e.getFieldName().equalsIgnoreCase("Ngayvaolam")) {
                dcNgayvaolam.requestFocus();
            }
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(AddNhanVien.this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(AddNhanVien.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AddNhanVien.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        if (result) {
            clearFormInputs();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên mới thành công");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thêm nhân viên, vui lòng thử lại.");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddNhanVien().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbxChucvu;
    private javax.swing.JComboBox<String> cbxVaitro;
    private com.toedter.calendar.JDateChooser dcNgaysinh;
    private com.toedter.calendar.JDateChooser dcNgayvaolam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JRadioButton rbtnNam;
    private javax.swing.JRadioButton rbtnNu;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSgp;
    private javax.swing.JTextField txtTaikhoan;
    private javax.swing.JTextField txtTrangthai;
    // End of variables declaration//GEN-END:variables
}
