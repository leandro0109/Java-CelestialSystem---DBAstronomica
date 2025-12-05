package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LigaBaseDados implements AutoCloseable{
	
	private final Connection connection;
	private final static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DBAstronomica;user=sa;password=1234;encrypt=true;trustServerCertificate=true";;
	
	public LigaBaseDados() throws SQLException{
		this.connection = DriverManager.getConnection(URL);
		System.out.println("Ligação estabelecida com sucesso.");
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	@Override
	public void close() {
		try {
			if(connection != null && !connection.isClosed())
			{
				connection.close();
				System.out.println("Ligação fechada.");
			}
		}catch(SQLException e)
			{
				System.err.println("Erro ao fechar a ligação: " + e.getMessage());
			}
		}
	}
