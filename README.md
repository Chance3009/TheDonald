# The Donald 
## Introduction
This is my first Java project, created during Project J (24/5/2021), where I achieved 1st runner-up. <br/>
The project involves the development of a card game called **'The Donald’**. In addition to meeting the basic requirements, I implemented a GUI for the game rule window and hand card display. This extra effort set my project apart from others, leading to my 1st runner-up position. <br/>
Currently, I'm developing the GUI version of this game. Feel free to check it out [HERE](https://github.com/Chance3009/TheDonald-GUI).

---

## Game Flow
The game begins with a window displaying the game rules. <br/>
Players must enter their names and bid for *Donald*. If no one bids for *Donald* or multiple players bid the same highest amount, the system randomly selects *Donald*. <br/>
After this, *Donald* chooses a color, then selects a teammate by entering the **[COLOR RANK]** of the other player's card.

The system displays information for both teams and initiates Round 1.

Each player has four commands:

1. Show teammate
2. Display current points and winning criteria
3. Display card (visible in both console and pop-up window)
4. Play card (Player enters the [COLOR RANK] of the card to play it)

A player's turn doesn't pass to the next player until they play a card.

The game continues until one team meets the winning criteria. Post-game, users can opt to play again.

**Note: If a player repeatedly enters invalid inputs, the system will skip their action and continue the game. This allows the game to be played automatically by pressing Enter repeatedly.** *(So it is possible to play the game automatically by pressing enter infinitely :P)*

**ATTENTION: PLEASE AVOID PRESSING ENTER TOO RAPIDLY, AS IT MAY LEAD TO ISSUES.**

---
### SPECIAL CASE
In the following scenario:

- Donald color: BLUE
- Donald: Player 3

Player 1 : BLUE C <br/>
Player 2 : BLUE 7 <br/>
Player 3 : BLUE 4 (Activates Donald) <br/>
Player 4 : BLUE 6 

Player 4 will win as his card (BLUE 6) triggered the **Donald Activation perk**.