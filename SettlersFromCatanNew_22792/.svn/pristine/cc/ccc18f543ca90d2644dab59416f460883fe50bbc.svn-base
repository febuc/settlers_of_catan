package gameplay;

/**
 * Simulates a pair of dice. The dice will be rolled during the game. Each die can randomly roll a number
 * between 1 and 6. Their result will be added.
 * @author Panos
 *
 */
public class Dice {
	
	/**
	 * The number that was rolled for the first die.
	 */
	private int die1;
	/**
	 * The number that was rolled for the second die.
	 */
	private int die2;
	
	/**
	 * Constructor for this class. Creates a pair of dice.
	 */
	public Dice() {
		this.die1 = 0;
		this.die2 = 0;
	}
	
	/**
	 * Rolls both dice.
	 */
	public void rollDice() {
		// Numbers between 0 and 5 are rolled. Since dice usually have numbers between 1 and 6 we add 1.
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;
	}
	
	/**
	 * @return int The number that was rolled for the first die. Possible values are numbers between 1 and 6.
	 */
	public int getDie1() {
		return die1;
	}
	
	/**
	 * @return int The number that was rolled for the second die. Possible values are numbers between 1 and 6.
	 */
	public int getDie2() {
		return die2;
	}
	
	/**
	 * @return int Returns the sum of both dice. Possible values are numbers between 2 and 12.
	 */
	public int getDiceTotal() {
		return die1 + die2;
	}

}
