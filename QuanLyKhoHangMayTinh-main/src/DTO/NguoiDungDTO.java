package DTO;

import java.util.Date;

public class NguoiDungDTO {
    private String taiKhoan;
    private String matKhau;
    private String hoTen;
    private String email;
    private int maNhomQuyen;
    private int trangThai;
    private String tenNhomQuyen;
    private int doUuTien;
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String tenChucvu;
    private int maChucVu;
    private Date ngayVaoLam;
    private int soNgayPhep;
    private int luongCoBan;
    
    public NguoiDungDTO(String taiKhoan, String matKhau, String hoTen, String email, int maNhomQuyen, int trangThai) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.maNhomQuyen = maNhomQuyen;
        this.trangThai = trangThai;
    }
    
    public NguoiDungDTO(String taiKhoan, String hoTen, String email, int maNhomQuyen, String tenNhomQuyen, int doUuTien) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.email = email;
        this.maNhomQuyen = maNhomQuyen;
        this.tenNhomQuyen = tenNhomQuyen;
        this.doUuTien = doUuTien;
    }

    public NguoiDungDTO(String taiKhoan, String hoTen, String email, int doUuTien, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String tenChucvu, Date ngayVaoLam, int soNgayPhep, int luongCoBan) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.email = email;
        this.doUuTien = doUuTien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tenChucvu = tenChucvu;
        this.ngayVaoLam = ngayVaoLam;
        this.soNgayPhep = soNgayPhep;
        this.luongCoBan = luongCoBan;
    }

    public NguoiDungDTO(String taiKhoan, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String email, int maChucVu, Date ngayVaoLam, int soNgayPhep, int luongCoBan, int trangThai) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.maChucVu = maChucVu;
        this.ngayVaoLam = ngayVaoLam;
        this.soNgayPhep = soNgayPhep;
        this.luongCoBan = luongCoBan;
        this.trangThai = trangThai;
    }

    public NguoiDungDTO(String taiKhoan, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String email, int maChucVu, Date ngayVaoLam, int soNgayPhep, int luongCoBan, int trangThai, String tenchucvu, int manhomquyen) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.maChucVu = maChucVu;
        this.ngayVaoLam = ngayVaoLam;
        this.soNgayPhep = soNgayPhep;
        this.luongCoBan = luongCoBan;
        this.trangThai = trangThai;
        this.tenChucvu = tenchucvu;
        this.maNhomQuyen= manhomquyen;
    }
    
    public NguoiDungDTO() {
    }

    public NguoiDungDTO(String taiKhoan, String hoTen, String email, java.sql.Date ngaySinh, String gioiTinh, String diaChi, String sdt, String tenChucVu, java.sql.Date ngayVaoLam, int soNgayPhep, int luongCoBan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(int maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenNhomQuyen() {
        return tenNhomQuyen;
    }

    public void setTenNhomQuyen(String tenNhomQuyen) {
        this.tenNhomQuyen = tenNhomQuyen;
    }

    public int getDoUuTien() {
        return doUuTien;
    }

    public void setDoUuTien(int doUuTien) {
        this.doUuTien = doUuTien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenChucvu() {
        return tenChucvu;
    }

    public void setTenChucvu(String tenChucvu) {
        this.tenChucvu = tenChucvu;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public int getSoNgayPhep() {
        return soNgayPhep;
    }

    public void setSoNgayPhep(int soNgayPhep) {
        this.soNgayPhep = soNgayPhep;
    }

    public int getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(int luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }
    
    
}