package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.easteregg.Tris;
import it.uniroma1.textadv.interfaces.Playable;

/**
 * Classe che rappresenta un computer.
 * @author 1849661
 *
 */
public class Computer extends GameObject implements Playable{
	
	/**
	 * <code>Costruttore</code> di un computer.
	 * @param name Nome del computer.
	 */
	public Computer(String name) {
		super(name);
	}

	/**
	 * <code>Costruttore</code> di un computer.
	 * @param information <code>Array</code> di informazioni. Nella prima posizione si troverà il nome dell'oggetto,
	 * nella seconda la classe specifica.
	 */
	public Computer(String[] information) {
		super(information);
	}

	@Override
	public void play() {
		new Tris().startGame();;
		System.out.println("Michele: speriamo che al laboratorio il Professore mi dia un punto bonus per il gioco");
	}
}
