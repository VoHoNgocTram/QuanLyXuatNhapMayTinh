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
public class CaNhanDTO {
    private String taiKhoan;
    private String hoTen;
    private String email;
    private Date ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String sdt;
    private String tenChucvu;
    private Date ngayVaoLam;
    private int soNgayPhep;
    private int luongCoBan;
    private int trangThai;

    public CaNhanDTO(String taiKhoan, String hoTen, String email, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String tenChucvu, Date ngayVaoLam, int soNgayPhep, int luongCoBan, int trangThai) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tenChucvu = tenChucvu;
        this.ngayVaoLam = ngayVaoLam;
        this.soNgayPhep = soNgayPhep;
        this.luongCoBan = luongCoBan;
        this.trangThai = trangThai;
    }
    
    
}


