/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DoQuynhChi
 */
public class Order_Details_Ex {

    private String ord_Ex_Num;
    private String proID;
    private int num_Products;

    public Order_Details_Ex(String ord_Ex_Num, String proID, int num_Products) {
        this.ord_Ex_Num = ord_Ex_Num;
        this.proID = proID;
        this.num_Products = num_Products;
    }

    public String getOrd_Ex_Num() {
        return ord_Ex_Num;
    }

    public String getProID() {
        return proID;
    }

    public int getNum_Products() {
        return num_Products;
    }

  

}
