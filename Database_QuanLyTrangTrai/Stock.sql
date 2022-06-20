-- Tao bang Stock
CREATE TABLE Stock
(
    stockID varchar2(10) PRIMARY KEY,
    statusStock number(1),
    type number(1)
);
ALTER TABLE Stock ADD CONSTRAINT CHK01_STATUS CHECK (statusStock IN (0, 1));
ALTER TABLE Stock ADD CONSTRAINT CHK02_TYPE CHECK (type IN (1, 2));



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