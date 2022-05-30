/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author pem41
 */
public class ROLE {
    private String userRole;
    private String roleName;
    
public ROLE() {
    }

    public ROLE(String userRole, String roleName)
    {
        this.userRole= userRole;
        this.roleName= roleName;
    }
     public String getuserRole() {
        return userRole;
    }

    public void setuserRole(String userRole) {
        this.userRole = userRole;
    }
     public String getroleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }
}

