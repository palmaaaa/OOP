package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che non può accarezzare personaggi che non siano animali.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class NotAnAnimalException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String NOT_AN_ANIMAL = "E' strano accarezzare le persone ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>NOT_AN_ANIMAL</code>.
	 */
	public NotAnAnimalException() {
		super(NOT_AN_ANIMAL);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public NotAnAnimalException(String errorMessage) {
    	super(errorMessage);
    }
}