package DTO;

public class KhoDTO {
    private int maKho;
    private String tenKho;
    private String diaChi;

    
    public KhoDTO() {
    }

    public KhoDTO(int maKho, String tenKho, String diaChi) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
      
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


}
