package com.ucsal.log.interfaces;

public interface ILogger {
    void info(String message);
    void error(String message, Throwable throwable);
}