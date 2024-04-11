package com.ucsal.log.interfaces;

public interface ILogger {
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message, Throwable throwable);
}