package Service;

import DAO.Sys_UserDAO;
import DAO.Sys_UserDAOImpl;
import Model.Sys_User;

import java.util.ArrayList;

public class Sys_UserService {
    private Sys_UserDAO obj1;

    public Sys_UserService() {
        obj1 = new Sys_UserDAOImpl();
    }

    public ArrayList<Sys_User> listUser() {
        return obj1.listUser();
    }

    public boolean login(String userName, String password) {
        return obj1.login(userName, password);
    }
}
