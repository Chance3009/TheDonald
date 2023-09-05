//CHAN CI EN

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Player {
	public String name;
    public int id;
    public int points;
    public List<Card> handCard;
    public String bid;
    public int bidNum;
    public int winningCriteria;
    public Player teammate; 
    public Card myCard;
	public int size;
	public int compareSize;    
	public JFrame myFrame = new JFrame();
	
	JPanel panel = new JPanel();
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);
	
    public Player(int id) {
    	this.id = id;
    }
 
    @Override
    public String toString() {
    	return name; 
    }
    
    // Display dialogue to bid for donald
    public void bidForDonald() {
    	
    	String regex = "^[1-6]";
		String temp = "";
		int count1 = 0;
		int count2 = 0;
		
    	System.out.print(name + ", do you want to call for donald? [Y for yes | N for no] : ");

    	do {
    		// Force player to not to bid if invalid input is entered for >=7 times
    		if (count1 >= 7) {
    			System.out.println("\n......I'll help you to decide! YOU DON'T BID!");
    			bid = "N";
    		} else {
    			bid = scanner.nextLine().trim().toUpperCase();
    		}
    		
    		switch (bid) {
    			case "Y" :
    				if (Game.currentBid == 0) {
    					System.out.print("Enter the number of donald [1-6] : ");
    				} else {
    					System.out.print("Enter the number of donald [" + Game.currentBid + "-6] : ");
    				}
    				Game.bidForDonald.add(this);
    				do {
    					if (count2 >= 7) {
    						System.out.println("\n......I'll help you to decide! YOU DON'T BID!");
    						bid = "N";
    						Game.bidForDonald.remove(this);
    						break;
    					} else {
    						temp = scanner.nextLine().trim();
    					}
    					
    					if (temp.matches(regex) && Integer.parseInt(temp) <= 6 && Integer.parseInt(temp) >= Game.currentBid) {
    						bidNum = Integer.parseInt(temp);
    						Game.currentBid = bidNum;
    					} else {
    						if (Game.currentBid == 0) {
    							System.out.print("Invalid number, please enter a valid number [1-6] : ");
    						} else {
    							System.out.print("Invalid number, please enter a valid number [" + Game.currentBid + "-6] : ");
    						}
    						count2++;
    					}
    				} while (!temp.matches(regex) || Integer.parseInt(temp)>6 || Integer.parseInt(temp) < Game.currentBid);	
    				
    				break;
    			case "N" :
    				break;
    			default :
    				System.out.print("Invalid input, please enter a valid input [Y for yes | N for no] : ");
    				count1++;
    		}
    	}  while(!bid.equals("Y") && !bid.equals("N"));
    }
    
    // Display command dialogue repeatedly until a card is played
    public void fullCommand(HashMap<String, String> numToWord) throws InterruptedException {
    	do {
    		command(numToWord);
    		Thread.sleep(750);
    		System.out.println();
    	} while(size == compareSize);
    	
    	myFrame.setVisible(false);
    }
       
    // Start a new turn and display the command dialogue 
    public void command(HashMap<String, String> numToWord) {
    	
    	String regex = "^[1-4]";
    	String temp;
    	int count = 0;
    	size = handCard.size();
    	compareSize = handCard.size();
    	    	
    	if (id != Game.currentPlayer.id) {
    		System.out.println("Current color : " + Game.currentColor);
    	}
    	
		System.out.println("Donald color : " + Game.donaldColor);
		System.out.println("Donald Activation : " + Game.donaldActivation);
    	System.out.println(name + ", it's your turn!");
    	System.out.println("1. Show your teammate");
    	System.out.println("2. Show your team's current point and winning criteria");
    	System.out.println("3. Display your card");
    	System.out.println("4. Play your card");
    	System.out.print("Enter your command [1-4] : ");
    	
    	do {
    		// Force player to enter play card dialogue if invalid command is entered for >=10 times 
    		if (count >= 10) {
    			temp = "4";
    			System.out.println("\nAHHHHHHHHHH JUST PLAY THE CARD!!!!");
    		} else {
    			temp = scanner.nextLine().trim();
    		}
    		
    		if(temp.matches(regex) && Integer.parseInt(temp)<=4) {
    			switch (temp) {
    				case "1" : 
    					System.out.println("Your teammate is " + teammate);
    					break;	
    				case "2" : 
    					System.out.println("Your team now has " + points +" point(s) and needs to get " + (winningCriteria-points) + " points more to win");
    					break;
    				case "3" :
    					myFrame.setVisible(true);
    					System.out.println(handCard);
    					break;
    				case "4" :
    					playCard(numToWord);
    					compareSize = handCard.size();
    					break;
    			}
    		} else {
    			System.out.print("Invalid input. Please enter a valid command [1-4] : ");
    			count ++;
    		}
    	} while(!temp.matches(regex) || Integer.parseInt(temp)>4);
    }
    
    // Display dialogue to play card
    public void playCard(HashMap<String, String> numToWord) {
    	String str = "";
    	int count = 0;
    	
    	System.out.print("Please enter the [COLOR RANK] of the card you want to play : ");
    	
    	do {	
    		// Choose random card from a list of valid card if count >= 7
    		if(count >= 7) { 
				randomPlay();
			} else {
				str = scanner.nextLine().trim().toUpperCase();
				for (String s : numToWord.keySet()) {
    			str = str.replace(s, numToWord.get(s));
				}
			}
    		
    		if (count >= 7 || (isMyCard(str) && isValidCard())) {
    			// If the player plays the first card in the round, set the color of card he plays as currentColor
    			if (id == Game.currentPlayer.id) {
    				Game.currentColor = myCard.color;
    			}
    			// If the donald play a card with donald color, activate donald
    			if (id == Game.donaldIndex && myCard.color == Game.donaldColor) {
    				Game.donaldActivation = true;
    			}
    			// Assign different value to the card in different situation to make their card comparable
    			if (Game.donaldActivation && myCard.color == Game.donaldColor) {
    				myCard.tempValue+=26;
    			} else if (myCard.color == Game.currentColor) {
    				myCard.tempValue+=13;
    			}
    			
    			handCard.remove(handCard.indexOf(myCard));
    			panel.remove(myCard.label);
    			
    			if (count == 7) {
    				System.out.println("\n...... ENOUGH! I will choose a card for you !!!!!!");
    				System.out.println("Play " + myCard + ", " + name + "!");
    			} else {
    				System.out.println(name + ", you play " + myCard);
    			}
    			
    			break;
    		} else {
    			System.out.print("Invalid card. Please enter the [COLOR RANK] of the card you want to play : ");
    			count++;
    		}
    	} while(!isMyCard(str) || !isValidCard());
    }

    // Check if the card played is owned by the player
    public boolean isMyCard(String str) {
    	//Check the player has the given card or not 
    	for (Card card : handCard) {
    		if (card.toString().equals(str)) {
    			myCard = card;
    			return true;
    		}	
    	}
    	return false;
    }
 
    // Check if player played a valid card 
    public boolean isValidCard() {
    	int validCount = 0;
    	//Check the number of valid card 
    	for (Card card : handCard) {
    		if (card.color == Game.currentColor) {
    			validCount++;
    		}	
    	}
    	// Return true if player played a valid card or doesn't have any valid card
    	return (id == Game.currentPlayer.id || validCount == 0 || (validCount > 0 && myCard.color == Game.currentColor));
   	}
    
    // Play random card 
    public void randomPlay() {
    	List <Card> validList = new ArrayList<Card>();
    	
    	if (id == Game.currentPlayer.id) {
    		myCard = handCard.get(random.nextInt(handCard.size()));
    	} else {
    		for (Card card : handCard) {
    			myCard = card;
    			if (isValidCard()) {
    				validList.add(card);
    			}
    		}
    		myCard = validList.get(random.nextInt(validList.size()));
    	}
    	
    }
    
    // Display player's cards in a pop-up window
    public void initializeFrame() {
    	myFrame.setSize(1280,720);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.setPreferredSize(new Dimension(1280,900));
		panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	
		myFrame.add(scrollPane);
		
		for (Card card : handCard) {
			panel.add(card.label);
		}
    }
}