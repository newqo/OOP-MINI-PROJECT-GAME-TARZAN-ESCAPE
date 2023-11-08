import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class InGame extends JPanel
{
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 960;
    public static final int MARGIN = 100;

    public static final int PLANK_MARGIN_BOTTOM = 200;
    public static final int PLANK_MARGIN_LEFT = 90;
    public static final int PLANK_PADDING = 110;
    public static final int TWOPLANK_PADDING = 40;

    public static final int SHARK_MARGIN = 20;

    // when click home button in end game and start new game;
    public void RestartGame()
    {
        homeButton.setVisible(false);

        // reset plank's coordinate
        // floor 1
        fl1_pl1.setY(PLANK_PADDING + (6 * MARGIN));
        fl1_pl2.setY(PLANK_PADDING + (6 * MARGIN));
        fl1_pl3.setY(PLANK_PADDING + (6 * MARGIN));

        // floor 2
        fl2_pl1.setY(PLANK_PADDING + (4 * MARGIN));
        fl2_pl2.setY(PLANK_PADDING + (4 * MARGIN));

        // floor 3
        fl3_pl1.setY(PLANK_PADDING + (2 * MARGIN));
        fl3_pl2.setY(PLANK_PADDING + (2 * MARGIN));
        fl3_pl3.setY(PLANK_PADDING + (2 * MARGIN));

        // floor 4
        fl4_pl1.setY(PLANK_PADDING);
        fl4_pl2.setY(PLANK_PADDING);

        // // reset shark's coordinate
        shark.setX(SCREEN_WIDTH - MARGIN - (2 * MARGIN) - (MARGIN / 2));
        shark.setY(SCREEN_HEIGHT - 100);
        Shark_Image = 0;
        shark_jumping = true;
        Shark_count_forward = 0;
        Shark_count_jump = 0;

        // reset tarzan spawn
        tarzan.setX(fl3_pl1.getX());
        tarzan.setY(fl3_pl1.getY());

        // reset hp and coin of tarzan
        int health = 3;
        tarzan.setHp(health);
        coint_amount = 0;
    }

    //Background
    URL ImageBackgroundURL = this.getClass().getResource("Image/Map.png");
    Image ImageBackground = new ImageIcon(ImageBackgroundURL).getImage();

    // --------------------------------------------------------------------------------------------------------------------
    // Win display
    URL ImageWinURL = this.getClass().getResource("Image/win.png");
    Image ImageWin = new ImageIcon(ImageWinURL).getImage();
    // --------------------------------------------------------------------------------------------------------------------
    // Lose Display
    URL ImageLoseURL = this.getClass().getResource("Image/lose.png");
    Image ImageLose = new ImageIcon(ImageLoseURL).getImage();
    // --------------------------------------------------------------------------------------------------------------------
    // Button in Endgame
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonX = SCREEN_WIDTH/2 - MARGIN;
    int buttonY = SCREEN_HEIGHT/2 + (2 * MARGIN);
    Font bt_font = new Font("Open Sans", Font.BOLD, 20);
    JButton homeButton = new JButton("HOME");

    // --------------------------------------------------------------------------------------------------------------------
    //Tsunami
    TSUNAMI tsunami = new TSUNAMI(0, SCREEN_HEIGHT - (9* (MARGIN/4)));
    int TsunamiMotion = 0;
    // --------------------------------------------------------------------------------------------------------------------
    
    // PlANK
    public void UpdatePlank(PLANK p)
    {   
        int plank_speed = 5;
        int start = 0;
        if(p.getY() >= getWidth() - MARGIN)
        {
            p.setY(start);
        }
        else
        {
            p.UpdateY(plank_speed);
        }
    }
    //Plank pattern 2 (Long Plank)
    URL ImagePlankP2URL = this.getClass().getResource("Image/Long_Plank.png");
    Image ImagePlankP2 = new ImageIcon(ImagePlankP2URL).getImage();

    // Coordinate (x,y) of plank
    // floor 1
    PLANK fl1_pl1 = new PLANK(PLANK_MARGIN_LEFT,  PLANK_PADDING + (6 * MARGIN));
    PLANK fl1_pl2 = new PLANK(PLANK_MARGIN_LEFT + (2 * PLANK_PADDING) + MARGIN, PLANK_PADDING + (6 * MARGIN));
    PLANK fl1_pl3 = new PLANK(PLANK_MARGIN_LEFT + (5 * PLANK_PADDING) + MARGIN, PLANK_PADDING + (6 * MARGIN));

    // floor 2
    PLANK fl2_pl1 = new PLANK(PLANK_MARGIN_LEFT + (PLANK_PADDING/2) + MARGIN, PLANK_PADDING + (4 * MARGIN));
    PLANK fl2_pl2 = new PLANK(PLANK_MARGIN_LEFT + TWOPLANK_PADDING + (PLANK_PADDING/2) + (4 * MARGIN), PLANK_PADDING + (4 * MARGIN));

    // floor 3
    PLANK fl3_pl1 = new PLANK(PLANK_MARGIN_LEFT, PLANK_PADDING + (2 * MARGIN));
    PLANK fl3_pl2 = new PLANK(PLANK_MARGIN_LEFT + (2 * PLANK_PADDING) + MARGIN, PLANK_PADDING + (2 * MARGIN));
    PLANK fl3_pl3 = new PLANK(PLANK_MARGIN_LEFT + (5 * PLANK_PADDING) + MARGIN, PLANK_PADDING + (2 * MARGIN));

    // floor 4
    PLANK fl4_pl1 = new PLANK(PLANK_MARGIN_LEFT + (PLANK_PADDING/2) + MARGIN, PLANK_PADDING);
    PLANK fl4_pl2 = new PLANK(PLANK_MARGIN_LEFT + TWOPLANK_PADDING + (PLANK_PADDING/2) + (4 * MARGIN), PLANK_PADDING);
    
    ArrayList<PLANK> Planklist = new ArrayList<PLANK>();
    int random , x_random = 0 , y_random = 0 , last_plank_spawn = 5; // 5 is mean floor 3 plank 1 is Tarzan spawn
    // --------------------------------------------------------------------------------------------------------------------

    //Tarzan
    // Coordinate (x,y) of Tarzan Spawn at plank floor 3 plank 1
    
    private int xStart = fl3_pl1.getX();
    private int yStart = fl3_pl1.getY();

    TARZAN tarzan = new TARZAN(xStart, yStart);

    boolean left = false , right = true , jumbable = false , Spawn = false;
    int TarzanMotion = 0 , coint_amount = 0 , Tarzan_Max_Jump = 270 , Tarzan_count_jumpMotion = 0;

    // --------------------------------------------------------------------------------------------------------------------

    // Shark
    int Shark_Max_Jump = 500 , Shark_jump = 10 , Shark_count_jump = 0 , Shark_forward = 60, Shark_count_forward = 0 , Shark_Image = 0 ; 
    boolean shark_jumping = true;
    SHARK shark = new SHARK(SCREEN_WIDTH - MARGIN- (2 * MARGIN) - (MARGIN / 2),SCREEN_HEIGHT - 100);

    // --------------------------------------------------------------------------------------------------------------------
    // Thread timer
    private Thread timer;
    private int delay = 75;
    // --------------------------------------------------------------------------------------------------------------------
    InGame()
    {
        Planklist.add(fl1_pl1);
        Planklist.add(fl1_pl2);
        Planklist.add(fl1_pl3);
        Planklist.add(fl2_pl1);
        Planklist.add(fl2_pl2);
        Planklist.add(fl3_pl1);
        Planklist.add(fl3_pl2);
        Planklist.add(fl3_pl3);
        Planklist.add(fl4_pl1);
        Planklist.add(fl4_pl2);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode()) {
                    // when Tarzan Jump
                    case KeyEvent.VK_UP: 
                        if (tarzan.getY() >= MARGIN/2) 
                        {
                            // jump
                            if(jumbable == true)
                            {
                                for(int i = 0 ; i < 240 ; i ++)
                                {
                                    tarzan.UpdateY(-1);
                                    repaint();
                                }
                                repaint();
                            }
                        }
                        break;
                    // when Tarzan going left side
                    case KeyEvent.VK_LEFT:
                        if (tarzan.getX() >= 0 + MARGIN) 
                        {
                            tarzan.UpdateX(-20);
                            //  x -= 30;
                        }
                        right = false; left = true;
                        break;
                    // when Tarzan going right side
                    case KeyEvent.VK_RIGHT:
                        if (tarzan.getX() + MARGIN < SCREEN_WIDTH) 
                        {
                            tarzan.UpdateX(20);
                            //  x += 30;
                        }
                        right = true; left = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        if(tarzan.getY() < SCREEN_HEIGHT - (5 * MARGIN))
                        {
                            tarzan.UpdateY(100);
                        }
                    default:
                        break;
                }
                repaint();
            }
        });
        timer = new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (true) {
                    try{
                        repaint();
                        Thread.sleep(delay);
                    }catch(Exception e)
                    {

                    }
                }
            }
        });
        timer.start();

        homeButton.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(tarzan.getHp() == 0) // when tarzan died because he got 3 hit from shark or downing in Tsunami (Lose Display)
        {
            // Draw lose display
            g.drawImage(ImageLose,0,0,this);

        }
        else if (coint_amount == 10) // when tarzan got 10 coins (Win display)
        {
            // Draw win display
            g.drawImage(ImageWin,0,0,this);
        }
        else
        {
            // Draw Map
            g.drawImage(ImageBackground, 0, 0, this);
            // --------------------------------------------------------------------------------------------------------------------

            // Draw Plank Pattern
            // --------------------------------------------------------------------------------------------------------------------
            // floor 4 (2 Plank)
            // fl4_pl1
            g.drawImage(ImagePlankP2, fl4_pl1.getX() , fl4_pl1.getY(), this);
            // fl4_pl2
            g.drawImage(ImagePlankP2, fl4_pl2.getX() , fl4_pl2.getY(), this);
            
            // floor 3 (3 Plank)
            // fl3_pl1
            g.drawImage(ImagePlankP2, fl3_pl1.getX(), fl3_pl1.getY(), this);
            // fl3_pl2
            g.drawImage(ImagePlankP2, fl3_pl2.getX(), fl3_pl2.getY(), this);
            // fl3_pl3
            g.drawImage(ImagePlankP2, fl3_pl3.getX(), fl3_pl3.getY(), this);
            
            // floor 2 (2 Plank)
            // fl2_pl1
            g.drawImage(ImagePlankP2, fl2_pl1.getX(), fl2_pl1.getY(), this);
            // fl2_pl2
            g.drawImage(ImagePlankP2, fl2_pl2.getX(), fl2_pl2.getY() , this);
            
            // floor 1 (3 Plank)
            // fl1_pl1
            g.drawImage(ImagePlankP2, fl1_pl1.getX() ,fl1_pl1.getY(), this);
            // fl1_pl2
            g.drawImage(ImagePlankP2, fl1_pl2.getX(), fl1_pl2.getY(), this);
            // fl1_pl3
            g.drawImage(ImagePlankP2, fl1_pl3.getX(), fl1_pl3.getY() , this);
            // --------------------------------------------------------------------------------------------------------------------

            // Draw Coin
            if(Spawn == false)
            {
                Random rand = new Random();
                do
                {
                    random = rand.nextInt(Planklist.size());
                }while(random == last_plank_spawn); // compare between new coin spawn and last coin spawn. it check to not spawn at same place
                x_random = Planklist.get(random).getX() + 60;
                y_random = Planklist.get(random).getY();
                Spawn = true;
                last_plank_spawn = random;
            }
            COIN coin = new COIN(x_random, y_random,coint_amount);
            
            if(Spawn == true)
            {
                coin.y = Planklist.get(random).getY();
                g.drawImage(coin.ImgCoin.getImage(), x_random, coin.y - 60, coin.width, coin.height, this);
            }

            if(tarzan.GetCoin(coin) == true)
            {
                coint_amount++;
                Spawn = false;
            }
            // --------------------------------------------------------------------------------------------------------------------
            // Draw Tarzan
            int down;
            if(tarzan.IsOnPlank(fl1_pl1) == true ||
            tarzan.IsOnPlank(fl1_pl2) == true ||
            tarzan.IsOnPlank(fl1_pl3) == true ||
            tarzan.IsOnPlank(fl2_pl1) == true ||
            tarzan.IsOnPlank(fl2_pl2) == true ||
            tarzan.IsOnPlank(fl3_pl1) == true ||
            tarzan.IsOnPlank(fl3_pl2) == true ||
            tarzan.IsOnPlank(fl3_pl3) == true ||
            tarzan.IsOnPlank(fl4_pl1) == true ||
            tarzan.IsOnPlank(fl4_pl2) == true)
            {
                jumbable = true;
                down = 0;
            }
            else
            {
                jumbable = false;
                down = 20;
                tarzan.UpdateY(down);
            }

            if(right == true)
            {
                g.drawImage(tarzan.im_right_animation[TarzanMotion].getImage(), tarzan.getX(), tarzan.getY() + down ,tarzan.getWidth(),tarzan.getHeight(),this);
            }
            else
            {
                g.drawImage(tarzan.im_left_animation[TarzanMotion].getImage(),tarzan.getX(), tarzan.getY() + down ,tarzan.getWidth(),tarzan.getHeight(),this);
            }
            TarzanMotion++;
            TarzanMotion %= 4;
            // outline of tarzan
            // g.drawRect(tarzan.getX(), tarzan.getY() + down ,tarzan.getWidth(),tarzan.getHeight());
            // --------------------------------------------------------------------------------------------------------------------
            // Draw Shark
            // Update Shark Jump
            if(Shark_count_jump < Shark_Max_Jump && shark_jumping == true) // jump
            {
                shark.UpdateY(Shark_jump);
                Shark_count_jump += Shark_jump;
            }
            else if (Shark_count_jump == Shark_Max_Jump && shark_jumping == true) // when shark at highest jump
            {
                Shark_Image = 1;
                shark.UpdateX(Shark_forward);
                shark_jumping = false;
                Shark_count_jump += Shark_jump;
                Shark_count_forward += Shark_forward;
            }
            else if (shark_jumping == false && Shark_count_jump > 0) // fall
            {
                Shark_count_jump -= Shark_jump;
                Shark_Image = 2;
                shark.UpdateY(-Shark_jump);
            }
            else if(Shark_count_jump == 0)
            {
                Shark_Image = 0;
                shark_jumping = true;
                if(shark.getHit() == 0)
                {
                    shark.setHit(1);
                }
            }
            if(Shark_count_forward == 600 && Shark_count_jump == 0)
            {
                shark.setX(SCREEN_WIDTH - (2*MARGIN));
                shark.setY(SCREEN_HEIGHT - 100);
                Shark_count_forward = 0;
            }

            g.drawImage(shark.Img_shark[Shark_Image].getImage(), shark.getX(), shark.getY(), shark.getWidth(), shark.getHeight(),this);
            // outline of shark
            // g.drawRect(shark.getX(), shark.getY(), shark.width, shark.height);
            // --------------------------------------------------------------------------------------------------------------------
            // Draw Tsunami
            g.drawImage(tsunami.Img_tsunami[TsunamiMotion].getImage(),tsunami.getX()-10,tsunami.getY(),tsunami.getWidth()+10,tsunami.getHeight(),this);
            // outline of tsunami
            // g.drawRect(tsunami.getX(),tsunami.getY(),tsunami.getWidth(), tsunami.getWidth());
            TsunamiMotion++;
            TsunamiMotion%=tsunami.Img_tsunami.length;

            // --------------------------------------------------------------------------------------------------------------------
            // Draw Display
            g.setFont(new Font("Open Sans",Font.BOLD,40));
            
            g.setColor(new Color(0x4a4077));
            g.fillRoundRect(0,0,160,175,25,25);

            g.setColor(Color.white);
                    
            //Draw Heart Display
            int HeartX = MARGIN/8;
            int HeartY = MARGIN/6;
            int Heart_PADDING  = 10;
            int Heart_info_Padding = (MARGIN/2) + (MARGIN/4);
            g.drawImage(tarzan.Img_heart.getImage(),HeartX,HeartY,coin.width,coin.height, this);
            g.drawString(": " + tarzan.getHp(),HeartX + Heart_info_Padding ,HeartY + (MARGIN/3) + (3*Heart_PADDING)/2);

            //Draw Coin Display
            int CoinX = HeartX;
            int CoinY = (MARGIN/2) + (MARGIN/4) + CoinX;
            int Coin_info_Padding = Heart_info_Padding;
            g.drawImage(coin.ImgCoin.getImage(), CoinX, CoinY,coin.width,coin.height, this);
            g.drawString(": " + coin.coin,CoinX+Coin_info_Padding,CoinY + (CoinY/2) + (Coin_info_Padding/10));
            // --------------------------------------------------------------------------------------------------------------------

            // Update Coordinate y of Plank to move downward
            UpdatePlank(fl1_pl1);
            UpdatePlank(fl1_pl2);
            UpdatePlank(fl1_pl3);
            UpdatePlank(fl2_pl1);
            UpdatePlank(fl2_pl2);
            UpdatePlank(fl3_pl1);
            UpdatePlank(fl3_pl2);
            UpdatePlank(fl3_pl3);
            UpdatePlank(fl4_pl1);
            UpdatePlank(fl4_pl2);

            // --------------------------------------------------------------------------------------------------------------------
            // Update tarzan hp
            if(tarzan.GotHitAttack(shark) == true)
            {
                if(tarzan.getHp() > 0)
                {
                    tarzan.UpdateHp(shark.getHit());
                    shark.setHit(0);
                }
            }
            if(tarzan.Downing(tsunami) == true) // if tarzan fall in tsunami (tarzan downing)
            {
                int death = 0;
                tarzan.setHp(death);
            }
            // --------------------------------------------------------------------------------------------------------------------
        }
        if(tarzan.getHp() == 0 || coint_amount == 10)
        {
            homeButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            homeButton.setFont(bt_font);
            add(homeButton);
            homeButton.setVisible(true);
        }
    }
}
