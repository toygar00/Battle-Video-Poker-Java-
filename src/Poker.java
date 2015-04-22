import java.util.Arrays;




public class Poker {
	private Deck deck;
  	private Hand hand;
  	
  	private enum Status { dealing, helding };
	private Status gameStatus = Status.dealing;
  	
  	private int credits;
  	private int betAmount;
  	private int maxBet = 100;
  	private int winAmount = 0;
  	
  	Boolean heldCards[] = new Boolean[5];
  	
  	public Poker()
  	{
  		Arrays.fill(heldCards, false);
  		deck= new Deck();
  		credits = 500;
  		betAmount = 1;
  	}
  	
  	public  void dealCards()
  	{
  		System.out.println("in DealCards() function." + "Game status: "+gameStatus);
  		
	  	hand= new Hand(deck);
	  	//hand.display(); //show the summary of the hand, e.g. "full house"
	  	//hand.displayAll(); //look at all the individual cards in the hand	
	  	 hand.display();
	  	credits -= betAmount;
	  			
	  	gameStatus = Status.helding;
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
  		calculateWin();
  		credits += winAmount;
  		
  		deck.resetCards();
  		gameStatus = Status.dealing;
  	}
  	
  	public boolean isDealing()
  	{
  		if(gameStatus == Status.dealing)
  		{
  			return true;
  		}
  			
  		else return false;
  	}
  	
  	
  	public void addToBet(int i)
  	{
  		if(betAmount + i <= maxBet && betAmount + i > 0 )betAmount += i;
  	}
  	
  	public void calculateWin()
  	{
  		int[] value = hand.getValue();
  		switch( value[0] )
  		{
		case 2:
			if(value[1] > 9) winAmount = betAmount * 1;
			break;
		case 3:
			winAmount = betAmount * 2;
			break;
		case 4:
			winAmount = betAmount * 3;
			break;
		case 5:
			winAmount = betAmount * 4;
			break;
		case 6:
			winAmount = betAmount * 6;
			break;
		case 7:
			winAmount = betAmount * 9;
			break;
		case 8:
			winAmount = betAmount * 25;
			break;
		case 9:
			winAmount = betAmount * 50;
			break;
		case 10:
			winAmount = betAmount * 100;
			break;
		default:
				winAmount = 0;
  		}
  	}
  	
  	
  	public void toogleHeldCard(int i) { heldCards[i] = !heldCards[i];  	}
  	
  	public void initHeldCards()	{	Arrays.fill(heldCards, false);	}
	
  	public String getResultString()	{	return hand.getResult();  	}
  	
  	public Boolean getHeldCard(int i){  return heldCards[i];  	}
  	
  	public String[] getCardsLocations(){	return hand.getHandImages();	}
  	
  	public int getBetAmount(){	return betAmount; }
  
  	public int getCredits(){ return credits;	}
  	
  	public void betMax(){ betAmount = maxBet; };
  	
  	public int getWinAmount(){ return winAmount; }
  	
}
