package com.ucsal.log;

import java.util.Random;
import java.util.Scanner;

import com.ucsal.log.annotations.InjectLogger;
import com.ucsal.log.factories.LoggerFactory;
import com.ucsal.log.interfaces.ILogger;
import com.ucsal.log.utils.enums.TipoSaida;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogginComponentApplication {
	
	@InjectLogger
	private static ILogger loggerI;
	private static Scanner scanner = new Scanner(System.in);
	private static boolean inicio = true; 
	private static TipoSaida tipoSaida;
	
	public static void main(String[] args) {
		while(inicio) {
			System.out.println("\nEscolha como quer seus logs:");
            System.out.println("1 - Arquivo");
            System.out.println("2 - Console");
            
            int valor = scanner.nextInt();
            switch (valor) {
				case 1: {
					tipoSaida = TipoSaida.ARQUIVO; 
					inicio = false;
					break;
				}
				case 2: {
					tipoSaida = TipoSaida.CONSOLE;
					inicio = false;
					break;
				}
				default:
					break;
			}
		}
		
		LoggerFactory.injectLogger(new LogginComponentApplication(), tipoSaida); 
		
        loggerI.info("Aplicação iniciada.");
        
        ExecutorService executor = Executors.newCachedThreadPool();
        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Sair");

            int opcao = scanner.nextInt();

            if (opcao == 3) {
                break;
            }

            System.out.println("Digite o valor:");
            int valor = scanner.nextInt();

            executor.submit(new TransacaoBancaria(opcao, valor));
        }
        executor.shutdown();
        scanner.close();

        loggerI.info("Aplicação finalizada.");
	}
	
	static class TransacaoBancaria implements Runnable{
        private int opcao;
        private int valor;
        private Random random = new Random();

        public TransacaoBancaria(int opcao, int valor) {
            this.opcao = opcao;
            this.valor = valor;
        }
        
		@Override
		public void run() {
			try {
				
	            Thread.sleep(random.nextInt(2000));
	            if (opcao == 1) {
	                loggerI.info("Depositando R$" + valor);
	            } else if (opcao == 2) {
	                loggerI.info("Sacando R$" + valor);
	            }
	        } catch (InterruptedException e) {
	            loggerI.error("Erro na transação", e);
	        }
		}

	}
}



