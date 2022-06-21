///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Stock;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ngocLinh 
 */
public class Controller_Stock {
    
    //Lay danh sach tat ca thong tin cua cac kho de hien len bang
    public static List<Stock> findAll() throws SQLException, ClassNotFoundException {
        List<Stock> stockList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call display_all_stock(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Stock st = new Stock(rs.getString("STOCKID"),
                    rs.getInt("STATUSSTOCK"),
                    rs.getInt("TYPE")
                    );
            stockList.add(st);
        }
        
        conn.close();
        return stockList;
    }
    
    //them kho
    public static boolean insert(Stock st) throws SQLException, ClassNotFoundException {
//        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_Stock(?,?)}");
        stmt.setInt(1, st.getStatusStock());
        stmt.setInt(2, st.getType());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    //xoa kho
    public static boolean delete(String str) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_Stock(?)}");
        stmt.setString(1, str);
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return true;
    }
    
    
    //sua kho
    public static boolean update(Stock st) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_Stock(?,?,?)}");
        stmt.setString(1, st.getStockID());
        stmt.setInt(2, st.getStatusStock());
        stmt.setInt(3, st.getType());
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    //tim kho
     public static ArrayList<Stock> Search_Stock(String s_stockid, String s_stt, String s_type) throws ClassNotFoundException, SQLException {
        ArrayList<Stock> StockList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        CallableStatement stmt = conn.prepareCall("{call search_Stock_forAll(?, ?, ?, ?)}");
        stmt.setString(1, s_stockid);
        stmt.setString(2, s_stt);
        stmt.setString(3, s_type);
        stmt.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        
        ResultSet rs = (ResultSet) stmt.getObject(4);

        while (rs.next()) {
            String stockid = rs.getString("STOCKID");
            int sttstock = rs.getInt("STATUSSTOCK");
            int stype = rs.getInt("TYPE");

            StockList.add(new Stock(stockid, sttstock, stype));
        }
        
        conn.close();
        return StockList;
    }
     
     
}
