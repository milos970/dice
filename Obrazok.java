import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.IOException;
import java.awt.Image;

public class Obrazok extends JLabel 
{
    public Obrazok() 
    {
        super.setVisible(false);
        
        
    }

    public void zobraz() 
    {
        super.setVisible(true);
    }

    public void zmenObrazok(String cestaKSuboru) 
    {
        super.setIcon(nacitajObrazok(cestaKSuboru));
    }

    public void zmenPolohu(Pozicia pozicia) 
    {
        super.setBounds(pozicia.x, pozicia.y, pozicia.sirka, pozicia.vyska);
    }

    private ImageIcon nacitajObrazok(String cestaKObrazku) 
    {   
         BufferedImage img = null;
        try
        {
             img = ImageIO.read(getClass().getResource(cestaKObrazku));
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