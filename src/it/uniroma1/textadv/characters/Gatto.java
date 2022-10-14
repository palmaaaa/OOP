package it.uniroma1.textadv.characters;

import it.uniroma1.textadv.interfaces.Tradable;

/**
 * Classe che rappresenta un gatto.
 * @author 1849661
 *
 */
public class Gatto extends Animal implements Tradable{

	/**
	 * <code>Costruttore</code> di un gatto.
	 * @param name Nome del gatto.
	 */
	public Gatto(String name) {
		super(name);
	}
	
	/**
	 * <code>Costruttore</code> di un gatto.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni del gatto.
	 */
	public Gatto(String[] information) {
		super(information[0]);
	}

	
	/**
	 * Metodo che permette al gatto di fare le fusa.
	 */
	@Override
	public String personalSentence() { return this.toString()+": Frrrrrr"; }
}