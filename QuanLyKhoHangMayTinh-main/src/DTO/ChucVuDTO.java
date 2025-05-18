/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author downny
 */
public class ChucVuDTO {
    private int maChucVu;
    private String tenChucVu;
    private int luongCoBan;
    private int trangThai;

    public ChucVuDTO(int maChucVu, String tenChucVu, int luongCoBan, int trangThai) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.luongCoBan = luongCoBan;
        this.trangThai = trangThai;
    }

    public ChucVuDTO(int maChucVu, String tenChucVu, int luongCoBan) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.luongCoBan = luongCoBan;
    }
    
    
    public ChucVuDTO() {
    }

    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public int getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(int luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
