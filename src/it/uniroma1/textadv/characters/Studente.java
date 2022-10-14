package it.uniroma1.textadv.characters;

/**
 * Classe che rappresenta uno studente. Fa parte di un easter egg.
 * @author 1849661
 *
 */
public class Studente extends GameCharacter{
	
	/**
	 * <code>Costruttore</code> di uno studente.
	 * @param name Nome dello studente.
	 */
	public Studente(String name) {
		super(name);
	}

	/**
	 * <code>Costruttore</code> di uno studente.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni dello studente.
	 */
	public Studente(String[] information) {
		super(information);
	}

	@Override
	public String personalSentence() {
		return "Ho ricreato Tris in Java per il Professor Navigli. Se vuoi provarlo gioca al computer :)";
	}

}
