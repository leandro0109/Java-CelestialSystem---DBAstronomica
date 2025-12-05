package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GalaxiaService {
	// Variáveis
	private final Connection conn;
		 
	// Construtor
	public GalaxiaService(Connection conn) {
		this.conn = conn;
		}
	
	// Métodos 
	public boolean galaxiaExiste(String nomeEstrela) throws SQLException {
	    String sql = "SELECT 1 FROM TblGalaxia WHERE nomeEstrela = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nomeEstrela);
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next(); // retorna true se encontrar estrela com o nome inserido
	        }
	    }
	}
	
	public void criarGalaxia(String nomeGalaxia, String massa, String idade, String quantidadeEstrelas, String diametroAprox) throws SQLException {
		String sql = "INSERT INTO TblGalaxia(nomeGalaxia, massa, idade, quantidadeEstrelas, diametroEstimado) VALUES (?,?,?,?,?)";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, nomeGalaxia);
			stmt.setString(2, massa);
			stmt.setString(3, idade);
			stmt.setString(4, quantidadeEstrelas);
			stmt.setString(5, diametroAprox);
			
			stmt.executeUpdate();
			System.out.println("Galáxia criada com sucesso!");
		}
	}
	
	public void listarGalaxias() throws SQLException {
		String sql = "SELECT * FROM TblGalaxia ORDER BY NomeGalaxia ASC";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
		{
			while(rs.next())
			{
				String nomeGalaxia = rs.getString("NomeGalaxia");
				String massa = rs.getString("Massa");
				String idade = rs.getString("Idade");
				String diametroAprox = rs.getString("DiametroEstimado");
				
				System.out.println("Galáxia: " + nomeGalaxia + ", Massa: " + massa + ", Idade: " + idade + " anos" + ", DiametroEst: " + diametroAprox);
			}
		}
	}
	
	public void excluirGalaxia(String nomeGalaxia) throws SQLException {
		String sql = "DELETE from TblGalaxia WHERE nomeGalaxia=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, nomeGalaxia);
			stmt.executeUpdate();
			System.out.println("Galáxia excluida com sucesso!");
		}
	}
	
	public void atualizarGalaxia(int idGalaxia, String nomeGalaxia, String massa, String idade, String quantidadeEstrelas, String diametroAprox) throws SQLException {
		String sql = "UPDATE TblGalaxia SET nomeGalaxia=?, massa=?, idade=?, quantidadeEstrelas=?, diametroEstimado=? WHERE idGalaxia=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, nomeGalaxia);
			stmt.setString(2, massa);
			stmt.setString(3, idade);
			stmt.setString(4, quantidadeEstrelas);
			stmt.setString(5, diametroAprox);
			stmt.setInt(6, idGalaxia);
			
			stmt.executeUpdate();
			System.out.println("Galáxia atualizada com sucesso!");
		}
	}
	
	public void visualizarGalaxia(String galaxiaNome) throws SQLException {
		String sql = "SELECT * FROM TblGalaxia WHERE nomeGalaxia=?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, galaxiaNome);
			try(ResultSet rs = stmt.executeQuery())
			{
				while(rs.next())
				{
					int IDGalaxia = rs.getInt("IDGalaxia");
	        		String nomeGalaxia = rs.getString("NomeGalaxia");
	        		String massa = rs.getString("Massa");
	        		String idade = rs.getString("Idade");
	        		String quantEstrelas = rs.getString("QuantidadeEstrelas");
	        		String diametroAprox = rs.getString("DiametroEstimado");
	        			
	        		System.out.println("ID: " + IDGalaxia + ", Nome: " + nomeGalaxia + ", Massa: " + massa + 
	        				", Idade: " + idade + ", Quantidade Estrelas: " + quantEstrelas + ", Diametro Estimado: " + diametroAprox);
				}
			}
		}
	} 
	
	public void contarGalaxias() throws SQLException {
	    String sql = "SELECT COUNT(*) AS quantidade FROM TblGalaxia";
	    try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            int total = rs.getInt("quantidade");
	            System.out.println("Total de galáxias: " + total);
	        }
	    }
	}	
	
}
