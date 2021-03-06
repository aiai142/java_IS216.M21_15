/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Transport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngoclinh
 */
public class Controller_Transport {
    //Lay danh sach tat ca thong tin cua cac van chuyen de hien len bang
    public static List<Transport> findAll() throws SQLException, ClassNotFoundException {
        List<Transport> transportList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call display_all_transport_ord_ex(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Transport tr = new Transport(rs.getString("TRANSID"),
                    rs.getString("ORD_EX_NUM"),
                    rs.getInt("STATUSTRANS")
                    );
            transportList.add(tr);
        }
        
        conn.close();
        return transportList;
    }
    
    //them van chuyen
    public static boolean insert(Transport tr) throws SQLException, ClassNotFoundException {
//        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_Transport(?)}");
        stmt.setInt(1, tr.getStatusTrans());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    //Xoa van chuyen
    public static boolean delete(String str) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_Trans(?)}");
        stmt.setString(1, str);
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return true;
    }
    
    //cap nhat van chuyen
    public static boolean update(Transport ts) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_Transport(?,?)}");
        stmt.setString(1, ts.getTransID());
        stmt.setInt(2, ts.getStatusTrans());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
        //tim van chuyen
    public static ArrayList<Transport> searchTrans(String maVC, String maHD, String status) throws ClassNotFoundException, SQLException {
        ArrayList<Transport> TransList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;

        CallableStatement stmt = conn.prepareCall("{call search_Transport_forAll(?, ?, ?, ?)}");
        stmt.setString(1, maVC);
        stmt.setString(2, maHD);
        stmt.setString(3, status);
        stmt.registerOutParameter(4, java.sql.Types.REF_CURSOR);
        
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(4);
        
        while (rs.next()) {
            String transid = rs.getString("transID");
            String OrdExNum = rs.getString("ord_Ex_Num");
            int stttrans = rs.getInt("statusTrans");

            TransList.add(new Transport(transid, OrdExNum, stttrans));
        }
        
        conn.close();
        return TransList;
    }
    
    
}
