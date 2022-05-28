/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.util.Date;

/**
 *
 * @author pem41
 */
public class SYS_USER {
    private String userID;
    private String userName; 
    private String userPassword;
    private Date createdDate;
    private String userRole;
    
public SYS_USER() {
    }

    public SYS_USER(String userID, String userName, String userPassword, Date createdDate, String userRole)
    {
        this.userID= userID;
        this.userName= userName;
        this.userPassword= userPassword;
        this.createdDate= createdDate;
        this.userRole= userRole;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
     
    
}
            



