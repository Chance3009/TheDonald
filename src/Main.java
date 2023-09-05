//CHAN CI EN

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main {
	public static void main(String[] args) {
		String response = "";
		Scanner scanner = new Scanner(System.in);
		HashMap<String, String> numToWord = new HashMap<String, String>();
		
		for (Card.Rank r : Card.ranks) {
			numToWord.put(Integer.toString(r.value), r.name());
		}

		// Display "How to Play" Window
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Candara", Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setForeground(new Color(249,211,26));
		textArea.setBackground(new Color(41,40,38));
		textArea.setMargin(new Insets(10,10,10,10));
		
		try {
			BufferedReader input =  new BufferedReader(new FileReader("GameRule.txt"));
			textArea.read(input, "GameRule");
			input.close();
		}
		catch (Exception e){
			System.out.println("Unable to show game rule correctly, please refer to \"GameRule.txt\" in the game folder.");
		}	
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(1280, 720));
			
		JFrame frame = new JFrame();
		frame.setTitle("How to Play");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.add(scrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try { 
			Thread.sleep(5000);
			System.out.println("OK. Let's start our game now!");	
			
			do { 
				new Game(numToWord);
				frame.dispose();
				System.out.print("Do you want to play again? [Y for yes | N for no] : ");
				response = scanner.nextLine().trim().toUpperCase();
				System.out.println("\n******************************************************************************************************************************************\n");
			} while (response.equals("Y"));
	    	
			scanner.close();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Something wrong with the game, please try on another console.");
		}
		
		if (response.equals("N")) {
			System.out.println("Have a nice day.");
			System.out.println("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee!");
			System.exit(0);
		} else {
			System.out.println("I dunno what you want but I decide to quit.");
			System.out.println(":P");
			System.exit(0);
		}
	}
}