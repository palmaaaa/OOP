package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che non è possibile prendere l'oggetto.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ObjectNotPickupableException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String OBJECT_NOT_PICKUPABLE = "Non puoi prenderlo... ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>OBJECT_NOT_PICKUPABLE</code>.
	 */
	public ObjectNotPickupableException() {
		super(OBJECT_NOT_PICKUPABLE);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ObjectNotPickupableException(String errorMessage) {
    	super(errorMessage);
    }
}