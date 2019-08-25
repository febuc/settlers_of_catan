package view;

import application.GameStart;
import controller.PlayerTradeController;
import gameobjects.Cards.ResourceCard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import networking.MessageObjects.ReceivedTradeOffer;
import networking.MessageObjects.Resources;
import tools.ResourceType;
import tools.Styling;

import static javafx.geometry.Pos.CENTER;

import java.util.HashMap;

/**
 * Draws a request notification on the screen
 */
public final class TradeNotification {
	private VBox layout;

	/**
	 * Constructor
	 *
	 * @param receivedTradeOffer
	 */
	public TradeNotification(ReceivedTradeOffer receivedTradeOffer) {

		//Deactivate if not enough resources
		HashMap<ResourceType,Integer> playerResources = GameStart.siedlerVonCatan.findPlayerByID(GameStart.network.getConnectionHandler().getPlayerId()).getResources();
		Resources resource = receivedTradeOffer.getResourcesDemand();
		boolean enoughResources = playerResources.get(ResourceType.GRAIN) >= resource.getGrain()
				&&playerResources.get(ResourceType.LOAM) >= resource.getLoam()
				&&playerResources.get(ResourceType.STONE) >= resource.getStone()
				&&playerResources.get(ResourceType.WOOD) >= resource.getWood()
				&&playerResources.get(ResourceType.WOOL) >= resource.getWool();


		Label headerLabel = new Label("Trade offer received!");
		Label youPayLabel = new Label("Offering: " + "(By: " + GameStart.siedlerVonCatan.findPlayerByID(receivedTradeOffer.getPlayer()).getTeam()
				+ " Nr. " + receivedTradeOffer.getTradeId() + ")");
		Label youGetLabel;
		if(enoughResources)
			youGetLabel = new Label("Price:");
		else
			youGetLabel = new Label("Price: (Insufficient resources)");

		double screenWidth = GameStart.gameView.getMainGameScene().getWidth();
		double screenHeight = GameStart.gameView.getMainGameScene().getHeight();
		HBox youPayLayout = createHbox(receivedTradeOffer.getResourcesSupply());

		HBox youGetLayout = createHbox(receivedTradeOffer.getResourcesDemand());

		layout = new VBox(30);
		//layout.setBackground(new Background(new BackgroundFill(new Color(0.2863, 0.2902, 0.2902, 0.9059), null, null)));
		layout.setEffect(GameStart.gameView.getDropShadowHexagon());
		layout.setId("roundedWindow");

		// Style labels
		Styling.styleLabel(headerLabel, screenHeight / 30);
		Styling.styleLabel(youPayLabel, screenHeight / 45);
		Styling.styleLabel(youGetLabel, screenHeight / 45);

		// Add to main layout and set size
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.setMinSize(screenWidth / 2.6, screenHeight * 11 / 18);
		layout.setMaxSize(screenWidth / 2.6, screenHeight * 11 / 18);

		// Define accept & decline button
		Button acceptButton = new Button("Trade!");
		Button refuseButton = new Button("Decline");
		if(!enoughResources)
			acceptButton.setDisable(true);

		refuseButton.setTextFill(Color.BROWN);
		acceptButton.setTextFill(Color.DARKSEAGREEN);

		Styling.styleButton(acceptButton, screenHeight / 35);
		Styling.styleButton(refuseButton, screenHeight / 35);
		HBox hBoxButtons = new HBox(30);
		hBoxButtons.getChildren().addAll(acceptButton, refuseButton);
		hBoxButtons.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(headerLabel, youPayLabel, youPayLayout, youGetLabel, youGetLayout,
				hBoxButtons);
		layout.relocate(screenWidth / 2 - layout.getMinWidth() / 2, screenHeight / 2 - layout.getMinHeight() / 2);
		// Add controller to accept and decline button
		new PlayerTradeController(acceptButton, refuseButton, receivedTradeOffer.getTradeId());
	}

	// Getters

	/**
	 * @return layout
	 */
	public VBox getLayout() {
		return layout;
	}

	/**
	 * Draws trade resources to the screen
	 *
	 * @param resources
	 */
	public HBox createHbox(Resources resources) {
		// Display cards
		HBox hBox = new HBox(10);
		hBox.setAlignment(CENTER);
		for (ResourceType type : ResourceType.values()) {
			int count = 0;
			switch (type) {

			case WOOD:
				count = resources.getWood();
				break;
			case WOOL:
				count = resources.getWool();
				break;
			case GRAIN:
				count = resources.getGrain();
				break;
			case LOAM:
				count = resources.getLoam();
				break;
			case STONE:
				count = resources.getStone();
				break;
			default:
				break;
			}
			if (type == ResourceType.HIDDEN || count <= 0)
				continue;
			ResourceCard card = new ResourceCard(type);
			card.getCardView().updateCountLabel(count);
			hBox.getChildren().add(card.getCardView().getLayout());
		}
		return hBox;
	}
}
