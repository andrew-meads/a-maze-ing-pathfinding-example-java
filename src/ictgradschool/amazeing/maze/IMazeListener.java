package ictgradschool.amazeing.maze;

/**
 * This interface is to be implemented by classes who want to be informed when a {@link Maze}'s state changes.
 */
public interface IMazeListener {

    /**
     * Called by an observed {@link Maze} when its state changes.
     *
     * @param maze the maze that changed.
     */
    void mazeChanged(Maze maze);
}
