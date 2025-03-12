/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class PhieuNhapDTO {
    private int maPhieuNhap;
    private Date thoiGianTao;
    private int maKho;
    private String nguoiTao;
    private String nguoiXacNhan ;
    private String nguoiNhanHang ;
    private int tongTien;
    private int  trangThai;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPhieuNhap, Date thoiGianTao, int maKho, String nguoiTao, String nguoiXacNhan, String nguoiNhanHang, int tongTien, int trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.thoiGianTao = thoiGianTao;
        this.maKho = maKho;
        this.nguoiTao = nguoiTao;
        this.nguoiXacNhan = nguoiXacNhan;
        this.nguoiNhanHang = nguoiNhanHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public PhieuNhapDTO(Date thoiGianTao, int maKho, String nguoiTao, int tongTien, int trangThai) {
        this.thoiGianTao = thoiGianTao;
        this.maKho = maKho;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public PhieuNhapDTO(int maPhieuNhap, int maKho, int trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.maKho = maKho;
        this.trangThai = trangThai;
    }
    
    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNguoiXacNhan() {
        return nguoiXacNhan;
    }

    public void setNguoiXacNhan(String nguoiXacNhan) {
        this.nguoiXacNhan = nguoiXacNhan;
    }

    public String getNguoiNhanHang() {
        return nguoiNhanHang;
    }

    public void setNguoiNhanHang(String nguoiNhanHang) {
        this.nguoiNhanHang = nguoiNhanHang;
    }
    
    
}
