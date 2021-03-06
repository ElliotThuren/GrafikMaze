package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas  implements Runnable {
    private final Rectangle Goal1;
    private final Rectangle Goal2;
    private final Rectangle Goal3;
    private final Rectangle player;
    private int width = 1806;
    private int height = 606;

    int q= 356;
    int e= 456;
    int n= 935;
    int m= 256;
    int b= 1735;
    int h= 456;
    int x= 90;
    int y= 90;

    private Thread thread;
    int fps = 60;
    private boolean isRunning;

    private BufferStrategy bs;

    private int Goal1VX, Goal1VY;
    private int Goal2VX, Goal2VY;
    private int Goal3VX, Goal3VY;
    private int playerVX, playerVY;

    int[] xs = {56,56,156,156,56,56,50,50,556,556,56,56,150,150,156,156,350,350,450,450,356,356,350,350,456,456,456,356,356,550,550,156,156,256,256,156,156,256,256,356,356,256,256,250,250,150,150,56};
    int[] ys = {56,350,350,356,356,556,556,50,50,556,556,550,550,450,450,550,550,450,450,156,156,256,256,150,150,150,456,456,550,550,56,56,150,150,156,156,250,250,350,350,356,356,456,456,256,256,56,56};
    Polygon p = new Polygon(xs,ys,xs.length);

    int[] xSs = {656,656,716,716,656,656,716,716,656,656,650,650,1156,1156,656,656,900,900,906,906,1150,1150,1090,1090,1150,1150,956,956,950,950,756,756,816,816,756,756,900,900,906,906,1050,1050,990,990,1050,1050,856,856,950,950,890,890,1016,1016,956,956,1016,1016,950,950,856,856,790,790,850,850,790,790,856,856,1050,1050,1116,1116,1056,1056,1116,1116,1050,1050,756,756,690,690,750,750,690,690,750,750,656};
    int[] ySs = {56,150,150,156,156,350,350,356,356,556,556,50,50,556,556,550,550,490,490,550,550,306,306,300,300,56,56,116,116,56,56,250,250,256,256,450,450,390,390,450,450,306,306,300,300,156,156,350,350,256,256,190,190,256,256,350,350,416,416,356,356,416,416,350,350,156,156,90,90,150,150,90,90,156,156,450,450,516,516,456,456,516,516,450,450,256,256,250,250,56,56};
    Polygon s = new Polygon(xSs,ySs,xSs.length);

    int[] xss = {1256,1256,1316,1316,1256,1256,1316,1316,1256,1256,1250,1250,1756,1756,1256,1256,1450,1450,1390,1390,1450,1450,1390,1390,1450,1450,1456,1456,1516,1516,1456,1456,1516,1516,1456,1456,1650,1650,1590,1590,1650,1650,1590,1590,1650,1650,1656,1656,1716,1716,1656,1656,1716,1716,1656,1656,1750,1750,1690,1690,1750,1750,1690,1690,1750,1750,1556,1556,1616,1616,1556,1556,1616,1616,1556,1556,1550,1550,1490,1490,1550,1550,1490,1490,1550,1550,1356,1356,1356,1416,1416,1356,1356,1416,1416,1356,1356,1350,1350,1290,1290,1350,1350,1290,1290,1350,1350,1256};
    int[] yss = {56,150,150,156,156,350,350,356,356,556,556,50,50,556,556,550,550,356,356,350,350,156,156,90,90,90,90,90,90,156,156,350,350,356,356,550,550,356,356,350,350,156,156,90,90,90,90,90,90,156,156,350,350,356,356,550,550,456,456,450,450,256,256,250,250,56,56,250,250,256,256,450,450,516,516,516,516,516,516,450,450,256,256,250,250,56,56,56,250,250,256,256,450,450,516,516,516,516,516,516,450,450,256,256,250,250,56,56};
    Polygon t = new Polygon(xss,yss,xss.length);


    Enemy[] enemy = new Enemy[39];

    public GrafikMaze() {
        JFrame frame = new JFrame("Killer Maze Raze!");
        this.setSize(1806, 606);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        Goal1 = new Rectangle(356,456,15,94);
        Goal1VX = 1;
        Goal1VY = 0;

        Goal1.x = q;
        Goal1.y = e;
        Goal1VX = 0;
        Goal1VY = 0;

        Goal2 = new Rectangle(935,256,15,94);
        Goal2VX = 1;
        Goal2VY = 0;

        Goal2.x = n;
        Goal2.y = m;
        Goal2VX = 0;
        Goal2VY = 0;

        Goal3 = new Rectangle(1735,456,15,94);
        Goal3VX = 1;
        Goal3VY = 0;

        Goal3.x = b;
        Goal3.y = h;
        Goal3VX = 0;
        Goal3VY = 0;

        player = new Rectangle(300,150,24,24);
        playerVX = 1;
        playerVY = 0;

        player.x = x;
        player.y = y;
        playerVX = 0;
        playerVY = 0;

        enemy[0] = new Enemy(96,296,3,0);
        enemy[1] = new Enemy(96,396,3,1);
        enemy[2] = new Enemy(96,496,-1,2);
        enemy[3] = new Enemy(196,96,8,0);
        enemy[4] = new Enemy(196,196,2,1);
        enemy[5] = new Enemy(296,296,-1,1);
        enemy[6] = new Enemy(296,396,3,2);
        enemy[7] = new Enemy(396,96,0,3);
        enemy[8] = new Enemy(396,196,-1,4);
        enemy[9] = new Enemy(496,196,1,1);
        enemy[10] = new Enemy(496,296,1,-2);
        enemy[11] = new Enemy(496,396,2,3);
        enemy[12] = new Enemy(496,496,0,6);

        enemy[13] = new Enemy(820,496,2,-1);
        enemy[14] = new Enemy(970,496,2,1);
        enemy[15] = new Enemy(696,196,1,1);
        enemy[16] = new Enemy(696,296,1,-1);
        enemy[17] = new Enemy(696,396,1,1);
        enemy[18] = new Enemy(796,196,1,2);
        enemy[19] = new Enemy(796,296,1,-2);
        enemy[20] = new Enemy(896,96,2,1);
        enemy[21] = new Enemy(996,96,-2,1);
        enemy[22] = new Enemy(1096,220,-1,2);
        enemy[23] = new Enemy(1096,370,1,2);

        enemy[24] = new Enemy(1296,196,1,2);
        enemy[25] = new Enemy(1296,296,1,-2);
        enemy[26] = new Enemy(1296,396,1,2);
        enemy[27] = new Enemy(1396,196,2,4);
        enemy[28] = new Enemy(1396,296,2,-4);
        enemy[29] = new Enemy(1396,396,2,4);
        enemy[30] = new Enemy(1496,196,1,2);
        enemy[31] = new Enemy(1496,296,2,3);
        enemy[32] = new Enemy(1496,396,1,2);
        enemy[33] = new Enemy(1596,196,1,3);
        enemy[34] = new Enemy(1596,296,-1,3);
        enemy[35] = new Enemy(1596,396,1,3);
        enemy[36] = new Enemy(1696,196,1,1);
        enemy[37] = new Enemy(1696,296,2,1);
        enemy[38] = new Enemy(1696,396,3,2);
    }

    public void update() {
        player.x += playerVX;
        player.y += playerVY;

        for (int i = 0 ; i < enemy.length ; i++) {
            enemy[i].box.x += enemy[i].vx;
            enemy[i].box.y += enemy[i].vy;
        }

        if (player.intersects(Goal1)) {
            System.out.println("Vidare till level 2!");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 690;
            player.y = 90;

        }

        if (player.intersects(Goal2)) {
            System.out.println("Vidare till level 3!");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 1290;
            player.y = 90;

        }

        if (player.intersects(Goal3)) {
            System.out.println("Grattis, du vann spelet!");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;

        }
        if (p.intersects(player)) {
            System.out.println("Du dog f??r att du kolliderade med sidan.");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;
        }

        if (s.intersects(player)) {
            System.out.println("Du dog f??r att du kolliderade med sidan.");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;
        }

        if (t.intersects(player)) {
            System.out.println("Du dog f??r att du kolliderade med sidan.");
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.x = 90;
            player.y = 90;
        }

        for (int i = 0; i  < enemy.length;i++ ) {
            if (enemy[i].box.intersects(player)) {
                System.out.println("Du dog f??r att du kolliderade med en fiende.");
                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player.x = 90;
                player.y = 90;
            }
        }
        for (int i = 0; i  < enemy.length;i++ ) {
            if (p.intersects(enemy[i].box) || s.intersects(enemy[i].box) || t.intersects(enemy[i].box)) {
            try {
                thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy[i].vx = -enemy[i].vx;
            enemy[i].vy = -enemy[i].vy;
        }
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

        for (int i = 0; i  < enemy.length;i++ ) {
            drawEnemydot(g, enemy[i].box.x,enemy[i].box.y);
        }

        drawGoal1(g, Goal1.x,Goal1.y);
        drawGoal2(g, Goal2.x,Goal2.y);
        drawGoal3(g, Goal3.x,Goal3.y);

        g.setColor(new Color(0, 0, 0));
        g.fillPolygon(p);
        g.fillPolygon(t);
        g.fillPolygon(s);

        drawPlayerdot(g, player.x,player.y);

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

    private void drawEnemydot(Graphics g, int x, int y) {
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, 12, 12);
    }

    private void drawPlayerdot(Graphics g, int x, int y) {
        g.setColor(new Color(20, 36, 180));
        g.fillRect(x, y, 24, 24);
    }

    private void drawGoal1(Graphics g, int x, int y) {
        g.setColor(new Color(25, 240, 5));
        g.fillRect(x, y, 15, 94);
    }
    private void drawGoal2(Graphics g, int x, int y) {
        g.setColor(new Color(25, 240, 5));
        g.fillRect(x, y, 15, 94);
    }
    private void drawGoal3(Graphics g, int x, int y) {
        g.setColor(new Color(25, 240, 5));
        g.fillRect(x, y, 15, 94);
    }

}