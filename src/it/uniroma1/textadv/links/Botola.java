package it.uniroma1.textadv.links;

/**
 * Classe che rappresenta una botola.
 * @author 1849661
 *
 */
public class Botola extends OpenableLink{

	/**
	 * <code>Costruttore</code> di una <code>Botola</code>.
	 * @param name Nome della <code>Botola</code>.
	 * @param node1 <code>Stanza</code> collegata al <code>link</code>.
	 * @param node2 <code>Stanza</code> collegata al <code>link</code>.
	 */
	public Botola(String name, String node1, String node2) {
		super(name,node1,node2);
	}
	
	/**
	 * <code>Costruttore</code> di una <code>Botola</code>.
	 * @param information <code>Array</code> di stringhe che contiene le informazioni della <code>Botola</code>.
	 */
	public Botola(String[] information) {
		super(information);
	}

}
