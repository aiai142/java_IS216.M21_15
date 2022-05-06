-- Tao bang Farm
CREATE TABLE FARM
(
    FarmID varchar2(10) PRIMARY KEY,
    FarmName varchar2(20),
    FarmAdd varchar2(400)
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
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Trang trai khu A', '55 Dinh An, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Trang trai khu B', '26 Dinh An, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Trang trai khu C', '47 Dinh Thanh, Dau Tieng, Binh Duong');
INSERT INTO FARM VALUES ('F' || farm_id.nextval, 'Trang trai khu D', '11/4 Dinh Hiep, Dau Tieng, Binh Duong');
