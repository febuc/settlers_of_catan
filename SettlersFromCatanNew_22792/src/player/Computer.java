package player;

import application.GameStart;
import networking.Network;
import tools.PlayerTeam;

/**
 * This is a subclass of Player.
 *
 * @author Panos,Felip
 *
 */
public class Computer extends Player {
	private Network network;

	/**
	 * This is the constructor of this class. It uses the constructor of Player. AI
	 * is set to true in here because this is a Computer.
	 *
	 * @param team
	 *            This is the team-color of the player.
	 * @param name
	 *            This is the name of the player.
	 * @param connectKiToServer
	 *            True if KI should connect to server (USE: Singleplayer autofill
	 *            empty slots)
	 */
	public Computer(PlayerTeam team, String name, boolean connectKiToServer) {
		super(team, name);
		setAI(true);
		// Set up a connection to the server (if connectKiToServer == true)
		if (connectKiToServer) {
			//Connect to server
			joinSinglePlayerServer();
		}else{
            GameStart.network.getConnectionHandler().setAI(true);
        }
	}

	/**
	 * Joins the single player host server by sending requests to join.
	 */
	private void joinSinglePlayerServer() {
		network = new Network("127.0.0.1", 8080);
		//Mark as singleplayer ai
		network.getConnectionHandler().setSinglePlayerAI(true);
	}
	// Getter and Setter
	public Network getNetwork() {
		return network;
	}

}
