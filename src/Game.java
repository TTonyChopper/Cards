import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game
{ 
	int pauseDelay;
	Scanner sc; 
	boolean runThread;
	WaitingThread waitThread;
	int nbPlayers;
	int playersPlaying;
	Deck deck;
	String answer;
	ArrayList <Hand> hands;
	Card removed;
	int turn;
	ArrayList <Integer> turnPlayed;

	public Game()
	{
		pauseDelay=100;
		System.out.println("                      Jeu des Doubles : \"Le Pouilleux\" (by Anthony LE DREF 2013)                       ");
		sc = new Scanner(System.in); 
		deck = new Deck(52);
		Deck exil = new Deck(0);
		System.out.println("Donner une position entre 1 et 52 : une carte sera retirée.");
		waiting();
		int aretirer =  sc.nextInt();
		waitThread.stopT();
		removed=deck.lookCard(deck,aretirer-1);
		exil.takeCard(deck,aretirer-1);
		//carte retirée
		//removed.printCardfr();

		System.out.println("Donner le nombre de joueurs(vous inclu).");
		waiting();
		nbPlayers =  sc.nextInt();
		playersPlaying=nbPlayers;
		sc.nextLine();
		waitThread.stopT();

		askShuffle();

		int cardsperplayer = 51/nbPlayers;
		int leftcards = 51-nbPlayers*cardsperplayer;
		hands=new ArrayList <Hand> ();
		System.out.println("Votre main de départ :");
		hands.add(new Hand(cardsperplayer,deck,true,removed));
		turnPlayed=new ArrayList <Integer>();
		turnPlayed.add(0);
		for (int i=0;i<nbPlayers-1;i++)
		{
			turnPlayed.add(0);
			hands.add(new Hand(cardsperplayer,deck,false,removed));
			if (leftcards>0)
			{
				(hands.get(i)).takeCard(deck,0);	
				leftcards--;
			}
		}
		System.out.println("");
		pause();
		pause();

		System.out.println("Triez votre jeu :");
		(hands.get(0)).removeDoubles();
		for (int i=1;i<nbPlayers;i++)
		{
			System.out.println((hands.get(i)).getNameCPU()+" aussi :");
			(hands.get(i)).removeDoubles();
		}
		turn=1;
		boolean continuegame;
		playFirstTurn();
		turn++;
		while (continuegame=playTurn())
		{
			checkHands();
			System.out.println("-------------------------Fin du tour n°"+turn+"-------------------------");
			if(continuegame) turn++;
		}
		System.out.println("-------------------------Fin de partie au tour n°"+turn+"-------------------------");
		stats();
		infiniteLoop();
	}
	public void waiting()
	{
		waitThread= new WaitingThread();
	}
	public void pause()
	{
		try
		{Thread.sleep(pauseDelay);}
		catch(Exception E)
		{}
	}
	public void askToPickCard(int playernumber)
	{
		int pnumber = playernumber-1;
		int max = (hands.get(pnumber)).getNumber();
		int pos = 0;
		do
		{
			if (pos>max) System.out.print("\nErreur, réécrivez.\nWaiting");
			System.out.println("Vous retirez une carte au prochain joueur, "+(hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+"). Donnez une position(1-"+max+").");
			waiting();
			pos =  sc.nextInt();
			sc.nextLine();
			waitThread.stopT();
		}while(pos>max); 
		pos--;
		Card newcard=(hands.get(0)).takeCard(hands.get(pnumber),pos);
		System.out.print("Vous retirez une carte au prochain joueur, "+(hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+") : ");
		newcard.printCardfr();
		if(!((hands.get(0)).isDouble())) System.out.println("Pas de chance : pas de nouvelle paire créée...");
		System.out.print("");
		pause();
		pause();
	}
	public void askShuffle()
	{
		System.out.println("Voulez-vous mélangez votre main[système anti-triche :)] ?[Y/N]");
		waiting();
		boolean ok = false;
		do
		{
			answer =  sc.nextLine();
			ok = (answer.equals("Y"))||(answer.equals("y"))||(answer.equals("N"))||(answer.equals("n"));
			if (!ok) System.out.print("\nErreur, réécrivez.\nWaiting");
		}while (!ok);
		waitThread.stopT();
		if ((answer.equals("N"))||(answer.equals("n"))) 
		{
			System.out.println("De toute façon je vois tes cartes...");
			pause();
		}
	}
	public void shufflePlayer()
	{
		if ((answer.equals("Y"))||(answer.equals("y")))
		{
			(hands.get(0)).shuffleCards();
			System.out.println("Main mélangée :");
			(hands.get(0)).showCards();
		}
	}
	public boolean playTurnH()
	{
		if (((hands.get(0)).getNumber())==0) return false;
		boolean ok=false;
		for (int i=nbPlayers;i>1&&!ok;i--)
		{
			ok = (((hands.get(i-1)).getNumber())!=0);
			if (ok) askToPickCard(i);  
		}

		if (((hands.get(0)).getNumber())==0) return false;
		else 
		{
			System.out.println("Votre main :");
			(hands.get(0)).showCards();
			System.out.println("");

			pause();

			shufflePlayer();
			System.out.println("");

			pause();

			return true;
		}
	}
	public boolean playTurnCPU(int playernumber)
	{
		int pnumber=playernumber-1;
		if (((hands.get(pnumber)).getNumber())==0) return false;
		Card taken = null;
		boolean ok=false;

		for (int i=pnumber;i>0&&!ok;i--)
		{
			ok = (((hands.get(i-1)).getNumber())!=0);
			if (ok)
			{
				taken = (hands.get(pnumber)).takeRandomCard(hands.get(i-1));
				if (pnumber==1) System.out.print((hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+") vous prend une carte : ");
				else System.out.print((hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+") s'occupe de "+(hands.get(i-1)).getNameCPU()+" ("+(i)+") : ");
				taken.printCardfr();
				System.out.println("");
				(hands.get(pnumber)).isDouble();
			}
			pause();
		}  

		if (taken==null)
		{
			for (int i=nbPlayers;i>pnumber+1&&!ok;i--)
			{
				ok = (((hands.get(i-1)).getNumber())!=0);
				if (ok)
				{
					taken = (hands.get(pnumber)).takeRandomCard(hands.get(i-1));
					if (pnumber==1) System.out.print((hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+") vous prend une carte : ");
					else System.out.print((hands.get(pnumber)).getNameCPU()+" ("+(pnumber+1)+") s'occupe de "+(hands.get(i-1)).getNameCPU()+" ("+(i)+") : ");
					taken.printCardfr();
					System.out.println("");
					(hands.get(pnumber)).isDouble();
				}
				pause();
			}  
		}


		if (((hands.get(pnumber)).getNumber())==0) return false;
		else 
		{
			(hands.get(pnumber)).shuffleCards();
			return true;
		}
	}
	public int qualify(int playernumber)
	{
		int pnumber=playernumber-1;
		if (pnumber==0) System.out.println("Vous n'avez plus plus de carte !");
		turnPlayed.set(pnumber,turn);	
		playersPlaying--;
		return turn;
	}
	public boolean playFirstTurn()
	{
		if ((hands.get(0)).getFirstPlayer()) 
		{ 
			System.out.println("Vous avez la dame de coeur(ou de carreau.....). Commencez ! ");
			return true;
		}
		else
		{
			int firstone=-1;
			for (int i=1;i<nbPlayers;i++)
			{
				if ((hands.get(i)).getFirstPlayer()) firstone=i; 
			}
			boolean toqualify=false;
			System.out.println((hands.get(firstone)).getNameCPU()+" ("+(firstone+1)+") a la dame de coeur(ou de carreau.....) et commence ! ");
			pause();
			////Qualifier les ordinateurs ?
			for (int i=firstone;i<nbPlayers;i++)
			{
				if ((turnPlayed.get(i))==0) toqualify=!playTurnCPU(i+1);
				if (toqualify&&((turnPlayed.get(i))==0))
				{
					System.out.println((hands.get(i)).getNameCPU()+" ("+(i+1)+") n'a plus de cartes");
					qualify(i+1);
					if (playersPlaying==1) return false;
				}
			}
			System.out.println("Nombre de Joueurs : "+playersPlaying);
		}
		if (playersPlaying==1) return false;
		else return true;
	}
	public boolean playTurn()
	{
		boolean toqualify=false;
		//Qualifier le joueur ?
		if ((turnPlayed.get(0))==0) toqualify=!playTurnH();
		if (toqualify&&((turnPlayed.get(0))==0))
		{
			System.out.println("Vous êtes qualifié !");
			qualify(1);
			if (playersPlaying==1) return false;
		}  
		////Qualifier les ordinateurs ?
		for (int i=1;i<nbPlayers;i++)
		{
			if ((turnPlayed.get(i))==0) toqualify=!playTurnCPU(i+1);
			if (toqualify&&((turnPlayed.get(i))==0))
			{
				System.out.println((hands.get(i)).getNameCPU()+" ("+(i+1)+") n'a plus de cartes");
				qualify(i+1);
				if (playersPlaying==1) return false;
			}
		}
		System.out.println("Nombre de Joueurs : "+playersPlaying);
		if (playersPlaying==1) return false;
		else return true;
	}
	public void checkHands()
	{
		System.out.println("Vous avez "+(hands.get(0)).getNumber()+" cartes");
		for (int i=1;i<nbPlayers;i++)
		{	
			System.out.println((hands.get(i)).getNameCPU()+" ("+(i+1)+") a : "+(hands.get(i)).getNumber()+" cartes");
		}
	}
	public void stats()
	{
		int nloser=-1;
		if ((turnPlayed.get(0))!=0)
		{
			System.out.println("Vous avez gagné au bout de "+turnPlayed.get(0)+" tours !");
			pause();
		}
		for (int i=1;i<nbPlayers;i++)
		{
			turnPlayed.add(0);
			if ((turnPlayed.get(i))==0)
			{
				nloser = i;
			}
			else
			{
				System.out.println((hands.get(i)).getNameCPU()+" ("+(i+1)+") a gagné au bout de "+turnPlayed.get(i)+" tours !");
				pause();
			}
		}
		if (nloser==-1) 
		{
			System.out.println("Vous avez perdu...au "+turn+"ième tour !");
			pause();
			System.out.println("Vous aviez la carte : ");
			(hands.get(0)).printCardfr(0);
			pause();
			System.out.print("La carte que vous aviez retirée était : ");
			pause();
			removed.printCardfr();
		}
		else System.out.println((hands.get(nloser)).getNameCPU()+" ("+(nloser+1)+") a perdu au bout de "+turn+" tours !");


	}
	public void infiniteLoop()
	{
		boolean end=false;
		System.out.println("Appuyez sur [ENTER] pour terminer");
		sc.nextLine();
	}
}