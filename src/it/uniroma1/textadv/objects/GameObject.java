package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.characters.GameCharacter;
import it.uniroma1.textadv.exceptions.ObjectHasOwnerException;
import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe astratta che rappresenta un oggetto generico del gioco. Può appartenere ad un personaggio e rompersi.
 * @author 1849661
 *
 */
public abstract class GameObject {

	private String name;
	private it.uniroma1.textadv.characters.GameCharacter owner;
	
	/**
	 * Costruttore di un{@link GameObject}
	 * @param name Nome dell'oggetto.
	 */
	public GameObject(String name) {
		this.name = name;
	}
	
	/**
	 * Costruttore dell'oggetto.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni dell'oggetto.
	 */
	public GameObject(String[] information) {
		this(information[0]);
	}

	/**
	 * Restituisce il nome dell'oggetto.
	 * @return Nome dell'oggetto.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Restituisce il riferimento al <code>personaggio</code> che possiede l'oggetto.
	 * @return Riferimento al <code>personaggio</code> proprietario dell'oggetto.
	 */
	public it.uniroma1.textadv.characters.GameCharacter getOwner() { return owner; }
	
	/**
	 * Imposta un proprietario per l'oggetto.
	 * @param owner Nuovo proprietario dell'oggetto.
	 * @throws ObjectHasOwnerException Se l'oggetto ha già un proprietario.
	 */
	public void setOwner(GameCharacter owner) throws ObjectHasOwnerException { 
		
		if (this.owner != null)
				throw new ObjectHasOwnerException();
		else {
			this.owner  = owner;
			owner.addToInventory( (Tradable) this); 
		}
	}
	
	/**
	 * Rimuove il proprietario dall'oggetto.
	 */
	public void removeOwner() {
		owner.removeFromInventory( (Tradable) this);
		owner = null;
	}
	
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
		GameObject other = (GameObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString () { return getName(); }
}