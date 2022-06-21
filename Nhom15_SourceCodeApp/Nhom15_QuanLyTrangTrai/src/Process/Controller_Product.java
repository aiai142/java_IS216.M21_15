/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vothanhphuong
 */
public class Controller_Product {
    public static List<Product> findAll() throws SQLException, ClassNotFoundException {
        List<Product> prolist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();
        

        CallableStatement stmt = conn.prepareCall("{call load_product(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
                    Product pro = new Product( rs.getString("PROID"),
                    rs.getString("PRONAME"),
                    rs.getInt("PROPRICE"),
                    rs.getString("FARMID"),
                    rs.getString("PROTYPE"),
                    rs.getString("IMAGE")
                     , 0); 
            prolist.add(pro);
        }
        
         for (Product pro : prolist) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= quantity_Product(?)}");
            stmt2.setString(2, pro.getProID());
            stmt2.registerOutParameter(1, Types.INTEGER);
            stmt2.execute();
            pro.setQuantity(stmt2.getInt(1));
        }
        
        conn.close();
        return prolist;
    }
    
    //Lay farm id de hien len combobox cho bang nong san
    public static List<String> farm_id() throws SQLException, ClassNotFoundException{
       List<String> farm_id_list = new ArrayList<>();
       
       Connection conn = null;
       PreparedStatement statement = null;
       conn = (Connection) ConnectionUtils.getMyConnection();
       
        CallableStatement stmt = conn.prepareCall("{call load_farm_id(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
       while (rs.next()) {
            farm_id_list.add(rs.getString("farmid"));
        }
       
       conn.close();
       return farm_id_list;
    }
    
    // THEM NONG SAN MOI VAO CHO TRANG QUAN LY NONG SAN
    public static boolean insert(Product rc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_product(?,?,?,?,?)}");
        stmt.setString(1, rc.getProName());
        stmt.setInt(2, rc.getProPrice());
        stmt.setString(3, rc.getFarmID());
        stmt.setString(4, rc.getProType());
        stmt.setString(5, rc.getImage());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
    //XOA NONG SAN
    public static void delete(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_product(?)}");
        stmt.setString(1, id);
        stmt.execute();
        conn.close();
    }   
    
    
   //UPDATE NONG SAN
    public static boolean update(Product pro) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_product(?,?,?,?,?,?)}");
        stmt.setString(1, pro.getProID());
        stmt.setString(2, pro.getProName());
        stmt.setInt(3, pro.getProPrice());
        stmt.setString(4, pro.getFarmID());
        stmt.setString(5, pro.getProType());
        stmt.setString(6, pro.getImage());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
    //Tim nong san
    public static List<Product> find_product(String name) throws SQLException, ClassNotFoundException {
        List<Product> prolist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call Find_product(?,?)}");
        stmt.setString(1, name);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            Product pro = new Product(rs.getString("PROID"),
                    rs.getString("PRONAME"),
                    rs.getInt("PROPRICE"),
                    rs.getString("FARMID"),
                    rs.getString("PROTYPE"),
                    rs.getString("IMAGE"),
                    0);
            prolist.add(pro);
        }

        for (Product pro : prolist) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= quantity_Product(?)}");
            stmt2.setString(2, pro.getProID());
            stmt2.registerOutParameter(1, Types.INTEGER);
            stmt2.execute();
            pro.setQuantity(stmt2.getInt(1));
        }

        conn.close();
        return prolist;
    }
    
    public static ArrayList<Product> Search_Product(String pproid, String pproname, String pprice,
    String ffarmid, String pprotype) throws ClassNotFoundException, SQLException {                   
        ArrayList<Product> ProList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        
        CallableStatement stmt = conn.prepareCall("{call search_Product_forAll(?,?,?,?,?,?)}");
        stmt.setString(1, pproid);
        stmt.setString(2, pproname);
        stmt.setString(3, pprice);
        stmt.setString(4, ffarmid);
        stmt.setString(5, pprotype);
        stmt.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(6);

        while (rs.next()) {
            String p_proid = rs.getString("PROID");
            String p_proname = rs.getString("PRONAME");
            int p_price = rs.getInt("PROPRICE");
            String f_farmid = rs.getString("FARMID");
            String p_protype = rs.getString("PROTYPE");
            String p_emage = rs.getString("IMAGE");
            
            ProList.add(new Product(p_proid, p_proname, p_price,f_farmid, p_protype, p_emage));
        }
        
        conn.close();
        return ProList;
    }

    
}
