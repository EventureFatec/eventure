package com.github.eventure.view.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import com.github.eventure.controllers.ImageController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
import com.github.eventure.model.passwords.Password;

public class ProfilePage extends JPanel {
    private JTextField name;
    private JTextField email;
    private JTextField username;
    private JPasswordField Password;
    private JPasswordField Password2;
    private JLabel imagePreview;
    private JButton selectImgBtn;
    private String path;
    private User loggedUser = Session.getLoggedUser();

    public ProfilePage() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(240, 240, 240)); // fundo claro neutro
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label "Imagem:"
        JLabel imageLabel = new JLabel("Imagem:");
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(imageLabel);

        // Preview da imagem
        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(300, 200));
        imagePreview.setMaximumSize(new Dimension(300, 200));
        imagePreview.setBorder(new LineBorder(Color.BLACK, 1));
        ImageIcon icon03 = new ImageIcon(getClass().getResource("/selecionarImagemRosa.png"));
        imagePreview.setIcon(icon03);
        imagePreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(imagePreview);

        // Botão de selecionar imagem
        selectImgBtn = new JButton(" ");
        selectImgBtn.setBackground(new Color(0xe5d8fd));
        selectImgBtn.setBorder(null);
        selectImgBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon icon02 = new ImageIcon(getClass().getResource("/iconFotoRosa.png"));
        selectImgBtn.setIcon(icon02);
        selectImgBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectImgBtn.addActionListener(e -> {
            ImageController imageController = new ImageController();
            path = imageController.selecionarImagem();
            if (path != null && !path.isEmpty()) {
                var icontemp = new ImageIcon(path);
                Image resizedImg = icontemp.getImage().getScaledInstance(
                        imagePreview.getWidth(),
                        imagePreview.getHeight(),
                        Image.SCALE_SMOOTH
                );
                imagePreview.setIcon(new ImageIcon(resizedImg));
                imagePreview.repaint();
            }
        });
        add(selectImgBtn);

        add(Box.createRigidArea(new Dimension(0, 15))); // espaço vertical

        // Campos do formulário
        add(label("Nome"));
        name = textField();
        name.setText(loggedUser.getName());
        add(name);

        add(label("User Name"));
        username = textField();
        username.setText(loggedUser.getUsername());
        add(username);

        add(label("Email"));
        email = textField();
        email.setText(loggedUser.getEmail());
        add(email);

        add(label("Nova Senha"));
        Password = passwordField();
        add(Password);

        add(label("Repita a Nova Senha"));
        Password2 = passwordField();
        add(Password2);

        add(Box.createRigidArea(new Dimension(0, 30))); // espaço antes dos botões

        // Painel horizontal para os botões lado a lado
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonsPanel.setOpaque(false);

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> {           
            String newName = name.getText();
            String newUsername = username.getText();
            String newEmail = email.getText();
            String newPassword = new String(Password.getPassword());
            String newPassword2 = new String(Password2.getPassword());

            System.out.println(newName);
            System.out.println(newUsername);
            System.out.println(newEmail);
            System.out.println(newPassword);
            System.out.println(newPassword2);

            if (newPassword.equals(newPassword2)){
                System.out.println("Modificando usuário...");
                ApplyChanges(newName, newUsername, newEmail, newPassword);
            }    
        });
        JButton closeButton = new JButton("Cancelar");
        JButton logoutButton = new JButton("Sair");

        buttonsPanel.add(saveButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(closeButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(logoutButton);

        add(buttonsPanel);
    }

    private JLabel label(String text) {
        var label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        return label;
    }

    private JTextField textField() {
        var field = new JTextField();
        field.setMaximumSize(new Dimension(360, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    private JPasswordField passwordField() {
        var field = new JPasswordField();
        field.setMaximumSize(new Dimension(360, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    private void ApplyChanges(String name, String username, String email, String password) {
        var userController = UserController.getInstance();

        int id = loggedUser.getUserId();
        String cpf = loggedUser.getCpf();

        userController.cloneUser(name, username, password, email, cpf, id);
        System.out.println("Usuário modificado!");
    }

}
