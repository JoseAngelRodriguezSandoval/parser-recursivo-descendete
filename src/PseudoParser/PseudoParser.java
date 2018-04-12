package PseudoParser;

import PseudoLexer.PseudoLexer;
import Parser.Parser;

public class PseudoParser extends Parser {
	PseudoLexer input;
	public PseudoParser(PseudoLexer in) {
		super(in);
		input = in;
	}
	
	public void programa() {
		//System.out.println("Index en programa:" + input.i);
		match("INICIOPROGRAMA");
		//enunciados();
		//System.out.println("Index en programa:" + input.i);
		match("FINPROGRAMA");
	}
	public void valor() {
		//System.out.println("Index en valor:" + input.i);
		if(input.tokens.get(input.i).token.name() == "NUMERO")
			match("NUMERO");
		else
			match("VARIABLE");
	}

	public void comparacion() {
		valor();
		match("OPERACIONAL");
		valor();
	}

	public void operacion() {
		valor();
		match("OPARITMETICO");
		valor();
	}
	
	public void escribir() {
		match("ESCRIBIR");
		match("CADENA");
		//System.out.print(input.tokens.get(input.i).token.name());
		if(input.tokens.get(input.i).token.name() == "COMA") {
			match("COMA");
			match("VARIABLE");
		}
	}
	
	public void leer() {
		match("LEER");
		match("CADENA");
		if(input.tokens.get(input.i).token.name() == "COMA") {
			match("COMA");
			match("VARIABLE");
		}	
	}
	
	public void si() {
		match("SI");
		comparacion();
		match("ENTONCES");
		enunciados();
		match("FINSI");
	}
	
	public void enunciados() {
		while(!input.tokens.get(input.i).token.name().equals("FINSI")) {
			enunciado();
		}
	}
	
	public void mientras() {
		match("MIENTRAS");
		match("FINMIENTRAS");
	}
	
	public void asignacion() {
		match("VARIABLE");
		match("IGUAL");
		match("OPERACION");
	}
	
	public void enunciado() {
		switch(input.tokens.get(input.i).token.name()) {
			case "LEER":
				leer();
			case "ESCRIBIR":
				escribir();
			case "SI":
				si();
			case "MIENTRAS":
				mientras();
			case "VARIABLE":
				asignacion();
		}
	}
	
}
