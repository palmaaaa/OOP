package it.uniroma1.textadv;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.uniroma1.textadv.characters.Animal;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.characters.Protector;
import it.uniroma1.textadv.coordinates.Coordinate;
import it.uniroma1.textadv.exceptions.CharacterNotPresentException;
import it.uniroma1.textadv.exceptions.FireProtectsObjectException;
import it.uniroma1.textadv.exceptions.NotAnAnimalException;
import it.uniroma1.textadv.exceptions.ObjectHasOwnerException;
import it.uniroma1.textadv.exceptions.ObjectNotInInventoryException;
import it.uniroma1.textadv.exceptions.ObjectNotPickupableException;
import it.uniroma1.textadv.exceptions.ObjectNotPresentException;
import it.uniroma1.textadv.exceptions.UnknownCommandException;
import it.uniroma1.textadv.exceptions.UnknownDirectionException;
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
import it.uniroma1.textadv.objects.Tool;
import it.uniroma1.textadv.objects.UnlockerObject;

/**
 * La classe rappresenta il motore testuale del gioco.
 * @author 1849661
 *
 */
public final class TextualEngine {
	
	/**
	 * <code>Insieme</code> di parole (articoli, preposizioni etc...) che possono essere inserite in un comando.
	 * Vengono utilizzate per determinare il tipo di interazione/comando e successivamente vengono rimosse.
	 */
	public static final Set<String> stopwords = new HashSet<>( Set.of( "a","da","in","con","su","per","tra","fra",	"la", "il",	"nella","nel", "al" ));

	/**
	 * <code>Costruttore privato</code> che non può essere invocato all'esterno.
	 */
	private TextualEngine() {
	}
	
	/**
	 * Rimuove tutte le <code>stopwords</code> da un comando.
	 * @param words <code>Array</code> di parole che compongono il comando.
	 * @return La <code>lista</code> di parole senza <code>stopwords</code>.
	 */
	public static List<String> removeStopwordsFrom(String[] words) {
		List<String> tempList = new ArrayList<>(List.of(words));
		tempList.removeAll(stopwords);		
		return tempList;
	}
	
	/**
	 * Permette di creare una stringa da una lista di stringhe per ottenere il parametro del comando. Dal comando vengono rimosse anche tutte le <code>stopwords</code>.
	 * @param listOfStrings <code>Lista</code> di stringhe che rappresentano un parametro scomposto (di un comando).
	 * @return La <code>stringa</code> ottenuta dalla concatenazione di ogni parola di <code>listOfStrings</code>.
	 */
	private static String mergeToOneString(List<String> listOfStrings) { 
		listOfStrings.removeAll(stopwords);
		return listOfStrings.stream().map(s -> s.trim()) .collect(Collectors.joining(" ")); 
	}
	
	/**
	 * Elabora una parte del comando per determinare cosa deve guardare il <code>Giocatore</code>.
	 * Effettua una chiamata al metodo <code>look</code> della classe {@linkplain Giocatore}.
	 * @param subject <code>Stringa</code> che rappresenta il soggetto da guardare.
	 */
	private static void look(String subject) { 
		
		if (subject.trim().equals(""))
			System.out.println(Giocatore.getInstance().look()); 
		else {
			var objectReference = Mondo.getInstance().getObjects().get(subject);
			System.out.println( Giocatore.getInstance().look( ((it.uniroma1.textadv.objects.Container)objectReference)));
		}
	}
	
	/**
	 * Stampa a video l'inventario del <code>Personaggio</code>. 
	 * Effettua una chiamata al metodo <code>getInventory()</code> della classe {@linkplain Giocatore}.
	 */
	private static void inventory() {
		System.out.println(Giocatore.getInstance().getInventory()); 
	}
	
	/**
	 * Stampa a video le direzioni percorribili. 
	 * Effettua una chiamata al metodo <code>getRoomLinks()</code> della classe {@linkplain Giocatore}.
	 */
	private static void map() { 
		System.out.println(Giocatore.getInstance().getCurrentRoom().getRoomLinks());
	}
	
	/**
	 * Elabora una parte del comando per determinare con quale oggetto deve giocare il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>play()</code> della classe {@linkplain Giocatore}.
	 * @param playable <code>Stringa</code> che rappresenta l'oggetto al quale è possibile giocare.
	 */
	private static void play(String playable) {
		
		if(playable.equals(""))
			System.out.println("Con cosa?");
		else
			Giocatore.getInstance().play( (Playable)  Mondo.getInstance().getObjects().get(playable) );
	}
	
	/**
	 * Elabora una parte del comando per determinare con quale <code>Personaggio</code> deve parlare il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>talkTo()</code> della classe {@linkplain Giocatore}.
	 * @param characterToTalkTo <code>Stringa</code> che rappresenta il <code>Personaggio</code> con il quale è possibile parlare.
	 * @throws CharacterNotPresentException Se il <code>Personaggio</code> inserito non è presente nella stanza.
	 */
	private static void talk( String characterToTalkTo) throws CharacterNotPresentException {
		
		if(characterToTalkTo.equals("")) 
			System.out.println("Con chi vuoi parlare?");
		else 
			System.out.println(  Giocatore.getInstance().talkTo (Mondo.getInstance().getCharacters().get(characterToTalkTo)));
	}
	
	/**
	 * Elabora una parte del comando per determinare quale animale verrà accarezzato dal <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>pet()</code> della classe {@linkplain Giocatore}.
	 * @param animalToPet <code>Stringa</code> che rappresenta l'animale da accarezzare.
	 * @throws CharacterNotPresentException Se il <code>Personaggio</code> inserito non è presente nella stanza.
	 * @throws NotAnAnimalException Se il <code>Personaggio</code> inserito non è un animale.
	 */
	private static void pet(String animalToPet) throws CharacterNotPresentException, NotAnAnimalException {
			
		if(animalToPet.equals("")) 
			System.out.println("Chi vuoi accarezzare?");
		else
			System.out.println(  Giocatore.getInstance().pet( (Animal) Mondo.getInstance().getCharacters().get(animalToPet) ));
	}
	
	/**
	 * Elabora una parte del comando per determinare in quale direzione si muoverà il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>goTo()</code> della classe {@linkplain Giocatore}.
	 * @param direction <code>Stringa</code> che rappresenta la direzione da prendere.
	 * @throws WrongDirectionException Se la direzione inserita non porta da nessuno parte.
	 * @throws UnknownDirectionException Se la direzione inserita non esiste.
	 */
	private static void goTo(String direction) throws WrongDirectionException, UnknownDirectionException {
		
		Coordinate coordinate = Coordinate.getCoordinate(direction);
		
		if(coordinate == null) throw new UnknownDirectionException();
		
		System.out.println( Giocatore.getInstance().goTo(coordinate));
	}
	
	/**
	 * Elabora una parte del comando per determinare in quale <code>stanza/link</code> entrerà il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>enter()</code> della classe {@linkplain Giocatore}.
	 * @param traversable <code>Stringa</code> che rappresenta l'entità attraversabile.
	 */
	private static void enterIn(String traversable) {
		
		if (traversable.equals("")) System.out.println("Dove?");
		
		else {
			
			var destination = (Traversable) Mondo.getInstance().getLinks().get(traversable);
			
			if(destination == null ) destination = (Traversable) Mondo.getInstance().getRooms().get(traversable);
			
			if(destination == null) System.out.println("Non puoi farlo");
			
			else
				System.out.println( Giocatore.getInstance().getCurrentRoom().getRoomLinks().values().contains(traversable) ?
									Giocatore.getInstance().enter( destination) : "Non puoi raggiungere questa stanza da qui");
		}
	}
	
	/**
	 * Elabora una parte del comando per determinare quale oggetto ({@link it.uniroma1.textadv.objects.GameObject}) prenderà il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>take()</code> della classe {@linkplain Giocatore}.
	 * @param listOfWords <code>Lista</code> di parole che compongono il comando.
	 * @throws ObjectNotPresentException Se l'oggetto inserito non è presente nella stanza.
	 * @throws ObjectNotPickupableException Se l'oggetto non può essere preso.
	 * @throws ObjectHasOwnerException Se l'oggetto inserito ha un proprietario.
	 */
	public static void take( List<String> listOfWords) throws ObjectNotPickupableException, ObjectNotPresentException, ObjectHasOwnerException {
		
		var index = listOfWords.indexOf("da");
		
		if (index == -1) {
			
			var objectReference = Mondo.getInstance().getObjects().get( mergeToOneString(listOfWords.subList(1, listOfWords.size())));
			var characterReference =  Mondo.getInstance().getCharacters().get( mergeToOneString(listOfWords.subList(1, listOfWords.size())));
			var linkReference = Mondo.getInstance().getLinks().get( mergeToOneString(listOfWords.subList(1, listOfWords.size())));
			
			if(linkReference instanceof Bus)
				System.out.println( Giocatore.getInstance().take( (Bus)linkReference ));
			else
				System.out.println( Giocatore.getInstance().take( (Tradable) (objectReference == null ? characterReference: objectReference) ));

		}else 

			System.out.println( Giocatore.getInstance().takeFrom(
					(Tradable) Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(1, index)) ), 
					(Container) Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size())))) );		
	}
	
	/**
	 * Elabora una parte del comando per determinare quale oggetto ({@link it.uniroma1.textadv.objects.GameObject}) aprirà il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>open()</code> della classe {@linkplain Giocatore}.
	 * @param object <code>Stringa</code> che rappresenta l'oggetto da aprire.
	 * @throws FireProtectsObjectException Se l'oggetto che si vuole aprire è protetto dal fuoco.
	 */
	public static void open(String object) throws FireProtectsObjectException {

		if (Mondo.getInstance().getObjects().containsKey(object))
			System.out.println( Giocatore.getInstance().open(  ((Container)  Mondo.getInstance().getObjects().get(object))     ));
		
		else if (Mondo.getInstance().getLinks().containsKey(object))
			System.out.println( Giocatore.getInstance().open(  ((Link)  Mondo.getInstance().getLinks().get(object))     ));
		else
			System.out.println("Non puoi aprirlo");
	}
	
	/**
	 * Metodo in <code>overload</code> per il metodo <code>open(String object)</code> di {@linkplain TextualEngine}.
	 * @param listOfWords <code>Lista</code> di parole che compongono il comando.
	 * @throws FireProtectsObjectException Se l'oggetto che si vuole aprire è protetto dal fuoco.
	 */
	public static void open( List<String> listOfWords) throws FireProtectsObjectException {
		
		var index = listOfWords.indexOf("con");
		
		if (index != -1) {
			
			var openableOne = Mondo.getInstance().getLinks().get(  mergeToOneString( listOfWords.subList(1, index)) );
			var openableTwo = Mondo.getInstance().getObjects().get( mergeToOneString( listOfWords.subList(1, index)));
			var unlocker = Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size())) );

			System.out.println( Giocatore.getInstance().openWith( (Openable) (openableOne == null ? openableTwo: openableOne),((UnlockerObject)unlocker)));
			
		}else 
			open(mergeToOneString(   listOfWords.subList(1, listOfWords.size())));
	}
	
	/**
	 * Elabora una parte del comando per determinare quale oggetto ({@link it.uniroma1.textadv.objects.GameObject}) romperà il <code>Giocatore</code>. 
	 * Effettua una chiamata al metodo <code>breakObject()</code> della classe {@linkplain Giocatore}.
	 * @param listOfWords <code>Lista</code> di parole che compongono il comando.
	 */
	public static void breakCommand(List<String> listOfWords) {
		
		var index = listOfWords.indexOf("con");
		
		if (index != -1) {
			System.out.println( Giocatore.getInstance().breakObject( 
					(DestroyableOnly)Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(1, index)) ),
					(DestroyerObject)Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size())) )));
		}else
			System.out.println("Con cosa?");
	
	}
	
	/**
	 * Elabora una parte del comando per determinare quale oggetto ({@link it.uniroma1.textadv.objects.GameObject})  il <code>Giocatore</code> darà ad un <code>Personaggio</code>. 
	 * Effettua una chiamata al metodo <code>giveTo()</code> della classe {@linkplain Giocatore}.
	 * @param listOfWords <code>Lista</code> di parole che compongono il comando.
	 * @throws ObjectNotInInventoryException Se l'oggetto non è presente nell'inventario.
	 * @throws CharacterNotPresentException Se il <code>Personaggio</code> inserito non è presente nella stanza.
	 * @throws ObjectHasOwnerException Se l'oggetto inserito ha un proprietario.
	 */
	public static void give(List<String> listOfWords) throws ObjectNotInInventoryException, CharacterNotPresentException, ObjectHasOwnerException {
		
		var index = listOfWords.indexOf("a");
		
		if (index != -1) {
			
			var objectToGive = Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(1, index))   );
			var characterToGive = Mondo.getInstance().getCharacters().get(  mergeToOneString( listOfWords.subList(1, index))   );
			var characterThatReceives = Mondo.getInstance().getCharacters().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size()))   );

			Giocatore.getInstance().giveTo( (Tradable) (objectToGive == null ? characterToGive: objectToGive), (Protector)characterThatReceives ) ;
		}else
			System.out.println("A chi vuoi dare questo oggetto?");
		
		
	}
	
	/**
	 * Elabora una parte del comando per determinare quale oggetto({@link it.uniroma1.textadv.objects.GameObject}) il <code>Giocatore</code> userà e in che modo.
	 * Effettua una chiamata al metodo <code>use()</code> della classe {@linkplain Giocatore}.
	 * @param listOfWords <code>Lista</code> di parole che compongono il comando.
	 * @throws ObjectNotInInventoryException Se l'oggetto non è presente nell'inventario.
	 * @throws ObjectNotPresentException Se l'oggetto inserito non è presente nella stanza.
	 */
	public static void use(List<String> listOfWords) throws ObjectNotInInventoryException, ObjectNotPresentException {
		
		var index = listOfWords.indexOf("su");

		if(index != -1) {
			
			var objOneReference =  Mondo.getInstance().getObjects().get(  mergeToOneString(listOfWords.subList(1, index)));
			var objTwoReference = Mondo.getInstance().getObjects().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size()))   );
			var linkReference = Mondo.getInstance().getLinks().get(  mergeToOneString( listOfWords.subList(index+1, listOfWords.size()))   );
			
			if(objOneReference instanceof UnlockerObject && linkReference instanceof OpenableLink) 
				System.out.println( Giocatore.getInstance().use( (UnlockerObject)objOneReference, (OpenableLink)linkReference));
			
			else if(objOneReference instanceof DestroyerObject && objTwoReference instanceof DestroyableOnly )
				System.out.println( Giocatore.getInstance().use( (DestroyerObject)objOneReference, (DestroyableOnly)objTwoReference));
			
			else if(objOneReference instanceof Tool && objTwoReference instanceof BlockingObject ) 
				System.out.println( Giocatore.getInstance().use( (Tool)objOneReference, (BlockingObject)objTwoReference));
			
			else if(objOneReference instanceof FillableObject && objTwoReference instanceof FillerObject )
				System.out.println( Giocatore.getInstance().use( (FillableObject)objOneReference, (FillerObject)objTwoReference));
			
			else if(objOneReference instanceof FillableObject && objTwoReference instanceof Extinguishable )
				System.out.println( Giocatore.getInstance().use( (FillableObject)objOneReference, (Extinguishable)objTwoReference));
			
			else
				System.out.println("Non puoi usarlo/a");

		}else 
			System.out.println(Giocatore.getInstance().use( 
					(OpenableLink) Mondo.getInstance().getLinks().get(  mergeToOneString(listOfWords.subList(1, listOfWords.size()))) ));
	}
	
	
	/**
	 * Preso in input un comando sotto forma di stringa lo trasforma in una chiamata a metodo della classe {@linkplain Giocatore}.
	 * @param command Comando sotto forma di stringa.
	 * @throws UnknownCommandException Se il comando inserito non esiste.
	 * @throws WrongDirectionException Se la direzione inserita non porta da nessuno parte.
	 * @throws UnknownDirectionException Se la direzione inserita non esiste.
	 * @throws CharacterNotPresentException Se il <code>Personaggio</code> inserito non è presente nella stanza.
	 * @throws ObjectNotPresentException Se l'oggetto inserito non è presente nella stanza.
	 * @throws ObjectHasOwnerException Se l'oggetto inserito ha un proprietario.
	 * @throws ObjectNotInInventoryException Se l'oggetto non è presente nell'inventario.
	 * @throws FireProtectsObjectException Se l'oggetto con il quale si vuole interagire è protetto dal fuoco.
	 * @throws ObjectNotPickupableException Se l'oggetto non può essere preso.
	 * @throws NotAnAnimalException Se il <code>Personaggio</code> inserito non è un animale.
	 */
	public static void textInterpreter(String command) 
			throws UnknownCommandException, WrongDirectionException, UnknownDirectionException, CharacterNotPresentException,
			ObjectNotPresentException, ObjectHasOwnerException, ObjectNotInInventoryException, FireProtectsObjectException,ObjectNotPickupableException, NotAnAnimalException {
		
		var splittedCommand = removeStopwordsFrom( command.split(" "));
		String commandType = splittedCommand.get(0).toLowerCase();
		
		var finalCommand = mergeToOneString(splittedCommand.subList(1, splittedCommand.size()));
		var finalCommandWithStopwords =  new ArrayList<> (List.of (command.split(" ")));
		
		switch(commandType) {
		
			case "guarda" -> look( finalCommand);
		
			case "vai" -> goTo( finalCommand) ;
			
			case "apri" -> open( finalCommandWithStopwords);
			
			case "prendi" -> take( finalCommandWithStopwords );
			
			case "rompi" -> breakCommand( finalCommandWithStopwords);
			
			case "usa" -> use( finalCommandWithStopwords); 
			
			case "dai" -> give( finalCommandWithStopwords);
			
			case "entra" -> enterIn( finalCommand );
			
			case "parla" ->  talk( finalCommand);
			
			case "mappa" -> map();
			
			case "inventario" -> inventory();
			
			case "accarezza" -> pet( finalCommand ); 
			
			case "gioca" -> play(finalCommand);
			
			case "comandi" -> System.out.println("Comandi disponibili: guarda, vai, apri, prendi, usa, rompi, dai, entra, parla, mappa, inventario, accarezza, gioca");
			
			default -> { if (Coordinate.getCoordinate(commandType) == null ) throw new UnknownCommandException(); else goTo( commandType );  }
		};
	}
}