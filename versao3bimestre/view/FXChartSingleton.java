/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.view;

import ch.makery.view.Interface.IFXChart;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Aluno
 */
public class FXChartSingleton implements IFXChart {
    
    private static FXChartSingleton instance = null;
    private ObservableList<String> categoryNames = FXCollections.observableArrayList();

    private FXChartSingleton() {}
    
    public static FXChartSingleton getInstance(){
        
        if (instance == null) {
            instance = getNewInstance();
        }
        
        return instance;
    }
    
    public static FXChartSingleton getNewInstance(){
        
        instance = new FXChartSingleton();
        return instance;
    }
    
    
    @Override
    public CategoryAxis createChart(CategoryAxis xAxis, String[] categories){
        
        
        // Convert it to a list and add it to our ObservableList of categories.
        categoryNames.addAll(Arrays.asList(categories));

        // Assign the names as categories for the horizontal axis.
        xAxis.setCategories(categoryNames);
        return xAxis;
    }
    
    @Override
    public BarChart<String, Integer> fillChart(BarChart<String, Integer> barChart, int[] monthCounter){
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each categories. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(categoryNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
        return barChart;
    }
}
