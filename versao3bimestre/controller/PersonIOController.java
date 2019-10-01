/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import ch.makery.model.Person;
import ch.makery.model.PersonIOModel;

public class PersonIOController {
    
    private final Stage primaryStage;
    private ObservableList<Person> personData;
    private PersonIOModel personModel;
    
    public PersonIOController(Stage primaryStage, ObservableList<Person> personData){
        
        
        this.primaryStage = primaryStage;
        this.personData = personData;
        personModel = new PersonIOModel(primaryStage, "XML files (*.xml)", ".xml");
    }
    
    public File Open() throws JAXBException {

                
        try {
            File file = personModel.OpenFile();
            
            if (file != null) {
                try {
                    personModel.loadDataFromFile(file, personData);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PersonIOController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return file;
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(PersonIOController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public void Save() throws JAXBException {
        
        File personFile = personModel.getFilePath();
        if (personFile != null) {
            personModel.saveDataToFile(personFile, personData);
        } else {
            SaveAs();
        }
    }
    
    public void SaveAs() throws JAXBException {
        
        personModel.SaveAs(personData);
    }

        /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getFilePath(){
        
        return personModel.getFilePath();
    }
    
    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setFilePath(File file){
        
        personModel.setFilePath(file);
    }
    
        /**
     * Loads person data from the specified file.The current person data will
 be replaced.
     * 
     * @param file
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    public void loadDataFromFile(File file) throws JAXBException, FileNotFoundException {

        personModel.loadDataFromFile(file, personData);
    }
    
    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     * @throws javax.xml.bind.JAXBException
     */
    public void saveDataToFile(File file) throws JAXBException{

        personModel.saveDataToFile(file, personData);
    }
}
