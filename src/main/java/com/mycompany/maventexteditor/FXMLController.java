package com.mycompany.maventexteditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

public class FXMLController implements Initializable {
    private Stage primaryStage;
    String opSystem = new String();
    String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    @FXML public TextArea textArea;
    private final TextArea textArea2 = new TextArea();
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createOpeningFolder();
    }    
}
