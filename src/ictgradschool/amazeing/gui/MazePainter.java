package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.maze.Maze;
import ictgradschool.amazeing.maze.TileTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The responsibility for drawing the maze has been delegated from {@link MazePanel} to this class, to reduce the amount
 * of code in that class.
 * <p>
 * Note: This will paint the top-left tile of the maze at position (0, 0). If you wish to change this, use
 * {@link Graphics#translate(int, int)} prior to calling the methods in this class.
 *
 * @author Andrew Meads
 */
public class MazePainter {

    // Constants. Change these to change the look of the maze.
    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    private static final Color GRIDLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 0.7f);
    private static final Color OUTLINE_COLOR = Color.black;
    private static final BufferedImage startImage, goalImage;

    // Initializes the start and goal images by loading them from disk.
    static {
        try {
            startImage = ImageIO.read(new File("graphics/Start.png"));
            goalImage = ImageIO.read(new File("graphics/Goal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This would paint the entire maze (foreground and background), but is commented out as it's no longer used.
//    public static void paintMaze(Maze maze, Graphics g) {
//        paintTiles(maze, g);
//        paintGridlines(maze, g);
//        paintStart(maze, g);
//        paintGoal(maze, g);
//    }

    /**
     * Paints the foreground of the given maze. That is, the start and goal images.
     *
     * @param maze the maze whose foreground should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    public static void paintForeground(Maze maze, Graphics g) {
        paintStart(maze, g);
        paintGoal(maze, g);
    }

    /**
     * Paints the background of the given maze. That is, the terrain and gridlines.
     *
     * @param maze the maze whose background should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    public static void paintBackground(Maze maze, Graphics g) {
        paintTerrain(maze, g);
        paintGridlines(maze, g);
    }

    /**
     * Paints the terrain tiles of the given maze.
     *
     * @param maze the maze whose terrain should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    private static void paintTerrain(Maze maze, Graphics g) {
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {

                TileTypes tile = maze.getTile(new ictgradschool.amazeing.Point(x, y));
                g.drawImage(tile.getImage(), x * TILE_WIDTH, y * TILE_HEIGHT, null);

            }
        }
    }

    /**
     * Paints the grid lines of the given maze.
     *
     * @param maze the maze whose grid lines should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    private static void paintGridlines(Maze maze, Graphics g) {

        g.setColor(GRIDLINE_COLOR);

        for (int x = 0; x < maze.getWidth(); x++) {
            g.drawLine(x * TILE_WIDTH, 0, x * TILE_WIDTH, maze.getHeight() * TILE_HEIGHT - 1);
        }

        for (int y = 0; y < maze.getHeight(); y++) {
            g.drawLine(0, y * TILE_HEIGHT, maze.getWidth() * TILE_WIDTH - 1, y * TILE_HEIGHT);
        }

        g.setColor(OUTLINE_COLOR);

        g.drawRect(0, 0, maze.getWidth() * TILE_WIDTH, maze.getHeight() * TILE_HEIGHT);
    }

    /**
     * Paints the start point of the given maze.
     *
     * @param maze the maze whose start point should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    private static void paintStart(Maze maze, Graphics g) {
        ictgradschool.amazeing.Point p = maze.getStartPoint();
        g.drawImage(startImage, p.getX() * TILE_WIDTH, p.getY() * TILE_HEIGHT, null);
    }

    /**
     * Paints the goal of the given maze.
     *
     * @param maze the maze whose goal should be painted.
     * @param g    the graphics object to use to paint the maze.
     */
    private static void paintGoal(Maze maze, Graphics g) {
        Point p = maze.getGoalPoint();
        g.drawImage(goalImage, p.getX() * TILE_WIDTH, p.getY() * TILE_HEIGHT, null);
    }
}
