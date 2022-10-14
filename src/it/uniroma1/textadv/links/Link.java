package it.uniroma1.textadv.links;

import it.uniroma1.textadv.interfaces.Traversable;

/**
 * Classe che rappresenta un link, ovvero un collegamento tra un punto A ed un punto B.
 * @author 1849661
 *
 */
public class Link implements Traversable{

	private String name;
	private String node1;
	private String node2;
	
	/**
	 * <code>Costruttore</code> di un <code>link</code>
	 * @param information <code>Array</code> che contiene le informazioni sugli attributi del <code>link</code>.
	 */
	public Link(String[] information) {
		name = information[0];
		node1 = information[2];
		node2 = information[3];
	}
	
	/**
	 * <code>Costruttore</code> di un <code>link</code>.
	 * @param name Nome del <code>link</code>.
	 * @param node1 Stanza collegata dal <code>link</code>.
	 * @param node2 Stanza collegata dal <code>link</code>.
	 */
	public Link(String name, String node1, String node2) {
		this.name = name;
		this.node1 = node1;
		this.node2 = node2;
	}
	
	/**
	 * Permette di ottenere in quale stanza si arriverà in base alla stanza in cui si è attualmente.
	 * @param currentRoom <code>Stanza</code> in cui ci si trova attualmente (sotto forma di <code>stringa</code>).
	 * @return La <code>stringa</code> relativa alla stanza nella quale si arriverà.
	 */
	public String getDestination(String currentRoom) { return currentRoom.equals(node1) ? node2 : node1 ;}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() { return name; }
}
