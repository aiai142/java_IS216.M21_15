/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlytrangtrai;

import View.LoginPage;

/**
 *
 * @author DoQuynhChi
 */
public class Main {
   public static void main(String arg[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new LoginPage().setVisible(true);
         }
      });
   }
}
