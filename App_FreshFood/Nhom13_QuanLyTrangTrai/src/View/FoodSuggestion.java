/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.MissedIngredient;
import Model.RecipeItem;
import Process.APIService;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 * @author DoQuynhChi
 */
public class FoodSuggestion extends javax.swing.JFrame implements Runnable {
    private final String apiKey = "dae41bf45d274c77907d17729c2101bd";
    private ArrayList<RecipeItem> recipeItems;
    /**
     * Creates new form FoodSuggestion
     */
    public FoodSuggestion(String englishName) {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        APIService.apiSerivce.recipeOfTheday(englishName, apiKey).enqueue(new Callback<ArrayList<RecipeItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeItem>> call, Response<ArrayList<RecipeItem>> response) {
                recipeItems = response.body();
                panelFoodSuggestion.setLayout(new GridLayout(3, 1));

                if (recipeItems != null) {
                    if (recipeItems.size() > 3) {
                        for (int i = 0; i < 3; i++) {
                            
                                ArrayList<MissedIngredient> missedIngredients = recipeItems.get(i).getMissedIngredients();
                                String ingredients = "- " + englishName + "\n";
                                for (MissedIngredient missedIngredient : missedIngredients) {
                                    ingredients += "- " + missedIngredient.getOriginal() + "\n";
                                }
                                JFoodCard foodCard = new JFoodCard();
                                foodCard.getjLabelTenMon().setText(recipeItems.get(i).getTitle());
                                foodCard.getjTextAreaCongThuc().setText(ingredients);
                                try {
                                URL hinhURL = new URL(recipeItems.get(i).getImage());
                                Image hinhMonAn = ImageIO.read(hinhURL);
                                foodCard.getjLabelHinh().setIcon(new ImageIcon(hinhMonAn));
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(FoodSuggestion.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(FoodSuggestion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                panelFoodSuggestion.add(foodCard);
                        }
                    } else {
                        for (int i = 0; i < recipeItems.size(); i++) {
                            ArrayList<MissedIngredient> missedIngredients = recipeItems.get(i).getMissedIngredients();
                            String ingredients = "- " + englishName + "\n";
                            for (MissedIngredient missedIngredient : missedIngredients) {
                                ingredients += "- " + missedIngredient.getOriginal() + "\n";
                            }
                            JFoodCard foodCard = new JFoodCard();
                            foodCard.getjLabelTenMon().setText(recipeItems.get(i).getTitle());
                            foodCard.getjTextAreaCongThuc().setText(ingredients);
                           try {
                                URL hinhURL = new URL(recipeItems.get(i).getImage());
                                Image hinhMonAn = ImageIO.read(hinhURL);
                                foodCard.getjLabelHinh().setIcon(new ImageIcon(hinhMonAn));
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(FoodSuggestion.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(FoodSuggestion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           panelFoodSuggestion.add(foodCard);
                        }
                    }
                    panelFoodSuggestion.revalidate();
                    panelFoodSuggestion.repaint();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<RecipeItem>> call, Throwable throwable) {
                System.out.println("Call Api Failed");
            }
        });
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelFoodSuggestion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 150, 75));

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MÓN ĂN CỦA NGÀY");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        panelFoodSuggestion.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelFoodSuggestionLayout = new javax.swing.GroupLayout(panelFoodSuggestion);
        panelFoodSuggestion.setLayout(panelFoodSuggestionLayout);
        panelFoodSuggestionLayout.setHorizontalGroup(
            panelFoodSuggestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFoodSuggestionLayout.setVerticalGroup(
            panelFoodSuggestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelFoodSuggestion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(252, 252, 252))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFoodSuggestion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
      
    }
    
}
