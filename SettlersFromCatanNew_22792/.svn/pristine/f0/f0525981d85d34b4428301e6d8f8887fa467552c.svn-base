package gameworld;

import java.util.ArrayList;
import java.util.HashMap;

import gameobjects.Elements.Street;
import tools.Vector2D;
import tools.Vector3D;

/**
 * Simulates the games world. Stores all the hexagons and wayPoints in a 3D
 * matrix.
 *
 * @author Felip
 */
public final class World {

	/**
	 * Defines how many (extra) layers of surrounding hexagons we want to add
	 * additionally around the middle hexagon. (Must be greater non negative!)
	 */
	private final int worldLayer = 2;
	/**
	 * Stores all hexagonFields and WayPoints from the level. The data structure
	 * used here is a 3 dimensional matrix. For example: gameMatrix[x][0] returns
	 * the hexagonal field gameMatrix[x][1] returns the upper-left WayPoint,
	 * gameMatrix[x][2] returns the lower-left WayPoint,
	 */
	private GameMatrixEntry[][][] gameMatrix;

	/**
	 * A hash map, mapping a tupel (Vector2D) of WayPoints to their (corresponding)
	 * street.
	 */
	private HashMap<Vector2D<WayPoint>, Street> wayPointsToStreet = new HashMap<Vector2D<WayPoint>, Street>();

	/**
	 * A list of all streets (but only in one direction)
	 */
	private ArrayList<Street> streets = new ArrayList<Street>();
	/**
	 * A list of all wayPoints
	 */
	private ArrayList<WayPoint> wayPoints = new ArrayList<WayPoint>();
	/**
	 * A list of all HexagonFields
	 */
	private ArrayList<HexagonField> fields = new ArrayList<HexagonField>();

	/**
	 * Constructor
	 */
	public World() {
		resetMatrix();
	}

	/**
	 * Initializes a basic grid layout for the game. Specific values, such as field
	 * types or chipNumber must be entered separately!
	 *
	 * @author Felip
	 */
	private void createGameLayout() {
		// Total layers must be one larger than the worldLayer since we want to have
		// "Water" on the outside
		int totalLayers = worldLayer + 1;
		int longestLine = 2 * totalLayers + 1;
		// Creates hexagonal fields and inserts them into the matrix (in 2
		// operations...)
		// Operation 1
		int layer = totalLayers;
		for (int row = 0; row < totalLayers; row++) {
			for (int j = 0; j < longestLine; j++) {
				if (j < layer)
					continue;
				createHexagonField(row, j);
			}
			layer--;
		}
		// Operation 2
		layer = 0;
		for (int row = totalLayers; row < longestLine; row++) {
			for (int j = 0; j < longestLine; j++) {
				if (j >= longestLine - layer)
					continue;
				createHexagonField(row, j);
			}
			layer++;
		}
		// For testing. Prints matrix to console.
		/*
		 * DebugMatrix<GameMatrixEntry> debug = new DebugMatrix<>();
		 * debug.debugOccupation3DMatrixIn2D(gameMatrix);
		 */

		// Assign the remaining (at most 2 additional) hexagon fields to the WayPoints's
		// neighbour-list
		// Iterate through all wayPoints again.
		// See: "https://www.redblobgames.com/grids/hexagons/#map-storage" to get a
		// basic idea of what is going on here!
		for (int row = 0; row < gameMatrix.length; row++) {
			for (int column = 0; column < gameMatrix.length; column++) {
				// If entry null, continue....
				if (gameMatrix[row][column][0] == null)
					continue;
				// We have 2 wayPoints per hexagonal field
				WayPoint wayPointUpperLeft = (WayPoint) gameMatrix[row][column][1];
				WayPoint wayPointLowerLeft = (WayPoint) gameMatrix[row][column][2];
				// Add to list
				wayPoints.add(wayPointUpperLeft);
				wayPoints.add(wayPointLowerLeft);
				// Try adding neighbours.
				// Upper Left WayPoint
				// For the upper-left with coordinates [x][y][1] we find the neighbouring
				// hexagonal fields at: [x][y-1][0] & [x-1][y][0]
				// Check if neighbour exists!
				if (row - 1 >= 0 && gameMatrix[row - 1][column][0] != null) {
					HexagonField neighbourHexagonfield_B = (HexagonField) gameMatrix[row - 1][column][0];
					wayPointUpperLeft.getFieldNeighbours().add(neighbourHexagonfield_B);
				}
				// Lower Left WayPoint
				// For the lower-left with coordinates [x][y][2] we find the neighbouring
				// hexagonal fields at: [x][y-1][0] & [x+1][y-1][0]
				// Check if neighbour exists!
				if (row + 1 < gameMatrix.length && column - 1 >= 0 && gameMatrix[row + 1][column - 1][0] != null) {
					HexagonField neighbourHexagonfield_B = (HexagonField) gameMatrix[row + 1][column - 1][0];
					wayPointLowerLeft.getFieldNeighbours().add(neighbourHexagonfield_B);
				}
				// Both neighbours share the following field at [x][y-1][0]
				// Check if neighbour exists!
				if (column - 1 >= 0 && gameMatrix[row][column - 1][0] != null) {
					HexagonField neighbourHexagonfield_Share = (HexagonField) gameMatrix[row][column - 1][0];
					wayPointUpperLeft.getFieldNeighbours().add(neighbourHexagonfield_Share);
					wayPointLowerLeft.getFieldNeighbours().add(neighbourHexagonfield_Share);
				}
			}
		}
	}

	/**
	 * Creates a hexagonal field. And assigns it to the matrix.
	 */
	private void createHexagonField(int xCoord, int yCoord) {
		HexagonField hexagon = new HexagonField(new Vector3D<Integer>(xCoord, yCoord, 0));
		WayPoint wpUpperLeft = new WayPoint(new Vector3D<Integer>(xCoord, yCoord, 1));
		WayPoint wpLowerLeft = new WayPoint(new Vector3D<Integer>(xCoord, yCoord, 2));
		// Add hexagon to neighbour list
		wpUpperLeft.getFieldNeighbours().add(hexagon);
		wpLowerLeft.getFieldNeighbours().add(hexagon);
		gameMatrix[xCoord][yCoord][0] = hexagon;
		gameMatrix[xCoord][yCoord][1] = wpUpperLeft;
		gameMatrix[xCoord][yCoord][2] = wpLowerLeft;

		// Add to list
		fields.add(hexagon);
	}

	/**
	 * Creates streets between adjacent waypoints (but not to and between the utmost
	 * waypoints, because they're in the water). Saves them in the arraylist
	 * 'streets' and in the hash map 'wayPointsToStreet', which
	 */
	private void createStreets() {
		for (int row = gameMatrix.length - 1; row >= 0; row--) {
			for (int column = 0; column < gameMatrix.length; column++) {
				if (row < gameMatrix.length - 1) {
					Vector2D<WayPoint> vec = new Vector2D<WayPoint>((WayPoint) gameMatrix[row][column][1],
							(WayPoint) gameMatrix[row][column][2]);
					Street street = new Street(vec.x, vec.y);
					if (wayPointsToStreet.get(vec) == null) {
						streets.add(street);
						wayPointsToStreet.put(vec, street);
						wayPointsToStreet.put(new Vector2D<WayPoint>(vec.y, vec.x), street);
					}
				}
				if (row - 1 >= 0 && column - 1 >= 0 && gameMatrix[row - 1][column - 1][0] != null) {
					Vector2D<WayPoint> vecLeft = new Vector2D<WayPoint>((WayPoint) gameMatrix[row][column][1],
							(WayPoint) gameMatrix[row - 1][column][2]);
					Street streetLeft = new Street(vecLeft.x, vecLeft.y);
					if (wayPointsToStreet.get(vecLeft) == null) {
						streets.add(streetLeft);
						wayPointsToStreet.put(vecLeft, streetLeft);
						wayPointsToStreet.put(new Vector2D<WayPoint>(vecLeft.y, vecLeft.x), streetLeft);
					}
				}
				if (row - 1 >= 0 && column + 1 < gameMatrix.length && gameMatrix[row - 1][column + 1][0] != null) {
					Vector2D<WayPoint> vecRight = new Vector2D<WayPoint>((WayPoint) gameMatrix[row][column][1],
							(WayPoint) gameMatrix[row - 1][column + 1][2]);
					Street streetRight = new Street(vecRight.x, vecRight.y);
					if (wayPointsToStreet.get(vecRight) == null) {
						streets.add(streetRight);
						wayPointsToStreet.put(vecRight, streetRight);
						wayPointsToStreet.put(new Vector2D<WayPoint>(vecRight.y, vecRight.x), streetRight);
					}
				}
			}
		}
	}

	/**
	 * Returns the street associated to both WayPoints.
	 *
	 * @param wayPoint
	 * @param wayPoint2
	 * @return
	 */
	public Street findStreetWithWayPoints(WayPoint wayPoint, WayPoint wayPoint2) {
		Street tempStreetA = new Street(wayPoint, wayPoint2);
		Street tempStreetB = new Street(wayPoint2, wayPoint);

		for (Street streetReal : streets) {
			if (streetReal.equals(tempStreetA) || streetReal.equals(tempStreetB))
				return streetReal;
		}
		return null;
	}
	// Getters

	/**
	 * Returns the matrix storing hexagonal fields and wayPoints
	 *
	 * @return gameMatrix
	 */
	public GameMatrixEntry[][][] getGameMatrix() {
		return gameMatrix;
	}

	/**
	 * Getter for the hash map, which maps the two representations of vectors to
	 *
	 * @return wayPointsToStreet
	 */
	public HashMap<Vector2D<WayPoint>, Street> getWayPointsToStreet() {
		return wayPointsToStreet;
	}

	/**
	 * Getter for the list of all streets
	 *
	 * @return streets
	 */
	public ArrayList<Street> getStreets() {
		return streets;
	}

	/**
	 * Returns all wayPoints
	 *
	 * @return wayPoints
	 */
	public ArrayList<WayPoint> getWayPoints() {
		return wayPoints;
	}

	/**
	 * Getter method for the fields
	 * @return fields
	 */
	public ArrayList<HexagonField> getFields() {
		return fields;
	}

	/**
	 * Resets the matrix
	 */
	public void resetMatrix() {
		// (worldLayer+1) since we want to have Water on the outside!
		int matrixSize = (worldLayer + 1) * 2 + 1;
		gameMatrix = new GameMatrixEntry[matrixSize][matrixSize][3];
		createGameLayout();
		createStreets();
	}
}
