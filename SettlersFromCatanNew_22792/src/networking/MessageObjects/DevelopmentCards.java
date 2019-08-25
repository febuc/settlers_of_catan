package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Class representing the message type object used to send information about development cards held/received by the player.
 * @author Marcelina
 */
public class DevelopmentCards {
		@SerializedName("Ritter") private Integer knight;
		@SerializedName("Strassenbau") private Integer roadBuilding;
		@SerializedName("Monopol") private Integer monopoly;
		@SerializedName("Erfindung") private Integer yearOfPlenty;
		@SerializedName("Siegpunkt") private Integer victoryPoint;
		@SerializedName("Unbekannt") private Integer hidden;
		public DevelopmentCards(Integer knight, Integer roadBuilding, Integer monopoly, Integer yearOfPlenty, Integer victoryPoint,
				Integer hidden) {
			this.knight = knight;
			this.roadBuilding = roadBuilding;
			this.monopoly = monopoly;
			this.yearOfPlenty = yearOfPlenty;
			this.victoryPoint = victoryPoint;
			this.hidden = hidden;
		}
		public String toString(){
			return "{"+knight+","+roadBuilding+","+monopoly+","+yearOfPlenty+","+victoryPoint+","+hidden+"}";
		}
		
		public void addCards(DevelopmentCards cards){
			setKnight(getKnight()+cards.getKnight());
			setRoadBuilding(getRoadBuilding()+cards.getRoadBuilding());
			setMonopoly(getMonopoly()+cards.getMonopoly());
			setYearOfPlenty(getYearOfPlenty()+cards.getYearOfPlenty());
			setVictoryPoint(getVictoryPoint()+cards.getVictoryPoint());
			setHidden(getKnight()+getVictoryPoint()+getRoadBuilding()+getYearOfPlenty()+getMonopoly());		
		}
		public void removeCard(DevelopmentCards cards){
			setKnight(getKnight()-cards.getKnight());
			setRoadBuilding(getRoadBuilding()-cards.getRoadBuilding());
			setMonopoly(getMonopoly()-cards.getMonopoly());
			setYearOfPlenty(getYearOfPlenty()-cards.getYearOfPlenty());
			setVictoryPoint(getVictoryPoint()-cards.getVictoryPoint());
			setHidden(getKnight()+getVictoryPoint()+getRoadBuilding()+getYearOfPlenty()+getMonopoly());		
		}

		public Integer getKnight() {
			if(knight!=null) return knight;
			return 0;
		}

		public void setKnight(Integer knight) {
			this.knight = knight;
		}

		public Integer getRoadBuilding() {
			if(roadBuilding!=null) return roadBuilding;
			return 0;
		}

		public void setRoadBuilding(Integer roadBuilding) {
			this.roadBuilding = roadBuilding;
		}

		public Integer getMonopoly() {
			if(monopoly!=null) return monopoly;
			return 0;
		}

		public void setMonopoly(Integer monopoly) {
			this.monopoly = monopoly;
		}

		public Integer getYearOfPlenty() {
			if(yearOfPlenty!=null) return yearOfPlenty;
			return 0;
		}

		public void setYearOfPlenty(Integer yearOfPlenty) {
			this.yearOfPlenty = yearOfPlenty;
		}

		public Integer getVictoryPoint() {
			if(victoryPoint!=null) return victoryPoint;
			return 0;
		}

		public void setVictoryPoint(Integer victoryPoint) {
			this.victoryPoint = victoryPoint;
		}
		public Integer getHidden() {
			if(hidden!=null) return hidden;
			return 0;
		}
		public void setHidden(Integer hidden) {
			this.hidden = hidden;
		}
		public Integer getTotalCards(){
			Integer totalCards = 0;
			if(knight!=null) totalCards+=knight;
			if(roadBuilding!=null) totalCards+=roadBuilding;
			if(monopoly!=null) totalCards+=monopoly;
			if(yearOfPlenty!=null) totalCards+=yearOfPlenty;
			if(victoryPoint!=null) totalCards+=victoryPoint;
			return totalCards;
		}
	}

