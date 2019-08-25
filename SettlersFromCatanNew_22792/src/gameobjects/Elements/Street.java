package gameobjects.Elements;

import application.GameStart;
import gameworld.HexagonField;
import gameworld.WayPoint;
import player.Player;
import tools.BuildingType;
import tools.ResourceType;
import tools.Vector2D;
import tools.WorldTranslation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines a connection point between two wayPoints. Simulates a street.
 */
public final class Street extends BuildingPrototype {
    /**
     * An array that stores both wayPoints that are connected (and thus "form" this
     * "street")
     */
    private WayPoint[] connectionPoints;

    /**
     * Constructor
     *
     * @param wpA The wayPoint connected with wpB
     * @param wpB The wayPoint connected with wpA
     */
    public Street(WayPoint wpA, WayPoint wpB) {
        connectionPoints = new WayPoint[2];
        connectionPoints[0] = wpA;
        connectionPoints[1] = wpB;
    }

    /**
     * Builds a street when a player buys it.
     *
     * @param id The unique ID of the owner
     */
    public void buildStreet(Integer id) {
        buildingType = BuildingType.STREET;
        setOwnerID(id);
    }

    /**
     * Transforms GameCoordinates to ServerCoordinates "Smaller" letter first.
     *
     * @return ServerCoordinates e.g. "AB"
     */
    public String getTranslatedCoordinates() {
        ArrayList<HexagonField> wpAFields = connectionPoints[0].getFieldNeighbours();
        ArrayList<HexagonField> wpBFields = connectionPoints[1].getFieldNeighbours();
        Set<HexagonField> interSection = new HashSet<HexagonField>(wpAFields);
        Set<HexagonField> wpBFieldsSet = new HashSet<HexagonField>(wpBFields);
        interSection.retainAll(wpBFieldsSet); //Schnittmenge
        String posString = "";
        HexagonField[] fields = interSection.toArray(new HexagonField[interSection.size()]);
        String letterA = WorldTranslation.getPositionToLetter(fields[0].getPosition().castTo2D());
        String letterB = WorldTranslation.getPositionToLetter(fields[1].getPosition().castTo2D());
        // If letterA < letterB then we get -1
        if (letterA.compareTo(letterB) < 0) {
            posString += letterA + letterB;
        } else
            posString += letterB + letterA;
        return posString;
    }

    /**
     * Returns if player is able to build street
     */
    @Override
    public boolean hasResourcesToBuildBuilding(Integer playerId) {
        Player player = GameStart.siedlerVonCatan.findPlayerByID(playerId);
        if (player.getStatus().equals("Strasse bauen"))
            return true;
        else
            return player.getNumberOfResource(ResourceType.WOOD) >= 1 && player.getNumberOfResource(ResourceType.LOAM) >= 1;
    }

    /**
     * Checks if the vector has the same waypoint as this street
     *
     * @param vec of two waypoints
     * @return true if the vector has the same waypoint as this street
     */
    public boolean vectorEqualsStreet(Vector2D<WayPoint> vec) {
        boolean x = (vec.x.equals(connectionPoints[0])) && (vec.y.equals(connectionPoints[1]));
        boolean y = (vec.x.equals(connectionPoints[1])) && (vec.y.equals(connectionPoints[0]));
        return x | y;
    }

    // Getter

    /**
     * Getter method for the connection points.
     *
     * @return connectionPoints
     */
    public WayPoint[] getConnectionPoints() {
        return connectionPoints;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(connectionPoints);
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Street other = (Street) obj;
        if (!Arrays.equals(connectionPoints, other.connectionPoints))
            return false;
        return true;
    }


}
