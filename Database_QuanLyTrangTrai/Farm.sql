-- Tao bang Farm
CREATE TABLE Farm
(
    farmID varchar2(10) PRIMARY KEY,
    farmName varchar2(20),
    farmAdd varchar2(400)
);


-- Insert du lieu
CREATE SEQUENCE farm_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Chi Farm', '55 Dinh An, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Linh Farm', '26 Dinh An, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'My Farm', '47 Dinh Thanh, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Phuong Farm', '11/4 Dinh Hiep, Dau Tieng, Binh Duong');
