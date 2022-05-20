-- Tao bang Role
CREATE TABLE Role
(
    userRole varchar2(10) PRIMARY KEY,
    roleName varchar2(50),

	ADD CONSTRAINT CHK_ROLE CHECK (roleName IN ('QuanLy', 'NhanVien', 'KhachHang'))
);


-- Insert du lieu
INSERT INTO ROLE VALUES ('UR1', 'QuanLy');
INSERT INTO ROLE VALUES ('UR2', 'NhanVien');
INSERT INTO ROLE VALUES ('UR3', 'KhachHang');