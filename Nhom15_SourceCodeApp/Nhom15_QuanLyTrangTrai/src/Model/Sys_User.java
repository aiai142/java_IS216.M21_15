package Model;

import java.sql.Date;

/**
 *
 * @author DoQuynhChi
 */
public class Sys_User {
    private String userID;
    private String userName;
    private String userPassword;
    private Date createdDate;
    private String userRole;

    public Sys_User() {
    }
    
    public Sys_User(String userID, String userName, String userPassword, Date createdDate, String userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createdDate = createdDate;
        this.userRole = userRole;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getUserRole() {
        return userRole;
    }

}
