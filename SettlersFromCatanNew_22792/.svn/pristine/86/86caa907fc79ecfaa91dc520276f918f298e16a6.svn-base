package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Object used to communicate the willingness to play a kinght card or inform that the card has been played
 * @author Marcelina
 *
 */
public class PlayKnightCard {
	@SerializedName("Ort") private Coordinates location;
	@SerializedName("Ziel") private Integer target;
	@SerializedName("Spieler") private Integer playerId;

	public PlayKnightCard(String location, Integer target, Integer playerId) {
		setLocation(location);
		this.target = target;
		this.playerId = playerId;
	}
	//we need a second constructor, since the player will be sending the message without the last field (see protocol)
	public PlayKnightCard(String location, Integer target) {
		setLocation(location);
		this.target = target;
		this.playerId = null;
	}
	public String getLocation() {
		return location.translateToLetter();
	}
	public void setLocation(String location) {
		this.location = new Coordinates(location);
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getPlayer() {
		return playerId;
	}
	public void setPlayer(Integer playerId) {
		this.playerId = playerId;
	}
}
