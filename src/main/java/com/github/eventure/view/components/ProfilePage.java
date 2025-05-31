package com.github.eventure.view.components;

import javax.swing.*;
import javax.swing.border.LineBorder;

import com.github.eventure.controllers.ImageController;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfilePage extends JPanel {
    private JTextField name;
    private JTextField email;
    private JTextField username;
    private JPasswordField newPassword;
    private JPasswordField newPassword2;
    private JLabel imagePreview;
    private JButton saveChangesBtn;
    private JButton selectImgBtn;
    private String imagePath;
    public ProfilePage() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // fundo claro neutro

        // Painel principal centralizado horizontalmente
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
        wrapperPanel.setOpaque(false);

        // Espaço esquerdo (para centralizar)
        wrapperPanel.add(Box.createHorizontalGlue());

        // LEFT PANEL (Foto simples - se quiser manter separado, pode deixar vazio ou colocar algo)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setMaximumSize(new Dimension(160, Integer.MAX_VALUE));
        leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        wrapperPanel.add(leftPanel);

        // Espaço entre leftPanel e rightPanel
        wrapperPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        // RIGHT PANEL (imagem + formulário empilhados verticalmente)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));
        rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // --- Começa a montagem da imagem + botão em cima ---

        // Label "Imagem:"
        JLabel imageLabel = new JLabel("Imagem:");
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(imageLabel);

        // Preview da imagem
        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(300, 200));
        imagePreview.setMaximumSize(new Dimension(300, 200));
        imagePreview.setBorder(new LineBorder(Color.BLACK, 1));
        ImageIcon icon03 = new ImageIcon(getClass().getResource("/selecionarImagemRosa.png"));
        imagePreview.setIcon(icon03);
        imagePreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(imagePreview);
        imagePreview.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              	ImageController imageController = new ImageController();
              	imagePath = imageController.selecionarImagem();
            	 if(!imagePath.isEmpty() && imagePath != null) {
                ImageIcon icon = new ImageIcon(imagePath);

                // Redimensiona para caber no JLabel
                Image imagemRedimensionada = icon.getImage().getScaledInstance(
                    imagePreview.getWidth(),
                    imagePreview.getHeight(),
                    Image.SCALE_SMOOTH
                );

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
        rightPanel.add(selectImgBtn);

        // Espaço após o botão
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // --- Agora os campos do formulário ---

        rightPanel.add(label("Nome"));
        name = textField();
        rightPanel.add(name);

        rightPanel.add(label("User Name"));
        username = textField();
        rightPanel.add(username);

        rightPanel.add(label("Email"));
        email = textField();
        rightPanel.add(email);

        rightPanel.add(label("Nova Senha"));
        newPassword = passwordField();
        rightPanel.add(newPassword);

        rightPanel.add(label("Repita a Nova Senha"));
        newPassword2 = passwordField();
        rightPanel.add(newPassword2);

        // Finaliza montagem adicionando rightPanel ao wrapperPanel
        wrapperPanel.add(rightPanel);

        // Espaço direito para centralizar
        wrapperPanel.add(Box.createHorizontalGlue());

        add(Box.createVerticalGlue(), BorderLayout.NORTH);
        add(wrapperPanel, BorderLayout.CENTER);
        add(Box.createVerticalGlue(), BorderLayout.SOUTH);
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
}
