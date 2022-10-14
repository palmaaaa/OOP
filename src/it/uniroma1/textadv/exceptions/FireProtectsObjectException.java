package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'oggetto è protetto da fuoco, pertanto non ci si può interagire.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class FireProtectsObjectException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String FIRE_PROTECTS = "Questo oggetto non può essere toccato, c'è del fuoco ";
	
	/**
	* <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>FIRE_PROTECTS</code>.
	*/
	public FireProtectsObjectException() {
		super(FIRE_PROTECTS);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public FireProtectsObjectException(String errorMessage) {
    	super(errorMessage);
    }
}