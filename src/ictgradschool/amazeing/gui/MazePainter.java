package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.maze.Maze;
import ictgradschool.amazeing.maze.TileTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazePainter {

    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    private static final Color GRIDLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 0.7f);
    private static final Color OUTLINE_COLOR = Color.black;

    private static final BufferedImage startImage, goalImage;

    static {
        try {
            startImage = ImageIO.read(new File("graphics/Start.png"));
            goalImage = ImageIO.read(new File("graphics/Goal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void paintMaze(Maze maze, Graphics g) {
//        paintTiles(maze, g);
//        paintGridlines(maze, g);
//        paintStart(maze, g);
//        paintGoal(maze, g);
//    }

    public static void paintForeground(Maze maze, Graphics g) {
        paintStart(maze, g);
        paintGoal(maze, g);
    }

    public static void paintBackground(Maze maze, Graphics g) {
        paintTiles(maze, g);
        paintGridlines(maze, g);
    }

    private static void paintTiles(Maze maze, Graphics g) {
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {

                TileTypes tile = maze.getTile(new ictgradschool.amazeing.Point(x, y));
                g.drawImage(tile.getImage(), x * TILE_WIDTH, y * TILE_HEIGHT, null);

            }
        }
    }

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

    private static void paintStart(Maze maze, Graphics g) {
        ictgradschool.amazeing.Point p = maze.getStartPoint();
        g.drawImage(startImage, p.getX() * TILE_WIDTH, p.getY() * TILE_HEIGHT, null);
    }

    private static void paintGoal(Maze maze, Graphics g) {
        Point p = maze.getGoalPoint();
        g.drawImage(goalImage, p.getX() * TILE_WIDTH, p.getY() * TILE_HEIGHT, null);
    }
}
