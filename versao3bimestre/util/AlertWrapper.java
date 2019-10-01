/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.util;

import javafx.scene.control.Alert;

/**
 *
 * @author Aluno
 */
public class AlertWrapper {
    
    private static Alert alert;
    
    public static void showConfirmation(String title, String header, String content){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        showAlert(title, header, content);
    }
    
    public static void showError(String title, String header, String content){
        alert = new Alert(Alert.AlertType.ERROR);
        showAlert(title, header, content);
    }
    
    public static void showInformation(String title, String header, String content){
        alert = new Alert(Alert.AlertType.INFORMATION);
        showAlert(title, header, content);
    }
    
    public static void showBasic(String title, String header, String content){
        alert = new Alert(Alert.AlertType.NONE);
        showAlert(title, header, content);
    }
    
    public static void showWarning(String title, String header, String content){
        alert = new Alert(Alert.AlertType.WARNING);
        showAlert(title, header, content);
    }
    
    private static void showAlert(String title, String header, String content){
        
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
