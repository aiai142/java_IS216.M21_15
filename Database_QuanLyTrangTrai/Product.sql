-- Tao bang Product
CREATE TABLE Product
(
    proID varchar2(10) PRIMARY KEY,
    proName varchar2(40),
    proPrice number(11,2),
    farmID varchar2(10), 
    proType varchar2(50),
    image varchar2(100),
    englishName varchar2(40)
);
-- Khoa ngoai
ALTER TABLE Product ADD CONSTRAINT FK_Pro FOREIGN KEY(farmID) REFERENCES Farm(farmID);


-- Insert du lieu
CREATE SEQUENCE product_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/ 
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Nho', 180000, 'F1', 'Trai cay', 'nho.jpg', 'Grapes');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Buoi', 40000, 'F1', 'Trai cay', 'buoi.jpg', 'Grapefruit');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Chanh', 30000, 'F1', 'Trai cay', 'chanh.jpg', 'Lemon');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Cam', 40000, 'F1', 'Trai cay', 'cam.jpg', 'Orange');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Vai', 65000, 'F1', 'Trai cay', 'vai.jpg', 'Lychee');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Nhan', 70000, 'F2', 'Trai cay', 'nhan.jpg', 'Chicken');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Xoai', 50000, 'F2', 'Trai cay', 'xoai.jpg', 'Mango');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Tao', 95000, 'F2', 'Trai cay', 'tao.jpg', 'Apple');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Bi xanh', 32000, 'F2', 'Cu qua', 'bixanh.jpg', 'Zucchini');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Bi do', 33000, 'F2', 'Cu qua', 'bido.jpg', 'Pumpkin');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Su su', 26000, 'F3', 'Cu qua', 'susu.jpg', 'Kohlrabi');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Ca rot', 28000, 'F3', 'Cu qua', 'carot.jpg', 'Carrot');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Ca tim', 24000, 'F3', 'Cu qua', 'catim.jpg', 'Eggplant');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Khoai tay', 24000, 'F3', 'Cu qua', 'khoaitay.jpg', 'Potato');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Khoai lang', 28000, 'F3', 'Cu qua', 'khoailang.jpg', 'Sweet Potato');

INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Xa lach', 24000, 'F4', 'Rau', 'xalach.jpg', 'Lettuce');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Rau cai', 25000, 'F4', 'Rau', 'raucai.jpg', 'Mustard Greens');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Rau muong', 21000, 'F4', 'Rau', 'raumuong.jpg', 'Water Spinach');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Hanh la', 30000, 'F4', 'Rau', 'hanhla.jpg', 'Green Onion');
INSERT INTO PRODUCT VALUES ('P' || product_id.nextval, 'Dua leo', 22000, 'F4', 'Cu qua', 'dualeo.jpg', 'Cucumber');

