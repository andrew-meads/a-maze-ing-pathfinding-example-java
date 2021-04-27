package ictgradschool.amazeing.gui;

import ictgradschool.amazeing.algorithms.GraphSearchAlgorithmFactory;
import ictgradschool.amazeing.algorithms.IGraphSearchAlgorithm;
import ictgradschool.amazeing.graph.Graph;
import ictgradschool.amazeing.graph.Node;
import ictgradschool.amazeing.maze.GraphFactory;
import ictgradschool.amazeing.maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppWindow extends JFrame {

    private JButton btnClearWalls;
    private JButton btnResetSearch;
    private JButton btnRunToCompletion;
    private JButton btnSingleStep;
    private JButton btnStartAnimation;
    private JButton btnStopAnimation;
    private JComboBox<String> cboSearchAlgorithm;
    private JPanel mazeContainer;
    private JCheckBox chkAllowDiagonal;

    private MazePanel mazePanel;
    private Maze maze;

    private State state = State.Editing;
    private Timer timer;
    private IGraphSearchAlgorithm algorithm;

    public AppWindow() {
        initComponents();

        setPreferredSize(new Dimension(1024, 768));
        setTitle(("A-MAZE-ing Pathfinding Example"));
        pack();

        btnClearWalls.addActionListener(this::handleClearWallsClick);
        btnResetSearch.addActionListener(this::handleResetSearchClick);
        btnRunToCompletion.addActionListener(this::handleRunClick);
        btnSingleStep.addActionListener(this::handleSingleStepClick);
        btnStartAnimation.addActionListener(this::handleStartAnimationClick);
        btnStopAnimation.addActionListener(this::handleStopAnimationClick);

        cboSearchAlgorithm.setModel(new DefaultComboBoxModel<>(GraphSearchAlgorithmFactory.getAlgorithmNames()));
        chkAllowDiagonal.setSelected(true);

        mazePanel = new MazePanel();
        mazeContainer.setLayout(new BorderLayout());
        mazeContainer.add(mazePanel, BorderLayout.CENTER);

        maze = new Maze(20, 20);
        mazePanel.setMaze(maze);

        updateControlsEnabledStatus();
    }

    private void initComponents() {

        mazeContainer = new JPanel();
        JPanel controlPanel = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        btnClearWalls = new JButton();
        JSeparator jSeparator1 = new JSeparator();
        JLabel jLabel5 = new JLabel();
        cboSearchAlgorithm = new JComboBox<>();
        JLabel jLabel6 = new JLabel();
        btnStartAnimation = new JButton();
        btnStopAnimation = new JButton();
        btnSingleStep = new JButton();
        btnRunToCompletion = new JButton();
        btnResetSearch = new JButton();
        chkAllowDiagonal = new JCheckBox();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mazeContainer.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        GroupLayout mazeContainerLayout = new GroupLayout(mazeContainer);
        mazeContainer.setLayout(mazeContainerLayout);
        mazeContainerLayout.setHorizontalGroup(
                mazeContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 539, Short.MAX_VALUE)
        );
        mazeContainerLayout.setVerticalGroup(
                mazeContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 507, Short.MAX_VALUE)
        );

        jLabel1.setText("Map controls:");

        jLabel2.setText("- Click and drag the start or goal to move them.");

        jLabel3.setText("- Left-click and drag to place walls.");

        jLabel4.setText("- Right-click and drag to remove walls.");

        btnClearWalls.setText("Clear all walls");

        jLabel5.setText("Select search algorithm:");

        jLabel6.setText("Search controls:");

        btnStartAnimation.setText("Start animation");

        btnStopAnimation.setText("Stop animation");

        btnSingleStep.setText("Single step");

        btnRunToCompletion.setText("Run to completion");

        btnResetSearch.setText("Reset search");

        chkAllowDiagonal.setText("Allow diagonal movement");

        GroupLayout controlPanelLayout = new GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnClearWalls, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator1)
                                        .addComponent(cboSearchAlgorithm, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStartAnimation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSingleStep, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStopAnimation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRunToCompletion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnResetSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(controlPanelLayout.createSequentialGroup()
                                                .addGroup(controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(chkAllowDiagonal)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                .addGap(0, 19, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClearWalls)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboSearchAlgorithm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkAllowDiagonal)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStartAnimation)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStopAnimation)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSingleStep)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRunToCompletion)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetSearch)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mazeContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mazeContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    private void handleStartAnimationClick(ActionEvent e) {
        initializeSearchIfNotInitialized();
        startTimer();
        this.state = State.Searching_AnimStarted;
        updateControlsEnabledStatus();
    }

    private void handleStopAnimationClick(ActionEvent e) {
        stopTimer();
        this.state = State.Searching_AnimNotStarted;
        updateControlsEnabledStatus();
    }

    private void handleSingleStepClick(ActionEvent e) {
        initializeSearchIfNotInitialized();
        stopTimer();
        boolean done = this.algorithm.step();
        this.state = done ? State.Done : State.Searching_AnimNotStarted;
        updateControlsEnabledStatus();
    }

    private void handleResetSearchClick(ActionEvent e) {
        resetSearch();
        this.state = State.Editing;
        updateControlsEnabledStatus();
    }

    private void handleRunClick(ActionEvent e) {
        initializeSearchIfNotInitialized();
        stopTimer();
        this.algorithm.run();
        this.state = State.Done;
        updateControlsEnabledStatus();
    }

    private void handleClearWallsClick(ActionEvent e) {
        this.maze.reset();
    }

    private void resetSearch() {
        stopTimer();
        algorithm = null;
        mazePanel.setAlgorithm(null);
    }

    private void initializeSearchIfNotInitialized() {

        if (algorithm == null) {
            String algName = (String) this.cboSearchAlgorithm.getSelectedItem();
            Graph graph = GraphFactory.fromMaze(this.maze, chkAllowDiagonal.isSelected());
            Node startNode = graph.findNode(this.maze.getStartPoint());
            Node goalNode = graph.findNode(this.maze.getGoalPoint());
            this.algorithm = GraphSearchAlgorithmFactory.createAlgorithm(algName, graph, startNode, goalNode);
            mazePanel.setAlgorithm(algorithm);
        }
    }

    private void startTimer() {
        stopTimer();
        timer = new Timer(25, this::timerTick);
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    private void timerTick(ActionEvent e) {
        boolean done = this.algorithm.step();
        if (done) {
            stopTimer();
            this.state = State.Done;
            updateControlsEnabledStatus();
        }
    }

    private void updateControlsEnabledStatus() {
        switch (state) {
            case Editing:
                maze.setLocked(false);
                btnClearWalls.setEnabled(true);
                cboSearchAlgorithm.setEnabled(true);
                btnStartAnimation.setEnabled(true);
                btnStopAnimation.setEnabled(false);
                btnSingleStep.setEnabled(true);
                btnRunToCompletion.setEnabled(true);
                btnResetSearch.setEnabled(false);
                chkAllowDiagonal.setEnabled(true);
                break;

            case Searching_AnimNotStarted:
                maze.setLocked(true);
                btnClearWalls.setEnabled(false);
                cboSearchAlgorithm.setEnabled(false);
                btnStartAnimation.setEnabled(true);
                btnStopAnimation.setEnabled(false);
                btnSingleStep.setEnabled(true);
                btnRunToCompletion.setEnabled(true);
                btnResetSearch.setEnabled(true);
                chkAllowDiagonal.setEnabled(false);
                break;

            case Searching_AnimStarted:
                maze.setLocked(true);
                btnClearWalls.setEnabled(false);
                cboSearchAlgorithm.setEnabled(false);
                btnStartAnimation.setEnabled(false);
                btnStopAnimation.setEnabled(true);
                btnSingleStep.setEnabled(true);
                btnRunToCompletion.setEnabled(true);
                btnResetSearch.setEnabled(true);
                chkAllowDiagonal.setEnabled(false);
                break;

            case Done:
                maze.setLocked(true);
                btnClearWalls.setEnabled(false);
                cboSearchAlgorithm.setEnabled(false);
                btnStartAnimation.setEnabled(false);
                btnStopAnimation.setEnabled(false);
                btnSingleStep.setEnabled(false);
                btnRunToCompletion.setEnabled(false);
                btnResetSearch.setEnabled(true);
                chkAllowDiagonal.setEnabled(false);
                break;
        }
    }

    private enum State {
        Editing, Searching_AnimNotStarted, Searching_AnimStarted, Done
    }
}
