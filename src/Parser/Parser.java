package Parser;
import Lexer.Lexer;
import Token.Token;

public class Parser {
	Lexer input;
	public Token lookahead;
	public Parser(Lexer in) 
	{
		input = in;
		consume();
	}
	public void match(int x) {
		if( lookahead.type == x) consume();
		else throw new Error("Expecting " + input.getTokenName(x) + "; found" + lookahead);
	}
	public void consume() { lookahead = input.nextToken(); }
}
