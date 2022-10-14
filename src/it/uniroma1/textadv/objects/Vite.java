package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta una vite.
 * @author 1849661
 *
 */
public class Vite extends BlockingObject{

	/**
	 * <code>Costruttore</code> di una vite.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza posizione l'oggetto che blocca.
	 */
	public Vite(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di una vite.
	 * @param name Nome dell'oggetto.
	 * @param blockedObject Oggetto bloccato.
	 */
	public Vite(String name, String blockedObject) {
		super(name,blockedObject);
	}
}
