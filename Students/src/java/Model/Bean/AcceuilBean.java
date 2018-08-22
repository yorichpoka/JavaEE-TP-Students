/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Bean;

import java.io.Serializable;
import javafx.scene.chart.Axis;
import javax.annotation.PostConstruct;
//import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author POKA
 */
public class AcceuilBean extends Bean implements Serializable {

    private LineChartModel lineModel1;
    
    /**
     * Creates a new instance of MainBean
     */
    public AcceuilBean() {
    }

    @PostConstruct
    @Override
    public void initialisation() 
    {
        createLineModels();
    }
    
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
     
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Évolution des notes des étudiants");
        lineModel1.setLegendPosition("e");
        
//        Axis yAxis = lineModel1.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Étudiant 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Étudiant 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
        
        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("Étudiant 3");
 
        series3.set(1, 5);
        series3.set(2, 1);
        series3.set(3, 9);
        series3.set(4, 15);
        series3.set(5, 2);
 
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series3);
         
        return model;
    }
    
}
