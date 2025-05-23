/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;


import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import com.formdev.flatlaf.FlatLightLaf;
import DAO.NhaCungCapDAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Tran Nhat Sinh
 */
public class UpdateNhaCungCap extends javax.swing.JDialog {

    /**
     * Creates new form AddAccount
     */
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private NhaCungCapForm parent;
    public UpdateNhaCungCap(javax.swing.JInternalFrame parent,javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        this.parent = (NhaCungCapForm) parent;
        initComponents();
        setLocationRelativeTo(null);
        displayInfo();
    }

    UpdateNhaCungCap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private UpdateNhaCungCap(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void displayInfo() {
        NhaCungCapDTO a = parent.getNhaCungCapSelect();
        txtMaNCC.setText(String.valueOf(a.getMaNhaCungCap()));
        txtDiaChi.setText(a.getDiaChi());
        txtTenNCC.setText(a.getTenNhaCungCap());
        txtSDT.setText(a.getSdt());
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
        lbMaNCC = new javax.swing.JLabel();
        txtMaNCC = new javax.swing.JTextField();
        lbTenNCC = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        lbSDT = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lbDiaChi = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cập nhật thông tin");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMaNCC.setText("Mã nhà cung cấp");
        jPanel1.add(lbMaNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, -1));

        txtMaNCC.setEnabled(false);
        jPanel1.add(txtMaNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 298, 38));

        lbTenNCC.setText("Tên nhà cung cấp");
        jPanel1.add(lbTenNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 24));
        jPanel1.add(txtTenNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 298, 38));

        lbSDT.setText("Số điện thoại");
        jPanel1.add(lbSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 100, -1));
        jPanel1.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 298, 38));

        lbDiaChi.setText("Địa chỉ");
        jPanel1.add(lbDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 50, -1));

        btnLuu.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 140, 38));

        btnHuy.setBackground(new java.awt.Color(255, 0, 51));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.setBorder(null);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 140, 38));

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CẬP NHẬT THÔNG TIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 70));
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 298, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try {
            NhaCungCapDTO ncc = new NhaCungCapDTO();
            ncc.setMaNhaCungCap(Integer.parseInt(txtMaNCC.getText()));
            ncc.setTenNhaCungCap(txtTenNCC.getText());
            ncc.setSdt(txtSDT.getText());
            ncc.setDiaChi(txtDiaChi.getText());
            String tenNcc = txtTenNCC.getText().trim();
            String sdtNcc = txtSDT.getText().trim();
            String diachiNcc = txtDiaChi.getText().trim();
            if (tenNcc.equals("") || sdtNcc.equals("") || diachiNcc.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            else if(!sdtNcc.matches("\\d{10}") ){
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            else if(NhaCungCapDAO.getInstance().hasSDTException(ncc)){
                JOptionPane.showMessageDialog(this, "Số điện thoại bị trùng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            else if(NhaCungCapDAO.getInstance().hasDiaChiException(ncc)){
                JOptionPane.showMessageDialog(this, "Địa chỉ bị trùng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            else if(NhaCungCapDAO.getInstance().hasNameException(ncc)){
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp bị trùng!",  "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            else{  
                JOptionPane.showMessageDialog(this, nccBUS.updateNhaCungCap(ncc));
                parent.loadDataToTable();
                this.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateNhaCungCap dialog = new UpdateNhaCungCap(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDiaChi;
    private javax.swing.JLabel lbMaNCC;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTenNCC;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
