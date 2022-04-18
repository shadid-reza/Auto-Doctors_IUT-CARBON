/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

/**
 *
 * @author hmsha
 */
public class Lat_Long_Extraction {
    
    public static double[] split_LatLong(String s_lat_long)
    {
        String parts[] = s_lat_long.split(",");  
        
        double lat_long[] = new double[3];
        lat_long[0] = Double.parseDouble(parts[0]);
        lat_long[1] = Double.parseDouble(parts[1]);
        
        return lat_long;
     }
    
   
    
    
}
