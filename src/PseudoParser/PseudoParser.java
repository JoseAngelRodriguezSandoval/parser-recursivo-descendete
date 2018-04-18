package PseudoParser;

import PseudoLexer.PseudoLexer;
import writer.Writer;

import java.io.IOException;

import Parser.Parser;

public class PseudoParser extends Parser {
	PseudoLexer input;
	Writer writer = new Writer("../parser-recursivo-descendete/prog.c");
	public PseudoParser(PseudoLexer in) {
		super(in);
		input = in;
	}
	
	public void programa() throws IOException {
		//System.out.println("Index en programa:" + input.i);
		match("INICIOPROGRAMA");
		writer.append("#include <stdio.h>", true);
		writer.append("#include <stdlib.h>\n", true);
		writer.append("int main() {", true);
		System.out.println("inicializacion de loop PROGRAMA");
		enunciados();
		System.out.println("Salida de loop PROGRAMA");
		//System.out.println("Index en programa:" + input.i);
		match("FINPROGRAMA");
		writer.append("\treturn 0;\n}", false);
		writer.closeWriter();
	}
	
	public void valor() throws IOException {
		//System.out.println("Index en valor:" + input.i);
		if(input.tokens.get(input.i).token.name() == "NUMERO") {
			writer.append(input.tokens.get(input.i).data, false);
			match("NUMERO");
		} else {
			writer.append(input.tokens.get(input.i).data, false);
			match("VARIABLE");
		}
	}

	public void comparacion() throws IOException {
		valor();
		//System.out.println("AAAAAAAAAAAA" + input.tokens.get(input.i).data);
		writer.append(" " + input.tokens.get(input.i).data + " ", false);
		match("OPERACIONAL");
		valor();
	}

	public void operacion() throws IOException {
		valor();
		writer.append(" " + input.tokens.get(input.i).data + " ", false);
		match("OPARITMETICO");
		valor();
	}
	
	public void escribir() throws IOException {
		writer.append("\t\tprintf(", false);
		match("ESCRIBIR");
		writer.append(input.tokens.get(input.i).data, false);
		match("CADENA");
		//System.out.print(input.tokens.get(input.i).token.name());
		if(input.tokens.get(input.i).token.name() == "COMA") {
			writer.append(input.tokens.get(input.i).data, false);
			match("COMA");
			writer.append(" " + input.tokens.get(input.i).data, false);
			match("VARIABLE");
		}
		writer.append(");", true);
	}
	
	public void leer() throws IOException {
		writer.append("\t\tscanf(", false);
		match("LEER");
		writer.append("\"%d\"", false);
		match("CADENA");
		if(input.tokens.get(input.i).token.name() == "COMA") {
			writer.append(input.tokens.get(input.i).data, false);
			match("COMA");
			writer.append(" " + input.tokens.get(input.i).data, false);
			match("VARIABLE");
		}
		writer.append(");", true);
	}
	
	public void si() throws IOException {
		match("SI");
		writer.append("\tif(", false);
		comparacion();
		writer.append("){", true);
		match("ENTONCES");
		System.out.println("Inicialacion de loop SI ENTONCES");
		enunciados();
		System.out.println("Salida de loop fin SI ENTONCES");
		writer.append("\t}", true);
		match("FINSI");
	}
	
	public void enunciados() {
		System.out.println("Entered Enunciados");
		while(!input.tokens.get(input.i).token.name().equals("FINSI") && !input.tokens.get(input.i).token.name().equals("FINMIENTRAS") && !input.tokens.get(input.i).token.name().equals("FINMIENTRAS")) {
			if(input.tokens.get(input.i).token.name().equals("FINPROGRAMA")) {
				break;
			}
			try {
				enunciado();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void mientras() throws IOException {
		writer.append("\twhile(", false);
		match("MIENTRAS");
		comparacion();
		writer.append("){", true);
		System.out.println("Inicialacion de loop fin mientras");
		enunciados();
		System.out.println("Salida de loop fin mientras");
		writer.append("\n\t}", true);
		match("FINMIENTRAS");
	}
	
	public void asignacion() throws IOException {
		writer.append("\t\tint " + input.tokens.get(input.i).data + " ", false);
		match("VARIABLE");
		writer.append(" " + input.tokens.get(input.i).data + " ", false);
		match("IGUAL");
		//operacion();
		System.out.println(input.tokens.get(input.i).data);
		if(input.tokens.get(input.i).token.name() == "NUMERO") {
			writer.append(" " + input.tokens.get(input.i).data, false);
			match("NUMERO");
		} else if(input.tokens.get(input.i).token.name() == "VARIABLE") {
			operacion();
		}
		writer.append(";", true);
	}
	
	public void enunciado() throws IOException {
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
