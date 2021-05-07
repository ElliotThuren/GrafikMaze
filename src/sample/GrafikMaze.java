package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas  implements Runnable {
    private final Rectangle player;
    private final Rectangle Goal;
    private int width = 606;
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

    public GrafikMaze() {
        JFrame frame = new JFrame("Killer Maze Raze!");
        this.setSize(606, 606);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        Goal = new Rectangle(356,456,15,94);
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
        drawFixdot(g, 250,150);
        drawFixdot(g, 150,350);
        drawFixdot(g, 350,250);
        drawFixdot(g, 250,450);
        drawFixdot(g, 350,350);
        drawFixdot(g, 450,450);
        drawFixdot(g, 550,550);
        drawEnemydot(g, 96,296);
        drawEnemydot(g, 96,496);
        drawEnemydot(g, 196,196);
        drawEnemydot(g, 296,296);
        drawEnemydot(g, 296,396);
        drawEnemydot(g, 496,196);
        drawEnemydot(g, 496,496);
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
        g.fillRect(x, y, 500, 6);
    }

    private void drawOutlineWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 500);
    }

    private void drawWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 100, 6);
    }

    private void drawWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 100);
    }

    private void drawFixdot(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 6);
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