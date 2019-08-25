package gameworld;

import gameobjects.Elements.Settlement;
import tools.PortTypes;
import tools.Vector3D;

import java.util.ArrayList;

/**
 * The WayPoint class defines the contact points between fields. WayPoints are use for navigation and path finding.
 */
public final class WayPoint extends GameMatrixEntry {

    /**
     * A list which stores all the (immediate) surrounding neighbour fields.
     * The field at index pos [0] is the directly associated field!
     */
    private ArrayList<HexagonField> fieldNeighbours;

    /**
     * A list of all wayPoints forming a street
     */
    private ArrayList<WayPoint> streetConnectedWaypoints;
    /**
     * Stores all the (immediate) surrounding neighbour wayPoints.
     * (Together, they can form a street)
     */
    private ArrayList<WayPoint> wayPointNeighbours;

    /**
     * Defines the type of the port. If out wayPoint is not a port, its portType is NONE.
     * (Default is NONE)
     */
    private PortTypes portType;
    /**
     * The 3D coordinates for this WayPoint
     */
    private Vector3D<Integer> position;

    /**
     * The settlement on this WayPoint
     */
    private Settlement settlement;

    /**
     * Constructor
     *
     * @param position The actual position of this WayPoint.
     */
    public WayPoint(Vector3D<Integer> position) {
    	portType = PortTypes.NONE;
        settlement = new Settlement(this);
        fieldNeighbours = new ArrayList<>();
        wayPointNeighbours = new ArrayList<>();
        streetConnectedWaypoints = new ArrayList<>();
        this.position = position;
    }

    /**
     * Redefines the toString method
     * @return
     */
    @Override
    public String toString(){
        return position.toString();
    }
    //Getters & Setters 

    /**
     * Returns the 3D coordinates of this WayPoint.
     *
     * @return position
     */
    @Override
    public Vector3D<Integer> getPosition() {
        return position;
    }

    /**
     * Returns all neighbour fields as an array list
     *
     * @return fieldNeighbours
     */
    public ArrayList<HexagonField> getFieldNeighbours() {
        return fieldNeighbours;
    }

    /**
     * Returns the settlement associated to this object
     *
     * @return settlement
     */
    public Settlement getSettlement() {
        return settlement;
    }

    /**
     * Returns the wayPoint neighbours
     *
     * @return wayPointNeighbours
     */
    public ArrayList<WayPoint> getWayPointNeighbours() {
        return wayPointNeighbours;
    }
    
     /**
	 * @param wayPoint Adds a wayPoint to the "wayPointNeighbours" list if not already there.
	 */
	public void addWayPointToNeighbourList(WayPoint wayPoint) {
		if(!wayPointNeighbours.contains(wayPoint))
			wayPointNeighbours.add(wayPoint);
	}
	

	/**
	 * @return the streetConnectedWaypoints
	 */
	public ArrayList<WayPoint> getStreetConnectedWaypoints() {
		return streetConnectedWaypoints;
	}

	/**
	 * @return the portType
	 */
	public PortTypes getPortType() {
		return portType;
	}

	/**
	 * @param portType the portType to set
	 */
	public void setPortType(PortTypes portType) {
		this.portType = portType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WayPoint wayPoint = (WayPoint) o;

        return position != null ? position.equals(wayPoint.position) : wayPoint.position == null;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
