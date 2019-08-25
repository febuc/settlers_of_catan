package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;
import tools.Vector2D;
import tools.WorldTranslation;

/**
     * Coordinates represents the location of various game objects
     */
    public class Coordinates {
        @SerializedName("x") private Integer x;
        @SerializedName("y") private Integer y;
        /**
         * Constructor
         * @param x This is the x coordinate.
         * @param y This is the y coordinate.
         */
        public Coordinates(Integer x, Integer y){
            this.x=x;
            this.y=y;
        }

        public Coordinates(String letters){
            for (Vector2D<Integer> vector2D: WorldTranslation.COORDINATE_TO_LETTER.keySet()) {
                if (WorldTranslation.COORDINATE_TO_LETTER.get(vector2D).equals(letters)) {
                    this.x = vector2D.x;
                    this.y = vector2D.y;
                }
            }
        }

        public String translateToLetter(){
            return WorldTranslation.COORDINATE_TO_LETTER.get(new Vector2D<Integer>(x,y));
        }

        //Getters and Setters
        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }

