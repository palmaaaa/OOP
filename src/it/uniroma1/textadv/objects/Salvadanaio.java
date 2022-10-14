package it.uniroma1.textadv.objects;

import java.util.Set;

import it.uniroma1.textadv.interfaces.DestroyableOnly;

/**
 * Classe che rappresenta un salvadanaio.
 * @author 1849661
 *
 */
public class Salvadanaio extends Container implements DestroyableOnly{

	/**
	 * <code>Costruttore</code> di un salvadanaio.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto contenuto nel salvadanaio.
	 */
	public Salvadanaio(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di un salvadanaio.
	 * @param name Nome del salvadanaio.
	 * @param content Contenuto del salvadanaio.
	 */
	public Salvadanaio(String name, Set<String> content) {
		super(name, content);
	}
}
