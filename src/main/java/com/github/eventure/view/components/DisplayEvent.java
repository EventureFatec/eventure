package com.github.eventure.view.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;

public class DisplayEvent extends JPanel {
	private int idEvento;

	public DisplayEvent(int id) {
		this.idEvento = id;
		setLayout(new BorderLayout());
		setBackground(new Color(0xf8f6fc));
		setBorder(new EmptyBorder(20, 20, 20, 20));

		Event event = EventController.getInstance().findEventById(idEvento);

		// --------- Left Panel: Image + Info ---------
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(550, 640));
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0xdddddd), 1),
				new EmptyBorder(15, 15, 15, 15)));

		// Image at the top
		String imagePath = event.getImagePath();
		ImageIcon originalIcon = new ImageIcon(imagePath);
		Image scaledImage = originalIcon.getImage().getScaledInstance(520, 300, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBorder(BorderFactory.createLineBorder(new Color(0xcccccc)));
		leftPanel.add(imageLabel, BorderLayout.NORTH);

		// Info panel below image
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

		// Title
		JLabel titleLabel = new JLabel(event.getTitle());
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
		titleLabel.setForeground(new Color(0x2e1a47));
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(titleLabel);

		infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Date and Time (with emojis)
		String dateTimeHtml = String.format(
				"<html><span style='font-family:Segoe UI; font-size:12px; color:#555555;'>"
						+ "📅 <b>Data:</b> %s<br/>"
						+ "⏰ <b>Início:</b> %s - <b>Término:</b> %s"
						+ "</span></html>",
				event.getDate(), event.getStartHours(), event.getEndHours());
		JLabel dateTimeLabel = new JLabel(dateTimeHtml);
		dateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(dateTimeLabel);

		infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));

		// Location
		String locationHtml = String.format(
				"<html><span style='font-family:Segoe UI; font-size:14px; color:#555555;'>"
						+ "📍 <b>Local:</b> Rua %s, %s - %s, %s - %s"
						+ "</span></html>",
				event.getAddress().getRua(),
				event.getAddress().getNumero(),
				event.getAddress().getBairro(),
				event.getAddress().getCidade(),
				event.getAddress().getEstado());
		JLabel locationLabel = new JLabel(locationHtml);
		locationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(locationLabel);

		infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));

		// Observation (age classification)
		String obsHtml = "<html><span style='font-family:Segoe UI; font-size:13px; font-style:italic; color:#b22222;'>"
				+ "⚠️ Classificação etária: 18 anos | Sujeito a alterações sem aviso prévio."
				+ "</span></html>";
		JLabel obsLabel = new JLabel(obsHtml);
		obsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(obsLabel);

		leftPanel.add(infoPanel, BorderLayout.CENTER);

		// --------- Right Panel: Description ---------
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setPreferredSize(new Dimension(580, 640));
		rightPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0xdddddd), 1),
				new EmptyBorder(15, 15, 15, 15)));

		// JTextPane to render HTML description with scroll
		JTextPane descriptionPane = new JTextPane();
		descriptionPane.setEditable(false);
		descriptionPane.setContentType("text/html");

		// Style sheet to customize font-family, size and color
		HTMLEditorKit kit = new HTMLEditorKit();
		descriptionPane.setEditorKit(kit);

		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule("body {font-family: 'Segoe UI'; font-size: 14px; color: #333333;}");
		styleSheet.addRule("b {color: #2e1a47;}");
		styleSheet.addRule("span {line-height: 1.4;}");

		// Format description text replacing \n by <br>
		String descricaoTexto = event.getDescription() != null ? event.getDescription() : "";
		descricaoTexto = descricaoTexto.replace("\n", "<br/>");

		String descricaoHtml = String.format(
				"<html><body>"
						+ "<p>📝 <b>Descrição:</b></p>"
						+ "<p>%s</p>"
						+ "</body></html>",
				descricaoTexto);

		descriptionPane.setText(descricaoHtml);
		descriptionPane.setCaretPosition(0); // rolar para o topo

		JScrollPane scrollDescription = new JScrollPane(descriptionPane);
		scrollDescription.setBorder(null);
		scrollDescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// Painel de informações adicionais (tipo, participantes, organizador)
		JPanel extraInfoPanel = new JPanel();
		extraInfoPanel.setLayout(new BoxLayout(extraInfoPanel, BoxLayout.Y_AXIS));
		extraInfoPanel.setBackground(Color.WHITE);

		// Adiciona primeiro a descrição (vai aparecer no topo)
		extraInfoPanel.add(scrollDescription);
		extraInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Tipo do evento
		String tipoEvento = event.getType() != null ? event.getType().getLabel() : "Não especificado";
		JLabel tipoLabel = new JLabel("🎯 Tipo de evento: " + tipoEvento);
		tipoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		tipoLabel.setForeground(new Color(0x333333));
		tipoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		extraInfoPanel.add(tipoLabel);
		extraInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Participantes
		int totalParticipantes = event.getConfirmedParticipantIds().size();
		JLabel participantesLabel = new JLabel("👥 Participantes confirmados: " + totalParticipantes);
		participantesLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		participantesLabel.setForeground(new Color(0x333333));
		participantesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		extraInfoPanel.add(participantesLabel);
		extraInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Organizador
		User user = UserController.getInstance().findUserById(event.getIdMaker());
		String organizador = String.valueOf(user.getName());
		JLabel organizadorLabel = new JLabel("🧑‍💼 Organizador: " + organizador);
		organizadorLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		organizadorLabel.setForeground(new Color(0x333333));
		organizadorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		extraInfoPanel.add(organizadorLabel);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(Color.WHITE);
		JButton participarBtn = new JButton("Quero participar");
		participarBtn.setBackground(new Color(0x2e1a47));
		participarBtn.setForeground(Color.WHITE);
		participarBtn.setFocusPainted(false);

		participarBtn.addActionListener(e -> {
		    // Aqui você adicionaria lógica para registrar o participante
			var eventController = EventController.getInstance();
			eventController.adicionarParticipante(idEvento, IdController.getIdUser());
		    
		    // Exemplo: EventController.getInstance().addParticipante(event.getId(), userId);
		});

		bottomPanel.add(participarBtn);
		
		rightPanel.add(bottomPanel,BorderLayout.SOUTH);

		// Adiciona esse painel na parte superior do painel da direita
		rightPanel.add(extraInfoPanel, BorderLayout.NORTH);

		

//		rightPanel.add(scrollDescription, BorderLayout.CENTER);

		// --------- Add panels to main panel ---------
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1150, 680);
	}
}