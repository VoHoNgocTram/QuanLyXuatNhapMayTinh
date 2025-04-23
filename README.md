# Quản Lý Xuất Nhập Máy Tính - Hệ thống thông tin doanh nghiệp

# Đóng góp
Nhóm 16 
1. Nguyễn Lê Bảo Duy - 3121410120 
2. Võ Hồ Ngọc Trâm - 3121410518
3. Hoàng Trọng Tiến - 3121410496
4. Bùi Nguyễn Trung Kiên - 3120560048
          
# Tổng quan
Quản Lý Xuất Nhập Máy Tính là một ứng dụng desktop được phát triển bằng Java, nhằm hỗ trợ quản lý hoạt động nhập và xuất máy tính trong doanh nghiệp. Ứng dụng cung cấp giao diện đồ họa thân thiện với người dùng, được xây dựng bằng Java Swing, và tích hợp cơ sở dữ liệu MySQL để lưu trữ, quản lý dữ liệu về sản phẩm, nhân viên, hóa đơn, kho, tài khoản, và các thống kê liên quan.
Tính năng

Quản lý sản phẩm: Thêm, cập nhật, xóa và xem thông tin sản phẩm máy tính như mã sản phẩm, tên, số lượng, giá và danh mục.
Quản lý kho: Theo dõi và quản lý số lượng hàng tồn kho, nhập/xuất kho và kiểm kê sản phẩm.
Quản lý hóa đơn: Tạo và quản lý hóa đơn nhập/xuất, bao gồm thông tin như mã hóa đơn, ngày, tổng tiền và sản phẩm liên quan.
Quản lý nhân viên: Quản lý thông tin nhân viên, bao gồm mã nhân viên, tên, thông tin liên lạc và các chi tiết khác.
Quản lý bảng chức vụ: Quản lý các chức vụ trong doanh nghiệp, phân quyền và vai trò cho nhân viên.
Quản lý đơn nghỉ: Xử lý các đơn xin nghỉ phép của nhân viên, theo dõi trạng thái và lịch sử.
Quản lý thông tin cá nhân: Cho phép nhân viên xem và cập nhật thông tin cá nhân của mình.
Quản lý tài khoản: Quản lý tài khoản người dùng, bao gồm đăng ký, đăng nhập và phân quyền truy cập.
Quản lý nhà cung cấp: Xử lý thông tin nhà cung cấp cho các hoạt động nhập hàng.
Thống kê: Cung cấp các báo cáo thống kê về sản phẩm, hóa đơn, kho và các hoạt động khác.
Giao diện đồ họa: Giao diện người dùng trực quan, dễ sử dụng, được xây dựng bằng Java Swing.
Kết nối cơ sở dữ liệu: Kết nối với MySQL để lưu trữ và truy xuất dữ liệu hiệu quả.

# Công nghệ sử dụng
Java: Ngôn ngữ lập trình chính cho logic ứng dụng và giao diện.
Java Swing: Dùng để xây dựng giao diện đồ họa.
MySQL: Hệ quản trị cơ sở dữ liệu để lưu trữ dữ liệu ứng dụng.
JDBC: Java Database Connectivity để tương tác với cơ sở dữ liệu MySQL.

# Cấu trúc dự án
src/DAO: Các lớp Data Access Object để xử lý thao tác cơ sở dữ liệu (ví dụ: NhaCungCapDAO, PhieuNhapDAO).
src/DTO: Các lớp Data Transfer Object để mô hình hóa các thực thể (ví dụ: NhaCungCap, NhanVien, PhieuNhap).
src/GUI: Các lớp Java Swing cho giao diện người dùng (ví dụ: NhaCungCapForm, PhieuNhapForm).
src/ConnectDB: Thiết lập và cấu hình kết nối cơ sở dữ liệu (ConnectDB.java).
src/BUS: Lớp logic nghiệp vụ để xử lý các hoạt động giữa GUI và DAO (ví dụ: NhaCungCapBUS).

# Yêu cầu
Java Development Kit (JDK): Phiên bản 8 trở lên.
MySQL: Được cài đặt và chạy với cơ sở dữ liệu đã cấu hình.
MySQL JDBC Driver: Cần thiết cho kết nối cơ sở dữ liệu.
IDE: Eclipse, IntelliJ IDEA hoặc bất kỳ IDE nào tương thích với Java.

# Hướng dẫn cài đặt
Tải mã nguồn:
git clone https://github.com/VoHoNgocTram/QuanLyXuatNhapMayTinh.git


# Thiết lập cơ sở dữ liệu:
Tạo một cơ sở dữ liệu trên MySQL.
Chạy các tập lệnh SQL (nếu có trong kho lưu trữ) để tạo các bảng cần thiết.
Cập nhật thông tin kết nối cơ sở dữ liệu trong src/ConnectDB/ConnectDB.java với thông tin đăng nhập MySQL của bạn (URL, tên người dùng, mật khẩu).


# Thêm JDBC Driver:
Tải xuống MySQL JDBC Driver (MySQL Connector/J).
Thêm driver vào classpath hoặc thư viện phụ thuộc của dự án.


# Chạy ứng dụng:
Mở dự án trong IDE bạn chọn.
Biên dịch và chạy lớp frmLogin.java để khởi động ứng dụng.
Đăng nhập bằng thông tin tài khoản mặc định (nếu có) hoặc tạo tài khoản người dùng mới.



# Hướng dẫn sử dụng
Đăng nhập: Nhập thông tin đăng nhập của nhân viên để truy cập bảng điều khiển chính.
Quản lý sản phẩm và kho: Thêm, sửa, xóa sản phẩm và theo dõi tồn kho trong phần quản lý kho.
Xử lý hóa đơn: Tạo hóa đơn nhập/xuất, liên kết với nhà cung cấp và xem chi tiết hóa đơn.
Quản lý nhân viên và chức vụ: Thêm, cập nhật thông tin nhân viên và quản lý các chức vụ trong doanh nghiệp.
Xử lý đơn nghỉ: Tạo, xem và duyệt các đơn xin nghỉ phép của nhân viên.
Quản lý thông tin cá nhân và tài khoản: Cập nhật thông tin cá nhân và quản lý tài khoản người dùng.
Thống kê: Xem các báo cáo về sản phẩm, hóa đơn, kho và các hoạt động khác.
Quản lý nhà cung cấp: Thêm hoặc cập nhật thông tin nhà cung cấp khi cần.

