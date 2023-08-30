import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
/**@author Matej Gregor */
public class Pocitadlo  
{
    private static final int VELKOST_FONTU = 40;
    private final JLabel label;
    private int pocet;

    public Pocitadlo(Pozicia pozicia) 
    {
        this.label = new JLabel();
        this.label.setBounds(pozicia.x, pozicia.y, 100, 100);
        this.label.setFont(new Font("TimesRoman",Font.BOLD,VELKOST_FONTU));
        this.label.setForeground(Color.red);
        this.label.setVisible(false);
        
        Menu.getInstancia().vlozDoPanelaPonuka(this.label);
    }
/** Zobrazí počet opakovaní hodu
*/
    public void zobraz() 
    {
        this.label.setVisible(true);
    }
/**Počíta hody */
    public void zapocitaj() 
    {
        this.pocet++;
        this.label.setText(String.valueOf(this.pocet));
    }
/**nastaví počet hodov na nulu */
    public void vynuluj() 
    {
        this.pocet = 0;
        this.label.setText(String.valueOf(this.pocet));
    }


}