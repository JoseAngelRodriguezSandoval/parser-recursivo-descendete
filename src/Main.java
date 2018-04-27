import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pseudoLexer.PseudoLexer;
import pseudoParser.PseudoParser;
import token.Token;
import writer.Writer;

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
		//pseuParser.si();
		//pseuParser.mientras();
	}

}
