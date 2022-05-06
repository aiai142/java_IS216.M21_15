-- Tao bang Stock
CREATE TABLE STOCK
(
    StockID varchar2(10) PRIMARY KEY,
    StatusStock number(1),
    Type number(1)
);
ALTER TABLE STOCK ADD CONSTRAINT CHK01_TUS CHECK (StatusStock IN (0, 1));
ALTER TABLE STOCK ADD CONSTRAINT CHK02_TYPE CHECK (Type IN (1, 2));

-- Insert du lieu
CREATE SEQUENCE stock_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 0, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 0, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 1);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 2);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 2);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 1, 2);
INSERT INTO STOCK VALUES ('S' || stock_id.nextval, 0, 2);