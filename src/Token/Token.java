package Token;
import PseudoLexer.PseudoLexer.TokenType;

public class Token {
	public TokenType token;
	public String data;

	// 	Constructor
	public Token(TokenType type, String data) {
		this.token = type; // igualacion de valores
		this.data = data; // igualacion de valores
	}
	// Overriding toString function
	@Override
	public String toString() {
		return String.format("(%s \"%s\")", token.name(), data);
	}
}
	



