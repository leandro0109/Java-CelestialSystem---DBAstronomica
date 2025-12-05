package UI;

import service.EstrelaInterativoService;

import javax.swing.*;
import java.awt.*;

public class MenuInterativoEstrela {
    private final EstrelaInterativoService estrelaInterativoService;
    private JFrame frame;
    private JPanel painelPrincipal;

    public MenuInterativoEstrela(EstrelaInterativoService estrelaInterativoService) {
        this.estrelaInterativoService = estrelaInterativoService;
    }

    public void criarInterfaceEstrela() {
        frame = new JFrame("Sistema de Estrelas ✨");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(new Color(245, 245, 255)); // tom suave de roxo-azulado

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Menu de Estrelas", SwingConstants.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 120));
        titulo.setHorizontalTextPosition(SwingConstants.RIGHT);
        titulo.setIconTextGap(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelPrincipal.add(titulo, gbc);

        // Botão: Criar Estrela
        JButton btnCriar = new JButton(" Criar Estrela");
        btnCriar.setPreferredSize(new Dimension(250, 50));
        btnCriar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCriar.addActionListener(e -> estrelaInterativoService.criarEstrelaInterativo(frame));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painelPrincipal.add(btnCriar, gbc);

        // Botão: Excluir Estrela
        JButton btnExcluir = new JButton(" Excluir Estrela");
        btnExcluir.setPreferredSize(new Dimension(250, 50));
        btnExcluir.setFont(new Font("Arial", Font.PLAIN, 16));
        btnExcluir.addActionListener(e -> estrelaInterativoService.excluirEstrelaInterativo(frame));
        gbc.gridy = 2;
        painelPrincipal.add(btnExcluir, gbc);
        
        // Botão: Listar Estrelas
        JButton btnListarEstrelas = new JButton(" Listar Estrelas");
        btnListarEstrelas.setPreferredSize(new Dimension(250, 50));
        btnListarEstrelas.setFont(new Font("Arial", Font.PLAIN, 16));
        btnListarEstrelas.addActionListener(e -> estrelaInterativoService.listarEstrelasInterativo(frame));
        gbc.gridy = 3;
        painelPrincipal.add(btnListarEstrelas, gbc);

        // Botão: Sair
        JButton btnSair = new JButton(" Sair");
        btnSair.setPreferredSize(new Dimension(250, 50));
        btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSair.addActionListener(e -> System.exit(0));
        gbc.gridy = 4;
        painelPrincipal.add(btnSair, gbc);

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }
}
