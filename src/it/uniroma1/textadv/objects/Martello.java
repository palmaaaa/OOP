package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta un martello.
 * @author 1849661
 *
 */
public class Martello extends Tool{

	/**
	 * <code>Costruttore</code> di un martello.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Martello(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di un martello.
	 * @param name Nome del martello.
	 */
	public Martello(String name) {
		super(name);
	}

}
