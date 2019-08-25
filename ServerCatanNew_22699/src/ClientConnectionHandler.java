import com.google.gson.Gson;

import networking.MessageObjects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * Handles messages sent from server to client and send messages or objects.
 *
 */
public class ClientConnectionHandler extends Thread {
	private Server server;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private Boolean clientActive;
	private ServerProtocol protocol;
	private int playerId;

/**
 * Sets up connectionhandler for new client.
 * @param server corresponding server
 * @param socket corresponding socket
 */
	public ClientConnectionHandler(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		this.clientActive = true;
		protocol = new ServerProtocol(this);

		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		protocol.sendHello(server.getVersion(), server.getProtocolInfo());
	}
	
	/**
	 * Reads message.
	 */
	@Override
	public void run() {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				handleMessage(line);
			}

			reader.close();
			writer.close();

		} catch (Exception e) {
			//GameStart.mainLogger.getLOGGER()().fine("ERROR [#002]: " + e.getMessage());
			//e.printStackTrace();
			//GameStart.mainLogger.getLOGGER()().fine("Client connection disrupted");
		}

		server.removeConnectionHandler(this);
	}
	
	/**
	 * Sends message.
	 * @param message message
	 */
	public void sendMessage(String message) {
		if (clientActive) {
			Gson gson = new Gson();
			String mes = gson.toJson(message);
			writer.println(mes);
		}
	}

	/**
	 * Sends Object and prints message.
	 * @param obj
	 */
	public synchronized void sendObject(Object obj) {
		if (clientActive) {
			Gson gson = new Gson();
			// 4.1
			if (obj instanceof InitialMessage)
				writer.println("{\"Hallo\":" + (gson.toJson(obj, InitialMessage.class)) + "}");
				// 4.3
			else if (obj instanceof WelcomeMessage)
				writer.println("{\"Willkommen\":" + (gson.toJson(obj, WelcomeMessage.class)) + "}");
				// 6.1
			else if (obj instanceof ServerResponse)
				writer.println(gson.toJson(obj, ServerResponse.class));
				// 6.3
			else if (obj instanceof ReceiveChat)
				writer.println("{\"Chatnachricht\":" + gson.toJson(obj, ReceiveChat.class) + "}");
				// 7.3
			else if (obj instanceof ServerError)
				writer.println("{\"Fehler\":" + gson.toJson(obj, ServerError.class) + "}");
				// 7.4
			else if (obj instanceof GameStarted)
				writer.println("{\"Spiel gestartet\":" + gson.toJson(obj, GameStarted.class) + "}");
				// 8.1
			else if (obj instanceof PlayerStatusUpdate)
				writer.println("{\"Statusupdate\":" + gson.toJson(obj, PlayerStatusUpdate.class) + "}");
				// 8.2
			else if (obj instanceof DiceThrow)
				writer.println("{\"Wuerfelwurf\":" + gson.toJson(obj, DiceThrow.class) + "}");
				// 8.3
			else if (obj instanceof Earnings)
				writer.println("{\"Ertrag\":" + gson.toJson(obj, Earnings.class) + "}");
				// 8.6
			else if (obj instanceof Costs)
				writer.println("{\"Kosten\":" + gson.toJson(obj, Costs.class) + "}");
				// 8.5
			else if (obj instanceof ThiefMoved)
				writer.println("{\"Raeuber versetzt\":" + gson.toJson(obj, ThiefMoved.class) + "}");
				// 8.4
			else if (obj instanceof BuildingEvent)
				writer.println("{\"Bauvorgang\":" + gson.toJson(obj, BuildingEvent.class) + "}");
				//9.7
			else if (obj instanceof DevelopmentCardBought)
				writer.println("{\"Entwicklungskarte gekauft\":" + gson.toJson(obj, DevelopmentCardBought.class) + "}");
				//9.10
			else if (obj instanceof LongestRoad)
				writer.println("{\"Laengste Handelsstrasse\":" + gson.toJson(obj, LongestRoad.class) + "}");
				//9.10
			else if (obj instanceof LargestArmy)
				writer.println("{\"Groesste Rittermacht\":" + gson.toJson(obj, LargestArmy.class) + "}");
				// 10.1
			else if (obj instanceof ReceivedTradeOffer)
				writer.println("{\"Handelsangebot\":" + gson.toJson(obj, ReceivedTradeOffer.class) + "}");
				// 10.2
			else if (obj instanceof PlayerWhoAcceptedTrade)
				writer.println("{\"Handelsangebot angenommen\":" + gson.toJson(obj, PlayerWhoAcceptedTrade.class) + "}");
				// 10.3
			else if (obj instanceof TradeFinished)
				writer.println("{\"Handel ausgefuehrt\":" + gson.toJson(obj, TradeFinished.class) + "}");
				// 10.4
			else if (obj instanceof AbandonedTrade)
				writer.println("{\"Handelsangebot abgebrochen\":" + gson.toJson(obj, AbandonedTrade.class) + "}");
				//12.1
			else if (obj instanceof PlayKnightCard)
				writer.println("{\"Ritter ausspielen\":" + gson.toJson(obj, PlayKnightCard.class) + "}");
				//12.2
			else if (obj instanceof PlayRoadBuildingCard)
				writer.println("{\"Strassenbaukarte ausspielen\":" + gson.toJson(obj, PlayRoadBuildingCard.class) + "}");
				//12.3
			else if (obj instanceof Monopoly)
				writer.println("{\"Monopol\":" + gson.toJson(obj, Monopoly.class) + "}");
				//12.4
			else if (obj instanceof YearOfPlenty)
				writer.println("{\"Erfindung\":" + gson.toJson(obj, YearOfPlenty.class) + "}");
			else if (obj instanceof GameOver)
				writer.println("{\"Spiel beendet\":" + gson.toJson(obj, GameOver.class) + "}");
		}
	}

	/**
	 * HAndles message sent from server.
	 * @param message corresponding message
	 */
	public void handleMessage(String message) {
		Gson gson = new Gson();
		//GameStart.mainLogger.getLOGGER()().fine("[-] " + message);
		if(message == null){
			//GameStart.mainLogger.getLOGGER()().fine("incorrect message");
			return;
		}
		else if (message.contains("Hallo")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Hallo\":", "");
			InitialMessage mes = gson.fromJson(objFromMessage, InitialMessage.class);
			protocol.receiveHello(mes);
		} else if (message.contains("Chatnachricht senden")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Chatnachricht senden\":",
					"");
			SendChat chat = gson.fromJson(objFromMessage, SendChat.class);
			protocol.receiveSendChatRequest(chat);
		} else if (message.contains("Spieler")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Spieler\":", "");
			PlayerForProtocol player = gson.fromJson(objFromMessage, PlayerForProtocol.class);
			protocol.receivePlayer(player);
		} else if (message.contains("Spiel starten")) {
			protocol.receiveStartGameRequest();
		} else if (message.contains("Wuerfeln")) {
			protocol.receiveDiceThrowRequest();
		} else if (message.contains("Bauen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Bauen\":", "");
			Building building = gson.fromJson(objFromMessage, Building.class);
			protocol.receiveBuildRequest(building);
		} else if (message.contains("Zug beenden")) {
			protocol.receiveTurnEndRequest();
		} else if (message.contains("Karten abgeben")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Karten abgeben\":", "");
			ResourcesReturned resourcesReturned = gson.fromJson(objFromMessage, ResourcesReturned.class);
			protocol.receiveResourceCardsReturnedRequest(resourcesReturned);
		} else if (message.contains("Raeuber versetzen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Raeuber versetzen\":", "");
			MoveThief moveThief = gson.fromJson(objFromMessage, MoveThief.class);
			protocol.receiveMoveThiefRequest(moveThief);
		}else if (message.contains("Entwicklungskarte kaufen")) {
			protocol.receiveBuyDevelopmentCardRequest();
		}
		else if (message.contains("Seehandel")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Seehandel\":", "");
			MaritimeTrade maritimeTrade  = gson.fromJson(objFromMessage, MaritimeTrade.class);
			protocol.receiveMaritimeTrade(maritimeTrade);
		} else if (message.contains("Handel anbieten")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Handel anbieten\":", "");
			DomesticTradeOffer domesticTradeOffer  = gson.fromJson(objFromMessage, DomesticTradeOffer.class);
			protocol.receiveDomesticTrade(domesticTradeOffer);
		} else if (message.contains("Handel annehmen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Handel annehmen\":", "");
			PlayerReadyForDomesticTrade playerReadyForDomesticTrade  = gson.fromJson(objFromMessage, PlayerReadyForDomesticTrade.class);
			protocol.receiveAcceptTrade(playerReadyForDomesticTrade);
		} else if (message.contains("Handel abschliessen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Handel abschliessen\":", "");
			CompleteDomesticTrade completeDomesticTrade  = gson.fromJson(objFromMessage, CompleteDomesticTrade.class);
			protocol.receiveExecuteDomesticTrade(completeDomesticTrade);
		} else if (message.contains("Handel abbrechen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Handel abbrechen\":", "");
			AbandonDomesticTrade abandonDomesticTrade = gson.fromJson(objFromMessage, AbandonDomesticTrade.class);	
			protocol.receiveAbandonDomesticTrade(abandonDomesticTrade);
		}
		else if (message.contains("Ritter ausspielen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Ritter ausspielen\":", "");
			PlayKnightCard playKnightCard = gson.fromJson(objFromMessage, PlayKnightCard.class);	
			protocol.receivePlayKnightCardRequest(playKnightCard);
		}
		else if (message.contains("Strassenbaukarte ausspielen")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Strassenbaukarte ausspielen\":", "");
			PlayRoadBuildingCard playRoadBuildingCard = gson.fromJson(objFromMessage, PlayRoadBuildingCard.class);	
			protocol.receivePlayRoadBuildingCardRequest(playRoadBuildingCard, false);
		}
		else if (message.contains("Monopol")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Monopol\":", "");
			Monopoly monopolyCard = gson.fromJson(objFromMessage, Monopoly.class);	
			protocol.receivePlayMonopolyCardRequest(monopolyCard);
		}
		else if (message.contains("Erfindung")) {
			String objFromMessage = message.substring(0, message.length() - 1).replace("{\"Erfindung\":", "");
			YearOfPlenty yearOfPlentyCard = gson.fromJson(objFromMessage, YearOfPlenty.class);	
			protocol.receivePlayYearOfPlentyCardRequest(yearOfPlentyCard);
		}
	}
	
	/**
	 * changes status of client to active.
	 * @param active status if client is active 
	 */
	public void setClientActive(boolean active) {
		this.clientActive = active;
	}

	// Getters and Setters
	public Server getServer() {
		return server;
	}

	public void setPlayerId(Integer id) {
		this.playerId = (int) id;
	}

	public int getPlayerId() {
		return playerId;
	}

}