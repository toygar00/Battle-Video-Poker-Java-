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
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;


public class WindowGUI {
	
	JButton cardImages[] = new JButton[5];
	Boolean heldCards[] = new Boolean[5];
	JLabel heldLabels[] = new JLabel[5];
	
	
	static JFrame frmVideoPoker;
	private JTextField textField;
	private JLabel resultLabel, betAmountLabel, winAmountLabel, currentCashLabel, enemyHealthLabel, playerHealthLabel, enemyNameLabel;
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
		frmVideoPoker.getContentPane().setBackground(Color.DARK_GRAY);
		frmVideoPoker.getContentPane().setForeground(Color.WHITE);
		frmVideoPoker.setBounds(100, 100, 956, 573);
		frmVideoPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVideoPoker.getContentPane().setLayout(null);
		
		Arrays.fill(heldCards, Boolean.FALSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 11, 500, 513);
		frmVideoPoker.getContentPane().add(panel);
		panel.setLayout(null);
		
		deal = new JButton("DEAL");
		deal.setBounds(391, 416, 90, 35);
		panel.add(deal);
		deal.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		betMaxButton = new JButton("Bet Max");
		betMaxButton.setBounds(291, 416, 90, 35);
		panel.add(betMaxButton);
		betMaxButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		currentCashLabel = new JLabel("500 Action Points");
		currentCashLabel.setBounds(291, 369, 190, 36);
		panel.add(currentCashLabel);
		currentCashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentCashLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton card1 = new JButton("");
		card1.setBounds(31, 253, 80, 105);
		panel.add(card1);
		card1.setBackground(new Color(128, 128, 128));
		card1.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		
		cardImages[0] = card1;
		
		JButton card2 = new JButton("");
		card2.setBounds(117, 253, 80, 105);
		panel.add(card2);
		card2.setBackground(new Color(128, 128, 128));
		card2.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		cardImages[1] = card2;
		
		JButton card3 = new JButton("");
		card3.setBounds(207, 253, 80, 105);
		panel.add(card3);
		card3.setBackground(new Color(128, 128, 128));
		card3.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		cardImages[2] = card3;
		
		JButton card4 = new JButton("");
		card4.setBounds(293, 253, 80, 105);
		panel.add(card4);
		card4.setBackground(new Color(128, 128, 128));
		card4.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		cardImages[3] = card4;
		
		JButton card5 = new JButton("");
		card5.setBounds(376, 253, 80, 105);
		panel.add(card5);
		card5.setBackground(new Color(128, 128, 128));
		card5.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/BlueBack.png")));
		cardImages[4] = card5;
		
				resultLabel = new JLabel("Result");
				resultLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				resultLabel.setBounds(10, 192, 471, 23);
				panel.add(resultLabel);
				resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel held1 = new JLabel("Held");
				held1.setBounds(31, 219, 80, 23);
				panel.add(held1);
				held1.setForeground(SystemColor.inactiveCaption);
				held1.setFont(new Font("Tahoma", Font.BOLD, 12));
				held1.setHorizontalAlignment(SwingConstants.CENTER);
				heldLabels[0] = held1;
				
				JLabel held2 = new JLabel("Held");
				held2.setBounds(117, 219, 80, 23);
				panel.add(held2);
				held2.setForeground(SystemColor.inactiveCaption);
				held2.setFont(new Font("Tahoma", Font.BOLD, 12));
				held2.setHorizontalAlignment(SwingConstants.CENTER);
				heldLabels[1] = held2;
				
				JLabel held4 = new JLabel("Held");
				held4.setBounds(291, 219, 82, 23);
				panel.add(held4);
				held4.setForeground(SystemColor.inactiveCaption);
				held4.setFont(new Font("Tahoma", Font.BOLD, 12));
				held4.setHorizontalAlignment(SwingConstants.CENTER);
				heldLabels[3] = held4;
				
				JLabel held3 = new JLabel("Held");
				held3.setBounds(207, 219, 80, 23);
				panel.add(held3);
				held3.setForeground(SystemColor.inactiveCaption);
				held3.setFont(new Font("Tahoma", Font.BOLD, 12));
				held3.setHorizontalAlignment(SwingConstants.CENTER);
				heldLabels[2] = held3;
				
				JLabel held5 = new JLabel("Held");
				held5.setBounds(376, 219, 80, 23);
				panel.add(held5);
				held5.setForeground(SystemColor.inactiveCaption);
				held5.setFont(new Font("Tahoma", Font.BOLD, 12));
				held5.setHorizontalAlignment(SwingConstants.CENTER);
				heldLabels[4] = held5;
				
					decreaseBet = new JButton("-");
					decreaseBet.setBounds(10, 416, 45, 35);
					panel.add(decreaseBet);
					decreaseBet.setFont(new Font("Tahoma", Font.BOLD, 12));
					betAmountLabel = new JLabel("Bet 1");
					betAmountLabel.setBounds(59, 414, 100, 36);
					panel.add(betAmountLabel);
					
					betAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
					betAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					winAmountLabel = new JLabel("Win: 0");
					winAmountLabel.setEnabled(false);
					winAmountLabel.setBounds(10, 369, 101, 36);
					panel.add(winAmountLabel);
					
					winAmountLabel.setHorizontalAlignment(SwingConstants.LEFT);
					winAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setEnabled(false);
					table.setBounds(10, 11, 478, 176);
					panel.add(table);
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
							{"Jacks Pair or Better", "1"},
							{"No Jack Pair", "0"},
							{"Other", "Damage to Player"},
						},
						new String[] {
							"Card", "Multiplier"
						}
					));
					
					increaseBet = new JButton("+");
					increaseBet.setBounds(164, 416, 45, 35);
					panel.add(increaseBet);
					increaseBet.setFont(new Font("Tahoma", Font.BOLD, 12));
					
					JPanel panel_1 = new JPanel();
					panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_1.setBackground(Color.GRAY);
					panel_1.setBounds(520, 11, 410, 281);
					frmVideoPoker.getContentPane().add(panel_1);
					panel_1.setLayout(null);
					
					enemyNameLabel = new JLabel("Level 1 Enemy");
					enemyNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
					enemyNameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					enemyNameLabel.setBounds(283, 11, 117, 36);
					panel_1.add(enemyNameLabel);
					
					JLabel playerNameLabel = new JLabel("Player");
					playerNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
					playerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					playerNameLabel.setBounds(10, 11, 117, 36);
					panel_1.add(playerNameLabel);
					
					playerHealthLabel = new JLabel("Health: 300");
					playerHealthLabel.setForeground(new Color(128, 0, 0));
					playerHealthLabel.setHorizontalAlignment(SwingConstants.LEFT);
					playerHealthLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					playerHealthLabel.setBounds(10, 178, 117, 36);
					panel_1.add(playerHealthLabel);
					
					enemyHealthLabel = new JLabel("Health: 300");
					enemyHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
					enemyHealthLabel.setForeground(new Color(128, 0, 0));
					enemyHealthLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
					enemyHealthLabel.setBounds(283, 178, 117, 36);
					panel_1.add(enemyHealthLabel);
					
					JLabel playerImage = new JLabel("");
					playerImage.setHorizontalAlignment(SwingConstants.CENTER);
					playerImage.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/c1.png")));
					playerImage.setBounds(10, 44, 96, 123);
					panel_1.add(playerImage);
					
					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(WindowGUI.class.getResource("/cards/d2.png")));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setBounds(304, 44, 96, 123);
					panel_1.add(label);
					
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_2.setBackground(Color.GRAY);
					panel_2.setBounds(520, 303, 410, 221);
					frmVideoPoker.getContentPane().add(panel_2);
		
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
			currentCashLabel.setText(poker.getCredits() + " Action Points");
			deal.setText("DRAW");
			decreaseBet.setEnabled(false);
			increaseBet.setEnabled(false);
			betMaxButton.setEnabled(false);
		}
		else{
			poker.getResult();
			winAmountLabel.setText("Win: " + poker.getWinAmount());
			currentCashLabel.setText(poker.getCredits() + " Action Points");
			resetHeldCards();
			deal.setText("DEAL");
			decreaseBet.setEnabled(true);
			increaseBet.setEnabled(true);
			betMaxButton.setEnabled(true);
			
			enemyNameLabel.setText("Level "+ poker.getEnemyLevel() + " Enemy");
			playerHealthLabel.setText("Health: " + poker.getPlayerHealth());
			enemyHealthLabel.setText("Health: " + poker.getEnemyHealth());
			betAmountLabel.setText(""+poker.getBetAmount());
			updateResultsTable();
		}
			
		
		
		String[] imgString = poker.getCardsLocations();
		
		for(int i = 0;i<imgString.length;i++)
	  	{
	  		cardImages[i].setIcon(new ImageIcon(WindowGUI.class.getResource(imgString[i])));
	  	}
	  	resultLabel.setText(poker.getResultString());
	}
	


	
	//VERY INEFFICENT CODE
	private void handleButtons()
	{
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
