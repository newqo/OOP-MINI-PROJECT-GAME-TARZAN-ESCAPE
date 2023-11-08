import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class COIN 
{
    public int coin;
    public int x;
    public int y;
    public int width = 70;
    public int height = 70;
    public ImageIcon ImgCoin = new ImageIcon(this.getClass().getResource("Image/Coins.png"));
    
    COIN(int x, int y , int c)
    {
        this.x = x;
        this.y = y;
        this.coin = c;
    }

    public int getCoin() {
        return coin;
    }

    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,width,height));
    }
}
