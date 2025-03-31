package DTO;

public class NhomQuyenDTO {
    private int maNhomQuyen;
    private String tenNhomQuyen;
    private int doUuTien;
    private int trangThai;

    public NhomQuyenDTO(int maNhomQuyen, String tenNhomQuyen, int trangThai) {
        this.maNhomQuyen = maNhomQuyen;
        this.tenNhomQuyen = tenNhomQuyen;
        this.trangThai = trangThai;
    }
    
    public NhomQuyenDTO(int maNhomQuyen, String tenNhomQuyen, int doUuTien, int trangThai) {
        this.maNhomQuyen = maNhomQuyen;
        this.tenNhomQuyen = tenNhomQuyen;
        this.doUuTien = doUuTien;
        this.trangThai = trangThai;
    }

    public int getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(int maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
