package it.uniroma1.textadv.objects;

import java.util.Set;

/**
 * Classe che rappresenta un cassetto.
 * @author 1849661
 *
 */
public class Cassetto extends Container{

	/**
	 * <code>Costruttore</code> di un cassetto.
	 * @param name Nome del cassetto.
	 * @param content Contenuto del cassetto.
	 */
	public Cassetto(String name, Set<String> content) {
		super(name,content);
	}
	
	/**
	 * <code>Costruttore</code> di un cassetto.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto contenuto nel cassetto.
	 */
	public Cassetto(String[] information) {
		super(information);
	}
}
