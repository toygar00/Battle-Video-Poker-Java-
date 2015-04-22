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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class WindowGUI {
	
	JButton cardImages[] = new JButton[5];
	Boolean heldCards[] = new Boolean[5];
	JLabel heldLabels[] = new JLabel[5];
	
	
	static JFrame frmVideoPoker;
	private JTextField textField;
	private JLabel resultLabel, betAmountLabel, winAmountLabel, currentCashLabel;
	private JButton increaseBet, decreaseBet, betMaxButton, deal;
	
	private enum Status { dealing, helding };
	private Status gameStatus = Status.dealing;
	
	Deck deck;
  	Hand hand;
  	
  	private Poker poker;
  	private JTable table;

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
		poker = new Poker();
		
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
		frmVideoPoker.getContentPane().setBackground(new Color(105, 105, 105));
		frmVideoPoker.getContentPane().setForeground(Color.WHITE);
		frmVideoPoker.setBounds(100, 100, 514, 500);
		frmVideoPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVideoPoker.getContentPane().setLayout(null);
		
		Arrays.fill(heldCards, Boolean.FALSE);
		
		deal = new JButton("DEAL");
		deal.setFont(new Font("Tahoma", Font.BOLD, 12));
		deal.setBounds(391, 416, 90, 35);
		frmVideoPoker.getContentPane().add(deal);
		
		betMaxButton = new JButton("Bet Max");
		betMaxButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		betMaxButton.setBounds(219, 416, 90, 35);
		frmVideoPoker.getContentPane().add(betMaxButton);
		
		currentCashLabel = new JLabel("500 $");
		currentCashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentCashLabel.setBounds(291, 369, 190, 36);
		currentCashLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmVideoPoker.getContentPane().add(currentCashLabel);
		
		JButton card1 = new JButton("");
		card1.setBackground(new Color(128, 128, 128));
		card1.setBounds(31, 253, 80, 105);
		card1.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		frmVideoPoker.getContentPane().add(card1);
		
		JButton card2 = new JButton("");
		card2.setBackground(new Color(128, 128, 128));
		card2.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card2.setBounds(117, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card2);
		
		JButton card3 = new JButton("");
		card3.setBackground(new Color(128, 128, 128));
		card3.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card3.setBounds(207, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card3);
		
		JButton card4 = new JButton("");
		card4.setBackground(new Color(128, 128, 128));
		card4.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		card4.setBounds(293, 253, 80, 105);
		frmVideoPoker.getContentPane().add(card4);
		
		JButton card5 = new JButton("");
		card5.setBackground(new Color(128, 128, 128));
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
	
		decreaseBet = new JButton("-");
		decreaseBet.setFont(new Font("Tahoma", Font.BOLD, 12));
		decreaseBet.setBounds(10, 416, 45, 35);
		frmVideoPoker.getContentPane().add(decreaseBet);
		betAmountLabel = new JLabel("Bet 1");
		
		betAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		betAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		betAmountLabel.setBounds(59, 414, 100, 36);
		frmVideoPoker.getContentPane().add(betAmountLabel);
		winAmountLabel = new JLabel("Win: 0");
		
		winAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		winAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		winAmountLabel.setBounds(10, 369, 101, 36);
		frmVideoPoker.getContentPane().add(winAmountLabel);
		
		cardImages[0] = card1;cardImages[1] = card2;cardImages[2] = card3;cardImages[3] = card4;cardImages[4] = card5;
		heldLabels[0] = held1;heldLabels[1] = held2;heldLabels[2] = held3;heldLabels[3] = held4;heldLabels[4] = held5;
		
		increaseBet = new JButton("+");
		increaseBet.setFont(new Font("Tahoma", Font.BOLD, 12));
		increaseBet.setBounds(164, 416, 45, 35);
		frmVideoPoker.getContentPane().add(increaseBet);
		
		table = new JTable();
		table.setBackground(new Color(211, 211, 211));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Royal Flush", "250"},
				{"Straight Flush", "50"},
				{"4 of a Kind", "25"},
				{"Full House", "9"},
				{"Flush", "6"},
				{"Straight", "4"},
				{"3 of a Kind", "3"},
				{"Two Pair", "2"},
				{"Jacks or Better", "1"},
			},
			new String[] {
				"Card", "Multiplier"
			}
		));
		
		
		
		table.setBounds(10, 11, 478, 144);
		frmVideoPoker.getContentPane().add(table);
		
		//BUTTONS
		deal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealButton();
			}
		});
		
	}
	
	
	private void dealButton()
	{
	
		if( poker.isDealing()){
			resetHeldCards();
			poker.dealCards();
			winAmountLabel.setText("Win: 0");
			currentCashLabel.setText(poker.getCredits() + " $");
			deal.setText("DRAW");
			decreaseBet.setEnabled(false);
			increaseBet.setEnabled(false);
			betMaxButton.setEnabled(false);
		}
		else{
			poker.getResult();
			winAmountLabel.setText("Win: " + poker.getWinAmount());
			currentCashLabel.setText(poker.getCredits() + " $");
			resetHeldCards();
			deal.setText("DEAL");
			decreaseBet.setEnabled(true);
			increaseBet.setEnabled(true);
			betMaxButton.setEnabled(true);
		}
			
		
		
		String[] imgString = poker.getCardsLocations();
		
		for(int i = 0;i<imgString.length;i++)
	  	{
	  		cardImages[i].setIcon(new ImageIcon(WindowGUI.class.getResource(imgString[i])));
	  	}
	  	resultLabel.setText(poker.getResultString());
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
		
	}
	
	//VERY INEFFICENT CODE
	private void handleButtons()
	{
		
		int i = 0;
		cardImages[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.toogleHeldCard(0);
				if(poker.getHeldCard(0))heldLabels[0].setForeground(Color.BLACK);
				else heldLabels[0].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.toogleHeldCard(1);
				if(poker.getHeldCard(1))heldLabels[1].setForeground(Color.BLACK);
				else heldLabels[1].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.toogleHeldCard(2);
				if(poker.getHeldCard(2))heldLabels[2].setForeground(Color.BLACK);
				else heldLabels[2].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.toogleHeldCard(3);
				if(poker.getHeldCard(3))heldLabels[3].setForeground(Color.BLACK);
				else heldLabels[3].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		cardImages[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.toogleHeldCard(4);
				if(poker.getHeldCard(4))heldLabels[4].setForeground(Color.BLACK);
				else heldLabels[4].setForeground(SystemColor.inactiveCaption);
			}
		});
		
		decreaseBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.addToBet(-1);
				betAmountLabel.setText("Bet " + poker.getBetAmount() );
				updateResultsTable();
			}
		});
		
		increaseBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.addToBet(+1);
				betAmountLabel.setText("Bet " + poker.getBetAmount() );
				updateResultsTable();
			}
		});
		
		betMaxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poker.betMax();
				betAmountLabel.setText("Bet " + poker.getBetAmount() );
				updateResultsTable();
			}
		});
		
	}
	
	private void resetHeldCards()
	{
		poker.initHeldCards();
		for(int i = 0;i<heldCards.length;i++)
			heldLabels[i].setForeground(SystemColor.inactiveCaption);
	}
	
	private void updateResultsTable()
	{
		table.getModel().setValueAt(poker.getBetAmount() * 250, 0, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 50, 1, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 25, 2, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 9, 3, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 6, 4, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 4, 5, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 3, 6, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 2, 7, 1);
		table.getModel().setValueAt(poker.getBetAmount() * 1, 8, 1);
	}
	

}
