package com.github.eventure.view.pages;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.User;
import com.github.eventure.view.components.EventPanelConsult;

public class EventPanelForConsult extends JPanel {
	private int x = 20;
	private int y = 20;
	public EventPanelForConsult(MainPage mainPage) {
	    this.setLayout(null);
	    setPreferredSize(new Dimension(1130, 590));

	    JLabel label = new JLabel("Escolha um Evento Para visualizar", SwingConstants.CENTER);
	    label.setFont(new Font("Arial", Font.BOLD, 24));
	    label.setBounds(0, 0, 1130, 50); 
        var userController = UserController.getInstance();
        var eventController = EventController.getInstance();
        User user = userController.findUserById(IdController.getIdUser());
        int cardWidth = 300;
        int cardHeight = 240;
        int spaceX = 20;
        int spaceY = 20;
        int cardsPerRow = 3;

        for (int i = 0; i < user.getMyEventsList().size(); i++) {
            int idEvent = user.getMyEventsList().get(i);
            var event = eventController.findEventById(idEvent);

            EventPanelConsult panel = new EventPanelConsult(
                event.getTitle(),
                event.getAddress().getEstado() + ", " + event.getAddress().getCidade(),
                event.getDate(),
                event.getImagePath(),
                event.getId(),
                mainPage
            );
            

            // Cálculo de posição (coluna e linha)
            int row = i / cardsPerRow;
            int col = i % cardsPerRow;
            int x = 50 +spaceX + (cardWidth + spaceX) * col;
            int y = 60 + (cardHeight + spaceY) * row;

            panel.setBounds(x, y, cardWidth, cardHeight);
            add(panel);
	    add(label);
	} 
}
}


