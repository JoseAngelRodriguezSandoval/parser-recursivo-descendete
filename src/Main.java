import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pseudoLexer.PseudoLexer;
import pseudoParser.PseudoParser;
import token.Token;
import writer.Writer;
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
		/*
		for(i = 0; i < pseuLexer.tokens.size(); i++) {
			System.out.println(pseuLexer.tokens.get(i));
		}
		*/
		pseuParser.programa();
	}

}
