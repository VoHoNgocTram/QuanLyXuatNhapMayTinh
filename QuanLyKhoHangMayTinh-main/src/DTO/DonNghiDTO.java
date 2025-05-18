/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author downny
 */
public class DonNghiDTO {
    private int maDonNghi;
    private String taiKhoan;
    private String hoTen;
    private String loaiNghi;
    private Date ngayNopDon;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String ghiChu;
    private int trangThai;

    public DonNghiDTO(int maDonNghi, String hoTen, String loaiNghi, Date ngayNopDon, Date ngayBatDau, Date ngayKetThuc, String ghiChu, int trangThai) {
        this.maDonNghi = maDonNghi;
        this.hoTen = hoTen;
        this.loaiNghi = loaiNghi;
        this.ngayNopDon = ngayNopDon;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public DonNghiDTO() {
    }

    public int getMaDonNghi() {
        return maDonNghi;
    }

    public void setMaDonNghi(int maDonNghi) {
        this.maDonNghi = maDonNghi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLoaiNghi() {
        return loaiNghi;
    }

    public void setLoaiNghi(String loaiNghi) {
        this.loaiNghi = loaiNghi;
    }

    public Date getNgayNopDon() {
        return ngayNopDon;
    }

    public void setNgayNopDon(Date ngayNopDon) {
        this.ngayNopDon = ngayNopDon;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
