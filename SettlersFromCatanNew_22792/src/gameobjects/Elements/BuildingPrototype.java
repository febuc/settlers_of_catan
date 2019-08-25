package gameobjects.Elements;

import tools.BuildingType;

/**
 * Super class for all buildings
 */
public abstract class BuildingPrototype extends GameElement {
    /**
     * Defines the type this building represents.
     */
    protected BuildingType buildingType;
    
    /**
     * The unique ID of the owner
     */
    protected Integer ownerID;

    /**
     * True if we have an owner
     */
    protected  boolean isOccupied=false;

    /**
     * Constructor
     */
    public BuildingPrototype() {
        this.buildingType = BuildingType.NONE;
    }
    //Getters and Setters

    /**
     * Returns the type of this building
     *
     * @return buildingType
     */
    public BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * Sets the buildingType.
     *
     * @param buildingType The new type of settlement.
     */
    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    /**
     *Transforms GameCoordinates to ServerCoordinates
     * @return The translated coordinates.
     */
    public abstract String getTranslatedCoordinates();

	/**
	 * Returns Owner
	 * @return the ownerID
	 */
	public Integer getOwnerID() {
		return ownerID;
	}

	/**
	 * Sets owner
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
        setOccupied(true);
	}

	/**
	 * Returns true if we have an owner
	 * @return
	 */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Sets that building has owner
     * @param occupied
     */
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    /**
     *Returns boolean, which defines if player is able to build building
     * @return True if player has enough resources to build the street
     * @param playerId
     */
    public abstract boolean hasResourcesToBuildBuilding(Integer playerId);
}
