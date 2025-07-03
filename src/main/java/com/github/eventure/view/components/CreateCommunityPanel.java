package com.github.eventure.view.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import com.github.eventure.controllers.CommunityController;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.MainPage;


public class CreateCommunityPanel extends JPanel {
	private String caminhoImagem = null;
    public CreateCommunityPanel(User user,MainPage mainPage) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setBackground(new Color(248, 249, 255)); 

        JLabel lblTitulo = new JLabel("Criar Comunidade");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtNome = new JTextField("");
        estilizarCampo(txtNome);

        JLabel lblDescricao = new JLabel("Descrição");
        lblDescricao.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtDescricao = new JTextField("");
        estilizarCampo(txtDescricao);

        JLabel imagemPreview = new JLabel("Prévia da Imagem");
        imagemPreview.setPreferredSize(new Dimension(300, 150));
        imagemPreview.setMaximumSize(new Dimension(300, 150));
        imagemPreview.setHorizontalAlignment(SwingConstants.CENTER);
        imagemPreview.setVerticalAlignment(SwingConstants.CENTER);
        imagemPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagemPreview.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnUpload = new JButton("Upload Imagem");
        btnUpload.setMaximumSize(new Dimension(200, 40));
        btnUpload.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUpload.setFocusPainted(false);
        btnUpload.setBackground(Color.WHITE);
        btnUpload.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 255)));
        btnUpload.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnUpload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                caminhoImagem = selectedFile.getAbsolutePath();
                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
                imagemPreview.setText("");
                imagemPreview.setIcon(new ImageIcon(scaled));
            }
        });

        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.setPreferredSize(new Dimension(300, 45));
        btnConcluir.setMaximumSize(new Dimension(300, 45));
        btnConcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConcluir.setBackground(new Color(50, 100, 255));
        btnConcluir.setForeground(Color.WHITE);
        btnConcluir.setFocusPainted(false);
        btnConcluir.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnConcluir.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnConcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnConcluir.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();

            if (!nome.isEmpty() && !caminhoImagem.isEmpty()) {
                var controller = CommunityController.getInstance();
                controller.createCommunity(user, nome,descricao,caminhoImagem);
                JOptionPane.showMessageDialog(this, "Comunidade criada com sucesso!");
                var communityPanel = new CommunityPanel(mainPage);
                mainPage.showMainPanel(communityPanel,0);
            } else {
                JOptionPane.showMessageDialog(this, "O nome da comunidade é obrigatório.");
            }
        });

        add(lblTitulo);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(lblNome);
        add(txtNome);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(lblDescricao);
        add(txtDescricao);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(imagemPreview);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btnUpload);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(btnConcluir);
    }

    private void estilizarCampo(JTextField field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        field.setBackground(Color.WHITE);
    }
}