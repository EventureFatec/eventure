package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
    private JTextField ufField;
    private JTextField cityField;
    private JTextField streetField;
    private JLabel addressLabel;

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

        var addressPanel = new JPanel(new MigLayout("fill", PageLayouts.REGULAR_LAYOUT));

        ufField = new JTextField();
        ufField.setFont(ufField.getFont().deriveFont(24f));
        addressPanel.add(ufField, "grow, split 2");

        cityField = new JTextField();
        cityField.setFont(cityField.getFont().deriveFont(24f));
        addressPanel.add(cityField, "grow");

        streetField = new JTextField();
        streetField.setFont(streetField.getFont().deriveFont(24f));
        addressPanel.add(streetField, "grow, span, push");

        var addressButton = new JButton("Buscar Endereço");
        addressButton.setFont(addressButton.getFont().deriveFont(24f));
        addressButton.addActionListener(event -> tryFindingStreet());
        addressPanel.add(addressButton, "grow, span, wrap");

        addressLabel = new JLabel("Endereço: ");
        addressLabel.setFont(addressLabel.getFont().deriveFont(16f));
        addressPanel.add(addressLabel, "align left, grow, span");

        add(addressPanel, "growx");
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

    private void tryFindingStreet() {
        var aController = new AddressController();
        ArrayList<Cep> cep;
        if ((cep = aController.searchAddressOnViacep(ufField.getText(), cityField.getText(), streetField.getText())) != null) {
            addressLabel.setText("Endereço: " + cep.getFirst().toString());
        } else {
            addressLabel.setText("Endereço: ");
        }
    }
}
