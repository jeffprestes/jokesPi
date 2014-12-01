/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paypal.developers.demo.speaker;

import com.paypal.developers.demo.jokespi.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jprestes
 */
public class Speaker {
    /**
     * Uses eSpeak T2S to speak some words
     * @param text Text you desire to listen to
     * @param language in which language the text was written
     */
    public static void speak(String text, String language)  {
        try {
            
            if (text.length()>42)   {
                text = text.substring(0, 42);
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Due a eSpeak bug, the text was truncated to fit 42 characters.");
            }
            
            String system = System.getProperty("os.name");
            String eSpeakLocation = "";
            Logger.getLogger(Main.class.getName()).log(Level.INFO, system);
            
            Process proc = null;
            //TODO: Figure out how use eSpeak on Windows
            if (system.contains("windows")) {
                
            
            } else if (system.contains("Mac"))  {
                eSpeakLocation = "/usr/local/Cellar/espeak/1.48.04/bin/espeak -m -v";
            
            } else  {
                eSpeakLocation = "/usr/bin/espeak -m -v";
            }
            
            proc = Runtime.getRuntime().exec(eSpeakLocation + language + " -s140 '" + text + "'");

            //proc.waitFor();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                Logger.getLogger(Main.class.getName()).log(Level.INFO, line);
            }
            br.close();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Speech finished.");

            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
