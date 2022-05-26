package finalprojectjava2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matthewrkeck
 */
public class Frame1 extends javax.swing.JFrame {

    // Global variables for the array that holds all nodes. The array list holds 
    //the path for our depth-first search function.
    Node[][] mazeArray = new Node[25][51];
    ArrayList<Node> path = new ArrayList<Node>();

    // Global variables for our path found condition, starting node, RNG, and 
    // counter.
    boolean goal = false;
    Node start;
    Random rand = new Random();
    int counter = 0;

    /**
     * Creates new form Frame1
     */
    // the constructor creates our frame creates the board that holds the node 
    // and adds items to our combo box.
    public Frame1() {
        initComponents();
        this.setLocationRelativeTo(null);

        // Calls the create board function.
        createBoard();

        jComboBoxAlgorithm.removeAllItems();
        jComboBoxAlgorithm.addItem("Depth Algorithm");
        jComboBoxAlgorithm.addItem("Breadth Algorithm");

    }

    // the function creates the board dynamically.
    private void createBoard() {
        int x = 0;
        int y = 0;
        int randomNumber;

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
                    randomNumber = rand.nextInt(3);

                    // creats a new game piece
                    Node maze = new Node();

                    // sets the loaction of the game peace and the color
                    maze.setLocation(x, y);
                    maze.setSize(100, 100);
                    maze.setVisible(true);

                    // There is a ⅔ chance of being black and a ⅓ chance of 
                    // being white.
                    if (randomNumber > 1) {
                        maze.setPieceColor(Color.black);
                    } else {
                        maze.setPieceColor(Color.white);
                        //If the node is white we save the row and column 
                        // position in the maze array.
                        maze.setNodeRowPosition(k);
                        maze.setNodeColPosition(i);
                    }

                    // repaints the board to display the new node colors.
                    jPanelBoard.repaint();
                    // Increments to x so that the next time through the loop 
                    // our node will be in the correct position to add a new node.
                    x = x + 10;

                    // adds the node to the panil and maze array.
                    jPanelBoard.add(maze);
                    mazeArray[k][i] = maze;

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
        jPanelBoard.repaint();

    }

    // sleep function slows the pathing so we can see it.
    private void mySleep() {
        try {
            Thread.sleep(20);

        } catch (InterruptedException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //The breadth search function finds the shortest path to the maze but will 
    //usually check more nodes than the depth search function.
    private void breadthSearch(Node current) {
        //Array list to store the nodes on the current level of the tree and to 
        //build the next level of the tree.
        ArrayList<Node> currentLevel = new ArrayList<Node>();
        ArrayList<Node> nextLevel = new ArrayList<Node>();

        // Adds the starting node.
        currentLevel.add(current);

        // while loop iterates until we find the finish node.
        while (goal == false) {

            // For loop iterates all the nodes on the current level of the tree.
            for (int i = 0; i < currentLevel.size(); i++) {
                // Selects a new node in the array list every time through the 
                // loop.
                current = currentLevel.get(i);
                // Calls sleep function to slow down the computer so we can see 
                // the animation.
                mySleep();

                // if statements check up, down, left, or right to see if it's a valid move.
                // if statements also check if we have met our goal.
                if (mazeArray[current.getNodeRowPosition() - 1][current.getNodeColPosition()] != null
                        && !mazeArray[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                        && !mazeArray[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.green)
                        && !mazeArray[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.red)
                        && goal != true) {

                    // Creates a new node that is above the current node.
                    current.up = mazeArray[current.getNodeRowPosition() - 1][current.getNodeColPosition()];
                    // Adds the node to the next level array list because it 
                    // will be on the next level of the tree.
                    nextLevel.add(current.up);
                    
                    // creates a link from the up node back to the current to 
                    // retain the path.
                    current.up.parent = current;

                    // checks to see if the up node is the finish node.
                    if (current.up.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        // Breaks the for loop if it is.
                        break;
                    } else {
                        // If not we set the status of the up node to searched 
                        // by setting its color to green.
                        current.up.setPieceColor(Color.green);
                    }

                }

                if (mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() + 1] != null
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.green)
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.right = mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() + 1];
                    nextLevel.add(current.right);

                    current.right.parent = current;

                    if (current.right.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.right.setPieceColor(Color.green);
                    }

                }

                if (mazeArray[current.getNodeRowPosition() + 1][current.getNodeColPosition()] != null
                        && !mazeArray[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                        && !mazeArray[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.green)
                        && !mazeArray[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.down = mazeArray[current.getNodeRowPosition() + 1][current.getNodeColPosition()];
                    nextLevel.add(current.down);

                    current.down.parent = current;

                    if (current.down.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.down.setPieceColor(Color.green);
                    }

                }

                if (mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() - 1] != null
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.green)
                        && !mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.left = mazeArray[current.getNodeRowPosition()][current.getNodeColPosition() - 1];
                    nextLevel.add(current.left);

                    current.left.parent = current;

                    if (current.left.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.left.setPieceColor(Color.green);
                    }

                }

            }
            
            // Checks to see if there is no path by checking if the next level 
            // in the tree has any nodes.
            if (nextLevel.size() != 0) {
                // clears the current level and then adds all the nodes from the
                // next level to the current level. 
                currentLevel.clear();
                for (int i = 0; i < nextLevel.size(); i++) {
                    currentLevel.add(nextLevel.get(i));
                }
                // clears next level.
                nextLevel.clear();
            } else {
                // if there were no valid moves we print no path and set goal 
                // equal to true.
                jlbPath.setText("NO PATH");
                goal = true;
                return;
            }

        }
        // Call reconstruct path after loop has finished.
        recontructPath(current);

    }
    
    // Function reconstructs the shortest path from breadth search.
    private void recontructPath(Node current) {
        // while statement loops through all the nodes that are the parent to 
        // the node that found the blue node.
        while (!current.parent.getPieceColor().equals(Color.red)) {
            current.setPieceColor(Color.yellow);
            mySleep();
            current = current.parent;
        }
        current.setPieceColor(Color.yellow);
    }

    // The function uses a recursive depth search algorithm to find the blue 
    // square.
    private void deapthSearch(Node temp) {
        // nodes we will recure represent directions in the maze.
        Node up;
        Node down;
        Node left;
        Node right;

        // if statements checks up, down, left, or right to see if its a valid 
        // move and if we have met our goal
        if (mazeArray[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()]
                != null && !mazeArray[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.green)
                && !mazeArray[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.red)
                && goal != true) {

            // if valid move then we set the node equal to the searched node
            down = mazeArray[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()];

            // checks to see if we found our goal node and if so sets our goal 
            // variable to true and returns to break the recursion.
            if (down.getPieceColor().equals(Color.blue)) {
                goal = true;
                return;
            }

            // if the goal has not been found add the node to our path array 
            // list.
            if (goal == false) {
                path.add(down);
            }

            // change the color of the node.
            down.setPieceColor(Color.green);
            
            mySleep();
            // recurse now with the node we pathed to repeat the process.
            deapthSearch(down);

        }
        if (mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1]
                != null && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.green)
                && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.red)
                && goal != true) {

            left = mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1];

            if (left.getPieceColor() == (Color.blue)) {
                goal = true;
                return;
            }

            if (goal == false) {
                path.add(left);
            }

            left.setPieceColor(Color.green);
            mySleep();
            deapthSearch(left);
        }

        if (mazeArray[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()]
                != null && !mazeArray[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.green)
                && !mazeArray[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.red)
                && goal != true) {

            up = mazeArray[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()];

            if (up.getPieceColor() == (Color.blue)) {
                goal = true;
                return;
            }

            if (goal == false) {
                path.add(up);
            }

            up.setPieceColor(Color.green);
            mySleep();
            deapthSearch(up);
        }

        if (mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1]
                != null && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.green)
                && !mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.red)
                && goal != true) {

            right = mazeArray[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1];

            if (right.getPieceColor() == (Color.blue)) {
                goal = true;
                return;
            }

            if (goal == false) {
                path.add(right);
            }
            right.setPieceColor(Color.green);
            mySleep();
            deapthSearch(right);
        }

        // if goal is equal to false and we have made it through the above if
        // statements we will backtrack to a node that we had options to move
        // while we are backtracking we remove nodes in the array list to remove
        // unnecessary nodes on our path
        if (goal == false) {
            int index = path.size() - 1;
            path.remove(index);
        }
    }

    // draw path function sets all nodes on the path to the color yellow.
    private void drawPath() {
        for (int i = 0; i < path.size(); i++) {
            mySleep();
            path.get(i).setPieceColor(Color.yellow);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBoard = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jbttnReset = new javax.swing.JButton();
        jlbPath = new javax.swing.JLabel();
        jComboBoxAlgorithm = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmenuQuit = new javax.swing.JMenuItem();
        jmenuMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelBoard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBoardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoardLayout = new javax.swing.GroupLayout(jPanelBoard);
        jPanelBoard.setLayout(jPanelBoardLayout);
        jPanelBoardLayout.setHorizontalGroup(
            jPanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );
        jPanelBoardLayout.setVerticalGroup(
            jPanelBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        jButton1.setText("Start Path Finder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jbttnReset.setText("Reset");
        jbttnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnResetActionPerformed(evt);
            }
        });

        jlbPath.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N

        jComboBoxAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jMenu1.setText("File");

        jmenuQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmenuQuit.setText("quit");
        jmenuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuQuitActionPerformed(evt);
            }
        });
        jMenu1.add(jmenuQuit);

        jmenuMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jmenuMenu.setText("menu");
        jmenuMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuMenuActionPerformed(evt);
            }
        });
        jMenu1.add(jmenuMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbttnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbPath, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jlbPath, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jbttnReset)
                    .addComponent(jComboBoxAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoardMouseClicked
        // allows the user to select start and finish by clicking on the panel.
        try {
            // gets the position of mouse clicked and divides by 10 because of 
            // our nodes our size 10 and we needed the proper integer for our 
            // array position. then we add one for correction.
            int x = (evt.getX() / 10) + 1;
            int y = (evt.getY() / 10) + 1;

            // checks to see if the position is a proper move.
            if (mazeArray[y][x].getPieceColor().equals(Color.white)) {
                // counter tells the computer how many times the user has 
                // clicked in order to select start and finish and prevent more 
                // than two clicks.
                if (counter == 0) {
                    // set the start node color to red.
                    mazeArray[y][x].setPieceColor(Color.red);
                    // set node start to the node the user-selected  and also 
                    // increment the counter
                    start = mazeArray[y][x];
                    counter++;
                } // checks for a second click to choose the ending node.
                else if (counter == 1) {
                    mazeArray[y][x].setPieceColor(Color.blue);
                    counter++;
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jPanelBoardMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // if statement stops the user from starting the path without selecting 
        // start and stop nodes.
        if (counter != 0) {

            try {

                if (jComboBoxAlgorithm.getSelectedIndex() == 0) {
                    // calls search and draw functions.
                    deapthSearch(start);
                    drawPath();
                }
                if (jComboBoxAlgorithm.getSelectedIndex() == 1) {
                    breadthSearch(start);
                }

            } catch (Exception e) {
                // if no path we return no path to a label.
                jlbPath.setText("NO PATH");
            }

        } else {
            // notifies the user to select start and finish.
            jlbPath.setText("enter start and finish");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbttnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnResetActionPerformed
        // allows the user to reset by clearing all old info and creating a new 
        // board.
        goal = false;
        mazeArray = new Node[25][51];
        path = new ArrayList<Node>();

        jPanelBoard.removeAll();
        counter = 0;
        path.clear();
        createBoard();

        jlbPath.setText("");
    }//GEN-LAST:event_jbttnResetActionPerformed

    private void jmenuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuMenuActionPerformed
        // allows the user to return to the menu.
        menuFrame menu = new menuFrame();

        menu.setLocationRelativeTo(null);
        menu.setVisible(true);

        this.dispose();

    }//GEN-LAST:event_jmenuMenuActionPerformed

    private void jmenuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuQuitActionPerformed
        // allows the user to exit.
        System.exit(0);
    }//GEN-LAST:event_jmenuQuitActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxAlgorithm;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLayeredPane jPanelBoard;
    private javax.swing.JButton jbttnReset;
    private javax.swing.JLabel jlbPath;
    private javax.swing.JMenuItem jmenuMenu;
    private javax.swing.JMenuItem jmenuQuit;
    // End of variables declaration//GEN-END:variables
}
