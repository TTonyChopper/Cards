import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Deck
{
	int number;
	ArrayList<Integer> nTaken;
	ArrayList<Integer> nNotTaken;

	public Deck(int S)
	{
		number = 0;
		nTaken= new ArrayList<Integer>();
		nNotTaken= new ArrayList<Integer>();
		for (int i = 0; i < 52; i++)
		{
			nNotTaken.add(i);
		}
		Collections.shuffle(nNotTaken);
		for (int i=0;i<S;i++)
		{
			addCardTop();
		}
	}
	public Deck()
	{
		number = 52;
		nTaken= new ArrayList<Integer>();
		nNotTaken= new ArrayList<Integer>();
		for (int i = 0; i < number; i++)
		{
			nTaken.add(i);
		}
		Collections.shuffle(nTaken);
	}
	public ArrayList<Integer> getNtaken()
	{
		return nTaken;
	}
	public ArrayList<Integer> getNottaken()
	{
		return nNotTaken;
	}
	public int getNumber()
	{
		return number;
	}
	public Card drawCard()
	{
		Random generator = new Random();
		int ndrew = generator.nextInt(nTaken.size());
		Card drewcard = new Card(nTaken.get(ndrew));
		Integer Ndrew = new Integer(ndrew);
		nNotTaken.add(Ndrew);
		shuffleAntiCards();
		nTaken.remove(ndrew);
		number--;
		return drewcard;
	}
	public Card drawCard(int position)
	{
		Card drewcard = new Card(nTaken.get(position));
		Integer Ndrew = new Integer(position);
		nNotTaken.add(Ndrew);
		shuffleAntiCards();
		nTaken.remove(position);
		number--;
		return drewcard;
	}
	public Card addCardTop()
	{
		Card ToBeAdded = null;   
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			nTaken.add(nNotTaken.get(0));
			int number = (nNotTaken.get(0)).intValue();
			ToBeAdded=new Card(number);
			nNotTaken.remove(0);
		}
		number++;
		return ToBeAdded;
	}
	public Card addCardTop(Card added)
	{
		int cardnumber=added.getNumber();
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			if (number==0) nTaken.add(cardnumber);
			else
			{
				nTaken.add(cardnumber);
			}
			for(int i=0;i<52-number;i++)
			{	 
				if ((nNotTaken.get(i))==cardnumber)
				{
					nNotTaken.remove(i);
					number++;
					return added;
				}
			}
		}
		number++;
		return added;
	}
	public Card addCard()
	{
		Card ToBeAdded = null;   
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			Random generator = new Random();
			int nput = generator.nextInt(nTaken.size());
			nTaken.add(nput,nNotTaken.get(0));
			int number = (nNotTaken.get(0)).intValue();
			ToBeAdded=new Card(number);
			nNotTaken.remove(0);
		}
		number++;
		return ToBeAdded;
	}
	public Card addCard(Card added)
	{
		int cardnumber=added.getNumber();
		if (number==52) System.out.println("Impossible d'ajouter une carte");
		else 
		{
			Random generator = new Random();
			if (number==0) nTaken.add(cardnumber);
			else
			{
				int nput = generator.nextInt(number);
				nTaken.add(nput,cardnumber);
			}
			for(int i=0;i<52-number;i++)
			{	 
				if ((nNotTaken.get(i))==cardnumber)
				{
					nNotTaken.remove(i);
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
			Card cardinhand = new Card(nTaken.get(i));
			System.out.println("Card n°"+zero+numberS+" : "+cardinhand.getRankfr()+" de "+cardinhand.getColorfr());
		}
	}
	public void printCardfr(int position)
	{
		Card card = new Card(nTaken.get(position));
		card.printCardfr();
	}
	public void shuffleCards()
	{
		Collections.shuffle(nTaken);
	}
	public void shuffleAntiCards()
	{
		Collections.shuffle(nNotTaken);
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
		if (calculateDoubleNumber(nTaken.get(i))+calculateDoubleNumber(nTaken.get(j))==51) return true;
		else return false;
	}
	public boolean isCouple(Card one, Card two)
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
	public Card lookCard(Deck two, int cardpos)
	{
		Card looked = new Card((two.getNtaken()).get(cardpos));
		return looked;
	}
	public Card takeCard(Deck two, int cardpos)
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