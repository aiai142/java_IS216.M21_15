package DAO;

import Model.Sys_User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sys_UserDAOImpl implements Sys_UserDAO{
    private static final CRUD CRUDOBJ = new CRUD();

    @Override
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

    @Override
    public boolean login(String userName, String password) {
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


}
