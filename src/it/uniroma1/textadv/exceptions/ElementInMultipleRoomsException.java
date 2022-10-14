package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che nel file di gioco esistono elementi presenti in più stanze contemporanemente.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ElementInMultipleRoomsException extends Exception{
	/**
	 * Messaggio d'errore di default.
	 */
	static final String ELEMENT_IN_MULTIPLE_ROOMS = "Nel file '.game' ci sono elementi presenti in più stanze contemporanemente";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>ELEMENT_IN_MULTIPLE_ROOMS</code>.
	 */
	public ElementInMultipleRoomsException() {
		super(ELEMENT_IN_MULTIPLE_ROOMS);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ElementInMultipleRoomsException(String errorMessage) {
    	super(errorMessage);
	}
}
