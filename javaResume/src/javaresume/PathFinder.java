/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaresume;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Math;
import java.util.*;

/**
 *
 * @author matth
 */

// pathfinder class creates a thread and then calls a path finding aglorithm that finds the solution to the maze while displaying its node
// searches to the user
public class PathFinder implements Runnable {

    Clock myClock;
    Node[][] mazeArrayTemp;
    ArrayList<Node> path;

    boolean goal = false;
    Node start;
    Node end;
    int userSelection;
    int nodeCount = 0;

    // consturctor for the pathFinder class
    public PathFinder(Node[][] mazeArrayTemp, ArrayList<Node> path, Node start, Node end, int userSelection, Clock myClock) {
        this.myClock = myClock;
        this.mazeArrayTemp = mazeArrayTemp;
        this.path = path;
        this.start = start;
        this.end = end;
        this.userSelection = userSelection;
    }

    // run function allows us to create threads so we can run to algorithms at the same time
    @Override
    public void run() {

        // if else statments determin the algorithm the user selected
        if (userSelection == 0) {
            // starts the clock class to time the algorithm count the nodes and count the path length
            myClock.Start();
            // calls the depth first search algorithm
            if (deapthSearch(start, mazeArrayTemp) == true) {

                drawPath();
            }
            myClock.Stop();

        } 
        // calss the a star algorithm
        else if (userSelection == 2) {
            myClock.Start();
            aStar(start, end, mazeArrayTemp);
            myClock.Stop();
        } 
        // calls the breadth search algorthim
        else {
            myClock.Start();
            breadthSearch(start, mazeArrayTemp);
            myClock.Stop();
        }

    }

    // sleep function slows the pathing so we can see it.
    private void mySleep() {
        try {
            Thread.sleep(20);

        } catch (InterruptedException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // g cost function is how far the searched node is from the start. function we use manhatten distatnce
    private int gCost(int x, int y) {
        int distance;
        return distance = Math.abs(start.getNodeColPosition() - x) + Math.abs((start.getNodeRowPosition() - y));
    }

    // h cost function is how far the searched node is from the goal. we use manhatten distatnce
    private int hCost(int x, int y) {
        int distance;
        return distance = Math.abs(end.getNodeColPosition() - x) + Math.abs((end.getNodeRowPosition() - y));
    }

    // a star algorithm knows were the end node is and priorites the nodes with the lowest f to be searched first
    // a star algorithm finds a short path while checking fewernodes then breadth first search
    private void aStar(Node current, Node end, Node[][] mazeArrayTemp) {
        // two arraylist for searched and non-searched nodes
        ArrayList<Node> openNodes = new ArrayList<Node>();
        ArrayList<Node> closedNodes = new ArrayList<Node>();

        int x;
        int y;

        int hCost;
        int gCost;
        int fCost;

        // gets teh nodes position in the array
        x = current.getNodeColPosition();
        y = current.getNodeRowPosition();

        // sets the g h and f cost
        current.sethCost(hCost(x, y));
        current.setgCost(gCost(x, y));
        current.setfCost();

        // adds node to open nodes list
        openNodes.add(current);

        // searches tell there are no nodes in the open list or the goal has been reached
        while (!openNodes.isEmpty() && goal == false) {
            // sets current node equal to the first node in the open list
            current = openNodes.get(0);
            mySleep();

            // for loop finds the node with the loest f cost in the open list
            for (int i = 0; i < openNodes.size(); i++) {
                if (openNodes.get(i).getfCost() < current.getfCost() || openNodes.get(i).getfCost() == current.getfCost() && openNodes.get(i).getgCost() < current.gethCost()) {
                    current = openNodes.get(i);
                }

            }
            // adds the node to the searched list and removes it form the open list
            closedNodes.add(current);
            openNodes.remove(current);

            // searches left of the current node if statment checks if it is avalid path or if it hass been searched already
            if (mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1] != null
                    && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                    && !closedNodes.contains(mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1])) {

                current.left = mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1];
                // finds the popition fo the left node in the array
                x = current.left.getNodeColPosition();
                y = current.left.getNodeRowPosition();

                // sets its g cost
                gCost = current.getgCost() + 1;

                // if the new gCost is less then an old gCost calulated then we save the new gCost
                // recalulate the fCost and save the new parent of the left node
                if (!openNodes.contains(current.left) || gCost < current.gCost) {
                    current.left.setgCost(gCost);
                    current.left.setfCost();
                    current.left.parent = current;

                    // if open nodes does not contain the left node calculate the h cost and recalculate the f cost
                    // and add the left node to the open list
                    if (!openNodes.contains(current.left)) {
                        current.left.sethCost(hCost(x, y));
                        current.left.setfCost();
                        openNodes.add(current.left);
                        // checks if the left node is not the goal node and paint the node green adds to the searched node count
                        // and sets the clock to display the node count
                        if (!current.left.getPieceColor().equals(Color.blue)) {
                            current.left.setPieceColor(Color.green);
                            nodeCount += 1;
                            myClock.setNoCount(nodeCount);
                        }
                    }
                }

            }
            // Right
            if (mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1] != null
                    && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                    && !closedNodes.contains(mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1])) {

                current.right = mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1];
                x = current.right.getNodeColPosition();
                y = current.right.getNodeRowPosition();

                gCost = current.getgCost() + 1;

                if (!openNodes.contains(current.right) || gCost < current.gCost) {
                    current.right.setgCost(gCost);
                    current.right.setfCost();
                    current.right.parent = current;

                    if (!openNodes.contains(current.right)) {
                        current.right.sethCost(hCost(x, y));
                        current.right.setfCost();
                        openNodes.add(current.right);
                        if (!current.right.getPieceColor().equals(Color.blue)) {
                            current.right.setPieceColor(Color.green);
                            nodeCount += 1;
                            myClock.setNoCount(nodeCount);
                        }

                    }
                }

            }

            // Up
            if (mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()] != null
                    && !mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                    && !closedNodes.contains(mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()])) {

                current.up = mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()];
                x = current.up.getNodeColPosition();
                y = current.up.getNodeRowPosition();

                gCost = current.getgCost() + 1;

                if (!openNodes.contains(current.up) || gCost < current.gCost) {
                    current.up.setgCost(gCost);
                    current.up.setfCost();
                    current.up.parent = current;

                    if (!openNodes.contains(current.up)) {
                        current.up.sethCost(hCost(x, y));
                        current.up.setfCost();
                        openNodes.add(current.up);
                        if (!current.up.getPieceColor().equals(Color.blue)) {
                            current.up.setPieceColor(Color.green);
                            nodeCount += 1;
                            myClock.setNoCount(nodeCount);
                        }
                    }
                }

            }

            // Down
            if (mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()] != null
                    && !mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                    && !closedNodes.contains(mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()])) {

                current.down = mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()];
                x = current.down.getNodeColPosition();
                y = current.down.getNodeRowPosition();

                gCost = current.getgCost() + 1;

                if (!openNodes.contains(current.down) || gCost < current.gCost) {
                    current.down.setgCost(gCost);
                    current.down.setfCost();
                    current.down.parent = current;

                    if (!openNodes.contains(current.down)) {
                        current.down.sethCost(hCost(x, y));
                        current.down.setfCost();
                        openNodes.add(current.down);
                        if (!current.down.getPieceColor().equals(Color.blue)) {
                            current.down.setPieceColor(Color.green);
                            nodeCount += 1;
                            myClock.setNoCount(nodeCount);
                        }
                    }
                }

            }

            // checks if the current node is the goal node and if so call the function
            // to paint the path and sets goal equal to true to stop the loop
            if (current.getPieceColor().equals(Color.blue)) {
                goal = true;
                recontructPath(current);
                break;
            }
        }
    }

    //The breadth search function finds the shortest path to the maze but will
    //usually check more nodes than the depth search function.
    private boolean breadthSearch(Node current, Node[][] mazeArrayTemp) {
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
                if (mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()] != null
                        && !mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                        && !mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.green)
                        && !mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()].getPieceColor().equals(Color.red)
                        && goal != true) {

                    // Creates a new node that is above the current node.
                    current.up = mazeArrayTemp[current.getNodeRowPosition() - 1][current.getNodeColPosition()];
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
                        nodeCount += 1;
                        myClock.setNoCount(nodeCount);
                    }

                }

                if (mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1] != null
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.green)
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.right = mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() + 1];
                    nextLevel.add(current.right);

                    current.right.parent = current;

                    if (current.right.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.right.setPieceColor(Color.green);
                        nodeCount += 1;
                        myClock.setNoCount(nodeCount);
                    }

                }

                if (mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()] != null
                        && !mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.black)
                        && !mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.green)
                        && !mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.down = mazeArrayTemp[current.getNodeRowPosition() + 1][current.getNodeColPosition()];
                    nextLevel.add(current.down);

                    current.down.parent = current;

                    if (current.down.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.down.setPieceColor(Color.green);
                        nodeCount += 1;
                        myClock.setNoCount(nodeCount);
                    }

                }

                if (mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1] != null
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.green)
                        && !mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1].getPieceColor().equals(Color.red)
                        && goal != true) {

                    current.left = mazeArrayTemp[current.getNodeRowPosition()][current.getNodeColPosition() - 1];
                    nextLevel.add(current.left);

                    current.left.parent = current;

                    if (current.left.getPieceColor().equals(Color.blue)) {
                        goal = true;
                        break;
                    } else {
                        current.left.setPieceColor(Color.green);
                        nodeCount += 1;
                        myClock.setNoCount(nodeCount);
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

                //jlbPath.setText("NO PATH");
                return goal;
            }

        }
        // Call reconstruct path after loop has finished.
        recontructPath(current);
        return goal;
    }

    // Function reconstructs the shortest path from breadth search.
    private void recontructPath(Node current) {
        int counter = 0;
        // while statement loops through all the nodes that are the parent to
        // the node that found the blue node.
        while (!current.parent.getPieceColor().equals(Color.red)) {
            if (!current.getPieceColor().equals(Color.blue)) {
                current.setPieceColor(Color.yellow);
                mySleep();
                counter++;
                myClock.setMyPath(counter);
            }
            current = current.parent;

        }
        current.setPieceColor(Color.yellow);
        counter++;
        myClock.setMyPath(counter);
    }
    
    // The function uses a recursive depth search algorithm to find the blue
    // square.
    private boolean deapthSearch(Node temp, Node[][] mazeArrayTemp) {
        // nodes we will recure represent directions in the maze.
        Node up;
        Node down;
        Node left;
        Node right;

        // if statements checks up, down, left, or right to see if its a valid
        // move and if we have met our goal
        if (mazeArrayTemp[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()]
                != null && !mazeArrayTemp[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArrayTemp[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.green)
                && !mazeArrayTemp[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()].getPieceColor().equals(Color.red)
                && goal != true) {

            // if valid move then we set the node equal to the searched node
            down = mazeArrayTemp[temp.getNodeRowPosition() + 1][temp.getNodeColPosition()];

            // checks to see if we found our goal node and if so sets our goal
            // variable to true and returns to break the recursion.
            if (down.getPieceColor().equals(Color.blue)) {
                goal = true;
                return goal;
            }

            // if the goal has not been found add the node to our path array
            // list.
            if (goal == false) {
                path.add(down);
            }

            // change the color of the node.
            down.setPieceColor(Color.green);
            nodeCount += 1;
            myClock.setNoCount(nodeCount);

            mySleep();
            // recurse now with the node we pathed to repeat the process.
            deapthSearch(down, mazeArrayTemp);

        }
        if (mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1]
                != null && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.green)
                && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1].getPieceColor().equals(Color.red)
                && goal != true) {

            left = mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() - 1];

            if (left.getPieceColor() == (Color.blue)) {
                goal = true;
                return goal;
            }

            if (goal == false) {
                path.add(left);
            }

            left.setPieceColor(Color.green);

            nodeCount += 1;
            myClock.setNoCount(nodeCount);

            mySleep();
            deapthSearch(left, mazeArrayTemp);
        }

        if (mazeArrayTemp[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()]
                != null && !mazeArrayTemp[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArrayTemp[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.green)
                && !mazeArrayTemp[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()].getPieceColor().equals(Color.red)
                && goal != true) {

            up = mazeArrayTemp[temp.getNodeRowPosition() - 1][temp.getNodeColPosition()];

            if (up.getPieceColor() == (Color.blue)) {
                goal = true;
                return goal;
            }

            if (goal == false) {
                path.add(up);
            }

            up.setPieceColor(Color.green);

            nodeCount += 1;
            myClock.setNoCount(nodeCount);

            mySleep();
            deapthSearch(up, mazeArrayTemp);
        }

        if (mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1]
                != null && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.green)
                && !mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1].getPieceColor().equals(Color.red)
                && goal != true) {

            right = mazeArrayTemp[temp.getNodeRowPosition()][temp.getNodeColPosition() + 1];

            if (right.getPieceColor() == (Color.blue)) {
                goal = true;
                return goal;
            }

            if (goal == false) {
                path.add(right);
            }
            right.setPieceColor(Color.green);

            nodeCount += 1;
            myClock.setNoCount(nodeCount);

            mySleep();
            deapthSearch(right, mazeArrayTemp);
        }

        // if goal is equal to false and we have made it through the above if
        // statements we will backtrack to a node that we had options to move
        // while we are backtracking we remove nodes in the array list to remove
        // unnecessary nodes on our path
        if (goal == false) {
            int index = path.size() - 1;
            if (index == 0) {
                return goal;
            }
            path.remove(index);
        }
        return goal;
    }

    // draw path function sets all nodes on the path to the color yellow.
    private void drawPath() {
        for (int i = 0; i < path.size(); i++) {
            mySleep();
            path.get(i).setPieceColor(Color.yellow);
            myClock.setMyPath(i);
        }
    }

}
