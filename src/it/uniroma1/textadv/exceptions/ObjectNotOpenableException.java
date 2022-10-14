package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'oggetto che si vuole aprire non è apribile .
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ObjectNotOpenableException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String OBJECT_NOT_OPENABLE = "Non puoi aprirlo ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>OBJECT_NOT_OPENABLE</code>.
	 */
	public ObjectNotOpenableException() {
		super(OBJECT_NOT_OPENABLE);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ObjectNotOpenableException(String errorMessage) {
    	super(errorMessage);
    }
}