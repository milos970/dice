import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.File;
import javax.swing.JRadioButton;

public class Hra implements ActionListener {
    private static final int CAKANIE = 1000;
    private static final int X = 200;
    private static final int Y = 200;
    
    private final NastavenieKociek nastavenieKociek;
    private final NastaveniePoctuKociek nastaveniePoctu;
    private final HodKockami hodKockami;
    private final Pocitadlo pocitadlo;
    private final Tlacitko tlacitko;
    private final MenicRychlosti menic;
    private final Histogram histogram;
    
    private Thread thread;
    private boolean pouzivatelomZastavene;
    private boolean vytvorene;
    private boolean bezi;
    private int zvolena;

}