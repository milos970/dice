import OSPRNG.UniformDiscreteRNG;
public class HodKockami
{
    private static final int POCET_KOCIEK = 4;
    private final UniformDiscreteRNG[] random;
    private final Kocka[] kocky;
    private int pocetKociekNaZobrazenie;

    public HodKockami()
    {
        this.kocky = new Kocka[POCET_KOCIEK];

        for (int i = 0; i < POCET_KOCIEK; ++i) 
        {
            this.kocky[i] = new Kocka();
            Menu.getInstancia().vlozDoPanelaHra(this.kocky[i]);
        }

        this.random = new UniformDiscreteRNG[POCET_KOCIEK];

        for (int i = 0; i < POCET_KOCIEK; ++i) 
        {
            this.random[i] = new UniformDiscreteRNG(1,this.kocky[i].getPocetStran());
        }

    }

    public void zobrazKocky(int pocetKociek) 
    {
        this.pocetKociekNaZobrazenie = pocetKociek;
        switch(pocetKociek) {
            case 1:
                this.kocky[0].zmenPolohu(new Pozicia(320, 300,150,150));
                this.kocky[0].zobraz();

                this.kocky[1].setVisible(false);
                this.kocky[2].setVisible(false);
                this.kocky[3].setVisible(false);
                break;
            case 2:
                for (int i = 0; i < 2; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(250 + 200 * i, 300,150,150));
                    this.kocky[i].zobraz();
                }
                this.kocky[2].setVisible(false);
                this.kocky[3].setVisible(false);
                break;
            case 3:
                for (int i = 0; i < 3; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(150 + 200 * i, 300,150,150));
                    this.kocky[i].zobraz();
                }
                this.kocky[3].setVisible(false);
                break;
            case 4:
                for (int i = 0; i < 4; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(250 + 200 * i, 220,150,150));

                    this.kocky[i].zobraz();
                    if (i > 1) {
                        this.kocky[i].zmenPolohu(new Pozicia(250 + 200 * (i - 2), 400,150,150 ));
                        this.kocky[i].zobraz();
                    }
                }
                break;
        }
    }

    public int hod()
    {
        int sucetHodov = 0;
        int hodnotaHodu = 0;

        for (int i = 0; i < this.pocetKociekNaZobrazenie; ++i) {
            hodnotaHodu = this.random[i].sample();
            sucetHodov += hodnotaHodu;
            this.kocky[i].zobrazStranu(hodnotaHodu);
        }

        return sucetHodov;
    }
}
