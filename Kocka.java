public class Kocka extends Obrazok
{
    private static final int POCET_STRAN = 6;
    private final String[] strany;
    private int cisloStrany;

    public Kocka() 
    {
        super.setVisible(false);
        this.strany = new String[POCET_STRAN];

        for (int i = 0; i < POCET_STRAN; ++i) 
        {
            this.strany[i] = "/obrazky/stranyKocky/" + (i + 1) + ".png";
        }
    }

    public void zobraz() 
    {
        this.zobrazStranu(1);
        this.cisloStrany = 0;
        super.zobraz();
    }

    public void zobrazStranu(int strana) 
    {
        super.zmenObrazok(this.strany[strana - 1]);
    }

    public void dalsiaStrana(boolean hodnota) 
    {
        if (hodnota) 
        {
            this.cisloStrany = (this.cisloStrany + 1) % POCET_STRAN;
            this.zobrazStranu(this.cisloStrany + 1);
        }
    }

    public int aktualnaStrana() 
    {
        return this.cisloStrany + 1;
    }

    public int getPocetStran() 
    {
        return POCET_STRAN;
    }
}