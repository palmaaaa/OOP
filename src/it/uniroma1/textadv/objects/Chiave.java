package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe che rappresenta una chiave.
 * @author 1849661
 *
 */
public class Chiave extends UnlockerObject implements Tradable{

	/**
	 * <code>Costruttore</code> di un armadio.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto che viene sbloccato dalla chiave.
	 */
	public Chiave(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di una chiave.
	 * @param name Nome della chiave.
	 * @param unlockableObject Link che può essere sbloccato dalla chiave.
	 */
	public Chiave(String name, String unlockableObject) {
		super(name, unlockableObject);
	}
}
