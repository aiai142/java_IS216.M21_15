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
public class Order_Export extends Transport{
    private String ord_Ex_Num;
    private Date dateOrdered;
    private String cusID;
    private String deli_Address;
    private double preTotal;
    private String disID;
    private double orderTotal;
    private String payBy;

    public Order_Export(String ord_Ex_Num, Date dateOrdered, String cusID, double preTotal, String disID, 
            double orderTotal, String TransID, int StatusTrans) {
        super(TransID, StatusTrans);
        this.ord_Ex_Num = ord_Ex_Num;
        this.dateOrdered = dateOrdered;
        this.cusID = cusID;
        this.preTotal = preTotal;
        this.disID = disID;
        this.orderTotal = orderTotal;
    }

    public Order_Export(Date dateOrdered, String cusID, String deli_Address, double preTotal, String disID, 
            double orderTotal, String payBy, String TransID) {
        super(TransID);
        this.dateOrdered = dateOrdered;
        this.cusID = cusID;
        this.deli_Address = deli_Address;
        this.preTotal = preTotal;
        this.disID = disID;
        this.orderTotal = orderTotal;
        this.payBy = payBy;
    }

    public Order_Export(String ord_Ex_Num, Date dateOrdered, String deli_Address, double orderTotal, String payBy, 
            String TransID, int StatusTrans) {
        super(TransID, StatusTrans);
        this.ord_Ex_Num = ord_Ex_Num;
        this.dateOrdered = dateOrdered;
        this.deli_Address = deli_Address;
        this.orderTotal = orderTotal;
        this.payBy = payBy;
    }
    
    public String getOrd_Ex_Num() {
        return ord_Ex_Num;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public String getCusID() {
        return cusID;
    }

    public double getPreTotal() {
        return preTotal;
    }

    public String getDisID() {
        return disID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public String getDeli_Address() {
        return deli_Address;
    }

    public String getPayBy() {
        return payBy;
    }
   
}
