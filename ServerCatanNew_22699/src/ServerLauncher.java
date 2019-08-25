import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class used to launch the server. 
 * @author Marcelina
 */
public class ServerLauncher extends Application{
	/**
	 * main class will only call launch for the application
	 * @param args
	 */
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ServerLauncher.initializeServer(primaryStage);

    }
    /**
     * Method used for the initialization (called in start).  Creates a server object, starts it (thread) and creates
     * a window for the server - used to print status updates of the server
     * @param primaryStage
     */
    public static void initializeServer(Stage primaryStage){
        Server server = new Server();
        server.start();
        ServerWindow serverWindow = new ServerWindow(server, primaryStage);
        server.setWindow(serverWindow);
    }
}