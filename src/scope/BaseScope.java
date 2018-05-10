package scope;

import java.util.HashMap;
import java.util.Map;

import symbol.Symbol;

public class BaseScope implements Scope {
	Scope enclosingScope;
    Map<String, Symbol> symbols = new HashMap<String, Symbol>();
    public BaseScope(Scope enclosingScope) {
    	this.enclosingScope = enclosingScope;
    }
    public Scope getEnclosingScope() { return enclosingScope; }
    public void define(Symbol sym) { symbols.put(sym.name, sym); }
    public Symbol resolve(String name) { 
    	Symbol s = symbols.get(name);
    	if( s!= null) return s;
    	//	Verficar si hay un scope de cerrado
    	if( enclosingScope != null ) return enclosingScope.resolve(name);
    	return null; //No se encontro
    }
    public String toString() { return getScopeName()+":"+symbols; }
	public String getScopeName() { return null; }
}
