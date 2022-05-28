/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Resources {
    private String reID;
    private String resourcesName;
    private Integer rePrice;
    private String unit;    
    private Integer quantity;
    
    public Resources() {
    }

    public Resources(String reID, String resourcesName, Integer rePrice, String unit, Integer quantity) {
        this.reID = reID;
        this.resourcesName = resourcesName;
        this.rePrice = rePrice;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getReID() {
        return reID;
    }

    public void setReID(String reID) {
        this.reID = reID;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public Integer getRePrice() {
        return rePrice;
    }

    public void setRePrice(Integer rePrice) {
        this.rePrice = rePrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
}
