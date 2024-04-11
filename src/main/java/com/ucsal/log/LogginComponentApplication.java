package com.ucsal.log;

import java.io.File;
import java.util.Scanner;

import com.ucsal.log.annotations.InjectLogger;
import com.ucsal.log.factories.LoggerFactory;
import com.ucsal.log.interfaces.ILogger;

public class LogginComponentApplication {
	@InjectLogger
	private static ILogger loggerI;
	
	 
	public static void main(String[] args) {
		LoggerFactory.injectLogger(new LogginComponentApplication()); 

        loggerI.info("Aplicação iniciada.");

        try {
        	File arquivo = new File("dados.txt");
            Scanner scanner = new Scanner(arquivo);
        } catch (Exception e) {
            loggerI.error("Erro durante a operação", e);
        }

        loggerI.info("Aplicação finalizada.");
	}

}
