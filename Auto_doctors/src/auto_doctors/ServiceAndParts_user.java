/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

/**
 *
 * @author hmsha
 */
public class ServiceAndParts_user {
    String w_name , w_contact,w_email,w_location;

    public ServiceAndParts_user(String w_name, String w_contact, String w_email, String w_location) {
        this.w_name = w_name;
        this.w_contact = w_contact;
        this.w_email = w_email;
        this.w_location = w_location;
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public String getW_contact() {
        return w_contact;
    }

    public void setW_contact(String w_contact) {
        this.w_contact = w_contact;
    }

    public String getW_email() {
        return w_email;
    }

    public void setW_email(String w_email) {
        this.w_email = w_email;
    }

    public String getW_location() {
        return w_location;
    }

    public void setW_location(String w_location) {
        this.w_location = w_location;
    }
    
}
