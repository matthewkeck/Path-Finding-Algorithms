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

    Node[][] mazeArray;
    Node[][] mazeArrayCopy;

    Random rand = new Random();

    int userSelection;

    int counter = 0;

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
            Thread.sleep(20);

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

    private void findAdjacentNodes(Node tempNode, ArrayList<Node> tempArray) {

        if (mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()] != null
                && !mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()].getSearched()) {

            tempNode.down = mazeArray[tempNode.getNodeRowPosition() + 1][tempNode.getNodeColPosition()];
            tempArray.add(tempNode.down);

        }
        if (mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()] != null
                && !mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()].getSearched()) {

            tempNode.up = mazeArray[tempNode.getNodeRowPosition() - 1][tempNode.getNodeColPosition()];

            tempArray.add(tempNode.up);

        }
        if (mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1] != null
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1].getSearched()) {

            tempNode.right = mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() + 1];

            tempArray.add(tempNode.right);

        }
        if (mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1] != null
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1].getPieceColor().equals(Color.black)
                && !mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1].getSearched()) {

            tempNode.left = mazeArray[tempNode.getNodeRowPosition()][tempNode.getNodeColPosition() - 1];

            tempArray.add(tempNode.left);
        }

    }

    private void depthFirstMaze() {

        int randomNumber = 0;

        PathStack myStack = new PathStack(1275);
        ArrayList<Node> currentArray = new ArrayList<Node>();
        ArrayList<Node> tempArray = new ArrayList<Node>();

        Node currentNode = mazeArray[1][1];

        Node tempNode;

        findAdjacentNodes(currentNode, currentArray);

        myStack.push(currentNode);
        while (myStack.getTop() >= 0) {

            if (currentArray.size() != 0) {
                currentNode.setSearched(true);
                randomNumber = rand.nextInt(currentArray.size());
                currentNode = currentArray.get(randomNumber);
                currentArray.remove(randomNumber);
                myStack.push(currentNode);

                for (int i = 0; i < currentArray.size(); i++) {
                    tempArray.add(currentArray.get(i));
                }

                currentArray.clear();

                findAdjacentNodes(currentNode, currentArray);

                if (currentArray.size() >= 1 && tempArray.size() != 0) {
                    mySleep();
                    tempArray.get(0).setPieceColor(Color.black);
                    mazeArrayCopy[tempArray.get(0).getNodeRowPosition()][tempArray.get(0).getNodeColPosition()].setPieceColor(Color.black);
                }

            } else {
                currentNode = myStack.pop();
                findAdjacentNodes(currentNode, currentArray);
            }

            tempArray.clear();

        }

    }

    private void generateDivisonMaze(Subdivision current, int randomNumberOne, int randomNumberTwo) {
        boolean validMove = false;

        int invalidLower = 0;
        int invalidUpper = 0;

        if (current.getWidth() > current.getHieght()) {

            randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;

            while (randomNumberOne == current.getInvalidMove()) {
                randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;
            }

            while (validMove == false) {

                if (mazeArray[current.getLowerHieght() - 1][randomNumberOne] != null) {
                    if (mazeArray[current.getLowerHieght() - 1][randomNumberOne].getPieceColor().equals(Color.white)) {
                        invalidLower = randomNumberOne;
                        randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;
                    }
                }
                if (mazeArray[current.getUpperHieght() + 1][randomNumberOne] != null) {
                    if (mazeArray[current.getUpperHieght() + 1][randomNumberOne].getPieceColor().equals(Color.white)) {
                        invalidUpper = randomNumberOne;
                        randomNumberOne = rand.nextInt(current.getUpperWidth() - current.getLowerWidth() - 1) + current.getLowerWidth() + 1;
                    }
                }
                if (randomNumberOne != invalidLower && randomNumberOne != invalidUpper) {
                    validMove = true;
                    break;
                }
                
                System.out.println(current.getUpperWidth() - current.getLowerWidth() - 1);
                        

                if (current.getWidth() <= 3) {
                    return;
                }
            }

            for (int i = current.getLowerHieght() - 1; i <= current.getUpperHieght() + 1; i++) {
                if (mazeArray[i][randomNumberOne] != null) {
                    mySleep();
                    mazeArray[i][randomNumberOne].setPieceColor(Color.black);
                    mazeArrayCopy[i][randomNumberOne].setPieceColor(Color.black);
                }
            }

            randomNumberTwo = rand.nextInt(current.getUpperHieght() - current.getLowerHieght()) + current.getLowerHieght();
            mazeArray[randomNumberTwo][randomNumberOne].setPieceColor(Color.white);
            mazeArrayCopy[randomNumberTwo][randomNumberOne].setPieceColor(Color.white);

            Subdivision right = new Subdivision();
            Subdivision left = new Subdivision();

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

            if (right.getWidth() > 1) {
                generateDivisonMaze(right, randomNumberOne, randomNumberTwo);

            }

            if (left.getWidth() > 1) {
                generateDivisonMaze(left, randomNumberOne, randomNumberTwo);
            }

        }

        if (current.getHieght() >= current.getWidth()) {

            randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;

            while (randomNumberOne == current.getInvalidMove()) {
                randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;
            }

            while (validMove == false) {

                if (mazeArray[randomNumberOne][current.getUpperWidth() + 1] != null) {
                    if (mazeArray[randomNumberOne][current.getUpperWidth() + 1].getPieceColor().equals(Color.white)) {
                        invalidUpper = randomNumberOne;
                        randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;
                    }
                }
                if (mazeArray[randomNumberOne][current.getLowerWidth() - 1] != null) {
                    if (mazeArray[randomNumberOne][current.getLowerWidth() - 1].getPieceColor().equals(Color.white)) {
                        invalidLower = randomNumberOne;
                        randomNumberOne = rand.nextInt(current.getUpperHieght() - current.getLowerHieght() - 1) + current.getLowerHieght() + 1;
                    }
                }

                if (randomNumberOne != invalidLower && randomNumberOne != invalidUpper) {
                    validMove = true;
                    break;
                }
                
                System.out.println(current.getUpperHieght() - current.getLowerHieght() - 1);

                if (current.getHieght() <= 3) {
                    return;
                }

            }

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

    private void recursiveDivisonMaze() {

        Subdivision start = new Subdivision();

        start.setLowerWidth(1);
        start.setUpperWidth(49);

        start.setWidth(49);

        start.setLowerHieght(1);
        start.setUpperHieght(23);

        start.setHieght(23);

        int randomNumberOne = 0;
        int randomNumberTwo = 0;

        int lowerWidth = 1;
        int upperWidth = 49;

        int lowerHight = 1;
        int upperHight = 23;

        int width = upperWidth;
        int height = upperHight;

        generateDivisonMaze(start, randomNumberOne, randomNumberTwo);

    }

}
