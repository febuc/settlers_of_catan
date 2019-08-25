package tools;

public enum PlayerTeam {
    TEAM_RED,TEAM_BLUE,TEAM_WHITE,TEAM_ORANGE,NONE;

    @Override
    public String toString() {
        if(this == null)
            return null;
        switch (this) {

            case TEAM_RED:
                return "Red";
            case TEAM_BLUE:
                return "Blue";
            case TEAM_WHITE:
                return "White";
            case TEAM_ORANGE:
                return "Orange";
            default:
                return "PlayerTeam{}";
        }
    }

    public PlayerTeam stringToTeamColor(String s){
    	switch(s){
    	  case "Red":
    		  return TEAM_RED;
    	  case "Blue":
    		  return TEAM_BLUE;
    	  case "White":
    		  return TEAM_WHITE;
    	  case "Orange":
    		  return PlayerTeam.TEAM_ORANGE;
    	  default:
    		  return null;
    	}

    }
}
