/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaresume;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author matth
 */
public class Frame1 extends javax.swing.JFrame {

    Clock myClock = new Clock();
    Clock myClockTwo = new Clock();

    // Global variables for the array that holds all nodes. The array list holds 
    //the path for our depth-first search function.
    Node[][] mazeArray = new Node[25][51];
    Node[][] mazeArrayCopy = new Node[25][51];
    ArrayList<Node> path = new ArrayList<Node>();
    

    // Global variables for our path found condition, starting node, RNG, and 
    // counter.
    Node start = new Node();
    Node startTwo = new Node();

    Node end = new Node();
    Node endTwo = new Node();

    boolean goal = false;
    Random rand = new Random();
    int startCounter = 0;
    int endCounter = 0;

    /**
     * Creates new form Frame1
     */
    public Frame1() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(23,180,252));
        this.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
        
        createEmptyBoard();

        // adding path finding algorithms to the combo boxes for the user to select
        jComboBoxBoardOne.removeAllItems();
        jComboBoxBoardOne.addItem("Depth Algorithm");
        jComboBoxBoardOne.addItem("Breadth Algorithm");
        jComboBoxBoardOne.addItem("A Star");

        jComboBoxBoardTwo.removeAllItems();
        jComboBoxBoardTwo.addItem("Depth Algorithm");
        jComboBoxBoardTwo.addItem("Breadth Algorithm");
        jComboBoxBoardTwo.addItem("A Star");

        // adding maze generating algorithms to the combo boxes for the user to select
        jComboBoxMazeSelect.removeAllItems();
        jComboBoxMazeSelect.addItem("Empty Maze");
        jComboBoxMazeSelect.addItem("Open Maze");
        jComboBoxMazeSelect.addItem("Depth First Maze");
        jComboBoxMazeSelect.addItem("Recursive Division Maze");

        // ads the lables and clockes to the frame
        myClock.setSize(500, 200);
        myClock.setLocation(65, 65);
        myClock.setVisible(true);
        jPanelBackground.add(myClock);

        myClockTwo.setSize(500, 200);
        myClockTwo.setLocation(65, 330);
        myClockTwo.setVisible(true);
        jPanelBackground.add(myClockTwo);
    }

    private void createEmptyBoard() {
        int x = 0;
        int y = 0;

        int pieceWidth = 10;

        // double for loop to populate the panel and our array
        for (int k = 0; k < mazeArray.length; k++) {
            for (int i = 0; i < mazeArray[k].length; i++) {
                if (i == 0 || k == 0 || i == 50 || k == 24) {
                    // our panel is contained by null nodes to make it easier to 
                    // stay in bounds.
                    mazeArray[k][i] = null;

                } else {
                    // Generates a random number to select a random color for 
                    // each node. 

                    // creats a new game piece
                    Node maze = new Node();
                    Node mazeCopy = new Node();

                    // sets the loaction of the game peace and the color
                    maze.setLocation(x, y);
                    maze.setSize(100, 100);
                    maze.setVisible(true);

                    mazeCopy.setLocation(x, y);
                    mazeCopy.setSize(100, 100);
                    mazeCopy.setVisible(true);

                    maze.setPieceColor(Color.white);
                    mazeCopy.setPieceColor(Color.white);

                    maze.setNodeRowPosition(k);
                    maze.setNodeColPosition(i);

                    mazeCopy.setNodeRowPosition(k);
                    mazeCopy.setNodeColPosition(i);

                    // Increments to x so that the next time through the loop 
                    // our node will be in the correct position to add a new node.
                    x = x + 10;

                    // adds the node to the panil and maze array.
                    jPanelBoardOne.add(maze);
                    jPanelBoardTwo.add(mazeCopy);
                    mazeArray[k][i] = maze;
                    mazeArrayCopy[k][i] = mazeCopy;

                }
            }

            // skips the first row because the first row is a null row.
            if (k != 0) {
                // Increments to y so that the next time through the loop 
                // our node will be in the correct position to add a new node.
                y = y + 10;
            }
            // sets x back to zero at the beginning of each row.
            x = 0;
        }
        jPanelBoardOne.repaint();
        jPanelBoardTwo.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanelBackground = new javax.swing.JPanel();
        jRadioButtonStart = new javax.swing.JRadioButton();
        jRadioButtonTarget = new javax.swing.JRadioButton();
        jComboBoxMazeSelect = new javax.swing.JComboBox<>();
        jRadioButtonClear = new javax.swing.JRadioButton();
        jPanelBoardOne = new javax.swing.JLayeredPane();
        jPanelBoardTwo = new javax.swing.JLayeredPane();
        jComboBoxBoardOne = new javax.swing.JComboBox<>();
        jComboBoxBoardTwo = new javax.swing.JComboBox<>();
        jbtnNewMaze = new javax.swing.JButton();
        jbttnReset = new javax.swing.JButton();
        jbtnStart = new javax.swing.JButton();
        jlbPath = new javax.swing.JLabel();
        jRadioButtonObstical = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemReturnMenu = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 0));
        setResizable(false);

        jPanelBackground.setBackground(new java.awt.Color(51, 51, 51));
        jPanelBackground.setPreferredSize(new java.awt.Dimension(1034, 648));

        buttonGroup1.add(jRadioButtonStart);
        jRadioButtonStart.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonStart.setText("Start");

        buttonGroup1.add(jRadioButtonTarget);
        jRadioButtonTarget.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonTarget.setText("Target");

        jComboBoxMazeSelect.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMazeSelect.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxMazeSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGroup1.add(jRadioButtonClear);
        jRadioButtonClear.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonClear.setText("Clear");

        jPanelBoardOne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 102, 102), null, null));
        jPanelBoardOne.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelBoardOneMouseDragged(evt);
            }
        });
        jPanelBoardOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBoardOneMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelBoardOneMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoardOneLayout = new javax.swing.GroupLayout(jPanelBoardOne);
        jPanelBoardOne.setLayout(jPanelBoardOneLayout);
        jPanelBoardOneLayout.setHorizontalGroup(
            jPanelBoardOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );
        jPanelBoardOneLayout.setVerticalGroup(
            jPanelBoardOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        jPanelBoardTwo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelBoardTwo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelBoardTwoMouseDragged(evt);
            }
        });
        jPanelBoardTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBoardTwoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoardTwoLayout = new javax.swing.GroupLayout(jPanelBoardTwo);
        jPanelBoardTwo.setLayout(jPanelBoardTwoLayout);
        jPanelBoardTwoLayout.setHorizontalGroup(
            jPanelBoardTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelBoardTwoLayout.setVerticalGroup(
            jPanelBoardTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        jComboBoxBoardOne.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBoardOne.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxBoardOne.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBoardOne.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxBoardOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBoardOneActionPerformed(evt);
            }
        });

        jComboBoxBoardTwo.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBoardTwo.setForeground(new java.awt.Color(0, 0, 0));
        jComboBoxBoardTwo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnNewMaze.setBackground(new java.awt.Color(255, 255, 255));
        jbtnNewMaze.setForeground(new java.awt.Color(0, 0, 0));
        jbtnNewMaze.setText("New Board");
        jbtnNewMaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNewMazeActionPerformed(evt);
            }
        });

        jbttnReset.setBackground(new java.awt.Color(255, 255, 255));
        jbttnReset.setForeground(new java.awt.Color(0, 0, 0));
        jbttnReset.setText("Reset");
        jbttnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnResetActionPerformed(evt);
            }
        });

        jbtnStart.setBackground(new java.awt.Color(255, 255, 255));
        jbtnStart.setForeground(new java.awt.Color(0, 0, 0));
        jbtnStart.setText("Start Path Finder");
        jbtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStartActionPerformed(evt);
            }
        });

        jlbPath.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jlbPath.setForeground(new java.awt.Color(255, 255, 255));
        jlbPath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonGroup1.add(jRadioButtonObstical);
        jRadioButtonObstical.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonObstical.setText("Obsitcals");

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbPath, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(jPanelBoardOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxBoardTwo, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
                            .addComponent(jComboBoxBoardOne, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMazeSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                                .addComponent(jbttnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jbtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jbtnNewMaze, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelBoardTwo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jRadioButtonTarget, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButtonClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButtonStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButtonObstical, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackgroundLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jlbPath, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBoardOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(jComboBoxBoardOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxBoardTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxMazeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBoardTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(jRadioButtonObstical)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonClear)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonStart)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonTarget)))
                .addGap(18, 18, 18)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnNewMaze)
                    .addComponent(jbttnReset)
                    .addComponent(jbtnStart))
                .addGap(21, 21, 21))
        );

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("File");

        jMenuItemReturnMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemReturnMenu.setText("retrun to menu");
        jMenuItemReturnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReturnMenuActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemReturnMenu);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemExit.setText("exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStartActionPerformed

        // if statement stops the user from starting the path without selecting
        // start and stop nodes.
        if (endCounter != 0 && startCounter != 0) {
            if (jComboBoxBoardOne.getSelectedIndex() != jComboBoxBoardTwo.getSelectedIndex()) {
                PathFinder pathSearch = new PathFinder(mazeArray, path, start, end, jComboBoxBoardOne.getSelectedIndex(), myClock);
                Thread myThread = new Thread(pathSearch);
                myThread.start();

                PathFinder pathSearchTwo = new PathFinder(mazeArrayCopy, path, startTwo, endTwo, jComboBoxBoardTwo.getSelectedIndex(), myClockTwo);
                Thread myThreadTwo = new Thread(pathSearchTwo);
                myThreadTwo.start();
            } else {
                jlbPath.setText("please select two dfferent path finding algorithms");
            }

        } else {
            // notifies the user to select start and finish.
            jlbPath.setText("enter start and finish");
        }

    }//GEN-LAST:event_jbtnStartActionPerformed

    private void jbttnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnResetActionPerformed
        // allows the user to reset by clearing all old info and creating a new
        // board.
        goal = false;
        path = new ArrayList<Node>();
        path.clear();

        myClock.resetTime();
        myClockTwo.resetTime();

        myClock.setNoCount(0);
        myClockTwo.setNoCount(0);

        myClock.setMyPath(0);
        myClockTwo.setMyPath(0);

        start.setgCost(0);
        startTwo.setgCost(0);

        end.sethCost(0);
        endTwo.sethCost(0);

        jlbPath.setText("");

        // for loop resets all nodes that changed to green or yellow back to white
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {

                if (mazeArray[i][j] != null) {
                    mazeArray[i][j].setSearched(false);
                    mazeArrayCopy[i][j].setSearched(false);
                    if (mazeArray[i][j].getPieceColor() == (Color.green)
                        || mazeArray[i][j].getPieceColor() == (Color.yellow)
                        || mazeArrayCopy[i][j].getPieceColor() == (Color.green)
                        || mazeArrayCopy[i][j].getPieceColor() == (Color.yellow)) {

                        mazeArray[i][j].setPieceColor(Color.white);
                        mazeArrayCopy[i][j].setPieceColor(Color.white);

                    }
                }

            }
        }
    }//GEN-LAST:event_jbttnResetActionPerformed

    private void jbtnNewMazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNewMazeActionPerformed
        // allows the user to reset by clearing all old info and creating a new
        // board.
        goal = false;
        mazeArray = new Node[25][51];
        mazeArrayCopy = new Node[25][51];
        path = new ArrayList<Node>();

        myClock.resetTime();
        myClockTwo.resetTime();

        myClock.setNoCount(0);
        myClockTwo.setNoCount(0);

        myClock.setMyPath(0);
        myClockTwo.setMyPath(0);

        jPanelBoardOne.removeAll();
        jPanelBoardTwo.removeAll();
        startCounter = 0;
        endCounter = 0;
        path.clear();

        // if else statatment to select which maze to style the user has selected
        if (jComboBoxMazeSelect.getSelectedIndex() == 0) {
            createEmptyBoard();
        } else {
            createEmptyBoard();
            MazeGenerator mazeGen = new MazeGenerator(mazeArray, mazeArrayCopy, jComboBoxMazeSelect.getSelectedIndex());
            Thread myThread = new Thread(mazeGen);
            myThread.start();
        }

        jlbPath.setText("");
    }//GEN-LAST:event_jbtnNewMazeActionPerformed

    private void jComboBoxBoardOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBoardOneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBoardOneActionPerformed

    private void jPanelBoardTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardTwoMouseClicked
        // allows the user to select start and finish by clicking on the panel.
        try {
            // gets the position of mouse clicked and divides by 10 because of
            // our nodes our size 10 and we needed the proper integer for our
            // array position. then we add one for correction.
            int x = (evt.getX() / 10) + 1;
            int y = (evt.getY() / 10) + 1;

            // checks to see if the position is a proper move.
            if (mazeArray[y][x].getPieceColor().equals(Color.white) || jRadioButtonClear.isSelected()) {
                // counter tells the computer how many times the user has
                // clicked in order to select start and finish and prevent more
                // than two clicks.
                if (jRadioButtonObstical.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.black);
                    mazeArrayCopy[y][x].setPieceColor(Color.black);

                } else if (jRadioButtonClear.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.white);
                    mazeArrayCopy[y][x].setPieceColor(Color.white);
                } else if (jRadioButtonStart.isSelected() && startCounter < 1) {
                    // set the start node color to red.
                    mazeArray[y][x].setPieceColor(Color.red);
                    mazeArrayCopy[y][x].setPieceColor(Color.red);
                    // set node start to the node the user-selected  and also
                    // increment the counter
                    start = mazeArray[y][x];
                    startTwo = mazeArrayCopy[y][x];
                    start.setgCost(0);
                    startTwo.setgCost(0);
                    startCounter++;
                } // checks for a second click to choose the ending node.
                else if (jRadioButtonTarget.isSelected() && endCounter < 1) {
                    mazeArray[y][x].setPieceColor(Color.blue);
                    mazeArrayCopy[y][x].setPieceColor(Color.blue);

                    end = mazeArray[y][x];
                    endTwo = mazeArrayCopy[y][x];
                    end.sethCost(0);
                    endTwo.sethCost(0);
                    endCounter++;
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jPanelBoardTwoMouseClicked

    private void jPanelBoardTwoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardTwoMouseDragged
        // allows the user to select start and finish by clicking on the panel.
        try {
            // gets the position of mouse clicked and divides by 10 because of
            // our nodes our size 10 and we needed the proper integer for our
            // array position. then we add one for correction.
            int x = (evt.getX() / 10) + 1;
            int y = (evt.getY() / 10) + 1;

            // checks to see if the position is a proper move.
            if (mazeArray[y][x].getPieceColor().equals(Color.white) || jRadioButtonClear.isSelected()) {
                // counter tells the computer how many times the user has
                // clicked in order to select start and finish and prevent more
                // than two clicks.
                if (jRadioButtonObstical.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.black);
                    mazeArrayCopy[y][x].setPieceColor(Color.black);
                } else if (jRadioButtonClear.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.white);
                    mazeArrayCopy[y][x].setPieceColor(Color.white);
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jPanelBoardTwoMouseDragged

    private void jPanelBoardOneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardOneMousePressed

    }//GEN-LAST:event_jPanelBoardOneMousePressed

    private void jPanelBoardOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardOneMouseClicked
        // allows the user to select start and finish by clicking on the panel.
        try {
            // gets the position of mouse clicked and divides by 10 because of
            // our nodes our size 10 and we needed the proper integer for our
            // array position. then we add one for correction.
            int x = (evt.getX() / 10) + 1;
            int y = (evt.getY() / 10) + 1;

            // checks to see if the position is a proper move.
            if (mazeArray[y][x].getPieceColor().equals(Color.white) || jRadioButtonClear.isSelected()) {
                // counter tells the computer how many times the user has
                // clicked in order to select start and finish and prevent more
                // than two clicks.
                if (jRadioButtonObstical.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.black);
                    mazeArrayCopy[y][x].setPieceColor(Color.black);

                } else if (jRadioButtonClear.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.white);
                    mazeArrayCopy[y][x].setPieceColor(Color.white);
                } else if (jRadioButtonStart.isSelected() && startCounter < 1) {
                    // set the start node color to red.
                    mazeArray[y][x].setPieceColor(Color.red);
                    mazeArrayCopy[y][x].setPieceColor(Color.red);
                    // set node start to the node the user-selected  and also
                    // increment the counter
                    start = mazeArray[y][x];
                    startTwo = mazeArrayCopy[y][x];
                    start.setgCost(0);
                    startTwo.setgCost(0);
                    startCounter++;
                } // checks for a second click to choose the ending node.
                else if (jRadioButtonTarget.isSelected() && endCounter < 1) {
                    mazeArray[y][x].setPieceColor(Color.blue);
                    mazeArrayCopy[y][x].setPieceColor(Color.blue);

                    end = mazeArray[y][x];
                    endTwo = mazeArrayCopy[y][x];
                    end.sethCost(0);
                    endTwo.sethCost(0);
                    endCounter++;
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jPanelBoardOneMouseClicked

    private void jPanelBoardOneMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardOneMouseDragged
        // allows the user to select start and finish by clicking on the panel.
        try {
            // gets the position of mouse clicked and divides by 10 because of
            // our nodes our size 10 and we needed the proper integer for our
            // array position. then we add one for correction.
            int x = (evt.getX() / 10) + 1;
            int y = (evt.getY() / 10) + 1;

            // checks to see if the position is a proper move.
            if (mazeArray[y][x].getPieceColor().equals(Color.white) || jRadioButtonClear.isSelected()) {
                // counter tells the computer how many times the user has
                // clicked in order to select start and finish and prevent more
                // than two clicks.
                if (jRadioButtonObstical.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.black);
                    mazeArrayCopy[y][x].setPieceColor(Color.black);
                } else if (jRadioButtonClear.isSelected()) {
                    mazeArray[y][x].setPieceColor(Color.white);
                    mazeArrayCopy[y][x].setPieceColor(Color.white);
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jPanelBoardOneMouseDragged

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // allows the user to exit
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemReturnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReturnMenuActionPerformed
        // allows the user to return to menu
        menuFrame menu = new menuFrame();

        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jMenuItemReturnMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> jComboBoxBoardOne;
    private javax.swing.JComboBox<String> jComboBoxBoardTwo;
    private javax.swing.JComboBox<String> jComboBoxMazeSelect;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemReturnMenu;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JLayeredPane jPanelBoardOne;
    private javax.swing.JLayeredPane jPanelBoardTwo;
    private javax.swing.JRadioButton jRadioButtonClear;
    private javax.swing.JRadioButton jRadioButtonObstical;
    private javax.swing.JRadioButton jRadioButtonStart;
    private javax.swing.JRadioButton jRadioButtonTarget;
    private javax.swing.JButton jbtnNewMaze;
    private javax.swing.JButton jbtnStart;
    private javax.swing.JButton jbttnReset;
    private javax.swing.JLabel jlbPath;
    // End of variables declaration//GEN-END:variables
}
