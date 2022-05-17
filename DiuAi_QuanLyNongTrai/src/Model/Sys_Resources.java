/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author diuai
 */
public class Sys_Resources {
    private String ReID;
    private String Re_Name;
    private Double RePrice;
    private String Unit;

    public Sys_Resources() {
    }

    public Sys_Resources(String ReID, String Re_Name, Double RePrice, String Unit) {
        this.ReID = ReID;
        this.Re_Name = Re_Name;
        this.RePrice = RePrice;
        this.Unit = Unit;
    }

    public String getReID() {
        return ReID;
    }

    public void setReID(String ReID) {
        this.ReID = ReID;
    }

    public String getRe_Name() {
        return Re_Name;
    }

    public void setRe_Name(String Re_Name) {
        this.Re_Name = Re_Name;
    }

    public Double getRePrice() {
        return RePrice;
    }

    public void setRePrice(Double RePrice) {
        this.RePrice = RePrice;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }
    
}
