package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.eventure.controllers.CommunityController;
import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Community;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;

public class CommunityAllPanel extends JPanel {
    private List<Community> allCommunities = new ArrayList<>();

    public CommunityAllPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1100, 550));
        setBackground(new Color(245, 245, 245)); 

        JPanel header = new JPanel(null);
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(1130, 60));

        JLabel logo = new JLabel("Eventure");
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        logo.setBounds(20, 15, 200, 30);
        header.add(logo);


        JLabel headerLabel = new JLabel("Escolha uma Comunidade Para Entrar", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setBounds(0, 10, 1130, 40);
        header.add(headerLabel);

        add(header, BorderLayout.NORTH);

        
        CommunityController communityController = CommunityController.getInstance();
        UserController userController = UserController.getInstance();
        int idUser = IdController.getIdUser();
        User user = userController.findUserById(idUser);
        allCommunities = communityController.getAllCommunities();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        for (Community community : allCommunities) {
            boolean isJoined = community.getIdUsers().contains(idUser);

            ModernCommunityCard card = new ModernCommunityCard(community, isJoined, e -> {
                if (isJoined) {
                    communityController.removeUser(community.getIdCommunity(), idUser);
                    user.removeCommunityList(community.getIdCommunity());
                } else {
                    communityController.addUser(community.getIdCommunity(), idUser);
                }
                refresh();
            });

            contentPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);
    }

    private void refresh() {
        removeAll();
        revalidate();
        repaint();
        add(new CommunityAllPanel(), BorderLayout.CENTER);
    }
}