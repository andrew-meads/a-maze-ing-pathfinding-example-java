package ictgradschool.amazeing.maze;

import ictgradschool.amazeing.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Maze. Mazes have passable terrain and walls. Mazes have an entrance (start position) and exit (goal
 * position). A user or algorithm might try and find a path from the start to the goal.
 *
 * @author Andrew Meads
 */
public class Maze {

    private final int width, height;

    private final TileTypes[][] tiles;

    private Point startPoint, goalPoint;

    private final List<IMazeListener> mazeListeners = new ArrayList<>();

    private boolean isLocked;

    /**
     * Creates a new Maze of the given size.
     *
     * @param width  the width, in tiles.
     * @param height the height, in tiles.
     */
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new TileTypes[width][height];

        reset();
    }

    /**
     * Resets the maze to its default configuration. That is, all tiles are passable, the start is in the top-left,
     * and the goal is in the bottom-right.
     */
    public void reset() {

        isLocked = false;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.tiles[x][y] = TileTypes.Blank;
            }
        }

        this.startPoint = new Point(0, 0);
        this.goalPoint = new Point(width - 1, height - 1);

        fireMazeChanged();
    }

    /**
     * Sets the locked status of this maze. A locked maze cannot be edited (i.e. its {@link #setTile(Point, TileTypes)},
     * {@link #setStartPoint(Point)}, and {@link #setGoalPoint(Point)} methods will do nothing).
     *
     * @param locked true if the maze should be locked, false otherwise.
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Gets the start point of the maze.
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Sets the start point of the maze. The start point cannot be set to a tile that's currently not passable.
     *
     * @param startPoint the new start point.
     */
    public void setStartPoint(Point startPoint) {
        if (!isLocked && this.tiles[startPoint.getX()][startPoint.getY()] != TileTypes.Wall) {
            this.startPoint = startPoint;
            fireMazeChanged();
        }
    }

    /**
     * Gets the goal point of the maze.
     */
    public Point getGoalPoint() {
        return goalPoint;
    }

    /**
     * Sets the goal point of the maze. The goal point cannot be set to a tile that's currently not passable.
     *
     * @param goalPoint the new goal point.
     */
    public void setGoalPoint(Point goalPoint) {
        if (!isLocked && this.tiles[goalPoint.getX()][goalPoint.getY()] != TileTypes.Wall) {
            this.goalPoint = goalPoint;
            fireMazeChanged();
        }
    }

    /**
     * Sets the tile at the given coords to the given type. The tiles corresponding to the current start and end
     * points cannot be edited. Move the start and end points first.
     *
     * @param tileCoords the coordinates of the tile to edit.
     * @param tile the new tile type.
     */
    public void setTile(Point tileCoords, TileTypes tile) {
        if (!isLocked && !(tileCoords.equals(startPoint) || tileCoords.equals(goalPoint))) {
            this.tiles[tileCoords.getX()][tileCoords.getY()] = tile;
            fireMazeChanged();
        }
    }

    /**
     * Gets the type of tile at the given coordinates.
     */
    public TileTypes getTile(Point tileCoords) {
        return this.tiles[tileCoords.getX()][tileCoords.getY()];
    }

    /**
     * Gets the width of the maze, in tiles.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the maze, in tiles.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Fires an event that lets listeners know that the maze has been updated.
     */
    private void fireMazeChanged() {
        for (IMazeListener l : mazeListeners) {
            l.mazeChanged(this);
        }
    }

    /**
     * Adds the given listener to the list of listeners to be notified when the maze changes.
     * @param l the listener to add.
     */
    public void addMazeListener(IMazeListener l) {
        this.mazeListeners.add(l);
    }

    /**
     * Removes the given listener from the list of listeners to be notified when the maze changes.
     * @param l the listener to remove.
     */
    public void removeMazeListener(IMazeListener l) {
        this.mazeListeners.remove(l);
    }
}