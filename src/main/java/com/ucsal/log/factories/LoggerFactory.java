package com.ucsal.log.factories;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.ucsal.log.annotations.InjectLogger;
import com.ucsal.log.component.LoggerComp;
import com.ucsal.log.interfaces.ILogger;

public class LoggerFactory {
	private static final Map<Class<?>, ILogger> loggers = new HashMap<>();

	  public static void injectLogger(Object object) {
	        Class<?> clazz = object.getClass();
	        for (Field field : clazz.getDeclaredFields()) {
	            if (field.isAnnotationPresent(InjectLogger.class)) {
	                field.setAccessible(true);
	                try {
	                    ILogger logger = loggers.computeIfAbsent(clazz, LoggerComp::new);
	                    if (Modifier.isStatic(field.getModifiers())) {
	                        field.set(null, logger);
	                    } else {
	                        field.set(object, logger);
	                    }
	                } catch (IllegalAccessException e) {
	                    throw new RuntimeException("Erro ao injetar logger", e);
	                }
	            }
	        }
	    }
}
