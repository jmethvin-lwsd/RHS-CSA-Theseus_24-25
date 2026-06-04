package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DayNightCycle extends JPanel implements ActionListener {
    Timer timer;
    public static boolean isDaytime = true;
    public DayNightCycle()
    {
        timer = new Timer(1500000, this);
        timer.start();
    }
    public void epilepsyMode()
    {
        timer = new Timer(30, this);
        timer.start();
    }
    public void actionPerformed(ActionEvent e)
    {
        isDaytime = !isDaytime;
    }
    public static void makeNight(Graphics g, Image nightOverlay, Image moon, Theseus caller)
    {
        g.drawImage(nightOverlay, 0, -20, 2000, 1000, caller);
        g.drawImage(moon, 400, 100, 120, 120, caller);
    }
}
