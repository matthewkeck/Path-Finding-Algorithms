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

// subdivion class is used by the recursive divison maze generation algorithm to keep track of the hight width upper and lower bouncs of each subdivion
// of the original array
public class Subdivision {
    
    // varables for the subdivion class
    int upperWidth;
    int lowerWidth;
    
    int upperHieght;
    int lowerHieght;
    
    int hieght;
    int width;
    
    int invalidMove;

    // empty constructior because we dont need info to instatncate
    public Subdivision() {
    }

    // getters and setters for the subdivion class
    public int getHieght() {
        return hieght;
    }

    public void setHieght(int hieght) {
        this.hieght = hieght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getUpperWidth() {
        return upperWidth;
    }

    public void setUpperWidth(int upperWidth) {
        this.upperWidth = upperWidth;
    }

    public int getLowerWidth() {
        return lowerWidth;
    }

    public void setLowerWidth(int lowerWidth) {
        this.lowerWidth = lowerWidth;
    }

    public int getUpperHieght() {
        return upperHieght;
    }

    public void setUpperHieght(int upperHieght) {
        this.upperHieght = upperHieght;
    }

    public int getLowerHieght() {
        return lowerHieght;
    }

    public void setLowerHieght(int lowerHieght) {
        this.lowerHieght = lowerHieght;
    }

    public int getInvalidMove() {
        return invalidMove;
    }

    public void setInvalidMove(int invalidMove) {
        this.invalidMove = invalidMove;
    }
    
    
    
}
