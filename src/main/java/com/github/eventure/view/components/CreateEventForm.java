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

        JLabel titleLabel = new JLabel("Vamos criar seu evento");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        // Função auxiliar
        formPanel.add(createLabeledField("Título do Evento:", titleField = new JTextField()));
        formPanel.add(createLabeledField("Local:", locationField = new JTextField()));
        formPanel.add(createLabeledField("Data e Hora:", dateTimeField = new JTextField()));

        JLabel imageLabel = new JLabel("Imagem:");
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageButton = new JButton("Selecionar Imagem");
        imageButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(imageLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(imageButton);
        formPanel.add(Box.createVerticalStrut(15));

        JLabel descLabel = new JLabel("Descrição:");
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(descLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(scrollPane);
        formPanel.add(Box.createVerticalStrut(20));

        submitButton = new JButton("Criar Evento");
        submitButton.setPreferredSize(new Dimension(200, 40));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(submitButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private JPanel createLabeledField(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
        return panel;
    }
}

