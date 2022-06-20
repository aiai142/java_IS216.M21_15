/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Feedback_M;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author pem41
 */
public class Feedback_C {
    public static boolean insertFeedback(Feedback_M fb) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_feedback(?,?,?,?,?)}");
        stmt.setString(1, fb.getnameCus());
        stmt.setString(2, fb.getemailCus());
        stmt.setString(3, fb.getmessage());
        stmt.setString(4, fb.getfb_image());
        stmt.setDate(5, fb.getdatesend());
        
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
}
