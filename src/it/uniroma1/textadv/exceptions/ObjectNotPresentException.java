package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'oggetto non è presente nella stanza corrente.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ObjectNotPresentException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String OBJECT_NOT_PRESENT = "L'oggetto non è presente nella stanza ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>OBJECT_NOT_PRESENT</code>.
	 */
	public ObjectNotPresentException() {
		super(OBJECT_NOT_PRESENT);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ObjectNotPresentException(String errorMessage) {
    	super(errorMessage);
    }
}