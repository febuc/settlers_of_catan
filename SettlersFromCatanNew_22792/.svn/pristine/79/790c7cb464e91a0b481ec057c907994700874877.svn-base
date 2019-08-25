package gameobjects.Cards;

import tools.ResourceType;
import view.CardView;

/**
 * The resource card from the game. Contains all types of resources the are available in the game.
 * Imports enum ResourceType which defines the type of resource this card represents.
 */
public final class ResourceCard extends Card {

    /**
     * Defines the type of resource this card represents.
     */
    private ResourceType resourceType;

    /**
     * Constructor
     *
     * @param resourceType The type we want this card to represent
     */
    public ResourceCard(ResourceType resourceType) {
        this.resourceType = resourceType;
        cardView = new CardView(this);
        String fileName = "";
        switch (resourceType){

            case WOOD: fileName = "WoodCard";
                break;
            case WOOL: fileName = "WoolCard";
                break;
            case GRAIN:fileName = "GrainCard";
                break;
            case LOAM: fileName = "LoamCard";
                break;
            case STONE:fileName = "StoneCard";
                break;
            case HIDDEN:fileName = "HiddenCard";
                break;
        }
        cardView.loadBackground(fileName);
    }

    /**
     * Returns the type of this card's resource
     *
     * @return resourceType
     */
    public ResourceType getResourceType() {
        return resourceType;
    }
    
    /**
     * Returns resource type as string.
     */
    @Override
    public String getName() {
        return resourceType.toString();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
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
		ResourceCard other = (ResourceCard) obj;
		if (resourceType != other.resourceType)
			return false;
		return true;
	}
    
}
