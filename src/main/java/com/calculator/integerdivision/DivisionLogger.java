package com.calculator.integerdivision;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DivisionLogger {

    private static final String LOGGER_NAME = "com.calculator.integerdivision";
    private static final String LOG_FILE_PATH = "division.log";

    private Logger logger;

    /**
     * @param logFilePath relative or absolute path to the log file
     * */
    public DivisionLogger(String logFilePath) {
        try {
            FileHandler handler = new FileHandler(getAbsolutePath(logFilePath));
            handler.setEncoding(StandardCharsets.UTF_8.toString());
            handler.setFormatter(new SimpleFormatter());

            logger = Logger.getLogger(LOGGER_NAME);
            logger.setLevel(Level.ALL);
            logger.addHandler(handler);
        } catch (IOException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public DivisionLogger() {
        this(LOG_FILE_PATH);
    }

    public void log(String message) {
        logger.log(Level.ALL, message);
    }

    private String getAbsolutePath(String relativePath) {
        return FileSystems.getDefault()
                .getPath(relativePath)
                .normalize()
                .toAbsolutePath()
                .toString();
    }

}
