package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'oggetto, che si vuole prendere, ha un proprietario.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ObjectHasOwnerException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String OBJECT_HAS_OWNER = "L'oggetto ha un proprietario";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>OBJECT_HAS_OWNER</code>.
	 */
	public ObjectHasOwnerException() {
		super(OBJECT_HAS_OWNER);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ObjectHasOwnerException(String errorMessage) {
    	super(errorMessage);
    }
}