/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author DELL
 */
public class ChiTietPhieuNhapDTO {
    private int maPhieuNhap;
    private int maNhaCungCap;
    private int maSanPham;
    private int soLuongNhap;
    private int donGia;
    private String nguoiThem;
    private int soLuongThucTe;
    private int soLuongTonKho;
    private int maKho;
    private String tenSanPham;
    private int trangThai ;
    
    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maPhieuNhap, int maNhaCungCap, int maSanPham, int soLuongNhap, int donGia,String nguoiThem ,int soLuongThucTe, int soLuongTonKho, int trangThai ) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maSanPham = maSanPham;
        this.soLuongNhap = soLuongNhap;
        this.donGia = donGia;
        this.nguoiThem = nguoiThem ;
        this.soLuongThucTe = soLuongThucTe;
        this.soLuongTonKho = soLuongTonKho;
        this.trangThai = trangThai;
    }

    public ChiTietPhieuNhapDTO(int maNhaCungCap, int maSanPham,  int soLuongNhap, int donGia, String nguoiThem, int soLuongThucTe, int soLuongTonKho, int trangThai) {
        this.maNhaCungCap = maNhaCungCap;
        this.maSanPham = maSanPham;
        this.soLuongNhap = soLuongNhap;
        this.donGia = donGia;
        this.nguoiThem = nguoiThem;
        this.soLuongThucTe = soLuongThucTe;
        this.soLuongTonKho = soLuongTonKho;
        this.trangThai = trangThai;
    }

    
    public ChiTietPhieuNhapDTO(int maSanPham, int soLuongNhap) {
        this.maSanPham = maSanPham;
        this.soLuongNhap = soLuongNhap;
    }

    public ChiTietPhieuNhapDTO(int maSanPham, int soLuongNhap, int maKho, String tenSanPham, int donGia, int maNhaCungCap, int soLuongTonKho) {
        this.maSanPham = maSanPham;
        this.soLuongNhap = soLuongNhap;
        this.maKho = maKho;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.maNhaCungCap = maNhaCungCap;
        this.soLuongTonKho = soLuongTonKho;
    }

    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public String getNguoiThem() {
        return nguoiThem;
    }

    public void setNguoiThem(String nguoiThem) {
        this.nguoiThem = nguoiThem;
    }

    public int getSoLuongThucTe() {
        return soLuongThucTe;
    }

    public void setSoLuongThucTe(int soLuongThucTe) {
        this.soLuongThucTe = soLuongThucTe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
