public class Card
{
	private int number;
	private int rank;
	private int color;
	private int coupleNumber;

	public Card(int S)
	{
		number = S;
		rank = S/4;
		color = S%4;
		coupleNumber = setCoupleNumber();
	}
	public Card(int S, int T)
	{
		number = (S-1)*4+T-1;
		rank = S-1;
		color = T-1;
		coupleNumber = setCoupleNumber();
	}
	public Card()
	{
		number = 1;
		rank = 0;
		color = 0;
	}  
	public int getNumber()
	{
		return number;
	}
	public int getRank()
	{
		return rank;
	}
	public int getColor()
	{
		return color;
	}
	public int getCoupleNumber()
	{
		return coupleNumber;
	}
	public String getRankfr()
	{
		String rankfr = new String();
		switch (rank) {
		case 0:  rankfr = "As";
		break;
		case 1:  rankfr = "Deux";
		break;
		case 2:  rankfr = "Trois";
		break;
		case 3:  rankfr = "Quatre";
		break;
		case 4:  rankfr = "Cinq";
		break;
		case 5:  rankfr = "Six";
		break;
		case 6:  rankfr = "Sept";
		break;
		case 7:  rankfr = "Huit";
		break;
		case 8:  rankfr = "Neuf";
		break;
		case 9:  rankfr = "Dix";
		break;
		case 10: rankfr = "Valet";
		break;
		case 11: rankfr = "Dame";
		break;
		case 12: rankfr = "Roi";
		break;
		default: rankfr = "Invalid rank";
		break;
		}
		return rankfr;
	}
	public String getColorfr()
	{
		String colorfr = new String();
		switch (color) {
		case 0:  colorfr = "Trèfle";
		break;
		case 1:  colorfr = "Carreau";
		break;
		case 2:  colorfr = "Pic";
		break;
		case 3:  colorfr = "Coeur";
		break;
		default: colorfr = "Invalid color";
		break;
		}
		return colorfr;
	}
	public String getCardfr()
	{
		return getRankfr()+" de "+getColorfr();
	}
	public void printCardfr()
	{
		System.out.println(getRankfr()+" de "+getColorfr());
	}
	public int setCoupleNumber()
	{
		if (color<2)
		{
			coupleNumber=color*13+rank;  
		}
		else if (color==2)
		{
			coupleNumber=3*13+12-rank; 
		}
		else if (color==3)
		{
			coupleNumber=2*13+12-rank; 
		}
		return coupleNumber;
	}
	public boolean isCouple(Card two)
	{
		if (this.coupleNumber+two.getCoupleNumber()==51) return true;
		else return false;
	}
}