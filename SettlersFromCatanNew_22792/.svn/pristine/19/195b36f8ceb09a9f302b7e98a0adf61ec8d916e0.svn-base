package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class used for logging or debugging.
 */
public class LoggingHolder {

    private final Logger LOGGER;
    private final Level LOGLEVEL;
    private final String FILENAME;
    private FileHandler txtFile;

    public LoggingHolder(String filename, Level logLevel) {
        FILENAME = filename + ".txt";
        LOGLEVEL = logLevel;
        LOGGER = Logger.getLogger(filename);
        LOGGER.setLevel(logLevel);

        // create a TXT formatter
        try {
            txtFile = new FileHandler("Logging.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtFile.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(txtFile);
    }

    //Getters
    public Logger getLOGGER() {
        return LOGGER;
    }

    public Level getLOGLEVEL() {
        return LOGLEVEL;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public FileHandler getTxtFile() {
        return txtFile;
    }
}
