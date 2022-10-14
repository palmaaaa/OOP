package it.uniroma1.textadv.links;

/**
 * Classe che rappresenta una porta.
 * @author 1849661
 *
 */
public class Porta extends OpenableLink{
	
	/**
	 * <code>Costruttore</code> di una <code>Porta</code>.
	 * @param name Nome della <code>Porta</code>.
	 * @param node1 <code>Stanza</code> collegata al link.
	 * @param node2 <code>Stanza</code> collegata al link.
	 */
	public Porta(String name, String node1, String node2) {
		super(name,node1,node2);
	}
	
	/**
	 * <code>Costruttore</code> di una <code>Porta</code>.
	 * @param information <code>Array</code> di stringhe che contiene le informazioni della <code>Porta</code>.
	 */
	public Porta(String[] information) {
		super(information);
	}
}
