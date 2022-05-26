-- Tao bang Employee
CREATE TABLE Employee
(
    empID varchar2(10) PRIMARY KEY,
    farmID varchar2(10),
    empName varchar2(40),
    empAdd varchar2(400),
    empPhone varchar2(25),
    empEmail varchar2(50),
    startDate date,
    userID varchar2(10),
    
    CONSTRAINT FK01_EMP FOREIGN KEY(farmID) REFERENCES Farm(farmID),
    CONSTRAINT FK02_EMP FOREIGN KEY(userID) REFERENCES Sys_User(userID)
);

-- Insert du lieu
CREATE SEQUENCE emp_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F1', 'Nguyen Thanh Hien', '731 Tran Hung Dao, Q5, TpHCM', '0913468953', 'hiennt@gmail.com', TO_DATE('22-JUL-2006', 'DD-MON-RR'), 'U1');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F1', 'Le Huynh Nhu', '23/5 Nguyen Trai, Q5, TpHCM', '0952485123', 'nhulh@gmail.com', TO_DATE('30-JUL-2006', 'DD-MON-RR'), 'U5');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F1', 'Tran Minh Anh', '45 Nguyen Canh Chan, Q1, TpHCM', '0974289138', 'anhtm@gmail.com', TO_DATE('05-AUG-2006', 'DD-MON-RR'), 'U6');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F1', 'Thai Ngoc Thanh Chau', '50/34 Le Dai Hanh, Q10, TpHCM', '0989346295', 'chautnt@gmail.com', TO_DATE('02-OCT-2006', 'DD-MON-RR'), 'U7');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F1', 'Ngo Thanh Tuan', '34 Truong Dinh, Q3, TpHCM', '0964512837', 'tuannt@gmail.com', TO_DATE('28-OCT-2006', 'DD-MON-RR'), 'U8');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F2', 'Pham Nhu Ngoc', '227 Nguyen Van Cu, Q5, TpHCM', '0976155796', 'ngocpn@gmail.com', TO_DATE('24-NOV-2006', 'DD-MON-RR'), 'U9');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F2', 'Nguyen Nhu Nhut', '32/3 Tran Binh Trong, Q5, TpHCM', '0979328591', 'nhutnn@gmail.com', TO_DATE('01-DEC-2006', 'DD-MON-RR'), 'U2');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F2', 'Do Dang Khoa', '45/2 An Duong Vuong, Q5, TpHCM', '0928316592', 'khoadd@gmail.com', TO_DATE('13-DEC-2006', 'DD-MON-RR'), 'U3');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F2', 'Ngo Quang Thien', '873 Le Hong Phong, Q5, TpHCM', '0945975268', 'thiennq@gmail.com', TO_DATE('25-DEC-2006', 'DD-MON-RR'), 'U10');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F2', 'Phan Thai', '34/34B Nguyen Trai, Q1, TpHCM', '0941798627', 'thaiphan@gmail.com', TO_DATE('28-DEC-2006', 'DD-MON-RR'), 'U11');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Hoang Nhu Ngoc', '56 Dien Bien Phu, Q1, TpHCM', '0997047382', 'ngochn@gmail.com', TO_DATE('14-JAN-2007', 'DD-MON-RR'), 'U12');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Nguyen Thi Truc Thanh', '712 Huynh Thuc Khang, Q1, TpHCM', '0933605636', 'thanhntt@gmail.com', TO_DATE('16-JAN-2007', 'DD-MON-RR'), 'U13');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Nguyen Van B', '2 Nam Ky Khoi Nghia, Q1, TpHCM', '0908591516', 'nguyenvanb@gmail.com', TO_DATE('21-JAN-2007', 'DD-MON-RR'), 'U14');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Ho Tuan Linh', '62 Le Duan, Q1, TpHCM', '0788853771', 'linhht@gmail.com', TO_DATE('11-FEB-2007', 'DD-MON-RR'), 'U15');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Lam Ngoc Son', '16/7 Nguyen Dinh Chieu, Q3, TpHCM', '0899871879', 'sonnl@gmail.com', TO_DATE('19-FEB-2007', 'DD-MON-RR'), 'U16');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F3', 'Dang Tan Phong', '78 Pham Ngoc Thach, Q3, TpHCM', '0704475479', 'phongdt@gmail.com', TO_DATE('23-MAR-2007', 'DD-MON-RR'), 'U4');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Ly Huynh Tam', '99 Hoang Sa, Q3, TpHCM', '0385892896', 'tamlh@gmail.com', TO_DATE('08-APR-2007', 'DD-MON-RR'), 'U17');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Vo Minh Thu', '138 Tran Quang Dieu, Q3, TpHCM', '0765727479', 'thuvm@gmail.com', TO_DATE('26-MAY-2007', 'DD-MON-RR'), 'U18');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Duong Ngoc Lam', '67 Truong Sa, Q3, TpHCM', '0898511979', 'lamdn@gmail.com', TO_DATE('31-MAY-2007', 'DD-MON-RR'), 'U19');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Tran Thi Huyen Anh', '23/6 Ho Thi Ky, Q10, TpHCM', '0322782271', 'anhtth@gmail.com', TO_DATE('03-SEP-2007', 'DD-MON-RR'), 'U5');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Ta Dang Dung', '17/9 Tran Nhat Duat, Q1, TpHCM', '0937173211', 'dungtd@gmail.com', TO_DATE('16-SEP-2007', 'DD-MON-RR'), 'U21');
INSERT INTO EMPLOYEE VALUES ('E' || emp_id.nextval, 'F4', 'Phan Ngoc Bach', '82 Hong Linh, Q10, TpHCM', '0772052999', 'bachpn@gmail.com', TO_DATE('24-SEP-2007', 'DD-MON-RR'), 'U22');
