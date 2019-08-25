package controller;

import application.GameStart;
import javafx.scene.control.Button;

/**
 * Controller class for trading buttons
 * 
 * @author Felip
 *
 */
public final class PlayerTradeController {

	/**
	 * Constructor.
	 * 
	 * @param accept
	 *            The button to accept the trade.
	 * @param decline
	 *            The button to decline the trade.
	 * @param tradeID 
	 */
	public PlayerTradeController(Button accept, Button decline, int tradeID) {
		accept.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		decline.setOnMouseEntered(e -> GameStart.soundManager.playSoundOnButtonHover());
		decline.setOnMouseClicked(e -> {
			//GameStart.network.getClientProtocol().abandonDomesticTrade(tradeID);
			GameStart.network.getClientProtocol().declineDomesticTrade(tradeID);
			GameStart.gameView.fadeAwayPlayerTradingWindow();
		});
		accept.setOnMouseClicked(e -> {
			GameStart.network.getClientProtocol().acceptDomesticTrade(tradeID);
			GameStart.gameView.fadeAwayPlayerTradingWindow();
		});
	}
}
