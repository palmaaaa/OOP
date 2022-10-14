package it.uniroma1.textadv.objects;

/**
 * Classe astratta che rappresenta un oggetto che ne può riempire altri.
 * @author 1849661
 *
 */
public abstract class FillerObject extends GameObject{

	/**
	 * <code>Costruttore</code> di un oggetto che può riempire altri.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public FillerObject(String[] information) {
		this(information[0]);
	}

	/**
	 * <code>Costruttore</code> di un oggetto che può riempire altri.
	 * @param name Nome dell'oggetto.
	 */
	public FillerObject(String name) {
		super(name);
	}

}
