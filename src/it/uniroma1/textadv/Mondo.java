package it.uniroma1.textadv;

import java.io.IOException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.uniroma1.textadv.characters.GameCharacter;
import it.uniroma1.textadv.characters.Giocatore;
import it.uniroma1.textadv.characters.Protector;
import it.uniroma1.textadv.coordinates.Coordinate;
import it.uniroma1.textadv.exceptions.DuplicateElementsException;
import it.uniroma1.textadv.exceptions.FileExtensionException;
import it.uniroma1.textadv.exceptions.FileNotPresentException;
import it.uniroma1.textadv.exceptions.ElementHasNoClassException;
import it.uniroma1.textadv.exceptions.ElementInMultipleRoomsException;
import it.uniroma1.textadv.exceptions.MultiplePlayersException;
import it.uniroma1.textadv.exceptions.ObjectHasOwnerException;
import it.uniroma1.textadv.links.Link;
import it.uniroma1.textadv.links.OpenableLink;
import it.uniroma1.textadv.objects.BlockingObject;
import it.uniroma1.textadv.objects.GameObject;
import it.uniroma1.textadv.rooms.Room;

/**
 * Classe che rappresenta un mondo di gioco.
 * @author 1849661
 *
 */
public final class Mondo {
	
	/**
	 * Istanza di <code>Mondo</code>.
	 */
	private static Mondo instance;
	
	private String name;
	private String description;
	private Map<String, Room> rooms;
	private Map<String, GameObject> objects;
	private Map <String, GameCharacter> characters;
	private Map<String, Link> links;
	private Giocatore player;
	
	/**
	 * Restituisce l'istanza del <code>Mondo</code>.
	 * @return Istanza di <code>Mondo</code>.
	 */
	public static Mondo getInstance() {
		if (instance == null) System.out.println("Devi creare il mondo con fromFile");
		return instance;
	}
	
	/**
	 * <code>Costruttore</code> privato del mondo.
	 * @param name Nome del <code>Mondo</code>.
	 * @param description Descrizione del <code>Mondo</code>.
	 * @param rooms Stanze nel <code>Mondo</code>.
	 * @param objects Oggetti nel <code>Mondo</code>.
	 * @param characters Personaggi nel <code>Mondo</code>.
	 * @param links Links nel <code>Mondo</code>.
	 * @param player Giocatore che sta nel <code>Mondo</code>.
	 */
	private Mondo( String name, String description, Map<String, Room> rooms,
				   Map<String,GameObject> objects,Map <String, GameCharacter> characters,
				   Map<String, Link> links ,Giocatore player) {
		
		this.name = name;
		this.description =description;
		this.rooms = rooms;
		this.objects= objects;
		this.characters = characters;
		this.links = links;
		this.player = player;
		instance = this;
	}
	
	/**
	 * Restituisce il nome del mondo.
	 * @return Nome del mondo.
	 */
	public String getName() { return name; }
	
	/**
	 * Restituisce la descrizione del mondo.
	 * @return Descrizione del mondo.
	 */
	public String getDescription() { return description; }
	
	/**
	 * Restituisce le stanze del mondo.
	 * @return Stanze del mondo.
	 */
	public Map<String, Room> getRooms() { return rooms; }
	
	/**
	 * Restituisce gli oggetti del mondo.
	 * @return Oggetti del mondo.
	 */
	public Map<String, GameObject> getObjects() { return objects; }
	
	/**
	 * Restituisce i personaggi del mondo.
	 * @return Personaggi del mondo.
	 */
	public Map<String, GameCharacter> getCharacters() { return characters; }
	
	/**
	 * Restituisce i link del mondo.
	 * @return Link del mondo.
	 */
	public Map<String, Link> getLinks() { return links; }
	
	/**
	 * Restituisce il giocatore del mondo.
	 * @return Giocatore nel mondo.
	 */
	public Giocatore getPlayer() { return player ; }

	
	/**
	 * Restituisce il nome di una classe, compreso di package.
	 * @param <T> Tipo di <code>Class</code>.
	 * @param genericClass Classe generica da cui si estrae il package name.
	 * @param className Nome delle classe di cui si vuole sapere il nome completo.
	 * @return Il nome della classe che comprende il package name.
	 */
	private static <T> String createClassName(Class<T> genericClass, String className) { return genericClass.getPackageName()+"."+className; }
	
	/**
	 * Restituisce una stringa con il primo carattere maiuscolo.
	 * @param string Stringa.
	 * @return Stringa con il primo carattere maiuscolo.
	 */
	private static String firstLetterUpperCase (String string) { 
		return string.substring(0, 1).toUpperCase() + string.substring(1); 
	}
	
	@Override
	public String toString() {
		return rooms.toString()+"\n"+ objects +"\n" + characters +"\n" + links +"\n" + player;
	}
	
	
	/**
	 * Enumerazione che contiene le costanti enumerative che rappresentano tutte le classe degli oggetti di un mondo.
	 * Ogni costante ha un metodo che permette di restituire la classe associata all'oggetto rappresentato.
	 * @author 1849661
	 *
	 */
	private enum classes{
		
		/**
		 * Costante enumerativa che permette di ottenere la classe di un link.
		 */
		LINK { protected Class<?> get() { return Link.class; }},
		
		/**
		 * Costante enumerativa che permette di ottenere la classe di un personaggio.
		 */
		CHARACTER { protected Class<?> get() { return GameCharacter.class; }},
		
		/**
		 * Costante enumerativa che permette di ottenere la classe di una stanza.
		 */
		ROOM { protected Class<?> get() { return Room.class; }},
		
		/**
		 * Costante enumerativa che permette di ottenere la classe di un oggetto.
		 */
		OBJECT { protected Class<?> get() { return GameObject.class; }};
		
		/**
		 * Metodo astratto che ritorna la classe dell'oggetto rappresentato dalla costante enumerativa.
		 * @return La classe dell'oggetto.
		 */
		protected abstract Class<?> get();
	}

	/**
	 * Prende una stringa che rappresenta un file presente nella directory del gioco e restituisce un Mondo di gioco.
	 * @param string Nome del file.
	 * @return Un <code>Mondo</code> di gioco.
	 * @throws FileNotPresentException Se il file specificato non è presente nella directory di gioco.
	 * @throws MultiplePlayersException Se nel file sono presenti più giocatori.
	 * @throws IOException Se avviene un'eccezione I/O.
	 * @throws ObjectHasOwnerException Se esiste un oggetto con più proprietari.
	 * @throws FileExtensionException Se l'estensione del file non è '.game'.
	 * @throws ElementHasNoClassException Se un oggetto/personaggio del gioco non ha una classe specificata nel file '.game'.
	 * @throws DuplicateElementsException Se esistono elementi duplicati nel file '.game'.
	 * @throws ElementInMultipleRoomsException Se un elemento nel file è presente in più stanze.
	 */
	public static Mondo fromFile(String string) 
			throws FileNotPresentException, MultiplePlayersException, IOException, ObjectHasOwnerException, FileExtensionException,
					ElementHasNoClassException, DuplicateElementsException, ElementInMultipleRoomsException {
		
		if(!string.contains(".game") ) throw new FileExtensionException();
		return fromFile(Path.of(Paths.get(string).toString()));
	}
	
	/**
	 * Restituisce un oggetto <code>Mondo</code>, estrapolando tutti i dati necessari dal file '.game' rappresentato da <code>path</code>.
	 * @param path <code>Path</code> che rappresenta il file '.game'.
	 * @return Oggetto <code>Mondo</code>.
	 * @throws FileNotPresentException Se il file specificato non è presente nella directory di gioco.
	 * @throws MultiplePlayersException Se nel file sono presenti più giocatori.
	 * @throws IOException Se avviene un'eccezione I/O.
	 * @throws ObjectHasOwnerException Se un oggetto nel file ha più di un proprietario.
	 * @throws ElementHasNoClassException Se un oggetto/personaggio del gioco non ha una classe specificata nel file '.game'.
	 * @throws DuplicateElementsException Se esistono elementi duplicati nel file '.game'.
	 * @throws ElementInMultipleRoomsException Se un elemento nel file è presente in più stanze.
	 */
	static public Mondo fromFile(Path path) 
			throws FileNotPresentException, MultiplePlayersException, IOException, ObjectHasOwnerException, ElementHasNoClassException,
					DuplicateElementsException, ElementInMultipleRoomsException {	
		
		if( !path.toFile().exists()) throw new FileNotPresentException(path.toString());
		
		Map<String,List<String>> sections = new HashMap<>();
		
		try {
		
		List<String> gameSections = List.of(removeCommentsAndGetString(path).replace("]", "\n").replaceAll("(?m)^\\s+", "").split("\\["));
		
		fileErrorChecker(gameSections);
		
		for (String s : gameSections.subList(1, gameSections.size())) 	
			
			sections.merge(s.split("\n")[0],  
							List.of( s.split("\n")).subList(1, s.split("\n").length),
							(oldVal,newVal ) -> { oldVal.addAll(newVal); return oldVal;} );

		var player = createPlayer(sections.get("player")); 
		Map<String,Link> linksObjects = createSection(sections.get("links"), classes.LINK.get() );
		Map<String, GameObject> objects = createSection(sections.get("objects"), classes.OBJECT.get() );
		
		// aggiorno i link aggiungendo a tutti i link bloccati l'oggetto che li blocca.
		var blockingObjects = objects.values().stream().filter(obj -> obj instanceof BlockingObject)
				  .map( obj -> (BlockingObject) obj) 
				  .collect(Collectors.toList());
		
		for ( BlockingObject blockingObject : blockingObjects){
			
			var blockedObject = (OpenableLink) linksObjects.get (blockingObject.getBlockedObject());
			blockedObject.setBlockedBy(blockingObject.toString());
		}
		
		Map<String,GameCharacter> characters = createSection(sections.get("characters"), classes.CHARACTER.get() );
		
		// per ogni oggetto protteto da un protettore, viene impostato l'oggetto come appartente a codesto protettore
		var Protectors = characters.values().stream().filter(charac -> charac instanceof Protector)
				   .map( charac -> (it.uniroma1.textadv.characters.Protector) charac)
				   .collect(Collectors.toList());
		
		for (Protector protector : Protectors)				
			for (String object: protector.getProtectedObjects()) 
					 objects.get(object).setOwner(protector);
		
		Map<String, Room > rooms = createRooms( sections.entrySet().stream().map( k -> k.getKey())
																			.filter( k -> k.contains("room") )
																			.collect(Collectors.toList()),
												 sections
												);
		
		var worldName = sections.entrySet().stream().map( k -> k.getKey()  ).filter( k -> k.contains("world")).collect(Collectors.joining());
		
		return createWorld(worldName.split(":")[1],
						   sections.get(worldName),
						   player,
						   objects,
						   linksObjects,
						   characters,
						   rooms	);
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException |
				 InvocationTargetException | NoSuchMethodException |  SecurityException   e) {
			e.printStackTrace();
			return null;

		} catch( ObjectHasOwnerException e) {
			throw new ObjectHasOwnerException("Errore nella fomattazione del file, esiste un proprietario duplicato per un oggetto");
		}
	}
	
	/**
	 * Controlla che nel file '.game' non ci siano nomi duplicati o elementi senza una classe specificata.
	 * @param sectionsList Sezioni del file (<code>es. Links, Characters, Objects</code>).
	 * @throws ElementHasNoClassException Se un elemento non ha una classe specificata.
	 * @throws DuplicateElementsException Se un elemento è duplicato nel file.
	 */
	private static void fileErrorChecker(List<String> sectionsList) throws ElementHasNoClassException, DuplicateElementsException {
		
		List<String> allGameElements = new ArrayList<>();
		
		for(String macroSection: sectionsList) {
			if(macroSection.contains("room") ||  macroSection.contains("world")) {
				allGameElements.add( macroSection.split("\n")[0].split(":")[1] );
				continue;
			}
			
			for( String line: List.of( macroSection.split("\n") ).subList(1, macroSection.split("\n").length)) {
				if (line.split("\t").length == 1) throw new ElementHasNoClassException(line.split("\t")[0]+" non ha una classe specificata.");
				allGameElements.add( line.split("\t")[0]);
			}	
		}

		for(String element: allGameElements)
			if (Collections.frequency(allGameElements,element) >1 )  throw new DuplicateElementsException("Esiste un elemento duplicato: "+element);
	}
	
	/**
	 * Restituisce un oggetto <code>Mondo</code>.
	 * @param name Nome del mondo.
	 * @param information Contiene le informazioni sul mondo, tra cui start e descrizione.
	 * @param player Riferimento a <code>Giocatore</code>.
	 * @param objects Oggetti del gioco.
	 * @param linksObjects Link del gioco.
	 * @param characters Personaggi del gioco.
	 * @param rooms Stanze del gioco.
	 * @return Oggetto di tipo <code>Mondo</code>.
	 */
	private static Mondo createWorld(String name, List<String> information, Giocatore player, Map<String, GameObject> objects,
			Map<String,Link> linksObjects, Map<String,GameCharacter> characters, Map<String, Room > rooms	)  {
		
		player.setStart( rooms.get(  information.get(1).split("\t")[1] ));
		
		return new Mondo(name, information.get(0).split("\t")[1], rooms, objects, characters, linksObjects, player);
	}
		
	/**
	 * Metodo che per ogni stanza presente nel file '.game' ne crea l'oggetto, verificando che non ci siano errori nel file.
	 * @param roomNames <code>Lista</code> dei nomi delle stanze.
	 * @param sections Sezioni delle stanze. (<code>es. descrizione, personaggi</code>).
	 * @return Una mappa che contiene tutte le stanze.
	 * @throws IllegalAccessException Vedere {@linkplain IllegalAccessException}.
	 * @throws IllegalArgumentException Vedere {@linkplain IllegalArgumentException}.
	 * @throws InvocationTargetException Vedere {@linkplain InvocationTargetException}.
	 * @throws NoSuchMethodException Vedere {@linkplain NoSuchMethodException}.
	 * @throws SecurityException Vedere {@linkplain SecurityException}.
	 * @throws DuplicateElementsException Se esistono elementi duplicati.
	 * @throws ElementInMultipleRoomsException Se un oggetto/personaggio è presente in più stanze contemporaneamente.
	 */
	private static Map<String, Room> createRooms( List<String> roomNames, Map<String,List<String>> sections) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DuplicateElementsException, ElementInMultipleRoomsException {
		
		Map<String,Room> map = new HashMap<>();
		
		for(String roomName: roomNames) 
			createRoom(  roomName.split(":")[1], sections.get(roomName), map );
		
		// Si controlla se esitono oggetti/personaggi in più stanze. Se esistono si emette l'eccezione.
		checkMultipleElementsInRooms(map);

		return map;
	}
	
	/**
	 * Controlla se in più stanze esiste uno stesso oggetto o personaggio.
	 * @param rooms Stanze.
	 * @throws ElementInMultipleRoomsException Se esiste un oggetto/personaggio in più stanze.
	 */
	private static void checkMultipleElementsInRooms( Map<String,Room> rooms) throws ElementInMultipleRoomsException {
		
		List<String> elementsInRooms = new ArrayList<>();
		for(Room room: rooms.values()) {
			elementsInRooms.addAll( room.getCharacters() == null ? Collections.emptyList() : room.getCharacters() );
			elementsInRooms.addAll( room.getObjects() == null ? Collections.emptyList() : room.getObjects() );
		}
		
		for(String element: elementsInRooms)
			if(Collections.frequency(elementsInRooms, element) > 1) 
				throw new ElementInMultipleRoomsException("Esiste un elemento che è presente in due stanze contemporaneamente: "+ element);
	}
	
	/**
	 * Crea una stanza e l'aggiungerà a <code>mapOfRooms</code>.
	 * @param name Nome della stanza.
	 * @param roomAttributes Attributi della stanza (<code> personaggi,oggetti,link</code>).
	 * @param mapOfRooms Mappa che contiene le stanze.
	 * @throws IllegalAccessException Vedere {@linkplain IllegalAccessException}.
	 * @throws IllegalArgumentException Vedere {@linkplain IllegalArgumentException}.
	 * @throws InvocationTargetException Vedere {@linkplain InvocationTargetException}.
	 * @throws NoSuchMethodException Vedere {@linkplain NoSuchMethodException}.
	 * @throws SecurityException Vedere {@linkplain SecurityException}.
	 * @throws DuplicateElementsException Se esistono due stanze con lo stesso nome.
	 */
	private static void createRoom( String name, List<String> roomAttributes, Map<String,Room> mapOfRooms) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, DuplicateElementsException {
		
		Room.Builder currentRoom = new Room.Builder(name);
		
		for ( String attribute: roomAttributes) {
			
			var args = attribute.split("\t");
			var section = args[0];
			List<String> input;
			
			if(args.length == 1)
				input = null;
			else {
				input = new ArrayList<> (List.of( args[1].split(",")));
				replaceMultipleEmptySpaces(input);
			}
			
			switch(section) {
			
				case "description" -> currentRoom.getClass().getMethod("add"+firstLetterUpperCase(section), String.class).invoke(currentRoom, input.get(0) );
				
				case "objects" -> { currentRoom.getClass().getMethod("add"+firstLetterUpperCase(section), List.class).invoke(currentRoom, input); }
				
				case "characters" -> { currentRoom.getClass().getMethod("add"+firstLetterUpperCase(section), List.class).invoke(currentRoom, input ); }
				
				case "links" ->  currentRoom.getClass().getMethod("add"+firstLetterUpperCase(section), Map.class).invoke(currentRoom, createLinkMapForRoom(input)); 
			
			};
		}
		
		if(mapOfRooms.containsKey(name))
			throw new DuplicateElementsException();  //Il nome della stanza è duplicato
		mapOfRooms.put(name,currentRoom.build());
	}
	
	/**
	 * Rimuove tutti gli spazi di una stringa.
	 * @param elements Elementi appartenenti ad una lista.
	 */
	private static void replaceMultipleEmptySpaces( List<String> elements){
		
		for(int i=0; i<elements.size(); i++) 
			elements.set(i, elements.get(i).trim());
	}
	
	/**
	 * Crea i link di una stanza, ovvero l'associazione coordinata-stanza / coordinata-link.
	 * @param links Link della stanza.
	 * @return Mappa che va da coordinata a nome del link/stanza.
	 */
	private static Map<Coordinate,String> createLinkMapForRoom( List<String> links) {
		
		Map<Coordinate,String> map = new HashMap<>();
		
		for(String pair: links)
			map.put( Coordinate.getCoordinate(pair.split(":")[0]), pair.split(":")[1]);
		
		return map;
	}

	/**
	 * Crea gli oggetti di una determinata sezione del file '.game'.
	 * @param <T> Tipo di <code>Class</code>.
	 * @param section Sezione del file della quale si vogliono creare gli oggetti.
	 * @param classType Classe degli oggetti della sezione.
	 * @return Mappa che va da stringa a oggetti della classe specificata.
	 * @throws InstantiationException Vedere {@linkplain InstantiationException}.
	 * @throws IllegalAccessException Vedere {@linkplain IllegalAccessException}.
	 * @throws IllegalArgumentException Vedere {@linkplain IllegalArgumentException}.
	 * @throws InvocationTargetException Vedere {@linkplain InvocationTargetException}.
	 * @throws NoSuchMethodException Vedere {@linkplain NoSuchMethodException}.
	 * @throws SecurityException Vedere {@linkplain SecurityException}.
	 * @throws ClassNotFoundException Vedere {@linkplain ClassNotFoundException}.
	 */
	@SuppressWarnings("unchecked")
	private static <T> Map<String,T> createSection(List<String> section, Class<?> classType)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		Map<String,T> map = new HashMap<>();
		
		for (String currentElement: section) {
				
			var args = currentElement.split("\t");
			map.put(args[0], (T) Class.forName( createClassName(classType,args[1]) ).getConstructor(String[].class).newInstance( (Object) args ) );	
		}
		return map;
	}
	
	/**
	 * Crea il personaggio.
	 * @param player <code>Stringa</code> che contiene le informazioni sul personaggio.
	 * @return Un oggetto <code>Giocatore</code>.
	 * @throws MultiplePlayersException Se esistono più giocatori nel file '.game'.
	 * @throws ClassNotFoundException Vedere {@linkplain ClassNotFoundException}.
	 * @throws InstantiationException Vedere {@linkplain InstantiationException}.
	 * @throws IllegalAccessException Vedere {@linkplain IllegalAccessException}.
	 * @throws IllegalArgumentException Vedere {@linkplain IllegalArgumentException}.
	 * @throws InvocationTargetException Vedere {@linkplain InvocationTargetException}.
	 * @throws NoSuchMethodException Vedere {@linkplain NoSuchMethodException}.
	 * @throws SecurityException Vedere {@linkplain SecurityException}.
	 */
	private static Giocatore createPlayer( List<String> player) throws MultiplePlayersException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			
		if (player.size() == 1) { 	
			
			Constructor<?> constructor =  Class.forName(Giocatore.class.getName()).getDeclaredConstructor(String.class);
			constructor.setAccessible(true);
			return (Giocatore) constructor.newInstance( player.get(0).split("\t")[0] );
		}
		else 
			throw new MultiplePlayersException();
	}
	
	/**
	 * Rimuove i commenti da un file rappresentato da un <code>Path</code>.
	 * @param path Oggetto <code>Path</code> che rappresenta un file.
	 * @return La stringa che contiene tutte le informazioni del file (senza commenti).
	 * @throws IOException Se avviene un'eccezione I/O.
	 */
	private static String removeCommentsAndGetString(Path path) throws IOException{

		return Files.readAllLines(path).stream().map( s -> { 
			if(s.contains("//"))
				return s.substring(0, s.indexOf("//")) .trim();
			else
				return s.trim();
		}).collect(Collectors.joining("\n"));
	}
}
