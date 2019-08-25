package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class AbandonedTrade {
	
	@SerializedName("Spieler") private int player;
	@SerializedName("Handel id") private int tradeId;
	
	public AbandonedTrade(int player, int tradeId) {
		super();
		this.player = player;
		this.tradeId = tradeId;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
}
