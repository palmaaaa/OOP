package it.uniroma1.textadv.links;

/**
 * Classe che rappresenta un bus.
 * @author 1849661
 *
 */
public class Bus extends Link{
	
	/**
	 * <code>Costruttore</code> di un <code>Bus</code>.
	 * @param name Nome del <code>Bus</code>.
	 * @param node1 <code>Stanza</code> collegata al <code>link</code>.
	 * @param node2 <code>Stanza</code> collegata al <code>link</code>.
	 */
	public Bus(String name, String node1, String node2) {
		super(name, node1, node2);
	}
	
	/**
	 * <code>Costruttore</code> di un <code>Bus</code>.
	 * @param information <code>Array</code> di stringhe che contiene le informazioni del <code>Bus</code>.
	 */
	public Bus(String[] information) {
		super(information);
	}

}
