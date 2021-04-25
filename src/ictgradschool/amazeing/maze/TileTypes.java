package ictgradschool.amazeing.maze;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum TileTypes {

    Blank("Grass.png"),
    Wall("Wall.png");

    private final BufferedImage image;

    TileTypes(String fileName) {
        try {
            this.image = ImageIO.read(new File("Graphics", fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
