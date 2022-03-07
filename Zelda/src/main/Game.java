package main;

import entities.Entity;
import entities.Player;
import graphs.ReadSprites;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    private static final int HEIGHT = 160;
    private static final int WIDTH = HEIGHT * 5 / 3;
    private static final int SCALE = 3;
    public int fps;

    public Thread thread;
    private boolean running = false;
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

    public List<Entity> entities;
    public static ReadSprites sprites;
    private final Player player;

    public Game() {
        initFrame();
        this.addKeyListener(this);
        //boot up
        entities = new ArrayList<>();
        sprites = new ReadSprites();
        player = new Player(50, 50, 16, 16, sprites.getSprites(1, 11, 16, 16));
        entities.add(player);
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        for (Entity e : entities) {
            e.tick();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(13, 68, 4));
        g.fillRect(0, 0, getWidth(), getHeight());

        //Graphics2D g2 = (Graphics2D) g;
        for (Entity e : entities) {
            e.render(g);
        }

        g.setFont(new Font("Arial", Font.ITALIC, 15));
        g.setColor(Color.white);
        g.drawString("FPS: " + fps, 3, 13);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); /* drawing */
        bs.show();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                fps = frames;
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    public void initFrame() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame frame = new JFrame();
        frame.setTitle("Mine zelda");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cs.png")));
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
    }
}
