package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.eventure.controllers.EventController;
import com.github.eventure.model.Event;

public class DisplayEvent extends JPanel{
	private int idEvento; 
    public DisplayEvent(int id) {
        idEvento = id;
        setLayout(null);
        setPreferredSize(new Dimension(1130, 590));

        JLabel label = new JLabel("Exibir Evento", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(0, 200, 1130, 50); 

        add(label);
        var evento = EventController.getInstance();
        Event e = evento.findEventById(idEvento);
        System.out.println(e.getTitle());
        
    }
}
