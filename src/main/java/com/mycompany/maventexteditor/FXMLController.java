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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    
    @FXML
    private void howToPopup(ActionEvent event) throws IOException{
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createOpeningFolder();
    }    
}
