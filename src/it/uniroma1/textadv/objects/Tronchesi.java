package it.uniroma1.textadv.objects;

/**
 * Classe che rappresenta una tronchesi.
 * @author 1849661
 *
 */
public class Tronchesi extends UnlockerObject{

	/**
	 * <code>Costruttore</code> di una tronchesi.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nella terza il 'contenitore' che sblocca.
	 */
	public Tronchesi(String[] information) {
		super(information);
	}

	/**
	 * <code>Costruttore</code> di una tronchesi.
	 * @param name Nome della tronchesi.
	 * @param unlockableObject Oggetto che viene sbloccato dalle tronchesi.
	 */
	public Tronchesi(String name, String unlockableObject) {
		super(name, unlockableObject);
	}
}
