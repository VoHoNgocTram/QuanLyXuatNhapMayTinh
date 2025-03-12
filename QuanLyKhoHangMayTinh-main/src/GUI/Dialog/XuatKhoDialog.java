package GUI.Dialog;

import BUS.TonKhoBUS;
import DTO.ChiTietQuyenDTO;
import DTO.ChiTietTonKhoDTO;
import DTO.SanPhamTonKhoDTO;
import static GUI.Dialog.PriceDetailDialog.getOwnerFrame;
import helper.CustomTableCellRenderer;
import helper.Exception.EmptyFieldException;
import helper.TonKhoSanPhamTableModel;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class XuatKhoDialog extends javax.swing.JDialog {
    private final TonKhoBUS tkBUS = new TonKhoBUS();
    private DefaultTableModel dtmSanPham;
    private TonKhoSanPhamTableModel tmTonKho;
    private List<ChiTietQuyenDTO> allowedActions;
    private boolean restrictGiaNhap;
    private String queryProductName = "";
    private SanPhamTonKhoDTO selectedProduct;
    private ChiTietTonKhoDTO selectedDetail;
    private ArrayList<SanPhamTonKhoDTO> arrSanPham;
    private ArrayList<ChiTietTonKhoDTO> arrTonKho;
    
    public XuatKhoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public XuatKhoDialog(JDialog parent, boolean modal, List<ChiTietQuyenDTO> allowedActions) {
        super(getOwnerFrame(parent), modal);
        initComponents();
        setLocationRelativeTo(null);
        this.allowedActions = allowedActions;
        checkRestriction();
        initTable();
        getInventoryProduct(queryProductName);
    }
    
    private void checkRestriction() {
        for (ChiTietQuyenDTO ctq : allowedActions) {
            if (ctq.findRestriction(ctq.getHanChe(), "gianhap")) {
                setRestrictGiaNhap(true);
                // break;
                return;
            }
        }
        setRestrictGiaNhap(false);
    }
    
    private void initTable() {
        dtmSanPham = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn kho", "Số lượng đã xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        tableProduct.setModel(dtmSanPham);
        tableProduct.getColumnModel().getColumn(1).setPreferredWidth(400);

        String[] columnNames = {"Mã kho", "Tên kho", "Mã phiếu nhập", "Thời gian tạo", "Mã nhà cung cấp", "Nhà cung cấp", "Đơn giá", "Số lượng tồn kho"};
        tmTonKho = new TonKhoSanPhamTableModel(new ArrayList<>(), columnNames);
        tableTonKho.setModel(tmTonKho);
        int[] columnsToBeHidden = {0, 4};
        for (int c : columnsToBeHidden) {
            tableTonKho.getColumnModel().getColumn(c).setMinWidth(0);
            tableTonKho.getColumnModel().getColumn(c).setMaxWidth(0);
            tableTonKho.getColumnModel().getColumn(c).setWidth(0);
            tableTonKho.getColumnModel().getColumn(c).setPreferredWidth(0);
        }
        tableTonKho.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableTonKho.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableTonKho.getColumnModel().getColumn(5).setPreferredWidth(400);
    }

    public String getQueryProductName() {
        return queryProductName;
    }

    public void setQueryProductName(String queryProductName) {
        this.queryProductName = queryProductName;
    }

    public SanPhamTonKhoDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(SanPhamTonKhoDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public boolean isRestrictGiaNhap() {
        return restrictGiaNhap;
    }

    public void setRestrictGiaNhap(boolean restrictGiaNhap) {
        this.restrictGiaNhap = restrictGiaNhap;
    }

    public ChiTietTonKhoDTO getSelectedDetail() {
        return selectedDetail;
    }

    public void setSelectedDetail(ChiTietTonKhoDTO selectedDetail) {
        this.selectedDetail = selectedDetail;
    }
    
    private void getInventoryProduct(String query) {
        ArrayList<SanPhamTonKhoDTO> arr = new ArrayList<>();
        try {
            arr = tkBUS.getInventoryProduct(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        this.arrSanPham = arr;
        
        dtmSanPham.setRowCount(0);
        for (int i = 0; i < arr.size(); ++i) {
            SanPhamTonKhoDTO sptk = arr.get(i);
            int maSanPham = sptk.getMaSanPham();
            String tenSanPham = sptk.getTenSanPham();
            int soLuongTonKho = sptk.getSoLuongTonKho();
            int soLuongDaXuat = sptk.getSoLuongDaXuat();
            Object [] row = {maSanPham, tenSanPham, soLuongTonKho, soLuongDaXuat};
            dtmSanPham.addRow(row);
        }
        
        for (int i = 0; i < tableProduct.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                case 2:
                case 3:
                    tableProduct.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
                    break;
                case 1:
                    tableProduct.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.LEFT);
                    break;
                default:
            }
        }
    }
    
    private ArrayList<ChiTietTonKhoDTO> getInventoryProductDetail(int productId, boolean restrictGiaNhap) {
        ArrayList<ChiTietTonKhoDTO> arr = new ArrayList<>();
        try {
            arr = tkBUS.getInventoryProductDetail(productId, restrictGiaNhap);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return arr;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return arr;
        }
        return arr;
    }
    
    private void showDataToTableTonKho(ArrayList<ChiTietTonKhoDTO> arr) {
        tmTonKho.setData(arr);
        tmTonKho.fireTableDataChanged();
        
        for (int i = 0; i < tableTonKho.getColumnCount(); ++i) {
            switch (i) {
                case 0:
                case 2:
                case 4:
                case 6:
                case 7:
                    tableTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.RIGHT);
                    break;
                case 1:
                case 5:
                    tableTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.LEFT);
                    break;
                case 3:
                    tableTonKho.getColumnModel().getColumn(i).setCellRenderer(CustomTableCellRenderer.CENTER);
                    break;
                default:
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

        pContainer = new javax.swing.JPanel();
        pChooseProduct = new javax.swing.JPanel();
        scrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        pFilterSanPham = new javax.swing.JPanel();
        lblInstruction = new javax.swing.JLabel();
        lblSearchProduct = new javax.swing.JLabel();
        inputProductName = new javax.swing.JTextField();
        btnReloadProduct = new javax.swing.JButton();
        pDetail = new javax.swing.JPanel();
        scrollPane2 = new javax.swing.JScrollPane();
        tableTonKho = new javax.swing.JTable();
        btnXuatKho = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblProductName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Xuất kho");
        setResizable(false);

        pContainer.setBackground(new java.awt.Color(255, 255, 255));
        pContainer.setLayout(new java.awt.CardLayout());

        pChooseProduct.setBackground(new java.awt.Color(255, 255, 255));
        pChooseProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn kho", "Số lượng đã xuất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.getTableHeader().setReorderingAllowed(false);
        scrollPane1.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        pChooseProduct.add(scrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 820, 590));

        btnNext.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNext.setText("Tiếp");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pChooseProduct.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 610, -1, -1));

        pFilterSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pFilterSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pFilterSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInstruction.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblInstruction.setText("Chọn sản phẩm cần xuất kho");
        pFilterSanPham.add(lblInstruction, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblSearchProduct.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSearchProduct.setText("Tìm kiếm sản phẩm");
        pFilterSanPham.add(lblSearchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        inputProductName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        inputProductName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputProductNameKeyPressed(evt);
            }
        });
        pFilterSanPham.add(inputProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 280, 40));

        btnReloadProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReloadProduct.setText("Làm mới");
        btnReloadProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadProductActionPerformed(evt);
            }
        });
        pFilterSanPham.add(btnReloadProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 90, -1));

        pChooseProduct.add(pFilterSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 590));

        pContainer.add(pChooseProduct, "card2");

        pDetail.setBackground(new java.awt.Color(255, 255, 255));

        tableTonKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kho", "Tên kho", "Mã phiếu nhập", "Thời gian tạo", "Mã nhà cung cấp", "Nhà cung cấp", "Đơn giá", "Số lượng tồn kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane2.setViewportView(tableTonKho);
        if (tableTonKho.getColumnModel().getColumnCount() > 0) {
            tableTonKho.getColumnModel().getColumn(5).setPreferredWidth(400);
        }

        btnXuatKho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXuatKho.setText("Xác nhận");
        btnXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatKhoActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblProductName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout pDetailLayout = new javax.swing.GroupLayout(pDetail);
        pDetail.setLayout(pDetailLayout);
        pDetailLayout.setHorizontalGroup(
            pDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDetailLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatKho))
                    .addGroup(pDetailLayout.createSequentialGroup()
                        .addComponent(lblProductName)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pDetailLayout.setVerticalGroup(
            pDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProductName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatKho)
                    .addComponent(btnBack))
                .addContainerGap())
        );

        pContainer.add(pDetail, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputProductNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputProductNameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (inputProductName.getText().trim().toLowerCase().equals(queryProductName.toLowerCase()))
                return;
            setQueryProductName(inputProductName.getText().trim());
            getInventoryProduct(queryProductName);
        }
    }//GEN-LAST:event_inputProductNameKeyPressed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (tableProduct.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Vui lòng chọn sản phẩm");
            return;
        }
        setSelectedProduct(arrSanPham.get(tableProduct.getSelectedRow()));
        lblProductName.setText(selectedProduct.getTenSanPham());
        arrTonKho = getInventoryProductDetail(selectedProduct.getMaSanPham(), restrictGiaNhap);
        showDataToTableTonKho(arrTonKho);
        
        CardLayout layout = (CardLayout) pContainer.getLayout();
        layout.next(pContainer);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnReloadProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadProductActionPerformed
        // TODO add your handling code here:
        inputProductName.setText("");
        setQueryProductName("");
        getInventoryProduct(queryProductName);
    }//GEN-LAST:event_btnReloadProductActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        getInventoryProduct(queryProductName);
        CardLayout layout = (CardLayout) pContainer.getLayout();
        layout.previous(pContainer);
        setSelectedProduct(null);
        lblProductName.setText("");
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatKhoActionPerformed
        // TODO add your handling code here:
        if (tableTonKho.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Vui lòng chọn phiếu nhập cần xuất kho");
            return;
        }
        setSelectedDetail(arrTonKho.get(tableTonKho.getSelectedRow()));
        String input = JOptionPane.showInputDialog(XuatKhoDialog.this, "Nhập số lượng:");
        int result;
        try {
            result = tkBUS.handleXuatKho(selectedDetail, input);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(XuatKhoDialog.this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        
        switch (result) {
            case 0:
                JOptionPane.showMessageDialog(XuatKhoDialog.this, "Có lỗi xảy ra trong quá trình xuất kho, vui lòng thử lại.");
                break;
            case 1:
                arrTonKho = getInventoryProductDetail(selectedProduct.getMaSanPham(), restrictGiaNhap);
                if (arrTonKho.isEmpty()) {
                    btnBackActionPerformed(null);
                } else {
                    showDataToTableTonKho(arrTonKho);
                }
                JOptionPane.showMessageDialog(XuatKhoDialog.this, "Xuất kho thành công");
        }
    }//GEN-LAST:event_btnXuatKhoActionPerformed

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
            java.util.logging.Logger.getLogger(XuatKhoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatKhoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatKhoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatKhoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                XuatKhoDialog dialog = new XuatKhoDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnReloadProduct;
    private javax.swing.JButton btnXuatKho;
    private javax.swing.JTextField inputProductName;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblSearchProduct;
    private javax.swing.JPanel pChooseProduct;
    private javax.swing.JPanel pContainer;
    private javax.swing.JPanel pDetail;
    private javax.swing.JPanel pFilterSanPham;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTable tableTonKho;
    // End of variables declaration//GEN-END:variables
}
