package UI;

import java.util.Scanner;

import service.EstrelaInterativoService;
import service.EstrelaService;
import service.GalaxiaService;

public class MainMenu {

		private final EstrelaService estrelaService;
		private final GalaxiaService galaxiaService;
		private final EstrelaInterativoService estrelaInterativoService;
		private Scanner scanner;
		
	 
		public MainMenu(EstrelaService estrelaService, GalaxiaService galaxiaService, EstrelaInterativoService estrelaInterativoService) {
			this.scanner = new Scanner(System.in);
			this.galaxiaService = galaxiaService;
			this.estrelaService = estrelaService;
			this.estrelaInterativoService = estrelaInterativoService;
		}
	 
		public void mostrarMainMenu() {
			while (true) {
				System.out.println("\n--- MENU PRINCIPAL ---");
				System.out.println("1 - Gerir Estrelas");
				System.out.println("2 - Gerir Galáxias");
				System.out.println("3 - Gerir Estrelas Interativo (com verificações, incompleto)");
				System.out.println("4 - Gerir Galáxias Interativo (a ser implementado futuramente)");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");
				int opcao = scanner.nextInt();
				scanner.nextLine();
	 
				switch (opcao) {
				case 1:
					EstrelaMenu estrelaMenu = new EstrelaMenu(estrelaService);
					estrelaMenu.mostrarEstrelasMenu();
					break;
				case 2:
					GalaxiaMenu galaxiaMenu = new GalaxiaMenu(galaxiaService);
					galaxiaMenu.mostrarGalaxiasMenu();
					break;
				case 3:
					MenuInterativoEstrela estrelaMenuInterativo = new MenuInterativoEstrela(estrelaInterativoService);
					estrelaMenuInterativo.criarInterfaceEstrela();;
					break;
				case 0:
					System.out.println("A sair...");
					return;
				default:
					System.out.println("Opção inválida. Tente novamente...");
				}
			}
		}
		
	}
