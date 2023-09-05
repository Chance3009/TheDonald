Hi, I am Ci En!
This is my first Java Project.
At first, I wanted to try to make a GUI for the game but then I realised I didn't have enough time to complete the whole game.
I had my first version of a complete runnable game on 20/5/2021 and improved the program continually by adding some features and removing some redundant codes. 
First submit on 24/5/2021 00:00.
Second submit on 24/5/2021 14:45.

The program starts with a game rule window.
Players are required to enter their name and bid for donald.
If no player bids for donald or there is more than one player that bids the same highest number, the system will choose the donald randomly.
After the donald is chosen, the donald needs to choose a donald color.
Next, donald chooses his/her teammate by entering the [COLOR RANK] of the other player's card.
The system will show the info of both teams and start round 1. 

A player has 4 commands: 
1-Show teammate
2-Show current point and winning criteria 
3-Display card (The card will be shown on both the console and pop-up window)
4-Play card (Player enter the [COLOR RANK] of the card to play the card)
The player's turn won't pass to next player until he/she plays a card. 

The game will keep going on until one of the teams meets the winning criteria.
After the game has ended, the user can choose to play the game again.

(For all the players' actions, if the player keeps entering invalid input until certain times, the system will force the game to continue and skip the player's action)
(So it is possible to play the game automatically by pressing enter infinitely :P)

*ATTENTION*
PLEASE DON'T PRESS ENTER TOO FAST AS IT MAY CAUSE SOME PROBLEM

*SPECIAL CASE*
In the scenario below:

Donald color： BLUE
Donald： Player 3

Player 1 : BLUE C
Player 2 : BLUE 7
Player 3 : BLUE 4 (Activate Donald)
Player 4 : BLUE 6

Player 4 will win as his card (BLUE 6) gained the *Donald Activation perk*