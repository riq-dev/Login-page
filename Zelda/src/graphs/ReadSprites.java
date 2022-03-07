package graphs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ReadSprites {

    private BufferedImage sprites;

    public ReadSprites() {
        try {
            sprites = ImageIO.read(getClass().getResource("/sheets.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprites(int x, int y, int w, int h) {
        return sprites.getSubimage(x, y, w, h);
    }
}
