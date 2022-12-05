import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Image;
import java.awt.Graphics2D;

public class Obrazok extends JLabel {
    private int x;
    private int y;
    
    public Obrazok() {
        super.setVisible(false);
    }
    
    public void zobraz() {
        super.setVisible(true);
    }
    
    public void zmenObrazok(String cestaKSuboru) {
        super.setIcon(nacitajObrazok(cestaKSuboru));
    }
    
    public void zmenPolohu(Pozicia pozicia) {
        this.x = pozicia.x;
        this.y = pozicia.y;
        
        super.setBounds(pozicia.x, pozicia.y, pozicia.sirka, pozicia.vyska);
    }
    
    private ImageIcon nacitajObrazok(String cestaKObrazku) {   
        BufferedImage img = null;
        try
        {
             img = ImageIO.read(new File(cestaKObrazku));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        
       Image dimg = img.getScaledInstance(super.getWidth(), super.getHeight(),
        Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }
}