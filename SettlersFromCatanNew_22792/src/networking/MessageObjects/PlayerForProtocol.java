package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Stores information about the player.
 * @author Marceline,Felip,Jonas
 *
 */
public class PlayerForProtocol {
	private Integer id;
	@SerializedName("Farbe") private String color;
	@SerializedName("Name") private String name;
	@SerializedName("Status") String status;
	@SerializedName("Siegpunkte") Integer victoryPoints;
	@SerializedName("Rohstoffe") Resources resources;
	@SerializedName("Rittermacht") Integer knightPoints;
	@SerializedName("Entwicklungskarten") DevelopmentCards developmentCards;
	@SerializedName("Groesste Rittermacht") private boolean largestArmy;
	@SerializedName("Laengste Handelsstrasse") private boolean longestRoad;
	
	/**
	 * Constructor
	 * @param id The player id.
	 * @param color The player's color.
	 * @param name The player's name.
	 * @param status Information about what next action is expected from the player. Possibilities are: spiel starten, wartet auf spielbeginn, dorf bauen, strasse bauen, wuerfeln, handeln oder bauen, warten, verbindung verloren
	 * @param victoryPoints The player's current points.
	 * @param resources The player's resources.
	 */
	public PlayerForProtocol(Integer id, String color, String name, String status, Integer victoryPoints, Resources resources, Integer knightPoints, DevelopmentCards developmentCards) {
		this.id = id;
		this.color = color;
		this.name = name;
		this.status = status;
		this.victoryPoints = victoryPoints;
		this.resources =resources;
		this.knightPoints = knightPoints;
		this.developmentCards = developmentCards;
		this.largestArmy = false;
		this.longestRoad = false;
	}
	public PlayerForProtocol(Integer id, String color, String name, String status, Integer victoryPoints, Resources resources, Integer knightPoints, DevelopmentCards developmentCards, boolean largestArmy, boolean longestRoad) {
		this.id = id;
		this.color = color;
		this.name = name;
		this.status = status;
		this.victoryPoints = victoryPoints;
		this.resources =resources;
		this.knightPoints = knightPoints; 
		this.developmentCards = developmentCards;
		this.largestArmy = largestArmy;
		this.longestRoad = longestRoad;
	}
	public PlayerForProtocol(Integer id, String color, String name) {
		this.id = id;
		this.color = color;
		this.name = name;
		this.status = "Verbunden";
		this.victoryPoints = 0;
		this.resources = new Resources(0,0,0,0,0,0);
		this.knightPoints = 0;
		this.developmentCards = new DevelopmentCards(0,0,0,0,0,0);
		this.largestArmy = false;
		this.longestRoad = false;
	}

	@Override
	public String toString() {
		return "PlayerForProtocol{" +
				"id=" + id +
				", color='" + color + '\'' +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", victoryPoints=" + victoryPoints +
				", resources=" + resources +
				", knightPoiints=" +knightPoints +
				", development cards" + developmentCards+
				'}';
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public Integer getKnightPoints() {
		if(knightPoints==null) return 0;
		return knightPoints;
	}
	public void setKnightPoints(Integer knightPoints) {
		this.knightPoints = knightPoints;
	}
	public DevelopmentCards getDevelopmentCards() {
		return developmentCards;
	}
	public void setDevelopmentCards(DevelopmentCards developmentCards) {
		this.developmentCards = developmentCards;
	}
	public void substractFromPlayersResources(Resources substractResources){
		this.resources.substractFromResources(substractResources);
	}
	public void addToPlayersResources(Resources addResources){
		this.resources.addToResources(addResources);
	}

	public boolean hasLargestArmy() {
		return largestArmy;
	}

	public void setLargestArmy(boolean largestArmy) {
		this.largestArmy = largestArmy;
	}

	public boolean hasLongestRoad() {
		return longestRoad;
	}

	public void setLongestRoad(boolean longestRoad) {
		this.longestRoad = longestRoad;
	}
}
