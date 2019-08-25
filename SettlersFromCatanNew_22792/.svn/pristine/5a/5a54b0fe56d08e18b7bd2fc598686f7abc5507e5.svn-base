package menu;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import resources.ResourcePointer;
import view.GameView;

/**
 * A slightly different main menu that appears only if you click the "go back to
 * main menu" button in the pause screen. The difference is that some Buttons
 * like Singleplayer and Multiplayer are missing and instead you'll find a
 * continue button that brings up the game screen again
 * 
 * @author Panos, Felicitas
 */
public class ContinueMainMenu {

	/**
	 * The main menu root
	 */
	private Parent root;

//	/**
//	 * The gameView from the game
//	 */
//	private GameView gameView;

	private Button continueButton = new Button("Continue game");
	private Button rules = new Button("How to play");
	private Button settings = new Button("Settings");
	private Button quit = new Button("Quit");
	private Label soundLabel = new Label("Change sound volume");
	private Slider musicVolumeSlider = new Slider(1, 10, 10);
	private VBox vBox;
	private HBox hBox;
	private boolean settingsHBoxShowing;

	/**
	 * Constructor. Creates the second main Menu
	 */
	public ContinueMainMenu(GameView gameView) {
//		this.gameView = gameView;
		vBox = new VBox(20);
		hBox = new HBox(20);
		hBox.setAlignment(Pos.CENTER);
		root = vBox;

		// VBox layout = new VBox(20);
		// root = layout;
		vBox.setAlignment(Pos.CENTER);

		musicVolumeSlider.setShowTickLabels(true);
		musicVolumeSlider.setShowTickMarks(true);
		musicVolumeSlider.setMajorTickUnit(1);
		musicVolumeSlider.setMinorTickCount(0);
		musicVolumeSlider.setSnapToTicks(true);
		musicVolumeSlider.setBlockIncrement(1);

		// Set offset position
		continueButton.setTranslateY(60);
		rules.setTranslateY(60);
		settings.setTranslateY(60);
		quit.setTranslateY(60);
		soundLabel.setTranslateY(60);
		musicVolumeSlider.setTranslateY(60);
		hBox.setTranslateY(80);

		// Add a shadow
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(4.0);
		dropShadow.setOffsetX(-10.0);
		dropShadow.setOffsetY(10.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.7));
		// Set id
		continueButton.setId("MenuButton");
		rules.setId("MenuButton");
		settings.setId("MenuButton");
		quit.setId("MenuButton");

		continueButton.setEffect(dropShadow);
		rules.setEffect(dropShadow);
		settings.setEffect(dropShadow);
		quit.setEffect(dropShadow);

		DropShadow dropShadow2 = new DropShadow();
		dropShadow2.setRadius(5.0);
		dropShadow2.setOffsetX(-5.0);
		dropShadow2.setOffsetY(5.0);
		dropShadow2.setColor(Color.color(0, 0, 0, 0.6));

		soundLabel.setEffect(dropShadow2);

		// Add a background image to the scene
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		vBox.setBackground(gameView.getBackground("MainMenuBackground1.jpg",
				new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, true, false), false));

		// Set up a font
		Font fontBasic = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.getHeight() / 45);
		// hBox.setPadding(new Insets(0, 0, -bounds.getHeight() / 10, 0));

		musicVolumeSlider.setMaxWidth(bounds.getWidth() / 6);
		musicVolumeSlider.setMinWidth(bounds.getWidth() / 6);

		// Assign font
		continueButton.setFont(fontBasic);
		rules.setFont(fontBasic);
		settings.setFont(fontBasic);
		quit.setFont(fontBasic);
		soundLabel.setFont(fontBasic);

		// Add objects to layout
		vBox.getChildren().addAll(continueButton, rules, settings, quit);
	}

	public void changeSettingsHBox() {
		if (!settingsHBoxShowing) {
			settings.setText("Back");
			vBox.getChildren().clear();
			hBox.getChildren().clear();
			hBox.getChildren().addAll(quit);
			vBox.getChildren().addAll(continueButton, rules, settings, soundLabel, musicVolumeSlider);
			vBox.getChildren().add(hBox);
			settingsHBoxShowing = true;
		} else {
			settings.setText("Settings");
			vBox.getChildren().remove(hBox);
			vBox.getChildren().clear();
			vBox.getChildren().addAll(continueButton, rules, settings, quit);
			settingsHBoxShowing = false;
		}
	}

	// Getter & Setter
	/**
	 * Returns the main menu's root.
	 * 
	 * @return root
	 */
	public Parent getRoot() {
		return root;
	}

//	/**
//	 * Returns the application which is the view of the entire game
//	 * 
//	 * @return gameView
//	 */
//	public GameView getGameView() {
//		return gameView;
//	}

	/**
	 * Returns the continue button
	 * 
	 * @return continueButton
	 */
	public Button getContinueButton() {
		return continueButton;
	}

	/**
	 * Returns the rules button
	 * 
	 * @return rules
	 */
	public Button getRulesButton() {
		return rules;
	}

	/**
	 * Returns the settings button
	 * 
	 * @return settings
	 */
	public Button getSettingsButton() {
		return settings;
	}

	/**
	 * Returns the quitGame button
	 * 
	 * @return quitGame
	 */
	public Button getQuitButton() {
		return quit;
	}

	/**
	 * gets the music volume slider from the settings
	 * @return musicVolumeSlider
	 */
	public Slider getMusicVolumeSlider() {
		return musicVolumeSlider;
	}

}
