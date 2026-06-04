//Test class fully designed by Arnav Jha

package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JPanel implements KeyListener, ActionListener {

    // === Level Design ===
    static char[][][] levels = {
        {
            "####################".toCharArray(),
            "#                  #".toCharArray(),
            "#                  #".toCharArray(),
            "#         222      #".toCharArray(),
            "#   P     2  2    X#".toCharArray(),
            "####################".toCharArray()
        },
        {
            "####################".toCharArray(),
            "#P   ######     2  #".toCharArray(),
            "#        #  2  222 #".toCharArray(),
            "#   ######  2      #".toCharArray(),
            "#           2     X#".toCharArray(),
            "####################".toCharArray()
        }
    };

    int level = 0;
    char[][] currentLevel;
    Timer timer = new Timer(30, this);
    int tileSize = 30;

    int playerX, playerY;
    double velY = 0;
    boolean onGround = false;

    boolean left, right, jump;

    public Test() {
        JOptionPane.showMessageDialog(this, "Arrow keys to move");
        setPreferredSize(new Dimension(600, 180));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        loadLevel(level);
        timer.start();
    }

    void loadLevel(int lvl) {
        currentLevel = levels[lvl];
        for (int y = 0; y < currentLevel.length; y++) {
            for (int x = 0; x < currentLevel[y].length; x++) {
                if (currentLevel[y][x] == 'P') {
                    playerX = x * tileSize;
                    playerY = y * tileSize;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < currentLevel.length; y++) {
            for (int x = 0; x < currentLevel[y].length; x++) {
                char c = currentLevel[y][x];
                int px = x * tileSize;
                int py = y * tileSize;

                if (c == '1' || c == '#') {
                    g.setColor(Color.GRAY);
                    g.fillRect(px, py, tileSize, tileSize);
                } else if (c == '2') {
                    g.setColor(Color.RED);
                    g.fillRect(px, py, tileSize, tileSize);
                } else if (c == 'X') {
                    g.setColor(Color.GREEN);
                    g.fillRect(px, py, tileSize, tileSize);
                }
            }
        }

        g.setColor(Color.CYAN);
        g.fillRect(playerX, playerY, tileSize, tileSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int oldX = playerX;
        int oldY = playerY;

        // Horizontal movement
        if (left) playerX -= 5;
        if (right) playerX += 5;

        // Horizontal collision
        if (isSolid(playerX / tileSize, (playerY + tileSize - 1) / tileSize) ||
            isSolid(playerX / tileSize, (playerY) / tileSize)) {
            if (left) playerX = oldX + 3; // Push right if hit while moving left
            if (right) playerX = oldX - 3; // Push left if hit while moving right
        }

        // Jump
        if (jump && onGround) velY = -8;
        velY += 0.5;
        if (velY > 10) velY = 10;
        playerY += velY;

        // Vertical collision
        if (isSolid(playerX / tileSize, (playerY + tileSize - 1) / tileSize)) {
            while (isSolid(playerX / tileSize, (playerY + tileSize - 1) / tileSize)) {
                playerY--;
                velY = 0;
            }
            onGround = true;
        } else if (isSolid(playerX / tileSize, playerY / tileSize)) {
            while (isSolid(playerX / tileSize, playerY / tileSize)) {
                playerY++;
                velY = 0;
            }
        } else {
            onGround = false;
        }

        // Lava check
        if (isLava(playerX / tileSize, playerY / tileSize)) {
            loadLevel(level);
        }

        // Goal check
        if (isGoal(playerX / tileSize, playerY / tileSize)) {
            level++;
            if (level >= levels.length) {
                JOptionPane.showMessageDialog(this, "Congrats! Your prize for this challenge: We close out your window");
                System.exit(0);
            } else {
                loadLevel(level);
            }
        }

        repaint();
    }

    boolean isSolid(int x, int y) {
        if (y < 0 || y >= currentLevel.length || x < 0 || x >= currentLevel[0].length) return false;
        return currentLevel[y][x] == '#';
    }

    boolean isLava(int x, int y) {
        if (y < 0 || y >= currentLevel.length || x < 0 || x >= currentLevel[0].length) return false;
        return currentLevel[y][x] == '~';
    }

    boolean isGoal(int x, int y) {
        if (y < 0 || y >= currentLevel.length || x < 0 || x >= currentLevel[0].length) return false;
        return currentLevel[y][x] == 'X';
    }

    // Input Handling
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: left = true; break;
            case KeyEvent.VK_RIGHT: right = true; break;
            case KeyEvent.VK_UP: jump = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_RIGHT: right = false; break;
            case KeyEvent.VK_UP: jump = false; break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Entry point
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sailor Platformer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(new Test());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
