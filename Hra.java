import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.File;
import javax.swing.JRadioButton;

public class Hra implements ActionListener {
    private final NastavenieKociek nastavenieKociek;
    private final NastaveniePoctuKociek nastaveniePoctu;
    private final HodKockami hodKockami;
    private final Pocitadlo pocitadlo;
    private final Tlacitko tlacitko;
    private final MenicRychlosti menic;
    private Thread thread;
    private boolean pouzivatelomZastavene = false;
    private boolean vytvorene = false;
    private boolean bezi = false;

    private static final int CAKANIE = 1000;
    private static final int X = 200;
    private static final int Y = 200;

    public Hra()  {
        

        this.hodKockami = new HodKockami();
        this.nastavenieKociek = new NastavenieKociek();
        this.nastavenieKociek.aktivuj(true);

        this.pocitadlo = new Pocitadlo(new Pozicia( 170, 350,100,100));
        this.pocitadlo.zobraz();

        this.tlacitko = new Tlacitko(new Pozicia( 140, 720,100,50));
        this.tlacitko.addActionListener(this);
        this.tlacitko.zobraz();

        this.menic = new MenicRychlosti(new Pozicia( 100, 280,150,150));
        this.menic.zobraz();

        this.nastaveniePoctu = new NastaveniePoctuKociek(new Pozicia( 100, 20,50,50));
        this.nastaveniePoctu.addActionListener(this);
        this.nastaveniePoctu.addActionListener(this.nastaveniePoctu);       
        this.nastavenieKociek.zobrazKocky(2);  

        this.hodKockami.zobrazKocky(2);
    }

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

        if (bezi == false && (e.getSource() instanceof JRadioButton)) {
            JRadioButton b = (JRadioButton)e.getSource();
            this.nastavenieKociek.zobrazKocky(this.nastaveniePoctu.zvolena());
            this.hodKockami.zobrazKocky(this.nastaveniePoctu.zvolena());
            return;
        }

        if (bezi == false) {
            this.pocitadlo.vynuluj();
            this.nastavenieKociek.aktivuj(false);
            this.nastaveniePoctu.aktivuj(false);
            this.bezi = true;
            this.pouzivatelomZastavene = false;
            this.tlacitko.aktivuj();
            this.thread.start();
        } else {
            
            this.pouzivatelomZastavene = true;
        }

    }


    private void spust() 
    {
        int sucetHodov = 0;
        int hodKociek =  0;
        do 
        {

            try
            {
                this.thread.sleep(CAKANIE / (1 * this.menic.aktualnaHodnota()));
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }  

            if (this.pouzivatelomZastavene == true) {
                this.tlacitko.aktivuj();
                this.nastavenieKociek.aktivuj(true);
                this.nastaveniePoctu.aktivuj(true);
                this.bezi = false;
                this.vytvorene = false;
                this.pocitadlo.vynuluj();
                this.thread.stop();
                
            } else {
                sucetHodov = this.nastavenieKociek.sucetStran();
                hodKociek = this.hodKockami.hod();

                this.pocitadlo.zapocitaj();
            }

        } while(sucetHodov != hodKociek );

        this.tlacitko.aktivuj();
        this.nastavenieKociek.aktivuj(true);
        this.nastaveniePoctu.aktivuj(true);
        this.bezi = false;
        this.vytvorene = false;
    }
}