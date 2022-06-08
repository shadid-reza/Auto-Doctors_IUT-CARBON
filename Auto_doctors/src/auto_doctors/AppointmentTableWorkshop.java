/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

/**
 *
 * @author Shihab Ahmad
 */
public class AppointmentTableWorkshop {
   
     Integer app_id;
    String a_name ,a_service, a_parts , app_time ,comp_time, a_status , a_carmodel;

    public AppointmentTableWorkshop(Integer app_id, String a_name, String a_service, String a_parts, String app_time, String comp_time, String a_status, String a_carmodel) {
        this.app_id = app_id;
        this.a_name = a_name;
        this.a_service = a_service;
        this.a_parts = a_parts;
        this.app_time = app_time;
        this.comp_time = comp_time;
        this.a_status = a_status;
        this.a_carmodel = a_carmodel;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
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

    public String getA_parts() {
        return a_parts;
    }

    public void setA_parts(String a_parts) {
        this.a_parts = a_parts;
    }

    public String getApp_time() {
        return app_time;
    }

    public void setApp_time(String app_time) {
        this.app_time = app_time;
    }

    public String getComp_time() {
        return comp_time;
    }

    public void setComp_time(String comp_time) {
        this.comp_time = comp_time;
    }

    public String getA_status() {
        return a_status;
    }

    public void setA_status(String a_status) {
        this.a_status = a_status;
    }

    public String getA_carmodel() {
        return a_carmodel;
    }

    public void setA_carmodel(String a_carmodel) {
        this.a_carmodel = a_carmodel;
    }
   
    
    
}
