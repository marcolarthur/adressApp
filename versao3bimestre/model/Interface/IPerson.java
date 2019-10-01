/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.model.Interface;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Aluno
 */
public interface IPerson {
    
    public String getFirstName();
    public void setFirstName(String firstName);
    public StringProperty firstNameProperty();
    public String getLastName();
    public void setLastName(String lastName);
    public StringProperty lastNameProperty();
    public String getStreet();
    public void setStreet(String street);
    public StringProperty streetProperty();
    public int getPostalCode();
    public void setPostalCode(int postalCode);
    public IntegerProperty postalCodeProperty();
    public String getCity();
    public void setCity(String city);
    public StringProperty cityProperty();
    public LocalDate getBirthday();
    public void setBirthday(LocalDate birthday);
    public ObjectProperty<LocalDate> birthdayProperty();
}
