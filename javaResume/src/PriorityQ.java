
import javaresume.Node;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matth
 */
public class PriorityQ {

    Node first;
    Node last;

    public PriorityQ() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);

    }
    
    public boolean contains(Node searchNode) {
        Node current = first;

        while (current != null) {
            if(current == searchNode){
                return true;
            }
            current = current.next;
            
        }
        
        return false;
    }
    
    public void add(Node searchNode){
        Node current = first;
        if(isEmpty()){
            first.next = searchNode;
        }
        while(current != null){
            if(current.getfCost() > searchNode.getfCost()){
                current.next = searchNode;
                searchNode.next = last;
            }
            current = current.next;
        }
    }

}
