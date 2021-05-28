package sample;

import java.awt.*;

public class Enemy {
    Rectangle box;
    int vx;
    int vy;

    public Enemy(int x, int y, int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
        this.box = new Rectangle(x,y,12,12);
    }

}
