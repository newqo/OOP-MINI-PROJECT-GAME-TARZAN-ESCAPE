import java.awt.geom.Rectangle2D;

public class PLANK 
{
    public int x;
    public int y;
    public int width = 210;
    public int height = 30;
    
    PLANK(int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void UpdateX(int x)
    {
        this.x += x;
    }
    public void UpdateY(int y)
    {
        this.y += y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,width,height));
    }
}
