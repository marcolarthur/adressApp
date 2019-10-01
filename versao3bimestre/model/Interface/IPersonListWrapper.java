/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.model.Interface;

import java.util.List;
import ch.makery.model.Person;

public interface IPersonListWrapper {
    
    public List<Person> getPersons();
    public void setPersons(List<Person> persons);
}
