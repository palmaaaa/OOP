package it.uniroma1.textadv.links;

import it.uniroma1.textadv.interfaces.Openable;

/**
 * Classe che rappresenta un Link apribile. Il link può essere bloccato da un oggetto o da un ostacolo.
 * @author 1849661
 *
 */
public abstract class OpenableLink extends it.uniroma1.textadv.links.Link implements Openable{
	
	private boolean open;
	private String blockedBy;


	/**
	 * <code>Costruttore</code> di un <code>OpenableObject</code>.
	 * @param name Nome dell' <code>OpenableObject</code>.
	 * @param node1 <code>Stanza</code> collegata al <code>link</code>.
	 * @param node2 <code>Stanza</code> collegata al <code>link</code>.
	 */
	public OpenableLink(String name, String node1, String node2) {
		super(name, node1, node2);
	}
	
	/**
	 * <code>Costruttore</code> di un <code>OpenableObject</code>.
	 * @param information <code>Array</code> di stringhe che contiene le informazioni dell'<code>OpenableObject</code>.
	 */
	public OpenableLink(String[] information) {
		super(information);
	}

	@Override
	public void unlock() { this.open = true; }
	
	/**
	 * Imposta quale oggetto (sotto forma di <code>stringa</code>) blocchi il <code>link</code>.
	 * @param blockedBy Oggetto che blocca il <code>link</code>.
	 */
	public void setBlockedBy(String blockedBy) { this.blockedBy = blockedBy; }
	
	/**
	 * Restituisce l'oggetto (sotto forma di stringa) che blocca il <code>link</code>.
	 * @return Stringa relativa all'oggetto che blocca il <code>link</code>.
	 */
	public String getBlockingObject() { return blockedBy; }
	
	/**
	 * Rimuove l'oggetto che blocca il <code>link</code>.
	 */
	public void removeBlockingObject() { setBlockedBy(null); }
	
	@Override
	public boolean isOpen() { return open; }

}
