/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Product {
   private String proID;
    private String proName;
    private int proPrice;
    private String farmID;
    private String proType;
    private String image;
    private String englishName;
    private int quantity;

    public Product(String proID, String proName, int proPrice, String farmID, String proType, String image) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.farmID = farmID;
        this.proType = proType;
        this.image = image;
    }

    public Product(String proID, String proName, int proPrice, String farmID, String proType, String image, int quantity) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.farmID = farmID;
        this.proType = proType;
        this.image = image;
        this.quantity = quantity;
    }

    public Product(String proname, int proprice, int quantity) {
        this.proName = proname;
        this.proPrice = proprice;
        this.quantity = quantity;
    }

    public Product(String proID, String proName, int proPrice, String farmID, String proType, String image, String englishName) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.farmID = farmID;
        this.proType = proType;
        this.image = image;
        this.englishName = englishName;
    }
    

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    public String getFarmID() {
        return farmID;
    }

    public void setFarmID(String farmID) {
        this.farmID = farmID;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEnglishName() {
        return englishName;
    }

}
