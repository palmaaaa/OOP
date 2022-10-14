package it.uniroma1.textadv.easteregg;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Classe che rappresenta il gioco del Tris.
 * @author 1849661
 *
 */
public final class Tris {
	private ScacchieraTris scacchiera;
	private char simboloGiocatore;
	private char simboloBot;
	private char winnerSimbol;

	/**
	 * <code>Costruttore</code> del <code>Tris</code>.
	 */
	public Tris() {
		scacchiera = new ScacchieraTris();
	}
	
	/**
	 * Assegna randomicamente il simbolo al giocatore.
	 */
	private void setSimboli() {
		int index = Math.random() <= 0.5 ? 0 : 1;
		setSimboloGiocatore( scacchiera.getSimbolo(index));
		setSimboloBot( scacchiera.getSimbolo(index == 1 ? 0: 1));
		setSimboloGiocatore(getSimboloGiocatore());
	}
	
	/**
	 * Permette di giocare a <code>Tris</code>.
	 */
	private void gioca() {
		
		for (int i = 1; i<10; i++) {	
			if (scacchiera.checkVittoria()) break;
			
			display();
			mossa(i%2);
		}
		display();
		if ( getWinner() == 'n')  System.out.println(asciiArt.DRAW.getAscii());
		else System.out.println( (getSimboloGiocatore() == getWinner() ? asciiArt.WIN.getAscii() : asciiArt.LOSS.getAscii())  );
	}
	
	/**
	 * In base al <code>turno</code> si fa fare la mossa al giocatore corrispondente.
	 * @param turno Intero che rappresenta il turno.
	 */
	private void mossa(int turno) {
		
		if( turno == 1)  //turno di chi ha la 'X'
			if (getSimboloGiocatore() == 'X' ) mossaGiocatore(); else mossaBot();
		else  //turno di chi ha 'O'		
			if (getSimboloGiocatore() == 'O' ) mossaGiocatore(); else mossaBot();
	}
	
	/**
	 * Permette al giocatore di effettuare la sua mossa.
	 */
	private void mossaGiocatore() {
		@SuppressWarnings("resource")
		Scanner inputUser = new Scanner(System.in);
		String input;

		do {
			
			System.out.print("Inserisci il numero della casella che desideri occupare: ");

			try {
				
			input = inputUser.next();
			
			if( posizioneOccupata(input))
				if (Integer.parseInt(input)<=1 || Integer.parseInt(input)>=9)
					System.out.println("Questa posizione è fuori dalla griglia!");
				else
					System.out.println("La posizione è occupata!");
			
			}catch (NumberFormatException e) {
				System.out.println("Devi inserire un numero!");
				input = "10";
			}
			
			
		}while(posizioneOccupata(input));
		scacchiera.memorizza(input, getSimboloGiocatore());
	}
	
	/**
	 * Permette al bot di fare la sua mossa.
	 */
	private void mossaBot() {
		
		try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {}
		
		int casella;
		do {  casella = new Random().nextInt(9) + 1;  } while(posizioneOccupata(casella+""));
		scacchiera.memorizza(casella+"", getSimboloBot());
		System.out.println("Il bot ha messo il simbolo nella casella "+casella);
		
		try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {}
	}
	
	/**
	 * Controlla se la casella inserita è già occupata ( o non valida).
	 * @param posizione <code>Posizione</code> inserita dal giocatore.
	 * @return <code>Booleano</code> che indica se la casella inserita è già occupata.
	 */
	private boolean posizioneOccupata(String posizione){
		
		if (scacchiera.checkOccupato(posizione) == '-' ||  scacchiera.checkOccupato(posizione) == 'X' || scacchiera.checkOccupato(posizione) == 'O') 
			return true;
		return false;
	}
	
	/**
	 * Getter del simbolo vincente.
	 * @return Simbolo vincente.
	 */
	private char getWinner(){ return winnerSimbol;}
	
	/**
	 * Setter del simbolo vincente.
	 * @param winner Simbolo vincente.
	 */
	private void setWinner(char winner){ winnerSimbol = winner;}
	
	/**
	 * Getter del simbolo del giocatore.
	 * @return Simbolo del giocatore.
	 */
	public char getSimboloGiocatore() { return simboloGiocatore; }
	
	/**
	 * Setter del simbolo del giocatore.
	 * @param simbolo Simbolo del giocatore.
	 */
	public void setSimboloGiocatore(char simbolo) { simboloGiocatore = simbolo; }
	
	/**
	 * Getter del simbolo del bot.
	 * @return Simbolo del bot.
	 */
	private char getSimboloBot() { return simboloBot; }
	
	/**
	 * Setter del simbolo del bot.
	 * @param simbolo Simbolo del bot.
	 */
	private void setSimboloBot(char simbolo) { simboloBot = simbolo; }
	
	
	/**
	 * Stampa a video il simbolo del giocatore.
	 */
	private void intro () {
		System.out.println("Il tuo simbolo è: \n" + (getSimboloGiocatore() == 'X' ? asciiArt.X.getAscii(): asciiArt.O.getAscii()) );
		try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {}
	}

	/**
	 * Stampa a video il campo da gioco.
	 */
	private void display() {
		System.out.println("\n".repeat(12));
		System.out.println(scacchiera.stampa());	
		System.out.println("\n".repeat(4));
	}
	
	/**
	 * Permette al giocatore di rigiocare la partita.
	 * @return <code>Booleano</code> che determina se la partita verrà giocata nuovamente.
	 */
	@SuppressWarnings("resource")
	private boolean rigioca() {
		System.out.print("Rigiocare la partita? (y/n): ");
		
		if(new Scanner(System.in).next().toLowerCase().trim().equals("y")) {
			System.out.println();
			return true;
		}else {
			System.out.println();
			return false;
		}
	}
	
	/**
	 * Inizia la partita a <code>Tris</code>.
	 */
	public void startGame() {
		System.out.println(asciiArt.TRIS.getAscii());
		do {
			scacchiera.resettaPartita();
			setSimboli();
			intro();
			gioca();
		}while(rigioca());
		System.out.println("Hai terminato la partita.");
		try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {}
		System.out.print("\n".repeat(2));
	}
	
	/**
	 * Enum che contiene tutte le asciiArt per {@linkplain Tris}
	 * @author 1849661
	 *
	 */
	private enum asciiArt{
		
		/**
		 * AsciiArt di Tris.
		 */
		TRIS { @Override protected String getAscii() { return    
				"=====================================================" + "\n"+
				"||      #######    ######     ###     #####        ||" + "\n"+
				"||         #       #     #     #     #     #       ||" + "\n"+ 
				"||         #       #     #     #     #             ||" + "\n"+
			    "||         #       ######      #      #####        ||" + "\n"+
			    "||         #       #   #       #           #       ||" + "\n"+
			    "||         #       #    #      #     #     #       ||" + "\n"+
			    "||         #       #     #    ###     #####        ||" + "\n"+
			    "=====================================================" + "\n";}
		},
		
		/**
		 * AsciiArt per quando si pareggia.
		 */
		DRAW { @Override protected String getAscii() { return    
				"============================================================================" + "\n" + 
				"============================================================================" + "\n" + 
				"||  ######     #    ######  #######  #####   #####  ### #######       ### ||" + "\n" + 
			    "||  #     #   # #   #     # #       #     # #     #  #  #     #       ### ||" + "\n" +    
			    "||  #     #  #   #  #     # #       #       #        #  #     #       ### ||" + "\n" +   
			    "||  ######  #     # ######  #####   #  #### #  ####  #  #     #        #  ||" + "\n" +    
			    "||  #       ####### #   #   #       #     # #     #  #  #     #           ||" + "\n" +   
			    "||  #       #     # #    #  #       #     # #     #  #  #     #       ### ||" + "\n" +    
			    "||  #       #     # #     # #######  #####   #####  ### #######       ### ||" + "\n" +
			    "============================================================================" + "\n" + 
			    "============================================================================" + "\n" ;}
		},
		
		/**
		 * AsciiArt per quando si vince.
		 */
		WIN { @Override protected String getAscii() { return    
				"============================================================================"  + "\n" + 
				"============================================================================"  + "\n" + 
			    "||  #     #    #    ###       #     # ### #     # ####### #######    ###  ||"  + "\n" +  
			    "||  #     #   # #    #        #     #  #  ##    #    #    #     #    ###  ||"  + "\n" +  
			    "||  #     #  #   #   #        #     #  #  # #   #    #    #     #    ###  ||"  + "\n" +  
			    "||  ####### #     #  #        #     #  #  #  #  #    #    #     #     #   ||"  + "\n" +  
			    "||  #     # #######  #         #   #   #  #   # #    #    #     #         ||"  + "\n" +  
			    "||  #     # #     #  #          # #    #  #    ##    #    #     #    ###  ||"  + "\n" +   
			    "||  #     # #     # ###          #    ### #     #    #    #######    ###  ||"  + "\n" + 
			    "============================================================================"  + "\n" + 
			    "============================================================================"  + "\n" ;}
		},
		
		/**
		 * AsciiArt per quando si perde.
		 */
		LOSS { @Override protected String getAscii() { return    
				"============================================================================"+ "\n" + 
				"============================================================================"+ "\n" + 
			    "||   #     #    #    ###        ######  ####### ######   #####  #######   ||"+ "\n" + 
			    "||   #     #   # #    #         #     # #       #     # #     # #     #   ||"+ "\n" + 
			    "||   #     #  #   #   #         #     # #       #     # #       #     #   ||"+ "\n" + 
			    "||   ####### #     #  #         ######  #####   ######   #####  #     #   ||"+ "\n" + 
			    "||   #     # #######  #         #       #       #   #         # #     #   ||"+ "\n" + 
			    "||   #     # #     #  #         #       #       #    #  #     # #     #   ||"+ "\n" + 
			    "||   #     # #     # ###        #       ####### #     #  #####  #######   ||"+ "\n" + 
			    "============================================================================"+ "\n" + 
			    "============================================================================"+ "\n" ;}
		},
		
		/**
		 * AsciiArt per il simbolo O.
		 */
		O { @Override protected String getAscii() { return       
				"   ####   "+ "\n" +  
				"  #    #  "+ "\n" +   
				"  #    #  "+ "\n" +   
				"  #    #  "+ "\n" +   
				"  #    #  "+ "\n" +   
				"   ####   "+ "\n" ;}
		},
		
		/**
		 * AsciiArt per il simbolo X.
		 */
		X { @Override protected String getAscii() { return    

                "  #    #  "   + "\n" + 
                "   #  #   "   + "\n" +   
                "    ##    "   + "\n" +   
                "    ##    "   + "\n" +   
                "   #  #   "   + "\n" + 
                "  #    #  "   + "\n" ;}
		};
		
		/**
		 * Si ottiene l'AsciiArt della <code>costante enumerativa</code> associata.
		 * @return
		 */
		protected abstract String getAscii();
	}
	
	
	
	/**
	 * Classe che rappresenta la scacchiera del {@linkplain Tris}
	 * @author 1849661
	 *
	 */
	private final class ScacchieraTris {
		
		private char[] posizioni = {'1','2','3','4','5','6','7','8','9'};
		private final char[] simboli = {'X','O'};
		
		/**
		 * Si ottiene il simbolo in base a <code>select</code>.
		 * @param select Intero che permette di selezionare quale simbolo ottenere.
		 * @return Il simbolo associato.
		 */
		public char getSimbolo(int select){ return simboli[select]; }

		/**
		 * Memorizza sulla griglia il simbolo del giocatore che sta effettuando la mossa.
		 * @param coordinata Posizione sulla griglia.
		 * @param simbolo <code>Simbolo</code> del giocatore che sta effettuando la mossa.
		 */
		public void memorizza(String  coordinata, char simbolo) {
			switch(coordinata ) {
			case "1" -> posizioni[0] = simbolo;
			case "2" -> posizioni[1] = simbolo;
			case "3" -> posizioni[2] = simbolo;
			case "4" -> posizioni[3] = simbolo;
			case "5" -> posizioni[4] = simbolo;
			case "6" -> posizioni[5] = simbolo;
			case "7" -> posizioni[6] = simbolo;
			case "8" -> posizioni[7] = simbolo;
			case "9" -> posizioni[8] = simbolo;
			default -> System.out.println("Coordinata non valida");
			}
		}
		
		/**
		 * Restituisce la rappresentazione sotto forma di <code>stringa</code> della <code>scacchiera</code>.
		 * @return La scacchiera.
		 */
		public String stampa() {
		return  "   "+posizioni[0] +" || "+posizioni[1]+" || "+posizioni[2]+" \n  =============\n"
				+ "   "+ posizioni[3] +" || "+ posizioni[4] +" || "+ posizioni[5] +" \n  =============\n"
				+ "   "+ posizioni[6] +" || "+ posizioni[7] +" || "+ posizioni[8] +" \n";
		}
		/**
		 * Controlla ogni riga e diagonale per determinare se c'è stata una mossa vincente.
		 * @return <code>Booleano</code> che determina se c'è stata una mossa vincente.
		 */
		public boolean checkVittoria(){
			for (int i=0; i<3; i++) {
				
				if((posizioni[i*3]  == posizioni[i*3+1] && posizioni[i*3+1] == posizioni[i*3+2]) ) { //Check riga
					setWinner(posizioni[i*3]);
					return true; 
				}
				
				if((posizioni[i]  == posizioni[i+3] && posizioni[i+3] == posizioni[i+6]) ) { //Check colonna
					setWinner(posizioni[i]);
					return true; 
				}
			}
			
			if((posizioni[0]  == posizioni[4] && posizioni[4] == posizioni[8])) { //Check diagonale sx
				setWinner(posizioni[0]);
				return true; 
			}		
			
			if((posizioni[2]  == posizioni[4] && posizioni[4] == posizioni[6])) { //Check diagonale dx
				setWinner(posizioni[2]);
				return true; 
			}
			
			setWinner('n');
			return false;
		}
		/**
		 * Permette di determinare se la casella inserita è occupata o non valida.
		 * @param input Posizione inserita dal giocatore.
		 * @return <code>Carattere</code> secondo il quale verrà determinato se la casella è occupata.
		 */
		public char checkOccupato(String input) {
			switch( input ) {
				case "1" : return posizioni[0];
				case "2" : return posizioni[1];
				case "3" : return posizioni[2];
				case "4" : return posizioni[3];
				case "5" : return posizioni[4];
				case "6" : return posizioni[5];
				case "7" : return posizioni[6];
				case "8" : return posizioni[7];
				case "9" : return posizioni[8];
				default : return '-';
			}
		}
		/**
		 * Riporta la griglia ai valori iniziali per poter rigiocare la partita.
		 */
		public void resettaPartita() { posizioni = new char[] {'1','2','3','4','5','6','7','8','9'}; }
	}
}