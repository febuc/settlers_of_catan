package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the Field object type used in the client/server communication to exchange information about field's properties.
 * @author Marcelina
 */
public class Field  {
	@SerializedName("Ort") private Coordinates location;
	@SerializedName("Typ") private String type;
	@SerializedName("Zahl") private Integer number;
	/**
	 * Constructor
	 * @param location The (server) coordinate of the field.
	 * @param type Type of the field
	 * @param number The number of this field (for the dice)
	 */
	public Field(String location, String type, Integer number){
		setLocation(location);
		this.type=type;
		this.number=number;
	}
	
	//Getters and Setters
	public String getLocation() {
		return location.translateToLetter();
	}
	public void setLocation(String location) {
		this.location = new Coordinates(location);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		if(number == null) return 0;
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
