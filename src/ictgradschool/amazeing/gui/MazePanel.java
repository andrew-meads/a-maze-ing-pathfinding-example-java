package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.Point;
import ictgradschool.amazeing.algorithms.IAlgorithmListener;
import ictgradschool.amazeing.algorithms.IGraphSearchAlgorithm;
import ictgradschool.amazeing.maze.IMazeListener;
import ictgradschool.amazeing.maze.Maze;
import ictgradschool.amazeing.maze.TileTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazePanel extends JPanel {

    private Maze maze;
    private IGraphSearchAlgorithm algorithm;

    private MouseOperation mouseOperation = MouseOperation.None;

    public MazePanel() {
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseMotionListener);
    }

    public void setMaze(Maze maze) {
        if (this.maze != null) {
            this.maze.removeMazeListener(mazeListener);
        }
        this.maze = maze;
        if (this.maze != null) {
            this.maze.addMazeListener(mazeListener);
        }
        repaint();
    }

    public void setAlgorithm(IGraphSearchAlgorithm algorithm) {
        if (this.algorithm != null) {
            this.algorithm.removeAlgorithmListener(algListener);
        }
        this.algorithm = algorithm;
        if (this.algorithm != null) {
            this.algorithm.addAlgorithmListener(algListener);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze != null) {

            Point offsets = getOffsets();
            g.translate(offsets.getX(), offsets.getY());

            MazePainter.getInstance().paintBackground(this.maze, g);

            if (algorithm != null) {
                SearchPainter.getInstance().paintAlgorithm(this.algorithm, g);
            }

            MazePainter.getInstance().paintForeground(this.maze, g);

            g.translate(-offsets.getX(), -offsets.getY());
        }
    }

    private Point getOffsets() {
        int mazeWidthPx = this.maze.getWidth() * MazePainter.TILE_WIDTH;
        int mazeHeightPx = this.maze.getHeight() * MazePainter.TILE_HEIGHT;

        int xOffs = (this.getWidth() - mazeWidthPx) / 2;
        int yOffs = (this.getHeight() - mazeHeightPx) / 2;

        return new Point(xOffs, yOffs);
    }

    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            Point clickedTile = getClickedTile(e);
            if (clickedTile == null) {
                mouseOperation = MouseOperation.None;
            } else if (clickedTile.equals(maze.getStartPoint())) {
                mouseOperation = MouseOperation.MoveStart;
            } else if (clickedTile.equals(maze.getGoalPoint())) {
                mouseOperation = MouseOperation.MoveGoal;
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                mouseOperation = MouseOperation.PlaceWall;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                mouseOperation = MouseOperation.DeleteWall;
            } else {
                mouseOperation = MouseOperation.None;
            }

            doMouseOperation(clickedTile);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseOperation = MouseOperation.None;
        }
    };

    private final MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            doMouseOperation(getClickedTile(e));
        }
    };

    private Point getClickedTile(MouseEvent e) {
        if (this.maze != null) {
            Point offsets = getOffsets();
            int clickedTileX = (e.getX() - offsets.getX()) / MazePainter.TILE_WIDTH;
            int clickedTileY = (e.getY() - offsets.getY()) / MazePainter.TILE_HEIGHT;
            if (clickedTileX >= 0 && clickedTileY >= 0 && clickedTileX < this.maze.getWidth() && clickedTileY < this.maze.getHeight()) {
                return new Point(clickedTileX, clickedTileY);
            }
        }
        return null;
    }

    private void doMouseOperation(Point clickedTile) {
        if (clickedTile != null) {
            switch (this.mouseOperation) {
                case MoveStart:
                    this.maze.setStartPoint(clickedTile);
                    break;
                case MoveGoal:
                    this.maze.setGoalPoint(clickedTile);
                    break;
                case PlaceWall:
                    this.maze.setTile(clickedTile, TileTypes.Wall);
                    break;
                case DeleteWall:
                    this.maze.setTile(clickedTile, TileTypes.Blank);
                    break;
            }
        }
    }

    private final IMazeListener mazeListener = maze -> repaint();

    private final IAlgorithmListener algListener = alg -> repaint();

    private enum MouseOperation {
        None, MoveStart, MoveGoal, PlaceWall, DeleteWall
    }
}
