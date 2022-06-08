/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

/**
 *
 * @author hmsha
 */
public class ServicesTableWorkshop {
    
    Integer app_id, a_price;
    String a_name , a_avail;

    public ServicesTableWorkshop(Integer app_id, Integer a_price, String a_name, String a_avail) {
        this.app_id = app_id;
        this.a_price = a_price;
        this.a_name = a_name;
        this.a_avail = a_avail;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public Integer getA_price() {
        return a_price;
    }

    public void setA_price(Integer a_price) {
        this.a_price = a_price;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_avail() {
        return a_avail;
    }

    public void setA_avail(String a_avail) {
        this.a_avail = a_avail;
    }

      
    
}
