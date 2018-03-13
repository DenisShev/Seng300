// Main method is used to test
// TODO: Find Declaration types in visit method within compilation unit
// This version currently finds the declaration VARIABLES, not types.

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
 
public class Counter{
	public static void parse(char[] str) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cu.accept(new ASTVisitor() {
			
			// might need to use a set to count references?
			Set names = new HashSet();
 
			// Checks for Classes/interface declarations
			public boolean visit(TypeDeclaration node) {
				SimpleName name = node.getName();
				System.out.println("Classes/interface: " + name);
				return true;
			}
			// Checks for non-primitive type declarations
			public boolean visit(SimpleType node) {
				Name varType = node.getName();
				System.out.println("Type: " + varType);
				return false;
			}
			
		});
	}
 
	public static void main(String[] args) throws IOException {
		char[] test = "@test\n public class A { char k; \nint i = 9;  \n int j;String d; \n ArrayList<Integer> al = new ArrayList<Integer>(); j=1000;} enum C{Y, N};interface D{}".toCharArray();
		parse(test);
	}
}
