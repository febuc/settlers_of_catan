package menu;

import application.GameStart;
import controller.GameSetUpController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import resources.ResourcePointer;
import tools.PlayerTeam;
import tools.Vector2D;

/**
 * Displays settings to be configured before actually joining a game.
 * 
 * @author Felip
 *
 */
public class GameSetUp {

	private static Button labelConnectedPlayers;
	private static Button labelConnectedInfo;
	private static VBox vboxWhite;
	private static VBox vboxOrange;
	private static VBox vboxRed;
	private static VBox vboxBlue;
	
	/**
	 * Returns a layout containing the GameSetUp scene
	 * 
	 * @return The parent containing the layout for the scene.
	 */
	public Parent getGameSetUpLayout(boolean isOnline) {
		labelConnectedPlayers = new Button("-");
		labelConnectedInfo = new Button("Opponents:");
		// Basic set up
		VBox layout = new VBox(30);
		layout.setAlignment(Pos.CENTER);
		Button toMainMenu = new Button("Back to menu");
		Button playButton = new Button(isOnline ? "Ready" : "Start Adventure");
		Button selectColor = new Button("Select your team");
		HBox hBoxName = new HBox(10);
		hBoxName.setAlignment(Pos.CENTER);
		TextField playerName = new TextField("Unknown Player");
		Button generateName = new Button("Generate");
		hBoxName.getChildren().addAll(playerName,generateName);
		Button startOnlineMatch = new Button("Start Online Match");

		// Create radio buttons
		ToggleGroup group = new ToggleGroup();
		RadioButton buttonHuman = new RadioButton("Human");
		buttonHuman.setToggleGroup(group);
		buttonHuman.setSelected(true);
		RadioButton buttonPC = new RadioButton("Computer");
		buttonPC.setToggleGroup(group);
		buttonPC.setTextFill(Color.WHITE);
		labelConnectedInfo.setTextFill(Color.WHITE);
		buttonHuman.setTextFill(Color.WHITE);
		generateName.setTextFill(Color.WHITE);
		startOnlineMatch.setDisable(true);

		// Add a shadow
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(-10.0);
		dropShadow.setOffsetY(10.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.8));

		toMainMenu.setEffect(dropShadow);
		playButton.setEffect(dropShadow);
		selectColor.setEffect(dropShadow);
		playerName.setEffect(dropShadow);
		generateName.setEffect(dropShadow);
		labelConnectedPlayers.setEffect(dropShadow);
		labelConnectedInfo.setEffect(dropShadow);
		startOnlineMatch.setEffect(dropShadow);

		toMainMenu.setId("MenuButton");
		playButton.setId("MenuButton");
		labelConnectedInfo.setId("MenuButton");
		startOnlineMatch.setId("MenuButton");
		generateName.setStyle("-fx-background-color: #848484");


		Vector2D<Double> bounds = new Vector2D<>(GameStart.gameView.getPrimaryStage().getScene().getWidth(),GameStart.gameView.getPrimaryStage().getScene().getHeight());
		// Set up a font
		Font fontBasic = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.y / 45);
		Font fontSmall = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.y / 55);
		Font verySmall = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				bounds.y / 70);
		// Create HBox
		HBox hBox = new HBox(15);
		hBox.setAlignment(Pos.CENTER);
		vboxWhite = createAvatarChooser(PlayerTeam.TEAM_WHITE, fontSmall, dropShadow);
		vboxOrange = createAvatarChooser(PlayerTeam.TEAM_ORANGE, fontSmall, dropShadow);
		vboxRed = createAvatarChooser(PlayerTeam.TEAM_RED, fontSmall, dropShadow);
		vboxBlue = createAvatarChooser(PlayerTeam.TEAM_BLUE, fontSmall, dropShadow);

		hBox.getChildren().addAll(vboxWhite, vboxOrange, vboxRed, vboxBlue);

		playerName.setAlignment(Pos.CENTER);

		// Load and set the style sheet
		// Add a background
		layout.setBackground(GameStart.gameView.getBackground("BackgroundGameSetUp.png",
				new BackgroundSize(bounds.x, bounds.y, false, false, true, false), false));

		playerName.setMaxWidth(bounds.x / 6);
		playerName.setMinWidth(bounds.x / 6);

		// Assign font
		toMainMenu.setFont(fontBasic);
		playButton.setFont(fontBasic);
		generateName.setFont(verySmall);
		selectColor.setFont(fontBasic);
		playerName.setFont(fontBasic);
		buttonPC.setFont(fontBasic);
		buttonHuman.setFont(fontBasic);
		startOnlineMatch.setFont(fontBasic);
		labelConnectedPlayers.setFont(fontSmall);
		labelConnectedInfo.setFont(fontSmall);

		// Radio Buttons
		VBox radioButtonVBox = new VBox(15);
		radioButtonVBox.setMinSize(bounds.x / 9, bounds.x / 14);
		radioButtonVBox.setMaxSize(bounds.x / 9, bounds.x / 14);
		radioButtonVBox.setAlignment(Pos.CENTER);
		radioButtonVBox.setEffect(dropShadow);

		radioButtonVBox.getChildren().addAll(buttonHuman, buttonPC);
		radioButtonVBox.setId("avatar");
		// Colors
		buttonHuman.setTextFill(Color.rgb(56, 56, 56));
		buttonPC.setTextFill(Color.rgb(56, 56, 56));

		// Add objects to layout
		// Only add "startOnlineMatch" if in online mode
		if (isOnline) {
			layout.getChildren().add(startOnlineMatch);
			buttonHuman.setDisable(true);
			buttonPC.setDisable(true);
			buttonHuman.setSelected(!GameStart.network.getConnectionHandler().isAI());
			buttonPC.setSelected(GameStart.network.getConnectionHandler().isAI());
		}
		layout.getChildren().addAll(playButton, selectColor, hBox, hBoxName, radioButtonVBox, toMainMenu,
				labelConnectedInfo,labelConnectedPlayers);
		labelConnectedPlayers.setOpacity(0);
		labelConnectedInfo.setOpacity(0);
		// Add a controller
		new GameSetUpController(isOnline, toMainMenu, playButton, vboxRed, vboxWhite, vboxBlue, vboxOrange, playerName,
				selectColor, buttonHuman, buttonPC, startOnlineMatch,generateName);

		return layout;
	}

	/**
	 * Creates an avatar button chooser for a specific team (-color)
	 * 
	 * @param teamColor
	 * @param dropShadow
	 * @return
	 */
	private VBox createAvatarChooser(PlayerTeam teamColor, Font fontBasic, DropShadow dropShadow) {

		// Add a shadow
		Button buttonTeam = new Button("Team: " + teamColor.toString());

		buttonTeam.setEffect(dropShadow);
		buttonTeam.setId("MenuButton2");
		buttonTeam.setTextFill(GameStart.gameView.getColorFromTeamColor(teamColor));

		VBox vBox = new VBox(15);

		buttonTeam.setFont(fontBasic);

		// Load avatar image
		Image avatarImage = new Image(
				ResourcePointer.class.getResourceAsStream("Avatar" + teamColor.toString() + ".png"));
		Circle imageHolder = new Circle(fontBasic.getSize() * 3.5);
		Button avatar = new Button();
		// Set image
		avatar.setGraphic(imageHolder);
		imageHolder.setFill((new ImagePattern(avatarImage, 0, 0, 1, 1, true)));
		avatar.setEffect(dropShadow);
		avatar.setId("avatar");

		vBox.getChildren().addAll(avatar, buttonTeam);
		return vBox;

	}

	/**
	 * Displays message when a player connects
	 * 
	 * @param color
	 */
	public static void playerConnected(String color) {
		if(color == null)
			return;
		GameStart.mainLogger.getLOGGER().fine(color + " connected...");
		String stringToAdd;
		switch (color) {
		case ("Blau"):
			stringToAdd = "Blue";
			vboxBlue.getChildren().forEach(c->c.setDisable(true));
			break;
		case ("Weiss"):
			stringToAdd = "White";
			vboxWhite.getChildren().forEach(c->c.setDisable(true));
			break;
		case ("Orange"):
			stringToAdd = "Orange";
			vboxOrange.getChildren().forEach(c->c.setDisable(true));
			break;
		case ("Rot"):
			stringToAdd = "Red";
			vboxRed.getChildren().forEach(c->c.setDisable(true));
			break;

		default:
			return;
		}
		if (!labelConnectedPlayers.getText().contains(color)) {
			labelConnectedPlayers.setText(labelConnectedPlayers.getText()+stringToAdd+"-");
			labelConnectedPlayers.setOpacity(1);
			labelConnectedInfo.setOpacity(1);
		}
	}

}
