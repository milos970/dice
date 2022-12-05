import OSPRNG.UniformDiscreteRNG;
import java.io.File;

public class Kocka extends Obrazok
{
    private final String[] strany;
    private static final int POCET_STRAN = 6;
    private int strana;

    public Kocka() 
    {
        
        super.setVisible(false);
        this.strany = new String[POCET_STRAN];
        
        for (int i = 0; i < POCET_STRAN; ++i) {
            this.strany[i] = "./obrazky/stranyKocky/" + (i + 1) + ".png";
        }
        
        
    }
    
    public void zobraz() {
        this.zobrazStranu(1);
        super.zobraz();
    }

    public void zobrazStranu(int strana) 
    {
        super.zmenObrazok(this.strany[strana - 1]);
    }

    public void nextStrana(boolean hodnota) {
        if (hodnota) {
            this.strana = (this.strana + 1) % POCET_STRAN;
            this.zobrazStranu(this.strana + 1);
        }
    }

    public int aktualnaStrana() {
        return this.strana + 1;
    }

    public int getPocetStran() {
        return POCET_STRAN;
    }
}