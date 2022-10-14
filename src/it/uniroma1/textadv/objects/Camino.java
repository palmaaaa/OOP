package it.uniroma1.textadv.objects;

import java.util.Set;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.interfaces.Extinguishable;

/**
 * Classe che rappresenta un camino.
 * @author 1849661
 *
 */
public class Camino extends Container implements Extinguishable{
	
	private boolean fire;

	/**
	 * <code>Costruttore</code> di un camino.
	 * @param name Nome del camino.
	 * @param content Contenuto del camino.
	 */
	public Camino(String name, Set<String> content) {
		super(name,content);
	}
	
	/**
	 * <code>Costruttore</code> di un camino.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto contenuto nel camino.
	 */
	public Camino(String[] information) {
		super(information);
		fire = true;	
	}

	/**
	 * Restituisce lo stato del fuoco nel camino.
	 * @return <code>true</code> se il fuoco è acceso.
	 */
	public boolean isFireOn() { return fire; }

	@Override
	public void extinguish() {
		fire = false;
		unlock();
		for(String obj: getContent()) {
			Giocatore.getInstance().getCurrentRoom().addObject( obj );
			removeFromContent(obj);
		}
	}
}
