package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Objects used to communicate the willingness to play a yearOfPlenty card or inform that the card has been played
 * @author Marcelina
 */
public class YearOfPlenty {

	@SerializedName("Rohstoffe") Resources resources;
	@SerializedName("Spieler") Integer playerId;
	
	//constructor for the server
	public YearOfPlenty(Resources resources, Integer playerId) {
		this.resources = resources;
		this.playerId = playerId;
	}
	//constructor for the client
	public YearOfPlenty(Resources resources) {
		this.resources = resources;
		this.playerId = null;
	}
	
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
