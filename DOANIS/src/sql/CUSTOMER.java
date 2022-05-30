/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.Date;



/**
 *
 * @author pem41
 */
public class CUSTOMER {

    private String cusID;
    private String cusName;
    private String gender;
    private Date dateOfBirth;
    private String cusAdd;
    private String cusPhone;
    private String cusEmail;
    private String cusType;
    private double accrued_Money;
    private String userID;

    public CUSTOMER() {
    }

    public CUSTOMER(String cusID, String cusName, String gender, Date dateOfBirth, String cusAdd,
            String cusPhone, String cusEmail, String cusType, double accrued_Money, String userID) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.cusAdd = cusAdd;
        this.cusPhone = cusPhone;
        this.cusEmail = cusEmail;
        this.cusType = cusType;
        this.accrued_Money = accrued_Money;
        this.userID = userID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCusAdd() {
        return cusAdd;
    }

    public void setCusAdd(String cusAdd) {
        this.cusAdd = cusAdd;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public double getAccrued_Money() {
        return accrued_Money;
    }

    public void setAccrued_Money(double accrued_Money) {
        this.accrued_Money = accrued_Money;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
    
        


