import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Poker {
	private Deck deck;
  	private Hand hand;
  	
  	private enum Status { dealing, helding };
	private Status gameStatus = Status.dealing;
	
	
  	
  	private int attackPoint;
  	private int betAmount;
  	private int maxBet = 100;
  	private int winAmount = 0;
  	
  	private int playerHealth;
  	private int enemyHealth;
  	private int enemyLevel;
  	
  	Boolean heldCards[] = new Boolean[5];
  	
  	public Poker()
  	{
  		Arrays.fill(heldCards, false);
  		deck= new Deck();
  		initValues();
  	}
  	
  	public  void dealCards()
  	{
  		System.out.println("in DealCards() function." + "Game status: "+gameStatus);
  		
	  	hand= new Hand(deck);
	  	//hand.display(); //show the summary of the hand, e.g. "full house"
	  	//hand.displayAll(); //look at all the individual cards in the hand	
	  	 hand.display();
	  	attackPoint -= betAmount;
	  			
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
  		
  		if( winAmount > 0 )
  			enemyHealth -= winAmount;
  		else
  			playerHealth += winAmount;
  		
  		if( enemyHealth <= 0 )
  			newEnemy();
  		
  		if(attackPoint <= 0)gameOver();
  		
  		deck.resetCards();
  		gameStatus = Status.dealing;
  	}
  	
  	public void newEnemy()
  	{
  		playerHealth += enemyLevel * 10;
  		attackPoint += enemyLevel * 20;
  		enemyLevel++;
  		if(enemyLevel % 10 == 0)
  			enemyHealth = enemyLevel * 500;
  		else
  			enemyHealth = enemyLevel * 300;
  		//Set New enemy
  	}
  	
  	public void gameOver()
  	{
  		JOptionPane.showMessageDialog(null, "YOU DIED!", "Game Over" , JOptionPane.INFORMATION_MESSAGE);
  		initValues();
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
  		case 1:
  			winAmount = -betAmount * enemyLevel;
  			break;
		case 2:
			if(value[1] > 9) winAmount = betAmount * 1;
			else winAmount = 0;
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
		//default:
	//			winAmount = -betAmount * enemyLevel;
  		}
  	}
  	
  	public void betMax(){ 
  		if(attackPoint < 100)
  			betAmount = attackPoint; 
  		else
  			betAmount = maxBet; 
  	}
  	
  	public void initValues()
  	{
  		attackPoint = 500;
  		betAmount = 1;
  		
  		playerHealth = 300;
  		enemyLevel = 1;
  		enemyHealth = enemyLevel * 300;
  	}
  	
  	public void toogleHeldCard(int i) { heldCards[i] = !heldCards[i];  	}
  	
  	public void initHeldCards()	{	Arrays.fill(heldCards, false);	}
	
  	public String getResultString()	{	return hand.getResult();  	}
  	
  	public Boolean getHeldCard(int i){  return heldCards[i];  	}
  	
  	public String[] getCardsLocations(){	return hand.getHandImages();	}
  	
  	public int getBetAmount(){	return betAmount; }
  
  	public int getCredits(){ return attackPoint;	}
  	
  	public int getWinAmount(){ return winAmount; }
  	
  	public int getPlayerHealth(){return playerHealth;}
  	
  	public int getEnemyHealth(){return enemyHealth;}
  	
  	public int getEnemyLevel(){return enemyLevel;}
}
