/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */
public class Transport {
    private String TransID ; 
    private String Ord_Ex_Num;
    private Integer StatusTrans;

    public Transport() {
    }

    public Transport(String TransID, String Ord_Ex_Num, Integer StatusTrans) {
        this.TransID = TransID;
        this.Ord_Ex_Num = Ord_Ex_Num;
        this.StatusTrans = StatusTrans;
    }

    public String getTransID() {
        return TransID;
    }

    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    public String getOrd_Ex_Num() {
        return Ord_Ex_Num;
    }

    public void setOrd_Ex_Num(String Ord_Ex_Num) {
        this.Ord_Ex_Num = Ord_Ex_Num;
    }

    public Integer getStatusTrans() {
        return StatusTrans;
    }

    public void setStatusTrans(Integer StatusTrans) {
        this.StatusTrans = StatusTrans;
    }
}
