package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che uno degli elementi, del file inserito, non ha una classe specificata.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ElementHasNoClassException extends Exception{
	/**
	 * Messaggio d'errore di default.
	 */
	static final String ELEMENT_HAS_NO_CLASS = "Nel file '.game' ci sono elementi che non ha una classe specificata";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>ELEMENT_HAS_NO_CLASS</code>.
	 */
	public ElementHasNoClassException() {
		super(ELEMENT_HAS_NO_CLASS);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ElementHasNoClassException(String errorMessage) {
    	super(errorMessage);
	}
}
