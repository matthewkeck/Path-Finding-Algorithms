/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaresume;

/**
 *
 * @author matth
 */
public class PathStack {

    private int maxSize = -1;
    private Node[] stackArray;
    private int top = -1;

    public PathStack(int maxSize) {

        this.maxSize = maxSize;
        stackArray = new Node[maxSize];

    }

    public int getTop() {
        return top;
    }
    

    // checks to see if the function is full
    public boolean isFull() {

        return (top == maxSize - 1);
    }

    // checks to see if the function is empty
    public boolean isEmpty() {

        return (top == -1);
    }

    // allous the user to add a card to the stack
    public void push(Node newNode) {

        if (isFull()) {
            return;
        }

        top = top + 1;
        stackArray[top] = newNode;

    }

    // allows the user to move an already created 
    public void move(Node temp) {

        if (isFull()) {
            return;
        }

        top = top + 1;
        stackArray[top] = temp;
    }

    // allows the user to remove a card from the stack
    public Node pop() {

        Node deltNode = new Node();
        if (!isEmpty()) {

            deltNode = stackArray[top];
            stackArray[top] = null;
            top = top - 1;

            return deltNode;

        }
        
        return null;

    }
}
