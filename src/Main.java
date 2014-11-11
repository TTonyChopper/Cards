public class Main
{
	public static void main (String[]args)
	{
		/*
		//tests carte
		Carte testone=new Carte(32);
		testone.printCardfr();
		System.out.println(testone.setCoupleNumber());
		System.out.println("\n\n");

		Carte testtwo=new Carte(9,3);
		testtwo.printCardfr();
		System.out.println(testtwo.setCoupleNumber());


		System.out.println("Parité ? "+testone.isCouple(testtwo));
		System.out.println("\n\n");
		 */
		/*
		//test paquet
		Paquet set = new Paquet(10);
		set.showCards();
		System.out.println("\n\n");

		set.shuffleCards();
		set.showCards();
		System.out.println("\n\n");

		set.drawCard();
		set.showCards();
		System.out.println("\n\n");

		set.addCardTop();
		set.showCards();
		System.out.println("\n\n");

	        set.drawCard(3);
		set.showCards();
		System.out.println("\n\n");

		set.addCard();
		set.showCards();
		System.out.println("\n\n");
		 */
		/*
		//tests paquet avec double
		Paquet set = new Paquet(52);
		set.showCards();
		for(int i=0;i<52;i++) System.out.println(i+""+set.isCouple(0,i));
		set.showCards();
		 */
		/*
		//tests de recherche de doubles
               Paquet set = new Paquet(47);
	       set.showCards();
               System.out.println("doubles "+set.removeDoubles());
	       set.showCards();
		 */
		/*
	       //Test avec deux mains piochant dans le paquet
	       Paquet pioche = new Paquet(51);

	       Hand joueur1 = new Hand(25,pioche,true);
	       System.out.println("doubles joueur "+joueur1.removeDoubles());

	       System.out.println("\n\nmain ordinateur");
	       Hand joueur2 = new Hand(26,pioche,false); 
	       System.out.println("doubles ordinateur "+joueur2.removeDoubles());
	       joueur2.showCards();
		 */

		//Jeu des doubles
		JeuDesDoubles jeu = new JeuDesDoubles();

	}
}
