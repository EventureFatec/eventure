package view.components;

import javax.swing.*;
import java.awt.*;

public class CreateEventForm extends JPanel {

    public JTextField titleField;
    public JTextField locationField;
    public JTextField dateTimeField;
    public JButton imageButton;
    public JTextArea descriptionArea;
    public JButton submitButton;

    public CreateEventForm() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Campos
        formPanel.add(new JLabel("Título do Evento:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Local:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Data e Hora:"));
        dateTimeField = new JTextField();
        formPanel.add(dateTimeField);

        formPanel.add(new JLabel("Imagem:"));
        imageButton = new JButton("Selecionar Imagem");
        formPanel.add(imageButton);

        formPanel.add(new JLabel("Descrição:"));
        descriptionArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane);

        formPanel.add(new JLabel(""));
        submitButton = new JButton("Criar Evento");
        formPanel.add(submitButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
