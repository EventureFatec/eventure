package com.github.eventure.view;

import javax.swing.*;

import com.github.eventure.view.pages.ViewRegister;

import java.awt.*;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {
    private CardLayout cardLayout; // Layout usado para alternar entre telas
    private JPanel contentPanel;   // Painel que contém as telas

    public MainFrame() {
        setTitle("Eventure | Cada evento é uma aventura!");  // Título da janela
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia maximizado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        setMinimumSize(new Dimension(800,600)); // Tamanho mínimo da janela
        setLocationRelativeTo(null); // Centra a janela na tela

        // Inicializando o CardLayout e o painel que vai conter as telas
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Adiciona as telas (pages) no contentPanel
//      contentPanel.add(new WelcomePage(this), "welcome"); // Tela de boas-vindas
//      contentPanel.add(new RegisterPage(this), "register"); // Tela de cadastro

        // Configura o contentPanel como o painel principal da janela
        setContentPane(contentPanel);
        var vr = new ViewRegister();
        contentPanel.add(vr,"welcome");
        // Exibe explicitamente a tela de boas-vindas ao iniciar
        cardLayout.show(contentPanel, "welcome");

        // Faz a janela aparecer
        setVisible(true);
    }

    // Método para mostrar um painel específico (por nome)
    public void showPanel(String pageName) {
        cardLayout.show(contentPanel, pageName); // Exibe o painel baseado no nome passado
    }
}
