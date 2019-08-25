package controller;

import application.GameStart;
import gameobjects.Cards.ResourceCard;
import javafx.scene.control.Button;
import view.DropCardsView;

/**
 * Controller class for the drop cards menu.
 *
 * @author Felip
 */
public class DropCardsController {

    /**
     * Reference to the corresponding card view.
     */
    private DropCardsView dropCardsView;

    /**
     * Constructor
     *
     * @param acceptButton
     * @param refuseButton
     * @param dropCardsView
     */
    public DropCardsController(Button acceptButton, Button refuseButton, DropCardsView dropCardsView) {
        this.dropCardsView = dropCardsView;
        // Play sound when mouse hovering
        acceptButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
        refuseButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
        // Define Actions
        refuseButton.setOnMouseClicked(e -> {
            GameStart.soundManager.playSoundOnHexagonOver();
            dropCardsView.reset();
        });
        acceptButton.setOnMouseClicked(e -> {
            GameStart.soundManager.playSoundOnHexagonOver();
            dropCardsView.accept();
        });

    }

    /**
     * Defines actions for a (resource) card.
     *
     * @param card
     */
    public void defineCardAction(ResourceCard card) {
        card.getCardView().getCardButton().setOnMouseEntered(e -> {
            GameStart.soundManager.playSoundOnHexagonOver();
            card.getCardView().getCardButton().setOpacity(0.65);
        });
        card.getCardView().getCardButton().setOnMouseExited(e -> card.getCardView().getCardButton().setOpacity(1));
        card.getCardView().getCardButton().setOnMouseClicked(e -> {
            GameStart.soundManager.playSoundConfirmPurchase();
            dropCardsView.UpdateCardsToDrop(card);
        });


    }

}
