import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import PseudoLexer.PseudoLexer;

public class Main {
	private static final String FILENAME = "../PsudoCodeLexer/pesudo";
	public static void main(String[] args) throws IOException {
		int i;
		PseudoLexer pseuLexer = new PseudoLexer();
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		String pseuCode;
		ArrayList<PseudoLexer.Token> pseuArray;
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
		pseuLexer.Pseudolexer(pseuCode);
		pseuArray = pseuLexer.tokens;
		for(i = 0; i < pseuArray.size(); i++) {
			System.out.println(pseuArray.get(i));
		}
	}

}
