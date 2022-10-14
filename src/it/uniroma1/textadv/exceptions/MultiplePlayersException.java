package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che sono stati inseriti più player nel file di gioco. E' ammesso solo un giocatore.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class MultiplePlayersException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String MULTIPLE_PLAYER = "Il file deve contenere solo un giocatore ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>MULTIPLE_PLAYER</code>.
	 */
	public MultiplePlayersException() {
		super(MULTIPLE_PLAYER);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public MultiplePlayersException(String errorMessage) {
    	super(errorMessage);
    }
}