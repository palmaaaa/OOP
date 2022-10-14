package it.uniroma1.textadv.characters;

import java.util.List;

/**
 * Classe che rappresente un Guardiano.
 * @author 1849661
 *
 */
public class Guardiano extends Protector{
	
	private String distractionObject;

	/**
	 * <code>Costruttore</code> di un Guardiano.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni del guardiano.
	 */
	public Guardiano(String[] information) {
		this(information[0], List.of (information[2]), information[3]);
	}

	/**
	 * <code>Costruttore</code> di un Guardiano.
	 * @param name Nome del guardiano.
	 * @param protectedObject Oggetto che è protetto dal guardiano.
	 * @param distractionObject Oggetto che distrae il guardiano dal proteggere l'oggetto da lui custodito.
	 */
	public Guardiano(String name, List<String> protectedObject, String distractionObject ) {
		super(name, protectedObject);
		this.distractionObject = distractionObject;	
	}
	
	/**
	 * Metodo che restituisce la <code>stringa</code> relativa all'oggetto/personaggio che distrae il guardiano.
	 * @return <code>Stringa</code> relativa all'oggetto/personaggio che distrae il guardiano.
	 */
	public String getDistraction() { return distractionObject; } 

	@Override
	public String personalSentence() {
		return getName()+": Sono uno dei guardiani secolari del tesoro";
	}
	
	@Override
	public String angrySentence() {
		return getName()+": CHI TI CREDI DI ESSERE? GIU' LE MANI DAL TESORO!";
	}
	
	@Override
	public String requirement() {
		return getDistraction();
	}

	@Override
	public String gotRequirement() {
		return getName()+ " è impegnato a giocare con "+ getDistraction();
	}
	
}