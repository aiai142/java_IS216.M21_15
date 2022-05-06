-- Tao bang Product
CREATE TABLE PRODUCT
(
    ProID varchar2(10) PRIMARY KEY,
    ProName varchar2(40),
    ProPrice number(11,2),
    FarmID varchar2(10)
);
ALTER TABLE PRODUCT ADD CONSTRAINT FK_Pro FOREIGN KEY(FarmID) REFERENCES FARM(FarmID);

-- Insert du lieu
CREATE SEQUENCE product_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Nho', 180000, 'F1', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Buoi', 40000, 'F1', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Chanh', 30000, 'F1', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Cam', 40000, 'F1', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Vai', 65000, 'F1', 'Trai cay');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Nhan', 70000, 'F2', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Xoai', 50000, 'F2', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Tao', 95000, 'F2', 'Trai cay');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Bi xanh', 32000, 'F2', 'Cu qua');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Bi do', 33000, 'F2', 'Cu qua');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Su su', 26000, 'F3', 'Cu qua');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Ca rot', 28000, 'F3', 'Cu qua');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Ca tim', 24000, 'F3', 'Cu qua');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Khoai tay', 24000, 'F3', 'Cu qua');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Khoai lang', 28000, 'F3', 'Cu qua');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Xa lach', 24000, 'F4', 'Rau');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Rau cai', 25000, 'F4', 'Rau');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Rau muong', 21000, 'F4', 'Rau');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Hanh la', 30000, 'F4', 'Rau');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Dua leo', 22000, 'F4', 'Cu qua');
