import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Histogram  {
    private int[] poleSuctovHodov;
    private int velkostPola;
    private int pocetKociek;
    private Pozicia pozicia;
    
    private CategoryDataset dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    
    public Histogram(Pozicia pozicia, int pocetKociek) {
        this.pocetKociek = pocetKociek;
        //this.poleSuctovHodov = new int[] {10, 5, 4, 8, 5, 3, 2, 8, 4, 12, 5};
        this.nastavVelkostPola();
        this.pozicia = pozicia;
        this.poleSuctovHodov = new int[this.velkostPola];
        for (int i = 0; i < this.poleSuctovHodov.length; i++) {
            this.poleSuctovHodov[i] = 0;
        }
        this.chartPanel = new ChartPanel(this.chart);
        this.vykresli();
    }

    public void vykresli() {
        this.dataset = this.vytvorDataset();
        this.chart = this.vytvorGraf(this.dataset);
        
        this.chartPanel.setChart(this.chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setBounds(this.pozicia.x, this.pozicia.y, this.pozicia.sirka, this.pozicia.vyska);
        Menu.getInstancia().vlozDoPanelaGraf(this.chartPanel);
    }

    private CategoryDataset vytvorDataset() {
        var data = new DefaultCategoryDataset();
        for (int i = 0; i < this.poleSuctovHodov.length; i++) {
            data.setValue(this.poleSuctovHodov[i], "Pocet", i + this.pocetKociek + "");
        }
        /* Napriklad taketo data: pre jednotku nebol este sucet
         * pre dvojku uz 3x ...
        (0, "Pocet", "1");
        (3, "Pocet", "2");
        (10, "Pocet", "3");
        */
        return data;
    }

    private JFreeChart vytvorGraf(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Početnosti súčtov hodov",
                "",
                "Počet",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    public void vynulujGraf() {
            for (int i = 0; i < this.poleSuctovHodov.length; i++) {
                this.poleSuctovHodov[i] = 0;
            }
    }
    
    public void nastavPocetKociek(int pocet) {
        this.pocetKociek = pocet;
        this.nastavVelkostPola();
        this.vykresli();
    }
    
    public void nastavVelkostPola() {
        switch(this.pocetKociek) {
            case 1:
                this.velkostPola = 6;
                break;
            case 2:
                this.velkostPola = 11;
                break;
            case 3:
                this.velkostPola = 16;
                break;
            case 4:
                this.velkostPola = 21;
                break;
        }
        this.poleSuctovHodov = new int[this.velkostPola];
    }

    public void pridajSucet(int sucet) {
        switch(this.pocetKociek) {
            case 1:
                this.poleSuctovHodov[sucet - 1] += 1;
                break;
            case 2:
                this.poleSuctovHodov[sucet - 2] += 1;
                break;
            case 3:
                this.poleSuctovHodov[sucet - 3] += 1;
                break;
            case 4:
                this.poleSuctovHodov[sucet - 4] += 1;
                break;
        }
    }
    
    public void zobraz() {
        this.chartPanel.setVisible(true);
    }
}