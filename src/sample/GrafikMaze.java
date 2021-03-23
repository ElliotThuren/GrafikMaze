package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas {
    private int width = 800;
    private int height = 600;

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
        drawoutlineWallY(g, 50,50);
        drawoutlineWallX(g, 50,50);
        drawoutlineWallY(g, 550,50);
        drawoutlineWallX(g, 50,550);
        drawWallY(g, 150,50);
        drawWallY(g, 150,150);
        drawWallX(g, 150,150);
        drawWallX(g, 150,250);
        drawWallX(g, 250,350);
        drawWallX(g, 350,450);
        drawWallY(g, 350,250);
        drawWallY(g, 350,150);
        drawWallY(g, 350,450);
        drawWallY(g, 450,350);
        drawWallY(g, 450,250);
        drawWallY(g, 450,150);
        drawWallX(g, 50,350);
        drawWallY(g, 150,450);
        drawWallY(g, 250,250);
        drawWallY(g, 250,350);
        drawfixdot(g, 250,150);
        drawfixdot(g, 150,350);
        drawfixdot(g, 250,450);
        drawfixdot(g, 350,350);
        drawfixdot(g, 450,450);
        drawfixdot(g, 550,550);
        drawenemydot(g, 96,296);
        drawenemydot(g, 96,496);
        drawenemydot(g, 496,196);
        drawenemydot(g, 196,196);
        drawenemydot(g, 296,296);
        drawenemydot(g, 296,396);
        drawenemydot(g, 496,496);
        drawplayerdot(g, 90,90);
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

    private void drawoutlineWallY(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 500);
    }

    private void drawoutlineWallX(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 500, 6);
    }

    private void drawfixdot(Graphics g, int x, int y) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 6, 6);
    }

    private void drawenemydot(Graphics g, int x, int y) {
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, 12, 12);
    }

    private void drawplayerdot(Graphics g, int x, int y) {
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