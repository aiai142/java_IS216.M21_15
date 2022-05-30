/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DoQuynhChi
 */
public class Transport {

    private String transID;
    private String statusTrans;

    public Transport(String transID, String statusTrans) {
        this.transID = transID;
        this.statusTrans = statusTrans;
    }

    public Transport(String transID) {
        this.transID = transID;
    }

    public String getTransID() {
        return transID;
    }

    public String getStatusTrans() {
        return statusTrans;
    }

}
