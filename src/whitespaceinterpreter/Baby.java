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
public class Baby {
    
    private String name;
    
    public Baby(String _name) {
        
        name = _name;
        
    }
    
    public void sayName() {
        
        System.out.println(name);
        
    }
    
    public void cry() {
        
        System.out.println("WAA");
        
    }
    
}
