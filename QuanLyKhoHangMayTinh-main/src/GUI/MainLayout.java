package GUI;

import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import DTO.NhomQuyenDTO;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainLayout extends javax.swing.JFrame {
    private Color DefaultColor, ClickedColor;
    private NguoiDungDTO user;
    private NhomQuyenDTO permissionInfo;
    private List<ChiTietQuyenDTO> permission;
    
    private MainLayout() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public MainLayout(NguoiDungDTO user, NhomQuyenDTO permissionInfo, List<ChiTietQuyenDTO> permission) throws UnsupportedLookAndFeelException {
        ImageIcon logo = new ImageIcon(getClass().getResource("/icon/logo.png"));
        setIconImage(logo.getImage());
        UIManager.setLookAndFeel(new FlatLightLaf());
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        initComponents();
        setLocationRelativeTo(null);
        DefaultColor = new Color(89, 168, 105);
        ClickedColor = new Color(26, 188, 156);
        this.user = user;
        this.permission = permission;
        this.permissionInfo = permissionInfo;
        javax.swing.JPanel[] panels = {Kho, NhaCungCap, PhieuNhap, PhieuXuat, SanPham, TaiKhoan, ThongKe, NhanVien, DonNghi, CaNhan, BangLuong, ChucVu};
        hideAllControllers(panels);
        authorize(panels, permission);
        lblFullName.setText(user.getHoTen());
        lblRole.setText(permissionInfo.getTenNhomQuyen());
        setVisible(true);
    }
    
    private void hideAllControllers(javax.swing.JPanel[] panels) {
    	for (javax.swing.JPanel p : panels) {
    		p.setVisible(false);
    	}
    }
    
    private void authorize(javax.swing.JPanel[] panels, List<ChiTietQuyenDTO> permission) {
        javax.swing.JPanel firstPanelFound = null;
        for (ChiTietQuyenDTO ctq: permission) {
            for (javax.swing.JPanel panel: panels) {
                if (panel.getName().equalsIgnoreCase(ctq.getMaChucNang())) {
                    panel.setVisible(true);
                    
                    if (firstPanelFound == null) {
                        firstPanelFound = panel;
                    }
                    break;
                }
            }
        }
        
        switch (firstPanelFound.getName()) {
            case "sanpham":
                SanPhamMouseClicked(null);
                SanPhamMousePressed(null);
                break;
            case "nhacungcap":
                NhaCungCapMouseClicked(null);
                NhaCungCapMousePressed(null);
                break;
            case "phieunhap":
                PhieuNhapMouseClicked(null);
                PhieuNhapMousePressed(null);
                break;
            case "phieuxuat":
                PhieuXuatMouseClicked(null);
                PhieuXuatMousePressed(null);
                break;
            case "kho":
                KhoMouseClicked(null);
                KhoMousePressed(null);
                break;
            case "taikhoan":
                TaiKhoanMouseClicked(null);
                TaiKhoanMousePressed(null);
                break;
            case "thongke":
                ThongKeMouseClicked(null);
                ThongKeMousePressed(null);
                break;
            case "nhanvien":
                NhanVienMouseClicked(null);
                NhanVienMousePressed(null);
                break;
            case "donnghi":
                DonNghiMouseClicked(null);
                DonNghiMousePressed(null);
                break;
            case "canhan":
                CaNhanMouseClicked(null);
                CaNhanMousePressed(null);
                break;
            case "bangluong":
                BangLuongMouseClicked(null);
                BangLuongMousePressed(null);
                break;
            case "chucvu":
                ChucVuMouseClicked(null);
                ChucVuMousePressed(null);
                break; 
            default:
                
        }
    }
    
    private void resetBackgroundAllPanels() {
        javax.swing.JPanel[] panels = {Kho, NhaCungCap, PhieuNhap, PhieuXuat, SanPham, TaiKhoan, ThongKe, NhanVien, DonNghi, CaNhan, BangLuong, ChucVu};
        for (javax.swing.JPanel panel : panels) {
            panel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
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

        pSidebar = new javax.swing.JPanel();
        pUserInfo = new javax.swing.JPanel();
        pFullName = new javax.swing.JPanel();
        lblFullName = new javax.swing.JLabel();
        pRole = new javax.swing.JPanel();
        lblRole = new javax.swing.JLabel();
        pNav = new javax.swing.JPanel();
        Kho = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        NhaCungCap = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PhieuNhap = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PhieuXuat = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        SanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TaiKhoan = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        ThongKe = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        NhanVien = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        DonNghi = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        BangLuong = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        ChucVu = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        pSettings = new javax.swing.JPanel();
        CaNhan = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        DangXuat = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pMainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý kho hàng máy tính");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pSidebar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pSidebar.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 1, 16, 1));
        pSidebar.setMinimumSize(new java.awt.Dimension(240, 690));
        pSidebar.setPreferredSize(new java.awt.Dimension(240, 730));
        pSidebar.setLayout(new java.awt.BorderLayout(0, 15));

        pUserInfo.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pUserInfo.setLayout(new java.awt.GridLayout(0, 1));

        pFullName.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pFullName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        pFullName.setLayout(new java.awt.BorderLayout());

        lblFullName.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblFullName.setText("[fullname]");
        lblFullName.setToolTipText("");
        pFullName.add(lblFullName, java.awt.BorderLayout.CENTER);

        pUserInfo.add(pFullName);

        pRole.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pRole.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        pRole.setLayout(new java.awt.BorderLayout());

        lblRole.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        lblRole.setForeground(new java.awt.Color(255, 255, 255));
        lblRole.setText("[role]");
        pRole.add(lblRole, java.awt.BorderLayout.PAGE_START);

        pUserInfo.add(pRole);

        pSidebar.add(pUserInfo, java.awt.BorderLayout.NORTH);

        pNav.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pNav.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        Kho.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        Kho.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        Kho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kho.setMinimumSize(new java.awt.Dimension(240, 36));
        Kho.setName("kho"); // NOI18N
        Kho.setPreferredSize(new java.awt.Dimension(240, 36));
        Kho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                KhoMousePressed(evt);
            }
        });
        Kho.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-warehouse-25.png"))); // NOI18N
        jLabel10.setText("Kho");
        Kho.add(jLabel10, java.awt.BorderLayout.CENTER);

        pNav.add(Kho);

        NhaCungCap.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        NhaCungCap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        NhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NhaCungCap.setMinimumSize(new java.awt.Dimension(240, 36));
        NhaCungCap.setName("nhacungcap"); // NOI18N
        NhaCungCap.setPreferredSize(new java.awt.Dimension(240, 36));
        NhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhaCungCapMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NhaCungCapMousePressed(evt);
            }
        });
        NhaCungCap.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_supplier_25px.png"))); // NOI18N
        jLabel6.setText("Nhà cung cấp");
        NhaCungCap.add(jLabel6, java.awt.BorderLayout.CENTER);

        pNav.add(NhaCungCap);

        PhieuNhap.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        PhieuNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        PhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PhieuNhap.setMinimumSize(new java.awt.Dimension(240, 36));
        PhieuNhap.setName("phieunhap"); // NOI18N
        PhieuNhap.setPreferredSize(new java.awt.Dimension(240, 36));
        PhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhieuNhapMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PhieuNhapMousePressed(evt);
            }
        });
        PhieuNhap.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_import_25px.png"))); // NOI18N
        jLabel2.setText("Phiếu nhập");
        PhieuNhap.add(jLabel2, java.awt.BorderLayout.CENTER);

        pNav.add(PhieuNhap);

        PhieuXuat.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        PhieuXuat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        PhieuXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PhieuXuat.setMinimumSize(new java.awt.Dimension(240, 36));
        PhieuXuat.setName("phieuxuat"); // NOI18N
        PhieuXuat.setPreferredSize(new java.awt.Dimension(240, 36));
        PhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhieuXuatMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PhieuXuatMousePressed(evt);
            }
        });
        PhieuXuat.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_database_daily_export_25px.png"))); // NOI18N
        jLabel9.setText("Phiếu xuất");
        PhieuXuat.add(jLabel9, java.awt.BorderLayout.CENTER);

        pNav.add(PhieuXuat);

        SanPham.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        SanPham.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        SanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SanPham.setMinimumSize(new java.awt.Dimension(240, 36));
        SanPham.setName("sanpham"); // NOI18N
        SanPham.setPreferredSize(new java.awt.Dimension(240, 36));
        SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SanPhamMousePressed(evt);
            }
        });
        SanPham.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_product_25px_2.png"))); // NOI18N
        jLabel1.setLabelFor(SanPham);
        jLabel1.setText("Sản phẩm");
        SanPham.add(jLabel1, java.awt.BorderLayout.LINE_START);

        pNav.add(SanPham);
        SanPham.getAccessibleContext().setAccessibleName("");

        TaiKhoan.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        TaiKhoan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        TaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TaiKhoan.setMinimumSize(new java.awt.Dimension(240, 36));
        TaiKhoan.setName("taikhoan"); // NOI18N
        TaiKhoan.setPreferredSize(new java.awt.Dimension(240, 36));
        TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TaiKhoanMousePressed(evt);
            }
        });
        TaiKhoan.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_test_account_25px.png"))); // NOI18N
        jLabel12.setText("Tài khoản");
        TaiKhoan.add(jLabel12, java.awt.BorderLayout.CENTER);

        pNav.add(TaiKhoan);

        ThongKe.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        ThongKe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        ThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ThongKe.setMinimumSize(new java.awt.Dimension(240, 36));
        ThongKe.setName("thongke"); // NOI18N
        ThongKe.setPreferredSize(new java.awt.Dimension(240, 36));
        ThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThongKeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ThongKeMousePressed(evt);
            }
        });
        ThongKe.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/statisticals.png"))); // NOI18N
        jLabel13.setText("Thống kê");
        ThongKe.add(jLabel13, java.awt.BorderLayout.CENTER);

        pNav.add(ThongKe);

        NhanVien.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        NhanVien.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        NhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NhanVien.setMinimumSize(new java.awt.Dimension(240, 36));
        NhanVien.setName("nhanvien"); // NOI18N
        NhanVien.setPreferredSize(new java.awt.Dimension(240, 36));
        NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NhanVienMousePressed(evt);
            }
        });
        NhanVien.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/employee.png"))); // NOI18N
        jLabel15.setText("Nhân viên");
        NhanVien.add(jLabel15, java.awt.BorderLayout.CENTER);

        pNav.add(NhanVien);

        DonNghi.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        DonNghi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        DonNghi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DonNghi.setMinimumSize(new java.awt.Dimension(240, 36));
        DonNghi.setName("donnghi"); // NOI18N
        DonNghi.setPreferredSize(new java.awt.Dimension(240, 36));
        DonNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DonNghiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DonNghiMousePressed(evt);
            }
        });
        DonNghi.setLayout(new java.awt.BorderLayout());

        jLabel16.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/contract.png"))); // NOI18N
        jLabel16.setText("Đơn nghỉ");
        DonNghi.add(jLabel16, java.awt.BorderLayout.CENTER);

        pNav.add(DonNghi);

        BangLuong.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        BangLuong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        BangLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BangLuong.setMinimumSize(new java.awt.Dimension(240, 36));
        BangLuong.setName("bangluong"); // NOI18N
        BangLuong.setPreferredSize(new java.awt.Dimension(240, 36));
        BangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangLuongMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BangLuongMousePressed(evt);
            }
        });
        BangLuong.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salary.png"))); // NOI18N
        jLabel17.setText("Bảng lương");
        BangLuong.add(jLabel17, java.awt.BorderLayout.CENTER);

        pNav.add(BangLuong);

        ChucVu.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        ChucVu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        ChucVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChucVu.setMinimumSize(new java.awt.Dimension(240, 36));
        ChucVu.setName("chucvu"); // NOI18N
        ChucVu.setPreferredSize(new java.awt.Dimension(240, 36));
        ChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChucVuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ChucVuMousePressed(evt);
            }
        });
        ChucVu.setLayout(new java.awt.BorderLayout());

        jLabel18.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/project-manager.png"))); // NOI18N
        jLabel18.setText("Chức vụ");
        ChucVu.add(jLabel18, java.awt.BorderLayout.CENTER);

        pNav.add(ChucVu);

        pSidebar.add(pNav, java.awt.BorderLayout.CENTER);

        pSettings.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        pSettings.setLayout(new java.awt.GridLayout(0, 1));

        CaNhan.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        CaNhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        CaNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CaNhan.setMinimumSize(new java.awt.Dimension(240, 36));
        CaNhan.setName("canhan"); // NOI18N
        CaNhan.setPreferredSize(new java.awt.Dimension(240, 36));
        CaNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CaNhanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CaNhanMousePressed(evt);
            }
        });
        CaNhan.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-information-25.png"))); // NOI18N
        jLabel14.setText("Cá nhân");
        CaNhan.add(jLabel14, java.awt.BorderLayout.CENTER);

        pSettings.add(CaNhan);

        DangXuat.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        DangXuat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 24, 1, 1));
        DangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DangXuat.setMinimumSize(new java.awt.Dimension(240, 36));
        DangXuat.setPreferredSize(new java.awt.Dimension(240, 36));
        DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DangXuatMousePressed(evt);
            }
        });
        DangXuat.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_shutdown_25px.png"))); // NOI18N
        jLabel5.setText("Đăng xuất");
        DangXuat.add(jLabel5, java.awt.BorderLayout.CENTER);

        pSettings.add(DangXuat);

        pSidebar.add(pSettings, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pSidebar, java.awt.BorderLayout.WEST);

        pMainContent.setBackground(new java.awt.Color(255, 255, 255));
        pMainContent.setPreferredSize(new java.awt.Dimension(1180, 750));

        javax.swing.GroupLayout pMainContentLayout = new javax.swing.GroupLayout(pMainContent);
        pMainContent.setLayout(pMainContentLayout);
        pMainContentLayout.setHorizontalGroup(
            pMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1177, Short.MAX_VALUE)
        );
        pMainContentLayout.setVerticalGroup(
            pMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 862, Short.MAX_VALUE)
        );

        getContentPane().add(pMainContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        SanPham.setBackground(ClickedColor);
    }//GEN-LAST:event_SanPhamMousePressed

    private void NhaCungCapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhaCungCapMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        NhaCungCap.setBackground(ClickedColor);
    }//GEN-LAST:event_NhaCungCapMousePressed

    private void PhieuNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhieuNhapMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        PhieuNhap.setBackground(ClickedColor);
    }//GEN-LAST:event_PhieuNhapMousePressed

    private void PhieuXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhieuXuatMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        PhieuXuat.setBackground(ClickedColor);
    }//GEN-LAST:event_PhieuXuatMousePressed

    private void SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SanPhamMouseClicked
        // TODO add your handling code here:
        ProductForm productForm = new ProductForm(user);
        pMainContent.removeAll();
        pMainContent.add(productForm).setVisible(true);
    }//GEN-LAST:event_SanPhamMouseClicked

    private void KhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhoMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        Kho.setBackground(ClickedColor);
    }//GEN-LAST:event_KhoMousePressed

    private void DangXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatMousePressed
        // TODO add your handling code here:
        DangXuat.setBackground(ClickedColor);
    }//GEN-LAST:event_DangXuatMousePressed

    private void NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhaCungCapMouseClicked
        // TODO add your handling code here:
        NhaCungCapForm nhaCungCapForm = new NhaCungCapForm(user);
        pMainContent.removeAll();
        pMainContent.add(nhaCungCapForm).setVisible(true);
    }//GEN-LAST:event_NhaCungCapMouseClicked

    private void KhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhoMouseClicked
        // TODO add your handling code here:
        khoGUI khoForm = new khoGUI(user);
        pMainContent.removeAll();
        pMainContent.add(khoForm).setVisible(true);
    }//GEN-LAST:event_KhoMouseClicked

    private void PhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhieuNhapMouseClicked
        // TODO add your handling code here:
        PhieuNhapForm pnForm = new PhieuNhapForm(user);
        pMainContent.removeAll();
        pMainContent.add(pnForm).setVisible(true);
    }//GEN-LAST:event_PhieuNhapMouseClicked

    private void PhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhieuXuatMouseClicked
        // TODO add your handling code here:
        PhieuXuatGUI px = new PhieuXuatGUI(user);
        pMainContent.removeAll();
        pMainContent.add(px).setVisible(true);
    }//GEN-LAST:event_PhieuXuatMouseClicked

    private void DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatMouseClicked
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn đăng xuất không?",
                "Đăng xuất",
                JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            setUser(null);
            setPermissionInfo(null);
            setPermission(null);
            this.dispose();
            Login a = new Login();
            a.setVisible(true);
        } else {
            DangXuat.setBackground(DefaultColor);
        }
    }//GEN-LAST:event_DangXuatMouseClicked

    private void TaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanMouseClicked
        // TODO add your handling code here:
        TaiKhoanGUI tkGUI = new TaiKhoanGUI(user);
        pMainContent.removeAll();
        pMainContent.add(tkGUI).setVisible(true);
    }//GEN-LAST:event_TaiKhoanMouseClicked

    private void TaiKhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        TaiKhoan.setBackground(ClickedColor);
    }//GEN-LAST:event_TaiKhoanMousePressed

    private void ThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeMouseClicked
        // TODO add your handling code here:
//        ThongKeForm thongKeForm = new ThongKeForm();
        ThongKeGUI thongKe = new ThongKeGUI(user);
        pMainContent.removeAll();
        pMainContent.add(thongKe).setVisible(true);
    }//GEN-LAST:event_ThongKeMouseClicked

    private void ThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongKeMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        ThongKe.setBackground(ClickedColor);
    }//GEN-LAST:event_ThongKeMousePressed

    private void CaNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaNhanMouseClicked
        // TODO add your handling code here:
        /*ChangePassword cp = new ChangePassword(this, rootPaneCheckingEnabled, user);
        cp.setVisible(true);*/
        CaNhanForm cn = null;
        try {
            cn = new CaNhanForm(user);
        } catch (SQLException ex) {
            Logger.getLogger(MainLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        pMainContent.removeAll();
        pMainContent.add(cn).setVisible(true);
    }//GEN-LAST:event_CaNhanMouseClicked

    private void CaNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CaNhanMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        CaNhan.setBackground(ClickedColor);
    }//GEN-LAST:event_CaNhanMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Thoát", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienMouseClicked
        NhanVienForm nhanVienForm = new NhanVienForm(user);
        pMainContent.removeAll();
        pMainContent.add(nhanVienForm).setVisible(true);
    }//GEN-LAST:event_NhanVienMouseClicked

    private void NhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhanVienMousePressed
        resetBackgroundAllPanels();
        NhanVien.setBackground(ClickedColor);
    }//GEN-LAST:event_NhanVienMousePressed

    private void DonNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DonNghiMouseClicked
        // TODO add your handling code here:
        DonNghiForm donNghiForm = new DonNghiForm(user);
        pMainContent.removeAll();
        pMainContent.add(donNghiForm).setVisible(true);
    }//GEN-LAST:event_DonNghiMouseClicked

    private void DonNghiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DonNghiMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        DonNghi.setBackground(ClickedColor);
    }//GEN-LAST:event_DonNghiMousePressed

    private void BangLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangLuongMouseClicked
        // TODO add your handling code here:
        BangLuongForm bangLuongForm = new BangLuongForm(user);
        pMainContent.removeAll();
        pMainContent.add(bangLuongForm).setVisible(true);
    }//GEN-LAST:event_BangLuongMouseClicked

    private void BangLuongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangLuongMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        BangLuong.setBackground(ClickedColor);
    }//GEN-LAST:event_BangLuongMousePressed

    private void ChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChucVuMouseClicked
        // TODO add your handling code here:
        ChucVuForm cvForm = new ChucVuForm(user);
        pMainContent.removeAll();
        pMainContent.add(cvForm).setVisible(true);
    }//GEN-LAST:event_ChucVuMouseClicked

    private void ChucVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChucVuMousePressed
        // TODO add your handling code here:
        resetBackgroundAllPanels();
        ChucVu.setBackground(ClickedColor);
    }//GEN-LAST:event_ChucVuMousePressed

    public NguoiDungDTO getUser() {
        return user;
    }

    public void setUser(NguoiDungDTO user) {
        this.user = user;
    }

    public NhomQuyenDTO getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(NhomQuyenDTO permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public List<ChiTietQuyenDTO> getPermission() {
        return permission;
    }

    public void setPermission(List<ChiTietQuyenDTO> permission) {
        this.permission = permission;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainLayout().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BangLuong;
    private javax.swing.JPanel CaNhan;
    private javax.swing.JPanel ChucVu;
    private javax.swing.JPanel DangXuat;
    private javax.swing.JPanel DonNghi;
    private javax.swing.JPanel Kho;
    private javax.swing.JPanel NhaCungCap;
    private javax.swing.JPanel NhanVien;
    private javax.swing.JPanel PhieuNhap;
    private javax.swing.JPanel PhieuXuat;
    private javax.swing.JPanel SanPham;
    private javax.swing.JPanel TaiKhoan;
    private javax.swing.JPanel ThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblRole;
    private javax.swing.JPanel pFullName;
    private javax.swing.JPanel pMainContent;
    private javax.swing.JPanel pNav;
    private javax.swing.JPanel pRole;
    private javax.swing.JPanel pSettings;
    private javax.swing.JPanel pSidebar;
    private javax.swing.JPanel pUserInfo;
    // End of variables declaration//GEN-END:variables
}
