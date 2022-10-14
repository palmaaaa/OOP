/**
 * 
 */
package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che il comando non è stato riconosciuto e quindi non è presente.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class UnknownCommandException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String UNKNOWN_COMMAND = "Questo comando non esiste ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>UNKNOWN_COMMAND</code>.
	 */
	public UnknownCommandException() {
		super(UNKNOWN_COMMAND);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public UnknownCommandException(String errorMessage) {
    	super(errorMessage);
    }
}
