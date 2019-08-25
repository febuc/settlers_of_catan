package controller;

import application.GameStart;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import menu.Credits;
import menu.MainMenu;
import networking.Network;
import view.GameView;

/**
 * Activates the buttons of the main menu
 * 
 * @author Jonas
 *
 */

public final class MainMenuController {

	/**
	 * A reference to the menu that we want to check with this class
	 */
	private MainMenu mainMenu;
	
	/**
	 * Constructor
	 * 
	 * @param mainMenu
	 */
	public MainMenuController(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		initializeButtonHandlers();
		initializeButtonHoverHandler();
	}

	/**
	 * Activates the buttons and reacts depending on which was pressed
	 */
	private void initializeButtonHandlers() {
		mainMenu.getQuitButton().setOnMouseClicked(event -> {
			System.exit(0);
		});
		mainMenu.getSinglePlayerButton().setOnMouseClicked(event -> {
			// If single player, check if server is running
			connectToServer("127.0.0.1", 8080);
			GameStart.gameView.showGameSetUp(false);
		});
		mainMenu.getMultiplayerButton().setOnMouseClicked(event -> {
			mainMenu.changeMultiplayerHBox();
		});
		mainMenu.getRulesButton().setOnMouseClicked(event -> {
			showGameRules(mainMenu.getGameView());
		});
		mainMenu.getCreditsButton().setOnMouseClicked(event -> {
			Credits credits = new Credits();
			credits.setIsCreditsScreen(true);
			GameStart.gameView.swapScenes(credits.getCreditsLayout(GameStart.gameView));
			if (credits.getIsCreditsScreen()) {
				GameStart.gameView.getPrimaryStage().getScene().setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.ESCAPE) {
						credits.setIsCreditsScreen(false);
						GameStart.gameView.createMainMenuScene();
						GameStart.gameView.getPrimaryStage().getScene().setOnKeyPressed(null);
					}
				});}
		});
		mainMenu.getSettingsButton().setOnAction(event -> {
			mainMenu.changeSettingsHBox();
		});
		mainMenu.getConnectButton().setOnMouseClicked(event -> {
			connectToServer(mainMenu.getIpAddress().getText(), Integer.parseInt(mainMenu.getPortNumber().getText()),
					mainMenu.getButtonPC().isSelected());
			GameStart.gameView.showGameSetUp(true);
		});
		mainMenu.getMusicVolumeSlider().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GameStart.soundManager.changeBackgroundMusicVolume((double) newValue);
			}
		});
		mainMenu.getSoundEffectsVolumeSlider().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GameStart.soundManager.changeSoundEffectsVolume((double) newValue);
			}
		});
	}

	/**
	 * Activates an animation and a sound whenever somebody hovers over one of the
	 * buttons
	 */
	private void initializeButtonHoverHandler() {
		mainMenu.getSinglePlayerButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
		mainMenu.getMultiplayerButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
		mainMenu.getRulesButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
		mainMenu.getCreditsButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
		mainMenu.getSettingsButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
		mainMenu.getQuitButton().setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());
	}

	/**
	 * Shows the game rules
	 */
	private void showGameRules(GameView gameView) {
		gameView.showGameRules();
	}

	public void connectToServer(String ip, int port) {
		GameStart.network = new Network(ip, port);
	}

	private void connectToServer(String ip, int port, boolean connectAsMultiplayerAi) {
		connectToServer(ip, port);
		GameStart.network.getConnectionHandler().setAI(connectAsMultiplayerAi);
	}
}
