package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta un cacciavite.
 * @author 1849661
 *
 */
public class Cacciavite extends Tool{

	/**
	 * <code>Costruttore</code> di un cacciavite.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Cacciavite(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di un cacciavite.
	 * @param name Nome del cacciavite.
	 */
	public Cacciavite(String name) {
		super(name);
	}

}
