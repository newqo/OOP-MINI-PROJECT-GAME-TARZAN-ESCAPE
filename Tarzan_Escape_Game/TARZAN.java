import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class TARZAN
{
    private int x;
    private int y;
    private int width = 60 , height = 64;
    private int hp = 3;
    public ImageIcon[] im_left_animation = new ImageIcon[4];
    public ImageIcon[] im_right_animation = new ImageIcon[4];
    public ImageIcon Img_heart = new ImageIcon(this.getClass().getResource("Image/Heart.png"));

    TARZAN(int x , int y)
    {
        this.x = x;
        this.y = y;

        // add Tarzan left animation to array
        for(int i=0;i<im_left_animation.length;i++)
        {
            im_left_animation[i] = new ImageIcon(this.getClass().getResource("Image/left_"+(i+1)+".PNG"));
        }
        // add Tarzan right animation to array
        for(int i=0;i<im_right_animation.length;i++)
        {
            im_right_animation[i] = new ImageIcon(this.getClass().getResource("Image/right_"+(i+1)+".PNG"));
        }
    }    

    public int getX() {
        return x;
    }
    public void UpdateX(int x) {
        this.x += x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void UpdateY(int y) {
        this.y += y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public void UpdateY(Double y) {
        this.y += y;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void UpdateHp(int hp) {
        this.hp -= hp;
    }
    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,width,height));
    }

    public boolean IsOnPlank(PLANK p) {
        if(((p.getbound()).intersects(getbound())))
        {
            return true;
        } else {
            return false;
        }
    }
    public boolean GetCoin(COIN c)
    {
        if((c.getbound()).intersects(getbound()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean GotHitAttack(SHARK sh)
    {
        if((sh.getbound()).intersects(getbound()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean Downing(TSUNAMI tsnm)
    {
        if((tsnm.getbound()).intersects(getbound()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
