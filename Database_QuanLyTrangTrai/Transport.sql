-- Tao bang Transport
CREATE TABLE TRANSPORT
(
    TransID varchar2(10) PRIMARY KEY,
    DateShipped date,
    StatusTrans number(1)
);

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
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('4-APR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('5-APR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('1-APR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('29-APR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('27-MAY-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('20-MAR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('10-MAR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('9-MAY-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('01-APR-2022', 'DD-MON-RR'), 0);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('27-MAY-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('01-APR-2022', 'DD-MON-RR'), 1);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('22-APR-2021', 'DD-MON-RR'), 0);
INSERT INTO TRANSPORT VALUES ('T' || trans_id.nextval, TO_DATE('02-APR-2022', 'DD-MON-RR'), 1);

