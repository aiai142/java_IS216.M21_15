/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author diuai
 */
public class Discount {
     private String disID;
    private String disCode;
    private Double value;
    private String cusType;
    Date startDate;
    Date endDate;
    private Integer status;

    public Discount() {
    }

    public Discount(String disID, String disCode, Double value, String cusType, Date startDate, Date endDate, Integer status) {
        this.disID = disID;
        this.disCode = disCode;
        this.value = value;
        this.cusType = cusType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getDisID() {
        return disID;
    }

    public void setDisID(String disID) {
        this.disID = disID;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
