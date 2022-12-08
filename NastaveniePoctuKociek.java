import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import java.util.Enumeration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NastaveniePoctuKociek implements ActionListener  {
    private static final int POCET_KOCIEK_NA_VYBER = 4;
    private final ButtonGroup bgroup;
    private final JRadioButton[] radioButtons;
    private int zvolenaHodnota;
    
    public NastaveniePoctuKociek(Pozicia pozicia)  
    {
        
        this.bgroup = new ButtonGroup();
        
        this.radioButtons = new JRadioButton[POCET_KOCIEK_NA_VYBER];
        
        for (int i = 0; i < this.radioButtons.length; ++i) {
            this.radioButtons[i] = new JRadioButton(String.valueOf(i + 1));
            this.radioButtons[i].setBounds(pozicia.x + (i * 50), pozicia.y, pozicia.sirka, pozicia.vyska);
            this.radioButtons[i].setVisible(true);
            this.radioButtons[i].setName(String.valueOf(i + 1));
            Menu.getInstancia().vlozDoPanelaPonuka(this.radioButtons[i]);
        }

        for (int i = 0; i < this.radioButtons.length; ++i) {
            this.bgroup.add(radioButtons[i]);
        }
        
        

        this.radioButtons[1].setSelected(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        this.zvol();
    }

    public void aktivuj(boolean hodnota) 
    {
        for (int i = 0; i < this.radioButtons.length; ++i) {
            this.radioButtons[i].setEnabled(hodnota);
        }
    }

    private void zvol() 
    {
        for (Enumeration<AbstractButton> buttons = this.bgroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                this.zvolenaHodnota = Integer.parseInt(button.getName());
            }
        }
    }
    
    public void addActionListener(ActionListener actionListener) 
    {
        for (int i = 0; i < this.radioButtons.length; ++i) {
            this.radioButtons[i].addActionListener(actionListener);
        }
    }
    
    public int zvolena() 
    {
        return this.zvolenaHodnota;
    }
}