import javax.swing.BorderFactory;
import java.awt.Color;
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
    private final Pozicia pozicia;
    private JFreeChart chart;
    private final ChartPanel chartPanel;
    
    /** 
     * Konštruktor, nastaví atribúty, nastaví veľkosť poľa podľa počtu kociek
     * Vytvorí nulové pole, vytvorí panel grafu, ktorý potom vykreslí cez metódu
     * 
     * @param pozicia - parameter pozície ktorú si ukladáme do atribútu
     * @param pocetKociek - parameter počtu kociek, ktoré budú v histograme
     */
    public Histogram(Pozicia pozicia, int pocetKociek) {
        this.pocetKociek = pocetKociek;
        this.nastavVelkostPola();
        this.pozicia = pozicia;
        this.poleSuctovHodov = new int[this.velkostPola];
        for (int i = 0; i < this.poleSuctovHodov.length; i++) {
            this.poleSuctovHodov[i] = 0;
        }
        this.chartPanel = new ChartPanel(this.chart);
        this.vykresli();
    }

    /**
     * Naplní a vytvorí graf, ktorý potom nastaví aby sa vykreslil na pozícií
     * a na správnom mieste v menu
     */
    public void vykresli() {
        CategoryDataset dataset = this.vytvorDataset();
        this.chart = this.vytvorGraf(dataset);
        
        this.chartPanel.setChart(this.chart);
        this.chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.chartPanel.setBackground(Color.white);
        this.chartPanel.setBounds(this.pozicia.x, this.pozicia.y, this.pozicia.sirka, this.pozicia.vyska);
        Menu.getInstancia().vlozDoPanelaGraf(this.chartPanel);
    }

    /**
     * Vytvorenie dát pre graf cez cyklus
     * @return vytvorené dáta
     */
    private CategoryDataset vytvorDataset() {
        var data = new DefaultCategoryDataset();
        for (int i = 0; i < this.poleSuctovHodov.length; i++) {
            data.setValue(this.poleSuctovHodov[i], "Pocet", String.valueOf(i + this.pocetKociek));
        }
        return data;
    }

    /**
     * @param dataset - na základe parametra data vytvorí graf
     */
    private JFreeChart vytvorGraf(CategoryDataset dataset) {

        return ChartFactory.createBarChart(
                "Početnosti súčtov hodov",
                "",
                "Počet",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }

    /**
     * Vynuluje počty súčtov
     */
    public void vynulujGraf() {
        for (int i = 0; i < this.poleSuctovHodov.length; i++) {
            this.poleSuctovHodov[i] = 0;
        }
    }
    
    /**
     * Nastavuje počet kociek a upraví veľkosť poľa
     * Pripraví prázdny graf
     * @param pocet - aký počet kociek chceme nastaviť
     */
    public void nastavPocetKociek(int pocet) {
        this.pocetKociek = pocet;
        this.nastavVelkostPola();
        this.vykresli();
    }
    
    /**
     * Na základe počtu kociek určí a zmení akú veľkosť bude mať pole
     * Následne vytvorí nové s novou veľkosťou
     */
    public void nastavVelkostPola() {
        switch (this.pocetKociek) {
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

    /**
     * Na základe počtu kociek a hodeného súčtu navýši v poli daný súčet o 1
     * @param sucet - hodený súčet na kockách
     */
    public void pridajSucet(int sucet) {
        switch (this.pocetKociek) {
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

}