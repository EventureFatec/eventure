package com.github.eventure.view.components;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.github.eventure.controllers.CommunityController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Message;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.EventPanelForEditPanel;
import com.github.eventure.view.pages.MainPage;

public class CommunityPanel extends JPanel {
	private int idComunidade;
	private String messagem;
	private String nome;
	private String horario;
	private Message message;
	private List<Message> listaDeMensagens = new ArrayList<>();
	private CommunityController communityController;
	private UserController userController = UserController.getInstance();
    public CommunityPanel(int id, MainPage mainPage) {
        this.idComunidade = id;
        setLayout(null);
        setPreferredSize(new Dimension(1130, 590));
        this.setBackground(Color.WHITE);
        // Painel lateral esquerdo
        JPanel sidePanel = new JPanel(null);
        sidePanel.setBounds(0, 0, 250, 590);
//        sidePanel.setBackground(new Color(0xE5D8FD));
        sidePanel.setBackground(Color.WHITE);
        ImageIcon setaImage = new ImageIcon("C:/Users/User/Downloads/seta (2).png");
        JButton backButton = new JButton(setaImage);
        backButton.setBorder(null);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(10, 10, 40, 40);
        sidePanel.add(backButton);
        
        JPanel conteudoScroll = new JPanel();
        conteudoScroll.setBackground(Color.BLUE);
        conteudoScroll.setLayout(new BoxLayout(conteudoScroll, BoxLayout.Y_AXIS));
        conteudoScroll.setBackground(Color.WHITE);
        

        // Adiciona alguns itens de exemplo
        for (int i = 0; i < 100; i++) {
            JLabel item = new JLabel("Item " + (i + 1));
            item.setAlignmentX(Component.LEFT_ALIGNMENT);
            conteudoScroll.add(item);
        }

        // ScrollPane que envolve o painel
        JScrollPane scrollPane01 = new JScrollPane(conteudoScroll);
        scrollPane01.setBounds(10, 60, 230, 400); // altura suficiente antes do botão em y = 500
//        scrollPane01.setBorder(null);
        scrollPane01.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane01.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane01.setBorder(BorderFactory.createEmptyBorder());

        
        scrollPane01.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane01.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        // Adiciona ao painel lateral
        sidePanel.add(scrollPane01);
        
        
        JButton btnCriarComunidade = new JButton("Criar comunidade") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = getHeight();

                // Fundo personalizado
                g2.setColor(new Color(0x330065)); // FUNDO desejado (roxo escuro, por exemplo)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Texto centralizado
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                g2.setFont(getFont());
                g2.setColor(Color.WHITE); // COR DO TEXTO
                g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0x330065)); // Cor da borda (opcional)
                g2.setStroke(new BasicStroke(1));
                int arc = getHeight();
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };

        btnCriarComunidade.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCriarComunidade.setFocusPainted(false);
        btnCriarComunidade.setBorderPainted(false);
        btnCriarComunidade.setContentAreaFilled(false);
        btnCriarComunidade.setOpaque(false);
        btnCriarComunidade.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarComunidade.setBounds(10,500,200,30);
        sidePanel.add(btnCriarComunidade);
        add(sidePanel);

        // Painel de topo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(250, 0, 880, 50);
        topPanel.setBackground(Color.WHITE);
        JLabel comunidadesLabel2 = new JLabel("Comunidade");
        comunidadesLabel2.setBounds(20, 20, 180, 20);
        topPanel.add(comunidadesLabel2);
        
        // colocar foto de perfil
        int idUserPerfil = IdController.getIdUser();
        var userPerfil = userController.findUserById(idUserPerfil);
        ImageIcon imagemOriginalPerfil;
        if(userPerfil.getProfilePic() != null && !userPerfil.getProfilePic().isEmpty())
        {
        	imagemOriginalPerfil = new ImageIcon(userPerfil.getProfilePic());
        
        }else
        {
       	imagemOriginalPerfil = new ImageIcon(getClass().getResource("/fotoPerfil.png"));
        }


        Image imagemRedimensionadaPerfil = imagemOriginalPerfil.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon novaImagemPerfil = new ImageIcon(imagemRedimensionadaPerfil);
        JLabel fotoPerfil = new JLabel(novaImagemPerfil);
        fotoPerfil.setBounds(750, 10, 100, 40);
        topPanel.add(fotoPerfil);

        add(topPanel);

        // Painel de mensagens
        JPanel messagePanel = new JPanel();
        communityController = CommunityController.getInstance();
        var comunidade = communityController.findCommunity(idComunidade);
        listaDeMensagens = comunidade.getMensagens();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Color.WHITE);

        // Crie o scroll e adicione ao painel principal
//        JScrollPane scrollPane = new JScrollPane(messagePanel);
//        scrollPane.setBounds(250, 50, 880, 400);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // rolagem mais suave
//        scrollPane.setBorder(BorderFactory.createEmptyBorder());
//        add(scrollPane);
        JScrollPane scrollPane = new JScrollPane(messagePanel);
        scrollPane.setBounds(250, 50, 880, 400);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        add(scrollPane);
        
        for (Message mensagem : listaDeMensagens) {
            JPanel msgContainer = new JPanel(new BorderLayout());
            msgContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            msgContainer.setBackground(Color.WHITE);
            
            int idUser = mensagem.getIdUser();
            var user = userController.findUserById(idUser);
            ImageIcon imagemOriginal;
            if(user.getProfilePic() != null && !user.getProfilePic().isEmpty())
            {
            	imagemOriginal = new ImageIcon(user.getProfilePic());
            
            }else
            {
            	imagemOriginal = new ImageIcon(getClass().getResource("/fotoPerfil.png"));
            }


            Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon novaImagem = new ImageIcon(imagemRedimensionada);

            
            JLabel avatar = new JLabel(novaImagem);
            avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            avatar.setPreferredSize(new Dimension(40, 40));

            // Painel da mensagem
            JPanel bubble = new JPanel();
            bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));
            bubble.setBackground(new Color(230, 240, 255));
            bubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            bubble.setPreferredSize(new Dimension(100, 60));
            bubble.setMaximumSize(new Dimension(100, 60));
            bubble.setMinimumSize(new Dimension(100, 60));
            bubble.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel nomeHora = new JLabel(mensagem.getName() + " - " + mensagem.getHorario());
            nomeHora.setFont(new Font("Segoe UI", Font.BOLD, 13));
            nomeHora.setForeground(new Color(70, 70, 70));

            String mensagemHtml = String.format(
            	    "<html><div style='width:180px;'>%s</div></html>", mensagem.getMessage()
            	);
            	JLabel texto = new JLabel(mensagemHtml);
            	texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            	texto.setForeground(new Color(40, 40, 40));


            bubble.add(nomeHora);
            bubble.add(Box.createVerticalStrut(5));
            bubble.add(texto);

            msgContainer.add(avatar, BorderLayout.WEST);
            msgContainer.add(bubble, BorderLayout.CENTER);
            msgContainer.setPreferredSize(new Dimension(600, 70));
            msgContainer.setMaximumSize(new Dimension(600, 70));
            msgContainer.setMinimumSize(new Dimension(600, 70));

            messagePanel.add(msgContainer);
            messagePanel.add(Box.createVerticalStrut(10));
        }

        // Campo de texto e botão
        JPanel inputPanel = new JPanel(null); // ou new FlowLayout() se quiser simples
        inputPanel.setBounds(250, 450, 880, 500);
        inputPanel.setBackground(Color.WHITE);

        JTextField inputField = new JTextField("");
        inputField.setBounds(20, 20, 600, 40);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputPanel.add(inputField);

        JButton btnEnviarMsg = new JButton("Enviar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = getHeight();

                // Fundo personalizado
                g2.setColor(new Color(0x330065)); // FUNDO desejado (roxo escuro, por exemplo)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Texto centralizado
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                g2.setFont(getFont());
                g2.setColor(Color.WHITE); // COR DO TEXTO
                g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0x330065)); // Cor da borda (opcional)
                g2.setStroke(new BasicStroke(1));
                int arc = getHeight();
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };

        btnEnviarMsg.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEnviarMsg.setFocusPainted(false);
        btnEnviarMsg.setBorderPainted(false);
        btnEnviarMsg.setContentAreaFilled(false);
        btnEnviarMsg.setOpaque(false);
        btnEnviarMsg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviarMsg.setBounds(640, 20, 100, 40);
        inputPanel.add(btnEnviarMsg);

        add(inputPanel);
        btnEnviarMsg.addActionListener(e ->{
        	messagem = inputField.getText(); 
        	User user = userController.findUserById(IdController.getIdUser());
        	nome = user.getName();
            LocalTime horaAtual = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            horario = horaAtual.format(formatter);
        	message = new Message(messagem,horario,nome,IdController.getIdUser());
        	var community = communityController.findCommunity(idComunidade);
        	community.addMessage(message);
        	listaDeMensagens = community.getMensagens();
        	inputField.setText("");
            atualizarMensagens(messagePanel);
        });
}
    private void atualizarMensagens(JPanel messagePanel) {
        messagePanel.removeAll();

        for (Message mensagem : listaDeMensagens) {
            JPanel msgContainer = new JPanel(new BorderLayout());
            msgContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            msgContainer.setBackground(Color.WHITE);
            int idUser = mensagem.getIdUser();
            var user = userController.findUserById(idUser);
            ImageIcon imagemOriginal;
            if(user.getProfilePic() != null && !user.getProfilePic().isEmpty())
            {
            	imagemOriginal = new ImageIcon(user.getProfilePic());
            
            }else
            {
            	imagemOriginal = new ImageIcon(getClass().getResource("/fotoPerfil.png"));
            }


            Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon novaImagem = new ImageIcon(imagemRedimensionada);

            
            JLabel avatar = new JLabel(novaImagem);
            avatar.setPreferredSize(new Dimension(40, 40));

            JPanel bubble = new JPanel();
            bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));
            bubble.setBackground(new Color(230, 240, 255));
            bubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            bubble.setPreferredSize(new Dimension(100, 60));
            bubble.setMaximumSize(new Dimension(100, 60));
            bubble.setMinimumSize(new Dimension(100, 60));

            JLabel nomeHora = new JLabel(mensagem.getName() + " - " + mensagem.getHorario());
            nomeHora.setFont(new Font("Segoe UI", Font.BOLD, 13));
            nomeHora.setForeground(new Color(70, 70, 70));

            String mensagemHtml = String.format(
                "<html><div style='width:180px;'>%s</div></html>", mensagem.getMessage()
            );
            JLabel texto = new JLabel(mensagemHtml);
            texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            texto.setForeground(new Color(40, 40, 40));

            bubble.add(nomeHora);
            bubble.add(Box.createVerticalStrut(5));
            bubble.add(texto);

            msgContainer.add(avatar, BorderLayout.WEST);
            msgContainer.add(bubble, BorderLayout.CENTER);
            msgContainer.setPreferredSize(new Dimension(600, 70));
            msgContainer.setMaximumSize(new Dimension(600, 70));
            msgContainer.setMinimumSize(new Dimension(600, 70));

            messagePanel.add(msgContainer);
            messagePanel.add(Box.createVerticalStrut(10));
        }

        messagePanel.revalidate();
        messagePanel.repaint();
    }
}
