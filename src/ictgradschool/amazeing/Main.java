package ictgradschool.amazeing;

import ictgradschool.amazeing.gui.MazePanel;
import ictgradschool.amazeing.maze.Maze;
import ictgradschool.amazeing.maze.TileTypes;

import javax.swing.*;
import java.awt.*;

public class Main {

    private Maze maze;

    private final MazePanel mazePanel;

    private Main() {
        JFrame frame = new JFrame();
        frame.setTitle("A-Mazeing Pathfinding Example");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(800, 800));
        contentPane.setLayout(new BorderLayout());
        frame.setContentPane(contentPane);

        this.mazePanel = new MazePanel();
        contentPane.add(this.mazePanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.reset();
    }

    void reset() {
        this.maze = new Maze(20, 20);
        this.mazePanel.setMaze(this.maze);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}
