-- Tao bang Order_Export
CREATE TABLE Order_Export
(
    ord_Ex_Num varchar2(10) PRIMARY KEY,
    dateOrdered date,
    transID varchar2(10),
    cusID varchar2(10),
    preTotal number(11,2),
    disID varchar2(10),
    orderTotal number(11,2),
    deli_Address varchar2(400), 
    payBy varchar2(100),
    
	CONSTRAINT FK01_ORD_EX FOREIGN KEY(transID) REFERENCES Transport(transID),
	CONSTRAINT FK02_ORD_EX FOREIGN KEY(cusID) REFERENCES Customer(cuSID),
	CONSTRAINT FK03_ORD_EX FOREIGN KEY(disID) REFERENCES Discount(disID)
);
ALTER TABLE Order_Export ADD CONSTRAINT CHK_PAYBY CHECK (payBy IN ('Tien mat', 'The Visa', 'The ngan hang'));


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
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('3-APR-2022', 'DD-MON-RR'), 'T1', 'C1', 8420000, NULL, 8420000, '221 Tran Hung Dao, Q5, Tp.HCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('5-APR-2022', 'DD-MON-RR'), 'T2', 'C2', 2560000, NULL, 2560000, '50/34 Le Dai Hanh, Q10, TpHCM', 'The Visa');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T3', 'C3', 5380000, NULL, 5380000, '284 Do Xuan Hop, Q9, TpHCM', 'The ngan hang');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('28-APR-2022', 'DD-MON-RR'), 'T4', 'C1', 2150000, 'D33', 2064000, '221 Tran Hung Dao, Q5, Tp.HCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('26-MAY-2022', 'DD-MON-RR'), 'T5', 'C4', 3300000, NULL, 3300000, '93 Man Thien, Q9, TpHCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('18-MAR-2022', 'DD-MON-RR'), 'T6', 'C7', 9190000, NULL, 91900000, '24b Ho Thi Ky, Q10, TpHCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('7-MAR-2022', 'DD-MON-RR'), 'T7', 'C8', 7260000, NULL, 7260000, '23/14 Tan Chanh Hiep, Q12,TpHCM', 'The Visa');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('8-MAY-2022', 'DD-MON-RR'), 'T8', 'C5', 10300000, NULL, 10300000, '2A Binh Chieu, Q.Thu Duc, TpHCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T9', 'C13', 10400000, NULL, 10400000, '59 Tran Nhat Duat, Q1, TpHCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('26-MAY-2022', 'DD-MON-RR'), 'T10', 'C4', 8240000, NULL, 8240000, '93 Man Thien, Q9, TpHCM', 'The Visa');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T11', 'C9', 4030000, NULL, 4030000, '16A Le Van Duyet, Q. Binh Thanh, TpHCM', 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('21-APR-2021', 'DD-MON-RR'), 'T12', 'C8', 1100000, NULL, 1100000, '23/14 Tan Chanh Hiep, Q12,TpHCM', 'The ngan hang');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('31-MAR-2022', 'DD-MON-RR'), 'T13', 'C4', 9360000, 'D37', 9079200, '93 Man Thien, Q9, TpHCM', 'The ngan hang');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('17-NOV-2022', 'DD-MON-RR'), NULL, 'C15', 1400000, NULL, 1400000, NULL, 'Tien mat');
INSERT INTO ORDER_EXPORT VALUES ('OE' || ord_ex_id.nextval, TO_DATE('14-FEB-2022', 'DD-MON-RR'), NULL, 'C16', 19750000, NULL, 19750000, NULL, 'The Visa');

