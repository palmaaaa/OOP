package it.uniroma1.textadv.characters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.uniroma1.textadv.interfaces.Tradable;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe astratta che modella un generico personaggio.
 * @author 1849661
 */
public abstract class GameCharacter {

	protected String name;
	protected Set<Tradable> inventory;
	protected Room currentRoom;
		
	/**
	 * <code>Costruttore</code> di un personaggio qualsiasi.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene diverse informazioni,
	 * in base alla formattazione la quale ogni oggetto dovrebbe essere costruito. Ovvero nella prima posizione
	 * si troverà il nome del personaggio, nella seconda la classe specifica, nella terza attributo1, nella quarta
	 *  attributo2, nell'ennesima l'attributoN,
	 */
	public GameCharacter(String[] information) {
		this(information[0]);
	}
	
	/**
	 * <code>Costruttore</code> di un personaggio qualsiasi.
	 * @param name Nome del personaggio.
	 */
	public  GameCharacter(String name) {
		this.name = name;
		inventory = new HashSet<>( Collections.emptySet());
	}
	
	/**
	 * Metodo che restituisce il nome del personaggio.
	 * @return Il nome del personaggio.
	 */
	public String getName() { return name; }
	
	/**
	 * Metodo che restituisce la <code>stringa</code> associata all'inventario del personaggio.
	 * @return La lista degli oggetti (sotto forma di <code>stringa</code>) presenti nell'inventario del personaggio.
	 */
	public String InventoryToString() { return getInventory().size() == 0 ? "Inventario vuoto " : "Inventario: " + inventory.toString(); }
	
	/**
	 * Metodo che restituisce una copia dell'inventario del personaggio.
	 * @return Una copia dell'inventario del personaggio.
	 */
	public Set<Tradable> getInventory(){ return Set.copyOf(inventory); }
	
	/**
	 * Metodo che permette di rimuovere un oggetto dall'inventario del personaggio, se presente.
	 * @param item Oggetto da rimuovere dall'inventario.
	 */
	public void removeFromInventory(Tradable item) { inventory.remove(item); }
	
	/**
	 * Metodo che permette di aggiungere un oggetto all'inventario del personaggio.
	 * @param item Oggetto da aggiungere all'inventario.
	 */
	public void addToInventory(Tradable item) { 
		inventory.add(item); }
	
	/**
	 * Metodo che restituisce la stanza in cui si trova il personaggio.
	 * @return Stanza nella quale si trova il personaggio.
	 */
	public Room getCurrentRoom() { return currentRoom; }
	
	/**
	 * Metodo astratto che restituisce la frase unica del personaggio.
	 * @return La frase unica del personaggio.
	 */
	public abstract String personalSentence();
	
	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameCharacter other = (GameCharacter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() { return getName(); }
}