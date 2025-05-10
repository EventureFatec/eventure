package com.github.eventure.view.components;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import com.github.eventure.controllers.ImageController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.NumericDocumentFilter;

import java.awt.*;


public class DualPanelLayout extends JPanel {

    public DualPanelLayout() {
        setLayout(null);
        setPreferredSize(new Dimension(1130, 590));

        // Painel esquerdo
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(0, 0, 426, 590);
        leftPanel.setBackground(new Color(33, 150, 243));

        JLabel leftLabel = new JLabel("<html><h1 style='color:white;'>Even3</h1><p style='color:white;'>Você em boas mãos</p></html>");
        leftLabel.setBounds(50, 300, 300, 100);
        leftPanel.add(leftLabel);

        // Painel direito
        JPanel rightPanel = new JPanel(null); // Layout absoluto para mais controle
        rightPanel.setBounds(426, 0, 704, 590);
        rightPanel.setBackground(Color.WHITE);

        // Título do formulário
        JLabel header = new JLabel("Vamos criar seu evento");
        header.setFont(new Font("Arial", Font.BOLD, 22));
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
        JLabel titleLabel = new JLabel("Qual nome do evento:");
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
        y +=20;
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
//        y += 50;
//        JLabel categoryLabel = new JLabel("Classificação:");
//        categoryLabel.setBounds(xLabel, y, 300, 20);
//        rightPanel.add(categoryLabel);
//        y += 25;
         y+=50;
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
        JButton submitButton = new JButton("Criar Evento");
        submitButton.setBounds(704 - 150 - 20, 590 - 50, 150, 30); // Largura 150, margem 20 da borda
        rightPanel.add(submitButton);

        this.add(leftPanel);
        this.add(rightPanel);
    }
}
