package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.DestroyerObject;

/**
 * Classe astratta che rappresenta uno strumento. Come ad esempio un martello, un cacciavite etc...
 * @author 1849661
 *
 */
public abstract class Tool extends it.uniroma1.textadv.objects.GameObject implements DestroyerObject{

	/**
	 * <code>Costruttore</code> di uno strumento.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto e
	 * nella seconda la classe specifica dello strumento.
	 */
	public Tool(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di uno strumento.
	 * @param name Nome dello strumento.
	 */
	public Tool(String name) {
		super(name);
	}
}
