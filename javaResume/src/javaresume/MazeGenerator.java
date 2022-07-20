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

/**
 *
 * @author matth
 */
public class MazeGenerator implements Runnable {
    
    // varables for the  class
    Node[][] mazeArray;
    Node[][] mazeArrayCopy;

    Random rand = new Random();

    int userSelection;

    int counter = 0;

    // constructor for the class
    public MazeGenerator(Node[][] mazeArray, Node[][] mazeArrayCopy, int userSelection) {
        this.mazeArray = mazeArray;
        this.mazeArrayCopy = mazeArrayCopy;
        this.userSelection = userSelection;
    }

    @Override
    public void run() {
        if (userSelection == 1) {
            randomMaze();
        } else if (userSelection == 2) {
            depthFirstMaze();
        } else {
            recursiveDivisonMaze();
        }

    }

    // sleep function slows the pathing so we can see it.
    private void mySleep() {
        try {
            Thread.sleep(50);

        } catch (InterruptedException ex) {
            Logger.getLogger(Frame1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // the function creates the board dynamically.
    private void randomMaze() {

        int x = 0;
        int y = 0;
        int randomNumber;

        int pieceWidth = 10;

        // double for loop to populate the panel and our array
        for (int k = 1; k < mazeArray.length - 1; k++) {
            for (int i = 1; i < mazeArray[k].length - 1; i++) {

                randomNumber = rand.nextInt(3);

                // There is a ⅔ chance of being black and a ⅓ chance of 
                // being white.
                if (randomNumber > 1) {
                    mySleep();
                    mazeArray[k][i].setPieceColor(Color.black);
                    mazeArrayCopy[k][i].setPieceColor(Color.black);
                }

            }

        }

    }

    // function fides if the nodes next to the current nodes that are able to be pathed to
    private void findAdjacentNodes(Node tempNode, ArrayList<Node> tempArray) {

        // if the node that is under the current node is not equal to black or out of bounds of the maze or have been searched and adds it to the array list
        if (mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()] != null
                && !mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()].getSearched()) {

            tempNode.down = mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()];
            tempArray.add(tempNode.down);

        }
        
        // if the node that is above the current node is not equal to black or out of bounds of the maze or have been searched and adds it to the array list
        if (mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()] != null
                && !mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()].getSearched()) {

            tempNode.up = mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()];

            tempArray.add(tempNode.up);

        }
        
        // if the node that is right of the current node is not equal to black or out of bounds of the maze or have been searched and adds it to the array list
        if (mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1] != null
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1].getSearched()) {

            tempNode.right = mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1];

            tempArray.add(tempNode.right);

        }
        
        // if the node that is left of the current node is not equal to black or out of bounds of the maze or have been searched and adds it to the array list
        if (mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1] != null
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1].getSearched()) {

            tempNode.left = mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1];

            tempArray.add(tempNode.left);
        }

    }

    // function uses a modified depth first algorithm to generate a maze
    private void depthFirstMaze() {

        int randomNumber = 0;

        // stack to save the nodes that will be the wight paths in the maze
        PathStack myStack = new PathStack(1275);
        // nodes that have been pathed to
        ArrayList<Node> currentArray = new ArrayList<Node>();
        // nodes that are around the pathed node
        ArrayList<Node> tempArray = new ArrayList<Node>();

        // instantinating the starting node in the top left corner
        Node currentNode = mazeArray[1][1];

        Node tempNode;

        // call function to find nodes next to current node
        findAdjacentNodes(currentNode, currentArray);

        // move current node on to the searched nodes stack
        myStack.push(currentNode);
        
        // while there are nodes in the stack the loop continues
        while (myStack.getTop() >= 0) {

            // if node has nabores we set the current node to searched select a nabor to path to and paint a node black
            if (currentArray.size() != 0) {
                currentNode.setSearched(true);
                randomNumber = rand.nextInt(currentArray.size());
                currentNode = currentArray.get(randomNumber);
                currentArray.remove(randomNumber);
                myStack.push(currentNode);

                // adds the old nodes nabors to a temp array so we can paint one of them black
                for (int i = 0; i < currentArray.size(); i++) {
                    tempArray.add(currentArray.get(i));
                }

                currentArray.clear();

                // call function to find the new current nodes nabors
                findAdjacentNodes(currentNode, currentArray);

                // paints one of the old nabors black
                if (currentArray.size() >= 1 && tempArray.size() != 0) {
                    mySleep();
                    tempArray.get(0).setPieceColor(Color.black);
                    mazeArrayCopy[tempArray.get(0).getNodeRowPosition()][tempArray.get(0).getNodeColPosition()].setPieceColor(Color.black);
                }

            } 
            
            // else statment pops the current node of the stack when it has no nabors and finds the preves nodes nabors
            else {
                currentNode = myStack.pop();
                findAdjacentNodes(currentNode, currentArray);
            }

            tempArray.clear();

        }

    }

    // funcion uses a recursive divison algorithm to generate a maze
    private void generateDivisonMaze(Subdivision current, int randomNumberOne, int randomNumberTwo) {
        
        // aray list keeps tark of moves that will paint walls over passeges
        ArrayList<Integer> invalidMoves = new ArrayList<Integer>();

        // if the width of the subdivesion is grater than the hight of the subdivision make a virtical line in the subdivision
        if (current.getWidth() > current.getHieght()) {

            // select a random number to create a line
            randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;

            // while loop findes the invalid moves and adds them to an array list tell we find a valid move
            while (invalidMoves.size() < current.getWidth()) {

                // if the node above the start of the line is not a null node and it equals white then it is a passage way
                // so wee add it to the invalid move list
                if (mazeArray[current.getLowerHieght() - 1][randomNumberOne] != null) {
                    if (mazeArray[current.getLowerHieght() - 1][randomNumberOne].getPieceColor().equals(Color.white)) {
                        invalidMoves.add(randomNumberOne);
                    }
                }

                // if the node below the start of the line is not a null node and it equals white then it is a passage way
                // so wee add it to the invalid move list
                if (mazeArray[current.getUpperHieght() + 1][randomNumberOne] != null) {
                    if (mazeArray[current.getUpperHieght() + 1][randomNumberOne].getPieceColor().equals(Color.white)) {
                        invalidMoves.add(randomNumberOne);
                    }
                }

                // if random nubmer is not in the invalid move list we break the loop to contiun the function with a valid
                // number
                if (!invalidMoves.contains(randomNumberOne)) {
                    break;
                }
                
                // selects a new random number for the next time through the loop
                randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;
            }

            // if we get trought the loop and there is no invalid move the function will skip painting the line
            if (!invalidMoves.contains(randomNumberOne)) {
                // for loop to paint a horizontal line
                for (int i = current.getLowerHieght() - 1; i <= current.getUpperHieght() + 1; i++) {
                    if (mazeArray[i][randomNumberOne] != null) {
                        mySleep();
                        mazeArray[i][randomNumberOne].setPieceColor(Color.black);
                        mazeArrayCopy[i][randomNumberOne].setPieceColor(Color.black);
                    }
                }

                // selects a random number to create a passage
                randomNumberTwo = rand.nextInt(current.getUpperHieght() - current.getLowerHieght()) + current.getLowerHieght();
                mazeArray[randomNumberTwo][randomNumberOne].setPieceColor(Color.white);
                mazeArrayCopy[randomNumberTwo][randomNumberOne].setPieceColor(Color.white);

                // creates new subdivions for the next recursive call
                Subdivision right = new Subdivision();
                Subdivision left = new Subdivision();

                // saves the bounds and widith and hight of the subdivisons
                right.setInvalidMove(randomNumberOne + 1);

                right.setLowerWidth(randomNumberOne + 1);
                right.setUpperWidth(current.getUpperWidth());

                right.setWidth(current.getUpperWidth() - right.getLowerWidth());

                right.setLowerHieght(current.getLowerHieght());
                right.setUpperHieght(current.getUpperHieght());

                right.setHieght(current.getHieght());

                left.setInvalidMove(randomNumberOne - 1);

                left.setLowerWidth(current.getLowerWidth());
                left.setUpperWidth(randomNumberOne - 1);

                left.setWidth(left.getUpperWidth() - left.getLowerWidth());

                left.setLowerHieght(current.getLowerHieght());
                left.setUpperHieght(current.getUpperHieght());

                left.setHieght(current.getHieght());

                // stops the recursive call if the subdivisons are too small
                if (right.getWidth() > 1) {
                    generateDivisonMaze(right, randomNumberOne, randomNumberTwo);

                }

                if (left.getWidth() > 1) {
                    generateDivisonMaze(left, randomNumberOne, randomNumberTwo);
                }
            }

        }

        // similar to the first if statment but divides the subdivison horizontally
        if (current.getHieght() >= current.getWidth()) {
            invalidMoves.clear();
            randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;

            while (invalidMoves.size() < current.getHieght()) {

                if (mazeArray[randomNumberOne][current.getUpperWidth() + 1] != null) {
                    if (mazeArray[randomNumberOne][current.getUpperWidth() + 1].getPieceColor().equals(Color.white)) {
                        invalidMoves.add(randomNumberOne);
                    }
                }

                if (mazeArray[randomNumberOne][current.getLowerWidth() - 1] != null) {
                    if (mazeArray[randomNumberOne][current.getLowerWidth() - 1].getPieceColor().equals(Color.white)) {
                        invalidMoves.add(randomNumberOne);
                    }
                }

                if (!invalidMoves.contains(randomNumberOne)) {
                    break;
                }
                randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;
            }

            if (!invalidMoves.contains(randomNumberOne)) {
                for (int i = current.getLowerWidth() - 1; i <= current.getUpperWidth() + 1; i++) {
                    if (mazeArray[randomNumberOne][i] != null) {
                        mySleep();
                        mazeArray[randomNumberOne][i].setPieceColor(Color.black);
                        mazeArrayCopy[randomNumberOne][i].setPieceColor(Color.black);
                    }

                }

                randomNumberTwo = rand.nextInt(current.getUpperWidth() - current.getLowerWidth()) + current.getLowerWidth();
                mazeArray[randomNumberOne][randomNumberTwo].setPieceColor(Color.white);
                mazeArrayCopy[randomNumberOne][randomNumberTwo].setPieceColor(Color.white);

                Subdivision up = new Subdivision();
                Subdivision down = new Subdivision();

                up.setInvalidMove(randomNumberOne - 1);

                up.setLowerWidth(current.getLowerWidth());
                up.setUpperWidth(current.getUpperWidth());

                up.setWidth(current.getWidth());

                up.setLowerHieght(current.getLowerHieght());
                up.setUpperHieght(randomNumberOne - 1);

                up.setHieght(up.getUpperHieght() - up.getLowerHieght());

                down.setInvalidMove(randomNumberOne + 1);

                down.setLowerWidth(current.getLowerWidth());
                down.setUpperWidth(current.getUpperWidth());

                down.setWidth(current.getWidth());

                down.setLowerHieght(randomNumberOne + 1);
                down.setUpperHieght(current.getUpperHieght());

                down.setHieght(current.getUpperHieght() - down.getLowerHieght());

                if (up.getHieght() > 1) {
                    generateDivisonMaze(up, randomNumberOne, randomNumberTwo);
                }

                if (down.getHieght() > 1) {
                    generateDivisonMaze(down, randomNumberOne, randomNumberTwo);
                }
            }

        }

    }

    // creates the fists subdivion for the generateDivisonMaze function
    private void recursiveDivisonMaze() {

        Subdivision start = new Subdivision();

        // seting bounds width and hight
        start.setLowerWidth(1);
        start.setUpperWidth(49);

        start.setWidth(49);

        start.setLowerHieght(1);
        start.setUpperHieght(23);

        start.setHieght(23);

        // instatninating varables for the generateDivisonMaze function
        int randomNumberOne = 0;
        int randomNumberTwo = 0;

        int lowerWidth = 1;
        int upperWidth = 49;

        int lowerHight = 1;
        int upperHight = 23;

        int width = upperWidth;
        int height = upperHight;

        // function call
        generateDivisonMaze(start, randomNumberOne, randomNumberTwo);

    }
}
