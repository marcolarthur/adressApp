package ch.makery.controller;

import ch.makery.controller.Interface.IRootLayoutController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javax.xml.bind.JAXBException;
import ch.makery.MainApp;
import ch.makery.util.AlertWrapper;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController implements IRootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    
    private PersonIOController personController;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        
        personController = new PersonIOController(mainApp.getPrimaryStage(), mainApp.getPersonData());
        try {
            personController.Open();
        } catch (JAXBException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        
        personController = new PersonIOController(mainApp.getPrimaryStage(), mainApp.getPersonData());
        try {
            personController.Save();
        } catch (JAXBException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        
        personController = new PersonIOController(mainApp.getPrimaryStage(), mainApp.getPersonData());
        try {
            personController.SaveAs();
        } catch (JAXBException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        
        AlertWrapper.showInformation(
            "Adress App",
            "About",
            "Author: Marco Jakob http://code.makery.ch/");
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
      mainApp.showBirthdayStatistics();
    }
}