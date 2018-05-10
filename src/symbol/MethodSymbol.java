package symbol;

import java.util.HashMap;
import java.util.Map;

import scope.Scope;

public class MethodSymbol extends Symbol implements Scope {
	Scope enclosingScope;
	Map<String, Symbol> members = new HashMap<String, Symbol>();
	public MethodSymbol(String name, Type type, Scope scope) {
		super(name, type);
		this.enclosingScope = scope;
	}
	public Symbol resolve(String name) {
		Symbol s = members.get(name);
		if(s != null) return s;
		if(enclosingScope != null)
			return enclosingScope.resolve(name);
		
		return null;
	}
	public String getScopeName() {return name;}
	public Scope getEnclosingScope() { return null; }
	public void define(Symbol sym) { 
		members.put(sym.name, sym);
	}
    public String toString() { return getScopeName()+":"+members; }
}
