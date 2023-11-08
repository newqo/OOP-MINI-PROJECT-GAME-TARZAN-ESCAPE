import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class TSUNAMI 
{
    private int x;
    private int y;
    private int width = 960;
    private int height = 162;
    public ImageIcon[] Img_tsunami = new ImageIcon[5];

    TSUNAMI(int x , int y)
    {
        this.x = x;
        this.y = y;
        for(int i=0;i<Img_tsunami.length;i++)
        {
            Img_tsunami[i] = new ImageIcon(this.getClass().getResource("Image/Tsunami_"+(i+1)+".png"));
        }
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,width,height));
    }
}
