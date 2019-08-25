package menu;

import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;
import resources.ResourcePointer;
import view.GameView;

/**
 * Simulates a splash screen which gets initialized when the game starts. Once a
 * players presses any key the actual game launches.
 * 
 * @author Felip
 *
 */
public class SplashScreen {

	/**
	 * Creates a splash scene for the game.
	 */
	public Scene createSplashScene(GameView gameView) {
		StackPane layout = new StackPane();
		Scene splashScene = new Scene(layout);
		// Set bounds
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		// Set up a font
		Font fontBasic = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.getHeight() / 17);

		// Load and set the style sheet
		splashScene.setFill(Color.BLACK);
		splashScene.getStylesheets().add(ResourcePointer.class.getResource("Style.css").toExternalForm());

		Label label = new Label("Press any key to start");
		// Add shadow
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(-4.0);
		dropShadow.setOffsetY(4.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.75));
		label.setEffect(dropShadow);
		// Assign font
		label.setFont(fontBasic);

		layout.getChildren().add(label);
		// Add background
		layout.setBackground(gameView.getBackground("BlackFlag.jpg",
				new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, true, false), false));
		// Set up an animation for the label (Message cycle)
		FadeTransition fadeAnimation = new FadeTransition(Duration.millis(2000), label);
		fadeAnimation.setFromValue(0.0);
		fadeAnimation.setToValue(1.0);
		fadeAnimation.setCycleCount(-1); // -1 == infinity
		fadeAnimation.setAutoReverse(true);
		fadeAnimation.play();

		// Set action when pressing any key
		Button buttonAction = new Button();
		buttonAction.setOpacity(0);
		layout.getChildren().add(buttonAction);

		buttonAction.setOnKeyPressed(e -> {
			layout.getChildren().clear();
			gameView.createMainMenuScene();
		});
		label.setOnMouseClicked(e -> {
			layout.getChildren().clear();
			gameView.createMainMenuScene();
		});
		return splashScene;
	}
}
