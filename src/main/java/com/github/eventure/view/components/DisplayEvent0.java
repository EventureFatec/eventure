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
	private JButton concluir;
    public DisplayEvent0(int id) {
    	setLayout(new BorderLayout());
    	idEvento = id;
    	EventController eventController = EventController.getInstance();
    	Event event = eventController.findEventById(idEvento);

    	JPanel contentPanel = new JPanel();
    	//contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    	contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
    	contentPanel.setBackground(Color.WHITE);

    	// Garantir que imagePreview foi instanciado
        imagePreview = new JLabel();
    	imagePreview.setPreferredSize(new Dimension(540, 300));

    	String imagePath = event.getImagePath();

    	if (imagePath != null && !imagePath.isEmpty()) {
    	    ImageIcon icon = new ImageIcon(imagePath);

    	    Image imagemRedimensionada = icon.getImage().getScaledInstance(
    	        imagePreview.getPreferredSize().width,
    	        imagePreview.getPreferredSize().height,
    	        Image.SCALE_SMOOTH
    	    );

    	    imagePreview.setIcon(new ImageIcon(imagemRedimensionada));

    	    JPanel leftPanel = new JPanel();
    	    leftPanel.setPreferredSize(new Dimension(565, 590));
    	    leftPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 520));
//    	    leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	    leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    	    leftPanel.add(new JLabel("teste"));
//    	    leftPanel.add(imagePreview);
    	    contentPanel.add(leftPanel);
    	    JPanel rightPanel = new JPanel();
    	    rightPanel.setPreferredSize(new Dimension(565, 590));
    	    rightPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 520));
    	    //rightPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	    rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	    rightPanel.add(imagePreview);
    	    contentPanel.add(rightPanel);
    	}

    	// Força altura maior para scroll funcionar mesmo com só 1 item
    	contentPanel.setPreferredSize(new Dimension(820, 600));

    	JScrollPane scrollPane = new JScrollPane(contentPanel);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scrollPane.setBorder(null);

    	add(scrollPane, BorderLayout.CENTER);
   }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1130, 590); // tamanho visível esperado pelo método showMainPanel
    }
}
