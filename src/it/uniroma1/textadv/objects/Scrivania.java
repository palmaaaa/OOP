package it.uniroma1.textadv.objects;

import java.util.Set;

/**
 * Classe che rappresenta un scrivania.
 * @author 1849661
 *
 */
public class Scrivania extends Container {

	/**
	 * <code>Costruttore</code> di una scrivania.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto contenuto nella scrivania.
	 */
	public Scrivania(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di una scrivania.
	 * @param name Nome della scrivania.
	 * @param content Contenuto della scrivania.
	 */
	public Scrivania(String name, Set<String> content) {
		super(name, content);
	}
}
