package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Theseus extends JPanel implements ActionListener, KeyListener {
    private int playerX = 0, playerY = 0;
    private Image playerImage, sailboatImage, closedTreasureChestImage, openTreasureChestImage, sailboatImage2, plank, moon, sky, nighttime, coinImage;
    private Image redShipBody, redShipFront, redShipBack, blueShipBody, blueShipFront, blueShipBack, lantern, speechImage, textBox;
    private int playerXVelocity = 0, playerYVelocity = 0;
    private Timer timer;
    private boolean jumped = false;
    private ArrayList<Particle> weatherParticles = new ArrayList<>();
    private String weatherType = "rain"; // Options: "rain", "snow"
    private int windDirection = 0; // -1 = left, 0 = none, 1 = right
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final int SAILBOAT_IMAGE_WIDTH = 500;
    private static final int SAILBOAT_IMAGE_HEIGHT = 500;
    private int coinX, coinY;
    private int coinsCollected;
    private static final int SAILBOAT_IMAGE_Y = WINDOW_HEIGHT - SAILBOAT_IMAGE_HEIGHT;
    private static String[] characterList = {"player.png", "player2.png", "player3.png", "player4.png", "player5.png", "player6.png", "player7.png", "player8.png", "player9.png", "player10.png"};
    private int currentCharacter = -1;
    private double speedBoost = 1.0;
    private int x_position = 750;
    private DayNightCycle dn;
    public Theseus() {
        //Team JAAS: Arnav Jha
        JOptionPane.showMessageDialog(this, "NOTICE: The following application contains copyrighted materials used under the Multimedia Guidelines and Fair Use exemptions of U.S. Copyright law. Further use is prohibited.");
        //JOptionPane.showMessageDialog(this, "Access easter egg by pressing P");
        playerImage = new ImageIcon("res\\player.png").getImage();
        sailboatImage = new ImageIcon("res\\ship.png").getImage();
        speechImage = new ImageIcon("res\\speechBox.png").getImage();
        textBox = new ImageIcon("res\\textBox.png").getImage();
        lantern = new ImageIcon("res\\lantern.png").getImage();
        redShipBody = new ImageIcon("res\\RedBoat\\RedShipBody.png").getImage();
        redShipFront = new ImageIcon("res\\RedBoat\\RedShipFront.png").getImage();
        redShipBack = new ImageIcon("res\\RedBoat\\RedShipBack.png").getImage();
        blueShipBody = new ImageIcon("res\\BlueBoat\\BlueShipBody.png").getImage();
        blueShipFront = new ImageIcon("res\\BlueBoat\\BlueShipFront.png").getImage();
        blueShipBack = new ImageIcon("res\\BlueBoat\\BlueShipBack.png").getImage();
        sky = new ImageIcon("res\\background.png").getImage();
        closedTreasureChestImage = new ImageIcon("res\\closedTreasureChestImage.png").getImage();
        plank= new ImageIcon("res\\wooden_plank-removebg-preview.png").getImage();
        moon = new ImageIcon("res\\moon.png").getImage();
        nighttime = new ImageIcon("res\\nighttimefilter.png").getImage();
        playerX = 1500;
        playerY = 600;
        timer = new Timer(20, this);
        timer.start();
        dn = new DayNightCycle();
        coinImage = new ImageIcon("res\\coin.png").getImage();
        coinX = (int) (Math.random() * (SAILBOAT_IMAGE_WIDTH - 120) + 1300);
        coinY = (int) (Math.random() * (SAILBOAT_IMAGE_HEIGHT - 500) + 640);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Blackjack game = new Blackjack();
        game.startGame();
        // TEAM: HAZA
        for (int i = 0; i < 150; i++) {
        int x = (int)(Math.random() * WINDOW_WIDTH);
        int y = (int)(Math.random() * WINDOW_HEIGHT);
        int speed = weatherType.equals("rain") ? 8 + (int)(Math.random() * 4) : 1 + (int)(Math.random() * 2);
        int size = weatherType.equals("rain") ? 1 : 5;
        Color color = weatherType.equals("rain") ? new Color(173, 216, 230, 180) : Color.WHITE;
        weatherParticles.add(new Particle(x, y, size, speed, color));
}

    }
    public void changeCharacter(){
        if (currentCharacter != characterList.length - 1){
            currentCharacter++;
            playerImage = new ImageIcon("res\\" + characterList[currentCharacter]).getImage();

        } else {
            currentCharacter = 0;
            playerImage = new ImageIcon("res\\" + characterList[currentCharacter]).getImage();
        }
        
    }
    public Timer getTimer()
    {
        return timer;
    }
    public void paintComponent(Graphics g) {
        Color c = new Color(100, 100, 100);
        Color d = new Color(0, 0, 0);
        Font f = new Font("Bookman Old Style", ALLBITS, 17);
        int centerX = 1050;
        int centerY = 835;
        int radius = 50;
        super.paintComponent(g);
        g.drawImage(sky, 0, -20, 2000, 1000, this);
        g.drawImage(sailboatImage, 750, SAILBOAT_IMAGE_Y+100, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(lantern, centerX-25, centerY-25, 50, 50, this);
        g.drawImage(redShipBack, x_position + 500, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(redShipBody, x_position + 500, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(redShipFront, x_position + 500, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(sailboatImage, 750, SAILBOAT_IMAGE_Y+100, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(playerImage, playerX, playerY, 40, 40, this);
        g.drawImage(speechImage, playerX, playerY - 50, 150, 100, this);
        g.drawImage(textBox, playerX + 10, playerY - 47, 120, 50, this);
        g.drawImage(sailboatImage2, 750, SAILBOAT_IMAGE_Y-450, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(blueShipBack, 250, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(blueShipBody, 250, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(blueShipFront, 250, 300, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);
        g.drawImage(closedTreasureChestImage, 1350 , 600, 40, 40, this);
        g.drawImage(plank, 1475 ,670, 40, 70, this);
        g.drawImage(plank, 475 ,670 , 40, 70, this);
        g.drawImage(closedTreasureChestImage, 600, 600, 40, 40, this);
        g.drawImage(sailboatImage2, 750, SAILBOAT_IMAGE_Y-450, SAILBOAT_IMAGE_WIDTH, SAILBOAT_IMAGE_HEIGHT, this);


        if(playerX < coinX + 20 && playerX > coinX - 20 && playerY < coinY + 30 && playerY > coinY - 30) {
            coinsCollected++;
            if(coinsCollected == 10) {
                speedBoost = 10;
            }
            coinX = (int) (Math.random() * (SAILBOAT_IMAGE_WIDTH - 120) + 1300);
            coinY = (int) (Math.random() * (SAILBOAT_IMAGE_HEIGHT - 500) + 640);
        }
        g.drawImage(coinImage, coinX, coinY, 40, 40, this);
        /*
         * 
         * 
         * ANYTHING THAT DOES NOT FALL UNDER THE BELOW CATAGORY PUT ABOVE HERE
         * 
         * 
         */
        if (!DayNightCycle.isDaytime) DayNightCycle.makeNight(g, nighttime, moon, this);
        /*
         * 
         * 
         * ANYTHING THAT MAKES LIGHT OR SHOULD NOT BE DARK DUE TO NIGHT TIME PUT BELOW HERE
         * 
         * 
         */
        g.drawRect(40, 100, 250, 60);
        g.setColor(c);
        g.fillRect(40, 100, 250, 60);
        g.setColor(d);
        g.setFont(f);
        g.drawString("Press C to choose character", 50, 130);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Create a radial gradient to simulate ORANGE light
        RadialGradientPaint gradient = new RadialGradientPaint(
            new Point(centerX, centerY), radius,
            new float[]{0f, 0.9f},
            new Color[]{
                new Color(254, 196, 127, 255),   // bright orange core
                new Color(254, 106, 127, 30)     // soft orange glow
            }
        );
        g2d.setPaint(gradient);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        for (Particle p : weatherParticles) {
            p.draw(g);
        }

    }

    public void actionPerformed(ActionEvent e) {
        playerX += playerXVelocity;
        playerY += playerYVelocity;
        if(playerX <= 1245){
            if(playerY < 900){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }   
        }
        else if(playerX <= 1305){
            if(playerY < 555){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }      
        }
        else if(playerX <= 1374){
            if(playerY < 600){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }            
        }
        else if(playerX <= 1563){
            if(playerY < 630){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }   
        }
        else if(playerX <= 1600){
            if(playerY < 585){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }   
        } 
        else if(playerX <= 1680){
            if(playerY < 605){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }   
        }
        else{
            if(playerY < 900){
                playerYVelocity += 2 * speedBoost;
            }
            else{
                jumped = false;
                playerYVelocity = 0;
            }   
        }

        //System.out.println(playerX);
        repaint();
        for (Particle p : weatherParticles) {
            p.update();
        }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) { // RAIN
            weatherType = "rain";
            System.out.println("Weather set to rain");
        }
        if (e.getKeyCode() == KeyEvent.VK_2) { // SNOW
            weatherType = "snow";
            System.out.println("Weather set to snow");
        }
        if (e.getKeyCode() == KeyEvent.VK_L) { // WIND LEFT
            windDirection = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) { // WIND RIGHT
            windDirection = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) { // STOP WIND
            windDirection = 0;
        }
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_C) changeCharacter();
        if (keyCode == KeyEvent.VK_E) dn.epilepsyMode();
        if (keyCode == KeyEvent.VK_LEFT) playerXVelocity -= (3 * speedBoost);
        if (keyCode == KeyEvent.VK_RIGHT) playerXVelocity += (3 * speedBoost);
        if ((keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP)  && !jumped){
            jumped = true;
            if(playerYVelocity > -20){
                playerYVelocity = -20;
            }
        } 
        if(playerXVelocity > 3){
            playerXVelocity = (int)(3 * speedBoost);
        }
        else if(playerXVelocity < -3){
            playerXVelocity = (int)(-3 * speedBoost);
        }

        if (keyCode == KeyEvent.VK_R) {
            x_position -= 10;
        }
        if (keyCode == KeyEvent.VK_T) {
            x_position += 10;
        }

        // If "P" is pressed, launch the platformer game in another thread
        if (keyCode == KeyEvent.VK_P) {
            new Thread(() -> {
                Test platformer = new Test();
                JFrame frame = new JFrame("Platformer Challenge");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(platformer);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                while (frame.isVisible()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // After finishing the platformer, multiply speedBoost
                speedBoost *= 1.3;
                System.out.println("Speed boost increased to: " + speedBoost);
            }).start();
        }
        //flip ur flap
        /*flip ur flap
        if (keyCode == KeyEvent.VK_MINUS) {
            System.exit(0)
        }
        /*if (keyCode == KeyEvent.VK_F) {
    java.util.List<JFrame> openFrames = new ArrayList<>();

    while (openFrames.size() < 100) {
        JFrame flipflap = new JFrame("Flip Your Flap " + (openFrames.size() + 1));

        // Fullscreen settings
        flipflap.setSize(1920, 1080);
        flipflap.setExtendedState(JFrame.MAXIMIZED_BOTH);
        flipflap.setUndecorated(true);
        flipflap.setAlwaysOnTop(true);
        flipflap.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        flipflap.getContentPane().setBackground(Color.WHITE);
        flipflap.setVisible(true);

        openFrames.add(flipflap); 

        try {
            Robot robot = new Robot();
            Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

            new Thread(() -> {
                while (flipflap.isVisible()) {
                    robot.mouseMove(center.x, center.y);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_TAB && e.isAltDown()) ||
                    (e.getKeyCode() == KeyEvent.VK_F4 && e.isAltDown()) ||
                    (e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    return true; // block input
                }
                return false;
            }
        });

        flipflap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
            }
        });

        flipflap.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                e.consume();
            }
        });

        flipflap.setFocusable(true);
        flipflap.requestFocusInWindow();

    }
}*/

        repaint();
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) playerXVelocity = 0;
        if (keyCode == KeyEvent.VK_RIGHT) playerXVelocity = 0;
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        // Sound effects for main
        new Thread(() -> Sound.main(null)).start();
        JFrame frame = new JFrame("Ship of Theseus - AP Computer Science A");
        Theseus game = new Theseus();
        frame.add(game);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    private class Particle {
        int x, y, size, speed;
        Color color;
        public Particle(int x, int y, int size, int speed, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
            this.color = color;
        }

        public void update() {
            y += speed;
            x += windDirection * 2;

            if (y > WINDOW_HEIGHT || x < 0 || x > WINDOW_WIDTH) {
                reset();
            }
        }

        public void reset() {
            x = (int)(Math.random() * WINDOW_WIDTH);
            y = 0;
            speed = weatherType.equals("rain") ? 8 + (int)(Math.random() * 4) : 1 + (int)(Math.random() * 2);
            size = weatherType.equals("rain") ? 1 : 5;
            color = weatherType.equals("rain") ? new Color(173, 216, 230, 180) : Color.WHITE;
        }

        public void draw(Graphics g) {
            g.setColor(color);
            if (weatherType.equals("rain")) {
                g.drawLine(x, y, x, y + 10);
            } else {
                g.fillOval(x, y, size, size);
            }
        }
    }

}