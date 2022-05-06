-- Tao bang Resources
CREATE TABLE RESOURCES
(
    ReID varchar2(10) PRIMARY KEY,
    Re_Name varchar2(50),
    RePrice number(11,2),
    Unit varchar2(20),

    CONSTRAINT CHK_UNIT CHECK (Unit IN('Bao', 'Goi', 'Chai', 'Cai'))
);

---------- Insert du lieu
CREATE SEQUENCE re_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Phan bon', 100000, 'Bao');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Hat giong rau', 25000, 'Goi');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Hat giong cu qua', 20000, 'Goi');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Hat giong trai cay', 20000, 'Goi');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Thuoc bao ve thuc vat', 42000, 'Chai');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Thung xop', 20000, 'Cai');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Dat trong', 250000, 'Bao');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Chat cai tao moi truong', 70000, 'Goi');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Gia do cay', 30000, 'Cai');
INSERT INTO RESOURCES VALUES ('R' || re_id.nextval, 'Xo dua', 40000, 'Bao');
