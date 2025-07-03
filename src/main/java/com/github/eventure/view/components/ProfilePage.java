package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import com.github.eventure.controllers.UserController;
// import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
// import com.github.eventure.model.passwords.Password;
import com.github.eventure.view.MainFrame;
import com.github.eventure.view.pages.MainPage;

public class ProfilePage extends JPanel {
    private JTextField name, email, cpf, username;
    private JPasswordField password, password2;
    private JLabel imagePreview;
    private String imagePath = "";
    private User loggedUser = Session.getLoggedUser();

    public ProfilePage(MainPage mainPage) {
        setLayout(new BorderLayout());

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(180, 600));
        sidePanel.setBackground(new Color(38, 38, 78));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JLabel profileIcon = new JLabel(new ImageIcon(getClass().getResource("/iconFotoRosa.png")));
        profileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sidePanel.add(profileIcon);

        JLabel loginLabel = new JLabel("Login Profile");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(loginLabel);

        add(sidePanel, BorderLayout.WEST);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("Login Profile");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(title);

        JLabel subtitle = new JLabel("Side Profile");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(subtitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        
        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(200, 140));
        imagePreview.setMaximumSize(new Dimension(200, 140));
        imagePreview.setBorder(new LineBorder(Color.GRAY, 1));
        imagePreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadImagePreview();
        mainPanel.add(imagePreview);

        imagePreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ImageController imgCtrl = new ImageController();
                imagePath = imgCtrl.selecionarImagem();
                if (imagePath != null && !imagePath.isEmpty()) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    imagePreview.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH)));
                }
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        name = addField(mainPanel, "Name", loggedUser.getName());
        email = addField(mainPanel, "Email", loggedUser.getEmail());
        cpf = addField(mainPanel, "CPF", loggedUser.getCpf());
        username = addField(mainPanel, "Username", loggedUser.getUsername());

        password = addPasswordField(mainPanel, "Password");
        password2 = addPasswordField(mainPanel, "Repeat Password");

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        buttons.setOpaque(false);

        buttons.add(button("Salvar", new Color(76, 175, 80), e -> {
            if (Arrays.equals(password.getPassword(), password2.getPassword())) {
                ApplyChanges();
            } else {
                JOptionPane.showMessageDialog(this, "Senhas não coincidem!");
            }
        }));

        buttons.add(button("Sair", new Color(244, 67, 54), e -> {
            int option = JOptionPane.showConfirmDialog(null, "Deseja sair da conta?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Session.logout();
                Container parent = this.getParent();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (frame instanceof MainFrame mainFrame) {
                    mainFrame.showPanel("welcome");
                }
                if (mainPage != null) {
                    mainPage.closePanel();
                }
            }
        }));

        buttons.add(button("Deletar", new Color(45, 45, 65), e -> {
            int option = JOptionPane.showConfirmDialog(null, "Deseja excluir sua conta?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                UserController.getInstance().desativarConta(loggedUser);
                Session.logout();
                Container parent = this.getParent();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (frame instanceof MainFrame mainFrame) {
                    mainFrame.showPanel("welcome");
                }
                if (mainPage != null) {
                    mainPage.closePanel();
                }
            }
        }));

        mainPanel.add(buttons);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void loadImagePreview() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/selecionarImagemRosa.png"));
        if (loggedUser.getProfilePic() != null && !loggedUser.getProfilePic().isEmpty()) {
            icon = new ImageIcon(loggedUser.getProfilePic());
        }
        imagePreview.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH)));
    }

    private JTextField addField(JPanel panel, String labelText, String value) {
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        JTextField field = new JTextField(value);
        field.setMaximumSize(new Dimension(360, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return field;
    }

    private JPasswordField addPasswordField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(360, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return field;
    }

    private JButton button(String text, Color color, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 35));
        btn.addActionListener(action);
        return btn;
    }

    private void ApplyChanges() {
        UserController userController = UserController.getInstance();
        userController.cloneUser(
                name.getText(),
                username.getText(),
                new String(password.getPassword()),
                email.getText(),
                cpf.getText(),
                imagePath,
                loggedUser.getUserId()
        );
        JOptionPane.showMessageDialog(this, "Dados atualizados!");
    }
}
