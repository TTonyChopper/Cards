package com.java;

public class Main
{
	static void main (String[]args)
	{
		/*
		//tests carte
		com.java.Card testone=new com.java.Card(32);
		testone.printCardfr();
		System.out.println(testone.setCoupleNumber());
		System.out.println("\n\n");

		com.java.Card testtwo=new com.java.Card(9,3);
		testtwo.printCardfr();
		System.out.println(testtwo.setCoupleNumber());


		System.out.println("Parité ? "+testone.isCouple(testtwo));
		System.out.println("\n\n");
		 */
		/*
		//test paquet
		com.java.Deck set = new com.java.Deck(10);
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
		com.java.Deck set = new com.java.Deck(52);
		set.showCards();
		for(int i=0;i<52;i++) System.out.println(i+""+set.isCouple(0,i));
		set.showCards();
		 */
		/*
		//tests de recherche de doubles
               com.java.Deck set = new com.java.Deck(47);
	       set.showCards();
               System.out.println("doubles "+set.removeDoubles());
	       set.showCards();
		 */
		/*
	       //Test avec deux mains piochant dans le paquet
	       com.java.Deck deck = new com.java.Deck(51);

	       com.java.Hand joueur1 = new com.java.Hand(25,deck,true);
	       System.out.println("doubles joueur "+joueur1.removeDoubles());

	       System.out.println("\n\nmain ordinateur");
	       com.java.Hand joueur2 = new com.java.Hand(26,deck,false);
	       System.out.println("doubles ordinateur "+joueur2.removeDoubles());
	       joueur2.showCards();
		 */

		//Jeu des doubles
		Game jeu = new Game();

	}
}
