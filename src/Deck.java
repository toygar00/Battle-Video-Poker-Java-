import java.util.Collections;
//import java.util.Random;
import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;

	 Deck()
	{
		cards = new ArrayList<Card>();
		//int index_1, index_2;
		//Random generator = new Random();
		//Card temp;

		for (short a=0; a<=3; a++)
		{
			for (short b=0; b<=12; b++)
			 {
			   cards.add( new Card(a,b) );
			 }
		}

		//int size = cards.size() -1;

		
		
		Collections.shuffle(cards);
	}

	public Card drawFromDeck()
	{	   
		return cards.remove( cards.size()-1 );
	}

	public int getTotalCards()
	{
		return cards.size();  //we could use this method when making a complete poker game to see if we needed a new deck
	}
} 
