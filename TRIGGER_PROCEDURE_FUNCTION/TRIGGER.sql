-- 1. Ngay sinh cua nguoi dung (DateOfBirth) phai nho hon ngay tao tai khoan (CreatedDate)
CREATE OR REPLACE TRIGGER checkDateofBirth
BEFORE INSERT ON CUSTOMER
FOR EACH ROW
DECLARE
    created_date DATE;
BEGIN
    SELECT SYS_USER.CREATEDDATE INTO created_date
    FROM SYS_USER
    WHERE SYS_USER.USERID = :NEW.USERID;

    IF(:NEW.dateofbirth < date '1900-01-01' or :NEW.dateofbirth > created_date)
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngay sinh cua nguoi dung phai nho hon ngay tao tai khoan');
    END IF;
END;

-- 2. Tong tien tich luy mua hang (Accrued_Money), loai khach hang (CusType) cua mot khach hang
-- duoc tu dong cap nhat khi trang thai giao hang (StatusTrans) thanh cong
CREATE OR REPLACE TRIGGER TRG_update_transport
AFTER UPDATE ON TRANSPORT
FOR EACH ROW
DECLARE
    cus_id CUSTOMER.CusID%TYPE;
    value_ord ORDER_EXPORT.OrderTotal%TYPE;
    money_of_cus CUSTOMER.Accrued_money%TYPE;
    
BEGIN    
    IF (:NEW.StatusTrans <> :OLD.StatusTrans)
    THEN
            
        SELECT CusID, OrderTotal INTO cus_id, value_ord
        FROM ORDER_EXPORT
        WHERE TransID = :NEW.TransID;
    
        IF (:NEW.StatusTrans = 1)
        THEN
            UPDATE CUSTOMER SET Accrued_Money = Accrued_Money + value_ord
            WHERE CusID = cus_id;
            
            SELECT Accrued_Money INTO money_of_cus
            FROM CUSTOMER
            WHERE CusID = cus_id;
            
            IF (money_of_cus > 4000000 AND money_of_cus < 9000000)
            THEN
                UPDATE CUSTOMER SET CusType = 'Silver'
                WHERE CusID = cus_id;
            END IF;
            
            IF (money_of_cus > 9000000 AND money_of_cus < 20000000)
            THEN
                UPDATE CUSTOMER SET CusType = 'Gold'
                WHERE CusID = cus_id;
            END IF;
                    
            IF (money_of_cus >= 20000000)
            THEN
                UPDATE CUSTOMER SET CusType = 'Diamond'
                WHERE CusID = cus_id;
            END IF;
        END IF;
        
    END IF;
    
END;

-- 3. Gia tien hoa don mua hang phai bang tong tri gia cac nong san co trong hoa don
CREATE OR REPLACE TRIGGER TRG_Ins_Del_Upd_OrderDetails_Ex
AFTER INSERT OR DELETE OR UPDATE ON ORDERDETAILS_EX
FOR EACH ROW
DECLARE
    pro_price PRODUCT.ProPrice%TYPE;
    pre_total ORDER_EXPORT.PreTotal%TYPE;
    total ORDER_EXPORT.OrderTotal%TYPE;
BEGIN
    
     IF (INSERTING OR UPDATING)
     THEN
        SELECT ProPrice INTO pro_price
        FROM PRODUCT
        WHERE ProID = :NEW.ProID;
        
        SELECT PreTotal, OrderTotal INTO pre_total, total
        FROM ORDER_EXPORT 
        WHERE Ord_Ex_Num = :NEW.Ord_Ex_Num;
        
        UPDATE ORDER_EXPORT 
        SET PreTotal = pre_total + (:NEW.Num_Products * pro_price),
            OrderTotal = total + (:NEW.Num_Products * pro_price)
        WHERE Ord_Ex_Num = :NEW.Ord_Ex_Num;
    END IF;
    
    IF (DELETING) 
    THEN
        SELECT ProPrice INTO pro_price
        FROM PRODUCT
        WHERE ProID = :OLD.ProID;
        
        SELECT PreTotal, OrderTotal INTO pre_total, total
        FROM ORDER_EXPORT 
        WHERE Ord_Ex_Num = :OLD.Ord_Ex_Num;
        
        UPDATE ORDER_EXPORT 
        SET PreTotal = pre_total - (:OLD.Num_Products * pro_price),
            OrderTotal = total - (:OLD.Num_Products * pro_price)
        WHERE Ord_Ex_Num = :OLD.Ord_Ex_Num;
    END IF;
    
END;

-- 4. Ngay mua hang cua mot khach hang thanh vien phai lon hon ngay khach hang do dang ky thanh vien
CREATE OR REPLACE TRIGGER ins_up_ord_ex
BEFORE INSERT OR UPDATE ON ORDER_EXPORT
FOR EACH ROW
DECLARE
    create_date SYS_USER.CREATEDDATE%TYPE;
BEGIN     
    IF(INSERTING OR UPDATING)
        THEN 
            BEGIN
                SELECT CREATEDDATE INTO create_date
                FROM SYS_USER US JOIN CUSTOMER CUS ON US.USERID = CUS.USERID
                WHERE CUSID = :NEW.CUSID;
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        NULL;
            END;
            IF ((create_date - :NEW.DATEORDERED) > 0)
                THEN raise_application_error(-20987, 'Ngay mua hang phai lon hon ngay khach hang dang ky thanh vien');
            END IF;
    END IF;
END;

---------------------------
CREATE OR REPLACE TRIGGER up_user
BEFORE UPDATE ON SYS_USER
FOR EACH ROW
DECLARE
    create_date SYS_USER.CREATEDDATE%TYPE;
    order_date_min ORDER_EXPORT.DATEORDERED%TYPE;
    cus_id CUSTOMER.CUSID%TYPE;
BEGIN
    BEGIN
        SELECT CUSID INTO cus_id 
        FROM CUSTOMER
        WHERE USERID = :NEW.USERID;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
    END;
    BEGIN
        SELECT DATEORDERED INTO order_date_min
        FROM ORDER_EXPORT
        WHERE CUSID= cus_id
        ORDER BY DATEORDERED ASC
        FETCH FIRST 1 ROW ONLY;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
    END;
    IF((order_date_min - :NEW.CREATEDDATE) < 0)
        THEN raise_application_error(-20987, 'Ngay mua hang phai lon hon ngay khach hang dang ky thanh vien'); 
    END IF;
END;

-- 5. Kho ch?a nguyên v?t li?u là h?t gi?ng ch? có th? ch?a thêm giá ?? ho?c thùng x?p (PH??NG)
CREATE OR REPLACE TRIGGER trg_insert_re
BEFORE INSERT ON INVENTORY_RESOURCES
FOR EACH ROW
DECLARE 
    sto_id stock.STOCKID%type := NULL;
BEGIN
    BEGIN
        SELECT DISTINCT STOCKID INTO sto_id
        FROM INVENTORY_RESOURCES
        WHERE REID IN ('R4','R2','R3') AND STOCKID=:NEW.STOCKID;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
            NULL;
    END;
    IF((:NEW.REID <> 'R6') AND (:NEW.REID <> 'R9') AND (sto_id IS NOT NULL))
        THEN raise_application_error(-20987,'Kho chua nguyen vat lieu la hat giong chi co the chua them gia do hoac thung xop');
    END IF;
END;
