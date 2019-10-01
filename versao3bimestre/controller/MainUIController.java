/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.controller;

import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import ch.makery.MainApp;
import ch.makery.model.Person;
import ch.makery.controller.PersonIOController;
import ch.makery.controller.BirthdayStatisticsControllerBridge;
import ch.makery.controller.PersonEditDialogController;
import ch.makery.controller.PersonOverviewController;
import ch.makery.controller.RootLayoutController;


public class MainUIController {
    
    private PersonIOController io;
    private MainApp application;
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Person> personData;
    
    public void MainUI(MainApp app){
        
        application = app;
    }
    
    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initialize(MainApp app, BorderPane rLayout, Stage pStage, ObservableList<Person> pData)
    throws IOException, JAXBException {
        
        application = app;
        rootLayout = rLayout;
        primaryStage = pStage;
        personData = pData;
        
        io = new PersonIOController(primaryStage, personData);
        
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the application.
        RootLayoutController controller = loader.getController();
        controller.setMainApp((MainApp) application);

        primaryStage.show();

        // Try to load last opened person file.
        File file = io.getFilePath();
        if (file != null) {
            io.loadDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() throws IOException {
        
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        rootLayout.setCenter(personOverview);

        // Give the controller access to the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp((MainApp) application);
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) throws IOException {
        
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Set the dialog icon.
        dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    }
    
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics(ObservableList<Person> personData)
    throws IOException{
        
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        BirthdayStatisticsControllerBridge controller = loader.getController();
        controller.setPersonData(personData);

        // Set the dialog icon.
        dialogStage.getIcons().add(new Image("file:resources/images/calendar.png"));

        dialogStage.show();
            
    }
    
    public void setPersonData(ObservableList<Person> personData){
        this.personData = personData;
    }
}
