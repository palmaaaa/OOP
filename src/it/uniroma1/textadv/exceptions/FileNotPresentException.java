package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che il <code>file</code> non è presente nella directory corrente.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class FileNotPresentException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String FILE_NOT_PRESENT = "Il file non è presente nella directory del gioco";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>FILE_NOT_PRESENT</code>.
	 */
	public FileNotPresentException() {
		super(FILE_NOT_PRESENT);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public FileNotPresentException(String errorMessage) {
    	super(errorMessage+ " non è presente nella directory del gioco");
    }
}