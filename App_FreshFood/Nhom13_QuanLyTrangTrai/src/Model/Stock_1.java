/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DoQuynhChi
 */
public class Stock_1 {

    private String stockID;
    private String statusStock;
    private String type;

    public Stock_1(String stockID, String statusStock, String type) {
        this.stockID = stockID;
        this.statusStock = statusStock;
        this.type = type;
    }

    public String getStockID() {
        return stockID;
    }

    public String getStatusStock() {
        return statusStock;
    }

    public String getType() {
        return type;
    }

}
