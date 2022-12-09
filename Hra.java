public class Hra {
    public Hra()  {
        

        this.hodKockami = new HodKockami();
        this.nastavenieKociek = new NastavenieKociek();
        this.nastavenieKociek.aktivuj(true);

        this.pocitadlo = new Pocitadlo(new Pozicia( 250, 350,100,100));
        this.pocitadlo.zobraz();

        this.tlacitko = new Tlacitko(new Pozicia( 230, 730,100,50));
        this.tlacitko.addActionListener(this);
        this.tlacitko.zobraz();

        this.menic = new MenicRychlosti(new Pozicia( 200, 280,150,150));
        this.menic.zobraz();

        this.nastaveniePoctu = new NastaveniePoctuKociek(new Pozicia( 200, 20,50,50));
        this.nastaveniePoctu.addActionListener(this);
        this.nastaveniePoctu.addActionListener(this.nastaveniePoctu);  
        this.nastaveniePoctu.zobraz();
        this.nastavenieKociek.zobrazKocky(2);  
        
        
        this.histogram = new Histogram(new Pozicia(0, 0, 347, 250), 2);
        this.histogram.nastavPocetKociek(2);
        

        this.hodKockami.zobrazKocky(2);
        this.zvolena = 2;
        
        this.bezi = false;
        this.vytvorene = false;
        this.pouzivatelomZastavene = false;
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
    
    private void spust() 
    {
        int sucetHodov = 0;
        int hodKociek =  0;
        this.histogram.vynulujGraf();
            
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

            //ak používateľ zastavil hru, je počítadlo vymazané a zvyšok aktivovaný pre ďalšie spustenie
            //inak pokračuje hodom a nastavuje graf a počítadlo
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
                
                this.histogram.pridajSucet(hodKociek);
                this.histogram.vykresli();
            
                this.pocitadlo.zapocitaj();
            }

        } while(sucetHodov != hodKociek );
        
        //ked sa uspešne nájde súčet, počítadlo nenuluje, aktivuje zvyšok pre možné ďalšie spustenie
        this.tlacitko.aktivuj();
        this.nastavenieKociek.aktivuj(true);
        this.nastaveniePoctu.aktivuj(true);
        this.bezi = false;
        this.vytvorene = false;
    }
    
    
}
 

  