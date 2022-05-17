/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author diuai
 */
public class Sys_Product {
    private String ProID;
    private String ProName;
    private Double ProPrice;
    private String FarmID;
    private String ProType;

    public Sys_Product() {
    }

    public Sys_Product(String ProID, String ProName, Double ProPrice, String FarmID, String ProType) {
        this.ProID = ProID;
        this.ProName = ProName;
        this.ProPrice = ProPrice;
        this.FarmID = FarmID;
        this.ProType = ProType;
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String ProID) {
        this.ProID = ProID;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String ProName) {
        this.ProName = ProName;
    }

    public Double getProPrice() {
        return ProPrice;
    }

    public void setProPrice(Double ProPrice) {
        this.ProPrice = ProPrice;
    }

    public String getFarmID() {
        return FarmID;
    }

    public void setFarmID(String FarmID) {
        this.FarmID = FarmID;
    }

    public String getProType() {
        return ProType;
    }

    public void setProType(String ProType) {
        this.ProType = ProType;
    }
    
}
