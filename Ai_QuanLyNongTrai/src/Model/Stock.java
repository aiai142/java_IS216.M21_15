/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DoQuynhChi
 */
public class Stock {

    private String stockID;
    private String statusStock;
    private String type;

    public Stock(String stockID, String statusStock, String type) {
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

 * @author ACER
 */
public class Stock {
    private String StockID ; 
    private int StatusStock ;
    private int Type;

    public Stock() {
    }

    public Stock(String StockID, int StatusStock, int Type) {
        this.StockID = StockID;
        this.StatusStock = StatusStock;
        this.Type = Type;
    }

    public String getStockID() {
        return StockID;
    }

    public void setStockID(String StockID) {
        this.StockID = StockID;
    }

    public int getStatusStock() {
        return StatusStock;
    }

    public void setStatusStock(int StatusStock) {
        this.StatusStock = StatusStock;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    

    
}
