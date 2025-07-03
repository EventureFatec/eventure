package com.github.eventure.view.components;

import javax.swing.*;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.MainPage;

import java.awt.*;
import java.util.List;

public class ConfirmarPresencaPanel extends JPanel {
    private int eventId;
    private MainPage mainPage;
    public ConfirmarPresencaPanel(int eventId,MainPage mainPage) {
    	this.mainPage = mainPage;
    	this.eventId = eventId;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("Confirmações de Presença");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Gerenciar Participantes");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField searchField = new JTextField("Buscar nome");
        searchField.setMaximumSize(new Dimension(400, 30));
        searchField.setForeground(Color.GRAY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        add(titulo);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(subtitulo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(searchField);
        add(Box.createRigidArea(new Dimension(0, 20)));

        List<Integer> participantesIds = EventController.getInstance().getConfirmadosQueNaoCompareceram(eventId);
                
        UserController userController = UserController.getInstance();

        JPanel listaCards = new JPanel();
        listaCards.setLayout(new BoxLayout(listaCards, BoxLayout.Y_AXIS));
        listaCards.setOpaque(false);

        for (Integer userId : participantesIds) {
            User user = userController.findUserById(userId);
            if (user == null) continue;
            listaCards.add(criarCardUsuario(user));
            listaCards.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(listaCards);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    private JPanel criarCardUsuario(User user) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        card.setMaximumSize(new Dimension(600, 80));
        
        JLabel foto = new JLabel();
        foto.setPreferredSize(new Dimension(40, 40));

        if (user.getProfilePic() != null && !user.getProfilePic().isEmpty()) {
            foto.setIcon(redimensionarImagem(user.getProfilePic(), 40, 40));
        } else {
            ImageIcon icon = new ImageIcon(getClass().getResource("/fotoPerfil.png"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            foto.setIcon(new ImageIcon(img));
        }
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setOpaque(false);
        JLabel nome = new JLabel(user.getName());
        nome.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel email = new JLabel(user.getEmail());
        email.setFont(new Font("SansSerif", Font.PLAIN, 12));
        email.setForeground(Color.GRAY);
        info.add(nome);
        info.add(email);

        JPanel botoes = new JPanel();
        botoes.setOpaque(false);
        JButton confirmar = new JButton("Confirmar presença");
        confirmar.setBackground(new Color(0, 180, 100));
        confirmar.setForeground(Color.WHITE);
        confirmar.addActionListener(e ->{
        	var eventController = EventController.getInstance();
        	eventController.ConfirmarPresenca(eventId, user.getUserId());
        	JOptionPane.showMessageDialog(null, "Presença confirmada");
        });
        JButton recusar = new JButton("Recusar");
        recusar.setBackground(new Color(220, 50, 50));
        recusar.setForeground(Color.WHITE);
        recusar.addActionListener(e -> {
        	
        });
        botoes.add(confirmar);
        botoes.add(Box.createRigidArea(new Dimension(10, 0)));
        botoes.add(recusar);

        JPanel esquerda = new JPanel();
        esquerda.setOpaque(false);
        esquerda.setLayout(new FlowLayout(FlowLayout.LEFT));
        esquerda.add(foto);
        esquerda.add(Box.createRigidArea(new Dimension(10, 0)));
        esquerda.add(info);

        card.add(esquerda, BorderLayout.WEST);
        card.add(botoes, BorderLayout.EAST);

        return card;
    }
    private ImageIcon redimensionarImagem(String path, int largura, int altura) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}