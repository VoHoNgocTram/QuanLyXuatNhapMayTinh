package DTO;

public class SanPhamTonKhoDTO {
    private int maSanPham;
    private String tenSanPham;
    private int soLuongTonKho;
    private int soLuongDaXuat;

    public SanPhamTonKhoDTO(int maSanPham, String tenSanPham, int soLuongTonKho, int soLuongDaXuat) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongTonKho = soLuongTonKho;
        this.soLuongDaXuat = soLuongDaXuat;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public int getSoLuongDaXuat() {
        return soLuongDaXuat;
    }

    public void setSoLuongDaXuat(int soLuongDaXuat) {
        this.soLuongDaXuat = soLuongDaXuat;
    }
}
