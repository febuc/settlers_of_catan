package view;

import application.GameStart;
import gameplay.SiedlerVonCatan;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Displays an error message as a pop up. The class will return back to the main
 * menu if selected after displaying the error message.
 *
 * @author Felip
 */
public final class ErrorPopUp {

	/**
	 * Constructor (Displays the pop Up immediately).
	 *
	 * @param errorMessageHeader
	 *            The message we want to display.
	 * @param errorMessage
	 *            An additonal message to display.
	 */
	public ErrorPopUp(String errorMessageHeader, String errorMessage, boolean getBackToMainMenu) {
		try {
			// Display error notification
			Alert alert = new Alert(AlertType.INFORMATION);
		   	alert.initOwner(GameStart.gameView.getPrimaryStage()); 
			alert.setTitle("Warning:");
			alert.setHeaderText(errorMessageHeader);
			alert.setContentText(errorMessage);
			Platform.runLater(() ->alert.show());
			if (getBackToMainMenu) {
				// Reset data structure
				GameStart.siedlerVonCatan = new SiedlerVonCatan();
				GameStart.gameView.resetVariables();
				// Go back to the main menu
				Platform.runLater(() -> GameStart.gameView.createMainMenuScene());
			}
		} catch (Exception e) {
			GameStart.mainLogger.getLOGGER().fine("ERROR POP UP not working...");
			e.printStackTrace();
		}
	}
}
