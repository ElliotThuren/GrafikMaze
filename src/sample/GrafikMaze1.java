package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class GrafikMaze1 extends Canvas  implements Runnable {
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

    int[] xs = {50,50,56,56};
    int[] ys = {50,556,556,50};
    Polygon p = new Polygon(xs,ys,xs.length);

    int[] xss = {1156,1156,1216,1216,1156,1156,1216,1216,1156,1156,1150,1150,1656,1656,1156,1156,1350};
    int[] yss = {56,150,150,156,156,350,350,356,356,556,556,50,50,556,556,550,550};
    Polygon t = new Polygon(xss,yss,xss.length);

    public GrafikMaze1() {
        JFrame frame = new JFrame("Killer Maze Raze!");
        this.setSize(1806, 606);
        frame.setResizable(false);
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
        if (p.intersects(player)) {
            System.out.println("Du dog för att du nuddade sidan.");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;
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
        drawOutlineWallX(g, 50,50);
        drawOutlineWallX(g, 50,550);
        drawOutlineWallY(g, 50,50);
        drawOutlineWallY(g, 550,50);
        drawWallX(g, 50,350);
        drawWallX(g, 150,150);
        drawWallX(g, 150,250);
        drawWallX(g, 250,350);
        drawWallX(g, 350,450);
        drawWallY(g, 150,50);
        drawWallY(g, 150,150);
        drawWallY(g, 350,150);
        drawWallY(g, 450,150);
        drawWallY(g, 250,250);
        drawWallY(g, 450,250);
        drawWallY(g, 250,350);
        drawWallY(g, 450,350);
        drawWallY(g, 150,450);
        drawWallY(g, 350,450);
        drawEnemydot(g, 96,296);
        drawEnemydot(g, 96,496);
        drawEnemydot(g, 196,196);
        drawEnemydot(g, 296,296);
        drawEnemydot(g, 296,396);
        drawEnemydot(g, 496,196);
        drawEnemydot(g, 496,496);
        g.setColor(new Color(8, 246, 218));
        g.fillPolygon(p);
        drawPlayerdot(g, player.x,player.y);
        drawGoal(g, Goal.x,Goal.y);
        drawOutlineWallX(g, 1150,50);
        drawOutlineWallX(g, 1150,550);
        drawOutlineWallY(g, 1150,50);
        drawOutlineWallY(g, 1650,50);

        drawWallY(g, 1250,50);
        drawWallY(g, 1250,150);
        drawWallY(g, 1250,250);
        drawWallY(g, 1250,350);

        drawWallY(g, 1350,150);
        drawWallY(g, 1350,250);
        drawWallY(g, 1350,350);
        drawWallY(g, 1350,450);

        drawWallY(g, 1450,50);
        drawWallY(g, 1450,150);
        drawWallY(g, 1450,250);
        drawWallY(g, 1450,350);

        drawWallY(g, 1550,150);
        drawWallY(g, 1550,250);
        drawWallY(g, 1550,350);
        drawWallY(g, 1550,450);

        drawWallhY(g, 1250,450);
        drawWallhY(g, 1350,90);
        drawWallhY(g, 1450,450);
        drawWallhY(g, 1550,90);

        drawWallhX(g, 1150,150);
        drawWallhX(g, 1190,250);
        drawWallhX(g, 1150,350);
        drawWallhX(g, 1190,450);

        drawWallhX(g, 1290,150);
        drawWallhX(g, 1250,250);
        drawWallhX(g, 1290,350);
        drawWallhX(g, 1250,450);

        drawWallhX(g, 1350,150);
        drawWallhX(g, 1390,250);
        drawWallhX(g, 1350,350);
        drawWallhX(g, 1390,450);

        drawWallhX(g, 1490,150);
        drawWallhX(g, 1450,250);
        drawWallhX(g, 1490,350);
        drawWallhX(g, 1450,450);

        drawWallhX(g, 1550,150);
        drawWallhX(g, 1590,250);
        drawWallhX(g, 1550,350);
        drawWallhX(g, 1590,450);

        g.setColor(new Color(8, 246, 218));
        g.drawPolygon(t);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        GrafikMaze1 painting = new GrafikMaze1();
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