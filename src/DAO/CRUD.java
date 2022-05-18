package DAO;

import ConnectDB.DBConnection;

import java.sql.*;

public class CRUD {
    private static Connection conn = null;
    private static final DBConnection DBCONN = new DBConnection();

    public void create(String sqlInsert) {
        try {
            conn = DBCONN.getConn();
            PreparedStatement prepStm = conn.prepareStatement(sqlInsert);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet read (String sqlSelect) {
        ResultSet rs = null;
        try {
            conn = DBCONN.getConn();
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(sqlSelect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void update(String sqlUpdate) {
        try {
            conn = DBCONN.getConn();
            PreparedStatement prepStm = conn.prepareStatement(sqlUpdate);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
