package com.github.eventure.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.github.eventure.view.MainFrame;

public class MainPage extends JPanel {

    private MainFrame frame;

    private JPanel sidebar;
    private JPanel topbar;
    private JPanel galleryPanel;
    private JLayeredPane layeredPane;

    private final int SIDEBAR_COLLAPSED_WIDTH = 50; // Tamanho da barra quando "fechada"
    private final int SIDEBAR_EXPANDED_WIDTH = 300; // Tamanho da barra quando "aberta"

    public MainPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        add(layeredPane, BorderLayout.CENTER);

        // Galeria de fundo
        galleryPanel = new JPanel();
        galleryPanel.setLayout(null);
        galleryPanel.setBackground(new Color(0x330065));
        galleryPanel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(galleryPanel, JLayeredPane.DEFAULT_LAYER);

        // Barra superior
        topbar = new JPanel();
        topbar.setLayout(new BorderLayout());
        topbar.setBackground(new Color(0xe5d8fd));
        topbar.setPreferredSize(new Dimension(0, 48));

        ImageIcon icon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        JLabel logo = new JLabel(icon);
        topbar.add(logo, BorderLayout.WEST);

        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);

        JButton createEventButton = new JButton("Criar Evento");
        createEventButton.setBackground(new Color(0x330065));
        createEventButton.setForeground(Color.WHITE);
        createEventButton.setFocusPainted(false);
        createEventButton.setBorderPainted(false);
        createEventButton.setOpaque(true);
        createEventButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        createEventButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createEventButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Criar Evento clicado!", "Criar Evento", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(createEventButton);

        ImageIcon chatIcon = new ImageIcon(getClass().getResource("/ChatButton.png"));
        JButton chatButton = new JButton(chatIcon);
        chatButton.setContentAreaFilled(false);
        chatButton.setBorderPainted(false);
        chatButton.setFocusPainted(false);
        chatButton.setOpaque(false);
        chatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chatButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Chat clicado!", "Chat", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(chatButton);

        ImageIcon bellIcon = new ImageIcon(getClass().getResource("/BellButton.png"));
        JButton bellButton = new JButton(bellIcon);
        bellButton.setContentAreaFilled(false);
        bellButton.setBorderPainted(false);
        bellButton.setFocusPainted(false);
        bellButton.setOpaque(false);
        bellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bellButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Notificação clicado!", "Notificação", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(bellButton);

        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/UserButton.png"));
        JButton profileButton = new JButton(profileIcon);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.setOpaque(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Perfil clicado!", "Perfil", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(profileButton);

        topbar.add(rightButtonsPanel, BorderLayout.EAST);
        add(topbar, BorderLayout.NORTH);

        // Barra lateral
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0xe5d8fd));
        sidebar.setBounds(0, 0, SIDEBAR_COLLAPSED_WIDTH, 1080);

        ImageIcon sbhomeIcon = new ImageIcon(getClass().getResource("/Sidebar/Home.png"));
        JButton btnInicio = new JButton(sbhomeIcon);
        configurarBotaoSidebar(btnInicio);

        ImageIcon sbprofileIcon = new ImageIcon(getClass().getResource("/Sidebar/Profile.png"));
        JButton btnPerfil = new JButton(sbprofileIcon);
        configurarBotaoSidebar(btnPerfil);

        ImageIcon sbsettingsIcon = new ImageIcon(getClass().getResource("/Sidebar/Settings.png"));
        JButton btnConfig = new JButton(sbsettingsIcon);
        configurarBotaoSidebar(btnConfig);

        ImageIcon sbcreateEventIcn = new ImageIcon(getClass().getResource("/Sidebar/CreateEventSB.png"));
        JButton btnCreateEventSB = new JButton(sbcreateEventIcn);
        configurarBotaoSidebar(btnCreateEventSB);

        ImageIcon sbeditEventIcn = new ImageIcon(getClass().getResource("/Sidebar/EditEventSB.png"));
        JButton btnEditEventSB = new JButton(sbeditEventIcn);
        configurarBotaoSidebar(btnEditEventSB);

        ImageIcon sbconsultEventIcn = new ImageIcon(getClass().getResource("/Sidebar/ConsultEventSB.png"));
        JButton btnConsultEventSB = new JButton(sbconsultEventIcn);
        configurarBotaoSidebar(btnConsultEventSB);

        ImageIcon sbpresenceIcn = new ImageIcon(getClass().getResource("/Sidebar/PresenceSB.png"));
        JButton btnPresenceSB = new JButton(sbpresenceIcn);
        configurarBotaoSidebar(btnPresenceSB);

        ImageIcon sbhelpCenterIcn = new ImageIcon(getClass().getResource("/Sidebar/HelpCenterSB"));
        JButton btnHelpCenterSB = new JButton();
        configurarBotaoSidebar(btnHelpCenterSB);
	
	ImageIcon sbsocialMediaIcn = new ImageIcon(getClass().getResource("/Sidebar/SocialMediaSB.png"));
	JButton btnSocialMediaSB = new JButton(sbsocialMediaIcn);
	configurarBotaoSidebar(btnSocialMediaSB);

        // Separador
        JPanel separator = new JPanel();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        separator.setBackground(new Color(0xCCCCCC)); // cor da linha
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel separator2 = new JPanel();
        separator2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        separator2.setBackground(new Color(0xCCCCCC)); // cor da linha
        separator2.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Itens barra lateral | Está em ordem de cima pra baixo
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(btnInicio);
        sidebar.add(btnPerfil);
        sidebar.add(btnConfig);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(separator);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(btnCreateEventSB);
        sidebar.add(btnEditEventSB);
        sidebar.add(btnConsultEventSB);
        sidebar.add(btnPresenceSB);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(separator2);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(btnHelpCenterSB);
	sidebar.add(btnSocialMediaSB);

        sidebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                expandSidebar();
                btnInicio.setVisible(true);
                btnPerfil.setVisible(true);
                btnConfig.setVisible(true);
                separator.setVisible(true);
                btnCreateEventSB.setVisible(true);
                btnEditEventSB.setVisible(true);
                btnConsultEventSB.setVisible(true);
                btnPresenceSB.setVisible(true);
                separator2.setVisible(true);
                btnHelpCenterSB.setVisible(true);
                btnSocialMediaSB.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    PointerInfo info = MouseInfo.getPointerInfo();
                    Point location = info.getLocation();
                    SwingUtilities.convertPointFromScreen(location, sidebar);
                    if (!sidebar.contains(location)) {
                        collapseSidebar();
                        btnInicio.setVisible(true);
                        btnPerfil.setVisible(true);
                        btnConfig.setVisible(true);
                        separator.setVisible(true);
                        btnCreateEventSB.setVisible(true);
                        btnEditEventSB.setVisible(true);
                        btnConsultEventSB.setVisible(true);
                        btnPresenceSB.setVisible(true);
                        separator2.setVisible(true); 
                        btnHelpCenterSB.setVisible(true);
                        btnSocialMediaSB.setVisible(true);
                    }
                });
            }
        });

        layeredPane.add(sidebar, JLayeredPane.PALETTE_LAYER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                galleryPanel.setBounds(0, 0, width, height);
                sidebar.setBounds(0, 0, sidebar.getWidth(), height);
            }
        });
    }

    private void expandSidebar() {
        sidebar.setBounds(0, 0, SIDEBAR_EXPANDED_WIDTH, getHeight());
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void collapseSidebar() {
        sidebar.setBounds(0, 0, SIDEBAR_COLLAPSED_WIDTH, getHeight());
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void configurarBotaoSidebar(JButton button) {
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setIconTextGap(10);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setForeground(Color.BLACK);
    }
}
    
