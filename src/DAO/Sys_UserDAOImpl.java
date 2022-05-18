package DAO;


import Model.Sys_User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sys_UserDAOImpl {
    private static final CRUD CRUDOBJ = new CRUD();

    public ArrayList<Sys_User> listUser() {
        ArrayList<Sys_User> arrListUser = new ArrayList<>();

        ResultSet rs = CRUDOBJ.read("SELECT * FROM SYS_USER");
            try {
                while (rs.next()) {
                    String id = rs.getString("UserID");
                    String name = rs.getString("UserName");
                    String password = rs.getString("UserPassword");
                    String date = rs.getString("CreatedDate");

                    arrListUser.add(new Sys_User(id, name, password, date));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return arrListUser;
    }


    public boolean login (String userName, String password) {
        boolean flag = false;
        ArrayList<Sys_User> arrListUser = listUser();

        for (Sys_User x : arrListUser) {
            if (x.getUserName().equals(userName) && x.getUserPassword().equals(password)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String validateEmail (String email) {

        ResultSet rs1 = CRUDOBJ.read("SELECT CusEmail, UserID FROM CUSTOMER");
        ResultSet rs2 = CRUDOBJ.read("SELECT EmpEmail, UserID FROM EMPLOYEE");

        try {

            while (rs1.next()) {
                if (rs1.getString("CusEmail").equals(email)) {
                    return rs1.getString("UserID");
                }
            }

            while (rs2.next()) {
                if (rs2.getString("EmpEmail").equals(email)) {
                    return rs2.getString("UserID");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void updatePassword (String userID, String newPass) {
            CRUDOBJ.update("UPDATE SYS_USER SET UserPassword = '" + newPass + "' WHERE UserID = '" + userID + "'");
    }
}
