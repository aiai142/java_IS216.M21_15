/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.*;
import Model.Order_Details_Ex;
import Model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DoQuynhChi
 */
public class C_Order_Details_Ex {
     
    public static void themCTHD(Order_Details_Ex ordDetailsEx) throws SQLException, ClassNotFoundException {
        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call add_Order_Details_Ex(?, ?, ?)}");
        stmt.setString(1, ordDetailsEx.getOrd_Ex_Num());
        stmt.setString(2, ordDetailsEx.getProID());
        stmt.setInt(3, ordDetailsEx.getNum_Products());

        stmt.execute();
        conn.close();
    }
     
     public static ArrayList<Product> timCTHD (String maHD) throws SQLException, ClassNotFoundException {
         ArrayList<Product> arrLProPurchase = new ArrayList<>();
         Connection conn = (Connection) ConnectionUtils.getMyConnection();
         CallableStatement stmt = conn.prepareCall("{call find_CTHD_of_HD(?, ?)}");
         stmt.setString(1, maHD);
         stmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);
         stmt.execute();
         ResultSet rs = (ResultSet) stmt.getObject(2);
         
         while (rs.next()) {
             Product pro = new Product(rs.getString("proName"), rs.getInt("proPrice"), rs.getInt("num_Products"));
             arrLProPurchase.add(pro);
         }
         
         conn.close();
         return arrLProPurchase;
     }
}
