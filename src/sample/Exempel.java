package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Exempel {
    private BufferStrategy bs;

    int[] xs = {480, 580, 580, 630};
    int[] ys = {190, 190, 250, 250};

    public void draw() {
        //bs = getBufferStrategy();
        if (bs == null) {
            //    createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Polygon p = new Polygon(xs, ys, 4);
        g.drawPolygon(p);
        Rectangle r = new Rectangle(20, 20, 100, 100);
        if (p.intersects(r)) {
            System.out.println("Du dog.");
        }
    }
}
