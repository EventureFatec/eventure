package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.eventure.controllers.EventController;
import com.github.eventure.view.pages.MainPage;

public class EventPanelForDelete extends JPanel{
    private int idEvento;
    public EventPanelForDelete(String title, String location, String date, String imagePath,int id,MainPage mainPage) {
        
        idEvento = id;
        Dimension panelSize = new Dimension(300, 240);
        setPreferredSize(panelSize);
        setMaximumSize(panelSize);
        setMinimumSize(panelSize);

        this.setBackground(new Color(0xe5d8fd));
        setBorder(BorderFactory.createLineBorder(new Color(0xE0E0E0), 1));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.NORTH);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0xFFFFFF));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x4A148C));

        JLabel locationLabel = new JLabel(location);
        locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        locationLabel.setForeground(new Color(0x777777));

        JLabel dateTimeLabel = new JLabel(date);
        dateTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        dateTimeLabel.setForeground(new Color(0x777777));

        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(dateTimeLabel);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int opcao = JOptionPane.showConfirmDialog(null,
                        "Deseja deletar esse Eventos?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION)
                {
                  var eventController = EventController.getInstance();
                  eventController.deleteEventById(idEvento);
                  JOptionPane.showMessageDialog(null, "Evento deletado com sucesso");
                  mainPage.ExibirEvents();
                }else
                {
                	JOptionPane.showMessageDialog(null, "Evento não foi deletado");
                }
            }
        });
        add(infoPanel, BorderLayout.CENTER);
    }
 public void initComponets()
 {
	 
 }
}
