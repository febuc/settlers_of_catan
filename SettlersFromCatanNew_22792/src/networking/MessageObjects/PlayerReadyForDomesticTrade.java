package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class PlayerReadyForDomesticTrade {

	@SerializedName("Handel id") private int tradeId;
	@SerializedName("Annehmen") private boolean accepted;
	
	//default for accepted is true
	public PlayerReadyForDomesticTrade(int tradeId) {
		this.tradeId = tradeId;
		this.accepted = true;
	}
	public PlayerReadyForDomesticTrade(int tradeId, boolean accepted) {
		this.tradeId = tradeId;
		this.accepted = accepted;
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
