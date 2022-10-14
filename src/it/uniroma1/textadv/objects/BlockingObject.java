package it.uniroma1.textadv.objects;

/**
 * Classe astratta che rappresenta un oggetto che è in grado di bloccarne altri.
 * @author 1849661
 *
 */
public abstract class BlockingObject extends it.uniroma1.textadv.objects.GameObject{

	private String blockedObject;
	
	/**
	 * <code>Costruttore</code> di un oggetto che può bloccarne altri.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza posizione l'oggetto che blocca.
	 */
	public BlockingObject(String[] information) {
		this(information[0], information[2]);
	}
	
	/**
	 * <code>Costruttore</code> di un oggetto che può bloccarne altri.
	 * @param name Nome dell'oggetto.
	 * @param blockedObject Oggetto bloccato.
	 */
	public BlockingObject(String name,String blockedObject) {
		super(name);
		this.blockedObject = blockedObject;
	}

	/**
	 * Restituisce la <code>stringa</code> dell'oggetto bloccato.
	 * @return <code>Stringa</code> che rappresenta l'oggetto bloccato.
	 */
	public String getBlockedObject() { return blockedObject; }
	
	/**
	 * Rimuove l'oggetto bloccato, liberandolo dal blocco.
	 */
	public void removeBlockedObject() { blockedObject = null; }
}
