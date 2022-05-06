-- Tao bang Order_Export
CREATE TABLE ORDER_EXPORT
(
    Ord_Ex_Num varchar2(10) PRIMARY KEY,
    DateOrdered date,
    TransID varchar2(10),
    CusID varchar2(10),
    PreTotal number(11,2),
    DisID varchar2(10),
    OrderTotal number(11,2),
    
    CONSTRAINT FK01_ORD_EX FOREIGN KEY(TransID) REFERENCES TRANSPORT(TransID),
    CONSTRAINT FK02_ORD_EX FOREIGN KEY(CusID) REFERENCES CUSTOMER(CuSID),
    CONSTRAINT FK03_ORD_EX FOREIGN KEY(DisID) REFERENCES DISCOUNT(DisID)
);

-- Insert du lieu
CREATE SEQUENCE ord_ex_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('3-APR-2022', 'DD-MON-RR'), 'T1', 'C1', 8420000, NULL, 8420000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('5-APR-2022', 'DD-MON-RR'), 'T2', 'C2', 2560000, NULL, 2560000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T3', 'C3', 5380000, NULL, 5380000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('28-APR-2022', 'DD-MON-RR'), 'T4', 'C1', 2150000, 'D33', 2064000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('26-MAY-2022', 'DD-MON-RR'), 'T5', 'C4', 3300000, NULL, 3300000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('18-MAR-2022', 'DD-MON-RR'), 'T6', 'C7', 9190000, NULL, 91900000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('7-MAR-2022', 'DD-MON-RR'), 'T7', 'C8', 7260000, NULL, 7260000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('8-MAY-2022', 'DD-MON-RR'), 'T8', 'C5', 10300000, NULL, 10300000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T9', 'C13', 10400000, NULL, 10400000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('26-MAY-2022', 'DD-MON-RR'), 'T10', 'C4', 8240000, NULL, 8240000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T11', 'C9', 4030000, NULL, 4030000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('21-APR-2021', 'DD-MON-RR'), 'T12', 'C8', 1100000, NULL, 1100000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T13', 'C4', 9360000, 'D37', 9079200);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('17-NOV-2022', 'DD-MON-RR'), NULL, 'C15', 1400000, NULL, 1400000);
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('14-FEB-2022', 'DD-MON-RR'), NULL, 'C16', 19750000, NULL, 19750000);