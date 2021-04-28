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

/**
 * This class acts as the View and Controller (of MVC pattern) for a {@link Maze} instance (the Model). This panel
 * displays the given maze along with any search algorithm currently in-progress on that maze. It also provides the
 * following Controller functionality:
 * <ul>
 *     <li>Left-clicking and dragging the start or goal to reposition them</li>
 *     <li>Left-clicking and dragging to place walls</li>
 *     <li>Right-clicking and dragging to remove walls (place grass)</li>
 * </ul>
 *
 * @author Andrew Meads
 */
public class MazePanel extends JPanel {

    // The maze to render, if any.
    private Maze maze;
    // The algorithm to render, if any.
    private IGraphSearchAlgorithm algorithm;
    // The current operation of the mouse on this panel.
    private MouseOperation mouseOperation = MouseOperation.None;

    /**
     * Creates a new MazePanel and allows us to respond to mouse events.
     */
    public MazePanel() {
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseMotionListener);
    }

    /**
     * Sets the maze to render. Registers a listener to respond to changes in the maze, so we know when a re-render
     * needs to occur.
     *
     * @param maze the maze to display.
     */
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

    /**
     * Sets the algorithm to render. Registers a listener to respond to changes in the algorithm, so we know when a
     * re-render needs to occur.
     *
     * @param algorithm the algorithm to display.
     */
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

    /**
     * This method is called by Swing when a re-render needs to occur.
     * <p>
     * Calculates the (x, y) position where the maze and algorithm should be drawn on the panel, then paints them using
     * the functionality contained in the {@link MazePainter} and {@link SearchPainter} classes.
     *
     * @param g the graphics object used to draw.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze != null) {

            Point offsets = getOffsets();
            g.translate(offsets.getX(), offsets.getY());

            MazePainter.paintBackground(this.maze, g);

            if (algorithm != null) {
                SearchPainter.paintAlgorithm(this.algorithm, g);
            }

            MazePainter.paintForeground(this.maze, g);

            g.translate(-offsets.getX(), -offsets.getY());
        }
    }

    /**
     * Calculates the (x, y) position within this panel, where the maze should be drawn. The maze should be drawn such
     * that it is centered in this panel.
     *
     * @return the co-ordinates which should correspond to the top-left corner of the maze.
     */
    private Point getOffsets() {
        int mazeWidthPx = this.maze.getWidth() * MazePainter.TILE_WIDTH;
        int mazeHeightPx = this.maze.getHeight() * MazePainter.TILE_HEIGHT;

        int xOffs = (this.getWidth() - mazeWidthPx) / 2;
        int yOffs = (this.getHeight() - mazeHeightPx) / 2;

        return new Point(xOffs, yOffs);
    }

    /**
     * A mouse listener which detects when the mouse is pressed or released, and modifies the {@link #mouseOperation}
     * accordingly.
     */
    private final MouseListener mouseListener = new MouseAdapter() {

        /**
         * When the mouse is pressed, determines the mouse operation based on the mouse button which was pressed,
         * and where on the panel the mouse was pressed.
         *
         * @param e contains information about the mouse event (i.e. button and location)
         */
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

        /**
         * When the mouse is released, cancel the mouse operation.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            mouseOperation = MouseOperation.None;
        }
    };

    /**
     * Allows us to respond to mouse-drag events (i.e. whenever the mouse is moved around on this panel while
     * one of the mouse buttons are held down). Whenever the mouse is moved, applies the current mouse operation to
     * the maze tile which is currently being moused-over by the user.
     */
    private final MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            doMouseOperation(getClickedTile(e));
        }
    };

    /**
     * Gets the coordinates of the maze tile which the mouse is currently hovering over, according to the given mouse
     * event object.
     *
     * @param e contains the location of the mouse.
     * @return a {@link Point} corresponding to one of the maze tiles, or null if no maze tile is currently being
     * hovered over.
     */
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

    /**
     * Applies the current mouse operation to the maze tile at the given tile coordinates.
     *
     * @param clickedTile the tile coordinates
     */
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

    // A listener which responds to maze change events by causing a repaint of this panel.
    private final IMazeListener mazeListener = maze -> repaint();

    // A listener which responds to algorithm change events by causing a repaint of this panel.
    private final IAlgorithmListener algListener = alg -> repaint();

    // The possible mouse operations.
    private enum MouseOperation {
        None, MoveStart, MoveGoal, PlaceWall, DeleteWall
    }
}
