package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Represents object sent from server after the game was started. The object contains game map which is crucial for the players.
 * @author Marcelina
 */
public class GameStarted {
	@SerializedName("Karte") private Map map;
	
	/**
	 * constructor
	 * @param map the map sent to the players
	 */
	public GameStarted(Map map) {
		this.setMap(map);
	}

	//Getters and Setters
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
