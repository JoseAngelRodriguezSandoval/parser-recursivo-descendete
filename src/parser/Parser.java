package parser;
import pseudoLexer.PseudoLexer;
import token.Token;

public class Parser {
	PseudoLexer input;
	int i = 0;
	public Token lookahead;
	public Parser(PseudoLexer in) {
		input = in;
		consume();
	}
	
	public void match(String x) {
		System.out.println(lookahead);
		if( lookahead.token.name().equals(x)) consume();
		else throw new Error("Error needed " + x +  "; found" + lookahead);
	}
	
	public void pseuMatch(String x) {
		if(lookahead.data.equals(x)) consume();
		else throw new Error("Expecting " + lookahead.data + "; found" + lookahead);

	}
	
	public void consume() { 
		if(i < input.tokens.size()) {
			lookahead = input.nextToken();
			i++;
		}
	}
}
