-- Tao bang Role
CREATE TABLE ROLE
(
    UserRole varchar2(10) PRIMARY KEY,
    Description varchar2(50)
);
ALTER TABLE ROLE ADD CONSTRAINT CHK_ROLE CHECK (Description IN ('QuanLy', 'NhanVien', 'KhachHang'));

-- Insert du lieu
INSERT INTO ROLE VALUES ('UR1',	'QuanLy');
INSERT INTO ROLE VALUES ('UR2', 'NhanVien');
INSERT INTO ROLE VALUES ('UR3', 'KhachHang');