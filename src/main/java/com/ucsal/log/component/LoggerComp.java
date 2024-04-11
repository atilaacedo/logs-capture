package com.ucsal.log.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ucsal.log.interfaces.ILogger;

public class LoggerComp implements ILogger{
	
	private Logger logger;
	
	public LoggerComp(Class<?> clazz) {
		this.logger = LogManager.getLogger(clazz);
	}
	
	@Override
	public void debug(String message) {
		this.logger.debug(message);
	}

	@Override
	public void info(String message) {
		this.logger.info(message);
	}

	@Override
	public void warn(String message) {
		this.logger.warn(message);
		
	}

	@Override
	public void error(String message, Throwable throwable) {
		this.logger.error(message, throwable);
		
	}

}
