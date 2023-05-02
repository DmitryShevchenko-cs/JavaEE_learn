package com.Lab6.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoggerImpl implements ILogger{
    private static final String DEBUG_FILE_PATH = "DebugLog.log";
    private static final String ERROR_FILE_PATH = "ErrorLog.log";

    public void debug(String msg)
    {
        try
        {
            if (! Files.exists(Paths.get(DEBUG_FILE_PATH))) {
                Files.createFile(Paths.get(DEBUG_FILE_PATH));
            }

            Files.write(Paths.get(DEBUG_FILE_PATH), ("DEBUG:" + msg).getBytes());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void error(String msg)
    {
        try
        {
            if (! Files.exists(Paths.get(ERROR_FILE_PATH))) {
                Files.createFile(Paths.get(ERROR_FILE_PATH));
            }

            Files.write(Paths.get(ERROR_FILE_PATH), ("ERROR:" + msg).getBytes());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
