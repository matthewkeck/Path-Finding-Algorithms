/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectjava2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author matthewrkeck
 */
public class Node extends JComponent {
    // Allows us to change the color of the node.
    Color pieceColor = Color.red;

    // Allows us to determine the position of each node in the array in which we 
    // store all the nodes.
    int nodeRowPosition;
    int nodeColPosition;

    // allows us to connect nodes in all the cardinal directions.
    public Node up;
    public Node right;
    public Node down;
    public Node left;
    
    // allows us to store the parent of each node so we can reconstruct the path.
    public Node parent;
    
    // constructor for our node.
    public Node() {
        this.setForeground(pieceColor);
        this.setVisible(true);
    }

    // function all sets the color and the size of the nodes on the panel.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //sets peace color and size of squars
        g.setColor(pieceColor);
        g.fillRect(0, 0, 10, 10);
        g.setColor(pieceColor);

    }

    // allows us to set and get the node row and column position.
    public int getNodeRowPosition() {
        return nodeRowPosition;
    }

    public void setNodeRowPosition(int nodeRowPosition) {
        this.nodeRowPosition = nodeRowPosition;
    }

    public int getNodeColPosition() {
        return nodeColPosition;
    }

    public void setNodeColPosition(int nodeColPosition) {
        this.nodeColPosition = nodeColPosition;
    }
    
    // allows us to set and get the color of each piece on the board.
    public Color getPieceColor() {
        return pieceColor;
    }
    
    public void setPieceColor(Color pieceColor) {
        this.pieceColor = pieceColor;
        this.paintImmediately(0, 0, 10, 10);
    }
}
