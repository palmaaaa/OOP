package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che il personaggio non è presente nella stanza corrente.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class CharacterNotPresentException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String CHARACTER_NOT_PRESENT = "Il personaggio non è presente nella stanza ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>CHARACTER_NOT_PRESENT</code>.
	 */
	public CharacterNotPresentException() {
		super(CHARACTER_NOT_PRESENT);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public CharacterNotPresentException(String errorMessage) {
    	super(errorMessage);
    }
}