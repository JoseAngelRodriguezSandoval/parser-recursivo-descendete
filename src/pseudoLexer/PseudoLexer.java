package pseudoLexer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lexer.Lexer;
import token.Token;

public class PseudoLexer extends Lexer {
	public int i = -1;
	
	public PseudoLexer(String input) {
		super(input);
		pslexer(input);
	}

	public ArrayList<Token> tokens = new ArrayList<Token>();

	public enum TokenType {
		// Can't use hyphen in name
		NUMERO("-?[0-9]+(\\.([0-9]+))?"), 	//0
		CADENA("\".*\""),					//1
		OPERACIONAL("==|<=|>=|<|>|!="),		//2
		IGUAL("="),							//3
		INICIOPROGRAMA("inicio-programa"),	//4
		FINPROGRAMA("fin-programa"),		//5
		SI("si"),							//6
		ENTONCES("entonces"),				//7
		FINSI("fin-si"),					//8
		MIENTRAS("mientras"),				//9
		FINMIENTRAS("fin-mientras"),		//10
		LEER("leer"),						//11
		ESCRIBIR("escribir"),				//12
		COMA(","),							//13
		PARENTESISQ("\\("),					//14
		PARENTESISDER("\\)"),				//15
		ESPACIOS("[ \t\f\r\n]+"),			//16
		TIPO("entero|flotante"),			//17
		VARIABLE("[a-zA-Z][a-zA-Z0-9]*"),	//18
		OPARITMETICO("[*|/|+|-]"),			//19
		DOSPUNTOS(":"),						//20
		ERROR(".+");						//21
		
		public final String pattern;
		
		private TokenType(String pattern) {
			this.pattern = pattern;
		}
	}

	public ArrayList<Token> lex(String input) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		
		for(TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
	
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1))); //	Se elimina primer separador de la cadena ya que no es necesario.
		
		Matcher matcher = tokenPatterns.matcher(input);
	
		while(matcher.find()) {
			for(TokenType t: TokenType.values()) {
				if(matcher.group(TokenType.ESPACIOS.name()) != null)
					continue;
				else if(matcher.group(t.name()) != null) {
					tokens.add(new Token(t, matcher.group(t.name())));
					break;
				}
			}
		}
		
		return tokens;
	}

	public Token nextToken() {
		if(i <= tokens.size())
			i++;
		return tokens.get(i);
	}

	public void pslexer(String input) {
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for(TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));

		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
		Matcher matcher = tokenPatterns.matcher(input);
		while(matcher.find()) {
			for(TokenType t: TokenType.values()) {
				if(matcher.group(TokenType.ESPACIOS.name()) != null)
					continue;
				else if(matcher.group(t.name()) != null) {
					tokens.add(new Token(t, matcher.group(t.name())));
					break;	
				}
			}
		}
	}

}
