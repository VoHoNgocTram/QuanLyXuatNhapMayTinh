package DTO;

import java.time.LocalDateTime;

public class ChiTietTonKhoDTO {
    private int maKho;
    private String tenKho;
    private int maPhieuNhap;
    private LocalDateTime thoiGianTao;
    private int maSanPham;
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private int soLuongTonKho;
    private Long donGia;
    
    public ChiTietTonKhoDTO() {
    }

    public ChiTietTonKhoDTO(int maKho, int maPhieuNhap, LocalDateTime thoiGianTao, int maSanPham, int maNhaCungCap, String tenNhaCungCap, int soLuongTonKho, Long donGia) {
        this.maKho = maKho;
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGianTao = thoiGianTao;
        this.maSanPham = maSanPham;
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soLuongTonKho = soLuongTonKho;
        this.donGia = donGia;
    }

    public ChiTietTonKhoDTO(int maKho, String tenKho, int maPhieuNhap, LocalDateTime thoiGianTao, int maSanPham, int maNhaCungCap, String tenNhaCungCap, int soLuongTonKho, Long donGia) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGianTao = thoiGianTao;
        this.maSanPham = maSanPham;
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soLuongTonKho = soLuongTonKho;
        this.donGia = donGia;
    }
    
    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public LocalDateTime getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(LocalDateTime thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public Long getDonGia() {
        return donGia;
    }

    public void setDonGia(Long donGia) {
        this.donGia = donGia;
    }
}
