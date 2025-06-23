package com.github.eventure.view;

import javax.swing.*;

import com.github.eventure.controllers.CommunityController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Message;
import com.github.eventure.model.Visibilidade;
import com.github.eventure.view.pages.LoginPage;
import com.github.eventure.view.pages.RegisterPage;
import com.github.eventure.view.pages.WelcomePage;

import com.github.eventure.view.pages.MainPage;

import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout; // Layout usado para alternar entre telas
    private JPanel contentPanel; // Painel que contém as telas

    public MainFrame() {
        setTitle("Eventure | Cada evento é uma aventura!"); // Título da janela
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
        userController.createUserSemMessageBox("luiz", "kkniow", "Teste123!", "bahneh971@gmail.com");
        // criando comunidade
        CommunityController communityController = CommunityController.getInstance();
        var u = userController.findUserById(0);
        var u2 = userController.findUserById(1);
        communityController.createCommunity(u, "salve crias");
        communityController.addUser(0, 1);
        communityController.addUser(0, 2);
        var comunidade = communityController.findCommunityById(0);
        Message m = new Message("Bom dia","7:50","allisson",0);
        Message m2 = new Message("Bom tarde","14:50","pedro",1);
        comunidade.addMessage(m);
        comunidade.addMessage(m2);
        
        communityController.createCommunity(u, "teste");
        communityController.addUser(1, 1);
        communityController.addUser(1, 2);
        var comunidade02 = communityController.findCommunityById(1);
        Message m1 = new Message("teste ","7:50","allisson",0);
        Message m21 = new Message("teste 02","14:50","pedro",1);
        comunidade02.addMessage(m1);
        comunidade02.addMessage(m21);
//        var eventos = EventController.getInstance();
//        String caminho = "C:/Users/User/Downloads/testeprojeto/teste.jpg";
//        eventos.createEventSemMessageBox(0, "Evento do google", "imersão ia", EventClassification.COURSES_AND_WORKSHOPS,
//                "20/02/2025", "25/06/2025", "15:20", "20:30", caminho, "01001000", "sao paulo", "guaralhus", "bairro20",
//                "orlando novaes", "300", "casa",Visibilidade.PRIVADO);
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
        showPanel("welcome");

        // Faz a janela aparecer
        setContentPane(contentPanel);
        setVisible(true);
    }

    // Método para mostrar um painel específico (por nome)
    public void showPanel(String pageName) {
        cardLayout.show(contentPanel, pageName); // Exibe o painel baseado no nome passado
    }
}