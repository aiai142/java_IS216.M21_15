----------- Tao bang OrderDetails_Im
CREATE TABLE ORDERDETAILS_IM
(
    Ord_Im_Num varchar2(10) not null,
    ReID varchar2(10) not null,
    Num_Resources number,
    
    CONSTRAINT PK_CTHD_IM PRIMARY KEY(Ord_Im_Num, ReID)
);
-- Rang buoc khoa ngoai
ALTER TABLE ORDERDETAILS_IM ADD CONSTRAINT FK01_CTHD_IM FOREIGN KEY(Ord_Im_Num) REFERENCES ORDER_IMPORT(Ord_Im_Num);
ALTER TABLE ORDERDETAILS_IM ADD CONSTRAINT FK02_CTHD_IM FOREIGN KEY(ReID) REFERENCES RESOURCES(ReID);


------------- Insert du lieu

INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R1', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R3', 250);
INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R6', 300);
INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R8', 450);
INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R9', 300);
INSERT INTO ORDERDETAILS_IM VALUES ('OI1', 'R10', 200);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI2', 'R2', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI2', 'R3', 250);
INSERT INTO ORDERDETAILS_IM VALUES ('OI2', 'R7', 360);
INSERT INTO ORDERDETAILS_IM VALUES ('OI2', 'R9', 100);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI3', 'R4', 500);
INSERT INTO ORDERDETAILS_IM VALUES ('OI3', 'R7', 350);
INSERT INTO ORDERDETAILS_IM VALUES ('OI3', 'R9', 150);
INSERT INTO ORDERDETAILS_IM VALUES ('OI3', 'R10', 250);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI4', 'R1', 360);
INSERT INTO ORDERDETAILS_IM VALUES ('OI4', 'R4', 200);
INSERT INTO ORDERDETAILS_IM VALUES ('OI4', 'R5', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI4', 'R6', 150);
INSERT INTO ORDERDETAILS_IM VALUES ('OI4', 'R8', 120);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI5', 'R2', 150);
INSERT INTO ORDERDETAILS_IM VALUES ('OI5', 'R3', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI5', 'R5', 50);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R1', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R5', 200);
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R7', 300);
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R8', 250);
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R9', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI6', 'R10', 40);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI7', 'R2', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI7', 'R4', 200);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI8', 'R5', 100);
INSERT INTO ORDERDETAILS_IM VALUES ('OI8', 'R7', 150);
INSERT INTO ORDERDETAILS_IM VALUES ('OI8', 'R8', 200);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI9', 'R6', 100);
	
INSERT INTO ORDERDETAILS_IM VALUES ('OI10', 'R7', 300);
INSERT INTO ORDERDETAILS_IM VALUES ('OI10', 'R9', 250);
INSERT INTO ORDERDETAILS_IM VALUES ('OI10', 'R10', 100);
