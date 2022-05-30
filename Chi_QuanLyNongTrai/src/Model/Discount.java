/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author DoQuynhChi
 */
public class Discount {

    private String disID;
    private String disCode;
    private double value;
    private String cusType;
    private Date startDate;
    private Date endDate;

    public Discount(String disCode, double value, Date startDate, Date endDate) {
        this.disCode = disCode;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDisID() {
        return disID;
    }

    public String getDisCode() {
        return disCode;
    }

    public double getValue() {
        return value;
    }

    public String getCusType() {
        return cusType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    

    

}
