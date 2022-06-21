
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import java.sql.*;
/**
 *
 * @author DoQuynhChi
 */
public class ConnectionOracle {
    public static Connection getOracleConnection() throws ClassNotFoundException,
            SQLException {

        String hostName = "13.215.95.47";
        String tenPDB = "DATABASE";
        String userName = "sysadb";
          String password = "Doquynhchi1311";
////        
//        String hostName = "localhost";
//        String tenPDB = "orclpdbQLTT";
//        String userName = "adminpdbQLTT";
//        String password = "pdbpassword";
        // Khai báo class Driver cho DB Oracle
        // Việc này cần thiết với Java 5
        // Java6 tự động tìm kiếm Driver thích hợp.
        // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Cấu trúc URL Connection dành cho Oracle
        // Ví dụ: jdbc:oracle:thin:@localhost:1521:db11g
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521/" + tenPDB;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
