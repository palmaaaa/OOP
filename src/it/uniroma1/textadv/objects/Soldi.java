package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe che rappresenta i soldi.
 * @author 1849661
 *
 */
public class Soldi extends GameObject implements Tradable{

	/**
	 * <code>Costruttore</code> dei soldi.
	 * @param name Nome dei soldi.
	 */
	public Soldi(String name) {
		super(name);
	}
	
	/**
	 * <code>Costruttore</code> dei soldi.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Soldi(String[] information) {
		super(information);
	}
}
