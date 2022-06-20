-- Tao bang Transport
CREATE TABLE Transport
(
    transID varchar2(10) PRIMARY KEY,
    statusTrans number(1)
);
ALTER TABLE Transport ADD CONSTRAINT CHK_Status CHECK (statusTrans IN (0, 1, 2));



-- Insert du lieu
CREATE SEQUENCE trans_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 0);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 0);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, 1);

