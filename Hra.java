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

  