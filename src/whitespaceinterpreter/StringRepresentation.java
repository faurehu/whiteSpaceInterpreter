/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whitespaceinterpreter;

/**
 *
 * @author faurehu
 */
public class StringRepresentation {
    
    public static String[] first = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static String[] dec = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    public static String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static String hundred = "_hundred_and_";
    
    public static String upToNineteen(int i) {
        
        if(i < 10) {
            
            return first[i];
            
        } else if (i%10 == 0) {
            
            return dec[i/10];
            
        } else if (i > 10) {
        
        return teens[i-10];
        
        }
        
        return "Number bigger than nineteen.";
        
    }
    
    public static String upToNinetyNine(int i) {
        
        if (i < 20) {
            
            return upToNineteen(i);
            
        } else if (i%10 == 0 && i < 100) {
            
            return dec[i/10];
        
        } else if (i < 100) {
            
            int num = (int) Math.floor(i/10);
            
            return dec[num] + "_" + first[i%10];
            
        }
        
        return "Number bigger than ninety nine.";
        
    }
    
    public static String upToThreeNines(int i) {
        
        if (i < 100) {
            
            return upToNinetyNine(i);
            
        } else if (i % 100 == 0 && i < 1000) {
            
            return first[i/100] + "_hundred";
            
        } else if (i < 1000) {
            
            int num = (int) Math.floor(i/100);
            
            return first[num] + hundred + upToNinetyNine(i%100);
            
        }
        
        return "Number bigger than nine hundred ninety nine.";
        
    }
    
    public static String upToFourNines(int i) {
        
        int num = (int) Math.floor(i/1000);
        
        if(i < 1000) {
            
            return upToThreeNines(i);
            
        } else if (i%1000 == 0 & i < 10000) {
           
          return first[num] + "_thousand";  
            
        } else if (i < 10000) {
            
            return first[num] + "_thousand_" + upToThreeNines(i%1000);
            
        }
        
        return "Number bigger than nine thousand nine hundred ninety nine.";
        
    }
    
}
