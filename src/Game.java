//CHAN CI EN

import java.util.*;

public class Game {
	public static int currentBid;
	public static List<Player> bidForDonald;
	public static int donaldNumber;
	public static int donaldIndex;
	public static Card.Color donaldColor;
	public static Card.Color currentColor;
	public static Player currentPlayer;
	public static boolean donaldActivation;
	public static int round;
	
	public List<Player> players; 
	public List<Player> teamDonald;
	public List<Player> teamNonDonald;
	public Player player1;
	public Player player2;
	public Player player3;
	public Player player4;
	public int donaldTeammateIndex;
	
	Scanner scanner = new Scanner(System.in);
	Random random = new Random();
	
	// Start the game
	public Game(HashMap<String, String> numToWord) throws InterruptedException {
		newGame();
		Thread.sleep(750);
		callForDonald();
		Thread.sleep(750);
		chooseDonaldColor();
		Thread.sleep(750);
		donaldTeammateIndex = chooseTeammate(numToWord);
		Thread.sleep(1000);
		showInfo();
		Thread.sleep(2200);
		while (teamDonald.get(0).points<teamDonald.get(0).winningCriteria && teamNonDonald.get(0).points<teamNonDonald.get(0).winningCriteria) {
    		newRound(numToWord);
    	}
		endGame();

	}
	
	// Initialize the game
	public void newGame() {
		currentBid = 0;
		round = 0;
		donaldActivation = false;
		
		Card.newCardDeck();
    	Card.shuffle(Card.cards);
    	
    	players = new ArrayList<Player>();
    	bidForDonald = new ArrayList<Player>();
    	teamDonald = new ArrayList<Player>();
    	teamNonDonald = new ArrayList<Player>();
    	
		player1 = new Player(0);
    	player2 = new Player(1);
    	player3 = new Player(2);
    	player4 = new Player(3);
    	
		System.out.print("Player 1, please enter your name : ");
    	player1.name = scanner.nextLine().trim();
    	System.out.print("Player 2, please enter your name : ");
    	player2.name = scanner.nextLine().trim();
    	System.out.print("Player 3, please enter your name : ");
    	player3.name = scanner.nextLine().trim();
    	System.out.print("Player 4, please enter your name : ");
    	player4.name = scanner.nextLine().trim();
    	
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	players.add(player4);
    	  	
    	player1.handCard = new ArrayList<Card>(Card.cards.subList(0, 13));
    	player2.handCard = new ArrayList<Card>(Card.cards.subList(13, 26));
    	player3.handCard = new ArrayList<Card>(Card.cards.subList(26, 39));
    	player4.handCard = new ArrayList<Card>(Card.cards.subList(39, 52));
    	
    	Collections.sort(player1.handCard);
    	Collections.sort(player2.handCard);
    	Collections.sort(player3.handCard);
    	Collections.sort(player4.handCard);
    	
    	player1.initializeFrame();
    	player2.initializeFrame();
    	player3.initializeFrame();
    	player4.initializeFrame();
    	
    	System.out.println("\n******************************************************************************************************************************************\n");
	}	
 
	// Start the section: Call for donald 
	public void callForDonald() {
		player1.bidForDonald();
		player2.bidForDonald();
		player3.bidForDonald();
		player4.bidForDonald();
		
		if (bidForDonald.isEmpty()) {	
			// Choose donald and donald number randomly if no player bid for donald
			donaldIndex = random.nextInt(players.size());
			currentPlayer = players.get(donaldIndex);		
			currentPlayer.bidNum = random.nextInt(6)+1;
			teamDonald.add(currentPlayer);
			
			System.out.println("\nSince nobody calls for donald, I will choose the donald randomly.");
			System.out.println(currentPlayer + ", you are the donald !");
			System.out.println("The random donald number is " + currentPlayer.bidNum + "");
		} else {
			int maxPlayer = 0;
			// Assign the index of the rightmost player that call for donald as donaldIndex
			for (int i=players.size()-1; i>=0; i--) {
				if (players.get(i).bid.equals("Y")) {
					donaldIndex = i;
					break;
				}
			}
			// Determine the number of player that bid the highest number
			for (int i=donaldIndex; i>=0; i--) {
				if (players.get(i).bidNum == currentBid) {
					maxPlayer++;
				}
			}	
			// Choose donald randomly among the players that bid the highest number if >1 player bid the highest number
			if (maxPlayer != 1) {
				donaldIndex = bidForDonald.size()-random.nextInt(maxPlayer)-1;
				currentPlayer = players.get(donaldIndex);
				teamDonald.add(currentPlayer);
				System.out.println(currentPlayer + ", you are the donald !");	
			} else {
				currentPlayer = players.get(donaldIndex);
				teamDonald.add(currentPlayer);
				System.out.println(currentPlayer + ", you are the donald !");
				
			}
		} 
		
		donaldNumber = currentPlayer.bidNum;
		System.out.println("\n******************************************************************************************************************************************\n");
	}	
	
	// Start the section: Choose donald color 
    public void chooseDonaldColor() {
    	String[] tempList = {"RED", "BLUE", "GREEN", "YELLOW", "NO DONALD"};
    	String temp;
    	int count = 0;
    	
    	System.out.print(currentPlayer + ", please choose a donald color [RED, BLUE, GREEN, YELLOW, NO DONALD] :  ");

    	do {
    		// Choose donald color randomly if invalid input is entered for >=7 times
    		if (count >= 7) {
    			System.out.println("\n...... STOP! I will choose a donald color for you .......");
    			temp = tempList[random.nextInt(5)];
    		} else {
    			temp = scanner.nextLine().trim().toUpperCase();
    		}
    		
    		if (!Arrays.asList(tempList).contains(temp)) {
    			System.out.print("Invalid input. Please enter a valid donald color [RED, BLUE, GREEN, YELLOW, NO DONALD] : ");
    			count++;
    		}
    		
    	} while(!Arrays.asList(tempList).contains(temp));
    	
    	for (Card.Color color : Card.colors) {
    		if (temp.equals("NO DONALD")) {
    			donaldColor = null;
    		} else if (temp.equals(color.name())) {
    			donaldColor = color;
    		}
    	}
    		
    	System.out.println("The donald color is " + donaldColor +"\n");
    }
    
    // Start the section: Choose teammate
    public int chooseTeammate(HashMap<String, String> numToWord) {
    	System.out.println(player1 + "'s cards : " + player1.handCard);
    	System.out.println(player2 + "'s cards : " + player2.handCard);
    	System.out.println(player3 + "'s cards : " + player3.handCard);
    	System.out.println(player4 + "'s cards : " + player4.handCard);
    	
    	System.out.print(currentPlayer + ", please choose your teammate by entering [COLOR RANK] of other's card : ");
    	String cardName = scanner.nextLine().trim().toUpperCase();

    	for (String s : numToWord.keySet()) {
			cardName = cardName.replace(s, numToWord.get(s));
		}
    	// Return the index of a player if the input matches other players' cards
    	for (Player player : players) {
    		for (Card card : player.handCard) {
    			if ((card.toString().equals(cardName)) && !currentPlayer.equals(player)) {
    		    	System.out.println("\n******************************************************************************************************************************************\n");
    				return player.id;	
    			}
    		}
    	}
    	// Choose teammate for donald randomly if the input doesn't match other players' cards
    	System.out.println("Invalid input. Never mind, I will choose your teammate randomly.");
    	System.out.println("\n******************************************************************************************************************************************\n");
    	return ((donaldIndex+random.nextInt(3)+1) % 4);
    	
    }   	
    	
    // Start the section: Show info	
    public void showInfo() {
    	teamDonald.add(players.get(donaldTeammateIndex));
    	// Add players to their respective team
    	for (Player player : players) {
    		if (!teamDonald.contains(player)) {
    			teamNonDonald.add(player);
    		}
    	}
    	// Set teammate
    	teamDonald.get(0).teammate = teamDonald.get(1);
    	teamDonald.get(1).teammate = teamDonald.get(0);
    	teamNonDonald.get(0).teammate = teamNonDonald.get(1);
    	teamNonDonald.get(1).teammate = teamNonDonald.get(0);
    	// Set winning requirement
    	teamDonald.get(0).winningCriteria = 6 + donaldNumber;
    	teamDonald.get(1).winningCriteria = 6 + donaldNumber;
    	teamNonDonald.get(0).winningCriteria = 8 - donaldNumber;
    	teamNonDonald.get(1).winningCriteria = 8 - donaldNumber;
    	// Show team member and winning requirement
    	System.out.println("Team Donald : " + teamDonald);
    	System.out.println("Team NonDonald : " + teamNonDonald);
    	System.out.println("Team Donald needs to get " + teamDonald.get(0).winningCriteria + " points to win");
    	System.out.println("Team NonDonald needs to get " + teamNonDonald.get(0).winningCriteria + " points to win");
    	System.out.println("\n******************************************************************************************************************************************\n");

    }

    // Start a new round
    public void newRound(HashMap<String, String> numToWord) throws InterruptedException{
		round++;
		int index = players.indexOf(currentPlayer);
		System.out.println("This is round " + round + "\n");
		currentPlayer.fullCommand(numToWord);
		players.get((index+1)%4).fullCommand(numToWord);
		players.get((index+2)%4).fullCommand(numToWord);
		players.get((index+3)%4).fullCommand(numToWord);
		calculateScore();
		Thread.sleep(1000);
	}    	
 
    // Determine winner and calculate score
	public void calculateScore() {
		//A list that contains the value of the card played by players in current round
		List<Integer> cardValue = Arrays.asList(players.get(0).myCard.tempValue, players.get(1).myCard.tempValue, 
												players.get(2).myCard.tempValue, players.get(3).myCard.tempValue);
		int max = Collections.max(cardValue);				// Determine the highest value of card
		int maxIndex = cardValue.indexOf(max);				// Index of the player that played the card with highest value in current round
		currentPlayer = players.get(maxIndex);				// Set the winner of current round be the first player to play in the next round
		
		// Add a point to the team where the winner came from
		if (teamDonald.contains(currentPlayer)) {
			teamDonald.get(0).points++;
			teamDonald.get(1).points++;
		} else {
			teamNonDonald.get(0).points++;
			teamNonDonald.get(1).points++;
		}
		
		System.out.println(currentPlayer + " is the winner of this round!");
		System.out.println("\n******************************************************************************************************************************************\n");
	}
	
	// Display result of the game
    public void endGame(){
    	
    	System.out.println("Game ended!");
    	System.out.println("Congratulations!");
    	if (teamDonald.get(0).points==teamDonald.get(0).winningCriteria) {
    		System.out.println(teamDonald.get(0) + " and " + teamDonald.get(1) + " win the game!");
    	} else {
    		System.out.println(teamNonDonald.get(0) + " and " + teamNonDonald.get(1) + " win the game!");
    	}

    }	
}