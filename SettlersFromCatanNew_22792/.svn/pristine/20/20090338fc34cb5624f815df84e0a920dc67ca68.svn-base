package testing;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import networking.Network;

/**
 * NetWork window is opened before establishing connection to the server, allowing the player to enter IP and Port they want to connect to.
 *
 */
public class TestNetworkWindow {
	private Network network;
    private Stage stage;
    private Scene scene;
    private HBox hBox;
    private TextField ipTextField;
    private TextField portTextField;
    private Button connectButton;
    
    /**
     * Used to test sending messaged over the network
     */
    public TestNetworkWindow(Object objectToSend){

    	stage = new Stage();
    	Pane root = new Pane();
    	
    	ipTextField = new TextField("127.0.0.1");
    	portTextField = new TextField("8080");
    	connectButton = new Button ("CONNECT");
    	connectButton.setOnAction(e-> connectToServer(ipTextField.getText(), Integer.parseInt(portTextField.getText())));

    	Button testButton = new Button("TEST");
    	//testButton.setOnAction(e-> testMessageSending("WE MADE IT"));
    	////testButton.setOnAction(e-> network.disconnectFromServer());
    	testButton.setOnAction(e-> network.getConnectionHandler().sendObject(objectToSend));
    	
    	hBox = new HBox();
    	hBox.getChildren().addAll(ipTextField, portTextField, connectButton);
    	hBox.getChildren().add(testButton);
    	root.getChildren().add(hBox);
    	
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

	public void connectToServer(String ip, int port) {

        ipTextField.clear();
        portTextField.clear();
    	this.network = new Network(ip, port);

	}
	public void testMessageSending(String message){
		network.sendMessage(message);
	}

}
