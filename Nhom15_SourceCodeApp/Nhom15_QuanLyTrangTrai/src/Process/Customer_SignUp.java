/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionOracle;
import Model.Customer;

import Model.Sys_User;
import java.sql.SQLException;

import java.sql.CallableStatement;

import java.sql.*;
import ConnectDB.ConnectionUtils;
import java.util.logging.Level;

/**
 *
 * @author pem41
 */
public class Customer_SignUp {

    

    public static boolean addUser(Sys_User tk) throws SQLException, ClassNotFoundException {

        boolean i = true;
        Connection conn = null;
       
        try {

            conn = ConnectionUtils.getMyConnection();

            CallableStatement stmt = conn.prepareCall("{CALL insertUSER(?,?,?,?,?)}");

            stmt.setString(1, tk.getUserID());
            stmt.setString(2, tk.getUserName());
            stmt.setString(3, tk.getUserPassword());
            stmt.setDate(4, (Date) tk.getCreatedDate());
            stmt.setString(5, tk.getUserRole());

            stmt.execute();
            conn.close();
            
        } catch (ClassNotFoundException e) {
            i = false;
            e.printStackTrace();
        } catch (SQLException ex) {
            i = false;
            java.util.logging.Logger.getLogger(Customer_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return i;
    }

    public static boolean addCus(Customer kh) throws ClassNotFoundException, SQLException {
        boolean i = true;
        Connection conn = null;

        try {
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{CALL insertCUS(?,?,?,?,?,?,?,?,?,?)}");

        stmt.setString(1, kh.getCusID());
        stmt.setString(2, kh.getCusName());
        stmt.setString(3, kh.getGender());
        stmt.setDate(4, kh.getDateOfBirth());
        stmt.setString(5, kh.getCusAdd());
        stmt.setString(6, kh.getCusPhone());
        stmt.setString(7, kh.getCusEmail());
        stmt.setString(8, kh.getCusType());
        stmt.setDouble(9, kh.getAccrued_Money());
        stmt.setString(10, kh.getUserID());
        
        stmt.execute();
        conn.close();
        
         } catch (ClassNotFoundException e) {
            i = false;
            e.printStackTrace();
        } catch (SQLException ex) {
            i = false;
            java.util.logging.Logger.getLogger(Customer_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return i;

    }
    

    public static String themKH() throws ClassNotFoundException,SQLException{
        String cusID = "";
        try ( Connection conn = ConnectionOracle.getOracleConnection()) {
            CallableStatement stmt = conn.prepareCall("{call Them_MaKH(?)}");
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();

            cusID = stmt.getString(1);
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return cusID;
    }
    
    
    public static String themTK() {
        String userID = "";
        try (Connection conn = ConnectionOracle.getOracleConnection()) {
            CallableStatement stmt = conn.prepareCall("{call Them_MaTK(?)}");
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();

            userID = stmt.getString(1);
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return userID;
    }
    
}
