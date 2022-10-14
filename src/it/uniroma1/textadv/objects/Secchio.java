package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta un secchio.
 * @author 1849661
 *
 */
public class Secchio extends FillableObject{
	
	/**
	 * <code>Costruttore</code> di un secchio.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Secchio(String[] information) {
		this(information[0], false);
	}

	/**
	 * <code>Costruttore</code> di un secchio.
	 * @param name Nome del pozzo.
	 * @param filled Stato del secchio (<code>true</code> rappresenta il secchio pieno).
	 */
	public Secchio(String name, boolean filled) {
		super(name,filled);
	}
}
