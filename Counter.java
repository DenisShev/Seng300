// Main method is used to test
// TODO: Declarations: Annotations, field, import, method, package
// TODO: references
// Current version only prints out the outputs into console  

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
 
public class Counter{
	// Used to count declarations
	// Key: String of the type
	// Value: Integer count of the number of declaration of the type
	static HashMap declare = new 	HashMap<String, Integer>();
	public static void parse(char[] str) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setResolveBindings(true);
		parser.setSource(str);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setBindingsRecovery(true);
		
		// setEnvironment and setUnitName is required to run resolveBindings
		parser.setEnvironment(null, null, null, true);
		parser.setUnitName("");
		 
 
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		cu.accept(new ASTVisitor() {			
			// Doesn't work as intended
			// Checks for annotation type declarations 
			public boolean visit(AnnotationTypeDeclaration node) {
				
				SimpleName name = node.getName();
				System.out.println("Annotations" + name);
				return true;
			}
			// Checks for Classes/interface declarations
			public boolean visit(TypeDeclaration node) {
				SimpleName name = node.getName();
				String key = name.toString();
				// adds to the declaration counter
				if(declare.containsKey(key)){
					int value = (int) declare.get(key);
					value++;
					declare.put(key, value);
				}else{
					int value = 1;
					declare.put(key, value);
				}	
				
				return true;
			}
			// Checks every variable declared and increases the declare hash map counter by one according to their respective types
			// Doesn't add primitives to declare hash map
			public boolean visit(VariableDeclarationFragment node) {
				// used to check variables for primitive types
				// primitives are never declared, only referenced from java.lang.(insert primitive type)
				//String primitives [] = { "boolean", "byte", "char", "short", "int", "long", "float", "double" };
				String primitives = "boolean byte char short int long float double";
				IVariableBinding binding = node.resolveBinding();
				String key = binding.getType().getName();
				
				// as long as the variable isn't a primitive type, increase the counter of the declare hash map by 1
				if(!(primitives.contains(key))){
					// adds to the declaration counter
					if(declare.containsKey(key)){
						int value = (int) declare.get(key);
						value++;
						declare.put(key, value);
					}else{
						int value = 1;
						declare.put(key, value);
					}					
				}				
				return false;
			}			
		});
	}
 
	// for debugging purposes
	// prints every key their respective count in the declare hashmap
	public static void printDeclare(){
		Set keys = declare.keySet();
		Iterator iter = keys.iterator();
		while(iter.hasNext()){
			String key = (String) iter.next();
			int value = (int) declare.get(key);
			System.out.println(key + " " + value); // used for debugging
		}
	}
	public static void main(String[] args) throws IOException {
		char[] test = "@test\n public class A { char k; \nint i = 9;  \n int j;String d = \"asd\"; d.toCharArray();\n ArrayList<Integer> al = new ArrayList<Integer>(); j=1000;} enum c{Y, N};interface D{} A a = new A();".toCharArray();
		parse(test);
		printDeclare();
	}
}
