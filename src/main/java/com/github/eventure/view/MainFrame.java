package com.github.eventure.view;

import javax.swing.*;

import com.github.eventure.controllers.CommunityController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Message;
import com.github.eventure.model.Visibilidade;
import com.github.eventure.view.components.EventRequestPanel;
import com.github.eventure.view.pages.LoginPage;
import com.github.eventure.view.pages.RegisterPage;
import com.github.eventure.view.pages.WelcomePage;

import com.github.eventure.view.pages.MainPage;

import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout; 
    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Eventure | Cada evento é uma aventura!"); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720)); 
        setLocationRelativeTo(null);

        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        var userController = UserController.getInstance();
        userController.createUserSemMessageBox("Allisson", "allissonsx", "Allisson7787@", "allisson@gmail.com");
        userController.createUserSemMessageBox("Pedro", "pedro", "Pedro7787@", "pedro@gmail.com");
        userController.createUserSemMessageBox("Luiz", "kkniow", "Teste123!", "bahneh971@gmail.com");
        userController.createUserSemMessageBox("Waldir", "waldir", "Allisson7787@", "waldir@gmail.com");
        
        CommunityController communityController = CommunityController.getInstance();
        var u = userController.findUserById(0);
        var u2 = userController.findUserById(1);
        String caminho02 = "C:/Users/User/Downloads/Acelera Fotos/ClubedoLivro.jpg";
        communityController.createCommunity(u, "Clube do livro","comunidade sobre livro",caminho02);
        communityController.addUser(0, 1);
        communityController.addUser(0, 2);
        var comunidade = communityController.findCommunityById(0);
        Message m = new Message("Bom dia","7:50","allisson",0);
        Message m2 = new Message("Recomendações de livro?","10:50","pedro",1);
        comunidade.addMessage(m);
        comunidade.addMessage(m2);
        String grupoEstudos = "C:/Users/User/Downloads/Acelera Fotos/grupoEstudos.jpg";
        communityController.createCommunity(u, "Grupo de Estudos","Assuntos diversos sobre educação",grupoEstudos);
        communityController.addUser(1, 1);
        communityController.addUser(1, 2);
        var comunidade02 = communityController.findCommunityById(1);
        Message m1 = new Message("Ola tudo bem","7:50","allisson",0);
        Message m21 = new Message("Que horas vai ser o encontro","12:00","pedro",1);
        comunidade02.addMessage(m1);
        comunidade02.addMessage(m21);
        var eventos = EventController.getInstance();
        String caminho = "C:/Users/User/Downloads/testeprojeto/teste.jpg";
        eventos.createEventSemMessageBox(0, "Conferência de Inovação e Tecnologia", "Conferencia para discutir sobre os avanços da tecnologia", EventClassification.CONFERENCE,
                "20/07/2025", "21/07/2025", "15:20", "20:30", caminho, "01001000", "São paulo", "Guaralhus", "Vila Brasil",
                "Orlando Novaes", "312", "Predio",Visibilidade.PRIVADO);
        
        String caminho03 = "C:/Users/User/Downloads/Acelera Fotos/rockInrio.jpg";
        eventos.createEventSemMessageBox(1, "Rock In Rio", "Show disponiveis:Cold Play, Eminem e Imagine Dragons", EventClassification.PARTIES_AND_SHOWS,
                "14/08/2025", "21/08/2025", "12:00", "23:00", caminho03, "20000000", "Rio de Janeiro", "Rio de Janeiro", "centro",
                "Avenida Rio Branco", "450", "Espaço",Visibilidade.PRIVADO);
        
        String caminho04 = "C:/Users/User/Downloads/Acelera Fotos/ClubedoLivro.jpg";
        eventos.createEventSemMessageBox(0, "Clube do livro", "clube sobre livros de ação e aventura", EventClassification.HOBBIES,
                "19/07/2025", "19/07/2025", "12:30", "20:00", caminho04, "12604030", "São paulo", "Lorena", "Comerciarios",
                "Avenida Central", "15", "casa",Visibilidade.PUBLICO);
        
        String caminho0f = "C:/Users/User/Downloads/Acelera Fotos/festajunina.jpg";
        eventos.createEventSemMessageBox(2, "Festa Junina", "Festa com diversas Doces e Salgados", EventClassification.GASTRONOMY,
                "19/07/2025", "19/07/2025", "12:30", "20:00", caminho0f, "12701120", "São paulo", "Cruzeiro", "Vila Paulista",
                "Quinze de Novembro", "18", "casa",Visibilidade.PUBLICO);
        
        var eventController = EventController.getInstance();
        eventController.adicionarParticipanteSemMessageBox(0, 1);
        eventController.adicionarParticipanteSemMessageBox(0, 2);
        eventController.adicionarParticipanteSemMessageBox(0, 3);
        eventController.ConfirmarPresencaSemMessageBox(0, 2);
        
        
        WelcomePage welcomePage = new WelcomePage(this);
        contentPanel.add(welcomePage, "welcome");

        RegisterPage registerPage = new RegisterPage(this);
        contentPanel.add(registerPage, "register"); 

        LoginPage loginPage = new LoginPage(this); 
        contentPanel.add(loginPage, "login"); 
        
        MainPage mainPage = new MainPage(this);
        contentPanel.add(mainPage, "home");

        
        showPanel("welcome");

       
        setContentPane(contentPanel);
        setVisible(true);
    }

    
    public void showPanel(String pageName) {
        cardLayout.show(contentPanel, pageName); 
    }
    
}