/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaresume;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author matth
 */
public class Clock extends JComponent {
    Timer myTimer;
    int time = 0;
    int noCount = 0;
    int myPath = 0;

    Date xtime;
    public Clock() {

    }

    public void setMyPath(int myPath) {
        this.myPath = myPath;
    }

    public void setNoCount(int noCount) {
        this.noCount = noCount;
    }
    
    public int getTime() {
        return time;
    }
    
    public void resetTime(){
        time = 0;
        repaint();
    }
    
    public boolean Start(){
        
        // set time to the slider
        myTimer = new Timer(10, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                time++;
                repaint();
                
            }

        });
        // starts the timer
        myTimer.start();
        
        return true;
    }

    public void Stop() {
        myTimer.stop();
        //time = 0;
    }
    
    
    @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
        
        String Timer = "Time : " + time;
        String nodeCounter = "Node Count : " + noCount;
        String pathLength = "Path Length : " + myPath;
        Font myFont = new Font("Lucida Console", Font.BOLD,18);
        g.setColor(Color.white);
        g.setFont(myFont);
        
        g.drawString(Timer,0,50);
        g.drawString(nodeCounter,0,100);
        g.drawString(pathLength,0,150);
    }
}
