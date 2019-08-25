package gameobjects.Cards;

import tools.EvolutionType;
import view.CardView;

/**
 * represents an evolution card.
 */
public class EvolutionCard extends Card {

    /**
     * Defines the type this card represents.
     */
    private EvolutionType evolutionType;
    /**
     * The name of the card
     */
    private String cardName;

    /**
     * Constructor
     * @param evolutionType
     */
    public EvolutionCard(EvolutionType evolutionType) {
        this.evolutionType = evolutionType;
        switch (evolutionType) {
            case KNIGHT:
                this.cardName = "Knight Card";
                break;
            case ROAD_BUILDING:
                this.cardName = "Road Building Card";
                break;
            case MONOPOLY:
                this.cardName = "Monopoly Card";
                break;
            case YEAR_OF_PLENTY:
                this.cardName = "Year Of Plenty Card";
                break;
            case VICTORY_POINT:
                this.cardName = "Victory Point Card";
                break;
            case HIDDEN:
                this.cardName = "Hidden Card";
                break;
            default:
                break;
        }
        cardView = new CardView(this);
        cardView.loadBackground(evolutionType.getFilepath());
    }

    /**
     * Returns the type of this card
     *
     * @return evolutionType
     */
    public EvolutionType getEvolutionType() {
        return evolutionType;
    }

    @Override
    public String getName() {
        return this.cardName;
    }
}
