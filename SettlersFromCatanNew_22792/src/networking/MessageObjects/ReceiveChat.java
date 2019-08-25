package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Class for objects representing the messages received from server by client
 * @author Marcelina
 */
public class ReceiveChat {
	@SerializedName("Absender") private Integer sender;
	@SerializedName("Nachricht") private String message;
	/**
	 * Constructor
	 * @param sender The "ID" of the player sending the message
	 * @param message The message we are sending.
	 */
	public ReceiveChat(Integer sender, String message) {
		setSender(sender);
		setMessage(message);
	}
	
	//Getters and Setters
	/**
	 * @return the sender
	 */
	public Integer getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
