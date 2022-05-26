-- 1. Hien thi danh sach cac loai nong san chua trong mot kho và so luong con lai trong kho
-- Tham so vao: ma kho (StockID)

CREATE OR REPLACE PROCEDURE ListProductInStock
   (makho IN stock.stockid%TYPE)
AS
    CURSOR DS IS
    SELECT i.PROID, p.PRONAME, i.NUM_INVENTORY_PRO 
    FROM INVENTORY_PRODUCT i JOIN PRODUCT p
    ON i.PROID = p.PROID
    WHERE i.STOCKID = makho;

    masp INVENTORY_PRODUCT.PROID%TYPE;
    tensp PRODUCT.PRONAME%TYPE;
    slton INVENTORY_PRODUCT.NUM_INVENTORY_PRO%TYPE; 
BEGIN
    DBMS_OUTPUT.PUT_LINE('ProID    ProName    Num_Inventory_Pro');
    DBMS_OUTPUT.PUT_LINE('-----    -------    -----------------');
    OPEN DS;
    LOOP
        FETCH DS INTO masp, tensp, slton;
        EXIT WHEN DS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(masp || '        ' || tensp || '        ' || slton);
    END LOOP;
    CLOSE DS;
END;



-- 2. Hien thi top 3 nong san ban chay nhat trong thang-nam
-- Hai ham so dau vao: thang, nam
-- Tham so ra: danh sach 3 nong san ban chay nhat trong thang va nam

CREATE OR REPLACE PROCEDURE Top_3_Products_Best_Seller (thang IN NUMBER, nam IN NUMBER)
IS
    pro_id PRODUCT.ProID%TYPE;
    pro_name PRODUCT.ProName%TYPE;
    pro_price PRODUCT.ProPrice%TYPE;
    farm_id FARM.FarmID%TYPE;
    sum_pro NUMBER;
    
    CURSOR cur_pro IS
    SELECT ode.ProID, ProName, ProPrice, FarmID, SUM(Num_Products)
    FROM ORDER_DETAILS_EX ode, ORDER_EXPORT oe, PRODUCT p
    WHERE ode.Ord_Ex_Num = oe.Ord_Ex_Num
        AND ode.ProID = p.ProID
        AND EXTRACT(MONTH FROM DateOrdered) = thang AND EXTRACT(YEAR FROM DateOrdered) = nam
    GROUP BY ode.ProID, ProName, ProPrice, FarmID
    ORDER BY SUM(Num_Products) DESC
    FETCH NEXT 3 ROWS WITH TIES;
    
BEGIN
    OPEN cur_pro;
    
    DBMS_OUTPUT.PUT_LINE('Top 3 nong san ban chay nhat trong thang ' || thang || '/' || nam);
    DBMS_OUTPUT.PUT_LINE('ProID' || '   ' || 'ProName' || '     ' || 'ProPrice' || '     ' || 'FarmID' || '     ' || 'Quantity');
    DBMS_OUTPUT.PUT_LINE('-----' || '   ' || '-------' || '     ' || '--------' || '     ' || '------' || '     ' || '--------');
    
    LOOP
        FETCH cur_pro INTO pro_id, pro_name, pro_price, farm_id, sum_pro;
        EXIT WHEN cur_pro%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(pro_id || '   ' || pro_name || '     ' || pro_price || '     ' || farm_id || '     ' || sum_pro);
    END LOOP;
    
    CLOSE cur_pro;  
END;



-- 3. Hien thi thong tin khach hang khi nhap vao mot ma khach hang (CusID). 
-- Neu khong co thong tin hien thi phai thong bao cho nguoi dung biet
-- 1 tham so vao: CusID
CREATE OR REPLACE PROCEDURE display_info_cus(cus_id customer.cusid%TYPE)
IS
    info CUSTOMER%ROWTYPE;
BEGIN
    SELECT * INTO info
    FROM CUSTOMER
    WHERE CUSID = cus_id;
    DBMS_OUTPUT.PUT_LINE('Thong tin cua khach hang co ma ' || cus_id ||'la: ');
    DBMS_OUTPUT.PUT_LINE('Name: '||info.cusname || ' Gender: ' || info.gender || ' Dateofbirth: ' || info.Dateofbirth ||
                         ' Address: '||info.cusadd || ' Phone number: ' || info.cusphone || ' Email: ' || info.cusemail ||
                         ' Type: ' || info.custype || ' Accrued money: ' || info.accrued_money);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Khong ton tai khach hang nay');
END;



-- 4. Hien thi thong tin khach hang co tong tien mua hang nhieu nhat trong mot nam

CREATE OR REPLACE PROCEDURE top1_money(year_order in number)
IS
    cus_id customer.cusid%TYPE;
    cus_name customer.cusname%TYPE;
    cus_gender customer.gender%TYPE;
    cus_phone customer.cusphone%TYPE;
    cus_email customer.cusemail%TYPE;
    cus_money customer.accrued_money%TYPE;
    sum_top_1 number;
    
    CURSOR cur_info IS   
    SELECT CUS.CUSID, CUS.CUSNAME, CUS.GENDER, CUS.CUSPHONE, CUS.CUSEMAIL,SUM(ORDERTOTAL) SUM_ORDER
    FROM (CUSTOMER CUS JOIN ORDER_EXPORT OD ON CUS.CUSID = OD.CUSID )JOIN TRANSPORT TP ON TP.TRANSID = OD.TRANSID
    WHERE EXTRACT(YEAR FROM DATEORDERED)=year_order AND TP.STATUSTRANS=1
    GROUP BY CUS.CUSID, CUS.CUSNAME, CUS.GENDER, CUS.CUSPHONE, CUS.CUSEMAIL, CUS.ACCRUED_MONEY
    ORDER BY SUM_ORDER DESC
    FETCH NEXT 1 ROW WITH TIES;
BEGIN
    OPEN cur_info;
    DBMS_OUTPUT.PUT_LINE('Thong tin cac khach hang mua hang nhieu nhat nam ' || year_order ||' la: ');
    DBMS_OUTPUT.PUT_LINE('ID          NAME                   GENDER         PHONE               EMAIL                SUM MONEY OF YEAR' );
    LOOP
        FETCH cur_info INTO cus_id,cus_name,cus_gender,cus_phone,cus_email,sum_top_1;
        EXIT WHEN cur_info%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(cus_id||'          '||cus_name||'          '||cus_gender||'          '||cus_phone||'          '||cus_email||'                 '||sum_top_1);
    END LOOP;
    CLOSE cur_info;
END;



-- 5. Hien thi danh sach cac khach hang mua hang trong mot ngay
-- 1 tham so vao: ngay

CREATE OR REPLACE PROCEDURE Cus_OrderOfTheDay (ngay IN NUMBER, thang IN NUMBER, nam IN NUMBER)
AS
    CURSOR DS IS
    SELECT c.CUSID, CUSNAME, GENDER, DATEOFBIRTH, CUSADD, CUSPHONE, CUSEMAIL, ACCRUED_MONEY, USERID
    FROM CUSTOMER c ,ORDER_EXPORT ore 
    WHERE c. cusid= ore.cusid
        AND EXTRACT( DAY FROM DATEORDERED )=ngay
        AND EXTRACT( MONTH FROM DATEORDERED) = thang
        AND EXTRACT( YEAR FROM DATEORDERED)=nam;
    
    makh CUSTOMER.CUSID%TYPE;
    tenkh CUSTOMER.CUSNAME%TYPE;
    gioitinh CUSTOMER.GENDER%TYPE;
    ngaysinh CUSTOMER.DATEOFBIRTH%TYPE;
    diachi CUSTOMER.CUSADD%TYPE;
    sodt CUSTOMER.CUSPHONE%TYPE;
    email CUSTOMER.CUSEMAIL%TYPE;
    tientichluy CUSTOMER.ACCRUED_MONEY%TYPE;
    idnguoidung CUSTOMER.USERID%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE(' Danh sach khach hang dat hang trong ngay ' || ngay || '/' || thang || '/' || nam);
    DBMS_OUTPUT.PUT_LINE('CUSID    CUSNAME  GENDER  DATEOFBIRTH                      CUSADD                       CUSPHONE   CUSEMAIL    ACCRUED_MONEY    USERID');
    DBMS_OUTPUT.PUT_LINE('-----          -------            ------         -----------                               ------                                --------         --------               -------------                ------');
    OPEN DS;
    LOOP
    FETCH DS INTO makh, tenkh, gioitinh, ngaysinh, diachi, sodt, email, tientichluy, idnguoidung;
    EXIT WHEN DS%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(makh || '    ' || tenkh || '     ' || gioitinh || '      '|| ngaysinh
    || '          ' || diachi || '   ' || sodt || '   ' || email || '    ' || tientichluy || '    ' || idnguoidung );
    END LOOP;
    CLOSE DS;
    
END;



-- 6. Hien thi danh sach thong tin khach hang dua tren loai khach hang nhap vao
-- 1 tham so vao: loai khach hang (CusType)

CREATE OR REPLACE PROCEDURE CustomerInfo (loaikh IN CUSTOMER.CUSTYPE%TYPE)
AS
    CURSOR DS IS
    SELECT CUSID, CUSNAME, GENDER, DATEOFBIRTH, CUSADD, CUSPHONE, CUSEMAIL, ACCRUED_MONEY, USERID
    FROM CUSTOMER
    WHERE CUSTYPE = loaikh;
    
    makh CUSTOMER.CUSID%TYPE;
    tenkh CUSTOMER.CUSNAME%TYPE;
    gioitinh CUSTOMER.GENDER%TYPE;
    ngaysinh CUSTOMER.DATEOFBIRTH%TYPE;
    diachi CUSTOMER.CUSADD%TYPE;
    sodt CUSTOMER.CUSPHONE%TYPE;
    email CUSTOMER.CUSEMAIL%TYPE;
    tientichluy CUSTOMER.ACCRUED_MONEY%TYPE;
    idnguoidung CUSTOMER.USERID%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('CUSID    CUSNAME        GENDER    DATEOFBIRTH    CUSADD    CUSPHONE    CUSEMAIL    ACCRUED_MONEY    USERID');
    DBMS_OUTPUT.PUT_LINE('-----    -------        ------    -----------    ------    --------    --------    -------------    ------');
    OPEN DS;
    LOOP
    FETCH DS INTO makh, tenkh, gioitinh, ngaysinh, diachi, sodt, email, tientichluy, idnguoidung;
    EXIT WHEN DS%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(makh || '    ' || tenkh || '        ' || gioitinh || '    ' || ngaysinh
    || '    ' || diachi || '    ' || sodt || '    ' || email || '    ' || tientichluy || '    ' || idnguoidung );
    END LOOP;
    CLOSE DS;
END;

-- =============================================================
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE Them_MaKH (makh OUT varchar)
IS
BEGIN
    makh := 'KH' || cus_id.nextval;
END;

/
DECLARE 
    ma_kh customer.cusid%TYPE;
BEGIN
    Them_MaKH(ma_kh);
    DBMS_OUTPUT.PUT_LINE(ma_kh);
END;
