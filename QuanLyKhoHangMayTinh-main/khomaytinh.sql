-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2023 at 05:43 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `khomaytinh`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietcungcap`
--

CREATE TABLE `chitietcungcap` (
  `manhacungcap` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `gianhap` bigint(20) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietcungcap`
--

INSERT INTO `chitietcungcap` (`manhacungcap`, `masanpham`, `gianhap`, `trangthai`) VALUES
(1, 1, 15150000, 1),
(1, 10, 9700000, 1),
(1, 12, 50390000, 1),
(1, 14, 34790000, 1),
(1, 17, 23090000, 1),
(1, 18, 16290000, 1),
(1, 19, 12540000, 1),
(1, 20, 40740000, 1),
(1, 23, 37990000, 1),
(2, 1, 15900000, 1),
(2, 5, 30190000, 1),
(2, 6, 39900000, 1),
(2, 8, 16000000, 1),
(2, 10, 10250000, 1),
(2, 11, 21290000, 1),
(2, 13, 32590000, 1),
(2, 16, 16390000, 1),
(2, 20, 38490000, 1),
(2, 21, 19290000, 1),
(3, 2, 9150000, 1),
(3, 8, 17250000, 1),
(3, 11, 20440000, 1),
(3, 14, 36490000, 1),
(3, 16, 17290000, 1),
(3, 17, 23190000, 1),
(4, 5, 30040000, 1),
(4, 14, 36290000, 1),
(4, 17, 24090000, 1),
(4, 20, 38490000, 1),
(4, 27, 25490000, 1),
(5, 7, 14200000, 1),
(5, 11, 21690000, 1),
(5, 18, 18190000, 1),
(5, 21, 19290000, 1),
(6, 3, 18990000, 1),
(6, 4, 21690000, 1),
(6, 8, 16550000, 1),
(6, 11, 20790000, 1),
(6, 17, 22790000, 1),
(6, 18, 16290000, 1),
(6, 23, 39740000, 1),
(7, 1, 15200000, 1),
(7, 7, 14200000, 1),
(7, 14, 34790000, 1),
(7, 15, 15190000, 1),
(8, 2, 9150000, 1),
(8, 4, 22040000, 1),
(8, 7, 13500000, 1),
(8, 8, 16900000, 1),
(8, 13, 31690000, 1),
(8, 21, 19790000, 1),
(8, 24, 13790000, 1),
(8, 25, 30790000, 1),
(8, 26, 22890000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `manhacungcap` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluongnhap` int(11) NOT NULL,
  `dongia` bigint(20) NOT NULL,
  `nguoithem` varchar(255) NOT NULL,
  `soluongthucte` int(11) NOT NULL,
  `soluongtonkho` int(11) NOT NULL,
  `trangthai` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maphieunhap`, `manhacungcap`, `masanpham`, `soluongnhap`, `dongia`, `nguoithem`, `soluongthucte`, `soluongtonkho`, `trangthai`) VALUES
(1, 1, 20, 10, 40740000, 'ngoctram', 10, 0, 1),
(1, 5, 21, 10, 19290000, 'ngoctram', 10, 0, 1),
(2, 5, 7, 10, 14200000, 'baoduy', 10, 0, 1),
(3, 1, 17, 10, 23090000, 'trunganh', 10, 0, 1),
(3, 2, 10, 10, 10250000, 'trunganh', 10, 0, 1),
(3, 7, 14, 20, 34790000, 'trunganh', 20, 0, 1),
(4, 8, 13, 20, 31690000, 'thuyduyen', 20, 0, 1),
(5, 6, 4, 20, 21690000, 'ngoctram', 20, 20, 1),
(5, 6, 17, 10, 22790000, 'ngoctram', 10, 0, 1),
(5, 7, 1, 20, 15200000, 'ngoctram', 20, 10, 1),
(6, 2, 20, 20, 38490000, 'thienan', 20, 0, 1),
(6, 8, 13, 10, 31690000, 'thienan', 10, 10, 1),
(7, 6, 4, 10, 21690000, 'baoduy', 10, 0, 1),
(7, 8, 25, 20, 30790000, 'baoduy', 20, 0, 1),
(8, 6, 8, 10, 16550000, 'ngoctram', 10, 0, 1),
(9, 2, 8, 20, 16000000, 'baoduy', 20, 0, 1),
(10, 8, 26, 10, 22890000, 'ngoctram', 10, 0, 1),
(11, 1, 17, 10, 23090000, 'hoainam', 10, 0, 1),
(11, 6, 11, 20, 20790000, 'hoainam', 20, 0, 1),
(12, 5, 11, 20, 21690000, 'thienan', 20, 0, 1),
(12, 7, 14, 10, 34790000, 'thienan', 10, 10, 1),
(13, 6, 18, 10, 16290000, 'baoduy', 10, 10, 1),
(13, 7, 7, 10, 14200000, 'baoduy', 10, 0, 1),
(13, 7, 14, 10, 34790000, 'baoduy', 10, 0, 1),
(14, 5, 7, 10, 14200000, 'thienan', 10, 10, 1),
(15, 4, 17, 10, 24090000, 'ngoctram', 10, 0, 1),
(15, 5, 7, 20, 14200000, 'ngoctram', 20, 0, 1),
(16, 2, 13, 10, 32590000, 'thuyduyen', 10, 10, 1),
(16, 8, 21, 10, 19790000, 'thuyduyen', 10, 0, 1),
(17, 7, 14, 10, 34790000, 'thienan', 10, 10, 1),
(18, 8, 2, 10, 9150000, 'thienan', 10, 10, 1),
(19, 5, 7, 10, 14200000, 'nvnhap', 8, 0, 1),
(20, 1, 19, 10, 12540000, 'thienan', 10, 10, 1),
(21, 8, 26, 10, 22890000, 'thienan', 10, 10, 1),
(22, 8, 24, 10, 13790000, 'nvnhap', 0, 0, 1),
(24, 8, 25, 5, 30790000, 'thienan', 5, 5, 1),
(24, 8, 26, 5, 22890000, 'thienan', 5, 5, 1),
(25, 6, 3, 15, 18990000, 'thienan', 10, 10, 1),
(26, 8, 2, 10, 9150000, 'nvnhap', 5, 5, 1),
(27, 2, 21, 10, 19290000, 'nvnhap', 0, 0, 1);

--
-- Triggers `chitietphieunhap`
--
DELIMITER $$
CREATE TRIGGER `increase_product_quantity` AFTER UPDATE ON `chitietphieunhap` FOR EACH ROW BEGIN
    DECLARE updated_soluong INT;
    DECLARE masanpham_val INT;
    
    -- Get the updated soluong value and masanpham value
    SET updated_soluong = NEW.soluongtonkho;
    SET masanpham_val = NEW.masanpham;
    
    IF updated_soluong < OLD.soluongtonkho THEN
        -- Update the soluong column in the sanpham table
        UPDATE sanpham SET soluong = soluong + (OLD.soluongtonkho - updated_soluong)
        WHERE masanpham = masanpham_val;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieuxuat`
--

CREATE TABLE `chitietphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietphieuxuat`
--

INSERT INTO `chitietphieuxuat` (`maphieuxuat`, `masanpham`, `soluong`, `dongia`) VALUES
(1, 11, 10, 23990000),
(2, 1, 10, 16890000),
(2, 21, 10, 21890000),
(3, 11, 20, 23990000),
(4, 4, 10, 24490000),
(5, 20, 20, 44990000),
(6, 7, 30, 14890000),
(7, 20, 10, 44990000),
(8, 8, 10, 18490000),
(8, 26, 10, 27990000),
(9, 10, 10, 12490000),
(9, 25, 10, 35990000),
(10, 13, 20, 35990000),
(11, 17, 20, 27490000),
(12, 14, 10, 40490000),
(13, 17, 1, 27490000),
(14, 17, 9, 27490000),
(15, 14, 1, 40490000),
(16, 14, 1, 40490000),
(17, 14, 1, 40490000),
(18, 8, 10, 18490000),
(19, 14, 7, 40490000),
(20, 14, 10, 40490000),
(21, 7, 10, 14890000),
(22, 11, 10, 23990000),
(22, 17, 10, 27490000),
(23, 25, 10, 35990000),
(24, 7, 8, 14890000),
(25, 21, 10, 21890000);

--
-- Triggers `chitietphieuxuat`
--
DELIMITER $$
CREATE TRIGGER `decrease_product_quantity` AFTER INSERT ON `chitietphieuxuat` FOR EACH ROW BEGIN
    DECLARE soluongxuat INT;
    DECLARE masanphamxuat INT;
    SET masanphamxuat = NEW.masanpham;
    SET soluongxuat = NEW.soluong;
    
    UPDATE sanpham SET soluong = soluong - soluongxuat WHERE masanpham = masanphamxuat;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(50) NOT NULL,
  `hanche` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietquyen`
--

INSERT INTO `chitietquyen` (`manhomquyen`, `machucnang`, `hanhdong`, `hanche`) VALUES
(1, 'taikhoan', 'create', '[]'),
(1, 'taikhoan', 'delete', '[]'),
(1, 'taikhoan', 'update', '[]'),
(1, 'taikhoan', 'view', '[]'),
(2, 'kho', 'create', '[]'),
(2, 'kho', 'delete', '[]'),
(2, 'kho', 'update', '[]'),
(2, 'kho', 'view', '[]'),
(2, 'loaisanpham', 'create', '[]'),
(2, 'loaisanpham', 'delete', '[]'),
(2, 'loaisanpham', 'update', '[]'),
(2, 'loaisanpham', 'view', '[]'),
(2, 'nhacungcap', 'create', '[]'),
(2, 'nhacungcap', 'delete', '[]'),
(2, 'nhacungcap', 'update', '[]'),
(2, 'nhacungcap', 'view', '[]'),
(2, 'phieunhap', 'create', '[]'),
(2, 'phieunhap', 'view', '[]'),
(2, 'phieuxuat', 'create', '[]'),
(2, 'phieuxuat', 'view', '[]'),
(2, 'sanpham', 'create', '[]'),
(2, 'sanpham', 'delete', '[]'),
(2, 'sanpham', 'update', '[]'),
(2, 'sanpham', 'view', '[]'),
(2, 'taikhoan', 'create', '[]'),
(2, 'taikhoan', 'delete', '[]'),
(2, 'taikhoan', 'view', '[]'),
(2, 'thongke', 'view', '[]'),
(2, 'tonkho', 'update', '[]'),
(2, 'tonkho', 'view', '[]'),
(3, 'kho', 'view', '[]'),
(3, 'loaisanpham', 'view', '[]'),
(3, 'nhacungcap', 'view', '[]'),
(3, 'phieunhap', 'create', '[\"nhacungcap\", \"gianhap\"]'),
(3, 'phieunhap', 'view', '[]'),
(3, 'sanpham', 'view', '[]'),
(3, 'tonkho', 'view', '[\"gianhap\"]'),
(4, 'kho', 'view', '[]'),
(4, 'loaisanpham', 'view', '[]'),
(4, 'phieuxuat', 'create', '[]'),
(4, 'phieuxuat', 'view', '[]'),
(4, 'sanpham', 'view', '[]'),
(4, 'tonkho', 'view', '[\"gianhap\"]');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('kho', 'Quản lý kho', 1),
('loaisanpham', 'Quản lý loại sản phẩm', 1),
('nhacungcap', 'Quản lý nhà cung cấp', 1),
('phieunhap', 'Quản lý phiếu nhập', 1),
('phieuxuat', 'Quản lý phiếu xuất', 1),
('sanpham', 'Quản lý sản phẩm', 1),
('taikhoan', 'Quản lý tài khoản', 1),
('thongke', 'Thống kê', 1),
('tonkho', 'Quản lý tồn kho', 1);

-- --------------------------------------------------------

--
-- Table structure for table `kho`
--

CREATE TABLE `kho` (
  `makho` int(11) NOT NULL,
  `tenkho` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kho`
--

INSERT INTO `kho` (`makho`, `tenkho`, `diachi`, `trangthai`) VALUES
(1, 'Kho Q1 TP.HCM', '65/13 Trần Đình Xu, Phường Cầu Kho, Quận 1, TP.HCM', 1),
(2, 'Kho Hà Nội', '412 Lạc Long Quân, Quận Tây Hồ, Hà Nội', 1),
(3, 'Kho Hải Phòng', '3 D22, Phường Đồng Quốc Bình, Quận Ngô Quyền, TP. Hải Phòng', 1),
(4, 'Kho Q. Bình Thạnh TP.HCM', '292 Nơ Trang Long, Phường 12, Quận Bình Thạnh, TP.HCM', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `maloaisanpham` int(11) NOT NULL,
  `tenloaisanpham` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`maloaisanpham`, `tenloaisanpham`, `trangthai`) VALUES
(1, 'Lenovo', 1),
(2, 'MSI', 1),
(3, 'Asus', 1),
(4, 'Acer', 1),
(5, 'HP', 1),
(6, 'Gigabyte', 1),
(7, 'DELL', 1),
(8, 'LG Gram', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nguoidung`
--

CREATE TABLE `nguoidung` (
  `taikhoan` varchar(255) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `hoten` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nguoidung`
--

INSERT INTO `nguoidung` (`taikhoan`, `matkhau`, `hoten`, `email`, `manhomquyen`, `trangthai`) VALUES
('baoduy', '$2a$12$PzpNeXoGH.MPBTCj5CoODekfzNz2ji2fb58NAhzgTeT5KIimwPgkm', 'Nguyễn Lê Bảo Duy', 'baoduy@gmail.com', 2, 1),
('haonhien', '$2a$12$8ZKIYfNbm61PsTv3vnMFiO82bn7I4KuyMpCcTdSQWWQiFaijVA4Z6', 'Âu Hạo Nhiên', 'nhienau@gmail.com', 1, 1),
('hoainam', '$2a$12$s6pNzMuvZvnhxzlg4tcWiuMgelOHjzfX4JLC5.QCCBuJjm5kO8p7e', 'Thân Trọng Hoài Nam', 'hoainam@gmail.com', 2, 1),
('ngoctram', '$2a$12$IRl.BXJrRIt2J5rux9V4s.ZoSxsQLpaITPSKTYTjFicLCAP1RR0ue', 'Võ Hồ Ngọc Trâm', 'ngoctram@gmail.com', 2, 1),
('nvnhap', '$2a$12$eVl09v71cYD5PB4C7eDoHu7phOu5FwVOsfu2h2bNXq9Z9qGjadtTC', 'Nguyễn Anh Duy', 'nvnhap@gmail.com', 3, 1),
('nvxuat', '$2a$12$0pgPE.PDGFfdY7.qiCXk2eMmNUwSXQpvgtqepOiufLf5c5eGxh1HS', 'Dương Tiến Ðức', 'nvxuat@gmail.com', 4, 1),
('testuser1', '$2a$12$AjXYGmuTP4ybCDRMm4.XPOR/3uRKdifqvwFBWAFulihyrI7DqdRzK', 'Ngô Lâm Vũ', 'test@gmail.com', 3, 0),
('testuser2', '$2a$12$8xi0pseEda5ofRH70nH2DedjWtdjufzoo7EdmNpKWeBaHCxBL51MO', 'Kim Quang Đông', 'testuser2@gmail.com', 5, 1),
('testuser3', '$2a$12$3QfUiAHfBXV4uBpdv6u9AuXHz4w18ab/Uw02Dj7fBRmJDEUt1JVQS', 'Trần Đức Duy', 'testuser3@gmail.com', 4, 1),
('thienan', '$2a$12$BQ3I/AQ4CrxTWEl.qscdken6AOUxYgXt0jRVSV0vREsjhGRUpkIOS', 'Nguyễn Thiên Ân', 'thienan@gmail.com', 2, 1),
('thuyduyen', '$2a$12$Zz/ScTqeWvuBorhHYF1XpOKD8g0XhFHX81AGEFbMdIlUJYVFyic4u', 'Nguyễn Thuỳ Duyên', 'thuyduyen@gmail.com', 2, 1),
('trunganh', '$2a$12$hGpO.9TAkYJBVlTJ0LTdvu4ykf.NAL.jHjSDQwKUqN1qD4b8xcNOK', 'Nguyễn Trung Anh', 'trunganh@gmail.com', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `sdt`, `diachi`, `trangthai`) VALUES
(1, 'Công Ty TNHH Điều Khiển Tự Động An Phát', '02835109735', '86/21 Phan Tây Hồ, P. 7, Q. Phú Nhuận TP. Hồ Chí Minh', 1),
(2, 'Công Ty TNHH Thương Mại Dịch Vụ Hoàng Cố Đô', '02838115345', '622/16/5 Cộng Hòa, Phường 13, Quận Tân Bình, TP HCM', 1),
(3, 'Công Ty Cổ Phần Bán Lẻ Kỹ Thuật Số FPT', '02873023456', '261 - 263 Khánh Hội, P2, Q4, TP. Hồ Chí Minh', 1),
(4, 'Công ty Cổ phần đầu tư công nghệ HACOM', '19001903', 'Số 129 - 131, phố Lê Thanh Nghị, Phường Đồng Tâm, Quận Hai Bà Trưng, Hà Nội', 1),
(5, 'Công Ty TNHH Thương Mại Hoàng Phát Hải Phòng', '02253250888', 'Số 4, Lô 2A Lê Hồng Phong, Ngô Quyền, Tp. Hải Phòng', 1),
(6, 'Công ty cổ phần dịch vụ - thương mại Phong Vũ', '0967567567', 'Tầng 5, Số 117-119-121 Nguyễn Du, Phường Bến Thành, Quận 1, Thành Phố Hồ Chí Minh', 1),
(7, 'Công ty cổ phần Thế Giới Di Động', '02838125960', '128 Trần Quang Khải, P. Tân Định, Q.1, TP.Hồ Chí Minh', 1),
(8, 'Công ty CP Công nghệ Thương mại Dịch vụ Vietstars', '090 469 0212', ' Số 109 Lê Thanh Nghị, TP Hải Dương', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `douutien` smallint(6) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `douutien`, `trangthai`) VALUES
(1, 'Admin', 2, 1),
(2, 'Quản lý kho', 1, 1),
(3, 'Nhân viên nhập hàng', 0, 1),
(4, 'Nhân viên xuất hàng', 0, 1),
(5, 'test', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigiantao` datetime NOT NULL DEFAULT current_timestamp(),
  `makho` int(11) NOT NULL,
  `nguoitao` varchar(255) NOT NULL,
  `nguoixacnhan` varchar(255) DEFAULT NULL,
  `nguoinhanhang` varchar(255) DEFAULT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigiantao`, `makho`, `nguoitao`, `nguoixacnhan`, `nguoinhanhang`, `tongtien`, `trangthai`) VALUES
(1, '2023-10-24 00:18:11', 2, 'ngoctram', 'ngoctram', 'ngoctram', 600300000, 4),
(2, '2023-10-25 00:18:24', 3, 'baoduy', 'baoduy', 'baoduy', 142000000, 4),
(3, '2023-10-25 00:18:24', 3, 'trunganh', 'trunganh', 'trunganh', 1029200000, 4),
(4, '2023-10-25 00:18:24', 2, 'thuyduyen', 'thuyduyen', 'thuyduyen', 633800000, 4),
(5, '2023-10-25 00:18:24', 3, 'ngoctram', 'ngoctram', 'ngoctram', 965700000, 4),
(6, '2023-10-25 00:18:24', 3, 'thienan', 'thienan', 'thienan', 1086700000, 4),
(7, '2023-10-25 00:18:24', 3, 'baoduy', 'baoduy', 'baoduy', 832700000, 4),
(8, '2023-10-25 00:18:24', 2, 'ngoctram', 'ngoctram', 'ngoctram', 165500000, 4),
(9, '2023-10-25 00:18:24', 1, 'baoduy', 'baoduy', 'baoduy', 320000000, 4),
(10, '2023-10-25 00:18:24', 3, 'ngoctram', 'ngoctram', 'ngoctram', 228900000, 4),
(11, '2023-10-25 00:18:24', 4, 'hoainam', 'hoainam', 'hoainam', 646700000, 4),
(12, '2023-10-25 00:18:24', 1, 'thienan', 'thienan', 'thienan', 781700000, 4),
(13, '2023-10-25 00:18:24', 2, 'baoduy', 'baoduy', 'baoduy', 652800000, 4),
(14, '2023-10-25 00:18:24', 1, 'thienan', 'thienan', 'thienan', 142000000, 4),
(15, '2023-10-25 00:18:24', 4, 'ngoctram', 'ngoctram', 'ngoctram', 524900000, 4),
(16, '2023-10-25 00:18:24', 2, 'thuyduyen', 'thuyduyen', 'thuyduyen', 523800000, 4),
(17, '2023-11-25 00:39:57', 1, 'thienan', 'thienan', 'thienan', 347900000, 4),
(18, '2023-12-04 00:00:00', 3, 'thienan', 'thienan', 'nvnhap', 91500000, 4),
(19, '2023-12-04 00:00:00', 1, 'nvnhap', 'thienan', 'nvnhap', 113600000, 4),
(20, '2023-12-05 00:00:00', 4, 'thienan', 'thienan', 'nvnhap', 125400000, 4),
(21, '2023-12-05 00:00:00', 4, 'thienan', 'thienan', 'nvnhap', 228900000, 4),
(22, '2023-12-05 00:00:00', 1, 'nvnhap', NULL, NULL, 137900000, 2),
(24, '2023-12-05 00:00:00', 1, 'thienan', 'thienan', 'nvnhap', 268400000, 4),
(25, '2023-12-05 00:00:00', 1, 'thienan', 'thienan', 'nvnhap', 189900000, 4),
(26, '2023-12-05 00:00:00', 1, 'nvnhap', 'trunganh', 'nvnhap', 45750000, 4),
(27, '2023-12-05 00:00:00', 3, 'nvnhap', NULL, NULL, 192900000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigiantao` datetime NOT NULL DEFAULT current_timestamp(),
  `nguoitao` varchar(255) NOT NULL,
  `tongtien` bigint(20) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigiantao`, `nguoitao`, `tongtien`, `trangthai`) VALUES
(1, '2023-10-26 00:46:54', 'baoduy', 239900000, 1),
(2, '2023-10-26 00:46:54', 'trunganh', 387800000, 1),
(3, '2023-10-26 00:46:54', 'ngoctram', 479800000, 1),
(4, '2023-10-26 00:46:54', 'trunganh', 244900000, 1),
(5, '2023-10-26 00:46:54', 'thuyduyen', 899800000, 1),
(6, '2023-10-26 00:46:54', 'hoainam', 446700000, 1),
(7, '2023-10-26 00:46:54', 'thienan', 449900000, 1),
(8, '2023-10-26 00:46:54', 'thuyduyen', 464800000, 1),
(9, '2023-10-26 00:46:54', 'hoainam', 484800000, 1),
(10, '2023-10-26 00:46:54', 'thienan', 719800000, 1),
(11, '2023-10-26 00:46:54', 'baoduy', 549800000, 1),
(12, '2023-10-26 00:46:54', 'ngoctram', 404900000, 1),
(13, '2023-11-21 23:19:24', 'thienan', 27490000, 1),
(14, '2023-11-21 23:23:32', 'hoainam', 247410000, 1),
(15, '2023-11-27 09:33:05', 'thienan', 40490000, 1),
(16, '2023-11-27 09:42:39', 'thienan', 40490000, 1),
(17, '2023-11-27 09:47:27', 'ngoctram', 40490000, 1),
(18, '2023-12-05 00:34:24', 'trunganh', 184900000, 1),
(19, '2023-12-05 00:35:14', 'baoduy', 283430000, 1),
(20, '2023-12-05 00:44:02', 'thienan', 404900000, 1),
(21, '2023-12-05 17:27:00', 'thienan', 148900000, 1),
(22, '2023-12-05 17:30:15', 'ngoctram', 514800000, 1),
(23, '2023-12-05 17:59:20', 'trunganh', 359900000, 1),
(24, '2023-12-05 23:33:38', 'ngoctram', 119120000, 1),
(25, '2023-12-05 23:37:12', 'ngoctram', 218900000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` int(11) NOT NULL,
  `maloaisanpham` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `giaxuat` bigint(20) NOT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `vga` varchar(255) DEFAULT NULL,
  `ocung` varchar(255) DEFAULT NULL,
  `manhinh` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `trongluong` float DEFAULT NULL,
  `mausac` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `maloaisanpham`, `tensanpham`, `soluong`, `giaxuat`, `cpu`, `ram`, `vga`, `ocung`, `manhinh`, `pin`, `trongluong`, `mausac`, `os`, `trangthai`) VALUES
(1, 1, 'Lenovo Ideapad Slim 5 Light 14ABR8 82XS002JVN', 0, 16890000, 'AMD Ryzen™ 7 7730U (8 Cores / 16 Threads, 2.0 to 4.5GHz, 4MB L2 / 16MB L3)', '16GB Onboard DDR4 3200MHz', 'AMD Radeon™ Graphics', '512GB SSD M.2 2242 PCIe® 4.0x4 NVMe', '14\" FHD (1920x1080) IPS 300nits Anti-glare, 100% sRGB', 'Integrated 47Wh', 1.17, 'Cloud Grey', 'Windows 11 Home', 1),
(2, 2, 'MSI Modern 14 C11M 011VN', 0, 9990000, 'Intel Core i3-1115G4 (up to 4.1Ghz, 6MB)', '8GB DDR4 3200Mhz Onboard (Không nâng cấp)', 'Intel® UHD Graphics', '512GB NVMe PCIe Gen 3x4 SSD (1 Slot)', '14.0\" FHD (1920x1080), IPS-Level , 45% NTSC', '3 cell, 39Whr', 1.3, 'Đen', 'Windows 11 Home', 1),
(3, 3, 'Asus Vivobook Pro 15 OLED M6500QC MA002W', 0, 21490000, 'AMD Ryzen 5-5600H up to 4.2GHz 19MB, 6 nhân, 12 luồng', '16GB (Onboard) DDR4 3200MHz (không nâng cấp)', 'NVIDIA GeForce RTX 3050 4GB GDDR6', '512GB SSD M.2 PCIE G3X2', '15.6-inch 2.8K (2880 x 1620) OLED 16:9 aspect ratio, 120Hz refresh rate, 600nits peak brightness, 100% DCI-P3 color gamut, VESA CERTIFIED Display HDR True Black 600, PANTONE Validated, Glossy display, 70% less harmful blue light', '70WHrs, 3S1P, 3-cell Li-ion', 1.8, 'Bạc', 'Windows 11 Home', 1),
(4, 2, 'MSI Katana 15 B13VEK 1205VN', 0, 24490000, 'Intel Core i5-13420H (3.40GHz~4.60GHz) 8 Cores 12 Threads, 12 MB Intel® Smart Cache', '8GB (1 x 8GB) DDR5 5200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'NVIDIA GeForce RTX 4050 Laptop GPU 6GB GDDR6 + MUX Switch', '512GB NVMe PCIe Gen 4 SSD (2 slots)', '15.6\" FHD (1920x1080), 144Hz, IPS-Level, 45% NSTC', '3 cell, 53.5Whr', 2.25, 'Đen', 'Windows 11 Home', 1),
(5, 4, 'Acer Predator Helios 300 PH315 55 76KG', 0, 33990000, 'Intel® Core™ i7-12700H (up to 4.7Ghz, 24MB cache)', '16GB DDR5 4800Mhz (2x8GB) (2x SO-DIMM socket, up to 32GB SDRAM)', 'NVIDIA GeForce RTX 3060 6GB GDDR6', '512GB NVMe PCIe Gen3x4 SSD (2 slot)', '15.6 inch QHD (2560 x1440) IPS 165Hz, DCI-P3 100%, 5ms, 300nits, SlimBezel', '4 cell, 90Whr', 2.4, 'Đen', 'Windows 11 Home', 1),
(6, 8, 'LG Gram 2023 16Z90R GAH76A5', 0, 44490000, 'Intel Core i7-1360P (12 Cores: 4P + 8E, P: 2.2 up to 5.0 GHz / E: 1.6 up to 3.7 GHz) 18 MB Cache', '16GB LPDDR5 6000MHz (Dual Channel, Onboard, không nâng cấp)', 'Intel Iris Xe Graphics', '512GB PCIe NVMe M.2 SSD (2 slot, còn trống 1 khe M.2)', '16 inch WQXGA (2560x1600), 16:10, IPS 60Hz, DCI-P3 99%, LGD, 350 nits, Anti-Glare', '80WHr', 1.199, 'Xám', 'Windows 11 Home', 1),
(7, 3, 'Asus VivoBook 14 OLED M1405YA KM047W', -8, 14890000, 'Ryzen™ 5 7530U Mobile Processor (6-core/12-thread, 16MB cache, up to 4.3 GHz max boost)', '8GB Onboard DDR4 3200MHz (1 x Sodimm Slot, up to 24GB Ram)', 'AMD Radeon™ Graphics', '512GB M.2 NVMe™ PCIe® 3.0 SSD (1 slot, support M.2 2280 PCIe 3.0x4)', '14\" 2.8K (2880 x 1800) OLED 16:10, 90Hz 0.2ms, 600nits, 100% DCI-P3, Glossy display, Screen-to-body ratio: 86%, PANTONE Validated, VESA CERTIFIED Display HDR True Black 600', '50WHrs, 3S1P, 3-cell Li-ion', 1.6, 'Cool Silver', 'Windows 11 Home', 1),
(8, 3, 'Asus VivoBook 15X OLED S3504VA L1226W', 10, 18490000, 'Intel® Core™ i5-1340P Processor 1.9 GHz (12MB Cache, up to 4.6 GHz, 12 cores, 16 Threads)', '16GB (8GB Onboard + 8GB Sodimm) DDR4 3200MHz', 'Intel Iris Xe Graphics (with dual channel memory)', '512GB M.2 NVMe™ PCIe® 4.0 SSD', '15.6\" FHD (1920 x 1080) OLED 16:9 aspect ratio, 60Hz 0.2ms, 550nits HDR peak brightness, 100% DCI-P3, Glossy display, Screen-to-body ratio: 85%, PANTONE Validated, VESA CERTIFIED Display HDR True Black 500', '50WHrs, 3S1P, 3-cell Li-ion', 1.7, 'Cool Silver', 'Windows 11 Home', 1),
(9, 7, 'Dell Inspiron 15 3520 N5I5122W1 Black', 0, 15990000, 'Intel(R) Core(TM) i5-1235U Processor (12MB Cache, up to 4.4 GHz)', '1 x 8GB DDR4 2666MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'Intel Iris Xe Graphics (with dual channel memory); Intel® UHD Graphics', '256GB SSD NVMe PCIe (1 Slot)', '15.6 Inch FHD (1920 x 1080),120Hz, Anti- Glare LED Backlit Narrow Border Display', '3-cell Li-ion, 41 Wh', 1.9, 'Carbon Black', 'Windows 11 Home + Office Home&Student', 1),
(10, 7, 'Dell Vostro 3520 V5I3614W1 Gray', 0, 12490000, 'Intel Core i3 - 1215U (Up to 4.4 Ghz, 12Mb)', '8GB (8x1) DDR4 2666MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'Intel UHD Graphics', '256GB SSD M.2 PCIE', '15.6 inch FHD (1920 x 1080) 120Hz 250 nits WVA Anti- Glare LED Backlit Narrow Border Display', '3 Cell 41WHr', 1.66, 'Đen', 'Windows 11 Home + Office Home & Student 2021', 1),
(11, 1, 'Lenovo ThinkPad E14 21E300E3VN', 0, 23990000, 'Intel® Core™ i7-1255U, 10 Cores (2P + 8E) / 12 Threads, P-core 1.7 / 4.7GHz, E-core 1.2 / 3.5GHz, 12MB', '8GB Soldered DDR4-3200 (Trống 1 slot Sodimm, nâng cấp tối đa 40GB)', 'Intel Iris Xe Graphics (with dual channel memory); Intel® UHD Graphics (with single channel memory)', '512GB SSD M.2 2242 PCIe® 4.0x4 NVMe® Opal 2.0 (Còn trống 1 Slot M.2 2242 PCIe 3.0 x4)', '14\" FHD (1920x1080) IPS 300nits Anti-glare, 45% NTSC', '45Whr battery', 1.64, 'Black', 'Windows 11 Home', 1),
(12, 3, 'ASUS ProArt Studiobook 16 OLED H7600ZM L2079W', 0, 57990000, 'Intel Core i9-12900H 2.5GHz up to 5.0GHz 24MB', '32GB (16x2) DDR5 4800MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'NVIDIA® GeForce® RTX™ 3060 6GB GDDR6', '1TB M.2 NVMe™ PCIe® 4.0 Performance SSD (2 slots M.2 2280 PCIe 4.0x4)', '16\" 4K (3840 x 2400) OLED 16:10, 0.2ms response time, 550nits, 100% DCI-P3, PANTONE Validated, Glossy display, 70% less harmful blue light', '4 Cell 90WHrs', 2.4, 'Mineral Black Aluminum', 'Windows 11 Home', 1),
(13, 8, 'LG Gram Style 14Z90RS GAH54A5', 0, 35990000, 'Intel Core i5-1340P (12 Cores: 4P + 8E, P: 1.9 up to 4.6 GHz / E: 1.4 up to 3.4 GHz) 12 MB Cache', '16GB LPDDR5 6000MHz (Dual Channel, Onboard, không nâng cấp)', 'Intel Iris Xe Graphics', '512GB PCIe NVMe M.2 SSD (2 slot, còn trống 1 khe M.2)', '14 inch WQXGA+ 2K8 (2880 x 1800), 16:10, OLED 90Hz 0.2ms, DCI-P3 100%, LGD, 500 nits, Anti-Glare Flow Refrection', '72 Wh Li-Ion, Thời lượng pin lên đến 15 giờ (Video playback)', 0.999, 'Trắng', 'Windows 11 Home', 1),
(14, 8, 'LG Gram Ultra Slim 15Z90RT GAH55A5', 0, 40490000, 'Intel Core i5-1340P (12 Cores: 4P + 8E, P: 1.9 up to 4.6 GHz / E: 1.4 up to 3.4 GHz) 12 MB Cache', '16GB LPDDR5 6000MHz (Dual Channel, Onboard, không nâng cấp)', 'Intel Iris Xe Graphics', '512GB PCIe NVMe M.2 SSD (2 slot, còn trống 1 khe M.2)', '15.6 inch FHD (1920*1080), 16:9, OLED 60Hz, DCI-P3 100%, LGD, 500 nits, Anti-Glare Flow Refrection', '60 Wh Li-Ion', 0.99, 'Đen', 'Windows 11 Home', 1),
(15, 1, 'Lenovo Ideapad Gaming 3 15IAH7 82S9006YVN', 0, 18990000, 'Intel Core i5-12500H, 12C (4P + 8E) / 16T, P-core 2.5 / 4.5GHz, E-core 1.8 / 3.3GHz, 18MB', '1 x 8GB DDR4 3200MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'NVIDIA GeForce RTX 3050 4GB GDDR6, Boost Clock 1740MHz, TGP 85W', '512GB SSD M.2 2242 PCIe 4.0x4 NVMe (2 Slots)', '15.6\" FHD (1920x1080) IPS 250nits Anti-glare, 120Hz, 45% NTSC, DC dimmer', 'Integrated 60Wh', 2.315, 'Onyx Grey', 'Windows 11 Home', 1),
(16, 3, 'ASUS TUF F15 FX507ZC4 HN074W', 0, 19990000, 'Intel® Core™ i5-12500H Processor 2.5 GHz (18M Cache, up to 4.5 GHz, 12 cores: 4 P-cores and 8 E-cores)', '8GB DDR4 3200MHz (2x SO-DIMM socket, up to 32GB RAM)', 'NVIDIA GeForce RTX 3050 4GB GDDR6, Up to 1790MHz* at 95W (1740MHz Boost Clock+50MHz OC, 80W+15W Dynamic Boost) + MUX Switch + Optimus', '512GB SSD M.2 PCIE G3X2 (Còn trống 1 khe SSD M.2 PCIE)', '15.6\" FHD (1920 x 1080) IPS, 144Hz, Wide View, 250nits, Narrow Bezel, Non-Glare with 45% NTSC, 62.5% sRGB', '4 Cell 56WHr', 2.2, 'Jaeger Grey', 'Windows 11 Home', 1),
(17, 4, 'Acer Nitro 5 Tiger AN515 58 50D2', 0, 27490000, 'Intel Core i5-12500H 3.3GHz up to 4.5GHz 18MB', '16GB (8x2) DDR5 4800MHz (2x SO-DIMM socket, up to 32GB SDRAM)', 'NVIDIA GeForce RTX™ 3060 6GB GDDR6', '512GB PCIe NVMe SED SSD (Còn trống 1 khe SSD M.2 PCIE và 1 khe 2.5\" SATA)', '15.6\" FHD (1920 x 1080) IPS, 165Hz, Acer ComfyView LED-backlit TFT LCD, SlimBezel, 100% sRGB', '4 Cell 57.5WHr', 2.5, 'Obsidian Black', 'Windows 11 Home', 1),
(18, 2, 'MSI GF63 12UC 887VN', 0, 19890000, 'Intel Core i7-12650H 3.5GHz up to 4.70GHz 24MB, 10 nhân, 16 luồng', '8GB (8x1) DDR4 3200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'Nvidia Geforce RTX 3050 4GB GDDR6', '512GB NVMe PCIe Gen 4x4 SSD ( Còn trống 1 khe 2.5\" SATA)', '15.6\" FHD (1920 x 1080) IPS 144Hz, Thin Bezel, 45%NTSC', '3 Cell 52.4 WHr', 1.86, 'Đen', 'Windows 11 Home', 1),
(19, 4, 'Acer Aspire 7 A715 42G R05G', 0, 14990000, 'AMD Ryzen 5 – 5500U (6 nhân 12 luồng)', '8GB DDR4 (2x SO-DIMM socket, up to 32GB SDRAM)', 'NVIDIA GeForce GTX 1650 4GB GDDR6', '512GB PCIe® NVMe™ M.2 SSD', '15.6\" FHD (1920 x 1080) IPS, Anti-Glare, 144Hz', '4 Cell 48Whr', 2.1, 'Đen, Có đèn bàn phím', 'Windows 11 Home', 1),
(20, 7, 'Dell Alienware M15 R6 P109F001CBL', 0, 44990000, 'Intel Core i7-11800H 2.3GHz up to 4.6GHz 24MB', '32GB (16x2) DDR4 3200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'NVIDIA GeForce RTX 3060 6GB GDDR6', '1TB SSD M.2 PCIe', '15.6 inch QHD (2560 x 1440) 240Hz, 2ms, with ComfortView plus, NVIDIA G-SYNC and Advanced Optimus, WVA Display', '6 Cell 86WHr', 2.69, 'Dark Side of the Moon', 'Windows 11 Home + Office Home & Student', 1),
(21, 6, 'Gigabyte G5 MF F2PH333SH', 0, 21890000, 'Intel Core i5-12450H 3.3GHz up to 4.4GHz, 8 Cores 12 Threads, 12MB Cache', '8GB (1x8GB) DDR4-3200Mhz (2 khe ram, nâng cấp tối đa 64GB RAM)', 'Nvidia Geforce RTX 4050 6GB GDDR6', '512GB SSD M.2 PCIE G4X4 (2 khe M.2, Còn trống 1 khe SSD M.2 PCIE G3x4)', '15.6 inch FHD (1920x1080), 144Hz, IPS-level, Thin Bezel, 45% NTSC', '4 Cell 54Whrs', 1.9, 'Đen', 'Windows 11 Home', 1),
(22, 5, 'HP Pavilion 15 eg3091TU 8C5L2PA', 0, 22990000, 'Intel® Core™ i7-1355U (up to 5.0 GHz with Intel® Turbo Boost Technology, 12 MB L3 cache, 10 cores, 12 threads)', '16GB (2 x 8) DDR4 3200MHz (Còn 1 slot SO-DIMM, nâng cấp tối đa 16GB)', 'Intel® Iris® Xe Graphics', '512GB SSD M.2 NVMe™ PCIe® 3.0 (1 Slot, nâng cấp tối đa 1TB)', '15.6\" diagonal, FHD (1920 x 1080), IPS, micro-edge, anti-glare, 250 nits, 45% NTSC', '41WHrs, 3S1P, 3-cell Li-ion', 1.74, 'Warm Gold', 'Windows 11 Home', 1),
(23, 5, 'HP Omen 16 b0127TX 4Y0W7PA', 0, 45990000, 'Intel Core i7-11800H (8 nhân, 16 luồng)', '16GB (2x8GB) DDR4 3200Mhz (2 khe, max 64GB RAM)', 'NVIDIA® GeForce RTX 3060 (6GB GDDR4)', '1TB SSD M.2 PCIE + 32GB Intel Optane memory', '16.1\"  QHD (2560 x 1440), 165 Hz 3 ms, IPS, 300 nits, 100% sRGB', '4 Cell 70WHr Li-ion polymer', 2.3, 'Shadow Black', 'Windows 10 Home', 1),
(24, 5, 'HP VICTUS 16 e0177AX 4R0U9PA', 0, 16990000, 'AMD Ryzen 5 5600H', '8GB (2x4GB) DDR4 3200Mhz (2 khe, max 64GB RAM)', 'NVIDIA® GeForce GTX™ 1650 Laptop GPU', '512GB SSD M.2 PCIE (2x M.2 SATA/NVMe)', '16.1\" FHD (1920 x 1080) IPS, 144Hz', '4 Cell 70WHr Li-ion polymer', 2.46, 'Mica Silver', 'Windows 11 Home', 1),
(25, 6, 'Gigabyte AORUS 15 XE4 73VNB14GH', 0, 35990000, 'Intel Core i7-12700H 3.6GHz up to 4.9GHz 25MB', '16GB (8x2) DDR4 3200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'NVIDIA GeForce RTX 3070Ti 8GB GDDR6 Boost Clock 1485 MHz / Maximum Graphics Power 130 W', '1TB SSD M.2 PCIE G4X4 (2 Slots)', '15.6\" QHD (2560x1440) IPS-level Anti-glare Display LCD (165Hz, 72% NTSC)', '4 Cell 99 WHrs', 2.4, 'Black', 'Windows 11 Home', 1),
(26, 1, 'Lenovo Legion 5 15ARH7H 82RE0036VN', 0, 27990000, 'AMD Ryzen 7 6800H (8C / 16T, 3.2 / 4.7GHz, 4MB L2 / 16MB L3)', '16GB (8x2) DDR5 4800MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'NVIDIA GeForce RTX 3050 Ti 4GB GDDR6, Boost Clock 1695MHz, TGP 95W', '512GB SSD M.2 2280 PCIe 4.0x4 NVMe (2 slots)', '15.6\" FHD (1920x1080) IPS 300nits Anti-glare, 165Hz, 100% sRGB, Dolby Vision, FreeSync, G-SYNC, DC dimmer', '4Cell, 80WHrs', 2.35, 'Storm Grey', 'Windows 11 Home', 1),
(27, 1, 'Lenovo Legion Y9000X IAH7', 0, 28990000, 'Intel Core i7-12700H (24MB Cache, 3.50GHZ up to 4.70 GHz, 14 cores 20 Threads)', '16GB DDR5 4800Mhz', 'NVIDIA GeForce RTX 3060 6GB GDDR6', '512GB M.2 2280 NVMe SSD', '16\" WQXGA 2K IPS 165Hz 100% sRGB 518 nits', '4 Cells, 80Wh', 2.1, 'Đen', 'Windows 11 bản quyền', 1);

-- --------------------------------------------------------

--
-- Table structure for table `trangthaiphieunhap`
--

CREATE TABLE `trangthaiphieunhap` (
  `matrangthai` int(11) NOT NULL,
  `tentrangthai` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trangthaiphieunhap`
--

INSERT INTO `trangthaiphieunhap` (`matrangthai`, `tentrangthai`) VALUES
(1, 'pending'),
(2, 'cancelled'),
(3, 'approved'),
(4, 'delivered');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietcungcap`
--
ALTER TABLE `chitietcungcap`
  ADD PRIMARY KEY (`manhacungcap`,`masanpham`),
  ADD KEY `FK_CHITIETCUNGCAP_SANPHAM` (`masanpham`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`manhacungcap`,`masanpham`),
  ADD KEY `FK_CHITIETPHIEUNHAP_CHITIETCUNGCAP_NHACUNGCAP` (`manhacungcap`),
  ADD KEY `FK_CHITIETPHIEUNHAP_CHITIETCUNGCAP_SANPHAM` (`masanpham`),
  ADD KEY `FK_CHITIETPHIEUNHAP_NGUOITHEM` (`nguoithem`);

--
-- Indexes for table `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`masanpham`),
  ADD KEY `FK_CHITIETPHIEUXUAT_SANPHAM` (`masanpham`);

--
-- Indexes for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`) USING BTREE,
  ADD KEY `FK_CHITIETQUYEN_CHUCNANG` (`machucnang`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Indexes for table `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`makho`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`maloaisanpham`);

--
-- Indexes for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`taikhoan`),
  ADD KEY `FK_NGUOIDUNG_NHOMQUYEN` (`manhomquyen`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_PHIEUNHAP_KHO` (`makho`),
  ADD KEY `FK_PHIEUNHAP_NGUOIDUNG` (`nguoitao`) USING BTREE,
  ADD KEY `FK_PHIEUNHAP_TRANGTHAIPHIEUNHAP` (`trangthai`),
  ADD KEY `FK_PHIEUNHAP_NGUOIXACNHAN` (`nguoixacnhan`),
  ADD KEY `FK_PHIEUNHAP_NGUOINHANHANG` (`nguoinhanhang`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_PHIEUXUAT_NGUOIDUNG` (`nguoitao`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`),
  ADD KEY `FK_SANPHAM_LOAISANPHAM` (`maloaisanpham`);

--
-- Indexes for table `trangthaiphieunhap`
--
ALTER TABLE `trangthaiphieunhap`
  ADD PRIMARY KEY (`matrangthai`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kho`
--
ALTER TABLE `kho`
  MODIFY `makho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `maloaisanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `trangthaiphieunhap`
--
ALTER TABLE `trangthaiphieunhap`
  MODIFY `matrangthai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietcungcap`
--
ALTER TABLE `chitietcungcap`
  ADD CONSTRAINT `FK_CHITIETCUNGCAP_NHACUNGCAP` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETCUNGCAP_SANPHAM` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `FK_CHITIETPHIEUNHAP_CHITIETCUNGCAP_NHACUNGCAP` FOREIGN KEY (`manhacungcap`) REFERENCES `chitietcungcap` (`manhacungcap`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETPHIEUNHAP_CHITIETCUNGCAP_SANPHAM` FOREIGN KEY (`masanpham`) REFERENCES `chitietcungcap` (`masanpham`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETPHIEUNHAP_NGUOITHEM` FOREIGN KEY (`nguoithem`) REFERENCES `nguoidung` (`taikhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETPHIEUNHAP_PHIEUNHAP` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD CONSTRAINT `FK_CHITIETPHIEUXUAT_PHIEUXUAT` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETPHIEUXUAT_SANPHAM` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD CONSTRAINT `FK_CHITIETQUYEN_CHUCNANG` FOREIGN KEY (`machucnang`) REFERENCES `chucnang` (`machucnang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CHITIETQUYEN_NHOMQUYEN` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD CONSTRAINT `FK_NGUOIDUNG_NHOMQUYEN` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_PHIEUNHAP_KHO` FOREIGN KEY (`makho`) REFERENCES `kho` (`makho`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUNHAP_NGUOINHANHANG` FOREIGN KEY (`nguoinhanhang`) REFERENCES `nguoidung` (`taikhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUNHAP_NGUOIXACNHAN` FOREIGN KEY (`nguoixacnhan`) REFERENCES `nguoidung` (`taikhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUNHAP_TAIKHOAN` FOREIGN KEY (`nguoitao`) REFERENCES `nguoidung` (`taikhoan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PHIEUNHAP_TRANGTHAIPHIEUNHAP` FOREIGN KEY (`trangthai`) REFERENCES `trangthaiphieunhap` (`matrangthai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_PHIEUXUAT_NGUOIDUNG` FOREIGN KEY (`nguoitao`) REFERENCES `nguoidung` (`taikhoan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_SANPHAM_LOAISANPHAM` FOREIGN KEY (`maloaisanpham`) REFERENCES `loaisanpham` (`maloaisanpham`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
