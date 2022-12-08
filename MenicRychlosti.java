import javax.swing.JSlider;
import java.util.Hashtable;
import javax.swing.JLabel;

public class MenicRychlosti  {
    private static final int MIN = 45;
    private static final int MAX = 500;
    private final JSlider slider;
    
    public MenicRychlosti(Pozicia pozicia) {
        this.slider = new JSlider();
        this.slider.setBounds(pozicia.x, pozicia.y, pozicia.vyska, pozicia.sirka);

        this.slider.setMajorTickSpacing(85);
        
        this.slider.setMinimum(MIN);
        this.slider.setMaximum(MAX);
        
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 70 ), new JLabel() );
        labelTable.put( new Integer( 90 ), new JLabel() );
        this.slider.setLabelTable( labelTable );

        this.slider.setPaintLabels(false);
        this.slider.setVisible(true);

        Menu.getInstancia().vlozDoPanelaPonuka(this.slider);
    }

    public void zobraz() {
        this.slider.setVisible(false);
    }

    public double aktualnaHodnota() {
        return this.slider.getValue();
    }
}