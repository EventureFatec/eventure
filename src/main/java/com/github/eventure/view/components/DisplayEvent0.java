package com.github.eventure.view.components;

import javax.swing.*;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.model.Event;

import java.awt.*;

public class DisplayEvent0 extends JPanel {
	private int idEvento;
	private JLabel titulo;
	private String imagePath;
	private JLabel imagePreview;
	private JLabel descricao;
	private JLabel data;
	private JLabel horarioInicio;
	private JLabel horarioTermino;
	private JLabel cep;
	private JLabel estado;
	private JLabel cidade;
	private JLabel numero;
	private JLabel bairro;
	private JLabel complemento;
	private JButton btnConcluir;
    public DisplayEvent0(int id) {
    	setLayout(new BorderLayout());
    	idEvento = id;
    	EventController eventController = EventController.getInstance();
    	Event event = eventController.findEventById(idEvento);

    	JPanel contentPanel = new JPanel();
    	contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
    	contentPanel.setBackground(Color.WHITE);

        imagePreview = new JLabel();
    	imagePreview.setPreferredSize(new Dimension(500,300));

    	String imagePath = event.getImagePath();
    	ImageIcon icon = new ImageIcon(imagePath);
    	Image imagemRedimensionada = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
    	RoundedImageLabel imagePreview = new RoundedImageLabel(imagemRedimensionada);

    	    imagePreview.setIcon(new ImageIcon(imagemRedimensionada));
    	    JPanel leftPanel = new JPanel();
    	    leftPanel.setPreferredSize(new Dimension(565, 590));
    	    
    	    leftPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 520));
    	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    	    leftPanel.setBackground(new Color(0xe5d8fd));
    	    titulo = new JLabel("<html><div style='font-family:Segoe UI; font-size:32px; font-weight:bold; margin:0;'>"
                    + event.getTitle() + "</div></html>");
    	    titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
    	    titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
    	    titulo.setForeground(new Color(0x333333)); // Cor escura para contraste
    	    leftPanel.add(Box.createVerticalStrut(20));
    	    leftPanel.add(titulo);

    	    
    	    String dataCompleta = String.format("%s - %s às %s", 
    	        event.getDate(), event.getDate(), event.getStartHours());
    	    JLabel dataHora = new JLabel("📅 " + dataCompleta);
    	    dataHora.setAlignmentX(Component.LEFT_ALIGNMENT);
    	    dataHora.setFont(new Font("SansSerif", Font.PLAIN, 20));
    	    dataHora.setForeground(Color.DARK_GRAY);
    	    leftPanel.add(Box.createVerticalStrut(10));
    	    leftPanel.add(dataHora);

    	    
    	    JLabel local = new JLabel("📍 " + "Rua "+event.getAddress().getRua() + ", " 
    	        + event.getAddress().getNumero() + " - " + event.getAddress().getBairro()
    	        + ", " + event.getAddress().getCidade() + " - " + event.getAddress().getEstado());
    	    local.setAlignmentX(Component.LEFT_ALIGNMENT);
    	    local.setFont(new Font("SansSerif", Font.PLAIN, 20));
    	    local.setForeground(Color.DARK_GRAY);
    	    leftPanel.add(Box.createVerticalStrut(10));
    	    leftPanel.add(local);

    	    
    	    JLabel descricaoLabel = new JLabel("Descrição:");
    	    descricaoLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
    	    leftPanel.add(descricaoLabel);
    	    descricao = new JLabel("<html><p>" + event.getDescription() + "</p></html>");
    	    descricao.setAlignmentX(Component.LEFT_ALIGNMENT);
    	    descricao.setFont(new Font("SansSerif", Font.PLAIN, 18));
    	    descricao.setForeground(new Color(0x222222));
    	    descricao.setMaximumSize(new Dimension(500, 100));
    	    leftPanel.add(Box.createVerticalStrut(20));
    	    leftPanel.add(descricao);

    	    // Observação (classificação etária ou aviso)
    	    JLabel observacao = new JLabel("Classificação etária: 18 anos | Sujeito a alterações sem aviso prévio.");
    	    observacao.setAlignmentX(Component.LEFT_ALIGNMENT);
    	    observacao.setFont(new Font("SansSerif", Font.ITALIC, 14));
    	    observacao.setForeground(new Color(0x880000));
    	    leftPanel.add(Box.createVerticalStrut(15));
    	    leftPanel.add(observacao);
    	    contentPanel.add(leftPanel);
    	    JPanel rightPanel = new JPanel();
    	    rightPanel.setPreferredSize(new Dimension(565, 590));
    	    rightPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 520));
    	    rightPanel.setBackground(new Color(0xe5d8fd));
    	    rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    	    rightPanel.add(imagePreview);
    	    contentPanel.add(rightPanel);
    	

    	
    	contentPanel.setPreferredSize(new Dimension(820, 600));

    	JScrollPane scrollPane = new JScrollPane(contentPanel);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setBorder(null);

    	add(scrollPane, BorderLayout.CENTER);
   }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1130, 590); 
    }
}
