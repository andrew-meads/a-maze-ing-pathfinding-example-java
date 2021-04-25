package ictgradschool.amazeing.maze;

import ictgradschool.amazeing.Point;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private final int width, height;

    private final TileTypes[][] tiles;

    private Point startPoint, goalPoint;

    private final List<IMazeListener> mazeListeners = new ArrayList<>();

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new TileTypes[width][height];

        reset();
    }

    public void reset() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.tiles[x][y] = TileTypes.Blank;
            }
        }

        this.startPoint = new Point(0, 0);
        this.goalPoint = new Point(width - 1, height - 1);

        fireMazeChanged();
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        fireMazeChanged();
    }

    public Point getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Point goalPoint) {
        this.goalPoint = goalPoint;
        fireMazeChanged();
    }

    public void setTile(Point tileCoords, TileTypes tile) {
        this.tiles[tileCoords.getX()][tileCoords.getY()] = tile;
        fireMazeChanged();
    }

    public TileTypes getTile(Point tileCoords) {
        return this.tiles[tileCoords.getX()][tileCoords.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void fireMazeChanged() {
        for (IMazeListener l : mazeListeners) {
            l.mazeChanged(this);
        }
    }

    public void addMazeListener(IMazeListener l) {
        this.mazeListeners.add(l);
    }

    public void removeMazeListener(IMazeListener l) {
        this.mazeListeners.remove(l);
    }
}