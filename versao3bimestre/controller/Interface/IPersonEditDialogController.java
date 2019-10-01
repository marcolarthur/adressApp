/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.controller.Interface;

import javafx.stage.Stage;
import ch.makery.model.Person;

public interface IPersonEditDialogController {

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    boolean isOkClicked();

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    void setDialogStage(Stage dialogStage);

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    void setPerson(Person person);
    
}
