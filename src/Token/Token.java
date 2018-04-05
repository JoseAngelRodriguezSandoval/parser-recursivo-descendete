package Token;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import ListLexer.ListLexer;
import PseudoLexer.PseudoLexer.TokenType;

public class Token {
	public int type;
	String text;

	// 	Constructor
	public Token(int type, String data) {
		this.type = type; // igualacion de valores
		this.text = data; // igualacion de valores
	}
	//	Returns tokenNames types
	public String toString() {
		String tname = ListLexer.tokenNames[type];
		return "<" + text +", " + tname + ">";
	}
}
	



