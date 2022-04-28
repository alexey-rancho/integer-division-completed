package com.calculator.integerdivision.logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DivisionLogger {

    private static final String LOGGER_NAME = "com.calculator.integerdivision";
    private static final String LOG_FILE_PATH = "division.log";

    private final Logger logger;
    private final String logFilePath;

    /**
     * @param logFilePath relative or absolute path to the log file. By default,
     *                    it's set to "division.log"
     * @param cleanFile   if true, the log file is cleaned before each
     *                    creating DivisionLogger instance. Otherwise, file
     *                    remains the same and log method appends passed message
     *                    into the log file.
     * @throws IOException              if there are IO problems opening the files
     * @throws SecurityException        if a security manager exists and if the caller
     *                                  does not have LoggingPermission("control")
     * @throws IllegalArgumentException if logFilePath is an empty string
     */
    public DivisionLogger(String logFilePath, boolean cleanFile)
            throws IOException, SecurityException, IllegalArgumentException {
        this.logFilePath = logFilePath;

        FileHandler handler = new FileHandler(logFilePath, !cleanFile);
        handler.setEncoding(StandardCharsets.UTF_8.toString());
        handler.setFormatter(new SimpleFormatter());

        logger = Logger.getLogger(LOGGER_NAME);
        logger.setLevel(Level.ALL);
        logger.addHandler(handler);
    }

    /**
     * @param cleanFile if true, the log file is cleaned before each
     *                  creating DivisionLogger instance. Otherwise, file
     *                  remains the same and log method appends passed message
     *                  into the log file.
     * @throws IOException              if there are IO problems opening the files
     * @throws SecurityException        if a security manager exists and if the caller
     *                                  does not have LoggingPermission("control")
     * @throws IllegalArgumentException if logFilePath is an empty string
     */
    public DivisionLogger(boolean cleanFile) throws IOException, SecurityException, IllegalArgumentException {
        this(LOG_FILE_PATH, cleanFile);
    }

    /**
     * @throws IOException              if there are IO problems opening the files
     * @throws SecurityException        if a security manager exists and if the caller
     *                                  does not have LoggingPermission("control")
     * @throws IllegalArgumentException if logFilePath is an empty string
     */
    public DivisionLogger() throws IOException, SecurityException, IllegalArgumentException {
        this(true);
    }

    /**
     * @return relative or absolute path to the log file. By default,
     * it's set to "division.log"
     */
    public String getLogFilePath() {
        return logFilePath;
    }

    public void log(String message) {
        logger.log(Level.ALL, message);
    }

}
