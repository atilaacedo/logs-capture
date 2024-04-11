package com.ucsal.log.component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



import com.ucsal.log.interfaces.ILogger;
import com.ucsal.log.utils.enums.TipoSaida;

public class LoggerComp implements ILogger{
	
	private static final String ARQUIVO_LOG = "log.txt";
	
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private Class<?> classLog;
    
    private TipoSaida tipoSaida;
    
    
    public LoggerComp(Class<?> clazz, TipoSaida tipoSaida) {
		this.classLog = clazz;
		this.tipoSaida = tipoSaida;
	}
	
    public void info(String mensagem) {
        this.registrarMensagem("INFO", mensagem);
    }

    public void error(String mensagem, Throwable throwlable) {
        this.registrarMensagem("ERRO", mensagem, throwlable.getMessage());
    }

    private void registrarMensagem(String nivel, String mensagem) {
    	LocalDateTime agora = LocalDateTime.now();
    	String mensagemLog = FORMATO_DATA.format(agora) + " [" + this.classLog.getSimpleName() +"] " + " [" + nivel + "] " + mensagem;
    	if(this.tipoSaida == TipoSaida.CONSOLE) {
    		System.out.println(mensagemLog);
    	}else {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_LOG, true))) {
	            writer.println(mensagemLog);
	        } catch (IOException e) {
	            System.err.println("Erro ao registrar mensagem de log: " + e.getMessage());
	        }
    	}
    }
    
    private void registrarMensagem(String nivel, String mensagem, String erro) {
    	LocalDateTime agora = LocalDateTime.now();
    	String mensagemLog = FORMATO_DATA.format(agora) + " [" + this.classLog.getSimpleName() +"] "+" [" + nivel + "] " + mensagem + '\n' + erro;
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_LOG, true))) {
            writer.println(mensagemLog);
        } catch (IOException e) {
            System.err.println("Erro ao registrar mensagem de log: " + e.getMessage());
        }
    }
}
