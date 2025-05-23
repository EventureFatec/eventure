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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ProfilePage extends JPanel {
	private JTextField name;
	private JTextField email;
	private JTextField username;
	private String profilePic;
	private JPasswordField newPassword;
	private JPasswordField newPassword2;
	private JButton saveChangesBtn;
	
	public ProfilePage() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 
		
		var leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(200, 300));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem

		// Adiciona uma margem dinâmica
		leftPanel.add(Box.createVerticalGlue());

		// Carrega a imagem de perfil padrão
		ImageIcon icon = new ImageIcon(getClass().getResource("/iconFotoRosa.png"));
		int size = 120;

		Image img = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		JButton selectImageBtn = new JButton();
		selectImageBtn.setIcon(icon);
		selectImageBtn.setPreferredSize(new Dimension(size, size));
		selectImageBtn.setMaximumSize(new Dimension(size, size));
		selectImageBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectImageBtn.setBackground(new Color(0xe5d8fd));
		selectImageBtn.setBorder(null);
		selectImageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		leftPanel.add(selectImageBtn);
		this.add(leftPanel);

		var rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// Nome
		rightPanel.add(new JLabel("Nome"));
		name = new JTextField(20);
		rightPanel.add(name);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// User Name
		rightPanel.add(new JLabel("User Name"));
		username = new JTextField(20);
		rightPanel.add(username);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Email
		rightPanel.add(new JLabel("Email"));
		email = new JTextField(20);
		rightPanel.add(email);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Nova Senha
		rightPanel.add(new JLabel("Nova Senha"));
		newPassword = new JPasswordField(20);
		rightPanel.add(newPassword);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Repetir Nova Senha
		rightPanel.add(new JLabel("Repita a Nova Senha"));
		newPassword2 = new JPasswordField(20);
		rightPanel.add(newPassword2);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		this.add(rightPanel);
		
	}
	
}