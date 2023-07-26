/* Matthew Huang
	 * 05/24/2023
	 * This code runs a game of Monopoly Deal. It allows for one player to play, using both the console and a file to store game information.
	 */

// Importing libraries to properly run the code.
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
	
public class FinalProjectMonopolyDealHuangMatthew {
    static Scanner sc = new Scanner(System.in); // Initializing static Scanner to use throughout the code.
    
    // Creating static variables to help reference Strings and numbers and compare them.
    static String [] deckCardNames = { 
            /* rent cards (indexes 0-5)*/ "(Brown/Light Blue) Property Rent", "(Pink/Orange) Property Rent", "(Red/Yellow) Property Rent", "(Green/Blue) Property Rent", "(Railroad/Utilities) Property Rent", "Multi-Colour Rent",
            /* action cards (indexes 6-15)*/ "Deal Breaker", "Forced Deal", "Sly Deal", "Just Say No", "Debt Collector", "It's My Birthday", "Double the Rent", "House", "Hotel", "Pass Go",
            /* property (16-51)*/ "Mediterranean Avenue (Brown)", "Baltic Avenue (Brown)", "Oriental Avenue (Light Blue)", "Vermont Avenue (Light Blue)", "Connecticut Avenue (Light Blue)", "St. Charles Place (Pink)", "States Avenue (Pink)", "Virginia Avenue (Pink)", "St. James Place (Orange)", "Tennessee Avenue (Orange)", "New York Avenue (Orange)", "Kentucky Avenue (Red)", "Indiana Avenue (Red)", "Illinois Avenue (Red)", "Atlantic Avenue (Yellow)", "Ventnor Avenue (Yellow)", "Marvin Gardens (Yellow)", "Pacific Avenue (Green)", "North Carolina Avenue (Green)", "Pennsylvania Avenue (Green)", "Park Place (Blue)", "Boardwalk (Blue)", "Electric Company (Utilities)", "Water Works (Utilities)", "Reading Railroad (Railroad)", "Pennsylvania Railroad (Railroad)", "B. & O. Railroad (Railroad)", "Short Line (Railroad)",
            "Wild Card (Brown/Light Blue)", "Wild Card (Pink/Orange)", "Wild Card (Red/Yellow)", "Wild Card (Green/Blue)", "Wild Card (Green/Railroad)", "Wild Card (Railroad/Utilities)", "Wild Card (Light Blue/Railroad)", "Property Wild Card (Any Colour)",
            /* money (52-57)*/ "1M", "2M", "3M", "4M", "5M", "10M"};
    static int [] deckCardValues = {
            /* rent cards 0-5 */ 1, 1, 1, 1, 1, 3,
            /* action cards 6-15 */ 5, 3, 3, 4, 3, 2, 1, 3, 4, 1,
            /* property 16-43 */ 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2,
            /* bicolour property 44-51 */ 1, 2, 3, 4, 4, 2, 4, 0,
            /* money 52-57 */ 1, 2, 3, 4, 5, 10
    };
    static String[] description = {
            /* rent cards */
            "Charges rent for (Brown/Light Blue) Property",
            "Charges rent for (Pink/Orange) Property", "Charges rent for (Red/Yellow) Property",
            "Charges rent for (Green/Blue) Property", "Charges rent for (Railroad/Utilities) Property",
            "Charges rent for any property",
            /* action cards */
            "Steals a full property set from opponent",
            "Trade one of your cards for your opponent's (cannot be a full set)",
            "Steal one of your opponent's cards (cannot be a full set)",
            "Deny an action card that is played against you. Use card by entering 0 when an action is used against you.", 
            "Steal 5M from opponent",
            "Steal 2M from opponent", 
            "Play before a rent card to double the rent",
            "When put on a full set, add 3M to the rent",
            "When put on a full set with a house, add 4M to the rent (on top of the house rent)",
            "Get 2 extra cards in your hand",
            /* property rent values */
            /* Brown */ "Number of properties owned in set: |    1     |    2     | \n                             Rent: |    1M    |    2M    |",
            "Number of properties owned in set: |    1     |    2     | \n                             Rent: |    1M    |    2M    |",
            /* Light Blue */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    3M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    3M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    3M    |",
            /* Pink */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    4M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    4M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    4M    |",
            /* Orange */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    3M    |    5M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    3M    |    5M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    3M    |    5M    |",
            /* Red */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    3M    |    6M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    3M    |    6M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    3M    |    6M    |",
            /* Yellow */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    6M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    6M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    6M    |",
            /* Green */ "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    7M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    7M    |",
            "Number of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    7M    |",
            /* Blue */ "Number of properties owned in set: |    1     |    2     |\n                             Rent: |    2M    |    8M    |",
            "Number of properties owned in set: |    1     |    2     |\n                             Rent: |    2M    |    8M    |",
            /* Utilities */ "Number of properties owned in set: |    1     |    2     |\n                             Rent: |    1M    |    2M    |",
            "Number of properties owned in set: |    1     |    2     |\n                             Rent: |    1M    |    2M    |",
            /* Railroads */ "Number of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            "Number of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            "Number of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            "Number of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            /* Bicolour */
            /* Brown/Light Blue */ "Brown: \nNumber of properties owned in set: |    1     |    2     | \n                             Rent: |    1M    |    2M    | \nLight Blue: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    3M    |",
            /* Pink/Orange */ "Pink: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    4M    |\nOrange: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    3M    |    5M    |",
            /* Red/Yellow */ "Red: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    3M    |    6M    |\nYellow: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    6M    |",
            /* Green/Blue */ "Green: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    7M    |\nBlue: \nNumber of properties owned in set: |    1     |    2     |\n                             Rent: |    2M    |    8M    |",
            /* Green/Railroad */ "Green: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    2M    |    4M    |    7M    |\nRailroad: \nNumber of properties owned in set: |    1     |    2     |    3     |    4     | \n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            /* Railroad/Utilities */ "Utilities: \nNumber of properties owned in set: |    1     |    2     | \n                             Rent: |    1M    |    2M    |\nRailroad: \nNumber of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            /* Light Blue/Railroad */ "Light Blue: \nNumber of properties owned in set: |    1     |    2     |    3     | \n                             Rent: |    1M    |    2M    |    3M    |\nRailroad: \nNumber of properties owned in set: |    1     |    2     |    3     |    4     |\n                             Rent: |    1M    |    2M    |    3M    |    4M    |",
            /* Property Wild Card */ "This card can be used as part of any property set. There is no monetary value to it",
            /* Money */ "Adds 1M to your bank", "Adds 2M to your bank", "Adds 3M to your bank",
            "Adds 4M to your bank", "Adds 5M to your bank", "Adds 10M to your bank"
    };
    static int [] propertyRentValues = {
        // Brown (0-1)
        1, 2,
        // Light Blue (2-4)
        1, 2, 3,
        // Pink (5-7)
        1, 2, 4,
        // Orange (8-10)
        1, 3, 5,
        // Red (11-13)
        2, 3, 6,
        // Yellow (14-16)
        2, 4, 6,
        // Green (17-19)
        2, 4, 7,
        // Blue (20-21)
        3, 8,
        // Utilities (22-23)
        1, 2,
        // Railroads (24-27)
        1, 2, 3, 4
    };

    
    
    public static void main(String[] args) throws Exception {
    	Scanner fs = new Scanner ("MatchHistory.txt"); 
        PrintWriter pw = new PrintWriter(new FileWriter("MatchHistory.txt", true)); // Creates a PrintWriter which appends to the file of MatchHistory.txt.

        System.out.println("Welcome to Monopoly Deal!"); // Welcomes user to Monopoly Deal.
        int wantRules = inputValidation("Would you like the rules on how to play? Enter 1 for yes, 2 for no.", 1, 2); // Asks user if they want to know the rules, using the inputValidation method to prevent invalid inputs.
        if (wantRules == 1) { // If user wants rules, this code is run.
            printRules(); // Prints the rules with the method printRules.
        }
         
        // Prompts the user for their username.
        System.out.println("What's your username?");
        String username = sc.nextLine();
        
        System.out.println("Hi " + username + ", let's start the game!");
        System.out.println();
        System.out.println();

        int playAgain = 0;
        do {
        	// Creates variables to be used and changed throughout the code in the methods.
            int [] deck = {
                    /* actions */ 2, 2, 2, 2, 2, 3, 2, 3, 3, 3, 3, 3, 2, 3, 2, 10,
                    /* property */ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    /* bicolour */ 1, 2, 2, 1, 1, 1, 1, /* property wild card */ 2,
                    /* money */ 6, 5, 3, 3, 2, 1
            };

            int [] deckLength = {0}; // A variable to track the deck length. It is made an array so that it can be changed through methods with pass-by-reference.
            for (int i = 0; i < deck.length; i++) { // Calculates the length of the deck by adding the values of the deck indexes together.
                deckLength[0] += deck[i];
            }

            String [] playerCards = new String [10]; // An array the length of 10 is used since it is the max possible amount in each player's hand; if you have 7 cards and use 3 Pass Go's, you will get a max of 10 cards and will need to discard them
            int [] playerMoney = new int [6]; // An array the length of 6 is used since there are only 6 possible monetary values: 1M, 2M, 3M, 4M, 5M, and 10M.
            int [] playerProperties = new int [10]; // There are 10 different types of properties.
            int [] playerFullProperties = {0}; // Sets a variable to count how many full properties there were.
            int [] playerHouseHotels = new int [8]; // Creates a variable to see which properties have houses and/orr hotels.

            // Create same variables for opponent/computer.
            String [] computerCards = new String [10];
            int [] computerMoney = new int [6];
            int [] computerProperties = new int [10];
            int [] computerFullProperties = {0};
            int [] computerHouseHotels = new int [8];
            int [] computerDecision = new int [2]; // Sets a variable to store the computer's choices. First index is for the first decision, second index is for the second decision after the first card, third index is for if there is a third action required.

            int turnCounter = 0; // Creates a variable for counting the turns.
            int roundCounter = 0; // Creates a variable for counting the rounds.
            
            
            
            // Continues running the code until one user has 3 full sets of property.
            while (playerFullProperties[0] < 3 && computerFullProperties[0] < 3) {
            	// Creates a system to evaluate whether the player has no cards and needs to be dealt 5 cards or dealt 2 cards.
            	System.out.println();
            	System.out.println(" __    _  _______  _     _    _______  __   __  ______   __    _  __  \r\n"
            			+ "|  |  | ||       || | _ | |  |       ||  | |  ||    _ | |  |  | ||  | \r\n"
            			+ "|   |_| ||    ___|| || || |  |_     _||  | |  ||   | || |   |_| ||  | \r\n"
            			+ "|       ||   |___ |       |    |   |  |  |_|  ||   |_|| |       ||  | \r\n"
            			+ "|  _    ||    ___||       |    |   |  |       ||    __ ||  _    ||__| \r\n"
            			+ "| | |   ||   |___ |   _   |    |   |  |       ||   |  ||| | |   | __  \r\n"
            			+ "|_|  |__||_______||__| |__|    |___|  |_______||___|  |||_|  |__||__| ");
            	System.out.println("PLAYER'S TURN!"); 
            	int nullValue = 0;
                for (int i = 0; i < playerCards.length; i++) {
                    if (playerCards[i] == null) {
                        nullValue++;
                    }
                }
                // Deals 5 cards if all values of the playerCards array are null.
                if (nullValue == 10) {
                    dealCards(5, playerCards, deck, deckLength); // Deals 5 cards if the player has no cards left.
                }
                // Deals 2 cards if at least 1 value of the playerCards array is not null.
                else {
                    dealCards(2, playerCards, deck, deckLength); // Deals 2 cards if the player still has cards left.
                }
                
                // Allows player to play their cards and adds to the turnCounter for statistics. Then adds to round counter for statistics.
                turnCounter += playCards(playerCards, playerMoney, deck, deckLength, playerProperties, playerFullProperties, computerMoney, computerProperties, computerFullProperties, computerCards, playerHouseHotels, computerHouseHotels, computerDecision, 1);
                // Evaluates the amount of full properties owned by the player.
                
                if (playerFullProperties[0] >= 3) {
            		break;
            	}
                
                
                System.out.println();
                System.out.println(" __    _  _______  _     _    _______  __   __  ______   __    _  __  \r\n"
                		+ "|  |  | ||       || | _ | |  |       ||  | |  ||    _ | |  |  | ||  | \r\n"
                		+ "|   |_| ||    ___|| || || |  |_     _||  | |  ||   | || |   |_| ||  | \r\n"
                		+ "|       ||   |___ |       |    |   |  |  |_|  ||   |_|| |       ||  | \r\n"
                		+ "|  _    ||    ___||       |    |   |  |       ||    __ ||  _    ||__| \r\n"
                		+ "| | |   ||   |___ |   _   |    |   |  |       ||   |  ||| | |   | __  \r\n"
                		+ "|_|  |__||_______||__| |__|    |___|  |_______||___|  |||_|  |__||__| ");
                System.out.println("COMPUTER'S TURN!");
                nullValue = 0;
                for (int i = 0; i < computerCards.length; i++) {
                    if (computerCards[i] == null) {
                        nullValue++;
                    }
                }
                // Deals 5 cards if all values of the playerCards array are null.
                if (nullValue == 10) {
                    dealCards(5, computerCards, deck, deckLength); // Deals 5 cards if the player has no cards left.
                }
                // Deals 2 cards if at least 1 value of the playerCards array is not null.
                else {
                    dealCards(2, computerCards, deck, deckLength); // Deals 2 cards if the player still has cards left.
                }
                
                computerAI(computerCards, playerCards, computerProperties, computerHouseHotels, computerMoney, 0, playerFullProperties, playerProperties, computerDecision, deck, 2, 1);
                turnCounter += playCards(computerCards, computerMoney, deck, deckLength, computerProperties, computerFullProperties, playerMoney, playerProperties, playerFullProperties, playerCards, computerHouseHotels, playerHouseHotels, computerDecision, 2);
                // Evaluates the amount of full properties owned by the player.
                
                if (playerFullProperties[0] >= 3 || computerFullProperties[0] >= 3) {
            		break;
            	}
                
                roundCounter++;
            }
            
            
            
            // Creates variables for statistics such as the game number and the amount of wins the user has.
            int gameCounter = 0; 
            int winCounter = 0;
            
            // Scans the file of MatchHistory to see how many wins the user has gotten (by scanning with username).
            while (fs.hasNextLine()) {
            	if (fs.next().equals(username)) {
            		winCounter++;
            	}
            	gameCounter++;
            }
            
            // Checks who won the game and reports statistics in the MatchHistory log.
            if (playerFullProperties[0] == 3) {
            	pw.println(username + " has won a game! The game lasted " + turnCounter + " turns and " + roundCounter + " rounds.");
            	System.out.println(username + " has beat the computer at Monopoly Deal with 3 full properties!");
            }
            else if (computerFullProperties[0] == 3) {
            	pw.println("Computer has won a game! This is game number " + gameCounter + " that has been played! The game lasted " + turnCounter + " turns and " + roundCounter + " rounds.");
            	System.out.println("Computer has beat " + username + " at Monopoly Deal with 3 full properties!");
            }
            
            // Prints out statistics.
            System.out.println("This is game number " + gameCounter + " that has been played!");
            System.out.println(username + " has won " + (winCounter+1) + " games so far!"); // Adds 1 win due to the current game counting as well.
            
            playAgain = inputValidation("Enter 1 to play again, 2 to quit", 1, 2);
        } while (playAgain == 1); // Loops the game if the player wants to keep playing.
        
        System.out.println("Thanks for playing Monopoly Deal! Hope to see you again!");
        
        // Closes the PrintWriter and File Scanner.
        pw.close();
        fs.close();
    }

    // Checks the validation of the input that the user has put in. Parameters take in a String to prompt user, a minimum integer and a maximum integer for the range for what is accepted.
    public static int inputValidation (String question, int min, int max) {
        while (true) { // Continues running the loop until the user inputs a valid integer.
            int input = 0; // Sets the input to 0 so the user can enter a number.
            
            // Try-catch is used to prevent any errors from happening, such as type mismatch. 
            try {
                System.out.println(question); // Prompts the user the question.
                input = sc.nextInt(); // Scans the user input.
                sc.nextLine(); // Scans the "enter" so that the system doesn't stop due to an integer being typed.
            }
            catch (Exception e) { // If any exceptions occur, this code is run.
                System.out.println("That is not a valid input! Please try again."); // Informs the user that the input is invalid.
                sc.nextLine(); // Scans the "enter" so that the system doesn't stop due to an integer being typed.
                continue;
            }
            if (input >= min && input <= max) { // Once the input is valid (between the minimum and maximum range (inclusive)), this code runs.
                return input; // Returns the number that the user entered.
            }
            else { // If the input is invalid (not between the minimum and maximum) but causes no exceptions, this code is run.
                System.out.println("That is not a valid input! Please try again."); 
            }
        }
    }
    
    // Prints the rules if the user wants to know them.
    public static void printRules () {
        System.out.println("Here are the rules of Monopoly Deal.");
        System.out.println();
        System.out.println("HOW TO PLAY MONOPOLY DEAL:");
        System.out.println("- Each player will be given 2 cards when their turn starts.");
        System.out.println("- If a player has no cards when their turn starts, they will be given 5 cards on their turn. He or she then takes two cards from the deck.");
        System.out.println("- Each player can play up to 3 Property cards, Action cards, Money cards or any combination of the three each turn.");
        System.out.println("- Put money/action cards into the bank. If an action card is put into the bank, it will be treated as money until the game ends.");
        System.out.println("- Use action cards to take money, even properties from your rivals.");
        System.out.println("- If your rival(s) request you to pay money you may choose to pay in cash or property. If you don't have any cards in front of you, you don't have to pay any cards on your hand. You still have to pay from a complete set.");
        System.out.println("- Change is not given if you overpay. The multi-coloured property wild cards are an exception to this: They do not need to be given away to pay off fees to your rival(s) as they do not have any monetary value, so they can only be taken away by Deal Breaker, Sly Deal and Force Deal action cards.");
        System.out.println("- Upon finishing your turn, if you have more than 7 cards in your hand you must choose some cards to discard to the bottom of the draw pile to keep to 7 cards in your hand.");
        System.out.println("- A player wins when they have 3 sets of different colored properties on his turn. This means that there is only one winner for any one game.");
        System.out.println("- You may put a house, then a hotel on a property full set if you have one, except Railroads and Utilities.");
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("GAME RUES:");
        System.out.println("If the printed letters on the Game Board look weird, zoom out and it should be fixed.");
        System.out.println("When prompted to input a property from 1 through 10 for Brown through Railroad, refer to this order printed below.");
        System.out.println("The order of properties is: Brown, Light Blue, Pink, Orange, Red, Yellow, Green, Blue, Utilities, Railroad.");
        System.out.println("When an action card is used, \"OPPONENT:\" is for the opponent to type out an action. If it is your turn, the computer is the opponent. If it is the computer's turn, you are the opponent.");
        System.out.println();
        System.out.println("GAME UI SETUP:");
        System.out.println("Gameboard:");
        System.out.println("----------------------------------------------------------------------------------------------------\r\n"
        		+ "8    Blue PROPERTIES (3 owned) [+3M] House\r\n"
        		+ "Number of properties owned in set: |    1     |    2     |\r\n"
        		+ "                             Rent: |    2M    |    8M    |\r\n"
        		+ "____________________________________________________________________________________________________");
        System.out.println("The first number, 8, refers to the number needed to be typed in to play the card.");
        System.out.println("There's the name of the property set (Blue PROPERTIES), how many owned (3 owned), the value of additional prices like houses ([+3M] House)");
        System.out.println("The next line(s) are the descriptions for the card.");
        System.out.println();
        System.out.println("Player cards description:");
        System.out.println("1  [3M] House\r\n"
        		+ "     When put on a full set, add 3M to the rent\r\n\n"
        		+ "2  [1M] Double the Rent\r\n"
        		+ "     Play before a rent card to double the rent\r\n\n"
        		+ "3  [3M] 3M\r\n"
        		+ "     Adds 3M to your bank\r\n");
        System.out.println("The first number is the card needed to be inputted to play the card.");
        System.out.println("The second number is the value of the card if placed as money (action cards and money cards can be placed as money).");
        System.out.println("The third part is the card name.");
        System.out.println("The next line is the description of the card.");
        System.out.println();
        System.out.println();
    }

    // Creating a method to deal cards to the player or computer
    public static void dealCards (int amount, String [] currentPlayerHand, int [] deck, int [] deckLength) {
        for (int i = 0; i < amount; i++) { // Sets the amount of cards dealt so that the process is repeated by the amount of cards wanted.
            int probability = (int) (Math.random()*deckLength[0]); // Generates a number from 0 to the deck length so that there is an even probability of each index to be drawn.

            for (int j = 0; j < 10; j++) { // Repeats up to 10 times since a player hand can hold up to 10 cards (indexes).
                if (currentPlayerHand[j] == null) { // If the current index (current card slot) is null (or empty), run this loop to deal a card to that slot.
                    int indexCounter = 0; // Set the indexCounter to 0. The variable will be used to identify which card to give to the user.
                    while (probability > 0) { // While the probability is still not 0, continue running the loop.
                        if ((probability - deck[indexCounter]) > 0) { // Continues to subtract the deck indexCounter until the card, generated from probability, is identified
                            probability -= deck[indexCounter]; // Subtract the deck with the index of indexCounter.
                            indexCounter++; // Adds 1 to indexCounter to continue to the next index of the deck cards.
                        }
                        else { // If the probability is 0 or less than 0 (meaning that the current index is the card wanted), run this loop until the probability is 0
                            probability--; // Subtracts 1 from the probability, securing the deck index to identify the card.
                        }
                    }
                    currentPlayerHand[j] = deckCardNames[indexCounter]; // Assigns the name of the card, identified with the deckCardNames to find the name of the card and indexCounter to find the index, to the empty slot of the current player's hand.
                    deck[indexCounter]--; // Subtracts 1 from the draw pile since it has been given to the player's hand.
                    deckLength[0]--; // Subtracts 1 from the deck.
                    break; // Breaks; the code since the card is already dealt.
                }
            }
        }
    }
    
    // Prints out the game board in console and a txt file so that the player can see what is played in front of them.
    public static void gameBoard (int [] playerProperties, int [] playerMoney, int [] opponentProperties, int [] opponentMoney, int [] playerHouseHotels, int [] computerHouseHotels, int [] playerFullProperties, int [] computerFullProperties) throws Exception{
    	PrintWriter pw = new PrintWriter("GameBoard.txt"); // Creates a PrintWriter with a txt file called GameBoard.txt which gets cleared and rewritten every turn.
        // Creates a String array for the property names so that it can be easily referenced and called upon when outputting to the game board.
        String [] propertyNames = {"[1M] Brown PROPERTIES", "[1M] Light Blue PROPERTIES", "[2M] Pink PROPERTIES", "[2M] Orange PROPERTIES", "[3M] Red PROPERTIES", "[3M] Yellow PROPERTIES", "[4M] Green PROPERTIES", "[4M] Blue PROPERTIES", "[2M] Utilities PROPERTIES", "[2M] Railroad PROPERTIES"};

        pw.println("### ##   ####       ##     ##  ##   ### ###  ### ##   \r\n"
        		+ " ##  ##   ##         ##    ##  ##    ##  ##   ##  ##  \r\n"
        		+ " ##  ##   ##       ## ##   ##  ##    ##       ##  ##  \r\n"
        		+ " ##  ##   ##       ##  ##   ## ##    ## ##    ## ##   \r\n"
        		+ " ## ##    ##       ## ###    ##      ##       ## ##   \r\n"
        		+ " ##       ##  ##   ##  ##    ##      ##  ##   ##  ##  \r\n"
        		+ "####     ### ###  ###  ##    ##     ### ###  #### ##  \r\n"
        		+ "                                                      \r\n"
        		+ "");
        pw.println("\r\n"
        		+ " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.\r\n"
        		+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
        		+ "| |   ______     | || |  _______     | || |     ____     | || |   ______     | || |  _________   | || |  _______     | || |  _________   | || |     _____    | || |  _________   | || |    _______   | |\r\n"
        		+ "| |  |_   __ \\   | || | |_   __ \\    | || |   .'    `.   | || |  |_   __ \\   | || | |_   ___  |  | || | |_   __ \\    | || | |  _   _  |  | || |    |_   _|   | || | |_   ___  |  | || |   /  ___  |  | |\r\n"
        		+ "| |    | |__) |  | || |   | |__) |   | || |  /  .--.  \\  | || |    | |__) |  | || |   | |_  \\_|  | || |   | |__) |   | || | |_/ | | \\_|  | || |      | |     | || |   | |_  \\_|  | || |  |  (__ \\_|  | |\r\n"
        		+ "| |    |  ___/   | || |   |  __ /    | || |  | |    | |  | || |    |  ___/   | || |   |  _|  _   | || |   |  __ /    | || |     | |      | || |      | |     | || |   |  _|  _   | || |   '.___`-.   | |\r\n"
        		+ "| |   _| |_      | || |  _| |  \\ \\_  | || |  \\  `--'  /  | || |   _| |_      | || |  _| |___/ |  | || |  _| |  \\ \\_  | || |    _| |_     | || |     _| |_    | || |  _| |___/ |  | || |  |`\\____) |  | |\r\n"
        		+ "| |  |_____|     | || | |____| |___| | || |   `.____.'   | || |  |_____|     | || | |_________|  | || | |____| |___| | || |   |_____|    | || |    |_____|   | || | |_________|  | || |  |_______.'  | |\r\n"
        		+ "| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |\r\n"
        		+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
        		+ " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\r\n"
        		+ "");
        // Runs 10 times due to there being 10 types of properties; prints out whichever ones that the user has played.
        for (int i = 0; i < playerProperties.length; i++) { 
        	if (playerProperties[i] != 0) { // If the index of playerProperties has cards, print out the property.
        		// Printing out lines for formatting
        		pw.println("----------------------------------------------------------------------------------------------------");
        		pw.println();
        		if (i < 10) {
        			pw.print((i+1) + "    "); // Printing out spaces so it aligns, even when there is an extra digit for numbers above 10
        		}
        		else {
        			pw.print((i+1) + "   ");
        		}
        		
        		// Prints out the property name as well as how many of the property type are owned.
        		pw.print(propertyNames[i] + " (" + playerProperties[i] + " owned)");
        		if (i < 8) { // Sets under 8 since Utilities and Railroad cannot have houses or hotels.
        			if (playerHouseHotels[i] > 0) {
            			if (playerHouseHotels[i] == 1) {
            				pw.print("[+3M] House");
            			}
            			if (playerHouseHotels[i] == 2) {
            				pw.print("[+3M] House, [+4M] Hotel");
            			}
            		}
        		}
        		pw.println();
        		
        		// Prints out the description of the property, informing the user about how many are needed for a full set and telling the user about the rent costs for each.
        		int temp = 16;
        		for (int j = 0; j < 10; j++) {
        			if (j < i) { // Uses i to compare the j value so that it doesn't go over the actual property set.
        				if (j == 7 || j == 8) { // Doesn't add to Brown since Brown starts at 16.
        					temp += 2;
        				}
        				if (j == 9) {
        					temp += 4; 
        				}
        				else {
        					temp += 3;
        				}
        			}
        		}
        		pw.println(description[temp]);
        		pw.println();
        		
        		// Printing out lines for formatting
        		pw.println("____________________________________________________________________________________________________");
        	}
        }
        pw.println("You have: " + playerFullProperties[0] + " set(s) of full properties.");
        pw.println();
        
        int playerTotalMoney = 0;
        pw.println("\r\n"
        		+ " .----------------.  .----------------.  .-----------------.  .----------------.  .----------------.\r\n"
        		+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
        		+ "| | ____    ____ | || |     ____     | || | ____  _____  | || |  _________   | || |  ____  ____  | |\r\n"
        		+ "| ||_   \\  /   _|| || |   .'    `.   | || ||_   \\|_   _| | || | |_   ___  |  | || | |_  _||_  _| | |\r\n"
        		+ "| |  |   \\/   |  | || |  /  .--.  \\  | || |  |   \\ | |   | || |   | |_  \\_|  | || |   \\ \\  / /   | |\r\n"
        		+ "| |  | |\\  /| |  | || |  | |    | |  | || |  | |\\ \\| |   | || |   |  _|  _   | || |    \\ \\/ /    | |\r\n"
        		+ "| | _| |_\\/_| |_ | || |  \\  `--'  /  | || | _| |_\\   |_  | || |  _| |___/ |  | || |    _|  |_    | |\r\n"
        		+ "| ||_____||_____|| || |   `.____.'   | || ||_____|\\____| | || | |_________|  | || |   |______|   | |\r\n"
        		+ "| |              | || |              | || |              | || |              | || |              | |\r\n"
        		+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
        		+ " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\r\n"
        		+ "");
        // Runs 6 times due to there being 6 types of money; prints out whichever ones that the user has played.
        for (int i = 10; i < (playerMoney.length + 10); i++) { // Sets i to 10 so that the user can enter them when prompted to pay money and so that it doesn't get confused with the properties.
        	if (playerMoney[i-10] > 0) {
        		// Printing out lines for formatting
        		pw.println();
        		pw.println("----------------------------------------------------------------------------------------------------");
        		pw.print((i+1) + "   ");
        		
        		// Prints out the money card name as well as how many cards of the money type they own.
        		pw.print(deckCardNames[i+42] + " (" + playerMoney[i-10] + " owned) ");
        		pw.println("____________________________________________________________________________________________________");
        		pw.println();
        	}
        	playerTotalMoney += playerMoney[i-10]; // Adds to the count of how much money the user has.
        }
        pw.println("You have: " + playerTotalMoney + "M."); // Tells the user their total money count.
        
        
        for (int i = 0; i < 5; i++) {
        	pw.println();
        }
        
        
        
        pw.println(" ## ##   ### ##   ### ##    ## ##   ###  ##  ### ###  ###  ##  #### ##  \r\n"
        		+ "##   ##   ##  ##   ##  ##  ##   ##    ## ##   ##  ##    ## ##  # ## ##  \r\n"
        		+ "##   ##   ##  ##   ##  ##  ##   ##   # ## #   ##       # ## #    ##     \r\n"
        		+ "##   ##   ##  ##   ##  ##  ##   ##   ## ##    ## ##    ## ##     ##     \r\n"
        		+ "##   ##   ## ##    ## ##   ##   ##   ##  ##   ##       ##  ##    ##     \r\n"
        		+ "##   ##   ##       ##      ##   ##   ##  ##   ##  ##   ##  ##    ##     \r\n"
        		+ " ## ##   ####     ####      ## ##   ###  ##  ### ###  ###  ##   ####    \r\n"
        		+ "");
        pw.println("\r\n"
        		+ " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.\r\n"
        		+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
        		+ "| |   ______     | || |  _______     | || |     ____     | || |   ______     | || |  _________   | || |  _______     | || |  _________   | || |     _____    | || |  _________   | || |    _______   | |\r\n"
        		+ "| |  |_   __ \\   | || | |_   __ \\    | || |   .'    `.   | || |  |_   __ \\   | || | |_   ___  |  | || | |_   __ \\    | || | |  _   _  |  | || |    |_   _|   | || | |_   ___  |  | || |   /  ___  |  | |\r\n"
        		+ "| |    | |__) |  | || |   | |__) |   | || |  /  .--.  \\  | || |    | |__) |  | || |   | |_  \\_|  | || |   | |__) |   | || | |_/ | | \\_|  | || |      | |     | || |   | |_  \\_|  | || |  |  (__ \\_|  | |\r\n"
        		+ "| |    |  ___/   | || |   |  __ /    | || |  | |    | |  | || |    |  ___/   | || |   |  _|  _   | || |   |  __ /    | || |     | |      | || |      | |     | || |   |  _|  _   | || |   '.___`-.   | |\r\n"
        		+ "| |   _| |_      | || |  _| |  \\ \\_  | || |  \\  `--'  /  | || |   _| |_      | || |  _| |___/ |  | || |  _| |  \\ \\_  | || |    _| |_     | || |     _| |_    | || |  _| |___/ |  | || |  |`\\____) |  | |\r\n"
        		+ "| |  |_____|     | || | |____| |___| | || |   `.____.'   | || |  |_____|     | || | |_________|  | || | |____| |___| | || |   |_____|    | || |    |_____|   | || | |_________|  | || |  |_______.'  | |\r\n"
        		+ "| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |\r\n"
        		+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
        		+ " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\r\n"
        		+ "");
        // Runs 10 times due to there being 10 types of properties; prints out whichever ones that the opponent has played.
        for (int i = 17; i < opponentProperties.length; i++) { 
        	if (playerProperties[i-17] != 0) { // If the index of opponentProperties has cards, print out the property.
        		// Printing out lines for formatting
        		pw.println("----------------------------------------------------------------------------------------------------");
        		pw.println();
        		if (i < 10) {
        			pw.print(i + "    ");
        		}
        		else {
        			pw.print(i + "   ");
        		}
        		
        		// Prints out the property name as well as how many of the property type are owned.
        		pw.print(propertyNames[i-17] + " (" + opponentProperties[i-17] + " owned) ");
        		if (i < 8) { // Sets under 8 since Utilities and Railroad cannot have houses or hotels.
        			if (computerHouseHotels[i-17] > 0) {
            			if (computerHouseHotels[i-17] == 1) {
            				pw.print("[+3M] House");
            			}
            			if (computerHouseHotels[i-17] == 2) {
            				pw.print("[+3M] House, [+4M] Hotel");
            			}
            		}
        		}
        		pw.println();
        		
        		// Prints out the description of the property, informing the opponent about how many are needed for a full set and telling the opponent about the rent costs for each.
        		int temp = 16;
        		for (int j = 0; j < 10; j++) {
        			if (j < i) { // Uses i to compare the j value so that it doesn't go over the actual property set.
        				if (j == 7 || j == 8) { // Doesn't add to Brown since Brown starts at 16.
        					temp += 2;
        				}
        				if (j == 9) {
        					temp += 4;
        				}
        				else {
        					temp += 3;
        				}
        			}
        		}
        		pw.println(description[temp]);
        		pw.println();
        		
        		// Printing out lines for formatting
        		pw.println("____________________________________________________________________________________________________");
        	}
        }
        pw.println("Computer has: " + computerFullProperties[0] + " set(s) of full properties.");
        pw.println();
        
        int opponentTotalMoney = 0;
        pw.println("\r\n"
        		+ " .----------------.  .----------------.  .-----------------.  .----------------.  .----------------.\r\n"
        		+ "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\r\n"
        		+ "| | ____    ____ | || |     ____     | || | ____  _____  | || |  _________   | || |  ____  ____  | |\r\n"
        		+ "| ||_   \\  /   _|| || |   .'    `.   | || ||_   \\|_   _| | || | |_   ___  |  | || | |_  _||_  _| | |\r\n"
        		+ "| |  |   \\/   |  | || |  /  .--.  \\  | || |  |   \\ | |   | || |   | |_  \\_|  | || |   \\ \\  / /   | |\r\n"
        		+ "| |  | |\\  /| |  | || |  | |    | |  | || |  | |\\ \\| |   | || |   |  _|  _   | || |    \\ \\/ /    | |\r\n"
        		+ "| | _| |_\\/_| |_ | || |  \\  `--'  /  | || | _| |_\\   |_  | || |  _| |___/ |  | || |    _|  |_    | |\r\n"
        		+ "| ||_____||_____|| || |   `.____.'   | || ||_____|\\____| | || | |_________|  | || |   |______|   | |\r\n"
        		+ "| |              | || |              | || |              | || |              | || |              | |\r\n"
        		+ "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\r\n"
        		+ " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\r\n"
        		+ "");
        // Runs 6 times due to there being 6 types of money; prints out whichever ones that the opponent has played.
        for (int i = 27; i < (opponentMoney.length + 27); i++) { // Sets i to 10 so that the opponent can enter them when prompted to pay money and so that it doesn't get confused with the properties.
        	if (playerMoney[i-27] > 0) {
        		// Printing out lines for formatting
        		pw.println();
        		pw.println("----------------------------------------------------------------------------------------------------");
        		pw.print(i + "   ");
        		
        		// Prints out the money card name as well as how many cards of the money type they own.
        		pw.print(deckCardNames[i+25] + " (" + opponentMoney[i-27] + " owned) ");
        		pw.println("____________________________________________________________________________________________________");
        		pw.println();
        	}
        	opponentTotalMoney += opponentMoney[i-27]; // Adds to the count of how much money the opponent has.
        }
        pw.println("You have: " + opponentTotalMoney + "M."); // Tells the user their total money count.
        pw.close(); // Closes the PrintWriter.
    	
    	
    	
    	
    	
        System.out.println();
        System.out.println("  ####      ##     ##   ##  ######   #####     ####      ##     #####    ####    \r\n"
        		+ " ##  ##    ####    ### ###  ##       ##  ##   ##  ##    ####    ##  ##   ## ##   \r\n"
        		+ " ##       ##  ##   #######  ##       ##  ##   ##  ##   ##  ##   ##  ##   ##  ##  \r\n"
        		+ " ## ###   ######   ## # ##  ####     #####    ##  ##   ######   #####    ##  ##  \r\n"
        		+ " ##  ##   ##  ##   ##   ##  ##       ##  ##   ##  ##   ##  ##   ####     ##  ##  \r\n"
        		+ " ##  ##   ##  ##   ##   ##  ##       ##  ##   ##  ##   ##  ##   ## ##    ## ##   \r\n"
        		+ "  ####    ##  ##   ##   ##  ######   #####     ####    ##  ##   ##  ##   #### ");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("PLAYER PROPERTIES:");
        System.out.println();
        // Runs 10 times due to there being 10 types of properties; prints out whichever ones that the user has played.
        for (int i = 0; i < playerProperties.length; i++) { 
        	if (playerProperties[i] != 0) { // If the index of playerProperties has cards, print out the property.
        		// Printing out lines for formatting
        		System.out.println("----------------------------------------------------------------------------------------------------");
        		if (i < 10) {
        			System.out.print((i+1) + "    ");
        		}
        		else {
        			System.out.print((i+1) + "   ");
        		}
        		
        		// Prints out the property name as well as how many of the property type are owned.
        		System.out.print(propertyNames[i] + " (" + playerProperties[i] + " owned) ");
        		if (i < 8) { // Sets under 8 since Utilities and Railroad cannot have houses or hotels.
        			if (playerHouseHotels[i] > 0) {
            			if (playerHouseHotels[i] == 1) {
            				System.out.print("[+3M] House");
            			}
            			if (playerHouseHotels[i] == 2) {
            				System.out.print("[+3M] House, [+4M] Hotel");
            			}
            		}
        		}
        		
        		System.out.println();
        		
        		// Prints out the description of the property, informing the user about how many are needed for a full set and telling the user about the rent costs for each.
        		int temp = 16;
        		
        		// Adds up the index of the card. j is the property colour/number (1 through 10) counter to add the position for the description, i is the property number.
        		for (int j = 0; j < 10; j++) {
        			if (j <= i) { // Uses i to compare the j value so that it doesn't go over the actual property set.
        				if (j == 7 || j == 8) { // Doesn't add to Brown since Brown starts at 16.
        					temp += 2;
        				}
        				else if (j == 9) { // Adding for Railroads.
        					temp += 4; 
        				}
        				else if (j < 7 && j != 0){ // Adding for other properties other than Brown.
        					temp += 3;
        				}
        			}
        		}
        		System.out.println(description[temp]);
        		
        		// Printing out lines for formatting
        		System.out.println("____________________________________________________________________________________________________");
        	}
        }
        System.out.println("You have: " + playerFullProperties[0] + " set(s) of full properties.");
        
        
        playerTotalMoney = 0;
        System.out.println();
        System.out.println();
        System.out.println("PLAYER MONEY:");
        System.out.println();
        // Runs 6 times due to there being 6 types of money; prints out whichever ones that the user has played.
        for (int i = 10; i < (playerMoney.length + 10); i++) { // Sets i to 10 so that the user can enter them when prompted to pay money and so that it doesn't get confused with the properties.
        	if (playerMoney[i-10] > 0) {
        		// Printing out lines for formatting
        		System.out.println("----------------------------------------------------------------------------------------------------");
        		System.out.print((i+1) + "   ");
        		
        		// Prints out the money card name as well as how many cards of the money type they own.
        		System.out.print(deckCardNames[i+42] + " (" + playerMoney[i-10] + " owned) ");
        		/* System.out.println(description[i+44]);
        		System.out.println();
        		*/ 
        		System.out.println();
        		System.out.println("____________________________________________________________________________________________________");
        	}
        	playerTotalMoney += playerMoney[(i-10)] * ((i-10) + 1); // Adds to the count of how much money the user has.
        }
        System.out.println("You have: " + playerTotalMoney + "M."); // Tells the user their total money count.
        
        for (int i = 0; i < 2; i++) {
        	System.out.println();
        }
        System.out.println("//////////////////////////////");
        for (int i = 0; i < 2; i++) {
        	System.out.println();
        }
        
        
        System.out.println("OPPONENT PROPERTIES:");
        System.out.println();
        // Runs 10 times due to there being 10 types of properties; prints out whichever ones that the user has played.
        for (int i = 0; i < opponentProperties.length; i++) { 
        	if (opponentProperties[i] != 0) { // If the index of opponentProperties has cards, print out the property.
        		// Printing out lines for formatting
        		System.out.println("----------------------------------------------------------------------------------------------------");
    			System.out.print((i+17) + "   ");
        		
        		// Prints out the property name as well as how many of the property type are owned.
        		System.out.print(propertyNames[i] + " (" + opponentProperties[i] + " owned) ");
        		if (i < 8) { // Sets under 8 since Utilities and Railroad cannot have houses or hotels.
        			if (computerHouseHotels[i] > 0) {
            			if (computerHouseHotels[i] == 1) {
            				System.out.print("[+3M] House");
            			}
            			if (computerHouseHotels[i] == 2) {
            				System.out.print("[+3M] House, [+4M] Hotel");
            			}
            		}
        		}
        		
        		System.out.println();
        		
        		// Prints out the description of the property, informing the opponent about how many are needed for a full set and telling the opponent about the rent costs for each.
        		int temp = 16;
        		for (int j = 0; j < 10; j++) {
        			if (j < i) { // Uses i to compare the j value so that it doesn't go over the actual property set.
        				if (j == 7 || j == 8) { // Doesn't add to Brown since Brown starts at 16.
        					temp += 2;
        				}
        				else if (j == 9) {
        					temp += 4;
        				}
        				else {
        					temp += 3;
        				}
        			}
        		}
        		System.out.println(description[temp]);
        		
        		// Printing out lines for formatting
        		System.out.println("____________________________________________________________________________________________________");
        	}
        }
        System.out.println("Computer has: " + computerFullProperties[0] + " set(s) of full properties.");
        System.out.println();
        System.out.println();
        
        opponentTotalMoney = 0;
        System.out.println("OPPONENT MONEY:");
        System.out.println();
        // Runs 6 times due to there being 6 types of money; prints out whichever ones that the opponent has played.
        for (int i = 10; i < (opponentMoney.length + 10); i++) { // Sets i to 10 so that the opponent can enter them when prompted to pay money and so that it doesn't get confused with the properties.
        	if (opponentMoney[i-10] > 0) {
        		// Printing out lines for formatting
        		System.out.println("----------------------------------------------------------------------------------------------------");
        		System.out.print((i+17) + "   ");
        		
        		// Prints out the money card name as well as how many cards of the money type they own.
        		System.out.print(deckCardNames[i+42] + " (" + opponentMoney[i-10] + " owned)");
        		System.out.println();
        		System.out.println("____________________________________________________________________________________________________");
        	}
        	opponentTotalMoney += opponentMoney[(i-10)] * ((i-10) + 1); // Adds to the count of how much money the opponent has.
        }
        System.out.println("Computer has: " + opponentTotalMoney + "M."); // Tells the user the opponent's total money count.
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (int i = 0; i < 5; i++) {
        	System.out.println();
        }
    }
    
    public static void computerAI (String [] opponentCards, String [] playerCards, int [] opponentProperties, int [] computerHouseHotels, int [] opponentMoney, int amountCharged, int [] playerFullProperties, int [] playerProperties, int [] computerDecision, int [] deck, int currentTurn, int moveType) {
    	/* COMPUTER MOVE DESCRIPTIONS:
    	 * 
    	 * moveType = 1 is to play a card
    	 * moveType = 2 is part 2 of an action card
    	 * moveType = 3 is to pay a rent or debt
    	 * moveType = 4 is to play Just Say No
    	 * 
    	 * computerDecision[0] is for the first move of a card, such as placing a card to place (single colour), placing money, selecting if you want to use action card or use it as money
    	 * computerDecision[1] is for the second move of a card, such as deciding which wild card colour to use, what colour on a rent card to charge, choosing which card to steal with Sly Deal
    	 * computerDecision[2] is for the third move of a card, which is used for Forced Deal to choose which card to give
    	 */
    	int opponentTotalMoney = 0; // Creates a variable to store how much money the computer has.
    	int computerCardNum = playerCardNum(opponentCards);
    	
    	// Adds the total amount of money that the computer has.
    	for (int i = 0; i < opponentMoney.length; i++) {
    		if (opponentMoney[i] != 0) {
    			opponentTotalMoney += opponentMoney[i];
    		}
    	}
    	
    	// Checks if the computer has no cards and ends the turn if so.
    	if (opponentCards[0] == null) {
    		return;
    	}
    	
    	if (moveType == 1) { // If the moveType is for the computer to play a card, this code is run.
    		// Creates an array with 10 indexes so that the computer cards can be identified in the deck and can be stored here since the computer cannot identify Strings.
        	int [] identifiedCards = new int [10]; 
        	for (int i = 0; i < computerCardNum; i++) {
        		identifiedCards[i] = identifyCard(opponentCards[i]);
        	}
        	
    		// Prioritizes putting down some money for the computer before putting down properties if the computer has money.
        	if (opponentTotalMoney < 5) {
        		boolean changedDecision = false;
    			while (changedDecision == false) {
    				for (int i = 0; i < identifiedCards.length; i++) {
    					if (identifiedCards[i] >= 52 && identifiedCards[i] <= 57) {
            				computerDecision[0] = i+1; // Returns the index of the card +1 to find the actual value that the computer would type.
            				changedDecision = true;
        				}
            			else if (identifiedCards[i] == 13 || identifiedCards[i] == 14) {
            				computerDecision[0] = i+1;
            				changedDecision = true;
            			}
            		}
    				if (changedDecision == true) {
        				return;
        			}
        			else {
        				break;
        			}
    			}
        	}
        	
        	// Places property after seeing if the computer has money
    		for (int i = 0; i < identifiedCards.length; i++) { // Running for loop through all the cards in the player hand.
    			// Checks if the card identified is a single-coloured property and places it if so.
    			if (identifiedCards[i] >= 16 && identifiedCards[i] <= 43) {
    				computerDecision[0] = i+1; // Returns the index of the card +1 to find the actual value that the computer would type.
    				break;
    			}
    			// Checks if the card identified is a wild-coloured property and places if there is another one of the colours matching.
    			else if (identifiedCards[i] >= 44 && identifiedCards[i] <= 51) {
    				if (identifiedCards[i] >= 44 && identifiedCards[i] <= 50) {
    					// Checks which property the computer has and places whichever one they have of. Otherwise, it chooses another card.
						if (opponentProperties[identifiedCards[i] - 44] > 0) { 
							computerDecision[0] = i+1; // Selects and uses the card position.
						}
						else if (opponentProperties[identifiedCards[i] - 43] > 0) {
							computerDecision[0] = i+1;
						}
    				}
    				else { // Runs for Property Wild Card
    					for (int j = 0; j < 9; j++) {
    						if (j == 0 || j == 7 || j == 8) {
    							if (opponentProperties[j] == 1 || opponentProperties[j] == 3) {
        							computerDecision[1] = j + 1;
        							return;
    							}
    						}
    						else if (j == 9) {
    							if (opponentProperties[j] == 3) {
        							computerDecision[1] = j + 1;
        							return;
    							}
    						}
    						else if (j > 0 && j < 7){
    							if (opponentProperties[j] == 2) {
        							computerDecision[1] = j + 1;
        							return;
    							}
    						}
    						else {
    							computerDecision[1] = (int) (Math.random()*10 + 1);
    						}
    					}
    				}
    			}
    		}
    		
    		if (computerDecision[0] == 0 && computerDecision[1] == 0) {
    			computerDecision[0] = (int) (Math.random()*computerCardNum + 1);
    			if (computerDecision[0] == 12) {
    				computerDecision[1] = 2;
    			}
    		}
    	}
    	
    	// Runs if the computer has to decide what to do for an action card.
    	else if (moveType == 2) {
    		// Switches through the possibilities that the computer may have to respond to.
    		// Returning 1 means that the computer wants to use the action card. 
    		// Returning 2 means that the computer wants to deposit the action card as money.
    		switch (computerDecision[0]) {
    			case 0: // Brown and Light Blue Rent
    				if (opponentProperties[0] > 0 || opponentProperties[1] > 0) {
    					computerDecision[0] = 1; // Shows that the computer wants to use this card.
    					if (opponentProperties[0] > opponentProperties[1]) {
    						computerDecision[1] = 1; // Chooses to charge Brown property if the computer has more Brown properties than Light Blue.
    					}
    					else {
    						computerDecision[1] = 2; // Chooses to charge Light Blue property if the computer has more Light Blue properties than Light Blue.
    					}
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			case 1: // Pink and Orange Rent
    				if (opponentProperties[2] > 0 || opponentProperties[3] > 0) {
    					computerDecision[0] = 1;
    					if (opponentProperties[2] > opponentProperties[3]) {
    						computerDecision[1] = 1;
    					}
    					else {
    						computerDecision[1] = 2;
    					}
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			case 2: // Red and Yellow Rent
    				if (opponentProperties[4] > 0 || opponentProperties[5] > 0) {
    					computerDecision[0] = 1;
    					if (opponentProperties[4] > opponentProperties[5]) {
    						computerDecision[1] = 1;
    					}
    					else {
    						computerDecision[1] = 2;
    					}
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			case 3: // Green and Blue Rent
    				if (opponentProperties[6] > 0 || opponentProperties[7] > 0) {
    					computerDecision[0] = 1;
    					if (opponentProperties[6] > opponentProperties[7]) {
    						computerDecision[1] = 1;
    					}
    					else {
    						computerDecision[1] = 2;
    					}
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			case 4: // Utilities and Railroad Rent
    				if (opponentProperties[8] > 0 || opponentProperties[9] > 0) {
    					computerDecision[0] = 1;
    					if (opponentProperties[8] > opponentProperties[9]) {
    						computerDecision[1] = 1;
    					}
    					else {
    						computerDecision[1] = 2;
    					}
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			case 5: // Multi-Colour Rent
    				// Creates a variable and a loop to scan the sets for the property with the highest value.
    				int mostProperty = 0; 
    				for (int i = 0; i < 10; i++) {
    					if (i == 0 || opponentProperties[i] > mostProperty) {
    						mostProperty = i;
    					}
    				}
    				if (mostProperty == 0) {
    					computerDecision[0] = 2;
    				}
    				else {
    					computerDecision[0] = 1;
    					computerDecision[1] = mostProperty+1;;
    				}
    				break;
    			case 6: // Deal Breaker
    				// Checks if the player has a full property to steal.
    				if (playerFullProperties[0] > 0) {
    					// Creates a code to evaluate which property is a full set. Scans through and identifies what to steal.
    					for (int i = 0; i < playerProperties.length; i++) {
    						if (i == 0 || i == 7 || i == 8) {
    							if (playerProperties[i] == 2) {
    								computerDecision[0] = 1;
    								computerDecision[1] = i+1;
    								return;
    							}
    						}
    						else if (i == 9) {
    							if (playerProperties[i] == 4) {
    								computerDecision[0] = 1;
    								computerDecision[1] = i+1;
    								return;
    							}
    						}
    						else {
    							if (playerProperties[i] == 3) {
    								computerDecision[0] = 1;
    								computerDecision[1] = i+1;
    								return;
    							}
    						}
    					}
    				}
    				computerDecision[0] = -1; // Returns an invalid value since nothing can be stolen.
    				break;
    			case 7: // Forced Deal
    				// Runs a loop which runs through all the player's properties and scans which ones to steal.
    				for (int i = 0; i < playerProperties.length; i++) {
    					// Scans through the sets with 2 properties for a full set.
						if (i == 0 || i == 7 || i == 8) { 
							if ((playerProperties[i] == 1 || playerProperties[i] > 2) && opponentProperties[i] == 1) {
								if (opponentProperties[i] == 1 || opponentProperties[i] == 3) {
									computerDecision[0] = 1;
									computerDecision[1] = i+16;
									for (int j = 0; j < opponentProperties.length; j++) {
										if (opponentProperties[j] == 1) {
											computerDecision[2] = j;
											return;
										}
 									}
									
									// Runs until the computer randomly generates a valid property to give.
									while (true) {
					    				computerDecision[2] = (int) (Math.random()*10 + 1);
					    				if (computerDecision[2] == 1 || computerDecision[2] == 7 || computerDecision[2] == 8) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 2) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    				else if (computerDecision[2] == 9) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 4) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    				else {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 3) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    			}
								}
							}
						}
						else if (i == 9) { // Evaluating railroad
							if (playerProperties[i] < 4 || playerProperties[i] > 4) {
								if (opponentProperties[i] >= 2) {
									computerDecision[0] = 1;
									computerDecision[1] = i+16;
									for (int j = 0; j < opponentProperties.length; j++) {
										if (opponentProperties[j] == 1) {
											computerDecision[2] = j;
											return;
										}
 									}
									
									// Runs until the computer randomly generates a valid property to give.
									for (int j = 0; j < 10; j++) {
					    				computerDecision[2] = (int) (Math.random()*10 + 1);
					    				if (computerDecision[2] == 1 || computerDecision[2] == 7 || computerDecision[2] == 8) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 2) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    				else if (computerDecision[2] == 9) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 4) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    				else {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 3) {
					    						computerDecision[2] += 16;
						        				return;
						        			}
					    				}
					    			}
								}
							}
						}
						else { 
							if (playerProperties[i] < 3 || playerProperties[i] > 3) { // Evaluating properties with 3 as a full set.
								if (opponentProperties[i] == 2) {
									computerDecision[0] = 1;
									computerDecision[1] = i + 16;
									
									// Selects a property with only only one of that property to give.
									for (int j = 0; j < opponentProperties.length; j++) {
										if (opponentProperties[j] == 1 && j != i) {
											computerDecision[2] = j;
											return;
										}
 									}
									
									// Runs until the computer randomly generates a valid property to give.
									while (true) {
					    				computerDecision[2] = (int) (Math.random()*10 + 1);
					    				if (computerDecision[2] == 1 || computerDecision[2] == 7 || computerDecision[2] == 8) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 2) {
					    						computerDecision[2] += 1;
						        				return;
						        			}
					    				}
					    				else if (computerDecision[2] == 9) {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 4) {
					    						computerDecision[2] += 1;
						        				return;
						        			}
					    				}
					    				else {
					    					if (opponentProperties[computerDecision[2]] != 0 && opponentProperties[computerDecision[2]] != 3) {
					    						computerDecision[2] += 1;
						        				return;
						        			}
					    				}
					    			}
								}
							}
						}
					}
    				computerDecision[0] = -1; // Returns an invalid value since nothing can be stolen.
    				break;
    			case 8: // Sly Deal
    				for (int i = 0; i < playerProperties.length; i++) {
    					// Scans through the sets with 2 properties for a full set.
						if (i == 0 || i == 7 || i == 8) {
							if ((playerProperties[i] == 1 || playerProperties[i] > 2) && opponentProperties[i] == 1) {
								if (opponentProperties[i] == 1 || opponentProperties[i] == 3) {
									computerDecision[0] = 1;
									computerDecision[1] = i+1;
									return;
								}
							}
						}
						else if (i == 9) {
							if (playerProperties[i] < 4 || playerProperties[i] > 4) {
								if (opponentProperties[i] >= 2) {
									computerDecision[0] = 1;
									computerDecision[1] = i+1;
									return;
								}
							}
						}
						else {
							if (playerProperties[i] < 3 || playerProperties[i] > 3) {
								if (opponentProperties[i] == 2) {
									computerDecision[0] = 1;
									computerDecision[1] = i+1;
									return;
								}
							}
						}
					}
    				computerDecision[0] = -1;
    				break;
    			case 10: // Debt Collector
    				computerDecision[0] = 1;
    				break;
    			case 11: // It's my Birthday
    				computerDecision[0] = 1;
    				break;
    			case 12: // Double the Rent
    				computerDecision[0] = 2;
    				break;
    			case 13: // House
    				for (int i = 0; i < 8; i++) {
    					if (i == 0 || i == 7) {
    						if (opponentProperties[i] == 2) {
    							if (computerHouseHotels[0] == 0) {
    								computerDecision[0] = 1;
        							computerDecision[1] = i+1;
        							computerHouseHotels[i] = 1;
        							return;
    							}
    						}
    					}
    					else {
    						if (opponentProperties[i] == 3) {
    							if (computerHouseHotels[i] == 0) {
    								computerDecision[0] = 1;
        							computerDecision[1] = i+1;
        							computerHouseHotels[i] = 1;
        							return;
    							}
    						}
    					}
    				}
    				computerDecision[0] = 2; // Decides to use if none of the conditions qualify.
    				computerDecision[1] = -1; // Exits out of an invalid decision.
    				break;
    			case 14: // Hotel
    				for (int i = 0; i < 8; i++) {
    					if (i == 0 || i == 7) {
    						if (opponentProperties[i] == 2) {
    							if (computerHouseHotels[i] == 1) {
    								computerDecision[0] = 1;
        							computerDecision[1] = i+1;
        							computerHouseHotels[i] = 2;
        							return;
    							}
    						}
    					}
    					else {
    						if (opponentProperties[i] == 3) {
    							if (computerHouseHotels[i] == 1) {
    								computerDecision[0] = 1;
        							computerDecision[1] = i+1;
        							computerHouseHotels[i] = 2;
        							return;
    							}
    						}
    					}
    				}
    				computerDecision[0] = 2; // Decides to use money if none of the conditions qualify.
    				computerDecision[1] = -1; // Exits out of an invalid decision.
    				break;
    			case 15: // Pass Go
    				if (computerCardNum < 5) {
    					computerDecision[0] = 1;
    				}
    				else {
    					computerDecision[0] = 2;
    				}
    				break;
    			default:
    				computerDecision[0] = 0;
    		}
    	}
    	// Runs if the computer has to decide what to do for paying a debt.
    	else if (moveType == 3) {
    		if (opponentTotalMoney != 0) { // Checks to make sure that the computer has money (otherwise, they pay with properties).
    			// Charges money and takes away from the computer's total money depending on the charge amount. 
    			// Prioritizes giving larger amounts.
    			System.out.println(amountCharged + " is being charged!");
    			if (amountCharged >= 10 && opponentMoney[5] > 0) {
    				opponentMoney[5]--;
    				computerDecision[0] = 32;
    			}
    			else if (amountCharged >= 5 && opponentMoney[4] > 0) {
    				opponentMoney[4]--;
    				computerDecision[0] = 31;
    			}
    			else if (amountCharged >= 4 && opponentMoney[3] > 0) {
    				opponentMoney[3]--;
    				computerDecision[0] = 30;
    			}
    			else if (amountCharged >= 3 && opponentMoney[2] > 0) {
    				opponentMoney[2]--;
    				computerDecision[0] = 29;
    			}
    			else if (amountCharged >= 2 && opponentMoney[1] > 0) {
    				opponentMoney[1]--;
    				computerDecision[0] = 28;
    			}
    			else if (amountCharged >= 1 && opponentMoney[0] > 0) {
    				opponentMoney[0]--;
    				computerDecision[0] = 27;
    			}
    			// Otherwise scenario in case none of the scenarios above are true (if opponent may need to overpay without refund).
    			else {
    				for (int i = 0; i < 5; i++) {
    					if (opponentMoney[i] != 0) {
    						opponentMoney[i]--;
    						computerDecision[0] = i+27;
    					}
    				}
    			}
    		}
    		
    		// If computer has no money, they have to pay with properties.
    		else {
    			while (amountCharged > 0) { // Runs until the computer generates a valid property to give.
    				// Checking properties to see if they have the property.
    				for (int i = 0; i < 10; i++) {
    					computerDecision[0] = i;
    					if (i == 0 || i == 7 || i == 8) {
        					if (opponentProperties[i] != 0 && opponentProperties[computerDecision[0]] != 2) {
        						computerDecision[0] += 17; // Adding 17 because that is the shift required to charge properties. 16 to get to properties, +1 for position and not index.
    	        				amountCharged -= deckCardValues [computerDecision[0] - 1]; // -1 since the index is needed and not the position.
    	        				return;
        					}
        				}
        				else if (i == 9) {
        					if (opponentProperties[i] != 0 && opponentProperties[i] != 4) {
        						computerDecision[0] += 17;
        						amountCharged -= deckCardValues[computerDecision[0] - 1];
        						return;
        					}
        				}
        				else if (i >= 1 && i <= 6){
        					if (opponentProperties[i] != 0 && opponentProperties[i] != 3) {
        						computerDecision[0] += 17;
    	        				amountCharged -= deckCardValues[computerDecision[0] - 1];
    	        				return;
        					}
        				}
    				}
    				for (int i = 0; i < 10; i++) {
    					computerDecision[0] = i;
    					if (opponentProperties[computerDecision[0]] > 0) { // Checking if the computer has the property selected.
    						computerDecision[0] += 17;
    						amountCharged -= deckCardValues[i - 1];
    						return;
    					}
    				}
    			}
    		}
    	}
    	// Just Say No
    	else if (moveType == 4) {
    		computerCardNum = playerCardNum(opponentCards);
    		for (int i = 0; i < computerCardNum; i++) {
				int justSayNo = identifyCard(opponentCards[i]); // Sets the justSayNo to the index of the opponent's cards with the index of i.
				if (justSayNo == 9) { // If the identified card is 9 (the index of the card in the deck representing Just Say No), this code is run.
					System.out.println("0");
					System.out.println("Computer has used Just Say No! Action has been denied."); // Informs user that their action has been denied.
					// Runs a loop to shift the cards since the Just Say No was played.
					for (int j = i; j < opponentCards.length - 1; j++) { // i is used since it is the index of the justSayNo card.
                        opponentCards[j] = opponentCards[j + 1];
                        opponentCards[j + 1] = null;
                        if (j == opponentCards.length - 1) {
                        	opponentCards[j] = null;
                        }
                    }
					break;
				}
			}
    	}
    }
    
    // Displays the cards of the user or computer.
    public static void displayCards (String [] playerCards, String [] computerCards, int currentTurn) throws Exception {
        if (currentTurn == 1) {
        	System.out.println(); // Space for formatting.
            for (int i = 0; i < playerCards.length; i++) { // Runs for the amount of indexes in the array playerCards, which is 10.
                if (playerCards[i] != null) { // Runs if the current index (i) is not null.
                    int value = 0; // Declares the variable value so that a money amount can be set.
                    value = deckCardValues[identifyCard(playerCards[i])]; // Identifies the card using the method and uses the number returned as the index to search in the deckCardValues. Assigns this to "value".
                    if (i == 9) {
                    	System.out.println((i + 1) + " [" + value + "M] " + playerCards[i]); // Displays the value and the player card.
                    }
                    else {
                    	System.out.println((i + 1) + "  [" + value + "M] " + playerCards[i]); // Displays the value and the player card.
                    }
                    System.out.println(description[identifyCard(playerCards[i])]);
                    System.out.println();
                }
            }
            System.out.println(); // Space for formatting.
        }
        else if (currentTurn == 2) {
            PrintWriter cc = new PrintWriter("ComputerCards.txt");
        	cc.println("--------------------------------------------------");
        	cc.println(); // Space for formatting.
            for (int i = 0; i < playerCards.length; i++) { // Runs for the amount of indexes in the array playerCards, which is 10.
                if (playerCards[i] != null) { // Runs if the current index (i) is not null.
                    int value = 0; // Declares the variable value so that a money amount can be set.
                    value = deckCardValues[identifyCard(playerCards[i])]; // Identifies the card using the method and uses the number returned as the index to search in the deckCardValues. Assigns this to "value".
                    if (i == 9) {
                    	cc.println((i + 1) + " [" + value + "M] " + playerCards[i]); // Displays the value and the player card.
                    }
                    else {
                    	cc.println((i + 1) + "  [" + value + "M] " + playerCards[i]); // Displays the value and the player card.
                    }
                }
            }
            cc.close();
        }
    }

    // Allows rent cards and cards that charge money to be played.
    public static int actionMoneyCard (int identifiedCard, String [] playerCards, int [] playerProperties, int [] playerMoney, int [] playerFullProperties, int [] opponentProperties, int [] opponentFullProperties, String [] opponentCards, int [] opponentMoney, int [] computerDecision, int [] deck, int [] playerHouseHotels, int [] opponentHouseHotels, int currentTurn) {
        int input = 0;
        switch (identifiedCard) { // Switches between the cases with the identifiedCard.
            case 0: // Brown and Light Blue property rent
                while (true) {
                	if (currentTurn == 1) {
                    	input = inputValidation("Enter 1 for (Brown) and 2 for (Light Blue) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 2);
                    	if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
                	}
                    else {
                    	computerDecision[0] = 1; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                    if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
                    if (input == 1) { // Charging Brown property
                        switch (playerProperties[0]) {
                            case 0:
                                System.out.println("You don't have any Brown properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[0]
                                return propertyRentValues[0];
                            default:
                                // Charging rent valueRentValues[1]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[0] == 1) {
                            		return propertyRentValues[1] + 3;
                            	}
                            	else if (playerHouseHotels[0] == 2) {
                            		return propertyRentValues[1] + 7;
                            	}
                                return propertyRentValues[1];
                        }
                    } 
                    else if (input == 2) { // Charging Light Blue property
                        switch (playerProperties[1]) {
                            case 0:
                                System.out.println("You don't have any Light Blue properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[2]
                                return propertyRentValues[2];
                            case 2:
                                // Charging rent valueRentValues[3]
                                return propertyRentValues[3];
                            default:
                                // Charging rent valueRentValues[4]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[1] == 1) {
                            		return propertyRentValues[4] + 3;
                            	}
                            	else if (playerHouseHotels[1] == 2) {
                            		return propertyRentValues[4] + 7;
                            	}
                                return propertyRentValues[4];
                        }
                    }
                }
            case 1: // Pink and Orange property rent
            	while (true) {
            		if (currentTurn == 1) {
                    	input = inputValidation("Enter 1 for (Pink) and 2 for (Orange) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 2);
                    	if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
            		}
                    else {
                    	computerDecision[0] = 1; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                    if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
                    if (input == 1) { // Charging Pink property
                        switch (playerProperties[2]) {
                            case 0:
                                System.out.println("You don't have any Pink properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[5]
                                return propertyRentValues[5];
                            case 2:
                                // Charging rent valueRentValues[6]
                                return propertyRentValues[6];
                            default:
                                // Charging rent valueRentValues[7]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[2] == 1) {
                            		return propertyRentValues[7] + 3;
                            	}
                            	else if (playerHouseHotels[2] == 2) {
                            		return propertyRentValues[7] + 7;
                            	}
                                return propertyRentValues[7];
                        }
                    }
                    else if (input == 2) { // Charging Orange property
                        switch (playerProperties[3]) {
                            case 0:
                                System.out.println("You don't have any Orange properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[8]
                                return propertyRentValues[8];
                            case 2:
                                // Charging rent valueRentValues[9]
                                return propertyRentValues[9];
                            default:
                                // Charging rent valueRentValues[10]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[3] == 1) {
                            		return propertyRentValues[10] + 3;
                            	}
                            	else if (playerHouseHotels[3] == 2) {
                            		return propertyRentValues[10] + 7;
                            	}
                                return propertyRentValues[10];
                        }
                    }
            	}
            case 2: // Red and Yellow property rent
            	while (true) {
            		if (currentTurn == 1) {
                    	input = inputValidation("Enter 1 for (Red) and 2 for (Yellow) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 2);
                    	if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
            		}
                    else {
                    	computerDecision[0] = 2; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                    if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
                    if (input == 1) { // Charging Red property
                        switch (playerProperties[4]) {
                            case 0:
                                System.out.println("You don't have any Red properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[11]
                                return propertyRentValues[11];
                            case 2:
                                // Charging rent valueRentValues[12]
                                return propertyRentValues[12];
                            default:
                                // Charging rent valueRentValues[13]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[4] == 1) {
                            		return propertyRentValues[13] + 3;
                            	}
                            	else if (playerHouseHotels[4] == 2) {
                            		return propertyRentValues[13] + 7;
                            	}
                                return propertyRentValues[13];
                        }
                    }
                    else if (input == 2) { // Charging Yellow property
                        switch (playerProperties[5]) {
                            case 0:
                                System.out.println("You don't have any Yellow properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[14]
                                return propertyRentValues[14];
                            case 2:
                                // Charging rent valueRentValues[15]
                                return propertyRentValues[15];
                            default:
                                // Charging rent valueRentValues[16]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[5] == 1) {
                            		return propertyRentValues[16] + 3;
                            	}
                            	else if (playerHouseHotels[5] == 2) {
                            		return propertyRentValues[16] + 7;
                            	}
                                return propertyRentValues[16];
                        }
                    }
            	}
            case 3: // Green and Blue property rent
            	while (true) {
            		if (currentTurn == 1) {
                    	input = inputValidation("Enter 1 for (Green) and 2 for (Blue) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 2);
                    	if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
            		}
                    else {
                    	computerDecision[0] = 3; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                	if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
                    if (input == 1) { // Charging Green property
                        switch (playerProperties[6]) {
                            case 0:
                                System.out.println("You don't have any Green properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[17]
                                return propertyRentValues[17];
                            case 2:
                                // Charging rent valueRentValues[18]
                                return propertyRentValues[18];
                            default:
                                // Charging rent valueRentValues[19]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[6] == 1) {
                            		return propertyRentValues[19] + 3;
                            	}
                            	else if (playerHouseHotels[6] == 2) {
                            		return propertyRentValues[19] + 7;
                            	}
                                return propertyRentValues[19];
                        }
                    } 
                    else if (input == 2) { // Charging Blue property
                        switch (playerProperties[7]) {
                            case 0:
                                System.out.println("You don't have any Blue properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[20]
                                return propertyRentValues[20];
                            default:
                                // Charging rent valueRentValues[21]
                            	// Charges house and hotel fees if applicable.
                            	if (playerHouseHotels[7] == 1) {
                            		return propertyRentValues[21] + 3;
                            	}
                            	else if (playerHouseHotels[7] == 2) {
                            		return propertyRentValues[21] + 7;
                            	}
                                return propertyRentValues[21];
                        }
                    }
            	}
            case 4: // Utilities and Railroad property
            	while (true) {
            		if (currentTurn == 1) {
                    	input = inputValidation("Enter 1 for (Utilities) and 2 for (Railroad) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 2);
                    	if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
            		}
                    else {
                    	computerDecision[0] = 4; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                	if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
                    if (input == 1) { // Charging Utilities property
                        switch (playerProperties[8]) {
                            case 0:
                                System.out.println("You don't have any Utilities properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[22]
                                return propertyRentValues[22];
                            default:
                                // Charging rent valueRentValues[23]
                                return propertyRentValues[23];
                        }
                    }
                    else if (input == 2) { // Charging Railroad property
                        switch (playerProperties[9]) {
                            case 0:
                                System.out.println("You don't have any Railroad properties!");
                                return -1; // Invalid move allows user to redo their turn.
                            case 1:
                                // Charging rent valueRentValues[24]
                                return propertyRentValues[24];
                            case 2:
                                // Charging rent valueRentValues[25]
                                return propertyRentValues[25];
                            case 3:
                                // Charging rent valueRentValues[26]
                                return propertyRentValues[26];
                            default:
                                // Charging rent valueRentValues[27]
                                return propertyRentValues[27];
                        }
                    }

            	}
            case 5: // Multi-Colour property
            	while (true) {
            		if (currentTurn == 1) {
                		input = inputValidation("Enter a number from 1 for (Brown) through 10 for (Railroad) property rent. OPPONENT: Type 0 if you want to use Just Say No.", 0, 10);
                		if (input == 0) {
        					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
        					return -1;
        				}
            		}
                    else {
                    	computerDecision[0] = 5; 
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	input = computerDecision[1];
                    }
                	if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                    	return 0; // No money is charged.
                    }
    				// Switches to the case to the property which the user enters to charge the rent for the property.
    				switch (input) {
    					case 0:
    						if (justSayNo (input, opponentCards, "Property Rent") == 0) {
    		                	return 0; // No money is charged.
    		                }
    					case 1: // Charging Brown Properties
    						switch (playerProperties[0]) {
    						case 0:
    							System.out.println("You don't have any Brown properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[0];
    						case 2:
    							// Charging rent value
    							if (playerHouseHotels[0] == 1) {
    	                    		return propertyRentValues[1] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[0] == 2) {
    	                    		return propertyRentValues[1] + 7;
    	                    	}
    							return propertyRentValues[1];
    						}
    					case 2: // Charging Light Blue Properties
    						switch (playerProperties[1]) {
    						case 0:
    							System.out.println("You don't have any Light Blue properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[2];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[3];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[1] == 1) {
    	                    		return propertyRentValues[4] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[1] == 2) {
    	                    		return propertyRentValues[4] + 7;
    	                    	}
    							return propertyRentValues[4];
    						}
    					case 3: // Charging Pink Properties
    						switch (playerProperties[2]) {
    						case 0:
    							System.out.println("You don't have any Pink properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[5];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[6];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[2] == 1) {
    	                    		return propertyRentValues[7] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[2] == 2) {
    	                    		return propertyRentValues[7] + 7;
    	                    	}
    							return propertyRentValues[7];
    						}
    					case 4: // Charging Orange Properties
    						switch (playerProperties[3]) {
    						case 0:
    							System.out.println("You don't have any Orange properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[8];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[9];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[3] == 1) {
    	                    		return propertyRentValues[10] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[3] == 2) {
    	                    		return propertyRentValues[10] + 7;
    	                    	}
    							return propertyRentValues[10];
    						}
    					case 5: // Charging Red Properties
    						switch (playerProperties[4]) {
    						case 0:
    							System.out.println("You don't have any Red properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[11];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[12];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[4] == 1) {
    	                    		return propertyRentValues[13] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[4] == 2) {
    	                    		return propertyRentValues[13] + 7;
    	                    	}
    							return propertyRentValues[13];
    						}
    					case 6: // Charging Yellow Properties
    						switch (playerProperties[5]) {
    						case 0:
    							System.out.println("You don't have any Yellow properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[14];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[15];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[5] == 1) {
    	                    		return propertyRentValues[16] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[5] == 2) {
    	                    		return propertyRentValues[16] + 7;
    	                    	}
    							return propertyRentValues[16];
    						}
    					case 7: // Charging Green Properties
    						switch (playerProperties[6]) {
    						case 0:
    							System.out.println("You don't have any Green properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[17];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[18];
    						case 3:
    							// Charging rent value
    							if (playerHouseHotels[6] == 1) {
    	                    		return propertyRentValues[19] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[6] == 2) {
    	                    		return propertyRentValues[19] + 7;
    	                    	}
    							return propertyRentValues[19];
    						}
    					case 8: // Charging Blue Properties
    						switch (playerProperties[7]) {
    						case 0:
    							System.out.println("You don't have any Blue properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[20];
    						case 2:
    							// Charging rent value
    							if (playerHouseHotels[7] == 1) {
    	                    		return propertyRentValues[21] + 3;
    	                    	}
    	                    	else if (playerHouseHotels[7] == 2) {
    	                    		return propertyRentValues[21] + 7;
    	                    	}
    							return propertyRentValues[21];
    						}
    					case 9: // Charging Utilities Properties
    						switch (playerProperties[8]) {
    						case 0:
    							System.out.println("You don't have any Utilities properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[22];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[23];
    						}
    					case 10: // Charging Railroad Properties
    						switch (playerProperties[9]) {
    						case 0:
    							System.out.println("You don't have any Railroad properties!");
    							return -1; // Invalid move allows user to redo their turn.
    						case 1:
    							// Charging rent value
    							return propertyRentValues[24];
    						case 2:
    							// Charging rent value
    							return propertyRentValues[25];
    						case 3:
    							// Charging rent value
    							return propertyRentValues[26];
    						case 4:
    							// Charging rent value
    							return propertyRentValues[27];
    					}
    				}
            	}
            case 10: // Charging  Debt Collector
            	int debtCollector = 1;
            	if (currentTurn == 2) {
            		for (int i = 0; i < 10; i++) {
            			if (playerCards[i] == "Just Say No") {
            				debtCollector = inputValidation("OPPONENT: Type 0 if you want to use Just Say No, 1 if not.", 0, 1);
            			}
            		}
                }
            	
            	if (justSayNo (debtCollector, opponentCards, "Debt Collector") == 0) {
                	return 0; // No money is charged.
                }
            	return 5;
            case 11: // Charging It's My Birthday
            	int itsMyBirthday = 1;
            	if (currentTurn == 2) {
            		itsMyBirthday = inputValidation("OPPONENT: Type 0 if you want to use Just Say No, 1 if not.", 0, 1);
            	}

            	if (justSayNo (itsMyBirthday, opponentCards, "It's my Birthday") == 0) {
                	return 0; // No money is charged.
                }
            	return 2;
            case 12: // Charging Double the Rent
            	// Creates booleans scanning whether or not the inputs are valid.
            	boolean hasRentCard = false;
            	boolean validSelectedRent = false;
            	
            	// Scans whether or not the user actually has a rent card.
            	for (int i = 0; i < playerCardNum(playerCards); i++) {
            		for (int j = 0; j < 4; j++) {
            			if (playerCards[i].equals(deckCardNames[j])) {
                			hasRentCard = true;
                			System.out.print("/  " + (i+1) + "  " + playerCards[i] + "  /  ");
            			}
            			if (hasRentCard == true) {
            				break;
            			}
            		}
            		if (hasRentCard == true) {
            			break;
            		}
            	}
            	if (hasRentCard == false) {
            		return -1; // Invalid move allows user to redo their turn.
            	}
            	System.out.println();
            	
            	int rentCardInput = 0; // Sets a variable to scan user input for which rent card they want to use.
            	while (validSelectedRent == false) {
					rentCardInput = inputValidation("Enter the card number in your card hand for which rent card you want to use. OPPONENT: Type 0 if you want to use Just Say No.", 0, playerCards.length); // Scans the user input for a number from 1-10 and sees which property rent they want to double.
					if (rentCardInput == 0) {
    					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
    					return -1;
    				}
					if (justSayNo (input, opponentCards, "Double the Rent") == 0) {
	                	return 0;
	                }
					for (int i = 0; i < deckCardNames.length; i++) {
						System.out.println(playerCards[rentCardInput - 1]);
						if (playerCards[rentCardInput - 1].equals(deckCardNames[i]) && identifyCard(playerCards[rentCardInput-1]) <= 5) { // Checks for the rentCardInput-1 (the index of the card in the player hand) and compares it to the indexes of the deck.
							rentCardInput = i; // Sets the variable so that it has the index of the deck.
							validSelectedRent = true; // Validates the selected rent and allows the code to continue.
							break;
						}
					}
				}
				// Finds the doubled rent by going through the actionMoneyCard again.
				int doubledRent = (2 * actionMoneyCard(rentCardInput, playerCards, playerProperties, playerMoney, playerFullProperties, opponentProperties, opponentFullProperties, opponentCards, opponentMoney, computerDecision, deck, playerHouseHotels, opponentHouseHotels, currentTurn));
				if (doubledRent < 0) { // Returns -2 due to the doubled amount of -1 (invalid input). 
					return -1;
				}
				return doubledRent; // Returns the doubled value of the rent.
        }
        return 0; // No money is charged if all the cases above aren't run.
    }
    
    public static int actionStealCard (int identifiedCard, String [] playerCards, int [] playerProperties, int [] playerMoney, int [] playerHouseHotels, int [] playerFullProperties, int [] opponentProperties, int [] opponentFullProperties, String [] opponentCards, int [] computerDecision, int [] deck, int currentTurn) {
    	switch (identifiedCard) { // Switches between cases of the identified steal action card.
    		case 6: // Deal Breaker
    			// Creates variables for identifying the stolen property.
    			int stealingProperty = 0;
    			
    			// Asks the user what property they want to steal. Evaluates if computer and player has Just Say No.
				if (currentTurn == 1) {
					int hasJustSayNo = 0;
					for (int i = 0; i < 10; i++) {
						if (opponentCards[i] == "Just Say No") {
							hasJustSayNo = 1;
						}
					}
					int doubleNo = 0;
					if (hasJustSayNo == 1) {
						System.out.println("Type 1 through 10 (Brown through Railroad) to pick which full set to steal. OPPONENT: Type 0 to use Just Say No.");
						computerAI(opponentCards, playerCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 4);
						for (int i = 0; i < playerCardNum(playerCards); i++) {
							if (identifyCard(playerCards[i]) == 9) {
								System.out.println();
								System.out.println();
								System.out.println("You have a Just Say No card!");
								int justSayNo2 = inputValidation("Enter 1 to use Just Say No on the computer's Just Say No. Enter 0 to not use Just Say No.", 0, 1);
								if (justSayNo2 == 1) {
									for (int j = i; j < playerCards.length - 1; j++) {
				                        playerCards[j] = playerCards[j + 1];
				                        playerCards[j + 1] = null;
				                        if (j == playerCards.length - 1) {
				                        	playerCards[j] = null;
				                        }
				                    }
									doubleNo = 1;
								}
							}
						}
						if (doubleNo != 1) {
							System.out.println();
							inputValidation("Enter 0 to continue.", 0, 0);
							return 0;
						}
					}
					stealingProperty = inputValidation("Type the full set's number (on the gameboard) of the opponent's properties to steal (17-26). OPPONENT: Type 0 to use Just Say No.", 17, 26);
					stealingProperty = stealingProperty - 17;
				}
				else {
					int hasJustSayNo = 0;
					for (int i = 0; i < 10; i++) {
						if (opponentCards[i] == "Just Say No") {
							hasJustSayNo = 1;
						}
					}
					if (hasJustSayNo == 1) {
						int useNo = inputValidation("OPPONENT: Type 0 to use Just Say No. 1 to not use.", 0, 1);
						if (useNo == 0) {
							stealingProperty = 0;
						}
						else {
							computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
							stealingProperty = computerDecision[1];
						}
					}
				}
				
				switch (stealingProperty) { // Identifies the property that the user wants to steal and runs the case.
					case 0: // Just Say No is entered.
						if (justSayNo (stealingProperty, opponentCards, "Deal Breaker") == 0) {
		                	return 0; // No property is stolen.
		                }
						break;
					case 1: // Brown property
						if (opponentProperties[0] >= 2) {
		    				System.out.println("Stealing full set of Brown Property!");
		    				opponentProperties[0] -= 2;
		    				playerProperties[0] += 2;
		    			}
						break;
					case 2: // Light Blue property
						if (opponentProperties[1] >= 3) {
		    				System.out.println("Stealing full set of Light Blue Property!");
		    				opponentProperties[1] -= 3;
		    				playerProperties[1] += 3;
		    			}
						break;
					case 3: // Pink property
						if (opponentProperties[2] >= 3) {
		    				System.out.println("Stealing full set of Pink Property!");
		    				opponentProperties[2] -= 3;
		    				playerProperties[2] += 3;
		    			}
						break;
					case 4: // Orange property
						if (opponentProperties[3] >= 3) {
		    				System.out.println("Stealing full set of Orange Property!");
		    				opponentProperties[3] -= 3;
		    				playerProperties[3] += 3;
		    			}
						break;
					case 5: // Red property
						if (opponentProperties[4] >= 3) {
		    				System.out.println("Stealing full set of Red Property!");
		    				opponentProperties[4] -= 3;
		    				playerProperties[4] += 3;
		    			}
						break;
					case 6: // Yellow property
						if (opponentProperties[5] >= 3) {
		    				System.out.println("Stealing full set of Yellow Property!");
		    				opponentProperties[5] -= 3;
		    				playerProperties[5] += 3;
		    			}
						break;
					case 7: // Green property
						if (opponentProperties[6] >= 3) {
		    				System.out.println("Stealing full set of Green Property!");
		    				opponentProperties[6] -= 3;
		    				playerProperties[6] += 3;
		    			}
						break;
					case 8: // Blue property
						if (opponentProperties[7] >= 3) {
		    				System.out.println("Stealing full set of Blue Property!");
		    				opponentProperties[7] -= 3;
		    				playerProperties[7] += 3;
		    			}
					case 9: // Utilities property
						if (opponentProperties[8] >= 2) {
		    				System.out.println("Stealing full set of Utilities Property!");
		    				opponentProperties[8] -= 2;
		    				playerProperties[8] += 2;
		    			}
						break;
					case 10: // Railroad property
						if (opponentProperties[9] >= 4) {
		    				System.out.println("Stealing full set of Railroad Property!");
		    				opponentProperties[9] -= 4;
		    				playerProperties[9] += 4;
		    			}
						break;
					default: // Nothing is stolen if all the above cases are false.
						System.out.println("Nothing stolen! Invalid decision due to property set not being a full set.");
						return -1; // Invalid move allows user to redo their turn.
				}
				break;
    		case 7: // Forced Deal
    			int forcedDeal1 = 0;
    			if (currentTurn == 1) {
    				forcedDeal1 = inputValidation("Enter the number from opponent's property (on the gameboard) that you want to trade (17-26). OPPONENT: Type 0 to use Just Say No.", 17, 26);
    				if (forcedDeal1 == 0) {
    					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
    					return -1;
    				}
    			}
    			else {
					int hasJustSayNo = 0;
					for (int i = 0; i < 10; i++) {
						if (opponentCards[i] == "Just Say No") {
							hasJustSayNo = 1;
						}
					}
					if (hasJustSayNo == 1) {
						int useNo = inputValidation("OPPONENT: Type 0 to use Just Say No. 1 to not use.", 0, 1);
						if (useNo == 0) {
							forcedDeal1 = 0;
						}
						else {
							computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
							forcedDeal1 = computerDecision[1];
						}
					}
				}
    			// Runs to evaluate whether or not the traded property is valid.
    			boolean isValid1 = false;
    			do {
        			isValid1 = true;
        			if (currentTurn == 1) {
        				opponentProperties[forcedDeal1 - 17]--;
            			if (opponentProperties[forcedDeal1 - 17] < 0) {
            				isValid1 = false;
            				opponentProperties[forcedDeal1 - 17]++;
            				System.out.println("Invalid decision!");
            				return -1;
            			}
            			playerProperties[forcedDeal1 - 17]++;
        			}
        			else {
        				opponentProperties[forcedDeal1 - 1]--;
        				if (opponentProperties[forcedDeal1 - 1] < 0) {
	        				isValid1 = false;
	        				opponentProperties[forcedDeal1 -1]++;
	        				System.out.println("Invalid decision!");
	        				return -1;
        				}
        				playerProperties[forcedDeal1 - 1]++;
        			}
        			
    			} while (isValid1 == false);
    			// Invalid move allows user to redo their turn.
    			
    			
    			
    			
    			
    			// Part 2: choosing which property to give.
    			int forcedDeal2 = 0;
    			if (currentTurn == 1) {
    				forcedDeal2 = inputValidation("Enter the number from your property (on the gameboard) that you want to trade (1-10). OPPONENT: Type 0 to use Just Say No.", 0, 10);
    				if (forcedDeal2 == 0) {
    					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
    					return -1;
    				}
    			}
    			else {
					int hasJustSayNo = 0;
					for (int i = 0; i < 10; i++) {
						if (opponentCards[i] == "Just Say No") {
							hasJustSayNo = 1;
						}
					}
					if (hasJustSayNo == 1) {
						int useNo = inputValidation("OPPONENT: Type 0 to use Just Say No. 1 to not use.", 0, 1);
						if (useNo == 0) {
							forcedDeal2 = 0;
						}
						else {
							computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
							forcedDeal2 = computerDecision[1];
						}
					}
				}
    			boolean isValid2 = false;
    			do {
    				isValid2 = true;
    				if (currentTurn == 1) {
    					playerProperties[forcedDeal2 - 1]--;
            			if (playerProperties[forcedDeal2 - 1] < 0) {
            				isValid2 = false;
            				playerProperties[forcedDeal2 - 1]++;
            				System.out.println("Invalid decision!");
            				return -1;
            			}
            			opponentProperties[forcedDeal2 - 1]++;
    				}
    				else {
    					playerProperties[forcedDeal2 - 17]--;
            			if (playerProperties[forcedDeal2 - 17] < 0) {
            				isValid2 = false;
            				playerProperties[forcedDeal2 - 17]++;
            				System.out.println("Invalid decision!");
            				return -1;
            			}
            			opponentProperties[forcedDeal2 - 17]++;
    				}
    			} while (isValid2 == false);
    			if (justSayNo (forcedDeal2, opponentCards, "Forced Deal") == 0) {
                	return 0; // No property is stolen.
                }
    			break;
    		case 8: // Sly Deal
    			// Prompts user to enter a number from opponent's property from 1 to 10 since there are 10 different possible colours.
    			int slyDeal = 0;
    			if (currentTurn == 1) {
    				slyDeal = inputValidation("Enter the number from opponent's property (on the gameboard) that you want to steal.", 17, 26);
    				if (slyDeal == 0) {
    					System.out.println("Hey, you can't choose to use the computer's Just Say No card!");
    					return -1;
    				}
    			}
    			else {
    				computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
    				slyDeal = computerDecision[1];
    			}
    			if (justSayNo (slyDeal, opponentCards, "Sly Deal") == 0) {
                	return 0; 
                }
    			
				if (slyDeal == 1 || slyDeal == 8 || slyDeal == 9 || slyDeal == 17 || slyDeal == 24 || slyDeal == 25) {
					if (opponentProperties[slyDeal-1] == 2) {
						System.out.println("They have a full set! You can't steal from a full set.");
						return -1;
					}
				}
				else if (slyDeal == 10 || slyDeal == 26) {
					if (opponentProperties[slyDeal-1] == 4) {
						System.out.println("They have a full set! You can't steal from a full set.");
						return -1;
					}
				}
				else {
					if (opponentProperties[slyDeal-1] == 3) {
						System.out.println("They have a full set! You can't steal from a full set.");
						return -1;
					}
				}
				
				// Subtracts from the opponent depending on which turn it is. 
				if (currentTurn == 1) {
					if (opponentProperties[slyDeal - 17] < 0) {
	    				isValid1 = false;
	    				opponentProperties[slyDeal - 17]++;
	    				System.out.println("Invalid decision!");
	    				return -1;
	    			}
					opponentProperties[slyDeal - 17]--;
					playerProperties[slyDeal - 17]++;
				}
				else {
					if (opponentProperties[slyDeal - 1] < 0) {
	    				isValid1 = false;
	    				opponentProperties[slyDeal - 1]++;
	    				System.out.println("Invalid decision!");
	    				return -1;
	    			}
					opponentProperties[slyDeal - 1]--;
					playerProperties[slyDeal - 1]++;
				}
				break;
    	}
    	return 0; // No monetary value is charged. 
    }
    
    public static int playCards(String [] playerCards, int [] playerMoney, int [] deck, int [] deckLength, int[] playerProperties, int [] playerFullProperties, int [] opponentMoney, int [] opponentProperties, int [] opponentFullProperties, String [] opponentCards, int [] playerHouseHotels, int [] opponentHouseHotels, int [] computerDecision, int currentTurn) throws Exception {
    	int playerCardNum = 0; // Sets a variable to store the amount of non-null value player cards in the player's hand.
    	int turnCounter = 0; // Sets a variable to store the amount of turns that passed for statistics.
    	
    	for (int i = 3; i > 0; i--) { // Runs a for loop that goes up to 3 times since a player can play up to 3 turns.
        	// Ensures that the player's statistics will always still show up at the top of the gameboard before computer statistics.
    		if (currentTurn == 1) {
        		gameBoard(playerProperties, playerMoney, opponentProperties, opponentMoney, playerHouseHotels, opponentHouseHotels, playerFullProperties, opponentFullProperties); // Updates the game board. 
        		System.out.println(" _______ _______  ______ ______  _______\r\n"
            			+ " |       |_____| |_____/ |     \\ |______\r\n"
            			+ " |_____  |     | |    \\_ |_____/ ______|"); // Tells user their cards.
    		}
    		else {
        		gameBoard(opponentProperties, opponentMoney, playerProperties, playerMoney, opponentHouseHotels, playerHouseHotels, opponentFullProperties, playerFullProperties); // Updates the game board. 
        		for (int j = 0; j < 3; j++) {
        			System.out.println();
        		}
    		}
        	
            
        	playerCardNum = playerCardNum(playerCards); // Sets the amount off non-null value player cards to playerCardNum.
            if (playerCardNum == 0) { // If the player has no cards, the turn is ended.
            	System.out.println("Looks like you have no cards! Ending turn.");
            	return turnCounter; // Returns the amount of turns that has passed.
            }
            
            // Resetting computerDecision variables
            for (int j = 0; j < computerDecision.length; j++) {
            	computerDecision[j] = 0;
            }
            
            boolean isValid = false; // Sets a variable for evaluating whether the user input was a valid input.
            // Evaluating validity of the money card/rent.
        	boolean validInput = true;
            while (isValid == false) { // Continues this loop while the input is invalid.
                displayCards(playerCards, opponentCards, currentTurn); // Displays the user's cards.
                
                int input = 0;
                if (currentTurn == 1) {
                	input = inputValidation("You have " + i + " turn(s) left. Enter from 1 to " + playerCardNum + " to play a card. 0 to end your turn", 0, playerCardNum);
                }
                else {
                	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 1);
                	input = computerDecision[0];
                }
                
                if (input == 0) { // Checks if the user input was "stop" or stop.
                    System.out.println("Ending turn!"); // Informs user that their turn is ending.
                    if (playerCardNum > 7) { // Limits the amount of cards the user has if they have over 7.
                        if (currentTurn == 1) {
                        	cardLimit(playerCards, deck);
                        }
                        else {
                        	computerCardLimit(playerCards, deck);
                        }
                    }
                    return turnCounter; // Stops running method since the user does not want to play more cards.
                }
                
                int index = input - 1; // Sets a variable for identifying the index of the card in static variables (since the input is 1 more than the actual index, which starts at 0).
                int action = 0; // Sets a variable to determine whether the user wants to use action card (if played) as money or to use it.
                
                int identifiedCard = identifyCard(playerCards[index]); // Identifies the played card and assigns it to an integer to store it. It is the index of deckCardNames, which is how the card is identified.
                
                // If the card is an action card (from indexes 0 to 15), this code is run.
                if ((identifiedCard >= 0) && (identifiedCard <= 15)) {
                	// Allows user to choose to use the action card as money or to play it.
                    if (currentTurn == 1) {
                    	action = inputValidation("Enter 1 to play the card, 2 to use the card as money.", 1, 2);
                    }
                    else {
                    	computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                    	action = computerDecision[0]; // Changes depending on what computerDecision[0] used to be and after it goes through the computerAI method.
                    }
                    // Runs if the user decides to use the card.
                    if (action == 1) {
                    	// Identifies if the card used is a card that requires the opponent to pay money.
                        if ((identifiedCard >= 0 && identifiedCard <= 5) || (identifiedCard >= 10 && identifiedCard <= 12)) {
                        	// Identifies the specific amount charged by using the method actionMoneyCard.
                        	int amountCharged = actionMoneyCard(identifiedCard, playerCards, playerProperties, playerMoney, playerFullProperties, opponentProperties, opponentFullProperties, opponentCards, opponentMoney, computerDecision, deck, playerHouseHotels, opponentHouseHotels, currentTurn);
                        	
                        	// Runs if nothing is charged or the opponent used Just Say No.
                        	if (amountCharged == 0) {
                        		System.out.println("Nothing was charged.");
                        	}
                        	// Runs if the user entered an invalid input.
                        	else if (amountCharged == -1) {
                        		System.out.println("Invalid input due to user not having the card! Please redo your turn.");
                        		// Allows user to redo their turn.
                        		validInput = false;
                        		break;
                        	}
                        	// Runs if something was charged.
                        	else {
                        		int opponentTotal = 0; // Sets a variable to see if the opponent has any cards worth any value.
                        		
                        		// Adds the total amount of money that the opponent has.
                        		for (int j = 0; j < opponentMoney.length; j++) {
                        			for (int k = 0; k < opponentMoney[j]; k++) {
                        				opponentTotal += j+1; // Adds the value of j+1 (1 for the shift, otherwise j would be 1 less than needed) for how many there are in that index of opponentMoney[j] (evaluated by k).
                        			}
                        		}
                        		for (int j = 0; j < opponentProperties.length; j++) {
                        			int additionalValue = 0;
                        			for (int k = 0; k < 10; k++) {
                        				if (j == 7 || j == 8) {
                        					additionalValue += 2;
                        				}
                        				else if (j < 7 && j > 0) {
                        					additionalValue += 3;
                        				}
                        				else if (j == 9) {
                        					additionalValue += 4;
                        				}
                        			}
                        			if (opponentProperties[j] > 0) { // Makes sure there is one of the property to actually add the value.
                        				opponentTotal += deckCardValues[additionalValue + 16] * opponentProperties[j]; // Adds the value of the opponent properties and multiplies it by how many properties they have.
                        				System.out.println((deckCardValues[additionalValue + 16] * opponentProperties[j]) + " is deckcardvalue");
                        			}
                        		}
                        		
                        		// If the opponent has no money or property, they aren't charged anything.
                        		if (opponentTotal == 0) {
                        			System.out.println("Nothing was charged due to opponent not having any money...");
                        		}
                        		
                        		// Otherwise, if they do have money or property, they are charged. 
                        		else {
                        			System.out.println("Charging " + amountCharged + "M."); // Informs the user on how much money is charged.
                        			boolean validPayment = false; // Sets variable to allow while loop to keep running until all is paid (no refund)
                                    while (amountCharged > 0) {
                                    	while (validPayment == false) { // Runs while the payment isn't valid or isn't completed yet.
                                			int opponentPay = 0; // Creates a variable to evaluate the amount paid.
                                			if (currentTurn == 2) { // Checks if it's the computer's turn since the player is the opponent at that turn and has to pay.
                                    			opponentPay = inputValidation("OPPONENT: Please pay " + amountCharged + " with money or property (enter numbers accordingly to gameboard). OPPONENT: Type 0 if you want to use Just Say No", 0, 16);
                                    		}
                                			else {
                                				computerAI(opponentCards, playerCards, opponentProperties, opponentHouseHotels, opponentMoney, amountCharged, playerFullProperties, playerProperties, computerDecision, deck, currentTurn, 3);
                                				System.out.println("OPPONENT: Please pay " + amountCharged + "M with money or property (enter numbers accordingly to gameboard).");
                                				opponentPay = computerDecision[0];
                                				System.out.println(opponentPay);
                                			}
                                			
                                    		// If the opponent uses Just Say No, no payment is needed.
                                    		if (justSayNo (input, opponentCards, "Property Rent") == 0) {
                                            	break; // Opponent doesn't have to pay if they use Just Say No.
                                            }
                                    		
                                    		int temp = 0; // Sets a temporary variable to the amount that the opponent paid. 
                                    		if (currentTurn == 1) {
                                    			temp = opponentPay-17; // Subtracts by 17 so that the property number is found.
                                    		}
                                    		else {
                                    			temp = opponentPay; // Sets the temporary variable to the opponent property number.
                                    		}
                                    		
                                    		int propertyValue = 0; // Sets propertyValue to 0 since it starts off assuming that the property is Brown (if opponentPay is 1). Adds depending on how many properties of each are there to make a full set. 
                                    		int counter = 0;
                                    		
                                    		// Runs if opponent pays with property to evaluate the value of the property.
                                    		if ((temp >= 1 && temp <= 10) || (temp >= 17 && temp <= 26)) {
                                    			while (temp > 0) { // Runs this code until the index of the property value is found. Scrolls through the colour types to check. 
                                        			switch (counter) {
                                        			// Adding to property value so it can be used as an index for deckCardValues in the future. 
                                        			// Goes through all colour properties until temp reaches 1 (meaning that the property is Railroad if it does hit 1).
                                        				case 0:
                                        					temp--;
                                        					propertyValue += 2;
                                        					counter++;
                                        				case 1:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 2:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 3:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 4:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 5:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 6:
                                        					temp--;
                                        					propertyValue += 3;
                                        					counter++;
                                        					break;
                                        				case 7:
                                        					temp--;
                                        					propertyValue += 2;
                                        					counter++;
                                        					break;
                                        				case 8:
                                        					temp--;
                                        					propertyValue += 2;
                                        					counter++;
                                        					break;
                                        				case 9:
                                        					temp--;
                                        					propertyValue += 4;
                                        					counter++;
                                        					break;
                                        			}
                                        		}
                                    		}
                                    		
                                    		// Runs for when the computer requires the player to pay money/property.
                                    		if (currentTurn == 2) { 
                                    			if (opponentPay > 0 && opponentPay <= 10) { // Runs if the current player is player paying property cards.
                                    				// Uses opponent since the player is paying (in this case, player is the opponent)
                                    				int payProperty = deckCardValues[propertyValue + 16]; // Sets payProperty to the value of the property being paid.
                                        			opponentProperties[opponentPay-1]--; // Subtracts 1 from the opponentPay-1(uses this to find the index of the player property)
                                        			
                                        			if (opponentProperties[opponentPay-1] < 0) { // If the player doesn't have the property owned, this runs.
                                        				opponentProperties[opponentPay-1]++; // Refunds the player the non-existent property that they tried to pay.
                                        				System.out.println("That is not a valid input!"); // Informs player that they don't have the property.
                                        				continue; // Skips the rest of the code since the player input was invalid.
                                        			}
                                        			else {
                                        				playerProperties[(opponentPay-1)]++; // Gives the computer the property if it is a valid input.
                                        				amountCharged -= payProperty; // Subtracts the property value from the amount due. 
                                        				opponentTotal -= payProperty; // Deducts the property value from the total amount of money the opponent has.
                                        				validPayment = true;
                                        			}
                                        		}
                                        		else if (opponentPay >= 11 && opponentPay <= 16) { // Runs if the current player is player paying money cards.
                                        			int payMoney = deckCardValues[(opponentPay-1) + 42]; // Sets payMoney to the value of the money being paid.
                                        			opponentMoney[opponentPay-1 - 10]--; // Subtracts 1 from the opponentPay-1(uses this to find the index of the player property). -10 is used to find the array index of the money.
                                        			
                                        			if (opponentMoney[opponentPay-1] < 0) { // If the player doesn't have the money owned, this runs.
                                        				opponentMoney[opponentPay-1]++; // Refunds the player the non-existent money that they tried to pay.
                                        				System.out.println("That is not a valid input!"); // Informs player that they don't have the money.
                                        				continue; // Skips the rest of the code since the player input was invalid.
                                        			}
                                        			else {
                                        				playerMoney[opponentPay-1]++; // Gives the opponent the money that the player paid.
                                        				amountCharged -= payMoney; // Subtracts the payment from the amount due. 
                                        				opponentTotal -= payMoney; // Deducts the money value from the total amount of money the opponent has.
                                        				validPayment = true;
                                        			}
                                        		}
                                    		}
                                    		
                                    		// Runs for when the player requires the computer to pay.
                                    		else if (currentTurn == 1) {
                                    			if (opponentPay >= 17 && opponentPay <= 26) { // Runs if the opponent is paying property cards.
                                        			// Uses opponent since the computer is the opponent in this case.
                                    				int payProperty = deckCardValues[propertyValue + 16];
                                    				opponentProperties[(opponentPay-17)]--;
                                        			playerProperties[(opponentPay-17)]++; // Gives the player the property if it is a valid input.
                                        			amountCharged -= payProperty; // Subtracts the property value from the amount due. 
                                        			opponentTotal -= payProperty; // Deducts the property value from the total amount of money the opponent has.
                                        			validPayment = true;
                                    			}
                                    			else if (opponentPay >= 27 && opponentPay <= 32) { // Runs if the current player is opponent paying money cards.
                                        			int payMoney = deckCardValues[(opponentPay-1) + 26]; // Sets the value of payMoney with the index of deckCardValues[opponentPay-1 + 25] because the number entered is going to shift. 
                                        			playerMoney[opponentPay-27]++; // Gives the player the money that the player paid.
                                        			amountCharged -= payMoney; // Subtracts the payment from the amount due. 
                                        			opponentTotal -= payMoney; // Deducts the money value from the total amount of money the opponent has.
                                        			validPayment = true;
                                    			}
                                    		}
                                        	
                                        	// Checks if there's still more money due and opponent doesn't have enough to pay it off.
                                        	if (amountCharged > 0) { 
                                        		validPayment = false;
                                        		if (opponentTotal <= 0) {
                                        			System.out.println("Opponent cannot pay any more due to them having no money or property!");
                                        			amountCharged = 0; // Stops payment charging.
                                        			validPayment = true;
                                        		}
                                        	}
                                		}
                                    }
                        		}
                        	}
                        }
                        // Runs if a stealing card is used.
                        else if (identifiedCard >= 6 && identifiedCard <= 8) {
                        	// Runs the method actionStealCard since the user has played an action card that steals from the opponent.
                        	int stealCard = 0;
                        	if (currentTurn == 1) {
                        		stealCard = actionStealCard(identifiedCard, playerCards, playerProperties, playerMoney, playerHouseHotels, playerFullProperties, opponentProperties, opponentFullProperties, opponentCards, computerDecision, deck, currentTurn);
                        	}
                        	else {
                        		computerDecision[0] = identifiedCard;
                        		computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                        		stealCard = actionStealCard(computerDecision[0], playerCards, playerProperties, playerMoney, playerHouseHotels, playerFullProperties, opponentProperties, opponentFullProperties, opponentCards, computerDecision, deck, currentTurn);
                        	}
                        	
                        	// Runs if the user entered an invalid input.
                        	if (stealCard == -1) {
                        		// Allows user to re-choose a card to play
                        		continue;
                        	}
                        	
                        	// Runs if the user uses a Just Say No on a Just Say No.
                        	if (stealCard == -2) {
                        		i--;
                        		continue;
                        	}
                        }
                        
                        // Runs if the user tries to use Just Say No by itself.
                        else if (identifiedCard == 9) { 
                        	System.out.println("You cannot use this card by itself!"); 
                        	// Allows user to re-choose a card to play
                        	continue;
                        }
                        
                        // Runs if the user uses a house.
                        else if (identifiedCard == 13) { // Identifies if the card played is a house
                        	int houseInput = 0;
                        	if (currentTurn == 1) {
                        		houseInput = inputValidation("Enter a number from 1 through 8 (Brown through Blue) for which Full Set you want to put a house on.", 1, 8); // Doesn't allow user to place property on Utilities or Railroads. 
                        	}
                        	else {
                        		computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                        		houseInput = computerDecision[1];
                        	}
                        	
                        	if (houseInput == 1 || houseInput == 8) { // Checks if the property that the user wants to place a house on is Brown or Blue since their full set is 2 properties. 
                        		if (playerProperties[houseInput-1] >= 2) { // If there are 2 properties on Brown or Blue
                        			// If there are no houses, the code adds a house to the full set.
                        			if (playerHouseHotels[houseInput-1] == 0) {
                        				playerHouseHotels[houseInput-1] = 1;
                        			}
                        			// Otherwise, it runs this code.
                        			else {
                        				System.out.println("You already have a house on that property! You can only place 1 house on a full set.");
                        				// Allows user to rechoose a card to play 
                        				continue;
                        			}
                        		}
                        		else {
                        			System.out.println("Invalid input! You don't have that property!");
                        			continue;
                        		}
                        	}
                        	else if (houseInput > 1 && houseInput < 8) { // Checks if the property that the user wants to place a house on is from Light Blue to Green since thier full set is 3 properties.
	                        	if (playerProperties[houseInput-1] >= 3) { // Checks if there are 3 properties from Light Blue to Green
	                        		// If there are no houses, the code adds a house to the full set.
	                    			if (playerHouseHotels[houseInput-1] == 0) {
	                    				playerHouseHotels[houseInput-1] = 1;
	                    			}
	                    			// Otherwise, it runs this code.
	                    			else {
	                    				System.out.println("You already have a house on that property! You can only place 1 house on a full set.");
	                    				// Allows user to re-choose a card to play
	                    				continue;
	                    			}
	                        	}
	                        	else {
                        			System.out.println("Invalid input! You don't have that property!");
                        			continue;
	                        	}
                    		}
                        }
                        
                        // Runs if the user uses a hotel.
                        else if (identifiedCard == 14) { // Identifies if the card played is a hotel
                        	int hotelInput = 0;
                        	if (currentTurn == 1) {
                        		hotelInput = inputValidation("Enter a number from 1 through 8 (Brown through Blue) for which Full Set you want to put a hotel on.", 1, 8);
                        	}
                        	else {
                        		computerAI(playerCards, opponentCards, playerProperties, playerHouseHotels, playerMoney, 0, opponentFullProperties, opponentProperties, computerDecision, deck, currentTurn, 2);
                        		hotelInput = computerDecision[1];
                        	}
                        	
                        	if (hotelInput == 1 || hotelInput == 8) { // Checks if the property that the user wants to place a house on is Brown or Blue since their full set is 2 properties. 
                        		// Checks if the Brown or Blue property is a full set
                        		if (playerProperties[hotelInput-1] >= 2) {
                        			// Checks if the user has a house on the full set.
                        			if (playerHouseHotels[hotelInput-1] == 0) {
                        				System.out.println("You don't have a house on that property! You cannot place a hotel before placing a house!");
                        				// Allows user to re-choose a card to play
                        				continue;
                        			}
                        			// If they do have a house, then add a hotel onto the property. 
                        			else if (playerHouseHotels[hotelInput-1] == 1) {
                        				playerHouseHotels[hotelInput-1] = 2;
                        			}
                        			// If none of the above are run, they already have a hotel.
                        			else {
                        				System.out.println("You already have a hotel on that property! You can only place 1 hotel on a full set.");
                        				// Allows user to re-choose a card to play
                        				continue;
                        			}
                        		}
                        		else {
                        			System.out.println("Invalid input! You don't have that property!");
                        		}
                        	}
                        	else if (hotelInput > 1 && hotelInput < 8) { // Checks if the property that the user wants to place a house on is Light Blue through Green since their full set is 3 properties.
                        		// Checks if the sets are fully completed.
                        		if (playerProperties[hotelInput-1] >= 3) {
                        			// Checks if there is a house on the full set of property.
                        			if (playerHouseHotels[hotelInput-1] == 0) {
                        				System.out.println("You don't have a house on that property! You cannot place a hotel before placing a house!");
                        				// Allows user to re-choose a card to play
                        				continue;
                        			}
                        			// If there is a house on the property, the code places a hotel onto it.
                        			else if (playerHouseHotels[hotelInput-1] == 1) {
                        				playerHouseHotels[hotelInput-1] = 2;
                        			}
                        			// If none of the above are run, they already have a hotel.
                        			else {
                        				System.out.println("You already have a hotel on that property! You can only place 1 hotel on a full set.");
                        				// Allows user to re-choose a card to play
                        				continue;
                        			}
                        		}
                        		else {
                        			System.out.println("Invalid input! You don't have that property!");
                        		}
                    		}
                        }
                        // Runs if the user uses Pass Go.
                        else if (identifiedCard == 15) { // Identifies if the card played is Pass Go.
                        	for (int k = index; k < playerCards.length - 1; k++) {
                                playerCards[k] = playerCards[k + 1];
                                playerCards[k + 1] = null;
                                if (k == playerCards.length - 1) {
                                	playerCards[k] = null;
                                }
                            }
                        	dealCards(2, playerCards, deck, deckLength); // Adds 2 cards to the user's deck.
                        }
                        for (int k = 0; k < deckCardNames.length; k++) { // Runs this to check through all the possibilities of the played card.
                            if (playerCards[index].equals(deckCardNames[k])) { // Checks if the player card is the same as the names of the deck cards' index.
                                deck[k] += 1; // Replaces the player's card back into the deck with the correct index to identify the card.
                            }
                        }
                        if (identifiedCard == 15) {
                        	break;
                        }
                    }
                    
                    else if (action == 2) { // If the user wants to place the action card as money instead, this code runs.
                    	// Finds the index of the card (deckCardValues-1 for index to be added in playerMoney) and adds 1 to the player money
                    	// Displays different value since it's only displaying; the index isn't needed
                        playerMoney[deckCardValues [identifyCard (playerCards [index])] - 1]++; // Adds the value of the played card by using the identifiedCard as the index to find the value in deckCardValues. -1 for the index.
                        System.out.println(deckCardValues [identifyCard (playerCards [index])] + "M added from action card used as money."); // Tells the user the value added to their money count.
                    }
                }
                
                
                // Runs this if the card played is a property
                if ((identifiedCard >= 16) && (identifiedCard <= 51)) { 
                    // Adding properties to the played cards for regular properties (singular coloured properties).
                	// Adds 1 to the colour since they placed one of the properties within the specified indexes.
                    if (identifiedCard >= 16 && identifiedCard <= 17) {
                        playerProperties[0]++;
                    }
                    else if (identifiedCard >= 18 && identifiedCard <= 20) {
                        playerProperties[1]++;
                    }
                    else if (identifiedCard >= 21 && identifiedCard <= 23) {
                        playerProperties[2]++;
                    }
                    else if (identifiedCard >= 24 && identifiedCard <= 26) {
                        playerProperties[3]++;
                    }
                    else if (identifiedCard >= 27 && identifiedCard <= 29) {
                        playerProperties[4]++;
                    }
                    else if (identifiedCard >= 30 && identifiedCard <= 32) {
                        playerProperties[5]++;
                    }
                    else if (identifiedCard >= 33 && identifiedCard <= 35) {
                        playerProperties[6]++;
                    }
                    else if (identifiedCard >= 36 && identifiedCard <= 37) {
                        playerProperties[7]++;
                    }
                    else if (identifiedCard >= 38 && identifiedCard <= 39) {
                        playerProperties[8]++;
                    }
                    else if (identifiedCard >= 40 && identifiedCard <= 43) {
                        playerProperties[9]++;
                    }

                    int bicolorInput = 0; // Creates a variable for the input that the user puts for which property they want to use.
                    // Selecting properties and adding them to the played properties for bicolor and wild cards
                    // Allows user to input which colour they want to place down.
                    if (identifiedCard == 44) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Brown) or 2 for (Light Blue)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1]; // Already set when computer picks the card, so computerAI() isn't needed again.
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[0]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[1]++;
                        }
                    }
                    else if (identifiedCard == 45) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Pink) or 2 for (Orange)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[2]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[3]++;
                        }
                    }
                    else if (identifiedCard == 46) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Red) or 2 for (Yellow)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[4]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[5]++;
                        }
                    }
                    else if (identifiedCard == 47) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Green) or 2 for (Blue)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[6]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[7]++;
                        }
                    }
                    else if (identifiedCard == 48) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Green) or 2 for (Railroad)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[6]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[9]++;
                        }
                    }
                    else if (identifiedCard == 49) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Railroad) or 2 for (Utilities)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[9]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[8]++;
                        }
                    }
                    else if (identifiedCard == 50) {
                    	if (currentTurn == 1) {
                    		bicolorInput = inputValidation("Input 1 for (Light Blue) or 2 for (Railroad)", 1, 2);
                    	}
                    	else {
                    		bicolorInput = computerDecision[1];
                    	}
                    	
                        if (bicolorInput == 1) {
                            playerProperties[1]++;
                        } 
                        else if (bicolorInput == 2) {
                            playerProperties[9]++;
                        }
                    }
                    else if (identifiedCard == 51) {
                    	int wildCardInput = 0; 
                    	if (currentTurn == 1) {
                    		wildCardInput = inputValidation("Input 1 for Brown, 2 for Light Blue, 3 for Pink, 4 for Orange, 5 for Red, 6 for Yellow, 7 for Green, 8 for Blue, 9 for Utilities, 10 for Railroad.", 1, 10);
                    	}
                    	else {
                    		wildCardInput = computerDecision[1];
                    	}
                    	playerProperties[wildCardInput-1]++;
                    }
                }
                
                // Identifies if the card is a money card and adds it to the playerMoney variable with the respective indexes.
                if ((identifiedCard >= 52) && (identifiedCard <= 57)) {
                	if (identifiedCard == 52) {
                		playerMoney[0]++; 
                	}
                	else if (identifiedCard == 53) {
                		playerMoney[1]++;
                	}
                	else if (identifiedCard == 54) {
                		playerMoney[2]++;
                	}
                	else if (identifiedCard == 55) {
                		playerMoney[3]++;
                	}
                	else if (identifiedCard == 56) {
                		playerMoney[4]++;
                	}
                	else if (identifiedCard == 57) {
                		playerMoney[5]++;
                	}
                }
                if (currentTurn == 1) {
                	System.out.println("You have played the " + playerCards[index] + " card."); // Informs user which card they have played.
                }
                else {
                	System.out.println("Computer has played the " + playerCards[index] + " card."); // Informs user which card computer has played.
                	System.out.println();
                	inputValidation("Enter 0 to continue.", 0, 0); // Notifying the user what the computer played and allows them to read the moves.
                }
                
                for (int j = 0; j < 10; j++) {
                	System.out.println();
                }
                
                // Formats the user's cards so that there are no gaps between the playerCards array.
                // Shifts the cards so that they all line up and all the null values are at the end of the array rather than mixed up within the array.
                for (int k = index; k < playerCards.length - 1; k++) {
                    playerCards[k] = playerCards[k + 1];
                    playerCards[k + 1] = null;
                    if (k == playerCards.length - 1) {
                    	playerCards[k] = null;
                    }
                }
                isValid = true; // Validates the card played.
                
                // Checking for the amount of full properties that the player and computer has.
                playerFullProperties[0] = 0; 
                for (int k = 0; k < 10; k++) {
                	if (k == 0 || k == 7 || k == 8) {
                		if (playerProperties[k] >= 2 && playerProperties[k] < 4) {
                			playerFullProperties[0]++;
                			continue;
                		}
                		else if (playerProperties[k] >= 4) {
                			playerFullProperties[0] += 2;
                		}
                	}
                	if (k == 9) {
                		if (playerProperties[k] == 4 && playerProperties[k] < 8) {
                			playerFullProperties[0]++;
                		}
                		else if (playerProperties[k] >= 8) {
                			playerFullProperties[0] += 2;
                		}
                	}
                	else {
                		if (playerProperties[k] == 3 && playerProperties[k] <= 6) {
                			playerFullProperties[0]++;
                		}
                		else if (playerProperties[k] >= 6) {
                			playerFullProperties[0] += 2;
                		}
                	}
                }
                
                opponentFullProperties[0] = 0; 
                for (int k = 0; k < 10; k++) {
                	if (k == 0 || k == 7 || k == 8) {
                		if (opponentProperties[k] >= 2 && opponentProperties[k] < 4) {
                			opponentFullProperties[0]++;
                			continue;
                		}
                		else if (opponentProperties[k] >= 4) {
                			opponentFullProperties[0] += 2;
                		}
                	}
                	if (k == 9) {
                		if (opponentProperties[k] == 4 && opponentProperties[k] < 8) {
                			opponentFullProperties[0]++;
                		}
                		else if (opponentProperties[k] >= 8) {
                			opponentFullProperties[0] += 2;
                		}
                	}
                	else {
                		if (opponentProperties[k] == 3 && opponentProperties[k] <= 6) {
                			opponentFullProperties[0]++;
                		}
                		else if (opponentProperties[k] >= 6) {
                			opponentFullProperties[0] += 2;
                		}
                	}
                }
            
                if (playerFullProperties[0] >= 3) { // If the player has reached a full set, the method is stopped.
                    return i; // Returns the turns played to add to the statistics.
                }
            }
            if (validInput == false) {
            	i++;
            	continue;
            }
            turnCounter++; // Adds a turn to the turnCounter
        }
        if (playerCardNum > 7) { // Limits the amount of cards the user has if they have over 7.
            if (currentTurn == 1) {
            	cardLimit(playerCards, deck);
            }
            else {
            	computerCardLimit(playerCards, deck);
            }
        	
        }
        return 3; // Returns 3 since the for loop has finished, therefore meaning that the player has used all 3 turns.
    }
    
    // Finds the index of the card wanted inside deckCardNames.
    public static int identifyCard (String playerCards) {
        int indexCounter = 0; // Sets the index of the selected card to 0 in deckCardNames.
        for (int i = 0; i < deckCardNames.length; i++) { // Loops to keep searching for the identified card.
            if (playerCards.equals(deckCardNames[i])) { // When the player card is the same as the index of deckCardNames, the code returns the index.
                break; // Breaks the code so that the value can be returned.
            }
            else { // If the player card isn't the same as the index of deckCardNames, this code runs.
                indexCounter++; // Adds one to the indexCounter because the two cards being compared aren't the same.
            }
        }
        return indexCounter; // Returns the index of the card being searched for (playerCards).
    }
    
    // Identifies the amount of cards the player has which don't have the null value.
    public static int playerCardNum (String [] playerCards) {
        int playerCardNum = 0; // Sets a variable to store the amount of non-null value variables.
        
        // Runs a for loop to go through all the playerCard indexes to find out which aren't null values and adds them to playerCardNum.
        for (int i = 0; i < playerCards.length; i++) {
            if (playerCards[i] != null) {
                playerCardNum++;
            }
        }
        return playerCardNum; // Returns the number of non-null cards that the player has.
    }

    public static void cardLimit (String [] playerCards, int [] deck) throws Exception {
    	for (int i = 0; i < 2; i++) {
    		System.out.println();
    	}
    	int playerCardNum = playerCardNum(playerCards); // Finds the amount of cards the player has (not including null values). 
    	String [] emptyStringArray = new String [0]; // Used for inputting into methods when parameters aren't needed.
    	for (int i = playerCardNum; i > 7; i--) {
        	displayCards(playerCards, emptyStringArray, 1); // Shows the player their current deck.
            int removedCard = inputValidation("You have too many cards (" + i + ")! Please remove cards until you have 7. Enter 1 to remove the top card through " + playerCardNum + " for the bottom card.", 1, playerCardNum); // Validates the input for the removal of a card.
            
            int identifiedCard = identifyCard(playerCards[removedCard - 1]); // Finds the index of the card that the player wants removed.
            deck[identifiedCard]++; // Adds the discarded card back into the deck.
            
            for (int k = (removedCard - 1); k < playerCardNum; k++) { // Continues running this code until the order of the cards are properly shifted to fill the first 7 indexes. It is set to removedCard-1 to get the index of the card.
            	if (k == playerCardNum - 1) { // If the card is the last card and there is nothing else to shift, this is run. removedCard-1 is used since 9 is the last index possible.
                    playerCards[k] = null; // Sets the card that the player wants removed to be null. 
            	}
            	else {
            		playerCards[k] = playerCards[k + 1]; // Sets the current index of the card to the card in next index.
                    playerCards[k + 1] = null; // Sets the card in the next index of the current card to null to shift the card.
            	}
            }
            
            playerCardNum--; // Scans for how many cards are left.
            if (playerCardNum > 7) { // If there are more than 7 cards left, the loop continues running.
                System.out.println("You have " + playerCardNum + " cards. Please remove " + (i-7) + " more card(s)"); // Informs user that they need to remove more cards.
            }
            else {
            	System.out.println("You have 7 cards. Continuing play!");
            	System.out.println();
            }
            playerCardNum = playerCardNum(playerCards); // Finds the amount of cards the player has (not including null values). 
        }
    }
    
    public static void computerCardLimit (String [] opponentCards, int [] deck) {
    	System.out.println("Removing computer cards!");
    	int computerCardNum = playerCardNum(opponentCards);
    	for (int i = computerCardNum; i > 7; i--) {
            int removedCard = (int)(Math.random()*computerCardNum + 1);
            System.out.println(opponentCards[removedCard] + " has been removed.");
            
            int identifiedCard = identifyCard(opponentCards[removedCard]); // Finds the index of the card that the player wants removed.
            deck[identifiedCard]++; // Adds the discarded card back into the deck.
            
            for (int k = removedCard; k < opponentCards.length - 1; k++) { // Continues running this code until the order of the cards are properly shifted to fill the first 7 indexes. It is set to removedCard-1 to get the index of the card.
            	if (k == opponentCards.length - 1) { // If the card is the last card and there is nothing else to shift, this is run. removedCard-1 is used since 9 is the last index possible.
                    opponentCards[k] = null; // Sets the card that the player wants removed to be null. 
                }
            	else {
            		opponentCards[k] = opponentCards[k + 1]; // Sets the current index of the card to the card in next index.
                    opponentCards[k + 1] = null; // Sets the card in the next index of the current card to null to shift the card.
            	}
            }
            
            int removalPlayerCardNum = playerCardNum(opponentCards); // Scans for how many cards are left.
            if (removalPlayerCardNum <= 7){
            	System.out.println("Computer has 7 cards. Continuing play!");
            	System.out.println();
            }
        }
    }
    
    // Used for when the Just Say No Card has been played.
    public static int justSayNo (int input, String [] opponentCards, String card) {
    	int justSayNo = 0; // Sets a variable to identify whether or not the card has been played.
		int opponentCardNum = playerCardNum(opponentCards); // Counts how many cards the opponent has that aren't null-values.
		
		// If the input is 0 (meaning that the opponent has used a Just Say No Card), this is run.
		if (input == 0) {
			// Runs a for loop scanning through the player cards.
			for (int i = 0; i < opponentCardNum; i++) {
				justSayNo = identifyCard(opponentCards[i]); // Sets the justSayNo to the index of the opponent's cards with the index of i.
				if (justSayNo == 9) { // If the identified card is 9 (the index of the card in the deck representing Just Say No), this code is run.
					System.out.println("Just Say No was used! " + card + " has been denied."); // Informs user that their action has been denied.
					// Runs a loop to shift the cards since the Just Say No was played.
					for (int j = justSayNo; j < opponentCards.length - 1; j++) {
                        opponentCards[j] = opponentCards[j + 1];
                        opponentCards[j + 1] = null;
                        if (j == opponentCards.length - 1) {
                        	opponentCards[j] = null;
                        }
                    }
					return 0; // Returns a value of 0 to prove that Just Say No was used.
				}
			}
		} 
		return 1; // Returns a value of 1 to show that Just Say No was not used.
    }
}