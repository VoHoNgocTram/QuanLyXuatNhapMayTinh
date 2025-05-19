/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class lsChucVuDTO {
    private int mals;
    private String taikhoan;
    private String hoten;
    private int machucvu;
    private String tenchucvu;
    private Date ngaythaydoi;
    private int luongcoban;
    private int trangthai;

    public lsChucVuDTO(int mals, String taikhoan, String hoten, int machucvu, String tenchucvu, Date ngaythaydoi, int luongcoban, int trangthai) {
        this.mals = mals;
        this.taikhoan = taikhoan;
        this.hoten = hoten;
        this.machucvu = machucvu;
        this.tenchucvu = tenchucvu;
        this.ngaythaydoi = ngaythaydoi;
        this.luongcoban = luongcoban;
        this.trangthai = trangthai;
    }

    public int getMals() {
        return mals;
    }

    public void setMals(int mals) {
        this.mals = mals;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }

    public Date getNgaythaydoi() {
        return ngaythaydoi;
    }

    public void setNgaythaydoi(Date ngaythaydoi) {
        this.ngaythaydoi = ngaythaydoi;
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(int luongcoban) {
        this.luongcoban = luongcoban;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
