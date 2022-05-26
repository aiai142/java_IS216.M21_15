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

    IF(:NEW.dateOfBirth < date '1900-01-01' or :NEW.dateOfBirth > created_date)
    THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngay sinh cua nguoi dung phai nho hon ngay tao tai khoan');
    END IF;
END;


-- ===============================================================
-- 2. Tong tien tich luy mua hang (accrued_Money) và loai khách hàng (cusType) duoc cap nhat 
-- khi trang thai giao hang (statusTrans) thay doi

CREATE OR REPLACE TRIGGER TRG_update_transport
AFTER UPDATE ON TRANSPORT
FOR EACH ROW
DECLARE
    cus_id CUSTOMER.cusID%TYPE;
    money_of_cus CUSTOMER.accrued_Money%TYPE;
    userNum CUSTOMER.cusType%TYPE;
    value_ord ORDER_EXPORT.orderTotal%TYPE;
    
BEGIN    
    SELECT c.CusID, c.accrued_money, c.userID, OrderTotal INTO cus_id, money_of_cus, userNum, value_ord
    FROM ORDER_EXPORT oe JOIN CUSTOMER c ON oe.CusID = c.CusID
    WHERE TransID = :NEW.TransID;

    IF (:OLD.StatusTrans IS NULL)
    THEN 
        IF (:NEW.StatusTrans = 1)
        THEN
            money_of_cus := money_of_cus + value_ord;
            
            UPDATE CUSTOMER SET Accrued_Money = money_of_cus
            WHERE CusID = cus_id;
        END IF;
    END IF;
        
    IF (:NEW.StatusTrans <> :OLD.StatusTrans)
    THEN
        IF (:NEW.StatusTrans = 1)
        THEN
            money_of_cus := money_of_cus + value_ord;
            
            UPDATE CUSTOMER SET Accrued_Money = money_of_cus
            WHERE CusID = cus_id;
        END IF;
        
        IF (:NEW.StatusTrans = 0)
        THEN
            money_of_cus := money_of_cus - value_ord;
            
            UPDATE CUSTOMER SET Accrued_Money = money_of_cus
            WHERE CusID = cus_id;
        END IF;
    END IF;
    
-- Cap nhat loai khach hang cho khach hang co dang ky thanh vien
    IF (userNum IS NOT NULL)
    THEN
        
        IF (money_of_cus > 4000000 AND money_of_cus < 9000000) THEN
            UPDATE CUSTOMER SET CusType = 'Silver'
            WHERE CusID = cus_id;
            
        ELSIF (money_of_cus > 9000000 AND money_of_cus < 20000000) THEN
            UPDATE CUSTOMER SET CusType = 'Gold'
            WHERE CusID = cus_id;
                    
        ELSIF (money_of_cus >= 20000000) THEN
            UPDATE CUSTOMER SET CusType = 'Diamond'
            WHERE CusID = cus_id;
            
        ELSE --IF (money_of_cus > 0 AND money_of_cus < 4000000) THEN
            UPDATE CUSTOMER SET CusType = NULL
            WHERE CusID = cus_id;
        END IF;
        
    END IF;
END;




---- 3. Gia tien hoa don mua hang phai bang tong tri gia cac nong san co trong hoa don

CREATE OR REPLACE TRIGGER TRG_Ins_Del_Upd_OrderDetails_Ex
AFTER INSERT OR DELETE OR UPDATE ON ORDER_DETAILS_EX
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




---- 4. Kho chua nguyen vat lieu là hat giong chi có the chua thêm gia do cay hoac thung xop

CREATE OR REPLACE TRIGGER trg_insert_resources
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




---- 5. Tong tien tich luy mua hang cua mot khach hang 
---- phai bang tong tien cac hoa don duoc giao hang thanh cong cho khach hang nay

-- ============================================== TRIGGER INSERT
CREATE OR REPLACE TRIGGER TRG_ins_ORDEREXPORT_NoTransport
AFTER INSERT ON ORDER_EXPORT
FOR EACH ROW
DECLARE
    sum_purchased number;
BEGIN
    IF (:NEW.TransID IS NULL)
    THEN        
        SELECT Accrued_Money INTO sum_purchased
        FROM CUSTOMER
        WHERE CusID = :New.CusID;
        
        UPDATE CUSTOMER SET Accrued_Money = sum_purchased + :NEW.PreTotal WHERE CusID = :NEW.CusID;
        
    END IF;
END;

-- ============================================== TRIGGER UPDATE
CREATE OR REPLACE TRIGGER TRG_upd_PreTotal
AFTER UPDATE OF OrderTotal ON ORDER_EXPORT
FOR EACH ROW
DECLARE
    sum_purchased number;
BEGIN
    SELECT Accrued_Money INTO sum_purchased
    FROM CUSTOMER
    WHERE CusID = :New.CusID;
    
    UPDATE CUSTOMER SET Accrued_Money = sum_purchased - :OLD.OrderTotal + :NEW.OrderTotal WHERE CusID = :NEW.CusID;
    
END;
/

-- ============================================== TRIGGER DELETE
CREATE OR REPLACE TRIGGER TRG_del_PreTotal
AFTER DELETE ON ORDER_EXPORT
FOR EACH ROW
DECLARE
    sum_purchased number;
BEGIN
    SELECT Accrued_Money INTO sum_purchased
    FROM CUSTOMER
    WHERE CusID = :OLD.CusID;
    
    UPDATE CUSTOMER SET Accrued_Money = sum_purchased - :OLD.OrderTotal WHERE CusID = :OLD.CusID;
    
END;
