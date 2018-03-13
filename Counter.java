// Main method is used to test
// TODO: Find Declaration types in visit method within compilation unit
// This version currently finds the declaration VARIABLES, not types.

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
 
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
 
public class Counter{
	public static void parse(char[] str) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
			
			// might need to use a set to count references?
			//Set names = new HashSet();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				
				System.out.println(name + ". Declarations found: " );
				return false;
			}
		});
	}
 
	public static void main(String[] args) throws IOException {
		char[] test = "public class A { int i = 9;  \n int j; \n ArrayList<Integer> al = new ArrayList<Integer>(); j=1000; i = 10;}".toCharArray();
		parse(test);
	}
}
