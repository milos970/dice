import javax.swing.JButton;
import java.awt.event.ActionListener;

public class Tlacitko     
{
    private final JButton button;
    private boolean aktivne;
    
    public Tlacitko(Pozicia pozicia) 
    {
        this.button = new JButton("Spustiť");
        this.button.setBounds(pozicia.x, pozicia.y, pozicia.sirka, pozicia.vyska);
        this.button.setVisible(false);
        
        
        Menu.getInstancia().vlozDoPanelaPonuka(this.button);
        
        this.aktivne = true;
    }
    
    
    public void zobraz() 
    {
        this.button.setVisible(true);
    }

    
    /**
     * Text sa po klikaní cyklí
     */

    public void aktivuj() 
    {
        if (this.aktivne == true) {
            this.button.setText("Stop");
            this.aktivne = false;
        } else {
            this.button.setText("Spustiť");
            this.aktivne = true;
        }
    }
    
      
    public void addActionListener(ActionListener actionListener) 
    {
        this.button.addActionListener(actionListener);
    }
}