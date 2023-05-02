package com.Lab6.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoggerFactory {
    public static String LOGGER_PROPERTIES_FILE = "/WEB-INF/classes/logger.properties";
    public ILogger getLogger()
    {
        return isFileLoggingEnabled()
                ? new FileLoggerImpl()
                : new ConsoleLoggerImpl();
    }

    // helper method, check if FileLogging is ON and if so,
    // logs messages to a file else print it to console.
    private boolean isFileLoggingEnabled()
    {
        try
        {
            Properties p = new Properties();
            p.load(new FileInputStream(LOGGER_PROPERTIES_FILE));
            return "true".equalsIgnoreCase(p.getProperty("FileLogging"));
        }
        catch (IOException e) {
            return false;
        }
    }

}
