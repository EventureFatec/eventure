package com.github.eventure.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.github.eventure.controllers.ImageController;
import com.github.eventure.view.MainFrame;

public class MainPage extends JPanel {

    private MainFrame frame;

    private JPanel sidebar;
    private JPanel topbar;
    private JPanel galleryPanel;
    private JLayeredPane layeredPane;

    private final int SIDEBAR_COLLAPSED_WIDTH = 45;
    private final int SIDEBAR_EXPANDED_WIDTH = 300;

    public MainPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        // setPreferredSize(1280, 720);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        add(layeredPane, BorderLayout.CENTER);

        // Barra superior
        topbar = new JPanel();
        topbar.setLayout(new BorderLayout());
        topbar.setBackground(new Color(0xe5d8fd));
        topbar.setPreferredSize(new Dimension(0, 48)); // Define a altura da barra superior

        // Galeria (plano de fundo)
        galleryPanel = new JPanel();
        galleryPanel.setLayout(null);
        galleryPanel.setBackground(new Color(0x330065));

        // Inicialmente cobre toda a tela
        galleryPanel.setBounds(SIDEBAR_COLLAPSED_WIDTH, 0, frame.getWidth() - SIDEBAR_COLLAPSED_WIDTH, frame.getWidth() - topbar.getHeight());
        layeredPane.add(galleryPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Logo à esquerda
        ImageIcon icon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        JLabel logo = new JLabel(icon);
        topbar.add(logo, BorderLayout.WEST);

        add(topbar, BorderLayout.NORTH);

        // Botões da barra superior à direita
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);

        // Botão Criar Evento
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

        // Botão Chat
        ImageIcon chatIcon = new ImageIcon(getClass().getResource("/ChatButton.png"));
        JButton chatButton = new JButton(chatIcon);
        chatButton.setContentAreaFilled(false); 
        chatButton.setBorderPainted(false);     
        chatButton.setFocusPainted(false);      
        chatButton.setOpaque(false);            
        chatButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Chat clicado!", "Chat", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(chatButton);

        // Botão Notificação
        ImageIcon bellIcon = new ImageIcon(getClass().getResource("/BellButton.png"));
        JButton bellButton = new JButton(bellIcon);
        bellButton.setContentAreaFilled(false); 
        bellButton.setBorderPainted(false);     
        bellButton.setFocusPainted(false);      
        bellButton.setOpaque(false);            
        bellButton.addActionListener(e ->{
            System.out.println("clicado bell");
            var i = new ImageController();
            String imagePath = i.selecionarImagem();
            String titulo = "evento do google";
            String locale = "sao paulo bairro da paz";
            String date = "19:20";
            var e2 = new EventPanel(titulo,locale,date,imagePath);
            galleryPanel.add(e2, JLayeredPane.POPUP_LAYER);
            e2.setBounds(SIDEBAR_COLLAPSED_WIDTH + 20, 20, 300, 240);
            e2.revalidate();
            e2.repaint();
            titulo = "test";
            locale = "rio de janeiro";
            date = "22:30";
            String imagePath2 = i.selecionarImagem();
            var e3 = new EventPanel(titulo,locale,date,imagePath2);
            galleryPanel.add(e3, JLayeredPane.POPUP_LAYER);
            e3.setBounds(90+300, 20, 300, 240);
            e3.revalidate();
            e3.repaint();
            
        });
        rightButtonsPanel.add(bellButton);

        // Botão Perfil
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/UserButton.png"));
        JButton profileButton = new JButton(profileIcon);
        profileButton.setContentAreaFilled(false); 
        profileButton.setBorderPainted(false);     
        profileButton.setFocusPainted(false);      
        profileButton.setOpaque(false);            
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Perfil clicado!", "Perfil", JOptionPane.INFORMATION_MESSAGE);
        });
        rightButtonsPanel.add(profileButton);

        topbar.add(rightButtonsPanel, BorderLayout.EAST);
        // Barra lateral
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0xe5d8fd));
        sidebar.setBounds(0, 0, SIDEBAR_COLLAPSED_WIDTH, 1080);

        // Botões da barra lateral

        // Botão "Início"
        ImageIcon sbhomeIcon = new ImageIcon(getClass().getResource("/Home.png")); 
        JButton btnInicio = new JButton(sbhomeIcon);
        
        btnInicio.setHorizontalAlignment(SwingConstants.LEFT);       // Texto e ícone alinhados à esquerda
        btnInicio.setAlignmentX(Component.LEFT_ALIGNMENT);           // Alinha o botão à esquerda dentro do BoxLayout
        btnInicio.setIconTextGap(10);                                // Espaço entre ícone e texto
        btnInicio.setOpaque(false);                                  // Torna o fundo transparente
        btnInicio.setContentAreaFilled(false);                       // Remove preenchimento padrão
        btnInicio.setBorderPainted(false);                           // Remove a borda
        btnInicio.setFocusPainted(false);                            // Remove o destaque de foco
        btnInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));         // Mostra cursor de mão ao passar
        btnInicio.setFont(new Font("SansSerif", Font.PLAIN, 14));    // Fonte personalizada (opcional)
        btnInicio.setForeground(Color.BLACK);                        // Cor do texto
        
        ImageIcon sbprofileIcon = new ImageIcon(getClass().getResource("/Profile.png"));
        JButton btnPerfil = new JButton(sbprofileIcon);
    
        btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);       // Texto e ícone alinhados à esquerda
        btnPerfil.setAlignmentX(Component.LEFT_ALIGNMENT);           // Alinha o botão à esquerda dentro do BoxLayout
        btnPerfil.setIconTextGap(10);                                // Espaço entre ícone e texto
        btnPerfil.setOpaque(false);                                  // Torna o fundo transparente
        btnPerfil.setContentAreaFilled(false);                       // Remove preenchimento padrão
        btnPerfil.setBorderPainted(false);                           // Remove a borda
        btnPerfil.setFocusPainted(false);                            // Remove o destaque de foco
        btnPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));         // Mostra cursor de mão ao passar
        btnPerfil.setFont(new Font("SansSerif", Font.PLAIN, 14));    // Fonte personalizada (opcional)
        btnPerfil.setForeground(Color.BLACK);                        // Cor do texto
        
        // Botão "Configurações"
        ImageIcon sbsettingsIcon = new ImageIcon(getClass().getResource("/Settings.png"));
        JButton btnConfig = new JButton(sbsettingsIcon);
        
        btnConfig.setHorizontalAlignment(SwingConstants.LEFT);       // Texto e ícone alinhados à esquerda
        btnConfig.setAlignmentX(Component.LEFT_ALIGNMENT);           // Alinha o botão à esquerda dentro do BoxLayout
        btnConfig.setIconTextGap(10);                                // Espaço entre ícone e texto
        btnConfig.setOpaque(false);                                  // Torna o fundo transparente
        btnConfig.setContentAreaFilled(false);                       // Remove preenchimento padrão
        btnConfig.setBorderPainted(false);                           // Remove a borda
        btnConfig.setFocusPainted(false);                            // Remove o destaque de foco
        btnConfig.setCursor(new Cursor(Cursor.HAND_CURSOR));         // Mostra cursor de mão ao passar
        btnConfig.setFont(new Font("SansSerif", Font.PLAIN, 14));    // Fonte personalizada (opcional)
        btnConfig.setForeground(Color.BLACK);                        // Cor do texto

        sidebar.add(Box.createVerticalStrut(8)); // Espaço acima, se quiser
        sidebar.add(btnInicio);
        sidebar.add(btnPerfil);
        sidebar.add(btnConfig);

        // Mouse Listener do Sidebar (Irá ler a posição do mouse)
        sidebar.addMouseListener(new MouseAdapter() {
            @Override
            // Ao passar o mouse no sidebar = expandir
            public void mouseEntered(MouseEvent e) {
                expandSidebar();
                btnInicio.setVisible(true);
                btnPerfil.setVisible(true);
                btnConfig.setVisible(true);
            }

            // Ao tirar o mouse de cima do sidebar = colapsar
            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    PointerInfo info = MouseInfo.getPointerInfo();
                    Point location = info.getLocation();
                    SwingUtilities.convertPointFromScreen(location, sidebar);
                    if (!sidebar.contains(location)) {
                        collapseSidebar();
                        btnInicio.setVisible(false);
                        btnPerfil.setVisible(false);
                        btnConfig.setVisible(false);
                    }
                });
            }
        });

        layeredPane.add(sidebar, JLayeredPane.PALETTE_LAYER);

        // Ajusta dinamicamente ao redimensionar
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
}