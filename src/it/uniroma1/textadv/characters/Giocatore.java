package it.uniroma1.textadv.characters;

import java.util.NoSuchElementException;

import it.uniroma1.textadv.Mondo;
import it.uniroma1.textadv.coordinates.Coordinate;
import it.uniroma1.textadv.exceptions.CharacterNotPresentException;
import it.uniroma1.textadv.exceptions.FireProtectsObjectException;
import it.uniroma1.textadv.exceptions.ObjectHasOwnerException;
import it.uniroma1.textadv.exceptions.ObjectNotInInventoryException;
import it.uniroma1.textadv.exceptions.ObjectNotPresentException;
import it.uniroma1.textadv.exceptions.WrongDirectionException;
import it.uniroma1.textadv.interfaces.DestroyableOnly;
import it.uniroma1.textadv.interfaces.DestroyerObject;
import it.uniroma1.textadv.interfaces.Extinguishable;
import it.uniroma1.textadv.interfaces.Openable;
import it.uniroma1.textadv.interfaces.Playable;
import it.uniroma1.textadv.interfaces.Tradable;
import it.uniroma1.textadv.interfaces.Traversable;
import it.uniroma1.textadv.links.Bus;
import it.uniroma1.textadv.links.Link;
import it.uniroma1.textadv.links.OpenableLink;
import it.uniroma1.textadv.objects.BlockingObject;
import it.uniroma1.textadv.objects.Container;
import it.uniroma1.textadv.objects.FillableObject;
import it.uniroma1.textadv.objects.FillerObject;
import it.uniroma1.textadv.objects.GameObject;
import it.uniroma1.textadv.objects.Tool;
import it.uniroma1.textadv.objects.UnlockerObject;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che rappresenta un Giocatore.
 * @author 1849661
 *
 */
public class Giocatore extends GameCharacter{

	/**
	 * Istanza di <code>Giocatore</code>.
	 */
	private static Giocatore instance;
	
	/**
	 * Restituisce l'istanza del <code>Giocatore</code>.
	 * @return Istanza di <code>Giocatore</code>.
	 */
	public static Giocatore getInstance() {
		if (instance == null) return new Giocatore("Zak");
		return instance;
	}
	 
	/**
	 * <code>Costruttore</code> di un giocatore.
	 * @param name Nome del giocatore.
	 */
	private Giocatore(String name) {
		super(name);
		instance = this;
	}
	
	/**
	 * Imposta la stanza nella quale il giocatore si troverà ad inizio partita.
	 * @param start Stanza di partenza.
	 */
	public void setStart(Room start) { currentRoom = start; }
	
	/**
	 * Fa cambiare stanza al giocatore.
	 * @param newRoom Nuova stanza nella quale si posizionerà il giocatore.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	private String changeRoom(Room newRoom) { 
		currentRoom = newRoom;
		return "Ti sei spostato, ora ti trovi in: " + getCurrentRoom().getName();
	}
	
	/**
	 * Fa cambiare stanza al giocatore in base al link che si vuole attraversare.
	 * @param link Link che il giocatore vuole attraversare.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	private String changeRoom(Link link) { 
		
		if (link instanceof OpenableLink)
			if( ((OpenableLink) link).isOpen() ) 
				
				return changeRoom(Mondo.getInstance().getRooms().get( link.getDestination( getCurrentRoom().getName() )));
			else
				return "Qui c'è: "+ link + ". E' attraversabile, ma ancora da sbloccare";
		else
			return changeRoom(Mondo.getInstance().getRooms().get(   link.getDestination( currentRoom.getName())));
	}
	
	/**
	 * Permette al giocatore cambiare stanza in base a ciò che sta attraversando. Può attraversare stanze e link.
	 * @param traversable Un qualcosa di attraversabile.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	private String changeRoom(Traversable traversable) { 
		
		if ( traversable instanceof Link)
			return changeRoom((Link) traversable);
		else
			return changeRoom((Room) traversable);
	}
	
	/**
	 * Muove il giocatore verso la coordinata desiderata.
	 * @param coordinate Coordinata.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 * @throws WrongDirectionException Se la direzione non esiste.
	 */
	public String goTo(Coordinate coordinate) throws WrongDirectionException {
		
		String destination = Giocatore.getInstance().getCurrentRoom().getRoomLinks().get( coordinate );
		 
		 if (destination == null) 
			 throw new WrongDirectionException();
		 else 
			return Giocatore.getInstance().changeRoom( Mondo.getInstance().getRooms().containsKey( destination) ?
						Mondo.getInstance().getRooms().get(destination) : Mondo.getInstance().getLinks().get(destination) );
	}
	
	/**
	 * Permette al giocatore di entrare in un qualcosa di attraversabile.
	 * @param traversable Qualcosa di attraversabile.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String enter(Traversable traversable) {
		return Giocatore.getInstance().changeRoom (  traversable );
	}
	
	/**
	 * Da l'oggetto indicato da <code>tradableObject</code> ad un personaggio.
	 * @param tradableObject Oggetto scambiabile posseduto dal giocatore.
	 * @param character Personaggio al quale si vuole dare l'oggetto.
	 * @throws CharacterNotPresentException Se il personaggio non è presente nella stanza.
	 * @throws ObjectNotInInventoryException Se l'oggetto che si vuole dare non è nel proprio inventario.
	 * @throws ObjectHasOwnerException Se l'oggetto che si sta dando non è privo di un proprietario.
	 */
	public void giveTo(Tradable tradableObject, GameCharacter character) 
			throws CharacterNotPresentException,ObjectNotInInventoryException,  ObjectHasOwnerException {  
		
		if (!getInventory().contains(tradableObject)) throw new ObjectNotInInventoryException();
		
		if (!getCurrentRoom().getCharacters().contains(character.toString())) throw new CharacterNotPresentException();
		
		if ( tradableObject instanceof GameObject) {

			((GameObject) tradableObject).removeOwner();
			((GameObject) tradableObject).setOwner( character );
		}
		
		if(tradableObject instanceof Animal) {
			removeFromInventory(tradableObject);
			character.addToInventory(tradableObject);
		}
		
		System.out.println("Hai dato: " + tradableObject+ " a " +  character);
		
		if(character instanceof Protector)
			((Protector) character).checkObtainedObject();
	}
	
	/**
	 * Fa prendere il bus al personaggio.
	 * @param bus Bus.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String take(Bus bus) {
		return changeRoom(bus);
	}
	
	/**
	 * Fa prendere un oggetto contenuto da qualche parte (armadio, cassetto etc...) al personaggio.
	 * @param object Oggetto che si vuole prendere.
	 * @param from Oggetto che lo contiene.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String takeFrom( Tradable object, Container from) {
		
		if(from.isOpen()) {
			
			if(from.getContent().contains(object.toString())) {
				addToInventory(object);
				from.removeFromContent(object.toString());
				getCurrentRoom().removeObject(object.toString());
				return "Hai preso: "+object+" da: "+from;
			}
			else
				return from+" non contiene: "+ object;
			
		}else {
			return "Non hai ancora aperto: "+ from;
		}
	}
	
	/**
	 * Fa prendere un oggetto presente nella stanza corrente al giocatore, se possibile.
	 * @param tradableObject Oggetto che si vuole prendere.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 * @throws ObjectNotPresentException Se l'oggetto non è presente nella stanza.
	 * @throws ObjectHasOwnerException Se l'oggetto ha un proprietario.
	 */
	public String take(Tradable tradableObject) throws ObjectNotPresentException, ObjectHasOwnerException { 
		
		if (!(getCurrentRoom().getObjects() == null) && !getCurrentRoom().getObjects().contains(tradableObject.toString())) 
			throw new ObjectNotPresentException();
		
		if ( tradableObject instanceof GameObject) {
			
			var isContainedAnywhereAndLocked =  Mondo.getInstance().getObjects().values().stream()
					.filter( obj -> obj instanceof Container).map(obj -> (Container)obj ) 
					.filter(obj -> obj.isOpen() == false)
					.anyMatch( obj -> obj.getContent().contains(tradableObject.toString()));
			
			if( !isContainedAnywhereAndLocked ) {
				//Questo oggetto è contenuto ( o anche no) in qualche altro oggetto aperto ( quindi si può prendere)
				
				if(((GameObject) tradableObject).getOwner() instanceof Protector) 
					return  (( Protector)((GameObject) tradableObject).getOwner()).angrySentence();
				
				((GameObject) tradableObject).setOwner(this);
				getCurrentRoom().removeObject(tradableObject.toString());
				
				// L'oggetto in cui è contenuto (se è contenuto in un oggetto) è sbloccato. 
				//Siccome sto prendendo l'oggetto dalla stanza lo rimuovo dal contenitore.
				try {
				Mondo.getInstance().getObjects().values().stream()
					.filter( obj -> obj instanceof Container).map(obj -> (Container)obj ) 
					.filter( obj -> obj.getContent().contains(tradableObject.toString()))
					.findFirst().get().removeFromContent(tradableObject.toString());
				}catch(NoSuchElementException e) {}
				
				
			}else 
				return "Devi prima sbloccare l'oggetto che lo contiene...";
		}
		
		if(tradableObject instanceof Animal){
			addToInventory( tradableObject);
			getCurrentRoom().removeCharacter(tradableObject.toString());
		}
		
		return "Hai preso: " + tradableObject;
	}
	
	/**
	 * Parla con un personaggio.
	 * @param character Personaggio con il quale si parla.
	 * @return Frase del personaggio.
	 * @throws CharacterNotPresentException Se il personaggio non è presente.
	 */
	public String talkTo(GameCharacter character) throws CharacterNotPresentException { 
		
		if(!getCurrentRoom().getCharacters().contains(character.toString())) throw new CharacterNotPresentException();
		return character.personalSentence(); 
	}
	
	/**
	 * Restituisce la descrizione della stanza nella quale si trova il giocatore.
	 * @return Descrizione della stanza corrente.
	 */
	public String look() { 
			return getCurrentRoom().toString(); 
	}
	
	/**
	 * Restituisce il contenuto di un oggetto 'contenitore'.
	 * @param containerObject Oggetto che ne contiene altri.
	 * @return Contenuto del 'contenitore'.
	 */
	public String look(Container containerObject) {
		return containerObject.getContent().size() == 0 ?
				containerObject+ " è vuoto/a" : containerObject+ " contiene: "+ containerObject.getContent();
	}
	
	/**
	 * Fa giocare il giocatore con un oggetto interagibile.
	 * @param playableObject Oggetto interagibile.
	 */
	public void play(Playable playableObject) {
		playableObject.play();
	}
	
	/**
	 * Fa aprire il link al personaggio, se possibile.
	 * @param link Il link.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String open(Link link) {
		
		if (link instanceof OpenableLink) {
			
			if ( ((OpenableLink) link).isOpen())
				return "Questo link è già aperto";
			
			boolean isBlocked = Mondo.getInstance().getObjects().values().stream()
			.filter( obj -> obj instanceof BlockingObject)
			.map(obj -> (BlockingObject)obj)
			.anyMatch( obj -> obj.getBlockedObject().equals(link.toString()));
			
			boolean isLocked = Mondo.getInstance().getObjects().values().stream()
			.filter( obj -> obj instanceof UnlockerObject)
			.map(obj -> (UnlockerObject)obj)
			.anyMatch( obj -> obj.getUnlockable().equals(link.toString()));
			
			if(isLocked || isBlocked) 
				return isLocked ? "Sembra che abbia una serratura..." : "Sembra che sia bloccato/a da qualcosa...";
			else {
				((OpenableLink) link).unlock();
				return "Hai aperto: "+link;
			}
			
		}else
			return "Questo link non è bloccato, devi solo attraversarlo";
	}
	
	/**
	 * Permette al giocatore di aprire un oggetto che ne contiene altri, se possibile.
	 * @param container Oggetto contenitore.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 * @throws FireProtectsObjectException Se il contenitore è protetto dal fuoco.
	 */
	public String open(Container container) throws FireProtectsObjectException {
		
		if (container instanceof Extinguishable) throw new FireProtectsObjectException();
		
		if ( container.isOpen())
			return "L'hai già aperto...";
			
		else {
			
			boolean isLocked = Mondo.getInstance().getObjects().values().stream()
			.filter( obj -> obj instanceof UnlockerObject)
			.map(obj -> (UnlockerObject)obj)
			.anyMatch( obj -> obj.getUnlockable().equals(container.toString()));
			
			if(isLocked)
				return "Serve un qualche oggetto per sbloccarlo/a";
			else {
				container.unlock();
				return "Hai aperto: "+ container;
			}
		}
	}
	
	/**
	 * Fa aprire un qualcosa di sbloccabile con un un oggetto che lo può sbloccare.
	 * @param openableObject Oggetto apribile.
	 * @param unlocker Oggetto che sblocca <code>openableObject</code>.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String openWith( Openable openableObject, UnlockerObject unlocker) {
		
		if(openableObject.isOpen()) return "Hai già aperto: "+ openableObject;
		
		if( unlocker.getUnlockable().equals(openableObject.toString())) {
			openableObject.unlock();
			return "Hai aperto: "+ openableObject+ " con: "+ unlocker;
		}else
			return "Non puoi aprire: "+ openableObject+ " con: "+ unlocker;
	}
	
	/**
	 * Esattamente come {@link #openWith} ma con i parametri invertiti.
	 * @param unlocker Oggetto che sblocca <code>locked</code>.
	 * @param locked Oggetto bloccato.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String use( UnlockerObject unlocker, Openable locked) {
		
		var resultOfOpen = openWith(locked, unlocker);
		if(locked instanceof OpenableLink)
			return changeRoom( (Link) locked);
		else
			return resultOfOpen;
	}
	
	/**
	 * Permette di utilizzare un link. Quest'azione è equivalente a {@link #enter(link)}.
	 * @param link Link da utilizzare.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String use( OpenableLink link) {
		return changeRoom( (Link)link);
	}
	
	/**
	 * Permette di rimuovere un blocco ad un oggetto, utilizzando un utensile.
	 * @param tool Utensile.
	 * @param blocker Oggetto che ne blocca un altro.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 * @throws ObjectNotInInventoryException Se l'oggetto non è presente nell'inventario del giocatore.
	 * @throws ObjectNotPresentException Se l'oggetto non è presente nella stanza.
	 */
	public String use(Tool tool, BlockingObject blocker) throws ObjectNotInInventoryException, ObjectNotPresentException {
		
		if( !getInventory().contains(((Tradable)tool))) throw new ObjectNotInInventoryException();
		
		if( !getCurrentRoom().getObjects().contains(blocker.toString())) throw new ObjectNotPresentException();
			
		var blockedObject = (OpenableLink) Mondo.getInstance().getLinks().get( blocker.getBlockedObject()) ;
		blockedObject.removeBlockingObject();
		blocker.removeBlockedObject();
		Mondo.getInstance().getObjects().remove(blocker.toString());
		return "Hai rimosso: "+ blocker;
	}
	
	/**
	 * Permette di estinguere un oggetto, utilizzandone un altro.
	 * @param fillable Oggetto riempibile <code>(es.secchio)</code>.
	 * @param extinguishable Oggetto che può essere estinguibile.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String use( FillableObject fillable, Extinguishable extinguishable) {
		
		if(fillable.isFilled()) {
			extinguishable.extinguish();
			return "Hai spento il fuoco";
		}
		return fillable + " è vuoto/a";
	}
	
	/**
	 * Fa riempire un oggetto con un altro.
	 * @param fillable Oggetto riempibile.
	 * @param filler Oggetto riempitore.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String use(FillableObject fillable, FillerObject filler) {
		
		if (fillable.isFilled()) return "E' già pieno/a";
		
		fillable.fill();
		return "Hai riempito: "+ fillable;
	}
	
	/**
	 * Equivalente a {@linkplain #breakObject} ma con i parametri invertiti.
	 * @param destroyer Oggetto che può distruggere.
	 * @param destroyable Oggetto distruttibile.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String use(DestroyerObject destroyer, DestroyableOnly destroyable) {
		return breakObject(destroyable,destroyer);
	}

	/**
	 * Fa rompere un oggetto al giocatore (utilizzandone un altro).
	 * @param destroyable Oggetto distruttibile.
	 * @param destroyer Oggetto che può distruggere.
	 * @return <code>Stringa</code> che indica se l'azione è stata compiuta.
	 */
	public String breakObject( DestroyableOnly destroyable, DestroyerObject destroyer) {
		
		getCurrentRoom().removeObject( destroyable.toString());
		getCurrentRoom().addObject( destroyable+" (rotto/a)");
		
		if(destroyable instanceof Container) 
			((Container) destroyable).unlock();

		return "Hai rotto: "+ destroyable+ " con:"+ destroyer;
	}
	
	/**
	 * Permette al giocatore di accarezzare un animale.
	 * @param animal <code>Animale</code> che il player accarezzerà.
	 * @return Verso, sotto forma di <code>stringa</code>, dell'animale accarezzato.
	 */
	public String pet(Animal animal){
		return animal.getName() + " è felice!"; 
	}

	@Override
	public String personalSentence() {
		return getName() + ": Sono pronto per l'avventura!";
	}
}