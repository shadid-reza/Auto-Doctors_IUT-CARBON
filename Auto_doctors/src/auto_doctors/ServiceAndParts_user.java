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
    String c_name , c_contact,c_email,c_location;

    public ServiceAndParts_user(String c_name, String c_contact, String c_email, String c_location) {
        this.c_name = c_name;
        this.c_contact = c_contact;
        this.c_email = c_email;
        this.c_location = c_location;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_contact() {
        return c_contact;
    }

    public void setC_contact(String c_contact) {
        this.c_contact = c_contact;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_location() {
        return c_location;
    }

    public void setC_location(String c_location) {
        this.c_location = c_location;
    }

}