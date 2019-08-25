package controller;

import application.GameStart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * Controller for the in-game client chat window.
 * 
 * @author Felip
 *
 */
public final class ChatWindowController {

	/**
	 * Constructor (assigns all needed actions to the "chat window")
	 */
	public ChatWindowController(TextField textField) {
		//Hitting "Enter" (== send message)
		textField.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				String message = textField.getText();
				textField.clear();
				//Send message to server
				GameStart.network.getClientProtocol().requestToSendChat(message);
				
			}
		});

	}
}
