import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pseudoLexer.PseudoLexer;
import pseudoParser.PseudoParser;
import scope.GlobalScope;
import scope.LocalScope;
import scope.Scope;
import token.Token;
import writer.Writer;
import symbol.BuiltInTypeSymbol;
import symbol.MethodSymbol;
import symbol.Symbol;
import symbol.Type;
import symbol.VariableSymbol;
/*
 *What to do ? Esto va en una clase prueba en el main
 *	scope currentScope;
 *	currentScope = new GlobalScope();
 *	p.push(currentScope);
 *	currentScope.def("int");
 *				.def("float");		
 *				.def("void");
 *
 *	x = currentScope.resolve("int"); tipo de token
 *	currentScope.def(new VariableSymbol("i"));
 *	y = currentScope.resolve("float);
 *
 *	aux = new MethodSymbol("f");
 *	currentScope.def(aux);
 *	currentScope = aux;
 *	p.push(currentScope);
 *
 *	currentScope = new MethodSymbol("f", currentScope);
 *	
 * 	currentScope = new LocalScope("f", currentScope);
 *	
 *	currentScope = currentScope.getEnclosingScope();

 * */
public class Main {
	private static final String FILENAME = "./pesudo";
	public static void main(String[] args) throws IOException {
		Scope currentScope;
		//Definimos las tipoas de variables
		Type entero = new BuiltInTypeSymbol("entero");
		Type flotante = new BuiltInTypeSymbol("entero");
		Type Void = new BuiltInTypeSymbol("entero");
		//Las declaramos dentro del scope global
		currentScope = new GlobalScope();
		currentScope.define(new BuiltInTypeSymbol("entero"));
		currentScope.define(new BuiltInTypeSymbol("flotante"));
		currentScope.define(new BuiltInTypeSymbol("void"));
		//Verifica que el tipo de variable este en la tabla
		currentScope.resolve("entero");
		currentScope.resolve("flotante");
		currentScope.resolve("void");
		//Definimos la variable y su tipo
		currentScope.define(new Symbol("i", entero));
		currentScope.define(new Symbol("j", flotante));
		currentScope.define(new Symbol("k", Void));
		System.out.println(currentScope);
		//Creamos scope de 
		MethodSymbol metodoScope = new MethodSymbol("metodo",entero,currentScope);
		currentScope.define(metodoScope);
		currentScope = metodoScope;
		System.out.println(currentScope);	
		LocalScope local = new LocalScope(currentScope);
		currentScope = local;
		System.out.println(currentScope);
		currentScope = currentScope.getEnclosingScope();
		currentScope = currentScope.getEnclosingScope();
		System.out.println(currentScope);

		/*		
 		BufferedReader br = new BufferedReader(new FileReader(FILENAME));

		String pseuCode;
		PseudoLexer pseuLexer;
		PseudoParser pseuParser;
		//ArrayList<Token> pseuArray;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    pseuCode = sb.toString();
		} finally {
		    br.close();
		}
		pseuLexer = new PseudoLexer(pseuCode);
		pseuParser = new PseudoParser(pseuLexer);		
		
		//for(i = 0; i < pseuLexer.tokens.size(); i++) {
		//	System.out.println(pseuLexer.tokens.get(i));
		//}		
		 pseuParser.programa();
	*/
	}

}
