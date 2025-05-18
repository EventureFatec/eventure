package com.github.eventure.view;

import javax.swing.*;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.view.components.DisplayEvent;
import com.github.eventure.view.pages.LoginPage;
import com.github.eventure.view.pages.RegisterPage;
import com.github.eventure.view.pages.WelcomePage;



import com.github.eventure.view.pages.MainPage;


import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout; // Layout usado para alternar entre telas
    private JPanel contentPanel;   // Painel que contém as telas

    public MainFrame() {
        setTitle("Eventure | Cada evento é uma aventura!");  // Título da janela
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia maximizado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        setMinimumSize(new Dimension(1280, 720)); // Tamanho mínimo da janela
        setLocationRelativeTo(null); // Centra a janela na tela

        // Inicializando o CardLayout e o painel que vai conter as telas
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
    	var userController = UserController.getInstance();
    	userController.createUserSemMessageBox("allisson", "allissonsx", "Allisson7787@", "allisson@gmail.com");
    	userController.createUserSemMessageBox("pedro", "pedrinho", "Pedro7787@", "pedro@gmail.com");
    	var eventos  = EventController.getInstance();
    	String caminho = "C:/Users/User/Downloads/testeprojeto/teste.jpg";
    	eventos.createEventSemMessageBox(IdController.getIdUser(),"evento do google", "imersão ia", EventClassification.COURSES_AND_WORKSHOPS, "20/02/2025", "15:20", "20:30", caminho, "01001000", "sao paulo", "guaralhus", "bairro20", "orlando novaes", "300", "casa");

        // Adiciona as telas (pages) no contentPanel
        WelcomePage welcomePage = new WelcomePage(this);
        contentPanel.add(welcomePage, "welcome"); // Tela de boas-vindas
        
        RegisterPage registerPage = new RegisterPage(this);
        contentPanel.add(registerPage, "register"); // Tela de cadastro
        
        LoginPage loginPage = new LoginPage(this); // Passando o UserStorage
        contentPanel.add(loginPage, "login"); // Tela de login

        MainPage mainPage = new MainPage(this);
        contentPanel.add(mainPage, "home");
       
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