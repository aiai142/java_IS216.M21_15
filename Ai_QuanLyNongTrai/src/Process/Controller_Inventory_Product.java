/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Inventory_Product;
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
 * @author Lenovo
 */
public class Controller_Inventory_Product {
    //Lay danh sach ma cac kho chua duoc nong san
    public static List<String> allStockId() throws SQLException, ClassNotFoundException{
       List<String> idList = new ArrayList<>();
       
       Connection conn = null;
       PreparedStatement statement = null;
       conn = (Connection) ConnectionUtils.getMyConnection();
       
        CallableStatement stmt = conn.prepareCall("{call find_stockid_pro(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
       while (rs.next()) {
            idList.add(rs.getString("STOCKID"));
        }
       return idList;
    }
    
    
    
     //Lay danh sach ma cac nong san
    public static List<String> id_product() throws SQLException, ClassNotFoundException{
       List<String> nameList = new ArrayList<>();
       
       Connection conn = null;
       PreparedStatement statement = null;
       conn = (Connection) ConnectionUtils.getMyConnection();
       
       CallableStatement stmt = conn.prepareCall("{call find_all_id_pro(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
       while (rs.next()) {
            nameList.add(rs.getString("PROID"));
        }
       return nameList;
    }
    
    
    //Lay ten nguyen vat lieu dua theo ma nguyen vat lieu
    public static String get_name_product(String id) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();
        
        CallableStatement stmt = conn.prepareCall("{call ?:= name_product(?)}");
        stmt.setString(2, id);
        stmt.registerOutParameter(1, Types.VARCHAR);
        stmt.execute();
        String name = stmt.getString(1);
        return name;
    }
    
    
    //Lay danh sach tat ca thong tin cua cac ton kho nguyen vat lieu de hien len bang
    public static List<Inventory_Product> findAll() throws SQLException, ClassNotFoundException {
        List<Inventory_Product> invenList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call find_all_inven_pro(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Inventory_Product pro = new Inventory_Product(rs.getString("STOCKID"),
                    rs.getString("PROID"),
                    "",
                    rs.getInt("NUM_INVENTORY_PRO")
                    );
            invenList.add(pro);
        }
        
         for (Inventory_Product pro : invenList) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= name_product(?)}");
            stmt2.setString(2, pro.getProId());
            stmt2.registerOutParameter(1, Types.VARCHAR);
            stmt2.execute();
            pro.setName(stmt2.getString(1));
        }
        
        return invenList;
    }
    
    
    //them NONG SAN vao bang TON NONG SAN
    public static boolean insert(Inventory_Product pro) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_inven_product(?,?,?)}");
        stmt.setString(1, pro.getStockId());
        stmt.setString(2, pro.getProId());
        stmt.setInt(3, pro.getNum_inventory_pro());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
    //
    public static boolean delete(String id_stock, String id_pro) throws SQLException, ClassNotFoundException {
       
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_inven_pro(?,?)}");
        stmt.setString(1, id_stock);
        stmt.setString(2, id_pro);
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
    public static boolean update(Inventory_Product pro) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_inven_pro(?,?,?)}");
        stmt.setString(1, pro.getStockId());
        stmt.setString(2, pro.getProId());
        stmt.setDouble(3, pro.getNum_inventory_pro());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
    public static ArrayList<Inventory_Product> find_inven_pro(String tenpro) throws ClassNotFoundException, SQLException {
        ArrayList<Inventory_Product> InvenProductsList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        
        CallableStatement stmt = conn.prepareCall("{call Find_inven_product(?, ?)}");
        stmt.setString(1, tenpro);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            String stockid = rs.getString("STOCKID");
                String proid = rs.getString("PROID");
            String proname = rs.getString("PRONAME");
            Integer num = rs.getInt("NUM_INVENTORY_PRO");

            InvenProductsList.add(new Inventory_Product(stockid,proid, proname, num));
        }
        return InvenProductsList;      
    }
    
}
