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
    private double preTotal;
    private String disID;
    private double orderTotal;

    public Order_Export(String ord_Ex_Num, Date dateOrdered, String cusID, double preTotal, String disID, double orderTotal, String transID, String statusTrans) {
        super(transID, statusTrans);
        this.ord_Ex_Num = ord_Ex_Num;
        this.dateOrdered = dateOrdered;
        this.cusID = cusID;
        this.preTotal = preTotal;
        this.disID = disID;
        this.orderTotal = orderTotal;
    }
    
     public Order_Export(Date dateOrdered, String cusID, double preTotal, String disID, double orderTotal, String transID) {
        super(transID);
        this.dateOrdered = dateOrdered;
        this.cusID = cusID;
        this.preTotal = preTotal;
        this.disID = disID;
        this.orderTotal = orderTotal;
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

}
