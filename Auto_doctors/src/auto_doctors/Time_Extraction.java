/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author hmsha
 */
public class Time_Extraction {

    /**
     *
     * @return
     */
    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        String str = dtf.format(now);
        return str;
    }
}
