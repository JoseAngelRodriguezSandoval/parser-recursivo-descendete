package pseudoParser;

import parser.Parser;
import pseudoLexer.PseudoLexer;
import symbol.BuiltInTypeSymbol;
import symbol.SymbolTable;
import symbol.VariableSymbol;
import writer.Writer;

import java.util.ArrayList;
import java.io.IOException;

public class PseudoParser extends Parser {
	PseudoLexer input;
	Writer writer = new Writer("../parser-recursivo-descendete/prog.c");
	SymbolTable symbolTable = new SymbolTable();
	VariableSymbol symbolVar;
	String tmpName;
    ArrayList<String> arr = new ArrayList<>();
	public PseudoParser(PseudoLexer in) {
		super(in);
		input = in;
	}
	
	public void programa() throws IOException {
		//System.out.println("Index en programa:" + input.i);
		writer.createNewFile();
		match("INICIOPROGRAMA");
		writer.append("#include <stdio.h>", true);
		writer.append("#include <stdlib.h>\n", true);
		writer.append("int main() {", true);
		declaraciones();
		enunciados();
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

	public void listaVariables() {
		arr.add(input.tokens.get(input.i).data);
		match("VARIABLE");
		if(input.tokens.get(input.i).token.name().equals("COMA")) {
			match("COMA");
			listaVariables();
		}
	}

	public void declaraciones() {
		listaVariables();
		match("DOSPUNTOS");
		String type = input.tokens.get(input.i).data;
		switch(type) {
			case "entero":
					defineWrapper(type);
				break;
			case "flotante":
					defineWrapper(type);
			default:
				System.out.println("TIPO de valor inexistente");
		}
		System.out.println(symbolTable);		
		match("TIPO");
		if(input.tokens.get(input.i).token.toString().equals("VARIABLE")) {
			declaraciones();
		}
	}
	
	public void defineWrapper(String type) { 
		for(int i = 0; i < arr.size(); i++) {
			 symbolVar = new VariableSymbol(arr.get(i), new BuiltInTypeSymbol(type));
			 symbolTable.define(symbolVar);
		}
		arr.clear();
	}

	
	public void comparacion() throws IOException {
		valor();
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
		enunciados();
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
		enunciados();
		writer.append("\n\t}", true);
		match("FINMIENTRAS");
	}
	
	public void asignacion() throws IOException {
		writer.append("\t\tint " + input.tokens.get(input.i).data + " ", false);
		verificador();
		match("VARIABLE");
		writer.append(" " + input.tokens.get(input.i).data + " ", false);
		match("IGUAL");
		//operacion();
		if(input.tokens.get(input.i).token.name() == "NUMERO") {
			writer.append(" " + input.tokens.get(input.i).data, false);
			match("NUMERO");
		} else if(input.tokens.get(input.i).token.name() == "VARIABLE") {
			operacion();
		}
		writer.append(";", true);
	}
	
	public void verificador() {
		if(symbolTable.resolve(lookahead.data) == null)
			throw new Error("Variable no declarada: " + lookahead.data);
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
	
	public void debugMarker() {
		System.out.print("Debugging here --->");
	}
}
