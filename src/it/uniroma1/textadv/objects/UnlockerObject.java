package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe astratta che rappresenta un oggetto che può sbloccarne un altro.
 * @author 1849661
 *
 */
public abstract class UnlockerObject extends it.uniroma1.textadv.objects.GameObject implements Tradable{
	
	private String unlockableObject;

	/**
	 * <code>Costruttore</code> di un oggetto che può sbloccarne altri.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nella terza posizione l'oggetto che sblocca.
	 */
	public UnlockerObject(String[] information) {
		this(information[0], information[2]);
	}

	/**
	 * <code>Costruttore</code> di un oggetto che può sbloccarne altri.
	 * @param name Nome dell'oggetto.
	 * @param unlockableObject Oggetto che sblocca.
	 */
	public UnlockerObject(String name,String unlockableObject) {
		super(name);
		this.unlockableObject = unlockableObject;
	}
	
	/**
	 * Restituisce l'oggetto che può sbloccare.
	 * @return La <code>stringa</code> relativa all'oggetto che sblocca.
	 */
	public String getUnlockable() { return unlockableObject; }

}
