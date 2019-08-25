package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Class used to create the longest road messages (sent to the player with the most roads connected to one another)
 * @author Marcelina
 */
public class LongestRoad {
	@SerializedName("Spieler") Integer playerId;
	public LongestRoad(Integer playerId) {
		this.playerId = playerId;
	}
	//if the longest road title was lost and not received by any other player
	public LongestRoad() {
		this.playerId = -1;
	}
	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
