package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection conn;


    public Connection getConn() {
        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:sysadb/Doquynhchi1311@13.215.95.47:1521:DATABASE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

// "jdbc:oracle:thin:sysadb/Doquynhchi1311@13.215.95.47:1521:DATABASE"
}
