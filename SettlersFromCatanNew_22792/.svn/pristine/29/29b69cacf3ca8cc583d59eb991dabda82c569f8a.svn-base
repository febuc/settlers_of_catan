package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class PlayerWhoAcceptedTrade {
	
	@SerializedName("Mitspieler") private int player;
	@SerializedName("Handel id") private int tradeId;
	@SerializedName("Annehmen") private boolean accepted;
	//default for accepted is true
	public PlayerWhoAcceptedTrade(int playerID, int tradeId) {
		this.player = playerID;
		this.tradeId = tradeId;
		this.accepted=true;
	}
	public PlayerWhoAcceptedTrade(int playerID, int tradeId, boolean accepted) {
		this.player = playerID;
		this.tradeId = tradeId;
		this.accepted=accepted;
	}

	public int getPlayerID() {
		return player;
	}

	public void setPlayerID(int player) {
		this.player = player;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}	
}
