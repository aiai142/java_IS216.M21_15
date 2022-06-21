/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author DoQuynhChi
 */
public class MissedIngredient {

    private String aisle;
    private double amount;
    private String extendedName;
    private int id;
    private String image;
    private ArrayList<String> meta;
    private String name;
    private String original;
    private String originalName;
    private String unit;
    private String unitLong;
    private String unitShort;

    public String getAisle() {
        return aisle;
    }

    public double getAmount() {
        return amount;
    }

    public String getExtendedName() {
        return extendedName;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

    public String getOriginal() {
        return original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public String getUnitShort() {
        return unitShort;
    }
}
