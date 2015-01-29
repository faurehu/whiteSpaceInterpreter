package whitespaceinterpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Stack {
    
    private LinkedList<Integer> stack;
    private int[] pair;
    
    public Stack() {
        
        stack = new LinkedList<Integer>();
        
    }
    
    public void addLast(int i) {
        
        stack.addLast(i);
        
    }
    
    public int[] lastTwo() {
        
        pair = new int[2];
        pair[0] = stack.removeLast(); 
        pair[1] = stack.removeLast();
        return pair;
        
    }
    
    public void addStack() {
        
        pair = lastTwo();
        addLast(pair[0]+pair[1]);
        
    }
    
    public void subtractStack() {
        
        pair = lastTwo();
        addLast(pair[1]-pair[0]);
        
    }
    
    public void multiplyStack() {
        
        pair = lastTwo();
        addLast(pair[0]*pair[1]);
        
    }
    
    public void divideStack() {
        
        pair = lastTwo();
        addLast(pair[1]/pair[0]);
        
    }
    
    public void modulusStack() {
        
        pair = lastTwo();
        addLast(pair[1]%pair[0]);
        
    }
    
    public void removeLast() {
        
        stack.removeLast();
        
    }
    
    public void printLastNum() {
        
        System.out.println(stack.getLast());
        stack.removeLast();
        
    }
    
    public void printLastASCII() {
        
        int num = stack.getLast();
        String ASCII = Character.toString((char)num);
        stack.removeLast();
        System.out.println(ASCII);
        
        
    }
    
    public int peek() {
        
        return stack.getLast();
        
    }
    
    public void duplicateTop() {
        
        addLast(stack.getLast());
        
    }
    
    public void printStack() {
        
        System.out.println("Stack: " + stack.toString());
        
    }
    
    public void swap() {
        
        pair = lastTwo();
        addLast(pair[0]);
        addLast(pair[1]);
        
    }
    
    public int getNumber(int i) {
        
        Collections.reverse(stack);
        int j = stack.get(i);
        Collections.reverse(stack);
        return j;
        
    }
    
}
