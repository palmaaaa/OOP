package it.uniroma1.textadv.objects;

import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.interfaces.Openable;

/**
 * Classe che rappresenta un oggetto che ne può contenere altri nel gioco, come ad esempio un cassetto, un armadio o un secchio.
 * @author 1849661
 *
 */
public abstract class Container extends it.uniroma1.textadv.objects.GameObject implements Openable{

	private Set<String> content;
	private boolean open;
	
	/**
	 * <code>Costruttore</code> di un oggetto che può contenerne altri.
	 * @param name Nome dell'oggetto.
	 * @param content Contenuto dell'oggetto.
	 */
	public Container(String name, Set<String> content) {
		super(name);
		this.content = new HashSet<> (Set.copyOf(content));
	}
	
	/**
	 * <code>Costruttore</code> di un oggetto che può contenerne altri.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni, in base alla formattazione
	 * secondo la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica e nelle posizioni successive gli oggetti contenuti.
	 */
	public Container(String[] information) {
		this(information[0], Set.copyOf( List.of( Arrays.copyOfRange(information, 2, information.length))));
	}
	
	/**
	 * Restituisce il contenuto dell'oggetto, se esiste
	 * @return Il contenuto dell'oggetto
	 */
	public Set<String> getContent(){ return content == null ? null : Set.copyOf(content); }
	
	/**
	 * Rimuove uno degli oggetti contenuti.
	 * @param obj L'oggetto che si vuole rimuovere.
	 */
	public void removeFromContent(String obj) { content.remove(obj); }
	
	@Override
	public void unlock() {
		
		this.open = true; 
		for(String obj: getContent()) 
			Giocatore.getInstance().getCurrentRoom().addObject(obj);
	}
	
	@Override
	public boolean isOpen() { return open; }
	
	@Override
	public String toString() { 
		return super.toString();
	}
}
