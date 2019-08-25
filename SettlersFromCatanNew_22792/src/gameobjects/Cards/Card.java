package gameobjects.Cards;

import gameobjects.GameObject;
import view.CardView;

/**
 * An abstract class extending gameObject for all card objects
 */
public abstract class Card extends GameObject {
    protected CardView cardView;

    /**
     * @return cardView
     */
    public CardView getCardView() {
        return cardView;
    }

    /**
     * Abstract method for returning the name of the card as a string
     * @return 
     */
    public abstract String getName();
}
