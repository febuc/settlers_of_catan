package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class ThiefMoved {
    @SerializedName("Spieler") private Integer player;
    @SerializedName("Ort") private Coordinates location;
    @SerializedName("Ziel") private Integer target;

    public ThiefMoved(Integer player, String location, Integer target){
        this.player = player;
        setLocation(location);
        this.target = target;
    }

    //Getters and setters
    public Integer getPlayer() {
        return player;
    }

    public String getLocation() {
        return location.translateToLetter();
    }

    public Integer getTarget() {
        return target;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public void setLocation(String location) {
        this.location = new Coordinates(location);
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}
