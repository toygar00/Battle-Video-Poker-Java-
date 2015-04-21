import java.util.Arrays;




public class Poker {
	private Deck deck;
  	private Hand hand;
  	
  	private enum Status { dealing, helding };
	private Status gameStatus = Status.dealing;
  	
  	private int credits;
  	private int betAmount;
  	
  	
  	Boolean heldCards[] = new Boolean[5];
  	
  	public Poker()
  	{
  		Arrays.fill(heldCards, false);
  		deck= new Deck();
  	}
  	
  	public  void dealCards()
  	{
  		System.out.println("in DealCards() function." + "Game status: "+gameStatus);
  		
	  	hand= new Hand(deck);
	  	//hand.display(); //show the summary of the hand, e.g. "full house"
	  	//hand.displayAll(); //look at all the individual cards in the hand	
	  	 hand.display();
	  	gameStatus = Status.helding;
	  	
	  	//return hand.getHandImages();
  	}
  	
  	public void getResult()
  	{
  		System.out.println("in getResult() function." + "Game status: "+gameStatus);
  		for(int i = 0;i<heldCards.length;i++)
  		{
  			if(!heldCards[i])hand.changeACard(i);
  				
  		}
  		hand.evalCards();
  		hand.display();
  		//handle credits
  		
  		deck.resetCards();
  		gameStatus = Status.helding;
  	}
  	
  	public void toogleHeldCard(int i)
  	{
  		heldCards[i] = !heldCards[i];
  		//System.out.println(   + i);
  	}
  	
  	public void initHeldCards()
  	{
  		Arrays.fill(heldCards, false);
  	}
	
  	public String getResultString()
  	{
  		return hand.getResult();
  	}
  	
  	public Boolean getHeldCard(int i)
  	{
  		System.out.println(heldCards[i]);
  		return heldCards[i];
  	}
  	
  	public String[] getCardsLocations()
  	{
  		return hand.getHandImages();
  	}
  	
  	public boolean isDealing()
  	{
  		if(gameStatus == Status.dealing)
  		{
  			// System.out.println("asdasdasd");
  			return true;
  		}
  			
  		else return false;
  	}
}
