package gameobjects.Cards;

import gameobjects.Cards.Card;

/**
 * Simulates special cards (that give extra victory points)
 */
public class SpecialCard extends Card {

    /**
     * Enum for the available card types for this class
     */
    private enum SpecialType{
        TRADING_ROUTE,MILITARY_POWER;
    }
    /**
     * Defines the type this card represents.
     */
    private SpecialType specialType;

    /**
     * Returns the type of this card
     * @return specialType
     */
    public SpecialType getSpecialType() {
        return specialType;
    }

    @Override
    public String getName() {
        return "Special card";
    }
}
