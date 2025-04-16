/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Acer
 */
public class NhanVienDTO {
    private int manv;
    private String hoten;
    private Date ngaysinh;
    private String gioitinh; // Nam hoặc Nữ
    private String diachi;
    private String sdt;
    private String email;
    private int machucvu;
    private Date ngayvaolam;
    private int songayphep;
    private int luongcoban;
    private String trangthai; // Đang làm hoặc Nghỉ việc
    private String taikhoan;
    private String tenchucvu;

    public NhanVienDTO(int manv, String hoten, Date ngaysinh, String gioitinh, String diachi, String sdt, String email, int machucvu, Date ngayvaolam, int songayphep, int luongcoban, String trangthai, String taikhoan) {
        this.manv = manv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.machucvu = machucvu;
        this.ngayvaolam = ngayvaolam;
        this.songayphep = songayphep;
        this.luongcoban = luongcoban;
        this.trangthai = trangthai;
        this.taikhoan = taikhoan;
//        this.tenchucvu = tenchucvu;
    }

    public NhanVienDTO() {
    // Constructor không tham số
    }
    
    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public Date getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(Date ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public int getSongayphep() {
        return songayphep;
    }

    public void setSongayphep(int songayphep) {
        this.songayphep = songayphep;
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(int luongcoban) {
        this.luongcoban = luongcoban;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }
    
}
