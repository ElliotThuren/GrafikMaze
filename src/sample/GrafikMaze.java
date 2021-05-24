package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas  implements Runnable {
    private final Rectangle player;
    private final Rectangle Goal;
    private int width = 1806;
    private int height = 606;

    int x= 90;
    int y= 90;
    int b= 356;
    int h= 456;

    private Thread thread;
    int fps = 30;
    private boolean isRunning;

    private BufferStrategy bs;

    private int playerVX, playerVY;

    private int GoalVX, GoalVY;

    int[] xs = {56,56,156,156,56,56,50,50,556,556,56,56,150,150,156,156,350,350,450,450,456,456,356,356,550,550,156,156,256,256,156,156,256,256,356,356,256,256,250,250,150,150,56};
    int[] ys = {56,350,350,356,356,556,556,50,50,556,556,550,550,450,450,550,550,450,450,150,150,456,456,550,550,56,56,150,150,156,156,250,250,350,350,356,356,456,456,256,256,56,56};
    Polygon p = new Polygon(xs,ys,xs.length);

    int[] xS = {356,356,350,350,356};
    int[] yS = {150,256,256,150,150};
    Polygon d = new Polygon(xS,yS,xS.length);

    public GrafikMaze() {
        JFrame frame = new JFrame("Killer Maze Raze!");
        this.setSize(1806, 606);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        Goal = new Rectangle(356,456,12,94);
        GoalVX = 1;
        GoalVY = 0;

        Goal.x = b;
        Goal.y = h;
        GoalVX = 0;
        GoalVY = 0;

        player = new Rectangle(300,150,24,24);
        playerVX = 1;
        playerVY = 0;

        player.x = x;
        player.y = y;
        playerVX = 0;
        playerVY = 0;
    }

    public void update() {
        player.x += playerVX;
        player.y += playerVY;

        if (player.intersects(Goal)) {
            System.out.println("Grattis, du har vunnit spelet!");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;

            Goal.x += GoalVX;
            Goal.y += GoalVY;
        }
    }

    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        drawOutlineWallX(g, 650,50);
        drawOutlineWallX(g, 650,550);
        drawOutlineWallY(g, 650,50);
        drawOutlineWallY(g, 1150,50);
        drawOutlineWallX(g, 1250,50);
        drawOutlineWallX(g, 1250,550);
        drawOutlineWallY(g, 1250,50);
        drawOutlineWallY(g, 1750,50);
        drawWallY(g,1350,50);
        drawWallY(g,1350,150);
        drawWallY(g,1350,250);
        drawWallY(g,1350,350);
        drawWallY(g,1450,150);
        drawWallY(g,1450,250);
        drawWallY(g,1450,350);
        drawWallY(g,1450,450);
        drawWallY(g,1550,50);
        drawWallY(g,1550,150);
        drawWallY(g,1550,250);
        drawWallY(g,1550,350);
        drawWallY(g,1650,150);
        drawWallY(g,1650,250);
        drawWallY(g,1650,350);
        drawWallY(g,1650,450);
        drawWallY(g,750,50);
        drawWallY(g,750,150);
        drawWallY(g,750,250);
        drawWallY(g,750,350);
        drawWallY(g,850,150);
        drawWallY(g,850,250);
        drawWallY(g,1050,150);
        drawWallY(g,1050,250);
        drawWallY(g,1050,350);
        drawWallY(g,950,250);
        drawWallX(g,750,450);
        drawWallX(g,850,450);
        drawWallX(g,950,450);
        drawWallX(g,850,350);
        drawWallX(g,850,150);
        drawWallX(g,950,150);
        drawWallhX(g,1250,150);
        drawWallhX(g,1290,250);
        drawWallhX(g,1250,350);
        drawWallhX(g,1290,450);
        drawWallhX(g,1350,150);
        drawWallhX(g,1390,250);
        drawWallhX(g,1350,350);
        drawWallhX(g,1390,450);
        drawWallhX(g,1450,150);
        drawWallhX(g,1490,250);
        drawWallhX(g,1450,350);
        drawWallhX(g,1490,450);
        drawWallhX(g,1550,150);
        drawWallhX(g,1590,250);
        drawWallhX(g,1550,350);
        drawWallhX(g,1590,450);
        drawWallhX(g,1650,150);
        drawWallhX(g,1690,250);
        drawWallhX(g,1650,350);
        drawWallhX(g,1690,450);
        drawEnemydot(g, 96,296);
        drawEnemydot(g, 96,496);
        drawEnemydot(g, 196,196);
        drawEnemydot(g, 296,296);
        drawEnemydot(g, 296,396);
        drawEnemydot(g, 496,196);
        drawEnemydot(g, 496,496);
        g.setColor(new Color(0, 0, 0));
        g.fillPolygon(p);
        g.fillPolygon(d);
        drawPlayerdot(g, player.x,player.y);
        drawGoal(g, Goal.x,Goal.y);
        g.dispose();
        bs.show();
    }

        public static void main(String[] args) {
            GrafikMaze painting = new GrafikMaze();
            painting.start();
        }

        public synchronized void start() {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }

        public synchronized void stop() {
            isRunning = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            double deltaT = 1000.0/fps;
            long lastTime = System.currentTimeMillis();
            while (isRunning) {
                long now = System.currentTimeMillis();
                if (now-lastTime > deltaT) {
                    update();
                    draw();
                    lastTime = now;
                }

            }
            stop();
        }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                playerVX = -2;
            }
            if (keyEvent.getKeyChar() == 'd') {
                playerVX = 2;
            }
            if (keyEvent.getKeyChar() == 'w') {
                playerVY = -2;
            }
            if (keyEvent.getKeyChar() == 's') {
                playerVY = 2;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'a') {
                playerVX = 0;
            }
            if (keyEvent.getKeyChar() == 'd') {
                playerVX = 0;
            }
            if (keyEvent.getKeyChar() == 'w') {
                playerVY = 0;
            }
            if (keyEvent.getKeyChar() == 's') {
                playerVY = 0;
            }
        }
    }

    private void drawOutlineWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 506, 6);
    }

    private void drawOutlineWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 506);
    }

    private void drawWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 106, 6);
    }

    private void drawWallhX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 66, 6);
    }

    private void drawWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 106);
    }

    private void drawWallhY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 66);
    }

    private void drawEnemydot(Graphics g, int x, int y) {
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, 12, 12);
    }

    private void drawPlayerdot(Graphics g, int x, int y) {
        g.setColor(new Color(20, 37, 180));
        g.fillRect(x, y, 24, 24);
    }

    private void drawGoal(Graphics g, int x, int y) {
        g.setColor(new Color(25, 239, 6));
        g.fillRect(x, y, 15, 94);
    }

}