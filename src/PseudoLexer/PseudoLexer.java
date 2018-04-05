package PseudoLexer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PseudoLexer {
	public ArrayList<Token> tokens = new ArrayList<Token>();

	public enum TokenType {
		// Can't use hyphen in name
		NUMERO("-?[0-9]+(\\.([0-9]+))?"),
		CADENA("\".*\""),
		OPERACIONAL("<|>|==|<=|>=|!="),
		IGUAL("="),
		INICIOPROGRAMA("inicio-programa"),
		FINPROGRAMA("fin-programa"),
		SI("si"),
		ENTONCES("entonces"),
		FINSI("fin-si"),
		MIENTRAS("mientras"),
		FINMIENTRAS("fin-mientras"),
		LEER("leer"),
		ESCRIBIR("escribir"),
		COMA(","),
		PARENTESISQ("\\("),
		PARENTESISDER("\\)"),
		ESPACIOS("[\t\f\r\n]+"),
		VARIABLE("[a-zA-Z][a-zA-Z0-9]*"),
		ERROR(".+");
		
		public final String pattern;
		
		private TokenType(String pattern) {
			this.pattern = pattern;
		}
	}
	
	public class Token {
		public TokenType type;
		public String data;
		//	Constructor
		public Token(TokenType type, String data) {
			this.type = type;
			this.data = data;
		}
		// Overriding toString function
		@Override
		public String toString() {
			return String.format("(%s \"%s\")", type.name(), data);
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

	public void Pseudolexer(String input) {
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
