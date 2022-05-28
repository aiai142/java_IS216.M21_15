/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Inventory_Resources {
   private String stockId;
   private String reId;
   private String name;
   private Integer num_inventory_re;

    public Inventory_Resources() {
    }

    public Inventory_Resources(String stockId, String reId, String name, Integer num_inventory_re) {
        this.stockId = stockId;
        this.reId = reId;
        this.name = name;
        this.num_inventory_re = num_inventory_re;
    }

    

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum_inventory_re() {
        return num_inventory_re;
    }

    public void setNum_inventory_re(Integer num_inventory_re) {
        this.num_inventory_re = num_inventory_re;
    }

    

   
}
