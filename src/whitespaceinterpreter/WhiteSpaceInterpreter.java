package whitespaceinterpreter;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static whitespaceinterpreter.IE.IE;

/**
 *
 * @author faurehu
 */
public class WhiteSpaceInterpreter {

    private static int pc = 0;
    private static Stack st = new Stack();
    
    public static void main (String[] args) {
        
        System.out.println("\t\tWelcome to the White Space Interpreter\nAfter every "
                + "instruction the whole program, the stack status and the program"
                + " counter are printed.\n\tIn the program: \n\t\t1 stands for \" \" (SPACE)\n\t\t2 stands for \"   \" (TAB)\n\t\t3 stands for a new line.\n\n");
        
        try{        
            
        String s = readFile("evennumbers.ws");
        
        ArrayList<Integer> program = whiteInterpret(s);
        
        runProgram(program);

        } catch(IOException e) {
            
        System.out.println("The file cannot be found.");
        System.exit(1);
            
        }
        
        System.out.println("The program has been interpreted. Now exiting.");
       
    } 
    
    /* Main to experiment with IE.java
    public static void main(String[] args) { Baby baby = new Baby("Foo"); boolean yayOrNay = true; IE("baby?sayName:cry",yayOrNay);};
    */
    
    public static String readFile(String s) throws IOException {
        
        InputStream is = null;
        DataInputStream dis = null;
        String file = "";
        
        try {
            
            is = new FileInputStream(s);
            
            dis = new DataInputStream(is);
            
            int length = dis.available();
            
            byte[] buf = new byte[length];
            
            dis.readFully(buf);
            
            for(byte b: buf) { 
                char c = (char)b;
                file += c;
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        if(is!=null) {
            
            try {
                
                is.close();
                
            } catch (Exception IOException) {
                
                
                
            } 
            
        }
        
        if(dis!=null) {
            
            try {
                
                dis.close();
                
            } catch (Exception IOException) {
                
                
                
            }
            
        }
        
        
        return file;        
        
        
    }
    
    public static ArrayList<Integer> whiteInterpret(String s) {
        
        ArrayList<Integer> program = new ArrayList<Integer>();
        
        Scanner sc = new Scanner(s);
        
        while(sc.hasNextLine()) {
            
            String t = sc.nextLine();
            
            //System.out.println("New line of length: " + t.length() + ".");
            
            for(int i = 0; i < t.length(); i++) {
                
                if(t.charAt(i) == ' ') {
                    
                    //System.out.println("Found space, adding 1.");
                    program.add(1);
                    
                } else if (t.substring(i,i+1).equals("\t")) {
                    
                    //System.out.println("Found tab, adding 2.");
                    program.add(2);
                    
                }
                
            }
            
            //System.out.println("End of line, adding 3.");
            program.add(3);
            
        }
        
        //System.out.println("End of program, removing last number 3.");
        program.remove(program.size()-1);
        
        return program;
        
    }
    
    public static void runProgram(ArrayList<Integer> program) {
        
        ArrayList<Integer> buffer;
        ArrayList<Integer> number;
        int endIndex = 0;
        
        Map label = new HashMap();
        boolean preprocessing = true;
        
        for(int pass = 0; pass < 2; pass++) {
        
        loop: while(pc < program.size()) {
            
            if(preprocessing != true) {
            System.out.println("New instruction.");
            System.out.println("Program: " + program);
            System.out.println("Program counter: " + pc);
            st.printStack();
            }
            
            switch(program.get(pc)) {
                
                case 1:
                    if(preprocessing != true) {
                    System.out.println("Entering stack manipulation.");
                    }
                    switch(program.get(pc+1)) {
                        case 1:
                            if(preprocessing != true) {
                            System.out.println("Pushing a number onto the stack.");
                            }
                            switch(program.get(pc+2)) {
                                case 1:
                                    if(preprocessing != true) {
                                    System.out.println("Number will be positive.");
                                    }
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    if(preprocessing != true) {
                                    st.addLast(programToDecimal(number));
                                    }
                                    pc = pc + number.size() + 4;
                                break;
                                case 2:
                                    if(preprocessing != true) {
                                    System.out.println("Number will be negative");
                                    }
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    if(preprocessing != true) {
                                    st.addLast(programToDecimal(number)*-1);
                                    }
                                    pc = pc + number.size() + 4;
                                break; 
                            }
                        break;
                        case 2:
                            switch(program.get(pc+2)) {
                                case 2:
                                    switch(program.get(pc+3)) {
                                        case 1:
                                            buffer = new ArrayList<Integer>(program.subList(pc+4, program.size()));
                                            endIndex = buffer.indexOf(3);
                                            number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                            int j = programToDecimal(number);
                                            System.out.println("Copying the number " + j + " on top of the stack.");
                                            st.addLast(st.getNumber(j));
                                            break;
                                    }
                                break;    
                            }
                            break;
                        case 3:
                            switch(program.get(pc+2)) {
                                case 1:
                                    if(preprocessing != true) {
                                    System.out.println("Duplicating the number on top of the stack.");
                                    st.duplicateTop();
                                    }
                                    pc = pc+3;
                                    break;
                                case 2:
                                    if(preprocessing != true) {
                                        System.out.println("Swapping the two numbers on top of the stack.");
                                        st.swap();
                                    }
                                    pc = pc+3;
                                break;
                                case 3:
                                    if(preprocessing != true) {
                                        System.out.println("Discarding number on top of the stack.");
                                        st.removeLast();
                                    }
                                pc = pc +3;
                                break;
                            }
                        break;
                    }
                break;
                                      
                case 2:
                    switch(program.get(pc+1)) {
                        case 1:
                            if(preprocessing != true) {
                            System.out.println("Entering arithmetic operation.");
                            }
                            switch(program.get(pc+2)) {
                                case 1:
                                    switch(program.get(pc+3)) {
                                        case 1:
                                            if(preprocessing != true) {
                                            System.out.println("Adding the last two numbers in the stack.");
                                            st.addStack();
                                            }
                                            pc = pc+4;
                                            break;
                                        case 2:
                                            if(preprocessing != true) {
                                            System.out.println("Substracting the last two numbers in the stack.");
                                            st.subtractStack();
                                            }
                                            pc = pc+4;
                                            break;
                                        case 3:
                                            if(preprocessing != true) {
                                            System.out.println("Multiplying the last two numbers in the stack.");
                                            st.multiplyStack();
                                            }
                                            pc = pc+4;
                                            break;
                                            }
                                break;
                                case 2:
                                    switch(program.get(pc+3)) {
                                        case 1:
                                            if(preprocessing != true) {
                                            System.out.println("Dividing the last two numbers in the stack.");
                                            st.divideStack();
                                            }
                                            pc = pc+4;
                                            break;
                                        case 2:
                                            if(preprocessing != true) {
                                            System.out.println("Calculating the modulo of the last two numbers in the stack.");
                                            st.modulusStack();
                                            }
                                            pc = pc+4;
                                            break;
                                    }
                                break;
                            }
                        break;
                        case 2:
                            break;
                        case 3:
                            if(preprocessing != true) {
                            System.out.println("Entering I/O.");
                            }
                            switch(program.get(pc+2)) {
                                case 1:
                                    switch(program.get(pc+3)) {
                                        case 1:
                                            if(preprocessing != true) {
                                        System.out.println("Printing the last character in the stack as ASCII.");
                                        st.printLastASCII();
                                            }
                                        pc = pc+4;
                                        break;
                                        case 2:
                                            if(preprocessing != true) {
                                        System.out.println("Printing the last number in the stack.");
                                        st.printLastNum();
                                            }
                                        pc = pc+4;
                                        break;
                                    }
                                break;
                            }
                        break;
                    }
                break;
                    
                case 3:
                    if(preprocessing != true) {
                    System.out.println("Reading flow-control instruction.");
                    }
                    switch(program.get(pc+1)) {
                        case 1:
                            switch(program.get(pc+2)){
                                case 1:
                                    if(preprocessing != true) {
                                    System.out.println("Marking this location in the program.");
                                    }
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    pc = pc + number.size() + 4;
                                    label.put(number,pc);
                                break;
                                case 3:
                                    if(preprocessing != true) {
                                    System.out.println("Jumping to a label");
                                    }
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    try {
                                        if(preprocessing != true) {
                                        pc = (int) label.get(number);
                                        break;
                                        } else if (preprocessing == true) {
                                        pc = pc+ number.size() + 4;
                                        }
                                    } catch (NullPointerException e) {
                                        System.out.println("This label does not exist. Exiting the program.");
                                        System.exit(1);
                                    }
                                break;
                            }
                        break;
                        case 2:
                            switch(program.get(pc+2)) {
                                case 1:
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    if(preprocessing != true) {
                                    if(st.peek() == 0) {
                                        System.out.println("Last number in the stack is 0. Jumping to the label.");
                                        pc = (int) label.get(number);
                                        st.removeLast();
                                        break;
                                    }
                                    System.out.println("Last number in the stack is not 0. Skipping the label.");
                                    }
                                    pc = pc + number.size() + 4;
                                break;
                                case 2:
                                    buffer = new ArrayList<Integer>(program.subList(pc+3, program.size()));
                                    endIndex = buffer.indexOf(3);
                                    number = new ArrayList<Integer>(buffer.subList(0,endIndex));
                                    if(preprocessing != true) {
                                    if(st.peek() < 0) {
                                        System.out.println("Last number in the stack is negative. Jumping to the label.");
                                        pc = (int) label.get(number);
                                        st.removeLast();
                                        break;
                                    }
                                    System.out.println("Last number in the stack is not negative. Skipping the label.");
                                    }
                                    pc = pc + number.size() + 4;
                            }
                        break;
                        case 3:
                            switch(pc+2) {
                                case 3:
                                    System.out.println("Command to exit the program. Exiting.");
                                    System.exit(-1);
                                    break;
                            }
                        break;
                    }
                    break;
                
            }
            if(preprocessing != true) {
            System.out.print("\n");
            }
        }
        
        System.out.println("The following labels have been processed: " + label.entrySet().toString());
        System.out.print("\n");
        preprocessing = false;
        pc = 0;
        
        }
        
    }
    
    public static int programToDecimal(ArrayList<Integer> number) {
        
        return toDecimal(toBinary(number));
        
    }
    
    public static int toDecimal(String s) {

        String w = turn(s);
        int sum = 0;
        
        for(int i = 0; i < w.length(); i++) {
            
            if(w.charAt(i) == '1') {
                
                double d = Math.pow(2.0,i);
                sum += d;
                
            }
            
        }
        
        return sum;
        
    }
    
    public static String turn(String s) {
        
        char[] a = s.toCharArray();
        ArrayList x = new ArrayList();
        
        for(char y: a) {
            
            x.add(y);
            
        }
        
        Collections.reverse(x);
        
        Iterator it = x.iterator();
        
        String reversed = "";
        
        while(it.hasNext()) {
        
            reversed += it.next();
        
        }
        
        return reversed;
        
    }
    
    public static String toBinary(ArrayList<Integer> number) {
      
        String binary = "";
        
        for(Integer i: number) {
            
            if(i == 1) {
                
                binary += 0;
                
            } else if (i == 2) {
                
                binary += 1;
                
            } else if (i > 2 || i < 1) {
                
                System.out.println("Your number is misrepresented. Exiting program.");
                System.exit(1);
                
            }
            
        } 
        
        return binary;
        
    }
    
    public static String cleanComments(String s) {
        
        return "";
        
    }
    
}
