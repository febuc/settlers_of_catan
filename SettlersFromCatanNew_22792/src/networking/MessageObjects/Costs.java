package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Represents the message sent in case a player has to pay resources to built something
 * or gets his resources taken by the thief
 * @author Minh
 *
 */
public class Costs {
	@SerializedName("Spieler") private int player;
	@SerializedName("Rohstoffe") private Resources resources;

	public Costs(int player, Resources resources){
		this.player = player;
		this.resources = resources;
	}

	public int getPlayer() {
		return player;
	}
	public Resources getResources() {
		return resources;
	}

	@Override
	public String toString() {
		return "Costs{" +
				"resources=" + resources +
				'}';
	}
}
