import java.util.Random;
public class Hand extends Paquet 
{
	boolean human;
	String nameCPU;
	boolean firstplayer;

	public Hand(int S,Paquet pioche,boolean B,Carte removed)
	{
		super(0);
		human=B;

		String names[]={"Heisenberg","Gustavo Fring","James Bond","Professeur Xavier","Flash","Patrick Jane",
				"L'homme invisible","La Mouche","God","Anthony LE DREF","Quelqu'un","J'onn J'onzz",
				"Emma Frost","Jean Grey","Le Maître du jeu","Psycho Mantis","Edward Cullen","Alice Cullen",
				"Albert Einstein", "Bruce Wayne","Phoebe Halliwell","Piper Halliwell","Prudence Halliwell","Rikimaru",
				"the Faceless Void","Solid Snake","Big Boss","Naked Snake","Hiro Nakamura","Doctor Who","Twisted Fate",
				"Sherlock Holmes","MewTwo","Steve Urkel","Sheldon Cooper"};

		Random generator = new Random();
		int rand = generator.nextInt(names.length);
		nameCPU=names[rand];
		Carte newcard;
		firstplayer=false;
		for (int i=0;i<S;i++)
		{
			newcard=takeCard(pioche,0);
			if (((removed.getNumber())==51))
			{
				if ((newcard.getNumber())==25) firstplayer=true;
			}
			else if ((newcard.getNumber())==51) firstplayer=true;
		}
		if(B) showCards();
	}
	public String getNameCPU()
	{
		return nameCPU;
	}
	public boolean getFirstPlayer()
	{
		return firstplayer;
	}
	public Carte takeRandomCard(Paquet two)
	{
		Random generator = new Random();
		int ndrew = generator.nextInt(two.getNumber());
		return addCardTop(two.drawCard(ndrew));
	}
}




