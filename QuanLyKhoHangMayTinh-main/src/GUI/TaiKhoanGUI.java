package GUI;

import BUS.ChiTietQuyenBUS;
import BUS.NguoiDungBUS;
import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import GUI.Dialog.CapNhatTaiKhoanDialog;
import GUI.Dialog.TaoTaiKhoanDialog;
import helper.TaiKhoanTableModel;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class TaiKhoanGUI extends javax.swing.JInternalFrame {
    private final ChiTietQuyenBUS ctqBUS = new ChiTietQuyenBUS();
    private final NguoiDungBUS ndBUS = new NguoiDungBUS();
    private TaiKhoanTableModel tableModel;
    private String query;
    private int userPriority;
    private NguoiDungDTO user;
    private ArrayList<NguoiDungDTO> arr = new ArrayList<>();
    
    public TaiKhoanGUI(NguoiDungDTO user) {
        initComponents();
        UIManager.put("Table.showVerticalLines", true);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblAccount.setDefaultEditor(Object.class, null);
        
        // Authorization
        javax.swing.JButton[] buttons = {btnAdd, btnDeleteAccount, btnEditAccount};
        disableAllButtons(buttons);
        authorizeAction(user);
        this.user = user;
        initTable();
        setQuery("");
        queryUserPriority(user);
        getUserList(query, userPriority);
    }
    
    public TaiKhoanGUI() {
        initComponents();
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
            allowedActions = ctqBUS.getAllowedActions(user.getMaNhomQuyen(), "taikhoan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        for (ChiTietQuyenDTO ctq : allowedActions) {
            if (ctq.getHanhDong().equals("create")) {
                btnAdd.setEnabled(true);
                continue;
            }
            if (ctq.getHanhDong().equals("update")) {
                btnEditAccount.setEnabled(true);
                continue;
            }
            if (ctq.getHanhDong().equals("delete")) {
                btnDeleteAccount.setEnabled(true);
                continue;
            }
        }
    }
    
    private void initTable() {
        String[] columnNames = {"Tên đăng nhập", "Họ tên", "Email", "Mã nhóm quyền", "Nhóm quyền", "Độ ưu tiên"};
        this.tableModel = new TaiKhoanTableModel(new ArrayList<>(), columnNames);
        tblAccount.setModel(tableModel);
        int[] columnsToBeHidden = {3, 5};
        for (int c : columnsToBeHidden) {
            tblAccount.getColumnModel().getColumn(c).setMinWidth(0);
            tblAccount.getColumnModel().getColumn(c).setMaxWidth(0);
            tblAccount.getColumnModel().getColumn(c).setWidth(0);
            tblAccount.getColumnModel().getColumn(c).setPreferredWidth(0);
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(int userPriority) {
        this.userPriority = userPriority;
    }
    
    private void queryUserPriority(NguoiDungDTO user) {
        int priority = -1;
        try {
            priority = ndBUS.getUserPriority(user);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        setUserPriority(priority);
    }
    
    public void getUserList(String query, int priority) {
        ArrayList<NguoiDungDTO> arr = null;
        try {
            arr = ndBUS.getUserList(query, priority);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.arr = arr;
        
        tableModel.setData(arr);
        tableModel.fireTableDataChanged();
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
        btnEditAccount = new javax.swing.JButton();
        btnDeleteAccount = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnreset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_40px.png"))); // NOI18N
        btnAdd.setText("Tạo");
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

        btnEditAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_40px.png"))); // NOI18N
        btnEditAccount.setText("Cập nhật");
        btnEditAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditAccount.setFocusable(false);
        btnEditAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAccountActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditAccount);

        btnDeleteAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_40px.png"))); // NOI18N
        btnDeleteAccount.setText("Khoá");
        btnDeleteAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteAccount.setFocusable(false);
        btnDeleteAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeleteAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAccountActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDeleteAccount);

        jPanel2.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 90));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        jPanel3.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 380, 40));

        btnreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnreset.setText("Làm mới");
        btnreset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        jPanel3.add(btnreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 140, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 550, 90));

        jScrollPane1.setBorder(null);

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên đăng nhập", "Họ tên", "Email", "Mã nhóm quyền", "Nhóm quyền", "Độ ưu tiên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblAccount.setGridColor(new java.awt.Color(204, 204, 204));
        tblAccount.setShowGrid(true);
        tblAccount.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAccount);
        if (tblAccount.getColumnModel().getColumnCount() > 0) {
            tblAccount.getColumnModel().getColumn(0).setResizable(false);
            tblAccount.getColumnModel().getColumn(1).setResizable(false);
            tblAccount.getColumnModel().getColumn(2).setResizable(false);
            tblAccount.getColumnModel().getColumn(3).setResizable(false);
            tblAccount.getColumnModel().getColumn(4).setResizable(false);
            tblAccount.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1160, 630));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        TaoTaiKhoanDialog cttk = new TaoTaiKhoanDialog(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, userPriority, query);
        cttk.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAccountActionPerformed
        // TODO add your handling code here:
        int row = tblAccount.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần khoá");
            return;
        }
        int priority = Integer.parseInt(tblAccount.getValueAt(row, 5).toString());
        if (priority >= userPriority) {
            JOptionPane.showMessageDialog(this, "Bạn không thể khoá tài khoản này.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn khoá tài khoản này?",
                "Tài khoản",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
        String username = tblAccount.getValueAt(row, 0).toString();
        int result = 0;
        try {
            NguoiDungDTO userToBeLocked = new NguoiDungDTO();
            userToBeLocked.setTaiKhoan(username);
            result = ndBUS.lockAccount(userToBeLocked);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        if (result <= 0) {
            JOptionPane.showMessageDialog(TaiKhoanGUI.this, "Có lỗi xảy ra khi khoá tài khoản này, vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getUserList(query, userPriority);
    }//GEN-LAST:event_btnDeleteAccountActionPerformed

    private void btnEditAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAccountActionPerformed
        // TODO add your handling code here:
        int row = tblAccount.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần cập nhật thông tin");
            return;
        }
        int priority = Integer.parseInt(tblAccount.getValueAt(row, 5).toString());
        if (priority >= userPriority) {
            JOptionPane.showMessageDialog(this, "Bạn không thể cập nhật thông tin tài khoản này.");
            return;
        }
        NguoiDungDTO userInfo = arr.get(row);
        CapNhatTaiKhoanDialog dialog = new CapNhatTaiKhoanDialog(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled, userInfo, userPriority, query);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnEditAccountActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        setQuery("");
        getUserList(query, userPriority);
    }//GEN-LAST:event_btnresetActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtSearch.getText().trim().toLowerCase().equals(query.toLowerCase()))
                return;
            setQuery(txtSearch.getText().trim());
            getUserList(query, userPriority);
        }
    }//GEN-LAST:event_txtSearchKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDeleteAccount;
    private javax.swing.JButton btnEditAccount;
    private javax.swing.JButton btnreset;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JTable tblAccount;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
