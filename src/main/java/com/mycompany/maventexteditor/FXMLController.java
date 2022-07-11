package com.mycompany.maventexteditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

public class FXMLController implements Initializable {
    
    private Stage primaryStage;
    String opSystem = new String();
    String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    @FXML public TextArea textArea;
    @FXML public TextArea spellcheckTextArea;
    @FXML public TextField textFieldFileFolderName;
    private final TextArea textArea2 = new TextArea();
    public static volatile int keyPressTime = 00;
    public static volatile int keyReleaseTime = 00;
    JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
    

    @FXML
    private void handleKeyPressed() throws IOException {
        keyPressTime = (int) System.currentTimeMillis();
        System.out.println(keyPressTime + "keyPressTime");
    }
    
    @FXML
    private void handleKeyReleased() throws IOException{
        keyReleaseTime = (int) System.currentTimeMillis();
        System.out.println(keyReleaseTime + "keyReleaseTime");
        
    }
    
    
    private void createOpeningFolder(){
        
        if (opSystem.contains("mac")) {
            Path path = Paths.get(documentsPath + "/Documents" + "/ssef");
            if (Files.exists(path)) {
                    // Do nothing
            } else {
            new File(documentsPath + "/Documents" + "/ssef").mkdir();
            }
        
        } else {
            Path path = Paths.get(documentsPath + "\\ssef");
            if (Files.exists(path)) {
                    // Do nothing
            } else {
            new File(documentsPath + "\\ssef").mkdir();
            }
        }
        
    }
    
    private void Save(String content, File file){
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class
                .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void saveFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){

            Save(textArea.getText().replaceAll("\n", System.getProperty("line.separator")), file);
        } 
    }
    
    @FXML
    private void howToPopup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/howtoFXML.fxml"));
        final Stage dialog = new Stage();
        dialog.getIcons().add(new Image("/images/pencil.png"));
        dialog.setTitle("Super Simple Editor - How To");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    @FXML
    private void aboutpopup(ActionEvent event){
        final Stage dialog = new Stage();
        dialog.getIcons().add(new Image("/images/pencil.png"));
        dialog.setTitle("Super Simple Editor");
        TextArea aboutTextArea = new TextArea();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        aboutTextArea.setWrapText(true);
        aboutTextArea.setEditable(false);
        aboutTextArea.setText("This editor was created to use with notation formatting languages like Jira's Text Formatting Notation or simple note taking and writing for all of your thoughts. Contact rtbfletch at outlook dot com with any quetions or input. ");
        Scene dialogScene = new Scene(aboutTextArea, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
           
    }
    
    @FXML
    private void quitApp(ActionEvent event){
        System.exit(0);
    }
    
    private void runAutoSave(){
        if (this.opSystem.contains("mac")){
                Timer timer = new Timer();
                TimerTask task = new AutoSaveMac();
                timer.schedule(task, 2000, 60000);
            } else {
                Timer timer = new Timer();
                TimerTask task = new AutoSave();
                timer.schedule(task, 2000, 60000);
            }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String osName = System.getProperty("os.name").toLowerCase();
        this.opSystem = osName;
        System.out.println("Operating System: "   + this.opSystem);
        textFieldFileFolderName.setFocusTraversable(true);
        textArea.setWrapText(true);
        createOpeningFolder();
        runAutoSave();
        
    }
    
    class AutoSave extends TimerTask {
      
        @Override
        public void run() {
             
            if(textArea2.getText().equals(textArea.getText())){
                // do nothing
            } else {
                // autosave new folder and file if needed
//                String[] firstLineOfTextArea = textArea.getText().split("\\n");
                    String[] firstLineOfTextArea = textFieldFileFolderName.getText().split("\\n");
                    String[] folderAndFileName = firstLineOfTextArea[0].split("-", 2);
                    Path path = Paths.get(documentsPath + "\\ssef\\" + folderAndFileName[0]);
                if (Files.exists(path)) {
                    // Do nothing
                } else {
                    new File(documentsPath + "\\ssef\\" + folderAndFileName[0]).mkdir();
                }
                File file = new File(String.format(documentsPath + "\\ssef\\" + folderAndFileName[0]+ "\\%s.txt", folderAndFileName[1] ));
                Save(textArea.getText().replaceAll("\n", System.getProperty("line.separator")), file);
                textArea2.setText(textArea.getText());
                
//                spell check
                List<RuleMatch> matches = null;
                        try {
                            matches = langTool.check(textArea.getText());
                            System.out.println(matches);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if (matches.isEmpty() == true) { 
                            spellcheckTextArea.clear();
                        }
                            
                            for (RuleMatch match : matches) {
                            spellcheckTextArea.setText(spellcheckTextArea.getText() + "\n" + "Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n" + "Suggested correction(s): " + match.getSuggestedReplacements());

                            System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage());
                            System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
                            }
                        
                    
            
            }
        } 
    }
    
    class AutoSaveMac extends TimerTask {
      
        @Override
        public void run() {
             
            if(textArea2.getText().equals(textArea.getText())){
                // do nothing
            } else {
                // autosave new folder and file if needed
                String[] firstLineOfTextArea = textFieldFileFolderName.getText().split(System.getProperty("line.separator"));
                System.out.println(firstLineOfTextArea[0] + " first line of textarea");
                String[] folderAndFileName = firstLineOfTextArea[0].split("-", 2);
                Path path = Paths.get(documentsPath + "/Documents" + "/ssef/" + folderAndFileName[0]);
                
                if (Files.exists(path)) {
                    // Do nothing
                } else {
                    new File(documentsPath +  "/Documents" + "/ssef/" + folderAndFileName[0]).mkdir();
                }
                File file = new File(documentsPath + "/Documents" + "/ssef/" + folderAndFileName[0] + "/" + folderAndFileName[1]+ ".txt" );
                Save(textArea.getText().replaceAll("/n", System.getProperty("line.separator")), file);
                textArea2.setText(textArea.getText());
                
                List<RuleMatch> matches = null;
                        try {
                            matches = langTool.check(textArea.getText());
                            System.out.println(matches);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if (matches.isEmpty() == true) { 
                            spellcheckTextArea.clear();
                        }
                            
                            for (RuleMatch match : matches) {
                            spellcheckTextArea.setText(spellcheckTextArea.getText() + "\n" + "Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n" + "Suggested correction(s): " + match.getSuggestedReplacements());

                            System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage());
                            System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
                            }
            
            }
        } 
    }
    
    
    
}
