package it.uniroma1.textadv.rooms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma1.textadv.coordinates.Coordinate;
import it.uniroma1.textadv.interfaces.Traversable;


/**
 * Classe che rappresenta una generica stanza nel gioco.
 * @author 1849661
 *
 */
public class Room implements Traversable{

	private final String name;
	private final String description;
	private List<String> objects;
	private List<String> characters;
	private Map<Coordinate,String> links;
	
	/**
	 * <code>Costruttore</code> di una stanza.
	 * @param name Nome della stanza.
	 * @param description Descrizione della stanza.
	 * @param objects <code>Mappa</code> da nome degli oggetti presenti nella stanza a riferimento degli oggetti stessi.
	 * @param characters Personaggi presenti nella stanza.
	 * @param link <code>Mappa</code> che ha per ogni coordinata una stanza confinante a quella corrente.
	 */
	private Room(String name, String description, List<String> objects, List<String> characters,Map<Coordinate,String> links ){
		this.name = name;
		this.description = description;
		this.objects = objects;
		this.characters = characters;
		this.links = links;
	}

	/**
	 * Restituisce il nome della stanza.
	 * @return Nome della stanza.
	 */
	public String getName() { return name; }
	
	/**
	 * Restituisce la descrizione della stanza.
	 * @return Descrizione della stanza.
	 */
	public String getDescription() { return description; }
	
	/**
	 * Restituisce una copia della <code>mappa</code> dei link della stanza
	 * @return Copia della <code>mappa</code> dei link della stanza.
	 */
	public Map<Coordinate,String> getRoomLinks() { return links == null ? Map.copyOf(new HashMap<>()) : Map.copyOf(links); }
	
	/**
	 * Restituisce una copia della lista degli oggetti nella stanza
	 * @return Copia della lista degli oggetti nella stanza.
	 */
	public List<String> getObjects() { return objects == null ? null : List.copyOf(objects); }
	
	/**
	 * Restituisce una copia della lista dei personaggi nella stanza
	 * @return Copia della lista dei personaggi nella stanza.
	 */
	public List<String> getCharacters() { return characters == null ? null : List.copyOf(characters); }
	
	/**
	 * Rimuove un personaggio dalla stanza.
	 * @param character Personaggio da rimuovere dalla stanza.
	 */
	public void removeCharacter(String character) { characters.remove(character); }
	
	/**
	 * Rimuove un oggetto dalla stanza.
	 * @param object Oggetto da rimuovere dalla stanza.
	 */
	public void removeObject(String object) { objects.remove(object); }
	
	/**
	 * Aggiunge un oggetto alla stanza.
	 * @param object Oggetto da aggiungere (sotto forma di stringa).
	 */
	public void addObject(String object) { objects.add(object); }
	
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
		Room other = (Room) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString(){ 
		return getDescription() + getString(objects, "oggetti")+  getString(characters, "personaggi");
	}
	
	/**
	 * Permette di ottenere una <code>stringa</code> che descrive il contenuto (di una specifica categoria) della stanza in maniera dinamica.
	 * @param genericList <code>Lista</code> contenente stringhe che rappresentano un qualcosa.
	 * @param subject Categoria degli oggetti ( es. Oggetti, Personaggi)
	 * @return La <code>stringa</code> composta nel formato: <code>'subject'</code> presenti: <code>'genericList'</code>
	 */
	private String getString( List<String> genericList,String subject) { return (genericList == null || genericList.size() == 0 )? "": ", "+ subject +" presenti: "+ genericList.toString().replace("[", "").replace("]", "");}
	
	/**
	 * Classe modellata con il builder pattern. Permette di costruire una stanza.
	 * @author 1849661
	 *
	 */
	public static class  Builder {
		private String name;
		private String description;
		private List<String> objects;
		private List<String> characters;
		private Map<Coordinate,String> links;
		
		/**
		 * <code>Costruttore</code> di un Builder.
		 * @param name Nome della stanza.
		 */
		public Builder(String name) {
			this.name = name;
		}
		
		/**
		 * Aggiunge una descrizione alla stanza.
		 * @param description Descrizione della stanza.
		 * @return Riferimento dell'oggetto corrente.
		 */
		public Builder addDescription(String description) {
			this.description = description;
			return this;
		}
		
		/**
		 * Aggiunge una <code>lista</code> di oggetti alla stanza.
		 * @param objects <code>Lista</code> di oggetti.
		 * @return Riferimento dell'oggetto corrente.
		 */
		public Builder addObjects(List<String> objects) {
			this.objects = objects;
			return this;
		}
		
		/**
		 * Aggiunge una <code>lista</code> di personaggi alla stanza.
		 * @param characters <code>Lista</code> di personaggi.
		 * @return Riferimento dell'oggetto corrente.
		 */
		public Builder addCharacters(List<String> characters) {
			this.characters = characters;
			return this;
		}
		
		/**
		 * Aggiunge i link alla stanza.
		 * @param links <code>Mappa</code> che va da coordinata a link.
		 * @return Riferimento dell'oggetto corrente.
		 */
		public Builder addLinks( Map<Coordinate,String> links) {
			this.links = links;
			return this;
		}

		/**
		 * Costruirisce la stanza.
		 * @return Un oggetto di tipo {@linkplain Room}
		 */
		public Room build() {
			return new Room (name, description, objects, characters, links);
		}
	}
}