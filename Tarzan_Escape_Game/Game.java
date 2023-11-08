import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Game extends JFrame
{
    // Assign Screen Size 1280 x 960
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 960;
    public static final int MARGIN = 100;

    private Home home = new Home();
    private InGame ingame = new InGame();

    public Game()
    {
        add(home);
        home.setFocusable(true);
        
        home.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == home.startButton)
                {
                    Game.this.add(ingame);
                    ingame.setFocusable(true);
                    ingame.requestFocus();
                    Game.this.revalidate(); 
                }
            }
        });
        ingame.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == ingame.homeButton)
                {
                    ingame.RestartGame();
                    Game.this.remove(ingame);
                    Game.this.add(home);
                    home.setFocusable(true);
                    home.requestFocus();
                    Game.this.revalidate(); 
                    Game.this.repaint();
                }
            }
        });
    }
    
    
    public static void main(String[] args) {
        // Screen setting
        Game frame = new Game();
        frame.setTitle("TARZAN ESCAPE");
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}