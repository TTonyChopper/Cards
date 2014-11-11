import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Paquet
{
	int number;
	ArrayList<Integer> ntaken;
	ArrayList<Integer> nnottaken;

	public Paquet(int S)
	{
		number = 0;
		ntaken= new ArrayList<Integer>();
		nnottaken= new ArrayList<Integer>();
		for (int i = 0; i < 52; i++)
		{
			nnottaken.add(i);
		}
		Collections.shuffle(nnottaken);
		for (int i=0;i<S;i++)
		{
			addCardTop();
		}
	}
	public Paquet()
	{
		number = 52;
		ntaken= new ArrayList<Integer>();
		nnottaken= new ArrayList<Integer>();
		for (int i = 0; i < number; i++)
		{
			ntaken.add(i);
		}
		Collections.shuffle(ntaken);
	}
	public ArrayList<Integer> getNtaken()
	{
		return ntaken;
	}
	public ArrayList<Integer> getNottaken()
	{
		return nnottaken;
	}
	public int getNumber()
	{
		return number;
	}
	public Carte drawCard()
	{
		Random generator = new Random();
		int ndrew = generator.nextInt(ntaken.size());
		Carte drewcard = new Carte(ntaken.get(ndrew));
		Integer Ndrew = new Integer(ndrew);
		nnottaken.add(Ndrew);
		shuffleAntiCards();
		ntaken.remove(ndrew);
		number--;
		return drewcard;
	}
	public Carte drawCard(int position)
	{
		Carte drewcard = new Carte(ntaken.get(position));
		Integer Ndrew = new Integer(position);
		nnottaken.add(Ndrew);
		shuffleAntiCards();
		ntaken.remove(position);
		number--;
		return drewcard;
	}
	public Carte addCardTop()
	{
		Carte ToBeAdded = null;   
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			ntaken.add(nnottaken.get(0));
			int number = (nnottaken.get(0)).intValue();
			ToBeAdded=new Carte(number);
			nnottaken.remove(0);
		}
		number++;
		return ToBeAdded;
	}
	public Carte addCardTop(Carte added)
	{
		int cardnumber=added.getNumber();
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			if (number==0) ntaken.add(cardnumber);
			else
			{
				ntaken.add(cardnumber);
			}
			for(int i=0;i<52-number;i++)
			{	 
				if ((nnottaken.get(i))==cardnumber)
				{
					nnottaken.remove(i);
					number++;
					return added;
				}
			}
		}
		number++;
		return added;
	}
	public Carte addCard()
	{
		Carte ToBeAdded = null;   
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			Random generator = new Random();
			int nput = generator.nextInt(ntaken.size());
			ntaken.add(nput,nnottaken.get(0));
			int number = (nnottaken.get(0)).intValue();
			ToBeAdded=new Carte(number);
			nnottaken.remove(0);
		}
		number++;
		return ToBeAdded;
	}
	public Carte addCard(Carte added)
	{
		int cardnumber=added.getNumber();
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			Random generator = new Random();
			if (number==0) ntaken.add(cardnumber);
			else
			{
				int nput = generator.nextInt(number);
				ntaken.add(nput,cardnumber);
			}
			for(int i=0;i<52-number;i++)
			{	 
				if ((nnottaken.get(i))==cardnumber)
				{
					nnottaken.remove(i);
					number++;
					return added;
				}
			}
		}
		number++;
		return added;
	}
	public void showCards()
	{
		int numberS;
		String zero=new String("");
		for (int i=0;i<number;i++)
		{
			numberS = i+1;
			if (numberS<10) zero="0";
			else zero="";
			Carte cardinhand = new Carte(ntaken.get(i));
			System.out.println("Carte n°"+zero+numberS+" : "+cardinhand.getRankfr()+" de "+cardinhand.getColorfr());
		}
	}
	public void printCardfr(int position)
	{
		Carte card = new Carte(ntaken.get(position));
		card.printCardfr();
	}
	public void shuffleCards()
	{
		Collections.shuffle(ntaken);
	}
	public void shuffleAntiCards()
	{
		Collections.shuffle(nnottaken);
	}
	public int calculateDoubleNumber(int S)
	{
		int rank = S/4;
		int color = S%4;
		int couplenumber=0;
		if (color<2)
		{
			couplenumber=color*13+rank;  
		}
		else if (color==2)
		{
			couplenumber=3*13+12-rank; 
		}
		else if (color==3)
		{
			couplenumber=2*13+12-rank; 
		}
		return couplenumber;
	}
	public boolean isCouple(int i, int j)
	{
		if (calculateDoubleNumber(ntaken.get(i))+calculateDoubleNumber(ntaken.get(j))==51) return true;
		else return false;
	}
	public boolean isCouple(Carte one, Carte two)
	{
		if (one.getCoupleNumber()+two.getCoupleNumber()==51) return true;
		else return false;
	}
	public boolean isDouble()
	{
		boolean ok=false;
		for (int i=0;i<number-1&&!ok;i++)
		{
			if (isCouple(i,number-1)) 
			{
				removeDouble(i,number-1);
				ok=true;
			}
		}
		return ok;
	}
	public Carte lookCard(Paquet two, int cardpos)
	{
		Carte looked = new Carte((two.getNtaken()).get(cardpos));
		return looked;
	}
	public Carte takeCard(Paquet two, int cardpos)
	{
		return addCardTop(two.drawCard(cardpos));
	}
	public void removeDouble(int i, int j)
	{
		System.out.print("  ");
		printCardfr(i);
		System.out.print("+ ");
		printCardfr(j);
		System.out.print("\n");
		drawCard(i);
		drawCard(j-1);
	}
	public int removeDoubles()
	{
		int drawndoubles=0;
		for(int i=0,j;i<number;i++)
		{
			for(j=i+1;j<number;j++)
			{
				if (isCouple(i,j))
				{
					removeDouble(i,j);
					drawndoubles++;
					j=i;
				}
			}
			if (j!=number) i--;  
		}
		return drawndoubles;
	}
}