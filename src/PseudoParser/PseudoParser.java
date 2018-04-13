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
		System.out.println("inicializacion de loop PROGRAMA");
		enunciados();
		System.out.println("Salida de loop PROGRAMA");
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
		System.out.println("Inicialacion de loop SI ENTONCES");
		enunciados();
		System.out.println("Salida de loop fin SI ENTONCES");
		match("FINSI");
	}
	
	public void enunciados() {
		System.out.println("Entered Enunciados");
		while(!input.tokens.get(input.i).token.name().equals("FINSI") && !input.tokens.get(input.i).token.name().equals("FINMIENTRAS") && !input.tokens.get(input.i).token.name().equals("FINMIENTRAS")) {
			if(input.tokens.get(input.i).token.name().equals("FINPROGRAMA")) {
				System.out.println("SHIT");
				break;
			}
			enunciado();
		}
		System.out.println("Exited loop");
	}
	
	public void mientras() {
		match("MIENTRAS");
		comparacion();
		System.out.println("Inicialacion de loop fin mientras");
		enunciados();
		System.out.println("Salida de loop fin mientras");
		match("FINMIENTRAS");
	}
	
	public void asignacion() {
		match("VARIABLE");
		match("IGUAL");
		operacion();
	}
	
	public void enunciado() {
		switch(input.tokens.get(input.i).token.name()) {
			case "LEER":
				leer();
				break;
			case "ESCRIBIR":
				escribir();
				break;
			case "SI":
				si();
				break;
			case "MIENTRAS":
				mientras();
				break;
			case "VARIABLE" :
				asignacion();
				break;
		}
		
	}
	
}
