package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas {
    private int width = 606;
    private int height = 606;

    private BufferStrategy bs;

    public GrafikMaze() {
        JFrame frame = new JFrame("Killer Maze Raze!");
        this.setSize(606, 606);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        update();
    }

    private void update() {
    }

    public void paint(Graphics g) {
        drawOutlineWallX(g, 50,50);
        drawOutlineWallX(g, 50,550);
        drawOutlineWallY(g, 50,50);
        drawOutlineWallY(g, 550,50);
        drawWallX(g, 150,150);
        drawWallX(g, 150,250);
        drawWallX(g, 250,350);
        drawWallX(g, 350,450);
        drawWallX(g, 50,350);
        drawWallY(g, 150,50);
        drawWallY(g, 150,150);
        drawWallY(g, 350,250);
        drawWallY(g, 350,150);
        drawWallY(g, 350,450);
        drawWallY(g, 450,350);
        drawWallY(g, 450,250);
        drawWallY(g, 450,150);
        drawWallY(g, 150,450);
        drawWallY(g, 250,250);
        drawWallY(g, 250,350);
        drawFixdot(g, 250,150);
        drawFixdot(g, 150,350);
        drawFixdot(g, 250,450);
        drawFixdot(g, 350,350);
        drawFixdot(g, 450,450);
        drawFixdot(g, 550,550);
        drawEnemydot(g, 96,296);
        drawEnemydot(g, 96,496);
        drawEnemydot(g, 496,196);
        drawEnemydot(g, 196,196);
        drawEnemydot(g, 296,296);
        drawEnemydot(g, 296,396);
        drawEnemydot(g, 496,496);
        drawPlayerdot(g, 90,90);
        drawGoal(g, 356,456);
    }

    private void drawWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 100);
    }

    private void drawWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 100, 6);
    }

    private void drawOutlineWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 500);
    }

    private void drawOutlineWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 500, 6);
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

    public static void main(String[] args) {
        GrafikMaze painting = new GrafikMaze();
    }
}