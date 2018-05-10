package symbol;

import java.util.HashMap;
import java.util.Map;

import scope.Scope;

public class SymbolTable implements Scope { //single-scope symtab
	Map<String, Symbol> symbols = new HashMap<String, Symbol>();
	public SymbolTable() { initTypeSystem(); }
	protected void initTypeSystem() {
		define(new BuiltInTypeSymbol("entero"));
		define(new BuiltInTypeSymbol("flotante"));
		define(new BuiltInTypeSymbol("void"));
	}
	//Satisfy Scope Interface
	public String getScopeName() { return "global"; }
	public Scope getEnclosingScope() { return null; }
	public void define(Symbol sym) { symbols.put(sym.name, sym); }
	public Symbol resolve(String name) { return symbols.get(name); }
	public String toString() { return getScopeName() + ";" + symbols; }
}
