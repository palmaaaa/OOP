package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che nel file di gioco esistono elementi con lo stesso nome.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class DuplicateElementsException extends Exception{
	/**
	 * Messaggio d'errore di default.
	 */
	static final String DUPLICATE_ELEMENT = "Nel file '.game' ci sono elementi con lo stesso nome";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>DUPLICATE_ELEMENT</code>.
	 */
	public DuplicateElementsException() {
		super(DUPLICATE_ELEMENT);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public DuplicateElementsException(String errorMessage) {
    	super(errorMessage);
	}
}
