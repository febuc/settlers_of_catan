package networking.MessageObjects;

import com.google.gson.annotations.SerializedName;

/**
 * 	Class representing the initial messages between client and server
 * @author Marcelina
 */
public class InitialMessage{
	@SerializedName("Version") private String version;
	@SerializedName("Protokoll") private String protocol;
	/**
	 * constructor
	 * @param version server version 
	 * @param protokoll used protocol version
	 */
	public InitialMessage(String version, String protocol) {
		this.version = version;
		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public InitialMessage(String clientVersion) {
		this.version = clientVersion;
	}

	public String getVersion(){
		return this.version;
	}
}
