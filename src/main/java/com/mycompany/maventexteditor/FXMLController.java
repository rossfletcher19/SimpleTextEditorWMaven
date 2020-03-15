package com.mycompany.maventexteditor;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.swing.JFileChooser;

public class FXMLController implements Initializable {
    String opSystem = new String();
    String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createOpeningFolder();
    }    
}
