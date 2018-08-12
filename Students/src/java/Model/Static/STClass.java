/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Static;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author POKA
 */
public class STClass<T> {
    
    public static String readAllBytesJava7(String filePath)
    {
        String content = "";
        
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return content;
        
    }
    
    /**
     *
     * @param filePath
     * @return
     */
    public String toString(ArrayList<T> obj)
    {
        String toString = "";
        
        try
        {
            if (obj != null)
            {
                for(T val : obj)
                {
                    toString = val.toString();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return toString;
        
    }
    
    public static String stringParameter(Object value){
        
        return 
            "'" + ((value != null) ? value.toString() : "") + "'";
        
    }
    
    public static boolean isNullOrEmpty(String value){
        return value.equals(null) || value.equals("");
    }
    
}
