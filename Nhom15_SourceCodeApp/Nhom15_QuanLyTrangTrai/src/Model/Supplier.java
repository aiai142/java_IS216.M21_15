/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author diuai
 */
public class Supplier {
    private String supID;
    private String supName;
    private String supPhone;
    private String supAdd;
    private String supEmail;

    public Supplier() {
    }

    public Supplier(String supID, String supName, String supPhone, String supAdd, String supEmail) {
        this.supID = supID;
        this.supName = supName;
        this.supPhone = supPhone;
        this.supAdd = supAdd;
        this.supEmail = supEmail;
    }

    public String getSupID() {
        return supID;
    }

    public void setSupID(String supID) {
        this.supID = supID;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupPhone() {
        return supPhone;
    }

    public void setSupPhone(String supPhone) {
        this.supPhone = supPhone;
    }

    public String getSupAdd() {
        return supAdd;
    }

    public void setSupAdd(String supAdd) {
        this.supAdd = supAdd;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }
}
