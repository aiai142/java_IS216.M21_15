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
public class RecipeItem {
    private int id;
    private String image;
    private String imageType;
    private int likes;
    private int missedIngredientCount;
    private ArrayList<MissedIngredient> missedIngredients;
    private String title;
    private ArrayList<UnusedIngredient> unusedIngredients;
    private int usedIngredientCount;
    private ArrayList<Object> usedIngredients;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }

    public int getLikes() {
        return likes;
    }

    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public ArrayList<MissedIngredient> getMissedIngredients() {
        return missedIngredients;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<UnusedIngredient> getUnusedIngredients() {
        return unusedIngredients;
    }

    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public ArrayList<Object> getUsedIngredients() {
        return usedIngredients;
    }
}