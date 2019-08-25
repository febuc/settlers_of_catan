package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the message sent in case a player receives new resources
 * @author Marcelina
 */
public class Earnings {
	@SerializedName("Spieler") private int player;
	@SerializedName("Rohstoffe") private Resources resources;
	
	public Earnings(int player, Resources resources) {
		this.player = player;
		this.resources = resources;
	}
	
	public int getPlayer() {
		return player;
	}
	public Resources getResources() {
		return resources;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Earnings{" +
				"resources=" + resources +
				'}';
	}
}
