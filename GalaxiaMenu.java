package UI;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import service.GalaxiaService;

public class GalaxiaMenu {
	// Variáveis
	private final GalaxiaService galaxiaService;
	private Scanner scanner;
	
	// Construtor
	public GalaxiaMenu(GalaxiaService galaxiaService) {
		this.galaxiaService = galaxiaService;
		this.scanner = new Scanner(System.in);
	}
	
	// Menu de Galáxias
	public void mostrarGalaxiasMenu() {
		while (true) {
			System.out.println("\n🌌 MENU DE GALÁXIAS 🚀");
			System.out.println("1 - Criar Galáxia");
			System.out.println("2 - Listar todas galáxias");
			System.out.println("3 - Atualizar Galáxia");
			System.out.println("4 - Excluir Galáxia");
			System.out.println("5 - Visualizar Galáxia");
			System.out.println("6 - Contar Galáxias");
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
				criarGalaxia();
				break;
			case 2:
				listarGalaxias();
				break;
			case 3:
				atualizarGalaxia();
				break;
			case 4:
				excluirGalaxia();
				break;
			case 5:
				visualizarGalaxia();
				break;
			case 6:
				contarGalaxias();
				break;
			case 0:
				return;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	
	// Métodos
	public void criarGalaxia() {
		try {
			System.out.println("Nome galáxia: ");
			String nomeGalaxia = scanner.nextLine();
			System.out.println("Massa: ");
			String massa = scanner.nextLine();
			System.out.println("Idade em anos: ");
			String idade = scanner.nextLine();
			System.out.println("Quantidade estrelas: ");
			String quantEstrelas = scanner.nextLine();
			System.out.println("Diâmetro aproximado: ");
			String diametroAprox = scanner.nextLine();
			if(!galaxiaService.galaxiaExiste(nomeGalaxia))
				galaxiaService.criarGalaxia(nomeGalaxia, massa, idade, quantEstrelas, diametroAprox);
			else
				System.out.println("Galáxia com o nome inserido já existe no sistema. Tente outro nome.");
		}
		catch(SQLException e) {
			System.out.println("Erro ao criar galáxia: " + e.getMessage());
		}
	}
	
	public void listarGalaxias() {
		try {
			galaxiaService.listarGalaxias();
		} catch(SQLException e) {
			System.out.println("Erro ao listar galáxias: " + e.getMessage());
		}
	}

	public void excluirGalaxia() {
		try {
			System.out.println("Insira nome da galáxia a ser excluida: ");
			String nomeGalaxia = scanner.nextLine();
			galaxiaService.excluirGalaxia(nomeGalaxia);
		}catch(SQLException e) {
			System.out.println("Erro ao excluir galáxia: " + e.getMessage());
		}
	}
	
	public void atualizarGalaxia() {
		try {
			System.out.println("Insira ID da galáxia a ser atualizada: ");
			int idGalaxia = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Nome galáxia: ");
			String nomeGalaxia = scanner.nextLine();
			System.out.println("Massa: ");
			String massa = scanner.nextLine();
			System.out.println("Idade: ");
			String idade = scanner.nextLine();
			System.out.println("Quantidade Estrelas: ");
			String quantEstrelas = scanner.nextLine();
			System.out.println("Diâmetro Aproximado: ");
			String diametroAprox = scanner.nextLine();
			galaxiaService.atualizarGalaxia(idGalaxia, nomeGalaxia, massa, idade, quantEstrelas, diametroAprox);
		}catch(SQLException e) {
			System.out.println("Erro ao editar galáxia: " + e.getMessage());
		}
	}
	
	public void visualizarGalaxia() {
		try {
			System.out.println("Insira nome da galáxia a ser visualizada: ");
			String nomeGalaxia = scanner.nextLine();
			galaxiaService.visualizarGalaxia(nomeGalaxia);
		}catch(SQLException e) {
			System.out.println("Erro ao visualizar a galáxia: " + e.getMessage());
		}
	}
	
	public void contarGalaxias() {
		try {
			galaxiaService.contarGalaxias();
		}catch(SQLException e) {
			System.out.println("Erro ao contar galáxias: " + e.getMessage());
		}
	}
}
