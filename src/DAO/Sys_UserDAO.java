package DAO;

import Model.Sys_User;

import java.util.ArrayList;

public interface Sys_UserDAO {
    ArrayList<Sys_User> listUser();
    boolean login(String userName, String password);
}
