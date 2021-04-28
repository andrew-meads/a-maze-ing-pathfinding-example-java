package ictgradschool.amazeing.maze;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents different kinds of tiles in a maze, along with their associated graphics.
 * <p>
 * TODO Potentially refactor so that the graphics part isn't contained in this enum, to remove Maze's dependency on Swing.
 *
 * @author Andrew Meads
 */
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
