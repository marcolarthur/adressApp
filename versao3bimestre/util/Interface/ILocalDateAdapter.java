/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.util.Interface;

import java.time.LocalDate;

/**
 *
 * @author Aluno
 */
public interface ILocalDateAdapter {

    String marshal(LocalDate v) throws Exception;

    LocalDate unmarshal(String v) throws Exception;
    
}
