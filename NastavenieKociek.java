import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NastavenieKociek {
    private final Kocka[] kocky;
    private boolean aktivovane;
    private int pocetKociek;

    public NastavenieKociek() {
        this.kocky = new Kocka[4];

        for (int i = 0; i < this.kocky.length; ++i) 
        {
            this.kocky[i] = new Kocka();
            Menu.getInstancia().vlozDoPanelaNastavenie(this.kocky[i]);
        }

        for (Kocka kocka : this.kocky) {
            //každej kocke pridá schopnosť zareagovať na kliknutie spustením metódy dalsiaStrana 
            kocka.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    kocka.dalsiaStrana(aktivovane);
                }
            });
        }
        this.aktivovane = true;
    }


    /**
     * Aktivuje/deaktivuje zmenu strán kocky po kliknutí.
     */
    public void aktivuj(boolean hodnota) 
    {
        this.aktivovane = hodnota;
    }

    public void zobrazKocky(int pocetKociek) 
    {
         this.pocetKociek = pocetKociek;
        switch (pocetKociek) {
            case 1 -> {
                this.kocky[0].zmenPolohu(new Pozicia(220, 60, 100, 100));
                this.kocky[0].zobraz();
                this.kocky[1].setVisible(false);
                this.kocky[2].setVisible(false);
                this.kocky[3].setVisible(false);
            }
            case 2 -> {
                for (int i = 0; i < 2; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(150 + 120 * i, 60, 100, 100));
                    this.kocky[i].zobraz();
                }
                this.kocky[2].setVisible(false);
                this.kocky[3].setVisible(false);
            }
            case 3 -> {
                for (int i = 0; i < 3; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(100 + 120 * i, 60, 100, 100));
                    this.kocky[i].zobraz();
                }
                this.kocky[3].setVisible(false);
            }
            case 4 -> {
                for (int i = 0; i < 4; ++i) {
                    this.kocky[i].zmenPolohu(new Pozicia(150 + 120 * i, 15, 100, 100));

                    this.kocky[i].zobraz();
                    if (i > 1) {
                        this.kocky[i].zmenPolohu(new Pozicia(150 + 120 * (i - 2), 120, 100, 100));
                        this.kocky[i].zobraz();
                    }
                }
            }
        }
    }

    public int sucetStran() 
    {
        int sucet = 0;

        for (int i = 0; i < this.pocetKociek; ++i) 
        {
            sucet += this.kocky[i].aktualnaStrana();
        }

        return sucet;
    }

}