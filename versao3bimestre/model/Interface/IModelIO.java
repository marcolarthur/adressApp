/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.model.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;
import ch.makery.Main;
import ch.makery.model.Person;

/**
 *
 * @author Aluno
 */
public interface IModelIO {

    public File OpenFile() throws JAXBException;

    public void Save(Main mainApp) throws JAXBException;

    public void SaveAs(ObservableList<Person> personData) throws JAXBException;
    
    public File getFilePath();
    
    public void setFilePath(File file);
    
    public void loadDataFromFile(File file, ObservableList<Person> personData) throws JAXBException, FileNotFoundException;
    
    public void saveDataToFile(File file, ObservableList<Person> personData) throws JAXBException;
    
    
}
