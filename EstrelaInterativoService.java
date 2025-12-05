package service;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EstrelaInterativoService {
	// Variáveis
	private final EstrelaService estrelaService;
	
	// Construtor
	public EstrelaInterativoService(EstrelaService estrelaService) {
		this.estrelaService = estrelaService;
	}
	
	// Métodos com frame
	public void criarEstrelaInterativo(JFrame frame) {
	        try {
	        	Integer idGalaxia = lerInteiroObrigatorio("ID da Galáxia:", frame);
	        	if (idGalaxia == null) return; // Cancelou ou entrada inválida

	        	Integer idClassificacao = lerInteiroObrigatorio("ID da Classificação da Estrela:", frame);
	        	if (idClassificacao == null) return;

	        	String nome = lerTextoObrigatorio(frame, "Nome da Estrela:");
	        	if (nome == null) return;

	        	String idade = lerTextoObrigatorio(frame, "Idade:");
	        	if (idade == null) return;

	        	Integer temperatura = lerInteiroObrigatorio("Temperatura Média (°C):", frame);
	        	if (temperatura == null) return;

	        	String massa = lerTextoObrigatorio(frame, "Massa:");
	        	if (massa == null) return;
	            
	            estrelaService.criarEstrela(idGalaxia, idClassificacao, nome, idade, temperatura, massa);  
	            JOptionPane.showMessageDialog(frame, "Estrela criada com sucesso!");
	           
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(frame, "Erro ao criar estrela: " + ex.getMessage());
	        }
	}
	
	public void excluirEstrelaInterativo(JFrame frame) {
			try {
				String nomeEstrela = lerTextoObrigatorio(frame, "Nome da Estrela:");
				if(nomeEstrela == null) return;
					
				estrelaService.excluirEstrela(nomeEstrela);	
				JOptionPane.showMessageDialog(frame, "Estrela excluida com sucesso!");
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(frame, "Erro ao excluir estrela: " + ex.getMessage());
			}	
	}	
	
	public void listarEstrelasInterativo(JFrame frame) {
	    try {
	    	String todasAsEstrelas = estrelaService.listarEstrelas(); 
	    	
		    JTextArea textArea = new JTextArea(todasAsEstrelas);
		    textArea.setEditable(false);
		    textArea.setLineWrap(false); // permite rolagem horizontal
		    textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

		    JScrollPane scrollPane = new JScrollPane(textArea);
		    scrollPane.setPreferredSize(new Dimension(1000, 500));
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		    // Exibe a janela
		    JOptionPane.showMessageDialog(
		        frame,
		        scrollPane,
		        "Lista de Estrelas",
		        JOptionPane.INFORMATION_MESSAGE
		    );
	    } catch (SQLException ex) {
	    	JOptionPane.showMessageDialog(frame, "Erro ao listar estrelas: " + ex.getMessage());
	    }  
	}
	
	// Métodos de verificação
	private String lerTextoObrigatorio(JFrame frame, String mensagem) {
		String entrada = JOptionPane.showInputDialog(frame, mensagem);
	    if (entrada == null) {
	        JOptionPane.showMessageDialog(frame, "Operação cancelada pelo usuário.");
	        return null;
	    }
	    if (entrada.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "Campo obrigatório não pode ficar vazio.");
	        return null;
	    }
	    return entrada;
	}
	
	private Integer lerInteiroObrigatorio(String mensagem, JFrame frame) {
	    String entrada = lerTextoObrigatorio(frame, mensagem);
	    if (entrada == null) return null;  // cancelado ou vazio
	    try {
	        return Integer.parseInt(entrada);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(frame, "Por favor, insira um número válido.");
	        return null;
	    }
	}
}


