package com.Lab6.logger;

public class ConsoleLoggerImpl implements ILogger {
    ConsoleLoggerImpl() { }  // Not accessible from other packages !

    @Override
    public void debug(String msg) {
        System.out.println("DEBUG: " + msg);
    }

    @Override
    public void error(String msg) {
        System.err.println("ERROR: " + msg);
    }

}
