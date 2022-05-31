/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.*;
import Model.Discount;
import Model.Order_Export;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DoQuynhChi
 */
public class C_NewOrderExport {
    private static ArrayList<Discount> arrLDisCode = new ArrayList<>();
    private static ArrayList<String> codeValidate = new ArrayList<>();
    
    /*
    * Tim ten va dia chi khach hang thanh vien khi nhap vao so dien thoai
    */
    public static String[] timKH(String sdt) throws ClassNotFoundException, SQLException {
        
            String[] cus_info = new String[3];

            Connection conn = (Connection) ConnectionUtils.getMyConnection();
            CallableStatement stmt = conn.prepareCall("{call find_Cus(?, ?, ?, ?)}");
            stmt.setString(1, sdt);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.execute();
            
            cus_info[0] = stmt.getString(2);
            cus_info[1] = stmt.getString(3);
            cus_info[2] = stmt.getString(4);
            
            return cus_info;
    }
    
    
    /*
    * Ham lay ma code khuyen mai dua theo loai khach hang va ngay mua hang
    */
    public static ArrayList<String> getDisCode(String sdt, LocalDate ngayHD) throws ClassNotFoundException, SQLException {
        
        ArrayList<Discount> arrLDisCode = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yy");
        String ngayDatHang = LocalDate.now().format(dtf);
        LocalDate ld = LocalDate.parse(ngayDatHang, dtf);
        Date dateOrdered = Date.valueOf(ld);

        /*
        * Lay cac ma code khuyen mai ap dung cho loai khach hang cua khach hang nay
         */
        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt1 = conn.prepareCall("{call find_disCode_cusType(?, ?)}");
        stmt1.setString(1, sdt);
        stmt1.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt1.execute();

        ResultSet rs = (ResultSet) stmt1.getObject(2);

        while (rs.next()) {
            String disCode = rs.getString("disCode");
            double value = rs.getDouble("value");
            Date startDate = rs.getDate("startDate");
            Date endDate = rs.getDate("endDate");
            arrLDisCode.add(new Discount(disCode, value, startDate, endDate));
        }

        /*
        * Kiem tra ma khuyen mai da duoc su dung hay chua
         */
        for (Discount x : arrLDisCode) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= check_discount_used(?)}");
            stmt2.setString(2, x.getDisCode());
            stmt2.registerOutParameter(1, java.sql.Types.INTEGER);
            stmt2.execute();

            //Neu ma chua duoc su dung thi kiem tra ngay ap dung
            if (stmt2.getInt(1) == 0) {
                if (x.getStartDate().compareTo(dateOrdered) <= 0 && x.getEndDate().compareTo(dateOrdered) >= 0) {
                    codeValidate.add(x.getDisCode());
                }
            }
        }

        return codeValidate;
    }
    
    
    /*
    * Lay gia tri khuyen mai cua code khuyen mai nhap vao
    */
    public static double timGiaTriKhuyenMai (String disCode) throws SQLException, ClassNotFoundException {
        
        double triGia;
        
        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call ?:= getValueDiscount(?)}");
        stmt.setString(2, disCode);
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.DOUBLE);      
        stmt.execute();
        triGia = stmt.getDouble(1);
     
        return triGia;
    }
    
    public static String layDisID(String disCode) throws SQLException, ClassNotFoundException {
        
        String disID;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call get_DisID(?, ?)}");
        stmt.setString(1, disCode);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.execute();
        disID = stmt.getString(2);
        
        return disID;
    }

    
    /*
    * Tao mot ma van chuyen khi nguoi dung click vao checkboxVanchuyen
    */
     public static String taoMaVanChuyen () throws SQLException, ClassNotFoundException {
         
        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call Increase_TransID(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);      
        stmt.execute();
        String maVanChuyen = stmt.getString(1);
        
        return maVanChuyen;
    }
    
     /*
     * Ham them vao mot khach mua hang vang lai (ten, so dien thoai) va tra ve ma khach hang tu tao cua khach hang nay
     */
     public static String layMaKH(String tenKH, String sdt) throws ClassNotFoundException, SQLException{
         String cusID;
         Connection conn = (Connection) ConnectionUtils.getMyConnection();
         CallableStatement stmt = conn.prepareCall("{call add_new_cus_not_member(?, ?, ?)}");
         stmt.setString(2, tenKH);
         stmt.setString(3, sdt);
         stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
         stmt.executeUpdate();

         cusID = stmt.getString(1);

         return cusID;
    }
     
     public static String themOrderExport(Order_Export ord) throws SQLException, ClassNotFoundException {

        int check = 0;
        Connection conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call add_new_order(?, ?, ?, ?, ?, ?, ?)}");
        stmt.registerOutParameter(1, java.sql.Types.VARCHAR);

        stmt.setDate(2, ord.getDateOrdered());
        stmt.setString(3, ord.getTransID());
        stmt.setString(4, ord.getCusID());
        stmt.setDouble(5, ord.getPreTotal());
        stmt.setString(6, ord.getDisID());
        stmt.setDouble(7, ord.getOrderTotal());

        stmt.execute();
        return stmt.getString(1);
    }
     
}

