import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.File;
import javax.swing.JRadioButton;

public class Hra {
    public Hra() {
        
    }
    
    /**
     * Vytvorenie vlákna, zobrazenie grafu, kociek podľa zvoleného počtu
     * Spustenie a zastavenie hry cez tlacitko
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {

        if (this.vytvorene == false) {
            this.vytvorene = true;
            this.thread = null;
            this.thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        spust();
                    }
                }); 
        }

        //pokial hra nebezi a je kliknuté na RadioButton
        //osetruje prípad, kedy je kliknuté na rovnaký RadioButton
        if (bezi == false && (e.getSource() instanceof JRadioButton)) {
            if (this.zvolena == this.nastaveniePoctu.zvolena()) {
                return;
            }
            this.zvolena = this.nastaveniePoctu.zvolena();
            this.nastavenieKociek.zobrazKocky(this.nastaveniePoctu.zvolena());
            this.hodKockami.zobrazKocky(this.nastaveniePoctu.zvolena());
            
            //podla zvoleneho poctu kociek vytvori prazdny graf
            this.histogram.nastavPocetKociek(this.nastaveniePoctu.zvolena());
            this.histogram.vykresli();
            //
            return;
        }

        //spustí hru
        if (bezi == false) {
            this.pocitadlo.vynuluj();
            this.nastavenieKociek.aktivuj(false);
            this.nastaveniePoctu.aktivuj(false);
            this.bezi = true;
            this.pouzivatelomZastavene = false;
            this.tlacitko.aktivuj();
            this.thread.start();
        } else {
            //zastaví hru
            this.pouzivatelomZastavene = true;
        }
    }
}
