import javax.swing.JSlider;
import java.util.Hashtable;
import javax.swing.JLabel;

public class MenicRychlosti  {
    private static final int MIN = 1;
    private static final int MAX = 10;
    private final JSlider slider;
    
    public MenicRychlosti(Pozicia pozicia) {
        this.slider = new JSlider();
        this.slider.setBounds(pozicia.x, pozicia.y, pozicia.sirka, pozicia.vyska);

        this.slider.setMajorTickSpacing(10);
        
        this.slider.setMinimum(MIN);
        this.slider.setMaximum(MAX);
        
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 1 ), new JLabel("Normálne") );
        labelTable.put( new Integer( 10 ), new JLabel("Rýchlo") );
        this.slider.setLabelTable( labelTable );

        this.slider.setPaintLabels(true);
        this.slider.setVisible(false);

        Menu.getInstancia().vlozDoPanelaPonuka(this.slider);
    }

    public void zobraz() {
        this.slider.setVisible(true);
    }

    public int aktualnaHodnota() {
        return this.slider.getValue();
    }
}