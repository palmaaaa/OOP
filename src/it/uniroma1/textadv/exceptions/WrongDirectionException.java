package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che la direzione presa non esiste.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class WrongDirectionException extends Exception {
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String WRONG_DIRECTION = "Non c'è niente in questa direzione ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>WRONG_DIRECTION</code>.
	 */
	public WrongDirectionException() {
		super(WRONG_DIRECTION);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public WrongDirectionException(String errorMessage) {
    	super(errorMessage);
    }
}
