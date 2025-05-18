package BUS;

import DAO.ChiTietQuyenDAO;
import DAO.NguoiDungDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NguoiDungDTO;
import DTO.NhomQuyenDTO;
import DTO.UserInfoDTO;
import GUI.ChangePassword;
import helper.BCrypt;
import helper.Exception.AuthenticationException;
import helper.Exception.EmptyFieldException;
import helper.OTP;
import helper.SendEmailSMTP;
import helper.SendEmailSMTP.EmailSentListener;
import helper.Validation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungBUS {
//    private final NguoiDungDTO user;
    private final NguoiDungDAO ndDAO = new NguoiDungDAO();
    private final NhomQuyenDAO nqDAO = new NhomQuyenDAO();
    private final ChiTietQuyenDAO ctqDAO = new ChiTietQuyenDAO();
    private  ChangePassword changepassword ;

    public NguoiDungBUS() {
    }
    
    public UserInfoDTO verifyLogin(String username, String password) throws Exception {
        // Input validation
        if (username.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập tài khoản", "username");
        }
        if (password.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập mật khẩu", "password");
        }
        if (!Validation.isValidUsername(username)) {
            throw new IllegalArgumentException("Tài khoản không hợp lệ");
        }
        
        // Authentication
        NguoiDungDTO user = null;
        try {
            user = ndDAO.verifyLogin(username);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        
        if (user == null || !BCrypt.checkpw(password, user.getMatKhau())) {
            throw new AuthenticationException("Tài khoản này không tồn tại hoặc mật khẩu không đúng.");
        }
        
        if (user.getTrangThai() == 0) {
            throw new AuthenticationException("Tài khoản của bạn đã bị khoá.");
        }
        
        NhomQuyenDTO permissionInfo = null;
        try {
            permissionInfo = nqDAO.getPermissionById(user.getMaNhomQuyen());
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        
        if (permissionInfo.getTrangThai() == 0) {
            throw new AuthenticationException("Đăng nhập thất bại do tài khoản của bạn thuộc vào nhóm quyền không còn hiệu lực.");
        }
        
        // Authorization
        List<ChiTietQuyenDTO> viewPermission = new ArrayList<>();
        try {
            viewPermission = ctqDAO.getAuthorization(user.getMaNhomQuyen());
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        
        return new UserInfoDTO(user, permissionInfo, viewPermission);
    }
    
    public NguoiDungDTO verifyEmail(String email) throws Exception {
        // Input validation
        if (email.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập địa chỉ email", null);
        }
        if (!Validation.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        
        // Authentication
        NguoiDungDTO user = null;
        try {
            user = ndDAO.getInfoByEmail(email);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        
        if (user == null) {
            throw new AuthenticationException("Không tìm thấy tài khoản nào được liên kết với địa chỉ email bạn đã nhập. Vui lòng thử lại.");
        }
        
        return user;
    }
    
    public void sendEmailOTP(String username, String email, EmailSentListener listener) throws Exception {
        String otp = OTP.generateOTP();
        SendEmailSMTP.sendOTPAsync(email, username, otp, listener);
    }
    
    public boolean handleConfirmOTP(String inputOtp, String otp) throws EmptyFieldException, IllegalArgumentException {
        if (inputOtp.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập mã xác nhận", null);
        }
        if (!Validation.isValidOTP(inputOtp)) {
            throw new IllegalArgumentException("Mã xác nhận không hợp lệ");
        }
        return otp.equals(inputOtp);
    }
    
    public boolean handleRecoverPassword(NguoiDungDTO user, String newPassword, String confirmNewPassword) throws Exception {
        if (newPassword.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập mật khẩu mới", "newPassword");
        }
        if (confirmNewPassword.isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập xác nhận mật khẩu mới", "confirmNewPassword");
        }
        if (!Validation.isValidPassword(newPassword) || !Validation.isValidPassword(confirmNewPassword)) {
            throw new IllegalArgumentException("Mật khẩu phải có 6-32 kí tự, có chứa ít nhất 1 kí tự thường, 1 kí tự hoa, 1 chữ số");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            throw new IllegalArgumentException("Mật khẩu không chính xác, vui lòng thử lại");
        }
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
        int result;
        try {
            result = ndDAO.changePassword(user, hashedPassword);
        } catch (SQLException e) {
            throw e;
        }
        return result > 0;
    }
    
    public int ChangEmail(NguoiDungDTO user, String newEmail) throws SQLException{
        return ndDAO.changeEmail(user, newEmail);
    }
    
    public int ChangPassword(NguoiDungDTO user, String pass) throws SQLException {
        return ndDAO.changePassword(user, pass);
    }
    
    public int getUserPriority(NguoiDungDTO user) throws SQLException {
        return ndDAO.getUserPriority(user);
    }
    
    public ArrayList<NguoiDungDTO> getUserList(String query, int priority) throws SQLException {
        return ndDAO.getUserList(query, priority);
    }
    
    public int lockAccount(NguoiDungDTO user) throws SQLException {
        return ndDAO.lockAccount(user);
    }
    
    public int handleCreateNewUser(NguoiDungDTO user) throws EmptyFieldException, SQLException {
        if (user.getTaiKhoan().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập tài khoản", "username");
        }
        if (user.getHoTen().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập họ tên", "fullName");
        }
        if (user.getEmail().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập email", "email");
        }
        if (user.getMatKhau().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập mật khẩu", "password");
        }

        if (!Validation.isValidUsername(user.getTaiKhoan())) {
            throw new IllegalArgumentException("Tài khoản không hợp lệ");
        }
        
        boolean existed;
        try {
            existed = ndDAO.checkUserAlreadyExisted(user);
        } catch (SQLException e) {
            throw e;
        }
        if (existed) {
            throw new IllegalArgumentException("Tài khoản đã tồn tại, vui lòng chọn một tên tài khoản khác");
        }
        
        if (!Validation.isValidVietnameseName(user.getHoTen())) {
            throw new IllegalArgumentException("Họ tên không được chứa kí tự số");
        }
        if (!Validation.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        if (!Validation.isValidPassword(user.getMatKhau())) {
            throw new IllegalArgumentException("Mật khẩu phải có 6-32 kí tự, có chứa ít nhất 1 kí tự thường, 1 kí tự hoa, 1 chữ số");
        }
        
        String hashedPassword = BCrypt.hashpw(user.getMatKhau(), BCrypt.gensalt(12));
        user.setMatKhau(hashedPassword);
        
        int result;
        try {
            result = ndDAO.createUser(user);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public int handleUpdateUser(NguoiDungDTO oldInfo, NguoiDungDTO newInfo) throws EmptyFieldException, SQLException {
        if (oldInfo.getHoTen().equals(newInfo.getHoTen()) &&
                oldInfo.getEmail().equals(newInfo.getEmail()) &&
                newInfo.getMatKhau().isEmpty()) {
            return -1;
        }
        
        if (newInfo.getHoTen().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập họ tên", "fullName");
        }
        if (newInfo.getEmail().isEmpty()) {
            throw new EmptyFieldException("Bạn chưa nhập email", "email");
        }
        
        if (!Validation.isValidVietnameseName(newInfo.getHoTen())) {
            throw new IllegalArgumentException("Họ tên không được chứa kí tự số");
        }
        if (!Validation.isValidEmail(newInfo.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        if (!(newInfo.getMatKhau().isEmpty()) && !Validation.isValidPassword(newInfo.getMatKhau())) {
            throw new IllegalArgumentException("Mật khẩu phải có 6-32 kí tự, có chứa ít nhất 1 kí tự thường, 1 kí tự hoa, 1 chữ số");
        }
        
        if (!(newInfo.getMatKhau().isEmpty())) {
            String hashedPassword = BCrypt.hashpw(newInfo.getMatKhau(), BCrypt.gensalt(12));
            newInfo.setMatKhau(hashedPassword);
        }
        
        int result = 0;
        try {
            result = ndDAO.updateUser(newInfo);
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
    
    public String updateCaNhan(NguoiDungDTO user) {
        try {
            if (ndDAO.updateInfo(user))
                return "Cập nhật thành công!";
            else
                return "Không có dữ liệu nào được cập nhật (có thể tài khoản không tồn tại hoặc dữ liệu không đổi).";
        } catch (Exception e) {
            return "Cập nhật thất bại: " + e.getMessage();
        }
    }
    
    //Dùng cho phần Nhân Viên
    public ArrayList<NguoiDungDTO> getAllDanhSachNhanVien(){
        return ndDAO.getAllDanhSachNhanVien();
    }
    
    public String updateChucVuNhanVien(NguoiDungDTO nd){
        if(ndDAO.updateChucVuCuaNhanVien(nd))
           return "Cập nhật thành công!";
        return "Cập nhật thất bại!";
    
    }   
}
