/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author pem41
 */
public class Feedback_M {
    private String fbID;
    private String nameCus;
    private String emailCus;
    private String message;
    private String fb_image;
    private Date   datesend;
     public Feedback_M() {
    }
    

    public Feedback_M(String fbID, String nameCus, String emailCus, String message, String fb_image, Date datesend) {
        this.fbID = fbID;
        this.nameCus = nameCus;
        this.emailCus = emailCus;
        this.message = message;
        this.fb_image = fb_image;
        this.datesend = datesend;
    }
    public String getfbID() {
        return fbID;
    }

    public void setfbID(String fbID) {
        this.fbID = fbID;
    }
    public String getnameCus() {
        return nameCus;
    }

    public void setnameCus(String nameCus) {
        this.nameCus = nameCus;
    }
    public String getemailCus() {
        return emailCus;
    }

    public void setemailCus(String emailCus) {
        this.emailCus = emailCus;
    }
     public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
       this.message = message;
    }
    public String getfb_image() {
        return fb_image;
    }

    public void setfb_image(String fb_image) {
        this.fb_image = fb_image;
    }
    public Date getdatesend() {
        return datesend;
    }

    public void setdatesend(Date datesend) {
             this.datesend = datesend;
    }
}
    
        
    


