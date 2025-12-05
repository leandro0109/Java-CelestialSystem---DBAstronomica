package main;

import java.sql.Connection;
import java.sql.SQLException;

import UI.MainMenu;
import database.LigaBaseDados;
import service.EstrelaInterativoService;
import service.EstrelaService;
import service.GalaxiaService;

public class Main {
    public static void main(String[] args) {
        try (LigaBaseDados databaseConnection = new LigaBaseDados()) {
            Connection conn = databaseConnection.getConnection();
            EstrelaService estrelaService = new EstrelaService(conn);
            GalaxiaService galaxiaService = new GalaxiaService(conn);
            EstrelaInterativoService estrelaInterativoService = new EstrelaInterativoService(estrelaService);

            MainMenu mainMenu = new MainMenu(estrelaService, galaxiaService, estrelaInterativoService);
            mainMenu.mostrarMainMenu();
            
        } catch (SQLException e) {
            System.err.println("Erro de ligação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
