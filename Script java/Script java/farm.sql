--. tao bang FARM
CREATE TABLE FARM (
    FARMID            VARCHAR2(10) NOT NULL,
    FARMNAME      VARCHAR2(20),
    FARMADD         VARCHAR2(400),
    PRIMARY KEY (FARMID)
); 

-- INSERT
INSERT INTO FARM (FARMID, FARMNAME, FARMADD) VALUES ('1','	FreshFood-V1','152, Duong 6, P. Phu Huu, Q9, TpHCM');
INSERT INTO FARM (FARMID, FARMNAME, FARMADD) VALUES ('2','	FreshFood-V2'	,'1407 Phan Van Tri, Q. Go Vap, TpHCM');
INSERT INTO FARM (FARMID, FARMNAME, FARMADD) VALUES ('3',	'FreshFood-V3','	34/36 Hoang Ngoc Phach, Q. Tan Phu, TpHCM');
INSERT INTO FARM (FARMID, FARMNAME, FARMADD) VALUES ('4',	'FreshFood-V4','	36 Thanh Loc, Q12, TpHCM');