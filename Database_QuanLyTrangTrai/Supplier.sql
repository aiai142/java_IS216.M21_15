-- Tao bang Supplier
CREATE TABLE SUPPLIER
(
    SupID varchar2(10) PRIMARY KEY,
    SupName varchar2(50),
    SupPhone varchar2(25),
    SupAdd varchar2(400),
    SupEmail varchar2(100)
);

-- Insert du lieu
CREATE SEQUENCE sup_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY VINH HUNG', '02838256258', '43 Mac Dinh Chi, Phuong Da Kao, Q1, TpHCM', 'info@vinhhung.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY PHU MY', '02923841304', ' C12/21 Tan Kien, Quan Binh Chanh, TpHCM', 'info@phumy.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY PHAN BON CAN THO', '02923841043', ' Khu Cong nghiep Tra Noc 1, Tra Noc, Binh Thuy, TP. Can Tho, Can Tho', 'info@cfccobay.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY SEN VANG', '0938123494', ' 210 Pham Cong Tru, KDC 143ha, Thanh My Loi, Q2, TpHCM', 'sales@senvang.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY BINH MINH', '0913723236', '53A, Duong 77, Tan Quy, Q7, TpHCM', 'cskh@binhminh.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY SFARM', '02818502591', '556 Nguyen Thi Ranh, Ap Bau Tron, Xa Nhuan Duc, Cu Chi, TpHCM', 'info@sfarm.vn');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY LAVAMIX', '02837252555', '3 Duong so 1, Phuoc Binh, TP. Thu Duc, TpHCM', 'info@lavamix.vn');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY VINATAP', '0947751206', '59 Ha Huy Giap, Thanh Loc, Q12, TpHCM', 'vinatap@gmail.com');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY PHU HUNG THINH', '0912562434', '68 Dong Nai, Phuong 15, Q10, TpHCM', 'info@phuhungthinh.vn');
INSERT INTO SUPPLIER VALUES ('S' || sup_id.nextval, 'CTY TIEN PHAT', '02838764706', '9 Dinh Nghi Xuan, Binh Tri Dong, Binh Tan, TpHCM', 'info@tienphat.com');
