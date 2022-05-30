package Process;


import ConnectDB.*;
import Model.Sys_User;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author DoQuynhChi
 */
public class C_LoginPage_ForgotPasswordPage {

    public static ArrayList<Sys_User> listUser() throws SQLException, ClassNotFoundException {
        ArrayList<Sys_User> arrListUser = new ArrayList<>();
        ResultSet rs;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call ?:= list_user}");
        stmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);
        stmt.execute();

        rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            String id = rs.getString("UserID");
            String name = rs.getString("UserName");
            String password = rs.getString("UserPassword");
            Date date = rs.getDate("CreatedDate");
            String role = rs.getString("UserRole");

            arrListUser.add(new Sys_User(id, name, password, date, role));
        }

        return arrListUser;
    }


    public static String login (String userName, String password) throws SQLException, ClassNotFoundException {
        String id = "";
        ArrayList<Sys_User> arrListUser = listUser();

        for (Sys_User x : arrListUser) {
            if (x.getUserName().equals(userName) && x.getUserPassword().equals(password)) {
                id = x.getUserRole();
                break;
            }
        }
        return id;
    }

    public static String validateEmail (String email) throws SQLException, ClassNotFoundException {

        ResultSet rs1;
        ResultSet rs2;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt1 = conn.prepareCall("{call get_cusEmail_userID(?)}");
        stmt1.registerOutParameter(1, java.sql.Types.REF_CURSOR);
        stmt1.execute();
        rs1 = (ResultSet) stmt1.getObject(1);

        while (rs1.next()) {
            if (rs1.getString("cusEmail").equals(email)) {
                return rs1.getString("userID");
            }
        }

        CallableStatement stmt2 = conn.prepareCall("{call get_empEmail_userID(?)}");
        stmt2.registerOutParameter(1, java.sql.Types.REF_CURSOR);
        stmt2.execute();
        rs2 = (ResultSet) stmt2.getObject(1);

        while (rs2.next()) {
            if (rs2.getString("empEmail").equals(email)) {
                return rs2.getString("userID");
            }
        }

        return null;
    }


    public static void updatePassword (String userID, String newPass) throws SQLException, ClassNotFoundException {

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call update_info_cus(?, ?)}");
        stmt.setString(1, newPass);
        stmt.setString(2, userID);

        stmt.execute();
    }

}
