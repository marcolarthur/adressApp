/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.controller.Interface;

import java.util.List;
import ch.makery.model.Person;

public interface IBirthdayStatisticsController {

    /**
     * Sets the persons to show the statistics for.
     *
     * @param persons
     */
    void setPersonData(List<Person> persons);
    
}
