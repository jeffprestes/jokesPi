/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paypal.developers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jprestes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            String system = System.getProperty("os.name");
            System.out.println(system);
            
            Process proc = null;
            //TODO: Figure out how use eSpeak on Windows
            if (system.contains("windows")) {
                
            
            } else if (system.contains("Mac"))  {
                proc = Runtime.getRuntime().exec("/usr/local/Cellar/espeak/1.48.04/bin/espeak -m -vpt -s140 'muito&nbsp;obrigado'");
            
            } else  {
                proc = Runtime.getRuntime().exec("/usr/bin/espeak -m -vpt -s140 'muito&nbsp;obrigado'");
            }
            
            //proc.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            System.out.println("Program terminated!");

            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
       // } catch (InterruptedException ex) {
        //    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
