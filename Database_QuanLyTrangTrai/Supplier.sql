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
CREATE SEQUENCE ord_im_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('3-APR-2021', 'DD-MON-RR'), 'S1', 'E1', 69500000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('5-APR-2021', 'DD-MON-RR'), 'S2', 'E5', 100500000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('31-MAR-2021', 'DD-MON-RR'), 'S9', 'E3', 112000000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('28-APR-2021', 'DD-MON-RR'), 'S2', 'E15', 55600000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('26-MAY-2021', 'DD-MON-RR'), 'S3', 'E2', 7850000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('18-MAR-2021', 'DD-MON-RR'), 'S10', 'E7', 115500000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('7-MAR-2021', 'DD-MON-RR'), 'S6', 'E9', 6500000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('8-MAY-2021', 'DD-MON-RR'), 'S5', 'E10', 55700000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('31-MAR-2021', 'DD-MON-RR'), 'S4', 'E12', 2000000);
INSERT INTO ORDER_IMPORT VALUES ('OI' || ord_im_id.nextval, TO_DATE('26-MAY-2021', 'DD-MON-RR'), 'S2', 'E1', 86500000);