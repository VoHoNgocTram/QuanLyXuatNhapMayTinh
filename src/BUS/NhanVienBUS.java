/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DAO.NguoiDungDAO;
import DTO.ChucVuDTO;
import DTO.NguoiDungDTO;
import DTO.lsChucVuDTO;
import helper.BCrypt;
import helper.Exception.EmptyFieldException;
import helper.Validation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.Date;
/**
 *
 * @author Acer
 */
public class NhanVienBUS {
    NhanVienDAO nvDAO = new NhanVienDAO();
    NguoiDungDAO ndDAO = new NguoiDungDAO();
    public ArrayList<NguoiDungDTO> getAllDSNhanVien() {
        return nvDAO.getAllDSNhanVien();
    }
    public NguoiDungDTO getNhanVienByTaiKhoan(String taikhoan){
        return nvDAO.getNhanVienByTaiKhoan(taikhoan);
    }
    public ArrayList<ChucVuDTO> getAllDSChucVu() {
        return nvDAO.getAllDSChucVu();
    }
    public ArrayList<lsChucVuDTO> getAllLichSuChucVu() {
        return nvDAO.getAllLichSuChucVu();
    }
    public ArrayList<lsChucVuDTO> getLichSuChucVuByTaiKhoan(String taikhoan){
        return nvDAO.getLichSuChucVuByTaiKhoan(taikhoan);
    }
    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    public boolean handleCreateNewUser(NguoiDungDTO user, lsChucVuDTO lscv) throws EmptyFieldException, SQLException {
        // Kiểm tra các trường bắt buộc
        if (user.getTaiKhoan().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập tài khoản", "Taikhoan");
        }
        if (user.getHoTen().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập họ tên", "Hoten");
        }
        if (user.getEmail().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập email", "Email");
        }
        if (user.getDiaChi().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập địa chỉ", "Diachi");
        }
        if (user.getSdt().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập số điện thoại", "sdt");
        }
        if (user.getNgaySinh() == null) {
            throw new EmptyFieldException("Bạn chưa chọn ngày sinh", "Ngaysinh");
        }
        if (user.getNgayVaoLam() == null) {
            throw new EmptyFieldException("Bạn chưa chọn ngày vào làm", "Ngayvaolam");
        }

        // Kiểm tra định dạng
        if (!Validation.isValidUsername(user.getTaiKhoan())) {
            throw new IllegalArgumentException("Tài khoản không hợp lệ");
        }
        if (!Validation.isValidVietnameseName(user.getHoTen())) {
            throw new IllegalArgumentException("Họ tên không được chứa kí tự số");
        }
        if (!Validation.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        if (!Validation.isValidPhoneNumber(user.getSdt())) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ (phải có đúng 10 chữ số)");
        }

        // Ngày vào làm phải sau ngày sinh
        if (!user.getNgayVaoLam().after(user.getNgaySinh())) {
            throw new IllegalArgumentException("Ngày vào làm phải sau ngày sinh");
        }

        // Kiểm tra trùng tài khoản
        boolean existed;
        try {
            existed = ndDAO.checkUserAlreadyExisted(user);
        } catch (SQLException e) {
            throw e;
        }
        if (existed) {
            throw new IllegalArgumentException("Tài khoản đã tồn tại, vui lòng chọn một tên tài khoản khác");
        }

//        // Mã hóa mật khẩu
        String passwd = generateRandomPassword(10);
        String hashedPassword = BCrypt.hashpw(passwd, BCrypt.gensalt(12));
        user.setMatKhau(hashedPassword);
        
         // Lưu người dùng và lịch sử chức vụ vào DB
        boolean addedUser = nvDAO.addNhanVien(user);
        boolean addedChucVu = nvDAO.addLichSuChucVu(lscv);
        
        // Gọi DAO để lưu vào DB
        return addedUser && addedChucVu;
        
    }
    
    public String addNhanVien(NguoiDungDTO nv){
        if(nvDAO.addNhanVien(nv))
            return "Thêm thành công !"; 
        return "Thêm thất bại !"; 
    }
    public String deleteNguoiDung(String tk){
        if(nvDAO.delete(tk))
            return "Xóa thành công!";
        return "Xóa thất bại!";
    }
    public boolean capNhatChucVuNguoiDung(String taiKhoan, int maChucVuMoi, Date ngayThayDoi, lsChucVuDTO lscv){
        
        return nvDAO.capNhatChucVuNguoiDung(taiKhoan, maChucVuMoi, ngayThayDoi, lscv); 
        
    }
}
