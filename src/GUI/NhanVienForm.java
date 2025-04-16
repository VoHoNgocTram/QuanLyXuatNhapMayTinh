/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import BUS.ChiTietQuyenBUS;
import BUS.NhanVienBUS;
import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import DTO.NhanVienDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author downny
 */
public class NhanVienForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form NhanVienForm
     */
    private final ChiTietQuyenBUS ctqBUS = new ChiTietQuyenBUS();
    private DefaultTableModel tblModel;
    NhanVienBUS nvBUS = new NhanVienBUS();
    public NhanVienForm(NguoiDungDTO user) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initComponents();
        lblManv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblImg.setSize(50, 50);
        authorizeAction(user);
        initTable();
        tblNV.setDefaultEditor(Object.class, null);
        loadDataToTable();
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                loadAvatar();
            }
        });
        tblNV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getNhanVienByMa();
                }
            }
        });
    }
    
    private void authorizeAction(NguoiDungDTO user) {
        // Get all allowed actions in this functionality
        List<ChiTietQuyenDTO> allowedActions = new ArrayList<>();
        try {
            allowedActions = ctqBUS.getAllowedActions(user.getMaNhomQuyen(), "nhanvien");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(NhanVienForm.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(NhanVienForm.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
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
            "Mã Nhân Viên", "Họ Tên", "Giới tính", "Ngày Sinh", "Số điện thoại", "Địa chỉ", "Trạng thái"
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
        tblNV.getColumnModel().getColumn(6).setPreferredWidth(150);

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
        public void loadDataToTable() {
            try {
                ArrayList<NhanVienDTO> nv = new ArrayList<NhanVienDTO>();
                nv = nvBUS.getAllDSNhanVien();
                tblModel.setRowCount(0);
                for (NhanVienDTO i : nv) {
                    tblModel.addRow(new Object[]{
                        i.getManv(), i.getHoten(), i.getGioitinh(), i.getNgaysinh(), i.getSdt(), i.getDiachi(),i.getTrangthai()
                    });
                }
                // Auto chọn dòng đầu tiên
                if (tblModel.getRowCount() > 0) {
                    tblNV.setRowSelectionInterval(0, 0);
                    getNhanVienByMa();
                }
            } catch (Exception e) {
            }
    }
    public void loadAvatar() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon/default-img.jpg"));

            // Scale ảnh về đúng kích thước của lblImg
            Image scaled = icon.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);

            // Set ảnh đã scale
            lblImg.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            System.out.println("Không thể tải ảnh: " + e.getMessage());
        }
    }
    public void getNhanVienByMa(){
        int selectedRow = tblNV.getSelectedRow();
        int manv = Integer.parseInt(tblNV.getValueAt(selectedRow, 0).toString());
        NhanVienDTO nv=nvBUS.getNhanVienByMa(manv);
        if (nv != null) {
            lblManv.setText("Mã nhân viên: "+ String.valueOf(nv.getManv()));
            lblHoten.setText("Họ tên: "+ nv.getHoten());
            lblNgaysinh.setText("Ngày sinh: "+ nv.getNgaysinh().toString());
            lblGioitinh.setText("Giới tính: "+ nv.getGioitinh());
            lblDiachi.setText("Địa chỉ: "+ nv.getDiachi());
            lblSdt.setText("Số điện thoại: "+ nv.getSdt());
            lblEmail.setText("Email: "+ nv.getEmail());
            lblChucvu.setText("Chức vụ: "+ nv.getTenchucvu());
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
        tblNV = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblImg = new javax.swing.JLabel();
        lblManv = new javax.swing.JLabel();
        lblHoten = new javax.swing.JLabel();
        lblGioitinh = new javax.swing.JLabel();
        lblNgaysinh = new javax.swing.JLabel();
        lblDiachi = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblChucvu = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1180, 774));

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

        tblNV.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Trạng thái"
            }
        ));
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNVMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNV);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 1160, 360));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblImg.setText("imgAvatar");
        lblImg.setMaximumSize(new java.awt.Dimension(50, 50));
        lblImg.setMinimumSize(new java.awt.Dimension(50, 50));
        lblImg.setPreferredSize(new java.awt.Dimension(50, 50));

        lblManv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblManv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblManv.setText("jLabel1");

        lblHoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHoten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoten.setText("jLabel2");

        lblGioitinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGioitinh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGioitinh.setText("jLabel3");

        lblNgaysinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgaysinh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNgaysinh.setText("jLabel4");

        lblDiachi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDiachi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDiachi.setText("jLabel5");

        lblSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSdt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSdt.setText("jLabel6");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEmail.setText("jLabel7");

        lblChucvu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChucvu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChucvu.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGioitinh, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(lblNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblChucvu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblManv, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDiachi, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(lblSdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblManv)
                            .addComponent(lblDiachi))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoten)
                            .addComponent(lblSdt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioitinh)
                            .addComponent(lblEmail))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgaysinh)
                            .addComponent(lblChucvu))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1160, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1192, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        AddNhanVien addNV = new AddNhanVien();
        addNV.setVisible(true);
        addNV.setLocationRelativeTo(null);
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

    private void tblNVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMousePressed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_tblNVMousePressed
//    public void runOnLoad() {
//        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/default-img.jpg"));
//        Image scaled = icon.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
//        lblImg.setIcon(new ImageIcon(scaled));
//    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cbxlLuaChon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblChucvu;
    private javax.swing.JLabel lblDiachi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioitinh;
    private javax.swing.JLabel lblHoten;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblManv;
    private javax.swing.JLabel lblNgaysinh;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtSearchForm;
    // End of variables declaration//GEN-END:variables
}
