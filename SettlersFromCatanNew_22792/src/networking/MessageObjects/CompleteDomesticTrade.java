package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class CompleteDomesticTrade {

	@SerializedName("Handel id") private int tradeId;
	@SerializedName("Mitspieler") private int playerId;
	
	public CompleteDomesticTrade(int tradeId, int playerId) {
		this.tradeId = tradeId;
		this.playerId = playerId;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}
