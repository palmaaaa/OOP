package it.uniroma1.textadv.characters;

/**
 * Classe che rappresenta un cane.
 * @author 1849661
 *
 */
public class Cane extends Animal{

	/**
	 * <code>Costruttore</code> di un cane.
	 * @param name Nome del cane.
	 */
	public Cane(String name) {
		super(name);
	}
	
	/**
	 * <code>Costruttore</code> di un cane.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni del cane.
	 */
	public Cane(String[] information) {
		super(information[0]);
	}

	/**
	 * Metodo che permette al cane di abbaiare.
	 */
	@Override
	public String personalSentence() { return this.toString()+": Woof woof :P"; }
}