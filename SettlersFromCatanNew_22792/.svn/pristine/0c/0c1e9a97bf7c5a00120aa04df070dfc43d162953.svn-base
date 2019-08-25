package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class ReceivedTradeOffer {
	
	@SerializedName("Spieler") private int player;
	@SerializedName("Handel id") private int tradeId;
	@SerializedName("Angebot") private Resources resourcesSupply;
	@SerializedName("Nachfrage") private Resources resourcesDemand;

	public ReceivedTradeOffer(int player, int tradeId, 
			Resources resourcesSupply, Resources resourcesDemand) {
		this.player = player;
		this.tradeId = tradeId;
		this.resourcesSupply = resourcesSupply;
		this.resourcesDemand = resourcesDemand;
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

	public Resources getResourcesSupply() {
		return resourcesSupply;
	}

	public void setResourcesSupply(Resources resourcesSupply) {
		this.resourcesSupply = resourcesSupply;
	}

	public Resources getResourcesDemand() {
		return resourcesDemand;
	}

	public void setResourcesDemand(Resources resourcesDemand) {
		this.resourcesDemand = resourcesDemand;
	}
	
	
	
}
