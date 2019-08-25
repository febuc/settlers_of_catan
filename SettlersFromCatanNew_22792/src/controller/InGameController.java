package controller;

import java.util.ArrayList;
import java.util.HashSet;

import application.GameStart;
import gameobjects.Elements.Settlement;
import gameobjects.Elements.Street;
import gameworld.HexagonField;
import gameworld.WayPoint;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import networking.MessageObjects.DomesticTradeOffer;
import networking.MessageObjects.MaritimeTrade;
import networking.MessageObjects.Resources;
import tools.*;
import player.Player;

/**
 * Controller for in game buttons
 */
public final class InGameController {

	public static Button diceButton;
	public static Button endTheTurnButton;
	private static Street[] streetsChosenForRoadBuildingCard = new Street[2];

	/**
	 * Defines action for the dice diceButton.
	 *
	 * @param diceButton
	 *            The diceButton to define the actions on.
	 */
	public void defineDiceActions(Button diceButton) {
		InGameController.diceButton = diceButton;
		diceButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		diceButton.setOnMouseClicked(e -> {
			// Send message request to server
			GameStart.network.getClientProtocol().requestDiceThrow();
			diceButton.setDisable(true);

		});
	}

	/**
	 * Defines action for the EndTurnButton button.
	 *
	 * @param endTurnButton
	 *            The button to define the actions on.
	 */
	public void defineEndTurnActions(Button endTurnButton) {
		endTheTurnButton = endTurnButton;
		endTurnButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		endTurnButton.setOnMouseClicked(e -> {
			// Send message request to server
			GameStart.network.getClientProtocol().requestTurnEnd();
			endTurnButton.setDisable(true);
		});
	}

	/**
	 * Defines action for accepting building of street
	 * 
	 * @param street
	 *            The Street object
	 * @param b
	 */
	public void defineAcceptStreetAction(Street street, Button b) {
		b.setOnMouseClicked(e -> {
			// Remove accept button (since we already clicked it)
			GameStart.gameView.removeAcceptButton();
			// Play sound
			GameStart.soundManager.playSoundConfirmPurchase();
			// Request to build street
			if (street.getBuildingType() == BuildingType.NONE) {
				if (GameStart.siedlerVonCatan.isRoadBuildingCardPlayed()) {
					GameStart.mainLogger.getLOGGER().fine("road building card phase");
					b.setOpacity(1);
					if (streetsChosenForRoadBuildingCard[0] == null) {
						streetsChosenForRoadBuildingCard[0] = street;
						//GameStart.mainLogger.getLOGGER().fine(
							//	GameStart.siedlerVonCatan.noMoreStreetSpace(streetsChosenForRoadBuildingCard[0]));
						if (GameStart.siedlerVonCatan.noMoreStreetSpace(streetsChosenForRoadBuildingCard[0])) {
							GameStart.gameView.updateInGameServerWindow("No space for the second road");
							GameStart.network.getClientProtocol()
									.requestToPlayRoadBuildingCard(streetsChosenForRoadBuildingCard);
							GameStart.siedlerVonCatan.setRoadBuildingCardPlayed(false);
							streetsChosenForRoadBuildingCard = new Street[2];
							GameStart.gameView.changeBackgroundToDefault();
						} else {
							GameStart.gameView.displayLargeMessage("Choose one more street");
							// Highlight selected street
							GameStart.gameView.highlightStreetButton(street);
							activateNearbyStreets(street.getConnectionPoints()[0]);
							activateNearbyStreets(street.getConnectionPoints()[1]);
						}
					} else if (streetsChosenForRoadBuildingCard[1] == null) {
						streetsChosenForRoadBuildingCard[1] = street;
						GameStart.network.getClientProtocol()
								.requestToPlayRoadBuildingCard(streetsChosenForRoadBuildingCard);
						GameStart.siedlerVonCatan.setRoadBuildingCardPlayed(false);
						GameStart.gameView.changeBackgroundToDefault();
						GameStart.gameView.resetStreetsCardChosen();
					}
				} else if (street.hasResourcesToBuildBuilding(GameStart.network.getConnectionHandler().getPlayerId()))
					GameStart.network.getClientProtocol().requestBuilding(street, BuildingType.STREET);
				else {
					Platform.runLater(() -> GameStart.gameView
							.displayLargeErrorMessage("Not enough resources to build a street!"));
				}
			} else
				return;
			// testing
			GameStart.mainLogger.getLOGGER().fine("Requesting purchase for: " + street);
		});
	}

	/**
	 * Defines action for clicking way point
	 * 
	 * @param wp
	 *            The wayPoint object
	 * @param b
	 */
	public void defineAcceptWayPointAction(WayPoint wp, Button b) {
		b.setOnMouseClicked(e -> {
			// Remove accept button (since we already clicked it)
			GameStart.gameView.removeAcceptButton();
			// Play sound
			GameStart.soundManager.playSoundConfirmPurchase();
			// Get settlement from the wayPoint
			Settlement settlement = wp.getSettlement();
			// TODO: Check if resources suffice
			// ...
			// Build settlement
			if (settlement.getBuildingType() == BuildingType.NONE) {
				if (settlement.hasResourcesToBuildBuilding(GameStart.network.getConnectionHandler().getPlayerId()))
					GameStart.network.getClientProtocol().requestBuilding(settlement, BuildingType.VILLAGE);
				else
					Platform.runLater(() -> GameStart.gameView
							.displayLargeErrorMessage("Not enough resources to build a village!"));
			} else if (settlement.getBuildingType() == BuildingType.VILLAGE) {
				if (settlement.hasResourcesToBuildBuilding(GameStart.network.getConnectionHandler().getPlayerId()))
					GameStart.network.getClientProtocol().requestBuilding(settlement, BuildingType.CASTLE);
				else
					Platform.runLater(() -> GameStart.gameView
							.displayLargeErrorMessage("Not enough resources to build a castle!"));
			} else
				return;

			// Request to build wayPoint
			GameStart.mainLogger.getLOGGER().fine("Requesting purchase for: " + wp);
		});
	}

	/**
	 * Defines action for when you have to click on a hexagon and accept something
	 * e.g. moving thief
	 * 
	 * @param hexagonField
	 *            The HexagonField object
	 * @param b
	 */
	public void defineAcceptHexagonFieldButton(HexagonField hexagonField, Button b) {
		b.setOnMouseClicked(e -> {
			// Remove accept button (since we already clicked it)
			GameStart.gameView.removeAcceptButton();
			// The thiefs new position is created here.
			GameStart.siedlerVonCatan.getThief().setThiefLocation(
					new Vector2D<Integer>(hexagonField.getPosition().x, hexagonField.getPosition().y));
			Vector2D<Integer> newThiefPosition = GameStart.siedlerVonCatan.getThief().getThiefPosition();

			Integer playerId = GameStart.network.getConnectionHandler().getPlayerId();
			// List of all the buildings in the game
			ArrayList<WayPoint> wayPointsAtHexagon = hexagonField.getNeighbourWayPointsWithSettlements(playerId);

			// Find out which player to steal from
			if (wayPointsAtHexagon.size() == 0) {
				GameStart.network.getClientProtocol().requestMoveThief(newThiefPosition, null);
			} else if (wayPointsAtHexagon.size() == 1) {
				GameStart.network.getClientProtocol().requestMoveThief(newThiefPosition,
						wayPointsAtHexagon.get(0).getSettlement().getOwnerID());
			} else {
				HashSet<Player> candidates = new HashSet<>();
				// We assume that settlements have owner ids!
				wayPointsAtHexagon.forEach(wp -> candidates
						.add(GameStart.siedlerVonCatan.findPlayerByID(wp.getSettlement().getOwnerID())));
				Platform.runLater(() -> GameStart.gameView.pickPlayerToStealResources(candidates));
			}
		});
	}

	/**
	 * Reduces Opacity while hovering over Village or Castle
	 *
	 * @param button
	 */
	public void defineButtonBuildingMouseAction(Button button) {
		button.setOnMouseEntered(e -> {
			button.getGraphic().setOpacity(0.75);
		});
		button.setOnMouseExited(e -> {
			button.getGraphic().setOpacity(1);
		});
	}

	/**
	 * Defines actions for avatar buttons.
	 *
	 * @param avatarButton
	 *            The button we want to define.
	 * @param teamColor
	 *            The associated color
	 */
	public void defineAvatarMouseActions(Button avatarButton, PlayerTeam teamColor) {
		avatarButton.setOnMouseClicked(click -> {
			switch (teamColor) {
			case TEAM_BLUE:
				GameStart.soundManager.playPlayerTradeSound(4);
				break;
			case TEAM_ORANGE:
				GameStart.soundManager.playPlayerTradeSound(1);
				break;
			case TEAM_RED:
				GameStart.soundManager.playPlayerTradeSound(2);
				break;
			case TEAM_WHITE:
				GameStart.soundManager.playPlayerTradeSound(3);
				break;
			default:
				break;
			}
			GameStart.gameView.createAvatarDropDownMenu(teamColor);
		});
	}

	/**
	 * Defines actions for trade buttons.
	 *
	 * @param teamColor
	 *            the team color of the player to which this button belongs
	 */
	public void defineTradeButtonActions(Button tradeButton, PlayerTeam teamColor, Player player) {

		// if(teamColor == player.getTeam())
	}

	/**
	 * Defines action for the "send trading" button
	 *
	 * @param button
	 *            The main trading button
	 * @param portType
	 *            The type we offer.
	 * @param grainButton
	 * @param woolButton
	 * @param woodButton
	 * @param stoneButton
	 * @param loamButton
	 * @param loamButtonOffer
	 * @param stoneButtonOffer
	 * @param woodButtonOffer
	 * @param woolButtonOffer
	 * @param grainButtonOffer
	 * @param cancelTradeButton
	 */
	public void defineBankTradingButton(Button button, PortTypes portType, RadioButton grainButton,
			RadioButton woolButton, RadioButton woodButton, RadioButton stoneButton, RadioButton loamButton,
			RadioButton grainButtonOffer, RadioButton woolButtonOffer, RadioButton woodButtonOffer,
			RadioButton stoneButtonOffer, RadioButton loamButtonOffer, Button cancelTradeButton) {

		cancelTradeButton.setOnMouseClicked(click -> {
			button.setDisable(true);
			cancelTradeButton.setDisable(true);
			GameStart.gameView.fadeTradingRequestAway();
			GameStart.soundManager.playSoundOnButtonHover();
		});

		button.setOnMouseClicked(click -> {
			GameStart.mainLogger.getLOGGER().fine(portType.toString());
			// Resources(Integer wood, Integer loam, Integer wool, Integer
			// grain, Integer
			// stone, Integer hidden)
			Integer wood = 0;
			Integer loam = 0;
			Integer wool = 0;
			Integer grain = 0;
			Integer stone = 0;
			Integer hidden = 0;

			Integer woodRequest = 0;
			Integer loamRequest = 0;
			Integer woolRequest = 0;
			Integer grainRequest = 0;
			Integer stoneRequest = 0;
			Integer hiddenRequest = 0;

			if (woodButton.isSelected())
				woodRequest = 1;
			else if (woolButton.isSelected())
				woolRequest = 1;
			else if (grainButton.isSelected())
				grainRequest = 1;
			else if (loamButton.isSelected())
				loamRequest = 1;
			else if (stoneButton.isSelected())
				stoneRequest = 1;
			else
				return;

			switch (portType) {

			case NONE:
				if (woodButtonOffer.isSelected())
					wood = 4;
				else if (woolButtonOffer.isSelected())
					wool = 4;
				else if (grainButtonOffer.isSelected())
					grain = 4;
				else if (loamButtonOffer.isSelected())
					loam = 4;
				else if (stoneButtonOffer.isSelected())
					stone = 4;
				else
					return;
				break;
			case THREE_FOR_ONE:
				if (woodButtonOffer.isSelected())
					wood = 3;
				else if (woolButtonOffer.isSelected())
					wool = 3;
				else if (grainButtonOffer.isSelected())
					grain = 3;
				else if (loamButtonOffer.isSelected())
					loam = 3;
				else if (stoneButtonOffer.isSelected())
					stone = 3;
				else
					return;
				break;
			case TWO_WOOD_FOR_ONE:
				wood = 2;
				break;
			case TWO_WOOL_FOR_ONE:
				wool = 2;
				break;
			case TWO_STONE_FOR_ONE:
				stone = 2;
				break;
			case TWO_GRAIN_FOR_ONE:
				grain = 2;
				break;
			case TWO_LOAM_FOR_ONE:
				loam = 2;
				break;
			}

			button.setDisable(true);
			cancelTradeButton.setDisable(true);

			Resources offerResource = new Resources(wood, loam, wool, grain, stone, hidden);
			Resources requestResource = new Resources(woodRequest, loamRequest, woolRequest, grainRequest, stoneRequest,
					hiddenRequest);
			MaritimeTrade maritimeTrade = new MaritimeTrade(offerResource, requestResource);
			GameStart.network.getClientProtocol().requestMaritimeTrade(maritimeTrade);
			GameStart.soundManager.playSoundConfirmPurchase();
			// Fade away trade window
			GameStart.gameView.fadeTradingRequestAway();
		});
	}

	/**
	 * Defines the actions for the buttons of a monopoly menu
	 */
	public void defineMonopolyButtons(Button sendButton, RadioButton grainButton, RadioButton woolButton,
			RadioButton woodButton, RadioButton stoneButton, RadioButton loamButton, Button cancelTradeButton) {

		cancelTradeButton.setOnMouseClicked(click -> {
			sendButton.setDisable(true);
			cancelTradeButton.setDisable(true);
			GameStart.soundManager.playSoundOnButtonHover();
			GameStart.gameView.fadeMonopolyAway();
		});

		sendButton.setOnMouseClicked(click -> {

			if (woodButton.isSelected())
				GameStart.network.getClientProtocol().requestToPlayMonopolyCard(ResourceType.WOOD);
			else if (woolButton.isSelected())
				GameStart.network.getClientProtocol().requestToPlayMonopolyCard(ResourceType.WOOL);
			else if (grainButton.isSelected())
				GameStart.network.getClientProtocol().requestToPlayMonopolyCard(ResourceType.GRAIN);
			else if (loamButton.isSelected())
				GameStart.network.getClientProtocol().requestToPlayMonopolyCard(ResourceType.LOAM);
			else if (stoneButton.isSelected())
				GameStart.network.getClientProtocol().requestToPlayMonopolyCard(ResourceType.STONE);
			else
				return;

			sendButton.setDisable(true);
			cancelTradeButton.setDisable(true);
			GameStart.soundManager.playSoundConfirmPurchase();
			// Fade away monopoly window
			GameStart.gameView.fadeMonopolyAway();
		});
	}

	/**
	 * Defines the actions for the buttons of a year of plenty menu
	 */
	public void defineYearOfPleantyButtons(ResourceType firstResource, Button sendButton, RadioButton grainButton,
			RadioButton woolButton, RadioButton woodButton, RadioButton stoneButton, RadioButton loamButton,
			Button cancelTradeButton) {

		cancelTradeButton.setOnMouseClicked(click -> {
			sendButton.setDisable(true);
			cancelTradeButton.setDisable(true);
			GameStart.soundManager.playSoundOnButtonHover();
			GameStart.gameView.fadeYearOfPleantyAway();
		});

		sendButton.setOnMouseClicked(click -> {

			if (firstResource == null) { // Choosing the first resource
				if (woodButton.isSelected()) {
					GameStart.gameView.drawOptionsForYearOfPleanty(ResourceType.WOOD);
				} else if (woolButton.isSelected()) {
					GameStart.gameView.drawOptionsForYearOfPleanty(ResourceType.WOOL);
				} else if (grainButton.isSelected()) {
					GameStart.gameView.drawOptionsForYearOfPleanty(ResourceType.GRAIN);
				} else if (loamButton.isSelected()) {
					GameStart.gameView.drawOptionsForYearOfPleanty(ResourceType.LOAM);
				} else if (stoneButton.isSelected()) {
					GameStart.gameView.drawOptionsForYearOfPleanty(ResourceType.STONE);
				} else
					return;
			} else { // Choosing the second resource and initialize the request to the server
				if (woodButton.isSelected())
					GameStart.network.getClientProtocol().requestToPlayYearOfPlentyCard(firstResource,
							ResourceType.WOOD);
				else if (woolButton.isSelected())
					GameStart.network.getClientProtocol().requestToPlayYearOfPlentyCard(firstResource,
							ResourceType.WOOL);
				else if (grainButton.isSelected())
					GameStart.network.getClientProtocol().requestToPlayYearOfPlentyCard(firstResource,
							ResourceType.GRAIN);
				else if (loamButton.isSelected())
					GameStart.network.getClientProtocol().requestToPlayYearOfPlentyCard(firstResource,
							ResourceType.LOAM);
				else if (stoneButton.isSelected())
					GameStart.network.getClientProtocol().requestToPlayYearOfPlentyCard(firstResource,
							ResourceType.STONE);
				else
					return;
				sendButton.setDisable(true);
				cancelTradeButton.setDisable(true);
				GameStart.soundManager.playSoundConfirmPurchase();
				// Fade away year of plenty window
				GameStart.gameView.fadeYearOfPleantyAway();
			}

		});
	}

	/**
	 * Defines the click action for trading 4:1 with the bank
	 *
	 * @param button
	 */
	public void defineActionToShowBasicBankTradingWindow(Button button) {
		button.setOnMouseClicked(click -> {
			GameStart.soundManager.playSoundOnHexagonOver();
			// NONE <=> 4 : 1 Trade
			GameStart.gameView.getLayout().getChildren().remove(GameStart.gameView.getDropDownMenu());
			GameStart.gameView.drawBankTrading(PortTypes.NONE);

		});
	}

	/**
	 * Defines the click action to show a player trading window.
	 *
	 * @param button
	 *            The button we want to define.
	 * @param color
	 *            The color of the player we want to trade with.
	 */
	public void defineActionToShowPlayerTradingWindow(Button button, PlayerTeam color) {
		button.setOnMouseClicked(click -> {
			GameStart.soundManager.playSoundConfirmPurchase();
			GameStart.gameView.getLayout().getChildren().remove(GameStart.gameView.getDropDownMenu());
			GameStart.gameView.drawPlayerTrading(color);
		});
	}

	/**
	 * Defines actions for player trading.
	 *
	 * @param grainSliderOffer
	 * @param woodSliderOffer
	 * @param woolSliderOffer
	 * @param stoneSliderOffer
	 * @param loamSliderOffer
	 * @param grainSliderRequest
	 * @param woodSliderRequest
	 * @param woolSliderRequest
	 * @param stoneSliderRequest
	 * @param loamSliderRequest
	 * @param sendTradeButton
	 * @param cancelTradeButton
	 */
	public void definePlayerTradingButtonAction(Slider grainSliderOffer, Slider woodSliderOffer, Slider woolSliderOffer,
			Slider stoneSliderOffer, Slider loamSliderOffer, Slider grainSliderRequest, Slider woodSliderRequest,
			Slider woolSliderRequest, Slider stoneSliderRequest, Slider loamSliderRequest, Button sendTradeButton,
			Button cancelTradeButton) {

		cancelTradeButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		sendTradeButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());

		cancelTradeButton.setOnMouseClicked(click -> {
			GameStart.gameView.fadeTradingRequestAway();
		});
		sendTradeButton.setOnMouseClicked(click -> {
			Resources offerResource = new Resources((int) woodSliderOffer.getValue(), (int) loamSliderOffer.getValue(),
					(int) woolSliderOffer.getValue(), (int) grainSliderOffer.getValue(),
					(int) stoneSliderOffer.getValue(), 0);
			Resources requestResource = new Resources((int) woodSliderRequest.getValue(),
					(int) loamSliderRequest.getValue(), (int) woolSliderRequest.getValue(),
					(int) grainSliderRequest.getValue(), (int) stoneSliderRequest.getValue(), 0);

			// Quit if both resources are empty
			if (offerResource.getTotalResources() == 0 && requestResource.getTotalResources() == 0)
				return;
			DomesticTradeOffer domesticTradeOffer = new DomesticTradeOffer(offerResource, requestResource);
			GameStart.network.getClientProtocol().requestDomesticTrade(domesticTradeOffer);
			// Remove request trade window
			GameStart.gameView.fadeTradingRequestAway();

			// Mark as waiting for acceptors
			GameStart.siedlerVonCatan.findPlayerByID(GameStart.network.getConnectionHandler().getPlayerId())
					.setWaitingForTradeRequests(true);

		});

	}

	/**
	 * Used to decline domestic trade.
	 *
	 * @param acceptedTradeRequestButton
	 * @param tradeID
	 */
	public void defineTradersButton(Button acceptedTradeRequestButton, Integer tradeID) {
		acceptedTradeRequestButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		acceptedTradeRequestButton.setOnMouseClicked(e -> {
			if (acceptedTradeRequestButton.getText().contains("Cancel")) {
				GameStart.network.getClientProtocol().abandonDomesticTrade(tradeID);
				GameStart.gameView.disableTradersWindow();
			} else {
				acceptedTradeRequestButton.setText("Cancel trade");
				GameStart.soundManager.playSoundOnHexagonOver();
			}
		});

	}

	/**
	 * Used to execute domestic trade.
	 *
	 * @param playerButton
	 * @param tradeID
	 */
	public void defineTradePartnerButton(Button playerButton, Integer tradeID, Integer playerID) {
		playerButton.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		playerButton.setOnMouseClicked(e -> {
			GameStart.soundManager.playSoundConfirmPurchase();
			GameStart.network.getClientProtocol().completeDomesticTrade(tradeID, playerID);
			GameStart.gameView.disableTradersWindow();
		});
	}

	/**
	 * Define pick thief target action.
	 *
	 * @param button
	 * @param player
	 * @param vbox
	 */
	public void definePickThiefTarget(Button button, Player player, VBox vbox) {
		button.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		button.setOnMouseClicked(e -> {
			// Remove vbox
			GameStart.gameView.getLayout().getChildren().remove(vbox);
			// Send message to server
			GameStart.network.getClientProtocol()
					.requestMoveThief(GameStart.siedlerVonCatan.getThief().getThiefPosition(), player.getPlayerID());
		});
	}

	/**
	 * Defines the behavior for the request development card button
	 *
	 * @param rectangle
	 */
	public void defineGetDevelopmentCardActions(Rectangle rectangle) {
		rectangle.setOnMouseEntered(e -> {
			rectangle.setOpacity(0.8);
			GameStart.soundManager.playSoundOnButtonHover();
		});
		rectangle.setOnMouseClicked(e -> {
			GameStart.gameView.removeMenus();
			GameStart.network.getClientProtocol().sendBuyDevelopmentCardRequest();
			rectangle.setOpacity(0.6);
		});
		rectangle.setOnMouseExited(e -> rectangle.setOpacity(1));
	}

	/**
	 * Activates streets next to a given way point
	 *
	 * @param wp
	 */
	public void activateNearbyStreets(WayPoint wp) {
		for (WayPoint neighbour : wp.getWayPointNeighbours()) {
			// neighbour.getSettlement().setOccupied(true);
			Street street = GameStart.gameView.wayPointsToStreet.get(new Vector2D<>(wp, neighbour));
			if (street != null)
				Platform.runLater(() -> GameStart.gameView.activateStreetButtonRoadBuilding(street));
		}
	}

	/**
	 * Resets the streets chosen for the roadbuildingcard to null
	 */
	public static void resetStreetsChosenForRoadBuildingCard() {
		streetsChosenForRoadBuildingCard[0] = null;
		streetsChosenForRoadBuildingCard[1] = null;
	}

	/**
	 * Defines button actions for aborting an already accepted trade
	 */
	public void defineTraderCancelButton(Button button, Integer tradeID) {
		button.setOnMouseClicked(e -> {
			GameStart.network.getClientProtocol().abandonDomesticTrade(tradeID);
			if (GameStart.gameView.abortTradeButton != null
					&& GameStart.gameView.getLayout().getChildren().contains(button))
				GameStart.gameView.getLayout().getChildren().remove(button);
		});
	}
}
