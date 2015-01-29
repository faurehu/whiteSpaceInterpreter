package whitespaceinterpreter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static whitespaceinterpreter.StringRepresentation.upToFourNines;
import java.lang.reflect.Method; 

/**
 *
 * I am trying to make a interpreter method that takes  parameters Object?a:b
 * and boolean. 
 * It executes the following:
 * if(boolean) {
 * Object.a();
 * } else {
 * Object.b();
 * }
 * 
 * @author faurehu
 */
public class IE {
    
    private static String[] buffer;
    private static String methods;
    private static String header;
    private static String object;
    private static String first;
    private static String second;
    /* 
    private static int counter = 1; 
    private static String fileName;
    private static String className;
    */
    
    public static void IE(String s, boolean b) {
        
        buffer = s.split("\\?");
        
        object = buffer[0];
        methods = buffer[1];
        
        buffer = methods.split("\\:");
        
        first = buffer[0];
        second = buffer[1];
        
        /*
        className = upToFourNines(counter);
        fileName = className + ".java";
        
        
        PrintWriter writer;
        
        try {
        
        if(b) {
        writer = new PrintWriter(fileName, "UTF-8");
        writer.println("package whitespaceinterpreter;");
        writer.println("import static whitespaceinterpreter." + object + "." + second + ";");
        writer.println("public class " + className + "{");
        writer.println("\tpublic static void " + className + "() {");
        writer.println("\t\t" + object + "." + first + "();");
        writer.println("\t}");
        writer.println("}");
        writer.close();
        } else {
        writer = new PrintWriter(fileName, "UTF-8");
        writer.println("package whitespaceinterpreter;");
        writer.println("import static whitespaceinterpreter." + object + "." + second + ";");
        writer.println("public class " + className + "{");
        writer.println("\tpublic static void " + className + "() {");
        writer.println("\t\t" + object + "." + second + "();");
        writer.println("\t}");
        writer.println("}");    
        }
        } catch (FileNotFoundException e) {
            
            System.out.println(e);
            System.exit(1);
            
        } catch (UnsupportedEncodingException e) {
            
            System.out.println(e);
            System.exit(1);
            
        }
        */
        
        Class noparams[] = {};
        Class[] paramString = new Class[1];	
	paramString[0] = String.class;
        Class[] paramInt = new Class[1];	
	paramInt[0] = Integer.TYPE;
        Method method;
        Class cls;
        
        try{
            
		cls = Class.forName("whitespaceinterpreter."+object);
		Object obj = cls.newInstance();
                if(b){
		method = cls.getDeclaredMethod(first, noparams);
		method.invoke(obj, null);
                }else{
                method = cls.getDeclaredMethod(first, noparams);
		method.invoke(obj, null);    
                }
 
	}catch(Exception e){
		
            System.out.print(e);
            System.exit(-1);
            
	}
        
    } 
    
}
