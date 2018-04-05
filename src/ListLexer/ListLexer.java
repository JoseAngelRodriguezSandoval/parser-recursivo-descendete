package ListLexer;

import Lexer.Lexer;
import Token.Token;

public class ListLexer extends Lexer {
	public static int NAME = 2;
	public static int COMMA = 3;
	public static int LBRACK = 4;
	public static int RBRACK = 5;
	public static String[] tokenNames = {
		"n/a",
		"<EOF>",
		"NAME",
		"COMMA",
		"LBRACK",
		"RBRACK"
	};
	//	Constructor
	public ListLexer(String input) {
		super(input);
	}
	//	returns token name
	public String getTokenName(int x) {
		return tokenNames[x];
	}
	// verify if var c located in LEXER is a letter
	public boolean isLetter() {
		return c >='a' && c <='z' || c>='A'&& c<='Z';
	}
	
	public Token nextToken() {
		while (c != EOF) {
			switch(c) {
			case ' ':
			case '\t':
			case '\n':
			case '\r':
				WS();
				continue;
			case ',':
				consume();
				return new Token(COMMA, ",");
			case '[':
				consume();
				return new Token(LBRACK, "[");
			case ']':
				consume();
				return new Token(RBRACK, "]");
			default:
				if(isLetter()) 
					return NAME();
				throw new Error("invalid character: "+ c);
			}
		}
		return new Token(EOF_TYPE, "<EOF");
	}

	public Token NAME() {
		StringBuilder buf = new StringBuilder();
		do {
			buf.append(c);
			consume();
		} while(isLetter());
		
		return new Token(NAME, buf.toString());
	}
	//checks for whitespace or special characters
	public void WS() {
		while(c == ' ' || c == '\t' || c == '\n' || c == '\r') 
			consume();
	}
}
