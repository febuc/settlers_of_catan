package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Forwards the result of a dice throw
 * @author Marcelina
 */
public class DiceThrow {
	@SerializedName("Spieler") private int player;
	@SerializedName("Wurf") private int[] diceThrows = new int[2];

	/**
	 * constructor
	 * @param player player who made the throw
	 * @param diceThrows number they got for each throw
	 */
	public DiceThrow(int player , int[] diceThrows){
        setPlayer(player);
        setDiceThrows(diceThrows);

    }

    //Getter and Setter
    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int[] getDiceThrows(){
    	return diceThrows;
    }
    
    public void setDiceThrows(int[] diceSum) {
        this.diceThrows = diceSum;
    }
    /**
     * @return the sum of the two dices
     */
    public int getDiceSum() {
        return diceThrows[0]+diceThrows[1];
    }
}
