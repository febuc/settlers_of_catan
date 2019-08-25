package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class DomesticTradeOffer {

	@SerializedName("Angebot") private Resources resourcesSupply;
	@SerializedName("Nachfrage") private Resources resourcesDemand;
	
	public DomesticTradeOffer(Resources resourcesSupply, Resources resourcesDemand) {
		this.resourcesSupply = resourcesSupply;
		this.resourcesDemand = resourcesDemand;		
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
	/**
	 * checks whether both demand and supply contain the same resource type (which isnt allowed)
	 * @return true if they containt same resource type (trade should not be allowed)
	 */
	public boolean containsSameResourceType(){
		if(resourcesDemand.getGrain()>0&&resourcesSupply.getGrain()>0){
			return true;
		}
		if(resourcesDemand.getLoam()>0&&resourcesSupply.getLoam()>0){
			return true;
		}
		if(resourcesDemand.getStone()>0&&resourcesSupply.getStone()>0){
			return true;
		}
		if(resourcesDemand.getWood()>0&&resourcesSupply.getWood()>0){
			return true;
		}
		if(resourcesDemand.getWool()>0&&resourcesSupply.getWool()>0){
			return true;
		}
		else return false;
	}
}
