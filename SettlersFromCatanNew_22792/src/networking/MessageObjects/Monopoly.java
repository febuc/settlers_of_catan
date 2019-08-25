package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Object used to communicate the willingness to play a monopoly card or inform that the card has been played
 * @author Marcelina
 */
public class Monopoly {

	@SerializedName("Rohstoff") private String resource;
	@SerializedName("Spieler") private Integer playerId;
	
	//constructor for the server
	public Monopoly(String resource, Integer playerId) {
		this.resource = resource;
		this.playerId = playerId;
	}
	//constructor for the client
	public Monopoly(String resource) {
		this.resource = resource;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
