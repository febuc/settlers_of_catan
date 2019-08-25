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
 * The victory screen that appears after you've won the game
 * 
 * @author Panos & Felicitas
 *
 */
public class VictoryScreen {

	/**
	 * a boolean to check if we are currently in the victory screen
	 */
	private Boolean isVictoryScreen = true;

	/**
	 * sets up victory screen + styling of Buttons, Labels etc.
	 * 
	 * @param gameView
	 * @return VBox layout
	 */
	public Parent getVictoryLayout(GameView gameView) {
		
		

		// creates the main layout and positions it
		GameStart.soundManager.playVictorySound();
		VBox layout = new VBox(50);
		layout.setAlignment(Pos.CENTER);

		// creates labels and the exit button
		Label label1 = new Label("Congratulations!");
		Label label = new Label("You were victorious");
		Button quitGameButton = new Button("Exit game");
		quitGameButton.setId("VictoryButton");

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
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(-5.0);
		dropShadow.setOffsetY(6.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.6));
		label.setEffect(dropShadow);
		label1.setEffect(dropShadow);
		quitGameButton.setEffect(dropShadow);
		FadeTransition fadeAnimation = new FadeTransition(Duration.millis(1500), label);
		fadeAnimation.setFromValue(0.0);
		fadeAnimation.setToValue(1.0);
		fadeAnimation.setCycleCount(5); // -1 == infinity
		fadeAnimation.setAutoReverse(true);
		fadeAnimation.play();
		FadeTransition fadeAnimation1 = new FadeTransition(Duration.millis(1500), label1);
		fadeAnimation1.setFromValue(0.0);
		fadeAnimation1.setToValue(1.0);
		fadeAnimation1.setCycleCount(5); // -1 == infinity
		fadeAnimation1.setAutoReverse(true);
		fadeAnimation1.play();

		label.setStyle("-fx-background-color:transparent; -fx-text-fill:rgb(190,160,138);"
				+ "-fx-text-fill: linear-gradient(rgb(95,70,52), rgb(203,179,160));");
		label1.setStyle("-fx-background-color:transparent; -fx-text-fill:rgb(190,160,138);"
				+ "-fx-text-fill: linear-gradient(rgb(95,70,52), rgb(203,179,160));");

		quitGameButton.setTranslateY(150);

		// Assign font
		label.setFont(fontLabel);
		label1.setFont(fontLabel);
		quitGameButton.setFont(fontBasic);

		// Add objects to layout
		layout.getChildren().addAll(label1, label, quitGameButton);
		layout.getStylesheets().add(ResourcePointer.class.getResource("Tutorial.css").toExternalForm());

		// Add a background
		layout.setBackground(gameView.getBackground("Victory1.jpg",
				new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, true, false), false));

		// adds action for the quick button after it was clicked
		quitGameButton.setOnMouseClicked(e -> {
			// Disconnect from server
			GameStart.network.getConnectionHandler().disconnectFromServer();
			GameStart.siedlerVonCatan = new SiedlerVonCatan();
			gameView.resetVariables();
			// Go back to the main menu
			gameView.createMainMenuScene();
		});
		quitGameButton.setOnMouseEntered(event -> GameStart.soundManager.playSoundOnButtonHover());

		// Return layout
		return layout;
	}

	/**
	 * Getter for the isVictoryScreen attribute
	 * 
	 * @return isVictoryScreen
	 */
	public Boolean getIsVictoryScreen() {
		return isVictoryScreen;
	}

	/**
	 * Setter for the isVictoryScreen attribute
	 * 
	 * @param isVictoryScreen
	 */
	public void setIsVictoryScreen(Boolean isVictoryScreen) {
		this.isVictoryScreen = isVictoryScreen;
	}

}
