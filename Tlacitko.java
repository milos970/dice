import javax.swing.JButton;
import java.awt.event.ActionListener;

public class Tlacitko     
{
    private final JButton button;
    private boolean aktivne;
    
    public Tlacitko(Pozicia pozicia) 
    {
        this.button = new JButton("Spustiť");
        this.button.setBounds
        this.button.setVisible(false);
        
        
        Menu.getInstancia().vlozDoPanelaPonuka(this.button);
        
        this.aktivne = true;
    }
    
    public void zobraz() 
    {
        this.button.
    }
    
    public void aktivuj() 
    {
        if (this.aktivne == true) {
            this.button.setText("Stop");
            this.aktivne = false;
        } else {
            this.button.;
            this.aktivne = true;
        }
    }
    
      
    public void addActionListener
    {
        this.button.addActionListener();
    }
}