/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naivgationdrawer.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javafx.scene.control.TextField;

/**
 *
 * @author Computer
 */
public class OtherTools {
    
    public static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;

    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source, 0, source.size());
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }
    }
}
    public static void ConditionText(TextField textField, int length) 
    {
    textField.setOnKeyTyped(event -> {
        String string = textField.getText();

        if (string.length() > length) 
        {
            textField.setText(string.substring(0, length));
            textField.positionCaret(string.length());
            
        }
        else 
        {
            if (textField.getText().length()!=length) 
            {
                textField.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } 
            else 
            {
                textField.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        
        }
    });
   }
    public static void ConditionText2(TextField textField) 
    {
        textField.setOnKeyTyped(event -> {
        String string = textField.getText();
        if (textField.getText().length()<1) 
            {
                textField.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } 
            else 
            {
                textField.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        });
    }
   

    
    
}
