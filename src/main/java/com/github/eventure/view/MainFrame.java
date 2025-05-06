package com.github.eventure.view;

import javax.swing.*;
import com.github.eventure.view.pages.RegisterPage;
import com.github.eventure.view.pages.WelcomePage;
import com.github.eventure.view.pages.LoginPage;
import com.github.eventure.storage.UserStorage;

import java.awt.*;

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

        // Criação da instância de UserStorage
        UserStorage userStorage = new UserStorage();

        // Adiciona as telas (pages) no contentPanel
        WelcomePage welcomePage = new WelcomePage(this);
        contentPanel.add(welcomePage, "welcome"); // Tela de boas-vindas
        RegisterPage registerPage = new RegisterPage(this);
        contentPanel.add(registerPage, "register"); // Tela de cadastro
        LoginPage loginPage = new LoginPage(this, userStorage); // Passando o UserStorage
        contentPanel.add(loginPage, "login"); // Tela de login

        // Exibe explicitamente a tela de boas-vindas ao iniciar
        cardLayout.show(contentPanel, "welcome");

        // Faz a janela aparecer
        setContentPane(contentPanel);
        setVisible(true);
    }

    // Método para mostrar um painel específico (por nome)
    public void showPanel(String pageName) {
        cardLayout.show(contentPanel, pageName); // Exibe o painel baseado no nome passado
    }
}