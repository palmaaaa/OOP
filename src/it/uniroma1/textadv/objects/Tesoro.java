package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe che rappresenta il tesoro.
 * @author 1849661
 *
 */
public class Tesoro extends GameObject implements Tradable{

	/**
	 * <code>Costruttore</code> del tesoro.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Tesoro(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> del tesoro.
	 * @param name Nome del tesoro.
	 */
	public Tesoro(String name) {
		super(name);
	}
}
