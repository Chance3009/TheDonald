//CHAN CI EN

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card implements Comparable<Card> {
	public enum Color {
		RED, BLUE, GREEN, YELLOW;
	}
	
	public enum Rank {
		ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), A(11), B(12), C(13);
		int value;
		private Rank(int i) {
			value = i;
		}
	}

	public final Color color;
	public final Rank rank;
	public final int value;
	
	public int tempValue;	
	public JLabel label;
	
	public static Color[] colors = Card.Color.values();
	public static Rank[] ranks = Card.Rank.values();
	public static List<Card> cards;
	
	public Card(final Color color,final Rank rank,int value) {
		this.color = color;
		this.rank = rank;
		this.value = value;
		this.tempValue = value;
		this.label = new JLabel(new ImageIcon("img/" + color + " " + rank + ".jpg"));
	}
	
	@Override
	public String toString() {
		return color + " " + rank;
	}

	@Override
    public int compareTo(Card card) {
		if (this.color.compareTo(card.color) == 0) {
			if (this.value > card.value) {
            	return 1;
            } else {
            	return -1;
            } 
		} else if (this.color.compareTo(card.color)<0) {
			return -1;
		} else {
			return 1;
		}      
    }

	// Initialize a new card deck
	public static void newCardDeck() {
		cards = new ArrayList<Card>();
		
		for (Color color : colors) {
			for (Rank rank : ranks) {
				cards.add(new Card(color,rank,rank.value));
			}
		}
	}

	public static void shuffle(List<Card> cards) {
		Collections.shuffle(cards);
	}
}

