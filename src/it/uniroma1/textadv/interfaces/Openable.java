package it.uniroma1.textadv.interfaces;

/**
 * <code>Interfaccia</code> che rappresenta tutti gli <code>oggetti/link</code> che possono essere aperti.
 * @author 1849661
 *
 */
public interface Openable {

	/**
	 * Metodo astratto che restituisce un booleano in base allo stato dell'oggetto (aperto o chiuso.
	 * @return Un booleano che determina se l'oggetto è aperto.
	 */
	abstract boolean isOpen();
	
	/**
	 * Metodo astratto che permette di sbloccare un oggetto bloccato.
	 */
	abstract void unlock();
}