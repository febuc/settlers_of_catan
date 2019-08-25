package menu;

import application.GameStart;
import gameplay.SiedlerVonCatan;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;
import resources.ResourcePointer;
import view.GameView;

/**
 * The gameover screen that appears after you've lost the game
 * 
 * @author Panos & Felicitas
 *
 */
public class GameOverScreen {

	/**
	 * a boolean to check if we are currently in the gameover screen
	 */
	private Boolean isGameOverScreen = true;

	/**
	 * sets up game over screen + styling of Buttons, Labels etc.
	 * 
	 * @param gameView
	 * @return VBox layout
	 */
	public Parent getGameOverLayout(GameView gameView) {

		gameView.setWorldIsDrawn(false);

		// creates the main layout and positions it
		VBox layout = new VBox(150);
		layout.setAlignment(Pos.CENTER);
		GameStart.soundManager.playDefeatSound();

		// create label and button
		Label label = new Label("You were defeated");
		Button backToMenuButton = new Button("Back to menu");
		backToMenuButton.setId("GameOverButton");

		// gets bounds to draw buttons/labels etc. relative to the screen resolution
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		// Set up a font
		Font fontBasic = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.getHeight() / 30);
		Font fontLabel = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.getHeight() / 8);

		// Load and set the style sheet
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(-10.0);
		dropShadow.setOffsetY(9.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.8));
		label.setEffect(dropShadow);
		label.setTranslateY(110);
		backToMenuButton.setEffect(dropShadow);
		backToMenuButton.setTranslateY(150);
		label.setTextFill(Color.rgb(92,61,56));
		// Set up an animation for the label (Message cycle)
		FadeTransition fadeAnimation = new FadeTransition(Duration.millis(1500), label);
		fadeAnimation.setFromValue(0.0);
		fadeAnimation.setToValue(1.0);
		fadeAnimation.setCycleCount(5); // -1 == infinity
		fadeAnimation.setAutoReverse(true);
		fadeAnimation.play();
		// label.setTranslateY(20);
		// label.setStyle("-fx-background-color:transparent;
		// -fx-text-fill:rgb(92,61,56);"
		// + "-fx-text-fill: linear-gradient(rgb(92,61,56), rgb(126,108,97))");

		// Assign font
		label.setFont(fontLabel);
		backToMenuButton.setFont(fontBasic);

		// Add objects to layout
		layout.getChildren().addAll(label, backToMenuButton);

		// Add a background
		layout.setBackground(gameView.getBackground("DefeatScreen.jpg",
				new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, true, false), false));

		// Define button action
		backToMenuButton.setOnMouseClicked(e -> {
			// Disconnect from server
			GameStart.network.getConnectionHandler().disconnectFromServer();
			GameStart.siedlerVonCatan = new SiedlerVonCatan();
			gameView.resetVariables();
			// Go back to the main menu
			gameView.createMainMenuScene();
		});
		backToMenuButton.setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());

		// Return layout
		return layout;
	}

	/**
	 * Getter for the isGameOverScreen attribute
	 * 
	 * @return isGameOverScreen
	 */
	public Boolean getIsGameOverScreen() {
		return isGameOverScreen;
	}

	/**
	 * Setter for the isGameOverScreen attribute
	 * 
	 * @param isGameOverScreen
	 */
	public void setIsGameOverScreen(Boolean isGameOverScreen) {
		this.isGameOverScreen = isGameOverScreen;
	}

}
