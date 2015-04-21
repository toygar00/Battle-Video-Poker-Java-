import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.Arrays;


public class WindowGUI {
	
	JButton cardImages[] = new JButton[5];
	Boolean heldCards[] = new Boolean[5];
	JLabel heldLabels[] = new JLabel[5];
	
	
	JButton deal;
	static JFrame frmVideoPoker;
	private JTextField textField;
	private JLabel resultLabel;
	
	private enum Status { dealing, helding };
	private Status gameStatus = Status.dealing;
	
	Deck deck;
  	Hand hand;
  	
  	private Poker poker;

  	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowGUI window = new WindowGUI();
					window.frmVideoPoker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public WindowGUI() {
		initialize();
		handleButtons();
		
	}

	private void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmVideoPoker.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frmVideoPoker = new JFrame();
		frmVideoPoker.setTitle("Video Poker");
		frmVideoPoker.setBackground(Color.BLACK);
		frmVideoPoker.getContentPane().setBackground(SystemColor.activeCaption);
		frmVideoPoker.getContentPane().setForeground(Color.WHITE);
		frmVideoPoker.setBounds(100, 100, 514, 500);
		frmVideoPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVideoPoker.getContentPane().setLayout(null);
		
		Arrays.fill(heldCards, Boolean.FALSE);
		
		JButton deal = new JButton("DEAL");
		deal.setFont(new Font("Tahoma", Font.BOLD, 12));
		deal.setBounds(391, 416, 90, 35);
		frmVideoPoker.getContentPane().add(deal);
		
		JLabel rules = new JLabel("RULES");
		rules.setVerticalAlignment(SwingConstants.TOP);
		rules.setBounds(10, 11, 471, 175);
		frmVideoPoker.getContentPane().add(rules);
		
		JButton betMaxButton = new JButton("Bet Max");
		betMaxButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		betMaxButton.setBounds(291, 416, 90, 35);
		frmVideoPoker.getContentPane().add(betMaxButton);
		
		JLabel currentCash = new JLabel("500 Credits");
		currentCash.setHorizontalAlignment(SwingConstants.RIGHT);
		currentCash.setBounds(291, 369, 190, 36);
		currentCash.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmVideoPoker.getContentPane().add(currentCash);
		
		JButton card1 = new JButton("");
		card1.setBounds(31, 253, 80, 105);
		card1.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		frmVideoPoker.getContentPane().add(card1);
		
		JButton card2 = new JButton("");
		card2.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card2.setBounds(117, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card2);
		
		JButton card3 = new JButton("");
		card3.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card3.setBounds(207, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card3);
		
		JButton card4 = new JButton("");
		card4.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card4.setBounds(293, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card4);
		
		JButton card5 = new JButton("");
		card5.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card5.setBounds(376, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card5);

		resultLabel = new JLabel("Result");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(159, 185, 184, 23);
		frmVideoPoker.getContentPane().add(resultLabel);
		
		JLabel held1 = new JLabel("Held");
		held1.setForeground(SystemColor.inactiveCaption);
		held1.setFont(new Font("Tahoma", Font.BOLD, 12));
		held1.setHorizontalAlignment(SwingConstants.CENTER);
		held1.setBounds(31, 219, 80, 23);
		frmVideoPoker.getContentPane().add(held1);
		
		JLabel held2 = new JLabel("Held");
		held2.setForeground(SystemColor.inactiveCaption);
		held2.setFont(new Font("Tahoma", Font.BOLD, 12));
		held2.setHorizontalAlignment(SwingConstants.CENTER);
		held2.setBounds(117, 219, 80, 23);
		frmVideoPoker.getContentPane().add(held2);
		
		JLabel held4 = new JLabel("Held");
		held4.setForeground(SystemColor.inactiveCaption);
		held4.setFont(new Font("Tahoma", Font.BOLD, 12));
		held4.setHorizontalAlignment(SwingConstants.CENTER);
		held4.setBounds(291, 219, 82, 23);
		frmVideoPoker.getContentPane().add(held4);
		
		JLabel held3 = new JLabel("Held");
		held3.setForeground(SystemColor.inactiveCaption);
		held3.setFont(new Font("Tahoma", Font.BOLD, 12));
		held3.setHorizontalAlignment(SwingConstants.CENTER);
		held3.setBounds(207, 219, 80, 23);
		frmVideoPoker.getContentPane().add(held3);
		
		JLabel held5 = new JLabel("Held");
		held5.setForeground(SystemColor.inactiveCaption);
		held5.setFont(new Font("Tahoma", Font.BOLD, 12));
		held5.setHorizontalAlignment(SwingConstants.CENTER);
		held5.setBounds(376, 219, 80, 23);
		frmVideoPoker.getContentPane().add(held5);
	
		JButton betOneButton = new JButton("Bet 1");
		betOneButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		betOneButton.setBounds(191, 416, 90, 35);
		frmVideoPoker.getContentPane().add(betOneButton);
		
		JButton coinTypeButton = new JButton("25 Cent");
		coinTypeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		coinTypeButton.setBounds(191, 371, 90, 35);
		frmVideoPoker.getContentPane().add(coinTypeButton);
		JLabel betAmountLabel = new JLabel("Bet 1");
		
		betAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		betAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		betAmountLabel.setBounds(127, 369, 54, 36);
		frmVideoPoker.getContentPane().add(betAmountLabel);
		JLabel winAmountLabel = new JLabel("Win 125");
		
		winAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		winAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		winAmountLabel.setBounds(10, 369, 101, 36);
		frmVideoPoker.getContentPane().add(winAmountLabel);
		
		JButton helpButton = new JButton("HELP");
		helpButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		helpButton.setBounds(10, 416, 90, 35);
		frmVideoPoker.getContentPane().add(helpButton);
		
		cardImages[0] = card1;cardImages[1] = card2;cardImages[2] = card3;cardImages[3] = card4;cardImages[4] = card5;
		heldLabels[0] = held1;heldLabels[1] = held2;heldLabels[2] = held3;heldLabels[3] = held4;heldLabels[4] = held5;
		
		//BUTTONS
		deal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameStatus == Status.dealing)
					getHand();
				else if(gameStatus == Status.helding)
					handleHelding();
			}
		});
		
	}
	
	private void notDealt()
	{
		for(int i = 0;i<cardImages.length;i++)
	  	{
	  		//cardImages[i].setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
	  		heldLabels[i].setForeground(SystemColor.inactiveCaption);
	  		heldCards[i] = false;
	  	}
	}
	
	private void getHand()
	{
		
		  	 deck= new Deck();
		  	hand= new Hand(deck);
		  	 hand.display(); //show the summary of the hand, e.g. "full house"
		  	 hand.displayAll(); //look at all the individual cards in the hand		 
		  	 
		String[] imgString =hand.getHandImages();
		  	 
		  	for(int i = 0;i<imgString.length;i++)
		  	{
		  		cardImages[i].setIcon(new ImageIcon(WindowGUI.class.getResource(imgString[i])));
		  	}
		  	resultLabel.setText(hand.getResult());
		 
		  	gameStatus = Status.helding;
	}
	
	private void handleHelding()
	{
		for(int i = 0;i<heldCards.length;i++)
	  	{
	  	//	cardImages[i].setIcon(new ImageIcon(WindowGUI.class.getResource(imgString[i])));
			if(heldCards[i])
			{
				
			}
	  	}
		gameStatus = Status.dealing;
	}
	
	//VERY INEFFICENT CODE
	private void handleButtons()
	{
		
		int i = 0;
		cardImages[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heldCards[0] = !heldCards[0];
				if(heldCards[0])heldLabels[0].setForeground(Color.BLACK);
				else heldLabels[0].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heldCards[1] = !heldCards[1];
				if(heldCards[1])heldLabels[1].setForeground(Color.BLACK);
				else heldLabels[1].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heldCards[2] = !heldCards[2];
				if(heldCards[2])heldLabels[2].setForeground(Color.BLACK);
				else heldLabels[2].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heldCards[3] = !heldCards[3];
				if(heldCards[3])heldLabels[3].setForeground(Color.BLACK);
				else heldLabels[3].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heldCards[4] = !heldCards[4];
				if(heldCards[4])heldLabels[4].setForeground(Color.BLACK);
				else heldLabels[4].setForeground(SystemColor.inactiveCaption);
			}
		});
		
	}
}
