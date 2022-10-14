package it.uniroma1.textadv.links;

/**
 * Classe che rappresenta un teletrasporto.
 * @author 1849661
 *
 */
public class Teletrasporto extends OpenableLink {

	/**
	 * <code>Costruttore</code> di un <code>Teletrasporto</code>.
	 * @param name Nome del <code>Teletrasporto</code>.
	 * @param node1 <code>Stanza</code> collegata al <code>link</code>.
	 * @param node2 <code>Stanza</code> collegata al <code>link</code>.
	 */
	public Teletrasporto(String name, String node1, String node2) {
		super(name,node1,node2);
	}
	
	/**
	 * <code>Costruttore</code> di un <code>Teletrasporto</code>.
	 * @param information <code>Array</code> di stringhe che contiene le informazioni del <code>Teletrasporto</code>.
	 */
	public Teletrasporto(String[] information) {
		super(information);
	}

}