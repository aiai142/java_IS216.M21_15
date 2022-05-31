/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Order_Export;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DoQuynhChi
 */
public class C_AdminHome {
    public static ArrayList<Order_Export> listOrder_Export() throws SQLException, ClassNotFoundException {
        
        ArrayList<Order_Export> arrListOrder_Export = new ArrayList<>();
        ResultSet rs;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call load_order_export_transport(?)}");
        stmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);
        stmt.execute();

        rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            String id = rs.getString("ord_Ex_Num");
            Date dateOrdered = rs.getDate("dateOrdered");
            String transID = rs.getString("transID");
            String cusID = rs.getString("cusID");
            double preTotal = rs.getDouble("preTotal");
            String disID = rs.getString("disID");
            double total = rs.getDouble("orderTotal");
            String checkTrans = rs.getString("statusTrans");

            if (rs.getString("statusTrans") == null) {
                checkTrans = "";
            }

            if (transID != null) {
                if (checkTrans.equals("1")) {
                    checkTrans = "Thành công";
                } else if (checkTrans.equals("0")) {
                    checkTrans = "Thất bại";
                } else {
                    checkTrans = "";
                }
            }
            arrListOrder_Export.add(new Order_Export(id, dateOrdered, cusID, preTotal, disID, total, transID, checkTrans));
        }

    return arrListOrder_Export ;
    
    }
    
    public static ArrayList<Order_Export> timKiemHoaDon(String maHD) throws SQLException, ClassNotFoundException {
        
        ArrayList<Order_Export> arrLInvoice = new ArrayList<>();
        ResultSet rs;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call find_order_export (?, ?)}");

        stmt.setString(1, maHD);
        stmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);
        stmt.execute();

        rs = (ResultSet) stmt.getObject(2);
        while (rs.next()) {
            String ord_Ex_Num = rs.getString("ord_Ex_Num");
            Date dateOrdered = rs.getDate("dateOrdered");
            String transID = rs.getString("transID");
            String cusID = rs.getString("cusID");
            double preTotal = rs.getDouble("preTotal");
            String disID = rs.getString("disID");
            double total = rs.getDouble("orderTotal");
            String checkTrans = rs.getString("statusTrans");

            // Chuyen doi kieu du lieu 0, 1 va null  cua DB thanh kieu chuoi de hieu
            if (transID != null) {
                    if (checkTrans.equals("1")) {
                        checkTrans = "Thành công";
                    } else if (checkTrans.equals("0")) {
                        checkTrans = "Thất bại";
                    } else {
                        checkTrans = "";
                    }
            } else 
                checkTrans = "";
            

            arrLInvoice.add(new Order_Export(ord_Ex_Num, dateOrdered, cusID, preTotal, disID, total, transID, checkTrans));
        }

        return arrLInvoice;
    }
    
}
