package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
/**
 * Object used to communicate the willingness to play a roadBuilding card or inform that the card has been played
 * @author Marcelina
 */
public class PlayRoadBuildingCard {

	@SerializedName("Strasse 1") private Coordinates[] street1 = new Coordinates[2];
	@SerializedName("Strasse 2") private Coordinates[] street2 = new Coordinates[2];
	@SerializedName("Spieler") private Integer playerId;
	//constructor used by server
	public PlayRoadBuildingCard(String street1, String street2, Integer playerId) {
		setStreet1(street1);
		setStreet2(street2);
		this.playerId = playerId;
	}
	//constructor used by server, only one street
	public PlayRoadBuildingCard(String street1, Integer playerId) {
		setStreet1(street1);
		this.street2=null;
		this.playerId = playerId;
	}
	//constructor used by client
	public PlayRoadBuildingCard(String street1, String street2) {
		setStreet1(street1);
		setStreet2(street2);
		this.playerId = null;
	}
	//constructor with only one street (client)
	public PlayRoadBuildingCard(String street1) {
		setStreet1(street1);
		this.street2=null;
		this.playerId = null;
	}
	public String getStreet1() {
		String letter = "";
	    letter += street1[0].translateToLetter();
	    letter += street1[1].translateToLetter();
		return letter;
	}
	public void setStreet1(String street1) {
		 this.street1[0] = new Coordinates(street1.charAt(0)+"");
	     this.street1[1] = new Coordinates(street1.charAt(1)+"");
	}
	public String getStreet2() {
		//if only one street was chosen
		if(street2==null) return null;
		String letter = "";
	    letter += street2[0].translateToLetter();
	    letter += street2[1].translateToLetter();
		return letter;
	}
	public void setStreet2(String street2) {
		 this.street2[0] = new Coordinates(street2.charAt(0)+"");
	     this.street2[1] = new Coordinates(street2.charAt(1)+"");
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
