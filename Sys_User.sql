-- Tao bang
CREATE TABLE Sys_User
(
    userID varchar2(10) PRIMARY KEY,
    userName varchar2(50),
    userPassword varchar2(16),
    createdDate date,
    userRole varchar2(10)
);
ALTER TABLE Sys_User ADD CONSTRAINT FK_User FOREIGN KEY(userRole) REFERENCES Role(userRole);

-- Insert du lieu
CREATE SEQUENCE user_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'QL10010415', 'abcde0415', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR1');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'QL10010515', 'kamen786', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR1');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'QL10110615', 'richardoscar', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR1');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'QL10010715', 'zxcvbnm!2603', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR1');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'QL10010815', 'smith82', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR1');

INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025501', 'mypassword', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025502', 'tructhanh260191', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025503', '2411tuanlinh', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025504', 'Ngocson!2306', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025505', 'doraemon', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025506', ' farmEmp', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025507', '987654321', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025508', 'kamenrider57', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025509', 'shinichi111', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025510', 'ngocbachphan', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025511', 'bigsixhero', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025512', 'huynhnhu283', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025513', '1810@minhthu', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025514', 'nguyennhunhut', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025515', '547896321', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025516', 'asdfghjkl', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'NV10025517', 'phanthai992', TO_DATE('10-MAR-2022', 'DD-MON-RR'), 'UR2');

INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'Sunshine', '12345678', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'nhungnguyen89', 'ngocnhung33', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'tranhoa419', 'bichhoa031297', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'hungcuong@086', 'football', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'Victoria', 'qwertyuiop', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'phanvanhoang', 'vanhoang#a8k3', TO_DATE('20-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'Ngochien2207', '666666', TO_DATE('22-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'chris', 'chris123', TO_DATE('23-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'nguyenthihoa', 'nguyenhoa09', TO_DATE('26-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'isabella', 'iloveyou', TO_DATE('26-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'dotung007', 'Tungthanh', TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'Charlotte', '0987162826', TO_DATE('19-APR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'ngocanhcute', 'anhngongoc', TO_DATE('19-APR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'olivia', 'password123', TO_DATE('19-APR-2022', 'DD-MON-RR'), 'UR3');
INSERT INTO SYS_USER VALUES ('U' || user_id.nextval, 'hatruong2k1', 'sophie57', TO_DATE('27-APR-2022', 'DD-MON-RR'), 'UR3');
