package scope;

import symbol.Symbol;

public interface Scope {
	public String getScopeName();		//do i have a name?
	public Scope getEnclosingScope();	//am i nested in another?
	public void define(Symbol sym);		//define sym in this scope
	public Symbol resolve(String name);	//loop up name in scope
}
