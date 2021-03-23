package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GrafikMaze extends Canvas {
    private int width = 800;
    private int height = 600;

    private BufferStrategy bs;

    public GrafikMaze() {
        JFrame frame = new JFrame("An empty canvas");
        this.setSize(800, 600);
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
        drawWallX(g, 50,350);
        drawWallY(g, 150,450);
        drawWallY(g, 250,250);
        drawWallY(g, 250,350);
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

    public static void main(String[] args) {
        GrafikMaze painting = new GrafikMaze();
    }
}