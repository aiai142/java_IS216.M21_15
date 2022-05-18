/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.Sys_UserDAOImpl;
import Model.Sys_User;

import java.util.ArrayList;

/**
 *
 * @author DoQuynhChi
 */

public class Sys_UserService {
    private Sys_UserDAOImpl obj1;

    public Sys_UserService() {
        obj1 = new Sys_UserDAOImpl();
    }

    public ArrayList<Sys_User> listUser() {
        return obj1.listUser();
    }

    public boolean login(String userName, String password) {
        return obj1.login(userName, password);
    }

    public String validateEmail (String email) {return obj1.validateEmail(email);}

    public void updatePassword (String userID, String newPass) {obj1.updatePassword(userID, newPass);}
}

