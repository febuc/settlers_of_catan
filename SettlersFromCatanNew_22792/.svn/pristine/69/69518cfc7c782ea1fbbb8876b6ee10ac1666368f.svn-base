package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Class representing objects send to inform that a player's status changed.
 * @author Marcelina
 */

public class PlayerStatusUpdate {
	@SerializedName("Spieler") private PlayerForProtocol player;

	/**
	 * constructor
	 * @param spieler the player whose status update is communicated
	 */
	public PlayerStatusUpdate(PlayerForProtocol player){
	    setPlayer(player);
    }

    public PlayerForProtocol getPlayer() {
        return player;
    }

    public void setPlayer(PlayerForProtocol player) {
        this.player = player;
    }
}
