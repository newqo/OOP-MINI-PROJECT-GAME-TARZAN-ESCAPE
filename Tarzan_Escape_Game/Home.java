import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Home extends JPanel
{   
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 960;
    public static final int MARGIN = 100;

    // Home page Background 
    private URL ImageHomeURL = this.getClass().getResource("Image/home.png");
    private Image ImageHome = new ImageIcon(ImageHomeURL).getImage();

    //Start button
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonX = 0; // FLowlayout center
    int buttonY = SCREEN_WIDTH/2 + (MARGIN/2);
    Font bt_font = new Font("Open Sans", Font.BOLD, 20);
    JButton startButton = new JButton("Start");
    Home()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER ,buttonX,buttonY));

        startButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        startButton.setFont(bt_font);
        add(startButton);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Draw Home page
        g.drawImage(ImageHome,0,0,this);
    }
}
