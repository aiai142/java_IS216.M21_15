/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Customer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diuai
 */
public class Customer_Controller {
     public static List<Customer> findAllCus() throws SQLException, ClassNotFoundException {
        List<Customer> listCus = new ArrayList<>();
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call FIND_ALL_CUS(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        while(rs.next()) {
        Customer cus = new Customer(rs.getString("CUSID"), rs.getString("CUSNAME"),
                rs.getString("GENDER"), rs.getDate("DATEOFBIRTH"), rs.getString("CUSADD"),
                rs.getLong("CUSPHONE"), rs.getString("CUSEMAIL"), rs.getString("CUSTYPE"),
                rs.getDouble("ACCRUED_MONEY"), rs.getString("USERID"));
        listCus.add(cus);
    }
    return listCus ;
    }


public static boolean insertCus(Customer cus) throws SQLException, ClassNotFoundException {
//        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_Customer(?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, cus.getCusName());
        stmt.setString(2, cus.getGender());
        stmt.setDate( 3,new java.sql.Date( cus.getDateOfBirth().getTime()));
        stmt.setString(4, cus.getCusAdd());
        stmt.setLong(5, cus.getCusPhone());
        stmt.setString(6,cus.getCusEmail());
        stmt.setString(7,cus.getCusType());
        stmt.setDouble(8,cus.getAccrued_Money());
        stmt.setString(9,cus.getUserID());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    public static ArrayList<Customer> findCus(String cusname) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> listCus = new ArrayList<>();
        Connection con = ConnectionUtils.getMyConnection();
        PreparedStatement stat = null;
        CallableStatement stmt = con.prepareCall("{call FIND_CUS(?,?)}");
        stmt.setString(1,cusname);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);
        while (rs.next()) {
            String idCus=rs.getString("CUSID");
            String nameCus = rs.getString("CUSNAME");
            String genderCus = rs.getString("GENDER");
            Date datebirthCus =rs.getDate("DATEOFBIRTH");
            String addCus=rs.getString("CUSADD");
            Long phoneCus=rs.getLong("CUSPHONE");
            String emailCus=rs.getString("CUSEMAIL");
            String typeCus=rs.getString("CUSTYPE");
            Double moneyCus=rs.getDouble("ACCRUED_MONEY");
            String uidCus=rs.getString("USERID");
            listCus.add(new Customer(idCus, nameCus, genderCus, datebirthCus, addCus, phoneCus, emailCus, typeCus, moneyCus, uidCus));
            
        }
        return listCus;
    }
          public static void deleteCus(String cusID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call delete_Customer(?)}");
        stmt.setString(1, cusID);
        stmt.execute();
    }
          public static boolean updateCus(Customer cus) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call update_Customer(?,?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1,cus.getCusID());
        stmt.setString(2, cus.getCusName());
        stmt.setString(3, cus.getGender());
         stmt.setDate( 4,new java.sql.Date( cus.getDateOfBirth().getTime()));
        stmt.setString(5, cus.getCusAdd());
        stmt.setLong(6, cus.getCusPhone());
        stmt.setString(7,cus.getCusEmail());
        stmt.setString(8,cus.getCusType());
        stmt.setDouble(9,cus.getAccrued_Money());
        stmt.setString(10,cus.getUserID());
        check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
        
    }
}
