/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.controller;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import ch.makery.model.Interface.IPerson;
import ch.makery.model.Person;
import ch.makery.view.FXChartSingleton;
import ch.makery.view.Interface.IFXChart;

public class BirthdayStatisticsControllerImp {
    
    private IFXChart birthdayChart;

    public BirthdayStatisticsControllerImp() {
        
        birthdayChart = FXChartSingleton.getNewInstance();
    }
    
    public void initialize(CategoryAxis xAxis){
        
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        
        birthdayChart.createChart(xAxis, months);
    }
    
    public void setPersonData(List<Person> persons, BarChart<String, Integer> barChart) {
        
        // Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (IPerson p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }
        
        birthdayChart.fillChart(barChart, monthCounter);
    }
}
