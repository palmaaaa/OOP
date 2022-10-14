package it.uniroma1.textadv.characters;

import java.util.Arrays;
import java.util.List;

import it.uniroma1.textadv.Mondo;

/**
 * Classe che rappresente un venditore.
 * @author 1849661
 *
 */
public class Venditore extends Protector{
	
	private static final String REQUIREMENT_TO_GET_SELLING_OBJECTS = "soldi";
	
	/**
	 * <code>Costruttore</code> di un Venditore.
	 * @param information <code>Array</code> di <code>stringhe</code> che contiene le informazioni del venditore.
	 */
	public Venditore(String[] information) {
		super(information[0],  List.of(  Arrays.copyOfRange(information, 2, information.length)));
	}

	/**
	 * <code>Costruttore</code> di un venditore.
	 * @param name Nome del venditore.
	 * @param sellingObjects <code>Lista</code> degli oggetti in vendita.
	 */
	public Venditore(String name, List<String> sellingObjects) {
		super(name,sellingObjects);
	}

	@Override
	public String personalSentence() {
		return getName()+": Il mio papa' mi ha lasciato questo negozietto in eredità. Mi manca papà...";
	}
	
	@Override
	public void checkObtainedObject(){
		var protectedObjects = getProtectedObjects();
		super.checkObtainedObject();
		
		if( getInventory().stream().map(Object::toString).anyMatch( obj -> obj.equals(requirement())) )
			for (String obj: protectedObjects) {
				try { Mondo.getInstance().getObjects().get(obj).setOwner( Giocatore.getInstance()); } catch (Exception e) { }
				Giocatore.getInstance().getCurrentRoom().removeObject(obj);
			}
	}
	
	@Override
	public String angrySentence() {
		return getName()+": Haha, ti piacerebbe! Pagami prima.";
	}
	
	@Override
	public String requirement() {
		return REQUIREMENT_TO_GET_SELLING_OBJECTS;
	}

	@Override
	public String gotRequirement() {
		return getName() + " ti ha dato: "+ this.getProtectedObjects().toString();
	}
}