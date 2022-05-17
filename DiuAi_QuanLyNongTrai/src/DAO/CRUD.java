/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import ConnectDB.ConnectUtils;
import java.sql.*;

/**
 *
 * @author diuai
 */
public class CRUD {
private static Connection conn=null;
private static final ConnectUtils DBCONN=new ConnectUtils();
public void create (String sqlInsert) {
    try {
        conn=DBCONN.getMyConnection();
        PreparedStatement prepStm=conn.prepareStatement(sqlInsert);
        prepStm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public ResultSet read (String sqlSelect) {
    ResultSet rs=null;
    try {
        conn=DBCONN.getMyConnection();
        Statement stm=conn.createStatement();
        rs=stm.executeQuery(sqlSelect);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rs;
}
}
