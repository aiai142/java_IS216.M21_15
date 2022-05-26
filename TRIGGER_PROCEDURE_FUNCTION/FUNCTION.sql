-- 1.  Thong ke tong doanh thu cua trang trai trong mot ngay
-- Ba tham so vao: ngay, thang, nam

CREATE OR REPLACE FUNCTION totalOfDay (ngay IN NUMBER, thang IN NUMBER, nam IN NUMBER)
RETURN NUMBER
AS
  total_of_day NUMBER;
BEGIN
    IF (thang < 1 AND thang > 12)
    THEN
        RAISE_APPLICATION_ERROR (-20399, 'Thang nhap vao khong hop le');
    END IF;
    
    IF (ngay < 1 AND ngay > 31)
    THEN
        RAISE_APPLICATION_ERROR (-20400, 'Ngay nhap vao khong hop le');
    END IF;
    
    SELECT SUM(OrderTotal) INTO total_of_day
    FROM ORDER_EXPORT
    WHERE EXTRACT(DAY FROM DateOrdered) = ngay
              AND EXTRACT(MONTH FROM DateOrdered) = thang
              AND EXTRACT(YEAR FROM DateOrdered) = nam;
    RETURN total_of_day;
    
    EXCEPTION
        WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE ('Loi khong xac dinh');
END;



-- 2. Thong ke tong doanh thu cua trang trai trong mot thang-nam nao do
-- Hai tham so vao: thang, nam

CREATE OR REPLACE FUNCTION totalOfMonth (thang IN NUMBER, nam IN NUMBER)
RETURN NUMBER
AS
  total_of_month NUMBER;
BEGIN
    IF (thang < 1 AND thang > 12)
    THEN
        RAISE_APPLICATION_ERROR (-20399, 'Thang nhap vao khong hop le');
    END IF;
    
    SELECT SUM(OrderTotal) INTO total_of_month
    FROM ORDER_EXPORT
    WHERE EXTRACT(MONTH FROM DateOrdered) = thang
                 AND EXTRACT(YEAR FROM DATEORDERED) = nam;
    RETURN total_of_month;
    
    EXCEPTION
        WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE ('Loi khong xac dinh');
    
END;



-- 3. Thong ke tong doanh thu cua trang trai trong mot nam
-- Tham so vao: nam

CREATE OR REPLACE FUNCTION totalOfYear (nam IN NUMBER ) 
RETURN NUMBER
AS
  total_of_year NUMBER;
BEGIN
    SELECT SUM(OrderTotal) INTO total_of_year
    FROM ORDER_EXPORT
    WHERE EXTRACT(YEAR FROM DateOrdered) = nam;
    RETURN total_of_year;
    
    EXCEPTION
        WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE ('Loi khong xac dinh');
    
END;



-- 4. Cho biet so khach hang dang ky thanh vien trong mot thang
-- Tham so vao: thang, nam
CREATE OR REPLACE FUNCTION Num_Cus_Register_In_Month (thang IN Number, nam IN number)
RETURN NUMBER
IS
    num_of_cus NUMBER;
BEGIN
    IF (thang < 1 AND thang > 12)
    THEN
        RAISE_APPLICATION_ERROR (-20399, 'Thang nhap vao khong hop le');
    END IF;
    
    SELECT COUNT(UserID) INTO num_of_cus
    FROM SYS_USER
    WHERE EXTRACT(MONTH FROM CreatedDate) = thang AND EXTRACT(YEAR FROM CreatedDate) = nam
            AND UserRole = 'UR3';
    
    RETURN num_of_cus;    
END;


-- 5. Kiem tra mot ma khuyen mai da duoc su dung hay chua
-- Tham so vao: ma khuyen mai (DisID)

CREATE OR REPLACE FUNCTION check_disid (dis_CODE IN DISCOUNT.DISCODE%type) return INT
IS
    dis_ID DISCOUNT.DISID%type;
BEGIN
    SELECT DISID into dis_ID
    FROM DISCOUNT ds
    where EXISTS (SELECT DISID
              FROM ORDER_EXPORT od join transport tran on od.transid=tran.transid
              where ds.disid = od.disid and ds.discode=dis_CODE and tran.STATUSTRANS='1');
    IF(dis_ID IS NOT NULL)
        THEN RETURN 1; 
    END IF;
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN 
            RETURN 0;
        WHEN OTHERS THEN 
            DBMS_OUTPUT.PUT_LINE('Loi khong xac dinh');
END;



-- 6. Thong ke so luong hang ton trong mot kho
-- Tham so vao: ma kho (StockID)

CREATE OR REPLACE FUNCTION Num_Pro_Inventory_In_One_Stock(stock_id IN INVENTORY_PRODUCT.StockID%TYPE)
RETURN NUMBER
IS
    num_of_pro NUMBER;
BEGIN
    SELECT SUM(Num_Inventory_Pro) INTO num_of_pro
    FROM INVENTORY_PRODUCT
    WHERE StockID = stock_id;
    
    RETURN num_of_pro;
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Kho khong ton tai');
        WHEN OTHERS THEN 
            DBMS_OUTPUT.PUT_LINE('Loi khong xac dinh');
END;
