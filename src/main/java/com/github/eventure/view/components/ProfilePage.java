package com.github.eventure.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.github.eventure.controllers.ImageController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.github.eventure.controllers.UserController;
// import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
// import com.github.eventure.model.passwords.Password;
import com.github.eventure.view.MainFrame;

public class ProfilePage extends JPanel {
    private JTextField name;
    private JTextField email;
    private JTextField cpf;
    private JTextField username;
    private JPasswordField Password;
    private JPasswordField Password2;
    private JLabel imagePreview;
    private JButton selectImgBtn;
    private String imagePath;
    private User loggedUser = Session.getLoggedUser();

    public ProfilePage() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(0xe5d8fd));

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
        imagePreview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageController imageController = new ImageController();
                imagePath = imageController.selecionarImagem();
                if (!imagePath.isEmpty() && imagePath != null) {
                    ImageIcon icon = new ImageIcon(imagePath);

                    // Redimensiona para caber no JLabel
                    Image imagemRedimensionada = icon.getImage().getScaledInstance(
                            imagePreview.getWidth(),
                            imagePreview.getHeight(),
                            Image.SCALE_SMOOTH);

                    // Define a imagem no JLabel
                    imagePreview.setIcon(new ImageIcon(imagemRedimensionada));
                    imagePreview.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                imagePreview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }
        });

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
            imagePath = imageController.selecionarImagem();
            if (imagePath != null && !imagePath.isEmpty()) {
                var icontemp = new ImageIcon(imagePath);
                Image resizedImg = icontemp.getImage().getScaledInstance(
                        imagePreview.getWidth(),
                        imagePreview.getHeight(),
                        Image.SCALE_SMOOTH);
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

        add(label("CPF"));
        cpf = textField();
        cpf.setText(loggedUser.getCpf());
        add(cpf);

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
            String newCpf = cpf.getText();
            String newEmail = email.getText();
            String newPassword = new String(Password.getPassword());
            String newPassword2 = new String(Password2.getPassword());

            System.out.println(newName);
            System.out.println(newUsername);
            System.out.println(newCpf);
            System.out.println(newEmail);
            System.out.println(newPassword);
            System.out.println(newPassword2);

            if (newPassword.equals(newPassword2)) {
                System.out.println("Modificando usuário...");
                ApplyChanges(newName, newUsername, newCpf, newEmail, newPassword);
            }
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair da conta?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                Session.logout();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (frame instanceof MainFrame mainFrame) {
                    mainFrame.showPanel("welcome");
                }
            }
        });

        buttonsPanel.add(saveButton);
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

    private void ApplyChanges(String name, String username, String cpf, String email, String password) {
        var userController = UserController.getInstance();

        int id = loggedUser.getUserId();

        userController.cloneUser(name, username, password, email, cpf, id);
        System.out.println("Usuário modificado!");
    }

}
