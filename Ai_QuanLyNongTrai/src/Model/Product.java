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
   private String proid;
   private String proname;
   private Integer proprice;
   private String profarmid;
   private String protype;
   private String image;
   
   private Integer quantity;

    public Product() {
    }

    public Product(String proid, String proname, Integer proprice, String profarmid, String protype, String image, Integer quantity) {
        this.proid = proid;
        this.proname = proname;
        this.proprice = proprice;
        this.profarmid = profarmid;
        this.protype = protype;
        this.image = image;
        this.quantity = quantity;
    }

    

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public Integer getProprice() {
        return proprice;
    }

    public void setProprice(Integer proprice) {
        this.proprice = proprice;
    }

    public String getProfarmid() {
        return profarmid;
    }

    public void setProfarmid(String profarmid) {
        this.profarmid = profarmid;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
   
    
}
