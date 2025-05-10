package com.github.eventure.view.components;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import org.jdesktop.swingx.JXDatePicker;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.NumericDocumentFilter;
import com.github.eventure.model.address.Cep;
import com.github.eventure.web.Requests;

import java.awt.*;


public class CreateEventPanel extends JPanel {

    public CreateEventPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(1130, 590));

        // Painel esquerdo
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(0, 0, 426, 590);
        leftPanel.setBackground(new Color(33, 150, 243));

        JLabel leftLabel = new JLabel("<html><h1 style='color:white;'>Eventure</h1><p style='color:white;'>Criando conexões</p></html>");
        leftLabel.setBounds(50, 300, 300, 100);
        leftPanel.add(leftLabel);

        // Painel direito
        JPanel rightPanel = new JPanel(null); // Layout absoluto para mais controle
        rightPanel.setBounds(426, 0, 704, 590);
        rightPanel.setBackground(Color.WHITE);

        // Título do formulário
        JLabel header = new JLabel("Vamos criar seu evento");
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setBounds(200, 20, 400, 30);
        rightPanel.add(header);

        int xMargin = 50;
        int y = 80;
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
        rightPanel.add(titleField);
        y += 50;

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
        y +=50;
        JLabel labeldate = new JLabel("Data do evento:");
        labeldate.setBounds(xLabel, y, 300, 20);
        labeldate.setFont(new Font("SansSerif", Font.BOLD, 12));
        rightPanel.add(labeldate);
        y += 25;
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setBounds(xField, y, 300, 30);
        rightPanel.add(datePicker);
        
        y+=50;
        
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

        JFormattedTextField endHourField = new JFormattedTextField(timeMask);
        endHourField.setBounds(xField + 160, y, 140, 30);
        rightPanel.add(endHourField);
//        JLabel hourLabel = new JLabel("Horário (início/término):");
//        hourLabel.setBounds(xLabel, y, 300, 20);
//        rightPanel.add(hourLabel);
//        y += 25;

//        JTextField startHourField = new JTextField();
//        PlainDocument doc1 = (PlainDocument) startHourField.getDocument();
//        doc1.setDocumentFilter(new NumericDocumentFilter(4));
//        startHourField.setBounds(xField, y, 140, 30);
//        rightPanel.add(startHourField);
//
//        JTextField endHourField = new JTextField();
//        PlainDocument doc2 = (PlainDocument) endHourField.getDocument();
//        doc2.setDocumentFilter(new NumericDocumentFilter(4));
//        endHourField.setBounds(xField + 160, y, 140, 30);
//        rightPanel.add(endHourField);
//
        y += 50;
        JLabel categoryLabel = new JLabel("Classificação:");
        categoryLabel.setBounds(xLabel, y, 300, 20);
        rightPanel.add(categoryLabel);
        y += 25;
        JComboBox<String> classificationBox = new JComboBox<>();
        for (EventClassification ec : EventClassification.values()) {
            classificationBox.addItem(ec.getLabel());
        }
        classificationBox.setBounds(xField, y, 300, 30);
        rightPanel.add(classificationBox);
        y += 50;
//        JLabel dateLabel = new JLabel("data");
//        dateLabel.setBounds(xLabel, y+20, 300, 20);
//        rightPanel.add(dateLabel);
//        y += 25;
//        JTextField dateField = new JTextField();
//        dateField.setBounds(xField, y + 20, 300, 30);
//        rightPanel.add(dateField);
        // Imagem
//        JLabel imgLabel = new JLabel("Imagem:");
//        imgLabel.setBounds(xLabel, y, 300, 20);
//        rightPanel.add(imgLabel);
//        y += 25;
//
//        JPanel imagePanel = new JPanel();
//        imagePanel.setBackground(new Color(240, 240, 240));
//        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        imagePanel.setBounds(xMargin, y, 300, 120);
//        rightPanel.add(imagePanel);
//        y += 130;

//        JButton imageButton = new JButton("Selecionar Imagem");
//        imageButton.setBounds(xLabel, y, 300, 30);
//        rightPanel.add(imageButton);
//
//        imageButton.addActionListener(e -> {
//            var i = new ImageController();
//            String caminho = i.selecionarImagem();
//            System.out.println("caminho = " + caminho);
//        });

        // Botão Criar Evento (inferior direito)
        JButton proximoButton = new JButton("Continuar");
        proximoButton.setBounds(704 - 150 - 20, 590 - 50, 150, 30); // Largura 150, margem 20 da borda
        rightPanel.add(proximoButton);
        proximoButton.addActionListener(e -> {
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
            imagemPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rightPanel.add(imagemPreview);
            y2 += 210;

            JButton selectImageBtn = new JButton(" ");
            
            selectImageBtn.setBounds((panelWidth - 300) / 2, y2, 35, 40);
            selectImageBtn.setBackground(Color.WHITE); 
            selectImageBtn.setBorder(null);
            ImageIcon icon02 = new ImageIcon(getClass().getResource("/iconFoto04.png"));
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
            //selecionar imagem 
            selectImageBtn.addActionListener(e2 -> {
            	ImageController imageController = new ImageController();
            	String caminho = imageController.selecionarImagem();
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
            });
            // buscar cep
            buscarCepBtn.addActionListener(e2 -> {
              	 String cep01 = cepField.getText();
                String url = Cep.VIACEP_URL + "/" + cep01 + "/json/";
                Cep cep = Requests.get(url, Cep.class);
                if(cep != null)
                {
               	 cidadeField.setText(cep.getLocality());
                }
               });
            // Botão final
            JButton concluirButton = new JButton("Criar Evento");
            concluirButton.setBounds(704 - 150 - 20, 590 - 50, 150, 30);
            rightPanel.add(concluirButton);

            rightPanel.revalidate();
            rightPanel.repaint();
        });

        this.add(leftPanel);
        this.add(rightPanel);
    }
}
