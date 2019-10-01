/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.view.Interface;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

public interface IFXChart {
    
    public CategoryAxis createChart(CategoryAxis xAxis, String[] categories);
    public BarChart<String, Integer> fillChart(BarChart<String, Integer> barChart, int[] monthCounter);
}
