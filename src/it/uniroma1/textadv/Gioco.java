package it.uniroma1.textadv;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

import it.uniroma1.textadv.exceptions.CharacterNotPresentException;
import it.uniroma1.textadv.exceptions.FileExtensionException;
import it.uniroma1.textadv.exceptions.FileNotPresentException;
import it.uniroma1.textadv.exceptions.FireProtectsObjectException;
import it.uniroma1.textadv.exceptions.NotAnAnimalException;
import it.uniroma1.textadv.exceptions.ObjectHasOwnerException;
import it.uniroma1.textadv.exceptions.ObjectNotInInventoryException;
import it.uniroma1.textadv.exceptions.ObjectNotPickupableException;
import it.uniroma1.textadv.exceptions.ObjectNotPresentException;
import it.uniroma1.textadv.exceptions.UnknownCommandException;
import it.uniroma1.textadv.exceptions.UnknownDirectionException;
import it.uniroma1.textadv.exceptions.WrongDirectionException;
import it.uniroma1.textadv.objects.Tesoro;
import it.uniroma1.textadv.characters.Giocatore;

/**
 * Classe che rappresenta un gioco (partita). Permette di giocare a Minizak.
 * @author 1849661
 *
 */
public final class Gioco {
	
	/**
	 * <code>Stringa</code> che viene visualizzata quando si vince.
	 */
	private static final String WIN =
			"##     ##    ###    ####    ##     ## #### ##    ## ########  #######    ####"+"\n"+   
			"##     ##   ## ##    ##     ##     ##  ##  ###   ##    ##    ##     ##   ####"+"\n"+     
			"##     ##  ##   ##   ##     ##     ##  ##  ####  ##    ##    ##     ##   ####"+"\n"+     
			"######### ##     ##  ##     ##     ##  ##  ## ## ##    ##    ##     ##    ## "+"\n"+     
			"##     ## #########  ##      ##   ##   ##  ##  ####    ##    ##     ##       "+"\n"+     
			"##     ## ##     ##  ##       ## ##    ##  ##   ###    ##    ##     ##   ####"+"\n"+     
			"##     ## ##     ## ####       ###    #### ##    ##    ##     #######    ####"+"\n"; 
	
	/**
	 * <code>Stringa</code> che viene visualizzata quando si avvia il gioco.
	 */
	private static final String MINIZAK = 
		    "MM    MM    IIIII    NN   NN    IIIII    ZZZZZ      AAA      KK  KK"+"\n"+
			"MMM  MMM     III     NNN  NN     III        ZZ     AAAAA     KK KK "+"\n"+
			"MM MM MM     III     NN N NN     III       ZZ     AA   AA    KKKK  "+"\n"+
			"MM    MM     III     NN  NNN     III      ZZ      AAAAAAA    KK KK "+"\n"+ 
			"MM    MM    IIIII    NN   NN    IIIII    ZZZZZ    AA   AA    KK  KK"+"\n";
	
	/**
	 * Effettua una chiamata a <code>textInterpreter</code> della classe {@linkplain TextualEngine}.
	 * @param command <code>Stringa</code> che rappresenta il comando inserito dall'utente.
	 */
	private void interpreterCall( String command) {
		
		try {
			TextualEngine.textInterpreter(command);
			
		} catch (UnknownCommandException | WrongDirectionException | UnknownDirectionException | CharacterNotPresentException| ObjectNotPickupableException 
				| ObjectHasOwnerException | ObjectNotPresentException | ObjectNotInInventoryException| FireProtectsObjectException | NotAnAnimalException e) {
			System.out.println(e.getMessage());
	
		} catch (ClassCastException | NullPointerException |IndexOutOfBoundsException e) {
			System.out.println("Non puoi farlo");
		}
	}
	
	/** 
	 * Rimuove tutti i commenti dal <code>file</code> localizzato da <code>path</code>.
	 * @param path <code>Path</code> del <code>file</code> dal quale verranno rimossi i commenti.
	 * @return La <code>lista</code> contenente tutti i comandi del <code>file</code> localizzato da <code>path</code>.
	 * @throws IOException Se un'<code>eccezione</code> I/O viene lanciata.
	 */
	private static List<String> removeCommentsFromPath(Path path) throws IOException {

		return Files.readAllLines( path).stream().map( s -> { 
			if(s.contains("//"))
				return s.substring(0, s.indexOf("//")).replaceAll("\t"," ").trim();
			else
				return s.replaceAll("\t"," ");
		}).collect(Collectors.toList());
	}
	
	/**
	 * Permette all'utente di utilizzare uno script per fare una giocata fast forward.
	 * @param m <code>Mondo</code> nel quale giocare.
	 * @param script <code>Path</code> del <code>file</code> fast forward.
	 * @throws IOException Se un'<code>eccezione</code> I/O viene lanciata.
	 * @throws FileNotPresentException Se il <code>file</code> localizzato dal <code>path</code> non esiste.
	 * @throws FileExtensionException Se l'estensione del <code>file</code> non è '.ff'.
	 */
	public void play(Mondo m, Path script) throws IOException, FileNotPresentException, FileExtensionException {
		
		if(!script.toString().contains(".ff")) throw new FileExtensionException();
		
		if( !script.toFile().exists()) throw new FileNotPresentException(script.toString());
		
		List<String> commands = removeCommentsFromPath(script);
			
		System.out.println("\n"+MINIZAK+"\n"+"[__Fast_Forward_Gameplay__]\n" +m.getDescription()+"\n");
		
		for(String command: commands) {
			
			System.out.print( ">"+command+" -> ");
			interpreterCall(command);
			
			if(Giocatore.getInstance().getInventory().stream().map(Object::getClass).anyMatch( clazz -> clazz ==Tesoro.class) ) {
				System.out.println("\n".repeat(2)+WIN);
				break;
			}
		}
	}

	/**
	 * Permette all'utente di giocare ad un <code>mondo</code> su minizak.
	 * @param m <code>Mondo</code> nel quale giocare.
	 */
	public void play(Mondo m) {
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("\n"+MINIZAK+"\n"+m.getDescription());
		System.out.println("Per la lista dei comandi digitare: 'comandi' \n");
		
		
		while( !Giocatore.getInstance().getInventory().stream().map(Object::getClass).collect(Collectors.toSet()).contains( Tesoro.class)) {
			System.out.print(">");
			interpreterCall(userInput.nextLine());
		}
		System.out.println("\n".repeat(2)+WIN);
		userInput.close();
	}
}