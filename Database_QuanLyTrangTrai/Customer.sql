-- Tao bang Customer
CREATE TABLE Customer
(
    cusID varchar2(10) PRIMARY KEY,
    cusName varchar2(50),
    gender varchar2(10),
    dateOfBirth date,
    cusAdd varchar2(400),
    cusPhone varchar2(25),
    cusEmail varchar2(100),
    cusType varchar2(20),
    accrued_Money number(11,2),
    userID varchar2(10),
    
    CONSTRAINT FK_CUS FOREIGN KEY(userID) REFERENCES SYS_USER(userID),
    CONSTRAINT CHK_GENDER CHECK (gender IN ('Nam', 'Nu'))
);
ALTER TABLE Customer ADD CONSTRAINT UNIQUE_cusPhone UNIQUE (cusPhone);


-- Insert du lieu
CREATE SEQUENCE cus_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Thi Hue', 'Nu', TO_DATE('07-AUG-1976', 'DD-MON-RR'), 
'221 Tran Hung Dao, Q5, Tp.HCM', '0947388089', 'nguyenhue@gmail.com', 'Gold', 10484000, 'U23');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Ngoc Nhung', 'Nu', TO_DATE('14-JAN-1989', 'DD-MON-RR'), 
'50/34 Le Dai Hanh, Q10, TpHCM', '0853322233', 'leduc@gmail.com', NULL, 2560000, 'U24');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Tran Bich Hoa', 'Nu', TO_DATE('19-DEC-1988', 'DD-MON-RR'), 
'284 Do Xuan Hop, Q9, TpHCM', '0989677543', 'tranbinh@gmai.com', 'Silver', 5380000, 'U25');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Ngo Thi Hong Anh', 'Nu', TO_DATE('12-JUN-1997', 'DD-MON-RR'), 
'93 Man Thien, Q9, TpHCM', '0855147311', 'ngoanh@gmail.com', 'Diamond', 20619200, 'U26');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Lam Thi Hong', 'Nu', TO_DATE('22-DEC-1979', 'DD-MON-RR'), 
'2A Binh Chieu, Q.Thu Duc, TpHCM', '0989057777', 'lamhong@gmail.com', 'Gold', 10300000, 'U27');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Phan Van Hoang', 'Nam', TO_DATE('27-MAY-1992', 'DD-MON-RR'), 
'119 Nguyen Dinh Chieu, Q3, TpHCM', '0947528619', 'leminh@gmail.com', NULL, 0, 'U28');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Tran Thi Mo', 'Nu', TO_DATE('07-AUG-1994', 'DD-MON-RR'), 
'24b Ho Thi Ky, Q10, TpHCM', '0338522142', 'tranmo@gmail.com', NULL, 0, 'U29');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Do Van Bao', 'Nam', TO_DATE('03-APR-1992', 'DD-MON-RR'), 
'23/14 Tan Chanh Hiep, Q12,TpHCM', '0914526312', 'dobao@gmail.com', 'Silver', 7260000, 'U30');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Thi Hoa', 'Nu', TO_DATE('25-OCT-1998', 'DD-MON-RR'), 
'16A Le Van Duyet, Q. Binh Thanh, TpHCM', '0889127855', 'nguyenhoa@gmail.com', 'Silver', '4030000', 'U31');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Kim Khanh', 'Nu', TO_DATE('11-APR-1986', 'DD-MON-RR'), 
'977 Quang Trung, Q.Go Vap, TpHCM', '0989454747', 'khanhnguyen@gmail.com', NULL, 0, 'U32');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Do Thanh Tung', 'Nam', TO_DATE('26-MAY-2000', 'DD-MON-RR'), 
'123/2 Phan Van Tri, Q.Go Vap, TpHCM', '0337335989', 'tungdo@gmail.com', NULL, 0, 'U33');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Quoc Huy', 'Nam', TO_DATE('28-MAR-2002', 'DD-MON-RR'), 
'98 Linh Trung, Q.Thu Duc, TpHCM', '0938126321', 'huynguyen@gmail.com', NULL, 0, 'U34');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Ngo Ngoc Anh', 'Nu', TO_DATE('01-MAY-2001', 'DD-MON-RR'), 
'59 Tran Nhat Duat, Q1, TpHCM', '0281288394', 'ngocanh@gmail.com', NULL, 0, 'U35');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Tran Thanh Hang', 'Nu', TO_DATE('17-NOV-1998', 'DD-MON-RR'), 
'36 Nam Ky Khoi Nghia, Q1, TpHCM', '0787155276', 'nguyenthinh@gmail.com', NULL, 0, 'U36');
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Nguyen Duc Thinh', 'Nam', 
NULL, NULL, '0284432598', 'thinhnguyen@gmail.com', NULL, 1400000, NULL);
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Vo Minh Cuong', 'Nam', 
NULL, NULL, '0902371152', 'cuongvo@gmail.com', NULL, 19750000, NULL);
INSERT INTO CUSTOMER VALUES ('C' || cus_id.nextval, 'Truong Thanh Ha', 'Nu', TO_DATE('12-APR-2001', 'DD-MON-RR'), 
'301 Nguyen Van Cu, Q5, TpHCM', '0789223919', 'hatruong@gmail.com', NULL, 0, 'U37');
