package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstrelaService {
	// Variáveis
	private final Connection conn;
	 
	// Construtor
	public EstrelaService(Connection conn) {
		this.conn = conn;
		}
		
	// Métodos
	public boolean estrelaExiste(String nomeEstrela) throws SQLException {
	    String sql = "SELECT 1 FROM TblEstrela WHERE nomeEstrela = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nomeEstrela);
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next(); // retorna true se encontrar estrela com o nome inserido
	        }
	    }
	}

	public void criarEstrela(int IDGalaxia, int IDClassificacaoEstrela, String nomeEstrela, String idade, int temperaturaMediaCelsius, String massa) throws SQLException{
		String sql = "INSERT INTO TblEstrela(IDGalaxia, IDClassificacaoEstrela, nomeEstrela, idade, temperaturaMediaCelsius, massa) VALUES (?, ?, ?, ?, ?, ?)";
		
		 try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, IDGalaxia);
	            stmt.setInt(2, IDClassificacaoEstrela);
	            stmt.setString(3, nomeEstrela);
	            stmt.setString(4, idade);
	            stmt.setInt(5, temperaturaMediaCelsius);
	            stmt.setString(6, massa);

	            stmt.executeUpdate();
	        } 
	}
		 
	public String listarEstrelas() throws SQLException{
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT e.IDEstrela, c.Classificacao, g.nomeGalaxia, e.nomeEstrela, e.idade, e.temperaturaMediaCelsius, e.massa " 
				+ "FROM TblEstrela e "
				+ "JOIN TblClassificacaoEstrela c ON e.IDClassificacaoEstrela = c.IDClassificacaoEstrela "
				+ "JOIN TblGalaxia g ON e.IDGalaxia = g.IDGalaxia "
				+ "ORDER BY e.IDEstrela ASC";
			
		try(PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
		{
			while(rs.next())
        	{
        		int IDEstrela = rs.getInt("IDEstrela");
        		String galaxia = rs.getString("NomeGalaxia");
        		String classificacaoEstrela = rs.getString("Classificacao");
        		String nomeEstrela = rs.getString("NomeEstrela");
        		String idade = rs.getString("Idade");
        		int temperaturaMediaCelsius = rs.getInt("TemperaturaMediaCelsius");
        		String massa = rs.getString("massa");
        			
        		sb.append("IDEstrela: ").append(IDEstrela)
                .append(", Galáxia: ").append(galaxia)
                .append(", Classificação: ").append(classificacaoEstrela)
                .append(", Nome: ").append(nomeEstrela)
                .append(", Idade: ").append(idade)
                .append(", Temperatura: ").append(temperaturaMediaCelsius)
                .append(" °C, Massa: ").append(massa)
                .append("\n");
        	}
			return sb.length() > 0 ? sb.toString() : "Nenhuma estrela cadastrada.";
        }
	}
	
	public void atualizarEstrela(int idEstrela, int idGalaxia, int idClassificacaoEstrela, String nomeEstrela, String idade, int temperaturaMediaCelsius, String massa) throws SQLException {
		String sql = "UPDATE TblEstrela SET IDGalaxia=?, IDClassificacaoEstrela=?, NomeEstrela=?, Idade=?, "
				+ "TemperaturaMediaCelsius=?, Massa=? WHERE IDEstrela=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setInt(1, idGalaxia);
			stmt.setInt(2, idClassificacaoEstrela);
			stmt.setString(3, nomeEstrela);
			stmt.setString(4, idade);
			stmt.setInt(5, temperaturaMediaCelsius);
			stmt.setString(6, massa);
			stmt.setInt(7, idEstrela);
			
			stmt.executeUpdate();
			System.out.println("Estrela atualizada com sucesso!");
		}
	}
	
	public void excluirEstrela(String nomeEstrela) throws SQLException {
		String sql = "DELETE from TblEstrela WHERE nomeEstrela=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, nomeEstrela);
			stmt.executeUpdate();
		}
	}
	
	public void listarEstrelasGalaxia(String nomeGalaxia) throws SQLException {
		String sql = "SELECT c.Classificacao, e.nomeEstrela, e.idade, e.temperaturaMediaCelsius, e.massa " 
				+ "FROM TblEstrela e "
				+ "JOIN TblClassificacaoEstrela c ON e.IDClassificacaoEstrela = c.IDClassificacaoEstrela "
				+ "JOIN TblGalaxia g ON e.IDGalaxia = g.IDGalaxia "
				+ "WHERE g.nomeGalaxia=?" 
				+ "ORDER BY e.nomeEstrela ASC";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, nomeGalaxia);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
	        		String classificacaoEstrela = rs.getString("Classificacao");
	        		String nomeEstrela = rs.getString("NomeEstrela");
	        		String idade = rs.getString("Idade");
	        		int temperaturaMediaCelsius = rs.getInt("TemperaturaMediaCelsius");
	        		String massa = rs.getString("massa");
	        			
	        		System.out.println("Nome: " + nomeEstrela + ", Classificação: " + classificacaoEstrela + ", Idade: " + idade + ", Temperatura Media Celsius: " + temperaturaMediaCelsius
	        				+ ", Massa: " + massa);
				}
			}
		}
	}
	
	public void visualizarEstrela(String estrelaNome) throws SQLException {
		String sql = "SELECT e.IDEstrela, c.Classificacao, g.nomeGalaxia, e.nomeEstrela, e.idade, e.temperaturaMediaCelsius, e.massa " 
				+ "FROM TblEstrela e "
				+ "JOIN TblClassificacaoEstrela c ON e.IDClassificacaoEstrela = c.IDClassificacaoEstrela "
				+ "JOIN TblGalaxia g ON e.IDGalaxia = g.IDGalaxia "
				+ "WHERE e.nomeEstrela=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, estrelaNome);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int IDEstrela = rs.getInt("IDEstrela");
	        		String galaxia = rs.getString("NomeGalaxia");
	        		String classificacaoEstrela = rs.getString("Classificacao");
	        		String nomeEstrela = rs.getString("NomeEstrela");
	        		String idade = rs.getString("Idade");
	        		int temperaturaMediaCelsius = rs.getInt("TemperaturaMediaCelsius");
	        		String massa = rs.getString("massa");
	        			
	        		System.out.println("ID: " + IDEstrela + ", Galaxia: " + galaxia + ", classificacao: " + classificacaoEstrela + 
	        				", nome: " + nomeEstrela + ", idade: " + idade + ", temperatura media celsius: " + temperaturaMediaCelsius
	        				+ ", massa: " + massa);
				}
			}
		}
	}
	
	public void listarEstrelasAltaTemperatura(int tempMinima) throws SQLException {
		String sql = "SELECT e.IDEstrela, c.Classificacao, g.nomeGalaxia, e.nomeEstrela, e.idade, e.temperaturaMediaCelsius, e.massa " 
				+ "FROM TblEstrela e "
				+ "JOIN TblClassificacaoEstrela c ON e.IDClassificacaoEstrela = c.IDClassificacaoEstrela "
				+ "JOIN TblGalaxia g ON e.IDGalaxia = g.IDGalaxia "
				+ "WHERE e.temperaturaMediaCelsius >= ?"
				+ "ORDER BY e.temperaturaMediaCelsius DESC";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setInt(1, tempMinima);
			try(ResultSet rs = stmt.executeQuery())
			{
				while (rs.next()) {
	                System.out.println("Estrela quente: " + rs.getString("nomeEstrela") + " - " + rs.getInt("temperaturaMediaCelsius") + "°C");
				}
			}
		}
	}
	
	public void contarEstrelasPorClassificacao() throws SQLException
	{
		String sql = "SELECT c.Classificacao, COUNT(*) AS quantidade FROM TblEstrela e " 
                +	"JOIN TblClassificacaoEstrela c ON e.IDClassificacaoEstrela = c.IDClassificacaoEstrela " 
                +	"GROUP BY c.Classificacao "
                +	"ORDER BY quantidade DESC";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
		{
			while (rs.next()) {
	            System.out.println(rs.getString("Classificacao") + ": " + rs.getInt("quantidade") + " estrela(s)");
			}
		}
	}
	
	public void contarEstrelasPorGalaxia() throws SQLException
	{
		String sql = "SELECT g.nomeGalaxia, COUNT(*) AS quantidade FROM TblEstrela e " 
				+	"JOIN TblGalaxia g ON e.IDGalaxia = g.IDGalaxia " 
				+	"GROUP BY g.nomeGalaxia "
                +	"ORDER BY quantidade DESC";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
		{
			while (rs.next()) {
	            System.out.println(rs.getString("nomeGalaxia") + ": " + rs.getInt("quantidade") + " estrela(s)");
			}
		}
	}
}


