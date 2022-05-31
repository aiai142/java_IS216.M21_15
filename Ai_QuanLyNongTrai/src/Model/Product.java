/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DoQuynhChi
 */
public class Product {

    private String proID;
    private String proName;
    private long proPrice;
    private String farmID;
    private String proType;
    private String image;
    private Integer quantity;

    public Product(String proID, String proName, long proPrice, String farmID, String proType, String image) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.farmID = farmID;
        this.proType = proType;
        this.image = image;
    }

    
    public Product(String proID, String proName, long proPrice, String farmID, String proType, String image, Integer quantity) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.farmID = farmID;
        this.proType = proType;
        this.image = image;
        this.quantity = quantity;
    }

    public Product(String proName, long proPrice, Integer quantity) {
        this.proName = proName;
        this.proPrice = proPrice;
        this.quantity = quantity;
    }

    

    public String getProID() {
        return proID;
    }

    public String getProName() {
        return proName;
    }

    public long getProPrice() {
        return proPrice;
    }

    public String getFarmID() {
        return farmID;
    }

    public String getProType() {
        return proType;
    }

    public String getImage() {
        return image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
