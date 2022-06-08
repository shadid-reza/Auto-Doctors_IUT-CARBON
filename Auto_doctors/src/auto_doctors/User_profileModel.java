/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

/**
 *
 * @author Shihab Ahmad
 */
public class User_profileModel {
    
    
    String a_name , a_service , a_date; 

    public User_profileModel(String a_name, String a_service, String a_date) {
        this.a_name = a_name;
        this.a_service = a_service;
        this.a_date = a_date;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_service() {
        return a_service;
    }

    public void setA_service(String a_service) {
        this.a_service = a_service;
    }

    public String getA_date() {
        return a_date;
    }

    public void setA_date(String a_date) {
        this.a_date = a_date;
    }
    
    
    
}
