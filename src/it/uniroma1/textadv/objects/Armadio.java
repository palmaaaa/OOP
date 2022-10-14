package it.uniroma1.textadv.objects;

import java.util.Set;

import it.uniroma1.textadv.interfaces.Openable;

/**
 * Classe che rappresenta un armadio.
 * @author 1849661
 *
 */
public class Armadio extends Container implements Openable{

	/**
	 * <code>Costruttore</code> di un armadio.
	 * @param name Nome dell'armadio.
	 * @param content Contenuto dell'armadio.
	 */
	public Armadio(String name, Set<String> content) {
		super(name,content);
	}
	
	/**
	 * <code>Costruttore</code> di un armadio.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle terza l'oggetto contenuto nell'armadio.
	 */
	public Armadio(String[] information) {
		super(information);
	}

}
