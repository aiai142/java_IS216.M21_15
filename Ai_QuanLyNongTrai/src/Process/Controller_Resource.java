/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Resources;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vothanhphuong
 */
public class Controller_Resource {

    public static List<Resources> findAll() throws SQLException, ClassNotFoundException {
        List<Resources> resourcesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        String query = "SELECT * FROM RESOURCES";
        statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            Resources rsc = new Resources(rs.getString("reID"),
                    rs.getString("resourcesName"),
                    rs.getInt("rePrice"),
                    rs.getString("unit"),
                    0);
            resourcesList.add(rsc);
        }

        for (Resources rc : resourcesList) {
            CallableStatement stmt = conn.prepareCall("{call ?:= quantity_Resources(?)}");
            stmt.setString(2, rc.getReID());
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            rc.setQuantity(stmt.getInt(1));
        }
        return resourcesList;
    }

    public static boolean insert(Resources rc) throws SQLException, ClassNotFoundException {
//        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_Resources(?,?,?)}");
        stmt.setString(1, rc.getResourcesName());
        stmt.setDouble(2, rc.getRePrice());
        stmt.setString(3, rc.getUnit());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
    //chinh sua thông tin nguyen vat lieu
    public static boolean update(Resources rc) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_Resources(?,?,?,?)}");
        stmt.setString(1, rc.getReID());
        stmt.setString(2, rc.getResourcesName());
        stmt.setDouble(3, rc.getRePrice());
        stmt.setString(4, rc.getUnit());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }

    //xoa nguyen vat lieu chua co hoa don va khong co hang ton kho
    public static void delete(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_Resources(?)}");
        stmt.setString(1, id);
        stmt.execute();
    }



    
    // Tim theo ten cua nguyen vat lieu
    public static ArrayList<Resources> TimRc(String tenrc) throws ClassNotFoundException, SQLException {
        ArrayList<Resources> ResourcesList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        
        CallableStatement stmt = conn.prepareCall("{call Find_resources(?, ?)}");
        stmt.setString(1, tenrc);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            String name = rs.getString("RESOURCESNAME");
            Integer price = rs.getInt("REPRICE");
            String id = rs.getString("REID");
            String type = rs.getString("UNIT");

            ResourcesList.add(new Resources(id, name, price, type, 0));
        }
        for (Resources rcs : ResourcesList) {
            CallableStatement stmt2 = conn.prepareCall("{call ?:= quantity_Resources(?)}");
            stmt2.setString(2, rcs.getReID());
            stmt2.registerOutParameter(1, Types.INTEGER);
            stmt2.execute();
            rcs.setQuantity(stmt2.getInt(1));
        }
        return ResourcesList;
    }

}
