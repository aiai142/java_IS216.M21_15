/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Supplier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diuai
 */
public class Supplier_Controller {
    public static List<Supplier> findAllSup() throws SQLException, ClassNotFoundException {
        List<Supplier> listSup = new ArrayList<>();
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call FIND_ALL_SUPPLIER(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        
        while (rs.next()) {
            Supplier sup = new Supplier(rs.getString("SUPID"), rs.getString("SUPNAME"),
                    rs.getString("SUPPHONE"), rs.getString("SUPADD"), rs.getString("SUPEMAIL"));
            listSup.add(sup);
        }
        
        con.close();
        return listSup;
    }

    
    public static boolean insertSup(Supplier sup) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call insert_Supplier(?,?,?,?)}");
        stmt.setString(1, sup.getSupName());
        stmt.setString(2, sup.getSupPhone());
        stmt.setString(3, sup.getSupAdd());
        stmt.setString(4, sup.getSupEmail());
        check = stmt.execute();
        con.close();
        
        if (check == false) {
            return true;
        }
        return false;

    }
    
    
      public static void deleteSup(String supID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call delete_Supplier(?)}");
        stmt.setString(1, supID);
        stmt.execute();
        con.close();
    }
      
      
      public static boolean updateSup(Supplier sup) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call update_Supplier(?,?,?,?,?)}");
        stmt.setString(1, sup.getSupID());
        stmt.setString(2, sup.getSupName());
        stmt.setString(3, sup.getSupPhone());
        stmt.setString(4, sup.getSupAdd());
        stmt.setString(5, sup.getSupEmail());
        check = stmt.execute();
        con.close();

        if (check == false) {
            return true;
        }
        return false;

    }
     
      public static ArrayList<Supplier> search_Supplier_forAll(String mancc, String tenncc, String sdtncc, String diachincc, String emailncc) throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> listSup = new ArrayList<>();
        Connection con = ConnectionUtils.getMyConnection();
        PreparedStatement stat = null;
        CallableStatement stmt = con.prepareCall("{call search_Supplier_forAll (?,?,?,?,?,?)}");
        stmt.setString(1,mancc);
        stmt.setString(2,tenncc);
        stmt.setString(3,sdtncc);
        stmt.setString(4, diachincc);
        stmt.setString(5,emailncc);
        stmt.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(6);
        
        while (rs.next()) {
            String idSup=rs.getString("SUPID");
            String nameSup = rs.getString("SUPNAME");
            String phoneSup = rs.getString("SUPPHONE");
            String addSup=rs.getString("SUPADD");
            String emailSup=rs.getString("SUPEMAIL");
            listSup.add(new Supplier(idSup,nameSup, phoneSup ,addSup, emailSup));
        }
        
        con.close();
        return listSup;
    }
      
}
