/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.*;
import Model.Order_Details_Ex;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

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
}
     
}
