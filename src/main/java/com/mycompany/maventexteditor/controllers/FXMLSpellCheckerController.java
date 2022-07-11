/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventexteditor.controllers;

import com.mycompany.maventexteditor.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

/**
 * FXML Controller class
 *
 * @author rtbfl
 */
public class FXMLSpellCheckerController extends Thread {

    /**
     * Initializes the controller class.
     */
    
//        class RunSpellChecker extends TimerTask  {
//        JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
//        
//    
//    @Override
//        public void run() {
//            
//            
//                    List<RuleMatch> matches = null;
//                        try {
//                            matches = langTool.check("A sentence with a error in the Hitchhiker's Guide tot he Galaxy");
//                        } catch (IOException ex) {
//                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    for (RuleMatch match : matches) {
//                        System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage());
//                        System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
//                    }
//            
//        } 
//    
//    }
    
    
    
    public void run(){
       System.out.println("MyThread running");
    }   
    
}

//    public class FirstThread extends Thread {
//        
//        JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
//        
//        
//        class RunTimerTask extends TimerTask {
//        
//
//            @Override
//            public void run() {
//                
//                System.out.println("System time" + " " + System.currentTimeMillis());
//                System.out.println("LastKeyPressedTime" + " " + lastKeyPressTime);
//                
//                if((System.currentTimeMillis() - lastKeyPressTime) > 5000) {
//                    
//                    //do something, because the user hasnt typed for two seconds
//                    System.out.println(textArea);
//                    System.out.println("something is done when user has not type for 2 seconds");
//                    
//                    JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
//                    // comment in to use statistical ngram data:
//                    //langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
//                    List<RuleMatch> matches = null;
//                        try {
//                            matches = langTool.check("A sentence with a error in the Hitchhiker's Guide tot he Galaxy");
//                        } catch (IOException ex) {
//                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    for (RuleMatch match : matches) {
//                        System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage());
//                        System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
//                    }
//            
//   
//                }
//           
//                
//                
//                
//            }
//    }
//
//    public void run(){
//       System.out.println("MyThread running");
//       Timer timer = new Timer();
//        TimerTask task = new RunTimerTask();
//        timer.schedule(task, 2000, 2000);
//       
//    }
//    
//    
//    
//  }
