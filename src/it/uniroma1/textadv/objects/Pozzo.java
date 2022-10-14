package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta un pozzo.
 * @author 1849661
 *
 */
public class Pozzo extends FillerObject{

	/**
	 * <code>Costruttore</code> di un pozzo.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Pozzo(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di un pozzo.
	 * @param name Nome del pozzo.
	 */
	public Pozzo(String name) {
		super(name);
	}
}
