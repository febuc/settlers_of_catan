package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * class representing the messaege object sent/received if a development card is bought
 * @author Wisniewska
 *
 */
public class DevelopmentCardBought {

	@SerializedName("Spieler") Integer playerId;
	@SerializedName("Entwicklungskarte") String developmentCard;	
	public DevelopmentCardBought(Integer playerId, String developmentCard) {
		this.playerId = playerId;
		this.developmentCard = developmentCard;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getDevelopmentCard() {
		return developmentCard;
	}
	public void setDevelopmentCard(String developmentCard) {
		this.developmentCard = developmentCard;
	}
}
