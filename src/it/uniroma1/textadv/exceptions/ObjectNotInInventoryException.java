package it.uniroma1.textadv.exceptions;

/**
 * <code>Eccezione</code> che segnala all'utente che l'oggetto, che si vuole dare ad un altro personaggio, non è presente nel suo inventario.
 * @author 1849661
 *
 */
@SuppressWarnings("serial")
public class ObjectNotInInventoryException extends Exception{
	
	/**
	 * Messaggio d'errore di default.
	 */
	static final String OBJECT_NOT_IN_INVENTORY = "L'oggetto non è nel tuo inventario ";
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con il messaggio d'errore di default: <code>OBJECT_NOT_IN_INVENTORY</code>.
	 */
	public ObjectNotInInventoryException() {
		super(OBJECT_NOT_IN_INVENTORY);
	}
	
	/**
	 * <code>Costruttore</code> dell'<code>eccezione</code>. Viene inizializzato con un messaggio d'errore personalizzato.
	 * @param errorMessage Messaggio di errore personalizzato.
	 */
	public ObjectNotInInventoryException(String errorMessage) {
    	super(errorMessage);
    }
}