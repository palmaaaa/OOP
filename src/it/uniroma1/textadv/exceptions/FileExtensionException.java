package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'estensione del file inserito non è corretta.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class FileExtensionException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String EXTENSION_NOT_VALID = "L'estensione del file non è corretta";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>EXTENSION_NOT_VALID</code>.
	 */
	public FileExtensionException() {
		super(EXTENSION_NOT_VALID);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public FileExtensionException(String errorMessage) {
    	super(errorMessage+ " non è presente nella directory del gioco");
	}
}
