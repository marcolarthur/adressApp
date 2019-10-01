package ch.makery.address;

import ch.makery.address.controller.MainUIController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import ch.makery.address.model.Person;
import ch.makery.address.util.AlertWrapper;
import ch.makery.address.controller.PersonIOController;

/**
 * @author Marco Jakob
 */
public class Main extends Application {

    private MainUIController ui;
    private PersonIOController io;
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    /**
     * The data as an observable list of Persons.
     */
    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        
        // Add some sample data
        personData.add(new Person("Arthur", "Marcolino"));
        personData.add(new Person("Albert", "Einstein"));
        personData.add(new Person("Niels", "Bohr"));
        personData.add(new Person("Erwin", "Schrödinger"));
        personData.add(new Person("Werner", "Heisenberg"));
        personData.add(new Person("Peter", "Higgs"));
        personData.add(new Person("Maria", "Sk?odowska-Curie"));
        personData.add(new Person("Max", "Planck"));
        personData.add(new Person("Louis", "de Broglie"));
        
        
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
         this.primaryStage.setTitle("AddressApp");
        
        
        io = new PersonIOController(primaryStage, personData);
        
        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        
        try {
            
            try {
                
                ui = new MainUIController();
                ui.initialize(this, rootLayout, primaryStage, personData);
            } catch (JAXBException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            
            ui.showPersonOverview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     */
    public boolean showPersonEditDialog(Person person) {
        
        try {
            ui.showPersonEditDialog(person);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics() {
        
        try {
            ui.showBirthdayStatistics(personData);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getPersonFilePath() {
        
        return io.getFilePath();
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        
        io.setFilePath(file);
    }
    
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            
            file = io.Open();

        } catch (JAXBException e) {
                
                AlertWrapper.showError(
                        "Error",
                        "Last used data was deleted",
                        "Could not load data from file:\n" + file.getPath());
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            
            io.saveDataToFile(file);
            
        } catch (JAXBException e) {
                
                AlertWrapper.showError(
                        "Error",
                        "Could not save data",
                        "Could not save data to file:\n" + file.getPath());
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public MainUIController getUi(){
        return ui;
    }

    public static void main(String[] args) {
        launch(args);
    }
}