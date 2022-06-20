-- Tao bang Feedback_Cus
CREATE TABLE Feedback_Cus
(
    fbID varchar2(10) PRIMARY KEY,
    nameCus varchar2(50),
    emailCus varchar2(100),
    message varchar2(1000),
    fb_Image varchar2(100),
    dateSend Date    
);


-- Insert du lieu
CREATE SEQUENCE  FB_ID
MINVALUE 1 
MAXVALUE 9999999 
INCREMENT BY 1 
START WITH 1 
NOCACHE  
ORDER  
NOCYCLE;
