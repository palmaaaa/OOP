package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe astratta che rappresente un oggetto riempibile, come ad esempio un secchio.
 * @author 1849661
 *
 */
public abstract class FillableObject extends GameObject implements Tradable{
	
	private boolean filled;

	/**
	 * <code>Costruttore</code> di un oggetto che può essere riempito.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public FillableObject(String[] information) {
		this(information[0], false);
	}

	/**
	 * <code>Costruttore</code> di un oggetto che può essere riempito.
	 * @param name Nome dell'oggetto.
	 * @param filled <code>Booleano</code> che rappresenta lo stato di rimepimento dell'oggetto.
	 */
	public FillableObject(String name, boolean filled) {
		super(name);
		this.filled = filled;
	}

	/**
	 * Riempie l'oggetto.
	 */
	public void fill() { filled = true; }
	
	/**
	 * Restituisce <code>true</code> se l'oggetto è pieno.
	 * @return <code>Booleano</code> che rappresenta lo stato di rimpimento dell'oggetto.
	 */
	public boolean isFilled() { return filled; }
}
