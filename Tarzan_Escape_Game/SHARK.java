import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class SHARK 
{
    public int x;
    public int y;
    public int width = 200;
    public int height = 140;
    public int Hit = 1;
    public ImageIcon[] Img_shark = new ImageIcon[3];
    
    SHARK(int x , int y)
    {
        for(int i=0;i<Img_shark.length;i++)
        {
            Img_shark[i] = new ImageIcon(this.getClass().getResource("Image/Shark_"+(i+1)+".PNG"));
        }
        this.x = x;
        this.y = y;
    }
    
    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,width,height));
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void UpdateY(int n)
    {
        this.y -= n;
    }

    public void UpdateX(int n)
    {
        this.x -= n;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setHit(int hit) {
        Hit = hit;
    }
    public int getHit() {
        return Hit;
    }
}
