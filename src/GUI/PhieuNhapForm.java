/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package GUI;

import BUS.ChiTietQuyenBUS;
import DAO.PhieuNhapDAO;
import DAO.khoDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import DTO.PhieuNhapDTO;
import DTO.khoDTO;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import GUI.Dialog.CTPhieuNhap;
import java.util.List;

/**
 *
 * @author DELL
 */
public class PhieuNhapForm extends javax.swing.JInternalFrame {
    private final ChiTietQuyenBUS ctqBUS = new ChiTietQuyenBUS();
    private Date dateStart;
    private Date dateEnd;
    public NguoiDungDTO userDTO;

    /**
     * Creates new form LichSuPhieuNhap
     */
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }
    
    public PhieuNhapForm(NguoiDungDTO user) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        userDTO = user;
        javax.swing.JButton[] buttons = {btnThem};
        disableAllButtons(buttons);
        authorizeAction(user);
        TitledBorder titledBorderNgay = BorderFactory.createTitledBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
            "Lọc theo ngày",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.PLAIN, 14)
        );
        TitledBorder titledBorderGia = BorderFactory.createTitledBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
            "Lọc theo Giá",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.PLAIN, 14)
        );
        pnLocTheoNgay.setBorder(titledBorderNgay);
        pnLocTheoGia.setBorder(titledBorderGia);
        
        PhieuNhapDAO phieuNhapDao = new PhieuNhapDAO();
      
        
        
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblPhieuNhap.setDefaultRenderer(String.class, renderer);
        tblPhieuNhap.getTableHeader().setDefaultRenderer(renderer);
        tblPhieuNhap.setDefaultEditor(Object.class, null);
        loadDanhSachPhieuNhap();
        
                
        tblPhieuNhap.addMouseListener(new MouseAdapter(){
            private ActionEvent evt;
            public void mousePressed(MouseEvent mouseEvent){
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if(mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 ){
                    try {
                        detailPhieuNhap();
                    } catch (SQLException ex) {
                        Logger.getLogger(PhieuNhapForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
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
            allowedActions = ctqBUS.getAllowedActions(user.getMaNhomQuyen(), "phieunhap");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        for (ChiTietQuyenDTO ctq : allowedActions) {
            if (ctq.getHanhDong().equals("create")) {
                btnThem.setEnabled(true);
                continue;
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new javax.swing.JTable();
        pnLocTheoNgay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnTimNgay = new javax.swing.JButton();
        dateChooserStart = new com.toedter.calendar.JDateChooser();
        dateChooserEnd = new com.toedter.calendar.JDateChooser();
        pnLocTheoGia = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtGiaMin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaMax = new javax.swing.JTextField();
        btnTimGia = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        cbbTrangThai = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1180, 750));

        jPanel2.setBackground(new java.awt.Color(89, 168, 105));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DANH SÁCH PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã phiếu nhập", "Thời gian tạo", "Tên kho", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblPhieuNhapAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);
        if (tblPhieuNhap.getColumnModel().getColumnCount() > 0) {
            tblPhieuNhap.getColumnModel().getColumn(0).setResizable(false);
            tblPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblPhieuNhap.getColumnModel().getColumn(1).setResizable(false);
            tblPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnLocTheoNgay.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm theo ngày"));
        pnLocTheoNgay.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Từ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Đến");

        btnTimNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimNgay.setText("Tìm");
        btnTimNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNgayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLocTheoNgayLayout = new javax.swing.GroupLayout(pnLocTheoNgay);
        pnLocTheoNgay.setLayout(pnLocTheoNgayLayout);
        pnLocTheoNgayLayout.setHorizontalGroup(
            pnLocTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocTheoNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(dateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        pnLocTheoNgayLayout.setVerticalGroup(
            pnLocTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocTheoNgayLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnLocTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserEnd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLocTheoNgayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimNgay)
                .addGap(11, 11, 11))
        );

        pnLocTheoGia.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm theo giá"));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Từ");

        txtGiaMin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Đến");

        txtGiaMax.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnTimGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimGia.setText("Tìm");
        btnTimGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLocTheoGiaLayout = new javax.swing.GroupLayout(pnLocTheoGia);
        pnLocTheoGia.setLayout(pnLocTheoGiaLayout);
        pnLocTheoGiaLayout.setHorizontalGroup(
            pnLocTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocTheoGiaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimGia, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );
        pnLocTheoGiaLayout.setVerticalGroup(
            pnLocTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocTheoGiaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnLocTheoGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimGia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThem.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_40px.png"))); // NOI18N
        btnThem.setText("Yêu cầu phiếu nhập");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã hủy", "Chờ xác nhận", "Đã giao hàng", "Đã xác nhận" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(51, 153, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnLamMoi.setText("  Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnLocTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnLocTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnLocTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnLocTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNgayActionPerformed
        if(dateStart == null || dateEnd == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ngày !");
            return;
        }
        PhieuNhapDAO phieuNhapDao = new PhieuNhapDAO();
        ArrayList<PhieuNhapDTO> listPhieuNhap = phieuNhapDao.layDanhSachPNTheoNgay(this.dateStart, this.dateEnd);
        if(listPhieuNhap.size() > 0) {
            loadDanhSachPhieuNhap();
        }
        else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
            loadDanhSachPhieuNhap();
        }
        
    }//GEN-LAST:event_btnTimNgayActionPerformed

    private void dateChooserStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserStartPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            java.util.Date utilDate = dateChooserStart.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dateStart = new java.sql.Date(utilDate.getTime());
        }
    }//GEN-LAST:event_dateChooserStartPropertyChange

    private void dateChooserEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserEndPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            java.util.Date utilDate = dateChooserEnd.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dateEnd = new java.sql.Date(utilDate.getTime());
        }
    }//GEN-LAST:event_dateChooserEndPropertyChange

    private void btnTimGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimGiaActionPerformed
        if(txtGiaMin.getText().trim().equals("") && txtGiaMax.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá tiền để tìm kiếm");
            return ;
        }
        
        
        try {
            if(txtGiaMax.getText().equals("")){
                int giaMin = Integer.parseInt(txtGiaMin.getText());
                ArrayList<PhieuNhapDTO> list = PhieuNhapDAO.getInstance().searchGiaMin( giaMin);
                loadTable(list);
                return;
            }
            if(txtGiaMin.getText().equals("")){
                int giaMax = Integer.parseInt(txtGiaMax.getText());
                ArrayList<PhieuNhapDTO> list = PhieuNhapDAO.getInstance().searchGiaMax( giaMax);
                loadTable(list);
                return;
            }
            
            
            int giaMin = Integer.parseInt(txtGiaMin.getText());
            int giaMax = Integer.parseInt(txtGiaMax.getText());
            
            PhieuNhapDAO phieuNhapDao = new PhieuNhapDAO();
            ArrayList<PhieuNhapDTO> listPhieuNhap = phieuNhapDao.layDanhSachPNTheoGia(giaMin, giaMax);
            if(listPhieuNhap.size() > 0) {
                loadTable(listPhieuNhap);
            }
            else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kiểm tra lại giá nhập vào");
        }
    }//GEN-LAST:event_btnTimGiaActionPerformed

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked

    }//GEN-LAST:event_tblPhieuNhapMouseClicked

    private void tblPhieuNhapAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblPhieuNhapAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPhieuNhapAncestorAdded

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        loadDanhSachPhieuNhap();
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        NhapHangForm nh = new NhapHangForm(userDTO, this);
        nh.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        loadDanhSachPhieuNhap();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    
    
    public void loadDanhSachPhieuNhap() {
        String choice = cbbTrangThai.getSelectedItem().toString();
        ArrayList<PhieuNhapDTO> listPhieuNhap;
        switch (choice){
            case "Tất cả" :
                listPhieuNhap = PhieuNhapDAO.getInstance().getAllDanhSachPhieuNhap();
                loadTable(listPhieuNhap);
                break;
            case "Đã hủy":
                listPhieuNhap = PhieuNhapDAO.getInstance().danhSachPhieuNhapTheoTrangThai(2);
                loadTable(listPhieuNhap);
                break;
            case "Chờ xác nhận":
                listPhieuNhap = PhieuNhapDAO.getInstance().danhSachPhieuNhapTheoTrangThai(1);
                loadTable(listPhieuNhap);
                break;
            case "Đã giao hàng":
                listPhieuNhap = PhieuNhapDAO.getInstance().danhSachPhieuNhapTheoTrangThai(4);
                loadTable(listPhieuNhap);
                break;
            case "Đã xác nhận":
                listPhieuNhap = PhieuNhapDAO.getInstance().danhSachPhieuNhapTheoTrangThai(3);
                loadTable(listPhieuNhap);
                break;
            default:
                throw new AssertionError();
        }
        
        
    }
    
    public void loadTable(ArrayList<PhieuNhapDTO> listPhieuNhap){
       
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
    }
    public void detailPhieuNhap() throws SQLException{
        
        int row = tblPhieuNhap.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập bạn muốn xem chi tiết");
            return;
        } else{
            PhieuNhapDTO pn;
            int maPhieu = (Integer.parseInt(tblPhieuNhap.getValueAt(row, 1).toString()));
            pn = PhieuNhapDAO.getInstance().layPhieuNhapTheoMa(maPhieu);
            
            if(userDTO.getMaNhomQuyen() == 2){
                QuanLyPhieuNhapForm ql = new QuanLyPhieuNhapForm(pn, this, userDTO);
                ql.setVisible(true);
                return ;
            }
        
            if(userDTO.getMaNhomQuyen() == 3){
                CTPhieuNhap detailGUI = new CTPhieuNhap(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, pn, userDTO);
                detailGUI.setVisible(true);
            }
            
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new PhieuNhapForm().setVisible(true);
            }
        });
    }

    private DefaultTableModel model;
    private DefaultTableCellRenderer renderer;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimGia;
    private javax.swing.JButton btnTimNgay;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private com.toedter.calendar.JDateChooser dateChooserEnd;
    private com.toedter.calendar.JDateChooser dateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnLocTheoGia;
    private javax.swing.JPanel pnLocTheoNgay;
    private javax.swing.JTable tblPhieuNhap;
    private javax.swing.JTextField txtGiaMax;
    private javax.swing.JTextField txtGiaMin;
    // End of variables declaration//GEN-END:variables

}
