package entities;

import main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, left, up, down;
    private final int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
    private int dir;
    private boolean moved;
    private int frames = 0, index = 0;
    private final int maxFrames = 10, maxIndex = 1;

    private final BufferedImage[] upPlayer;
    private final BufferedImage[] downPlayer;
    private final BufferedImage[] leftPlayer;
    private final BufferedImage[] rightPlayer;

    public Player(int x, int y, int w, int h, BufferedImage sprite) {
        super(x, y, w, h, sprite);

        upPlayer = new BufferedImage[3];
        downPlayer = new BufferedImage[3];
        leftPlayer = new BufferedImage[2];
        rightPlayer = new BufferedImage[2];

        rightPlayer[0] = Game.sprites.getSprites(35, 11, 15, 16);
        rightPlayer[1] = Game.sprites.getSprites(52, 12, 14, 15);

        leftPlayer[0] = Game.sprites.getSprites(35, 28, 15, 16);
        leftPlayer[1] = Game.sprites.getSprites(52, 28, 14, 15);

        upPlayer[0] = Game.sprites.getSprites(71, 11, 12, 16);
        upPlayer[1] = Game.sprites.getSprites(83, 11, 12, 16);
        upPlayer[2] = Game.sprites.getSprites(103, 11, 12, 15);

        downPlayer[0] = Game.sprites.getSprites(0, 14, 15, 16);
        downPlayer[1] = Game.sprites.getSprites(18, 10, 13, 16);
        downPlayer[2] = Game.sprites.getSprites(73, 40, 15, 15);

    }

    public void tick() {
        moved = false;
        double speed = 1.5;
        if (left) {
            moved = true;
            dir = left_dir;
            x -= speed;
        }

        if (right) {
            moved = true;
            dir = right_dir;
            x += speed;
        }
        if (up) {
            moved = true;
            dir = up_dir;
            y -= speed;
        }
        if (down) {
            moved = true;
            dir = down_dir;
            y += speed;
        }

        if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex)
                    index = 0;
            }
        }
    }

    public void render(Graphics g) {
        if (dir == right_dir && moved) {
            g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == right_dir) {
            g.drawImage(rightPlayer[0], this.getX(), this.getY(), null);
        }

        if (dir == left_dir && moved) {
            g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == left_dir) {
            g.drawImage(leftPlayer[0], getX(), getY(), null);
        }

        if (dir == up_dir && moved) {
            g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == up_dir) {
            g.drawImage(upPlayer[2], getX(), getY(), null);
        }

        if (dir == down_dir && moved) {
            g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == down_dir) {
            g.drawImage(downPlayer[2], getX(), getY(), null);
        }

    }
}
