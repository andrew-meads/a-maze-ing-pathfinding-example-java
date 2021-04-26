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

    private javax.swing.JButton btnClearWalls;
    private javax.swing.JButton btnResetSearch;
    private javax.swing.JButton btnRunToCompletion;
    private javax.swing.JButton btnSingleStep;
    private javax.swing.JButton btnStartAnimation;
    private javax.swing.JButton btnStopAnimation;
    private javax.swing.JComboBox<String> cboSearchAlgorithm;
    private javax.swing.JPanel mazeContainer;

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

        cboSearchAlgorithm.setModel(new DefaultComboBoxModel<>(GraphSearchAlgorithmFactory.getInstance().getAlgorithmNames()));

        mazePanel = new MazePanel();
        mazeContainer.setLayout(new BorderLayout());
        mazeContainer.add(mazePanel, BorderLayout.CENTER);

        maze = new Maze(20, 20);
        mazePanel.setMaze(maze);

        updateControlsEnabledStatus();
    }

    private void initComponents() {

        mazeContainer = new javax.swing.JPanel();
        JPanel controlPanel = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        btnClearWalls = new javax.swing.JButton();
        JSeparator jSeparator1 = new JSeparator();
        JLabel jLabel5 = new JLabel();
        cboSearchAlgorithm = new javax.swing.JComboBox<>();
        JLabel jLabel6 = new JLabel();
        btnStartAnimation = new javax.swing.JButton();
        btnStopAnimation = new javax.swing.JButton();
        btnSingleStep = new javax.swing.JButton();
        btnRunToCompletion = new javax.swing.JButton();
        btnResetSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mazeContainer.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black, 1));

        javax.swing.GroupLayout mazeContainerLayout = new javax.swing.GroupLayout(mazeContainer);
        mazeContainer.setLayout(mazeContainerLayout);
        mazeContainerLayout.setHorizontalGroup(
                mazeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 539, Short.MAX_VALUE)
        );
        mazeContainerLayout.setVerticalGroup(
                mazeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnClearWalls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator1)
                                        .addComponent(cboSearchAlgorithm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(controlPanelLayout.createSequentialGroup()
                                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                .addGap(0, 19, Short.MAX_VALUE))
                                        .addComponent(btnStartAnimation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSingleStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStopAnimation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRunToCompletion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnResetSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClearWalls)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboSearchAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStartAnimation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStopAnimation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSingleStep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRunToCompletion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetSearch)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mazeContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mazeContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            Graph graph = GraphFactory.fromMaze(this.maze);
            Node startNode = graph.findNode(this.maze.getStartPoint());
            Node goalNode = graph.findNode(this.maze.getGoalPoint());
            this.algorithm = GraphSearchAlgorithmFactory.getInstance().createAlgorithm(algName, graph, startNode, goalNode);
            mazePanel.setAlgorithm(algorithm);
        }
    }

    private void startTimer() {
        stopTimer();
        timer = new Timer(50, this::timerTick);
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
                break;
        }
    }

    private enum State {
        Editing, Searching_AnimNotStarted, Searching_AnimStarted, Done
    }
}
