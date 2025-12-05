package UI;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import service.EstrelaService;

public class EstrelaMenu {

	// Variáveis
	private final EstrelaService estrelaService;
	private Scanner scanner;
 
	// Construtor 
	public EstrelaMenu(EstrelaService estrelaService) {
		this.estrelaService = estrelaService;
		this.scanner = new Scanner(System.in);
	}
	
	// Menu de Estrelas
	public void mostrarEstrelasMenu() {
		while (true) {
			System.out.println("\n🌟 MENU DE ESTRELAS 💫");
			System.out.println("1 - Criar estrela");
			System.out.println("2 - Listar todas estrelas");
			System.out.println("3 - Atualizar Estrela");
			System.out.println("4 - Excluir Estrela");
			System.out.println("5 - Listar estrelas de uma galáxia");
			System.out.println("6 - Visualizar Estrela");
			System.out.println("7 - Listar Estrelas Alta Temperatura");
			System.out.println("8 - Contar Estrelas Por Classificação");
			System.out.println("9 - Contar Estrelas Por Galáxia");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");
 
			int opcao = 0;
			try {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Digite uma opção válida.");
				scanner.nextLine();
				continue;
			}
 
			switch (opcao) {
			case 1:
				criarEstrela();
				break;
			case 2:
				listarEstrelas();
				break;
			case 3:
				atualizarEstrela();
				break;
			case 4:
				excluirEstrela();
				break;
			case 5:
				listarEstrelasGalaxia();
				break;
			case 6:
				visualizarEstrela();
				break;
			case 7:
				listarEstrelasAltaTemperatura();
				break;
			case 8:
				contarEstrelasPorClassificacao();
				break;
			case 9:
				contarEstrelasPorGalaxia();
				break;
			case 0:
				return;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	// Métodos 
	public void criarEstrela() {
		try
		{
			System.out.println("ID galáxia que pertence a estrela: ");
			int idGalaxia = scanner.nextInt();
			System.out.println("ID classificação da estrela: ");
			int idclassificacao = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Nome da estrela: ");
			String nomeEstrela = scanner.nextLine();
			System.out.println("Idade da estrela: ");
			String idadeEstrela = scanner.nextLine();
			System.out.println("Temperatura média em celsius: ");
			int temperaturaMediaCelsius = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Massa da estrela: ");
			String massa = scanner.nextLine();
			if (!estrelaService.estrelaExiste(nomeEstrela)) {
				estrelaService.criarEstrela(idGalaxia, idclassificacao, nomeEstrela, idadeEstrela, temperaturaMediaCelsius, massa);
				System.out.println("Estrela criada com sucesso!");
			}	
			else
				System.out.println("Estrela com o nome inserido já existe no sistema.");
		} 
		catch (SQLException e)
		{
			System.out.println("Erro ao criar a estrela: " + e.getMessage());
		}
	}
	
	public void listarEstrelas() {
		try 
		{
			String todasAsEstrelas = estrelaService.listarEstrelas();
			System.out.println(todasAsEstrelas);
		}
		catch (SQLException e)
		{
			System.out.println("Erro ao listar estrelas: " + e.getMessage());
		}
	}
	
	public void atualizarEstrela() {
		try 
		{
			System.out.println("ID da estrela a ser atualizada: ");
			int idEstrela = scanner.nextInt();
			System.out.println("ID galáxia que pertence a estrela: ");
			int idGalaxia = scanner.nextInt();
			System.out.println("ID classificação da estrela: ");
			int idclassificacao = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Nome da estrela: ");
			String nomeEstrela = scanner.nextLine();
			System.out.println("Idade da estrela: ");
			String idadeEstrela = scanner.nextLine();
			System.out.println("Temperatura média em celsius: ");
			int temperaturaMediaCelsius = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Massa da estrela: ");
			String massa = scanner.nextLine();
			estrelaService.atualizarEstrela(idEstrela, idGalaxia, idclassificacao, nomeEstrela, idadeEstrela, temperaturaMediaCelsius, massa);
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao atualizar estrela: " + e.getMessage());
		}
	}
	
	public void excluirEstrela() {
		try 
		{
			System.out.println("Insira o nome da estrela a ser excluida: ");
			String nomeEstrela = scanner.nextLine();
			estrelaService.excluirEstrela(nomeEstrela);
			System.out.println("Estrela excluida com sucesso!");
		}
		catch (SQLException e)
		{
			System.out.println("Erro ao excluir estrela: " + e.getMessage());
		}
	}
	
	public void listarEstrelasGalaxia() {
		try 
		{
			System.out.println("Insira nome da galáxia da qual deseja visualizar as estrelas: ");
			String nomeGalaxia = scanner.nextLine();
			estrelaService.listarEstrelasGalaxia(nomeGalaxia);
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar estrelas da galáxia: " + e.getMessage());
		}
	}
	
	public void visualizarEstrela() {
		try 
		{
			System.out.println("Insira nome da Estrela a ser visualizada: ");
			String nomeEstrela = scanner.nextLine();
			estrelaService.listarEstrelasGalaxia(nomeEstrela);
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar estrelas da galáxia: " + e.getMessage());
		}
	}
	
	public void listarEstrelasAltaTemperatura() {
		try
		{
			System.out.println("Insira a temperatura minima das estrelas a serem visualizadas: ");
			int tempMinima = scanner.nextInt();
			scanner.nextLine();
			estrelaService.listarEstrelasAltaTemperatura(tempMinima);
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar estrelas de alta temperatura: " + e.getMessage());
		}
	}
	
	public void contarEstrelasPorClassificacao() {
		try
		{
			estrelaService.contarEstrelasPorClassificacao();
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao contar estrelas por classificação: " + e.getMessage());
		}
	}
	
	public void contarEstrelasPorGalaxia() {
		try
		{
			estrelaService.contarEstrelasPorGalaxia();
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao contar estrelas por galáxia: " + e.getMessage());
		}
	}
	
}
