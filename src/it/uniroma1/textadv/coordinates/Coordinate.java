package it.uniroma1.textadv.coordinates;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Contiene i punti cardinali.
 * @author 1849661
 *
 */
public enum Coordinate {
	/**
	 * Rappresenta il Nord.
	 */
	N,
	/**
	 * Rappresenta l'Est.
	 */
	E,
	/**
	 * Rappresenta il Sud.
	 */
	S,
	/**
	 * Rappresente l'Ovest.
	 */
	W;
	
	/**
	 *  Contiente come chiave <code>l'insieme di stringhe</code> che rappresentano una coordinata e come valore la <code>costante enumerativa</code> associata.
	 */
	public static Map<Set<String>, Coordinate> directions = Map.of( 
			Set.of("n","nord","north"), Coordinate.N,
			Set.of("e","est","east"), Coordinate.E,
			Set.of("s","sud","south"), Coordinate.S,
			Set.of("o","w","ovest","west"), Coordinate.W	);
	
	/**
	 * Consente di ottenere una <code>coordinata</code> associata alla <code>stringa</code> inserita in input.
	 * Se non esiste una <code>stringa</code> associata all'input viene restituito null.
	 * @param coordinate <code>Stringa</code> che rappresenta una <code>coordinata</code>.
	 * @return La <code>coordinata</code> associata alla <code>stringa</code>.
	 */
	public static Coordinate getCoordinate(String coordinate) {
		try { return Coordinate.directions.get( Coordinate.directions.keySet().stream().filter(set -> set.contains(coordinate.toLowerCase())).findFirst().get() ); 
		} catch(NoSuchElementException e) { return null; }
	}
}