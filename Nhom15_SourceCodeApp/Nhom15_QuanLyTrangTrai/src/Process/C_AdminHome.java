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
            int checkTrans = rs.getInt("statusTrans");
            
            arrListOrder_Export.add(new Order_Export(id, dateOrdered, cusID, preTotal, disID, total, transID, checkTrans));
        }
        
        conn.close();
        return arrListOrder_Export;    
    }
    
    
    public static ArrayList<Order_Export> searchOrderExport(String maHD, String maKH, String trangThaiVC) 
                                                                                                                    throws SQLException, ClassNotFoundException {
        
        ArrayList<Order_Export> arrLInvoice = new ArrayList<>();
        ResultSet rs;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call search_Order_Export_forAll (?, ?, ?, ?)}");

        stmt.setString(1, maHD);
        stmt.setString(2, maKH);
        stmt.setString(3, trangThaiVC);
        stmt.registerOutParameter(4, java.sql.Types.REF_CURSOR);
        
        stmt.execute();
        rs = (ResultSet) stmt.getObject(4);
        
        while (rs.next()) {
            String ord_Ex_Num = rs.getString("ord_Ex_Num");
            Date dateOrdered = rs.getDate("dateOrdered");
            String transID = rs.getString("transID");
            String cusID = rs.getString("cusID");
            double preTotal = rs.getDouble("preTotal");
            String disID = rs.getString("disID");
            double total = rs.getDouble("orderTotal");
            int checkTrans = rs.getInt("statusTrans");
            
            arrLInvoice.add(new Order_Export(ord_Ex_Num, dateOrdered, cusID, preTotal, disID, total, transID, checkTrans));
        }
        conn.close();
        return arrLInvoice;
    }
    
}
