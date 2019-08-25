package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the Building object type used in the client/server communication to exchange information about building's properties.
 * @author Marcelina
 */
public class Building {

	@SerializedName("Eigentuemer") private Integer owner;
	@SerializedName("Typ") private String type;
	@SerializedName("Ort") private Coordinates[] location;
	
    /**
     * Constructor
     * @param owner  id of the building's owner
     * @param type type of building: possible values are strasse, dorf, stadt
     * @param location localization on the map (as defined in the client/server communication)
     */
    public Building(Integer owner,String type, String location){
        setOwner(owner);
        setType(type);
        setLocation(location);
    }

	//Getter and Setter
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        String letter = "";
        letter += location[0].translateToLetter();
        letter += location[1].translateToLetter();
        if(location.length == 3)
            letter += location[2].translateToLetter();
        return letter;
    }
    public void setLocation(String location) {

        if(location.length() == 3)
            this.location = new Coordinates[3];
        else
            this.location = new Coordinates[2];
        this.location[0] = new Coordinates(location.charAt(0)+"");
        this.location[1] = new Coordinates(location.charAt(1)+"");
        if(location.length() == 3)
            this.location[2] = new Coordinates(location.charAt(2)+"");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        if (owner != null ? !owner.equals(building.owner) : building.owner != null) return false;
        return location != null ? location.equals(building.location) : building.location == null;
    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
