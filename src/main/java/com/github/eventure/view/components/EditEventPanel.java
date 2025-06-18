package com.github.eventure.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Visibilidade;
import com.github.eventure.model.address.Cep;
import com.github.eventure.view.pages.MainPage;
import com.github.eventure.web.Requests;

public class EditEventPanel extends JPanel {
	private String title;
	  private String description;
	  private String date;
	  private String dateEnd;
	  private String starsHours;
	  private String endHours;
	  private Cep cep;
	  private String caminho;
	  private String cepAddress;
	  private String estado;
	  private String cidade;
	  private String bairro;
	  private String rua;
	  private String numero;
	  private String complemento;
	  private Visibilidade visibilidade;
	  private int idEvent;
	  private EventClassification selectedClassification;
	    public EditEventPanel(int id,MainPage mainPage) {
	    	idEvent = id;
	        setLayout(null);
	        setPreferredSize(new Dimension(1130, 590));
             
	        // Painel esquerdo
	        JPanel leftPanel = new JPanel(null);
	        leftPanel.setBounds(0, 0, 426, 590);
	        leftPanel.setBackground(new Color(0xb10ef7));
	        //0xadd8e6 azul mais claro
	        //(33, 150, 243) azul mais forte
	        JLabel leftLabel = new JLabel("<html><h1 style='color:white;'>Eventure</h1><p style='color:white;'>Criando conexões</p></html>");
	        leftLabel.setBounds(50, 300, 300, 100);
	        leftPanel.add(leftLabel);
            var eventController02 = EventController.getInstance();
            var event = eventController02.findEventById(idEvent);
	        // Painel direito
	        JPanel rightPanel = new JPanel(null); // Layout absoluto para mais controle
	        rightPanel.setBounds(426, 0, 704, 590);
	        rightPanel.setBackground(new Color(0xe5d8fd));;

	        // Título do formulário
	        JLabel header = new JLabel("Editar seu evento");
	        header.setFont(new Font("SansSerif", Font.BOLD, 22));
	        header.setBounds(200, 20, 400, 30);
	        rightPanel.add(header);

	        int xMargin = 50;
	        int y = 60;
	        int panelWidth = 704; // largura do rightPanel

	        int fieldWidth = 300;
	        int labelWidth = 300;

	        int xLabel = (panelWidth - labelWidth) / 2;
	        int xField = (panelWidth - fieldWidth) / 2;

	        // Título do evento
	        JLabel titleLabel = new JLabel("Nome do evento:");
	        titleLabel.setBounds(xLabel, y, 300, 20);
	        rightPanel.add(titleLabel);
	        y += 25;

	        JTextField titleField = new JTextField();
	        titleField.setBounds(xField, y, 300, 30);
	        titleField.setText(event.getTitle());
	        rightPanel.add(titleField);
	        y += 35;
	        JRadioButton publicoButton = new JRadioButton("Público");
	        JRadioButton privadoButton = new JRadioButton("Privado");
	        publicoButton.setBounds(xLabel, y, 100, 20);
	        publicoButton.setBackground(new Color(0xe5d8fd));
	        if(event.getVisibilidade() == Visibilidade.PUBLICO)
	        {
	        publicoButton.setSelected(true);
	        }else
	        {
	        	privadoButton.setSelected(true);
	        }
	        privadoButton.setBounds(xLabel + 100, y, 100, 20);
	        privadoButton.setBackground(new Color(0xe5d8fd));
	        
	        ButtonGroup group = new ButtonGroup();
	        group.add(publicoButton);
	        group.add(privadoButton);
	        
	        rightPanel.add(publicoButton);
	        rightPanel.add(privadoButton);
	        y+=25;
	        // Descrição
	        JLabel descLabel = new JLabel("Descrição:");
	        descLabel.setBounds(xLabel, y, 300, 20);
	        rightPanel.add(descLabel);
	        y += 25;

	        JTextArea descriptionArea = new JTextArea();
	        JScrollPane scrollPane = new JScrollPane(descriptionArea);
	        scrollPane.setBounds(xField, y, 300, 120);
	        rightPanel.add(scrollPane);
	        y += 100;
	        descriptionArea.setText(event.getDescription());
	        y += 30;
	        JLabel labeldate = new JLabel("Data do inicio do evento:");
	        labeldate.setBounds(xLabel, y, 300, 20);
	        labeldate.setFont(new Font("SansSerif", Font.BOLD, 12));
	        rightPanel.add(labeldate);
	        y += 25;
	        JXDatePicker datePicker = new JXDatePicker();
	        datePicker.setBounds(xField, y, 300, 30);
	        datePicker.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        rightPanel.add(datePicker);
	        y+=30;
	        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            Date data = formato.parse(event.getDate());
	            datePicker.setDate(data); 
	        } catch (ParseException e) {
	            e.printStackTrace(); 
	        }
	        JLabel labeldate2 = new JLabel("Data do final do evento:");
	        labeldate2.setBounds(xLabel, y, 300, 20);
	        labeldate2.setFont(new Font("SansSerif", Font.BOLD, 12));
	        rightPanel.add(labeldate2);
	        y += 25;
	        JXDatePicker datePicker2 = new JXDatePicker();
	        datePicker2.setBounds(xField, y, 300, 30);
	        datePicker2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        rightPanel.add(datePicker2);
	        SimpleDateFormat formato02 = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            Date data02 = formato.parse(event.getDateEnd());
	            datePicker2.setDate(data02); 
	        } catch (ParseException e) {
	            e.printStackTrace(); 
	        }
	        y += 35;
	        JLabel hourLabel = new JLabel("Horário (início/término):");
	        hourLabel.setBounds(xLabel, y, 300, 20);
	        rightPanel.add(hourLabel);
	        y += 25;
	        MaskFormatter timeMask = null;
	        try {
	            timeMask = new MaskFormatter("##:##");
	            timeMask.setPlaceholderCharacter('_');
	        } catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }

	        JFormattedTextField startHourField = new JFormattedTextField(timeMask);
	        startHourField.setBounds(xField, y, 140, 30);
	        rightPanel.add(startHourField);
	        startHourField.setText(event.getStartHours());

	        JFormattedTextField endHourField = new JFormattedTextField(timeMask);
	        endHourField.setBounds(xField + 160, y, 140, 30);
	        rightPanel.add(endHourField);
	        endHourField.setText(event.getEndHours());
	        y += 35;
	        JLabel categoryLabel = new JLabel("Classificação:");
	        categoryLabel.setBounds(xLabel, y, 300, 20);
	        rightPanel.add(categoryLabel);
	        y += 25;
	        JComboBox<String> classificationBox = new JComboBox<>();
	        for (EventClassification ec : EventClassification.values()) {
	            classificationBox.addItem(ec.getLabel());
	        }
	        EventClassification selectedType = event.getType(); 
	        String selectedLabel = selectedType.getLabel();     
	        classificationBox.setSelectedItem(selectedLabel);
	        classificationBox.setBounds(xField, y, 300, 30);
	        classificationBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        rightPanel.add(classificationBox);
	        y += 50;

	        // Botão Criar Evento (inferior direito)
	        JButton proximoButton = new JButton("Continuar");
	        proximoButton.setBackground(new Color(0x330065));
	        proximoButton.setForeground(Color.WHITE);
	        proximoButton.setFocusPainted(false);
	        proximoButton.setBorderPainted(false);
	        proximoButton.setOpaque(true);
	        proximoButton.setFont(new Font("SansSerif", Font.BOLD, 14));
	        proximoButton.setBounds(704 - 150 - 20, 590 - 50, 150, 30); // Largura 150, margem 20 da borda
	        proximoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        rightPanel.add(proximoButton);
	        proximoButton.addActionListener(e -> {
	        	title = titleField.getText();
	        	description = descriptionArea.getText();
	        	String type = (String) classificationBox.getSelectedItem(); 
	        	 selectedClassification = null;
	        	for (EventClassification ec : EventClassification.values()) {
	        	    if (ec.getLabel().equals(type)) {
	        	        selectedClassification = ec;
	        	        break;
	        	    }
	        	}
	            Date selectedDate = datePicker.getDate();
	            
	            if (selectedDate != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                date = sdf.format(selectedDate);
	            }
	            System.out.println("data 1 = "+date);
	            Date selectedDateEnd = datePicker2.getDate();
	            
	            if (selectedDateEnd != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	                dateEnd = sdf.format(selectedDateEnd);
	            }
	            System.out.println("data 2 = "+dateEnd);
	            
	            if (publicoButton.isSelected()) {
	                System.out.println("Opção selecionada: Público");
	                visibilidade = Visibilidade.PUBLICO;
	            } else if (privadoButton.isSelected()) {
	                System.out.println("Opção selecionada: Privado");
	                visibilidade = Visibilidade.PRIVADO;
	            }
	            System.out.println("visibilidade = "+visibilidade.toString());

	            
	        	starsHours = startHourField.getText();
	        	endHours = endHourField.getText();
	        	System.out.println(title);
	        	System.out.println(description);
	        	System.out.println(selectedClassification.getLabel());
	        	System.out.println(date);
	        	System.out.println(starsHours);
	        	System.out.println(endHours);
	             if(!title.isEmpty() && !description.isEmpty() && !starsHours.isEmpty() && !endHours.isEmpty() && !date.isEmpty() && !dateEnd.isEmpty() && selectedClassification != null) {
	            	 
	            rightPanel.removeAll();

	            int y2 = 20;
	            int centerX;

	            JLabel header02 = new JLabel("Vamos criar seu evento");
	            header02.setFont(new Font("SansSerif", Font.BOLD, 22));
	            centerX = (panelWidth - 300) / 2;
	            header02.setBounds(centerX, y2, 300, 30);
	            rightPanel.add(header02);
	            y2 += 50;

	            JLabel imageLabel = new JLabel("Imagem:");
	            centerX = (panelWidth - 300) / 2;
	            imageLabel.setBounds(centerX, y2, 100, 20);
	            rightPanel.add(imageLabel);
	            y2 += 25;

	            JLabel imagemPreview = new JLabel();
	            imagemPreview.setBounds((panelWidth - 300) / 2, y2, 300, 200);
//	            imagemPreview.setBorder(null);
	            imagemPreview.setBorder(new LineBorder(Color.BLACK, 1));
	            ImageIcon icon03 = new ImageIcon(getClass().getResource("/selecionarImagem.png"));
	            imagemPreview.setIcon(icon03);
	            rightPanel.add(imagemPreview);
	            y2 += 210;

	            JButton selectImageBtn = new JButton(" ");
	            
	            selectImageBtn.setBounds((panelWidth - 300) / 2, y2, 35, 40);
	            selectImageBtn.setBackground(new Color(0xe5d8fd)); 
	            selectImageBtn.setBorder(null);
	            selectImageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            ImageIcon icon02 = new ImageIcon(getClass().getResource("/iconFotoRosa.png"));
	            selectImageBtn.setIcon(icon02);
	            rightPanel.add(selectImageBtn);
	            y2 += 35;

	            // Alinhamento
	            int alinhamentoX = imagemPreview.getX();
	            int campoAltura = 25;
	            int labelLargura = 80;
	            int campoLargura = 150;
	            int espacamentoY = 25;

	            // CEP
	            JLabel cepLabel = new JLabel("CEP:");
	            cepLabel.setBounds(alinhamentoX, y2, labelLargura, campoAltura);
	            rightPanel.add(cepLabel);
	            y2+= 25;
	            JTextField cepField = new JTextField();
	            cepField.setBounds(alinhamentoX, y2, campoLargura, campoAltura);
	            rightPanel.add(cepField);
                
                
	            JButton buscarCepBtn = new JButton("Buscar CEP");
	            buscarCepBtn.setBounds(alinhamentoX + 20 + campoLargura, y2, 120, campoAltura);
	            buscarCepBtn.setBackground(new Color(0x330065));
	            buscarCepBtn.setForeground(Color.WHITE);
	            buscarCepBtn.setFocusPainted(false);
	            buscarCepBtn.setBorderPainted(false);
	            buscarCepBtn.setOpaque(true);
	            buscarCepBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
	            buscarCepBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            rightPanel.add(buscarCepBtn);
	            y2 += espacamentoY;
	            
	            // Estado
	            JLabel estadoLabel = new JLabel("Estado:");
	            estadoLabel.setBounds(alinhamentoX, y2, labelLargura, campoAltura);
	            rightPanel.add(estadoLabel);
	            y2 += 25;
	            JTextField estadoField = new JTextField();
	            estadoField.setBounds(alinhamentoX, y2, campoLargura, campoAltura);
	            rightPanel.add(estadoField);
	           
	            
	            // Cidade
	            JLabel cidadeLabel = new JLabel("Cidade:");
	            cidadeLabel.setBounds(alinhamentoX + 20 + campoLargura, y2-25, labelLargura, campoAltura);
	            rightPanel.add(cidadeLabel);

	            JTextField cidadeField = new JTextField();
	            cidadeField.setBounds(alinhamentoX + 20 + campoLargura, y2, campoLargura, campoAltura);
	            rightPanel.add(cidadeField);
	            
	            y2 += espacamentoY;
	            y2+=25;
	            // Bairro
	            JLabel bairroLabel = new JLabel("Bairro:");
	            bairroLabel.setBounds(alinhamentoX, y2-25, labelLargura, campoAltura);
	            rightPanel.add(bairroLabel);

	            JTextField bairroField = new JTextField();
	            bairroField.setBounds(alinhamentoX, y2, campoLargura, campoAltura);
	            rightPanel.add(bairroField);
	            // Rua
	            JLabel ruaLabel = new JLabel("Rua:");
	            ruaLabel.setBounds(alinhamentoX + 20 + campoLargura, y2 - 25, labelLargura, campoAltura);
	            rightPanel.add(ruaLabel);

	            JTextField ruaField = new JTextField();
	            ruaField.setBounds(alinhamentoX + 20 + campoLargura, y2, 150, campoAltura);
	            rightPanel.add(ruaField);
	            
	            y2 += espacamentoY;
	            
	            // Número
	            JLabel numeroLabel = new JLabel("Número:");
	            numeroLabel.setBounds(alinhamentoX, y2, 150, campoAltura);
	            rightPanel.add(numeroLabel);

	            JTextField numeroField = new JTextField();
	            numeroField.setBounds(alinhamentoX, y2+25, 150, campoAltura);
	            rightPanel.add(numeroField);
	            

	            // Complemento
	            JLabel complementoLabel = new JLabel("Complemento:");
	            complementoLabel.setBounds(alinhamentoX + labelLargura + 90, y2, 100, campoAltura);
	            rightPanel.add(complementoLabel);

	            JTextField complementoField = new JTextField();
	            complementoField.setBounds(alinhamentoX + labelLargura + 90, y2+25, 150, campoAltura);
	            rightPanel.add(complementoField);
	            
	            y2 += espacamentoY;
	            // para garantir que mesmo que o usuario não selecione uma foto diferente. a antiga ja esta setada
	            caminho = event.getImagePath();
	            //selecionar imagem 
	            selectImageBtn.addActionListener(e2 -> {
	            	ImageController imageController = new ImageController();
	            	 caminho = imageController.selecionarImagem();
	            	 if(!caminho.isEmpty() && caminho != null) {
	                ImageIcon icon = new ImageIcon(caminho);

	                // Redimensiona para caber no JLabel
	                Image imagemRedimensionada = icon.getImage().getScaledInstance(
	                    imagemPreview.getWidth(),
	                    imagemPreview.getHeight(),
	                    Image.SCALE_SMOOTH
	                );
	                 
	                // Define a imagem no JLabel
	                imagemPreview.setIcon(new ImageIcon(imagemRedimensionada));
	                imagemPreview.repaint(); 
	            	 }
	            });
	            
	            imagemPreview.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                  	ImageController imageController = new ImageController();
	                	 caminho = imageController.selecionarImagem();
	                	 if(!caminho.isEmpty() && caminho != null) {
	                    ImageIcon icon = new ImageIcon(caminho);

	                    // Redimensiona para caber no JLabel
	                    Image imagemRedimensionada = icon.getImage().getScaledInstance(
	                        imagemPreview.getWidth(),
	                        imagemPreview.getHeight(),
	                        Image.SCALE_SMOOTH
	                    );

	                    // Define a imagem no JLabel
	                    imagemPreview.setIcon(new ImageIcon(imagemRedimensionada));
	                    imagemPreview.repaint();
	                	 }
	                }

	                @Override
	                public void mouseEntered(MouseEvent e) {
	                    imagemPreview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	                }
	            });
	            // buscar cep
	            buscarCepBtn.addActionListener(e2 -> {
	              	 String cep01 = cepField.getText();
	                String url = Cep.VIACEP_URL + "/" + cep01 + "/json/";
	                Cep cep = Requests.get(url, Cep.class);
	                if(cep != null)
	                {
	                	estadoField.setText(cep.getStateShorthand());
	                	cidadeField.setText(cep.getLocality());
	                	bairroField.setText(cep.getNeighborhood());
	                	ruaField.setText(cep.getStreet());
	                	complementoField.setText(cep.getComplement());
	                }
	               });
	            // Botão final
	            JButton concluirButton = new JButton("Editar Evento");
	            concluirButton.setBounds(704 - 150 - 20, 590 - 50, 150, 30);
	            concluirButton.setBackground(new Color(0x330065));
	            concluirButton.setForeground(Color.WHITE);
	            concluirButton.setFocusPainted(false);
	            concluirButton.setBorderPainted(false);
	            concluirButton.setOpaque(true);
	            concluirButton.setFont(new Font("SansSerif", Font.BOLD, 14));
	            concluirButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            rightPanel.add(concluirButton);
	            concluirButton.addActionListener(e2 -> {
	            	cepAddress = cepField.getText();
	            	estado = estadoField.getText();
	            	cidade = cidadeField.getText();
	            	bairro = bairroField.getText();
	            	rua = ruaField.getText();
	            	numero = numeroField.getText();
	            	complemento = complementoField.getText();
	            	boolean cepValido =  cepAddress.matches("\\d{8}");
	            	if(!caminho.isEmpty()  && !cepAddress.isEmpty() && cepValido && !estado.isEmpty() && !cidade.isEmpty() && !bairro.isEmpty() && !rua.isEmpty() && !numero.isEmpty()) {
	            		var eventController = EventController.getInstance();
	            		System.out.println("id controller no create event = "+IdController.getIdUser());
	            		// metodo de editar
	            		eventController.eventClone(id, title, description, selectedClassification, date, dateEnd, starsHours, endHours, caminho, cepAddress, estado, cidade, bairro, rua, numero, complemento,visibilidade);          		
	            		JOptionPane.showMessageDialog(null,"Evento editado com sucesso!");
	                    this.setVisible(false);
	                    mainPage.ExibirEvents();
	            	}else {
	            		JOptionPane.showMessageDialog(null, "Erro ao criar evento preencha as informações com valores validos");
	            	}
	            	});
	            JButton voltarButton = new JButton("Voltar");
	            voltarButton.setBounds(20, 590 - 50, 150, 30);
	            voltarButton.setBackground(new Color(0x330065));
	            voltarButton.setForeground(Color.WHITE);
	            voltarButton.setFocusPainted(false);
	            voltarButton.setBorderPainted(false);
	            voltarButton.setOpaque(true);
	            voltarButton.setFont(new Font("SansSerif", Font.BOLD, 14));
	            voltarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            rightPanel.add(voltarButton);
	            
	           rightPanel.revalidate();
	           rightPanel.repaint();
	           cepField.setText(event.getAddress().getCep());
	           estadoField.setText(event.getAddress().getEstado());
	           cidadeField.setText(event.getAddress().getCidade());
	           bairroField.setText(event.getAddress().getBairro());
	           ruaField.setText(event.getAddress().getRua());
	           numeroField.setText(event.getAddress().getNumero());
	           complementoField.setText(event.getAddress().getComplemento());
	           ImageIcon icon04 = new ImageIcon(event.getImagePath());
	           Image imagemRedimensionada02 = icon04.getImage().getScaledInstance(
	                    imagemPreview.getWidth(),
	                    imagemPreview.getHeight(),
	                    Image.SCALE_SMOOTH
	                );
	           imagemPreview.setIcon(new ImageIcon(imagemRedimensionada02));
	            voltarButton.addActionListener(e2 -> {
	            	rightPanel.removeAll();
	                rightPanel.add(header);
	                rightPanel.add(titleLabel);
	                rightPanel.add(titleField);
	                titleField.setText(title);
	                rightPanel.add(descLabel);
	                rightPanel.add(scrollPane);
	                descriptionArea.setText(description);
	                rightPanel.add(labeldate);
	                rightPanel.add(datePicker);
	                rightPanel.add(labeldate2);
	                rightPanel.add(datePicker2);
	                rightPanel.add(hourLabel);
	                rightPanel.add(privadoButton);
	                rightPanel.add(publicoButton);
	                rightPanel.add(startHourField);
	                startHourField.setText(starsHours);
	                rightPanel.add(endHourField);
	                endHourField.setText(endHours);
	                rightPanel.add(categoryLabel);
	                rightPanel.add(classificationBox);
	                rightPanel.add(proximoButton);
	                rightPanel.revalidate();
	                rightPanel.repaint();
	            });
	             }else {
	            	 JOptionPane.showMessageDialog(null, "Prencha os campos corretamente"); 
	            	
	             }
	        });

	        this.add(leftPanel);
	        this.add(rightPanel);
	    }
}
