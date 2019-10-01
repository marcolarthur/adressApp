/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.model;

import ch.makery.model.Interface.IModelIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.prefs.Preferences;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ch.makery.MainApp;

/**
 *
 * @author Aluno
 */
public class PersonIOModel implements IModelIO {
    
    private final Stage primaryStage;
    private final String fileTypeDescription;
    private final String fileTypeStringExtension;
    
    public PersonIOModel(Stage primaryStage, String fileTypeDescription,
            String fileTypeStringExtension){
        
        this.primaryStage = primaryStage;
        this.fileTypeDescription = fileTypeDescription;
        this.fileTypeStringExtension = fileTypeStringExtension;
        
    }

    public File OpenFile() throws JAXBException{
        
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                fileTypeDescription, "*" + fileTypeStringExtension);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        return fileChooser.showOpenDialog(primaryStage);
    }

    public void Save(MainApp mainApp) throws JAXBException{
        
    }

    public void SaveAs(ObservableList<Person> personData) throws JAXBException{
        
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                fileTypeDescription, "*" + fileTypeStringExtension);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(fileTypeStringExtension)) {
                file = new File(file.getPath() + fileTypeStringExtension);
            }
            saveDataToFile(file, personData);
        }
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    @Override
    public File getFilePath(){
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    @Override
    public void setFilePath(File file){
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Adress App - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Adress App");
        }
    }
    
    /**
     * Loads person data from the specified file.The current person data will
 be replaced.
     * 
     * @param file
     * @param personData
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public void loadDataFromFile(File file, ObservableList<Person> personData) throws JAXBException, FileNotFoundException {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            try {
                
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setFilePath(file);
        } catch (JAXBException e) {}
    }
    
    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     * @param personData
     * @throws javax.xml.bind.JAXBException
     */
    @Override
    public void saveDataToFile(File file, ObservableList<Person> personData) throws JAXBException{
        
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setFilePath(file);
    }
    
    
}
