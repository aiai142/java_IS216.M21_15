/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author diuai
 */
public class Farm {
    private String FarmID;
    private String FarmName;
    private String FarmAdd;

    public Farm() {
    }

    public Farm(String FarmID, String FarmName, String FarmAdd) {
        this.FarmID = FarmID;
        this.FarmName = FarmName;
        this.FarmAdd = FarmAdd;
    }

    public String getFarmID() {
        return FarmID;
    }

    public void setFarmID(String FarmID) {
        this.FarmID = FarmID;
    }

    public String getFarmName() {
        return FarmName;
    }

    public void setFarmName(String FarmName) {
        this.FarmName = FarmName;
    }

    public String getFarmAdd() {
        return FarmAdd;
    }

    public void setFarmAdd(String FarmAdd) {
        this.FarmAdd = FarmAdd;
    }
    
}
