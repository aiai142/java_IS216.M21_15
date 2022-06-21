/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Inventory_Product {
   private String stockId;
   private String proId;
   private String name;
   private Integer num_inventory_pro;

    public Inventory_Product() {
    }

    public Inventory_Product(String stockId, String proId, String name, Integer num_inventory_pro) {
        this.stockId = stockId;
        this.proId = proId;
        this.name = name;
        this.num_inventory_pro = num_inventory_pro;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum_inventory_pro() {
        return num_inventory_pro;
    }

    public void setNum_inventory_pro(Integer num_inventory_pro) {
        this.num_inventory_pro = num_inventory_pro;
    }
   
   
}
