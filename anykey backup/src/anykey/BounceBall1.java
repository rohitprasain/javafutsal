package anykey;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class BounceBall1 extends JComponent implements ActionListener, MouseMotionListener, KeyListener {

    private Timer timer;
    private int delay = 9;

    private int ballx = 150;
    private int bally = 30;
    private int paddlex = 0;
    private int ballySpeed = 7;
    private int ballxSpeed = 5;
    public int score = 0;
    public int score1 = 0;
    static public int scorefinal;
    public int bestscore;
    public int bestscore1;
    public boolean gameOver, started;

    public static void main1() {

        JFrame wind = new JFrame("BounceBall");
        BounceBall1 g = new BounceBall1();
        wind.add(g);
        wind.pack();
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setVisible(true);
        wind.addMouseMotionListener(g);

        Timer tt = new Timer(17, g);
        tt.start();

    }

    public void newball(int ballx, int bally, int ballxspeed, int ballyspeed) {

        ballx = 150;
        bally = 30;
        ballxspeed = 5;
        ballyspeed = 7;

        JOptionPane.showMessageDialog(null, "new ball!");

        return;
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(805, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {

        //draw the sky
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.GREEN);
        g.fillRect(0, 565, 800, 100);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 30));
        g.drawString("Do not let the ball fall down!", 100, 590);
        //draw border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 600);
        g.fillRect(0, 0, 800, 3);
        g.fillRect(800, 1, 3, 600);

        //draw the paddel
        g.setColor(Color.black);
        g.fillRect(paddlex, 510, 150, 20);

        //draw the ball
        g.setColor(Color.RED);
        g.fillOval(ballx, bally, 30, 30);
        //score	
        if (score >= 0) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("Score: " + score + "", 700, 30);
        }
        // start && gameOver
        if (score >= 0 && bestscore < 10) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 30));
            g.drawString("Level 1", 700, 590);

        }

        if (score >= 10 && bestscore < 20) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 30));
            g.drawString("Level 2", 700, 590);
        }

        if (score >= 30 && bestscore < 40) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 30));
            g.drawString("Level 4", 700, 590);
        }
        if (score >= 20 && bestscore < 30) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 8, 30));
            g.drawString("Level 3", 700, 590);
        }

        if (gameOver) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("Bestscore: " + scorefinal + "  Can You beat this ?", 300, 30);
            setVisible(false);
            exit ex = new exit();
            ex.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ballx = ballx + ballxSpeed;
        bally = bally + ballySpeed;

        // Window Down 
        if (ballx >= paddlex && ballx <= paddlex + 150 && bally >= 475) {

            ballySpeed = -7;
            score++;

        }

        // Window up
        if (bally <= 10) {

            ballySpeed = 7;

        }

        // Window right
        if (ballx >= 775) {

            ballxSpeed = -5;

        }

        // Window left
        if (ballx <= 0) {

            ballxSpeed = 5;

        }

        if (bally >= 700) {

            score = 0;
            bally = 0;
            gameOver = true;

        }
        bestscore = score;

        if (scorefinal > bestscore) {

            scorefinal = scorefinal;

        } else {

            scorefinal = score;
            bestscore = score;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        paddlex = e.getX() - 50;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
