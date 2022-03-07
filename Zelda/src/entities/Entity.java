package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

    protected double x, y;
    protected int w, h;
    private BufferedImage sprite;

    public Entity(int x, int y, int w, int h, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sprite = sprite;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(sprite, getX(), getY(), null);
    }
}
