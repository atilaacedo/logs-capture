package com.ucsal.log;

import java.util.Random;
import java.util.Scanner;

import com.ucsal.log.annotations.InjectLogger;
import com.ucsal.log.factories.LoggerFactory;
import com.ucsal.log.interfaces.ILogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogginComponentApplication {
	
	@InjectLogger
	private static ILogger loggerI;
	private static Scanner scanner = new Scanner(System.in);
	 
	public static void main(String[] args) {
		LoggerFactory.injectLogger(new LogginComponentApplication()); 

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



