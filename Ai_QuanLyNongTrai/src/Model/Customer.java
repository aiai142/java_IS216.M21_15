/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diuai
 */
public class Customer {
    private String cusID;
    private String cusName;
    private String gender;
    private Date dateOfBirth;
    private String cusAdd;
    private Long cusPhone;
    private String cusEmail;
    private String cusType;
    private Double accrued_Money;
    private String userID;

    public Customer() {
    }

    public Customer(String cusID, String cusName, String gender, Date dateOfBirth, String cusAdd, Long cusPhone, String cusEmail, String cusType, Double accrued_Money, String userID) {
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

    public Long getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(Long cusPhone) {
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

    public Double getAccrued_Money() {
        return accrued_Money;
    }

    public void setAccrued_Money(Double accrued_Money) {
        this.accrued_Money = accrued_Money;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
