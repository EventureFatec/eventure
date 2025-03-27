package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.eventure.controllers.AddressController;
import com.github.eventure.model.address.Cep;
import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

import net.miginfocom.swing.MigLayout;

public class SecondaryPage extends Page {
    private JButton tempButton;
    private JTextField cepField;
    private JLabel cepLabel;

    public SecondaryPage() {
        super(PageLayouts.REGULAR_LAYOUT);
        // setDefault(true);
        add(new JLabel("Secondary Page!"), "center, wrap");

        tempButton = new JButton("Previous Page");
        tempButton.addActionListener(event -> {
            switchPage(getPageId(HomePage.class));
        });
        add(tempButton);

        var cepPanel = new JPanel(new MigLayout("fill", PageLayouts.REGULAR_LAYOUT));
        cepField = new JTextField();
        cepField.setFont(cepField.getFont().deriveFont(24f));
        cepPanel.add(cepField, "growx, span");

        var cepButton = new JButton("Buscar CEP");
        cepButton.setFont(cepButton.getFont().deriveFont(24f));
        cepButton.addActionListener(event -> tryFindingCPF());
        cepPanel.add(cepButton, "growx, span");

        cepLabel = new JLabel("CEP: ");
        cepPanel.add(cepLabel);

        add(cepPanel, "span");
    }

    private void tryFindingCPF() {
        var aController = new AddressController();
        Cep cep;
        if ((cep = aController.getFromViacep(cepField.getText())) != null) {
            cepLabel.setText("CEP: " + cep.toString());
        } else {
            cepLabel.setText("CEP: ");
        }
    }
}
