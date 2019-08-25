package networking;

/**
 * Handles everything to do with networking and connection actions
 */
public class Network{
	/**
	 * Our referenced protocol
	 */
	private ClientProtocol protocol;
	private ServerConnectionHandler connectionHandler;
	private String serverIP;
	private int serverPort;
	
	/**
	 * Constructor
	 */
	public Network(String ip, int port) {
		this.serverIP = ip;
		this.serverPort = port;
		connectionHandler = new ServerConnectionHandler(this, serverIP, serverPort);
		connectionHandler.start();
		protocol = new ClientProtocol(connectionHandler);		
	}
	public void sendMessage(String message){
		connectionHandler.sendMessage(message);
	}
	public void disconnectFromServer(){
		connectionHandler.disconnectFromServer();
	}
	public ServerConnectionHandler getConnectionHandler(){
		return connectionHandler;
	}
	public ClientProtocol getClientProtocol(){
		return protocol;
	}
}