package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.*;

public class MyLogger {
	// Constantes
	private static final String LOG_FILE="user_access_log.txt";
	private static final String FAILED_LOG_FILE="user_deny_log.txt";
	private static final String ERROR_LOG_FILE="error_log.txt";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	
	// Métodos
	public static void logAcesso(String user) {
		String timestamp = LocalDateTime.now().format(DATE_FORMAT);
		String entradaLog = String.format("%s - Utilizador: %s acedeu", timestamp, user);
		try(PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
			writer.println(entradaLog);
		}catch(IOException e) {
			System.out.println("Erro a registrar acesso: " + e.getMessage());
		}
	}
	
	public static void logFalha(String mensagem) {
		String timestamp = LocalDateTime.now().format(DATE_FORMAT);
		String entradaLog = String.format("Erro às %s: %s", timestamp, mensagem);
		try(PrintWriter writer = new PrintWriter(new FileWriter(ERROR_LOG_FILE, true))) {
			writer.println(entradaLog);
		}catch(IOException e) {
			System.out.println("Erro a registrar falha: " + e.getMessage());
		}
	}
	
	public static void logFalhaAcesso(String user, String clienteIP, String host) {
		String timestamp = LocalDateTime.now().format(DATE_FORMAT);
		String entradaLog = String.format("Acesso falhado %s, Utilizador %s, IP %s, Host %s", timestamp, user, clienteIP, host);
		try(PrintWriter writer = new PrintWriter(new FileWriter(FAILED_LOG_FILE, true))) {
			writer.println(entradaLog);
		}catch(IOException e) {
			System.out.println("Erro a registrar falha de acesso: " + e.getMessage());
		}
	}
}
