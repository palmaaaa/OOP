package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che sono state inserite delle direzioni sbagliate per il movimento del giocatore.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class UnknownDirectionException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String UNKOWN_DIRECTION = "Questa direzione non esiste ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>UNKOWN_DIRECTION</code>.
	 */
	public UnknownDirectionException() {
		super(UNKOWN_DIRECTION);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public UnknownDirectionException(String errorMessage) {
    	super(errorMessage);
    }
}