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
public class BangLuongDTO {
     private int mabl;
    private String taikhoan;
    private String hoten;
    private int thang;
    private int nam;
    private int luongcoban;
    private int thuong;
    private int truluong;
    private int tongluong;
    private Date ngaytinhluong;
    private int songaycong;
    private int trangthai;

    public BangLuongDTO() {
    }

    public BangLuongDTO(int mabl, String taikhoan, String hoten, int thang, int nam, int luongcoban, int thuong,
                        int truluong, int tongluong, Date ngaytinhluong, int songaycong, int trangthai) {
        this.mabl = mabl;
        this.taikhoan = taikhoan;
        this.hoten = hoten;
        this.thang = thang;
        this.nam = nam;
        this.luongcoban = luongcoban;
        this.thuong = thuong;
        this.truluong = truluong;
        this.tongluong = tongluong;
        this.ngaytinhluong = ngaytinhluong;
        this.songaycong = songaycong;
        this.trangthai = trangthai;
    }

    public int getMabl() {
        return mabl;
    }

    public void setMabl(int mabl) {
        this.mabl = mabl;
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

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(int luongcoban) {
        this.luongcoban = luongcoban;
    }

    public int getThuong() {
        return thuong;
    }

    public void setThuong(int thuong) {
        this.thuong = thuong;
    }

    public int getTruluong() {
        return truluong;
    }

    public void setTruluong(int truluong) {
        this.truluong = truluong;
    }

    public int getTongluong() {
        return tongluong;
    }

    public void setTongluong(int tongluong) {
        this.tongluong = tongluong;
    }

    public Date getNgaytinhluong() {
        return ngaytinhluong;
    }

    public void setNgaytinhluong(Date ngaytinhluong) {
        this.ngaytinhluong = ngaytinhluong;
    }

    public int getSongaycong() {
        return songaycong;
    }

    public void setSongaycong(int songaycong) {
        this.songaycong = songaycong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
