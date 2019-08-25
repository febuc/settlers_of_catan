package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;


/**
 * Simulates an error message sent by the server.
 * @author Felip
 *
 */
public class ServerError {
	
	@SerializedName("Meldung") private String errorMessage;
	
	/**
	 * Constructor
	 * @param errorMessage The error message form the server
	 */
	public ServerError(String errorMessage) {
		this.setErrorMessage(errorMessage);
	}
	//Getters and Setters
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
