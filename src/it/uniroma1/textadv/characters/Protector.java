package it.uniroma1.textadv.characters;

import java.util.List;

import java.util.stream.Collectors;

import it.uniroma1.textadv.Mondo;

/**
 * Classe astratta che modella una categoria speciale di personaggi, ovvero quelli che proteggono uno o più oggetti.
 * @author 1849661
 *
 */
public abstract class Protector extends it.uniroma1.textadv.characters.GameCharacter{
	
	private List<String> protectedObjects;

	/**
	 * <code>Costruttore</code> di un Protector.
	 * @param information <code>Array</code> di <code>stringhe</code>, contenente le informazioni 
	 * necessarie alla costruzione di un Protettore.
	 */
	public Protector(String[] information) {
		super(information);
	}
	
	/**
	 * <code>Costruttore</code> di un Protector.
	 * @param name Nome del protettore.
	 * @param protectedObjects Lista di oggetti protetti (sotto forma di stringa).
	 */
	public Protector(String name, List<String> protectedObjects) {
		super(name);
		this.protectedObjects = protectedObjects;
	}
	
	/**
	 * Metodo che restituisce una copia della <code>lista</code> di oggetti protetti dal protettore.
	 * @return Copia della <code>lista</code> contenente gli oggetti protetti.
	 */
	public List<String> getProtectedObjects() { return List.copyOf(protectedObjects); }

	/**
	 * Metodo che indica quale oggetto (sotto forma di <code>stringa</code>) deve essere dato al protettore per effetuare uno scambio o per distrarlo dall'oggetto che custodisce.
	 * @return Oggetto richiesto dal protettore.
	 */
	public abstract String requirement();
	
	/**
	 * Metodo che restituisce una stringa in base all'oggetto che riceve in uno scambio.
	 * @return <code>Stringa</code> in base all'oggetto ricevuto in uno scambio.
	 */
	public abstract String gotRequirement();
	
	/**
	 * Restituisce la frase di un protettore al quale si vuole sottrarre un oggetto protetto senza dargli nulla in cambio.
	 * @return Frase del protettore arrabbiato.
	 */
	public abstract String angrySentence();
	
	/**
	 * Metodo che verifica se, in uno scambio, l'oggetto ricevuto sia quello desiderato dal protettore. In caso positivo verrà rimossa l'ownership dagli oggetti del protettore e verrà stampato un messaggio speciale.
	 * 
	 */
	public void checkObtainedObject() {
		
		if ( getInventory().stream().map(Object::toString).collect( Collectors.toSet()).contains(requirement()) ) {
			
			for (String obj: protectedObjects) {
				Mondo.getInstance().getObjects().get(obj).removeOwner();
			}
			
			System.out.println(gotRequirement());
		}
	}
}