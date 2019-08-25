package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

public class MoveThief {
    @SerializedName("Ort") private Coordinates location;
    @SerializedName("Ziel") private Integer target;

    public MoveThief(String location, Integer target){
        setLocation(location);
        this.target = target;
    }

    //Getters and setters

    public String getLocation() {
        return location.translateToLetter();
    }

    public Integer getTarget() {
        return target;
    }

    public void setLocation(String location) {
        this.location = new Coordinates(location);
    }

    public void setTarget(Integer target) {
        this.target = target;
    }


}
