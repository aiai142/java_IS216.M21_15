/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Inventory_Resources;
import java.sql.Connection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author vothanhphuong
 */
public class Controller_Inventory_Resources {
    
    //Lay danh sach ma cac kho chua duoc nguyen vat lieu
    public static List<String> allStockId() throws SQLException, ClassNotFoundException{
       List<String> idList = new ArrayList<>();
       
       Connection conn = null;
       PreparedStatement statement = null;
       conn = (Connection) ConnectionUtils.getMyConnection();
       
        CallableStatement stmt = conn.prepareCall("{call find_stockid_re(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
       while (rs.next()) {
            idList.add(rs.getString("STOCKID"));
        }
       return idList;
    }
    
    
    //Lay danh sach ma cac nguyen vat lieu
    public static List<String> id_resources() throws SQLException, ClassNotFoundException{
       List<String> nameList = new ArrayList<>();
       
       Connection conn = null;
       PreparedStatement statement = null;
       conn = (Connection) ConnectionUtils.getMyConnection();
       
       CallableStatement stmt = conn.prepareCall("{call find_all_id_re(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
       while (rs.next()) {
            nameList.add(rs.getString("reid"));
        }
       return nameList;
    }
    
    
    //Lay ten nguyen vat lieu dua theo ma nguyen vat lieu
    public static String get_name_resources(String id) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();
        
        CallableStatement stmt = conn.prepareCall("{call ?:= name_Resources(?)}");
        stmt.setString(2, id);
        stmt.registerOutParameter(1, Types.VARCHAR);
        stmt.execute();
        String name = stmt.getString(1);
        return name;
    }
    
    
    
    //Lay danh sach tat ca thong tin cua cac ton kho nguyen vat lieu de hien len bang
    public static List<Inventory_Resources> findAll() throws SQLException, ClassNotFoundException {
        List<Inventory_Resources> invenList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call find_all_inven_rcs(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Inventory_Resources rsc = new Inventory_Resources(rs.getString("STOCKID"),
                    rs.getString("REID"),
                    "",
                    rs.getInt("NUM_INVENTORY_RE")
                    );
            invenList.add(rsc);
        }
        
         for (Inventory_Resources rc : invenList) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= name_Resources(?)}");
            stmt2.setString(2, rc.getReId());
            stmt2.registerOutParameter(1, Types.VARCHAR);
            stmt2.execute();
            rc.setName(stmt2.getString(1));
        }
        
        return invenList;
    }
    
    //them nguyen vat vao bang ton nguyen vat lieu
    public static boolean insert(Inventory_Resources rc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_inven_Resources(?,?,?)}");
        stmt.setString(1, rc.getStockId());
        stmt.setString(2, rc.getReId());
        stmt.setInt(3, rc.getNum_inventory_re());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    
     public static boolean delete(String id_stock, String id_re) throws SQLException, ClassNotFoundException {
       
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_inven_re(?,?)}");
        stmt.setString(1, id_stock);
        stmt.setString(2, id_re);
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
     
     
    public static boolean update(Inventory_Resources rc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_inven_re(?,?,?)}");
        stmt.setString(1, rc.getStockId());
        stmt.setString(2, rc.getReId());
        stmt.setDouble(3, rc.getNum_inventory_re());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    public static ArrayList<Inventory_Resources> find_inven_Rc(String tenrc) throws ClassNotFoundException, SQLException {
        ArrayList<Inventory_Resources> InvenResourcesList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        
        CallableStatement stmt = conn.prepareCall("{call Find_inven_resources(?, ?)}");
        stmt.setString(1, tenrc);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            String stockid = rs.getString("STOCKID");
            String REID = rs.getString("REID");
            String NAME = rs.getString("RESOURCESNAME");
            Integer NUM = rs.getInt("NUM_INVENTORY_RE");

            InvenResourcesList.add(new Inventory_Resources(stockid,REID, NAME, NUM));
        }
        return InvenResourcesList;      
    }
}
