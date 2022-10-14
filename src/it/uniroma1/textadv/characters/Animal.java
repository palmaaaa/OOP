package it.uniroma1.textadv.characters;

/**
 * Classe astratta che modella un generico animale.
 * @author 1849661
 *
 */
public abstract class Animal extends GameCharacter {

	/**
	 * <code>Costruttore</code> di un animale.
	 * @param name Nome dell'animale.
	 */
	public Animal(String name) {
		super(name);
	}
	
	/**
	 * <code>Costruttore</code> di un animale.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni dell'animale.
	 */
	public Animal(String[] information) {
		super(information[0]);
	}
}