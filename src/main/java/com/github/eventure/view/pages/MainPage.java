package com.github.eventure.view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.view.MainFrame;
import com.github.eventure.view.components.CreateEventPanel;
import com.github.eventure.view.components.DisplayEvent;
import com.github.eventure.view.components.EditEventPanel;
import com.github.eventure.view.components.EventPanelEdit;

public class MainPage extends JPanel {

    private MainFrame frame;
    private int currentPage = 0;
    private final int pageSize = 3;
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
        galleryPanel.setBounds(SIDEBAR_COLLAPSED_WIDTH, 0, frame.getWidth() - SIDEBAR_COLLAPSED_WIDTH, frame.getWidth() - topbar.getHeight());
        layeredPane.add(galleryPanel, JLayeredPane.DEFAULT_LAYER);
        
        // Logo à esquerda
        ImageIcon icon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        JLabel logo = new JLabel(icon);
        topbar.add(logo, BorderLayout.WEST);

        add(topbar, BorderLayout.NORTH);
        // metodo para exibir os eventos na tela
        
//        ImageIcon iconSeta = new ImageIcon(getClass().getResource("/seta.png"));
//        JButton btnPrev = new JButton("Anterior");
//        btnPrev.setIcon(iconSeta);
//        btnPrev.setBounds(160, 300, 40, 40);
//        btnPrev.setEnabled((currentPage + 1) * pageSize < EventController.getInstance().getAllEvents().size());
//        btnPrev.addActionListener(e -> {
//            currentPage++;
//            ExibirEvents();
//        });
//        galleryPanel.add(btnPrev);
//        JButton btnNext = new JButton("Próximo");
//        btnNext.setBounds(580, 300, 120, 30);
//        btnNext.setEnabled((currentPage + 1) * pageSize < EventController.getInstance().getAllEvents().size());
//        btnNext.addActionListener(e -> {
//            currentPage++;
//            ExibirEvents();
//        });
//        galleryPanel.add(btnNext);

        ExibirEvents();
        
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
            var createEventPanel = new CreateEventPanel(); 
            showMainPanel(createEventPanel);
        });
        rightButtonsPanel.add(createEventButton);

        // Botão Chat
        ImageIcon chatIcon = new ImageIcon(getClass().getResource("/ChatButton.png"));
        JButton chatButton = new JButton(chatIcon);
        chatButton.setContentAreaFilled(false); 
        chatButton.setBorderPainted(false);     
        chatButton.setFocusPainted(false);      
        chatButton.setOpaque(false);
        chatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));            
        chatButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Botão Chat clicado!", "Chat", JOptionPane.INFORMATION_MESSAGE);
            ExibirEvents();
        });
        rightButtonsPanel.add(chatButton);

        // Botão Notificação
        ImageIcon bellIcon = new ImageIcon(getClass().getResource("/BellButton.png"));
        JButton bellButton = new JButton(bellIcon);
        bellButton.setContentAreaFilled(false); 
        bellButton.setBorderPainted(false);     
        bellButton.setFocusPainted(false);      
        bellButton.setOpaque(false);
        bellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));   
        bellButton.addActionListener(e ->{
        	var evt = EventController.getInstance();
        	var eventClassification = EventClassification.GASTRONOMY;

        	
        	var img = new ImageController();
        	String path = img.selecionarImagem();
        	evt.createEvent(0,"feira de gastronomia" , "uma feira cheia de delicias" , eventClassification , "20/02/2021","15:30","16:00",path,"12760000","São paulo","lavrinhas","capela do jacu" , "geraldo nogueira de sa", "100" , "casa");
        	ExibirEvents();
        });
        
        rightButtonsPanel.add(bellButton);

        // Botão Perfil
        ImageIcon profileIcon = new ImageIcon(getClass().getResource("/UserButton.png"));
        JButton profileButton = new JButton(profileIcon);
        profileButton.setContentAreaFilled(false); 
        profileButton.setBorderPainted(false);     
        profileButton.setFocusPainted(false);      
        profileButton.setOpaque(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));            
        profileButton.addActionListener(e -> {
            showMainPanel(new JPanel());
        });
        rightButtonsPanel.add(profileButton);

        topbar.add(rightButtonsPanel, BorderLayout.EAST);
        // Barra lateral
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0xe5d8fd));
        sidebar.setBounds(0, 0, SIDEBAR_COLLAPSED_WIDTH, 1080);

        // Botões da barra lateral

        ImageIcon sbhomeIcon = new ImageIcon(getClass().getResource("/Sidebar/HomeButton.png"));
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
        btnCreateEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateEventSB.addActionListener(e -> { 
            var createEventPanel = new CreateEventPanel();
            showMainPanel(createEventPanel);
        });
        configurarBotaoSidebar(btnCreateEventSB);

        ImageIcon sbeditEventIcn = new ImageIcon(getClass().getResource("/Sidebar/EditEventSB.png"));
        JButton btnEditEventSB = new JButton(sbeditEventIcn);
        btnEditEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditEventSB.addActionListener(e -> { 
            var editPanel = new EventPanelEdit(this);
            showMainPanel(editPanel);
        });
        configurarBotaoSidebar(btnEditEventSB);

        ImageIcon sbconsultEventIcn = new ImageIcon(getClass().getResource("/Sidebar/ConsultEventSB.png"));
        JButton btnConsultEventSB = new JButton(sbconsultEventIcn);
        configurarBotaoSidebar(btnConsultEventSB);

        ImageIcon sbpresenceIcn = new ImageIcon(getClass().getResource("/Sidebar/PresenceSB.png"));
        JButton btnPresenceSB = new JButton(sbpresenceIcn);
        configurarBotaoSidebar(btnPresenceSB);

        ImageIcon sbhelpCenterIcn = new ImageIcon(getClass().getResource("/Sidebar/HelpCenterSB.png"));
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

        // Mouse Listener do Sidebar (Irá ler a posição do mouse)
        sidebar.addMouseListener(new MouseAdapter() {
            @Override
            // Ao passar o mouse no sidebar = expandir
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

            // Ao tirar o mouse de cima do sidebar = colapsar
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

    public void showMainPanel(JPanel x) {
        galleryPanel.removeAll();

        JPanel whitePanel = x;
        whitePanel.setBackground(new Color(0xe5d8fd));
        whitePanel.setSize(1130, 590); // Tamanho do retângulo

        // Define margens fixas a partir da esquerda e do topo
        int margemEsquerda = SIDEBAR_COLLAPSED_WIDTH + 30;
        int margemTopo = 30;
        whitePanel.setLocation(margemEsquerda, margemTopo);

        // Botão de fechar
        JButton closeButton = new JButton("X");
        closeButton.setPreferredSize(new Dimension(30, 30));
        closeButton.setBackground(new Color(0xe5d8fd));
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
        closeButton.setForeground(Color.RED);

        // Posiciona o botão no canto superior direito do whitePanel
        closeButton.setBounds(whitePanel.getWidth() - 40, 10, 30, 30);

        closeButton.addActionListener(e -> {
            // Ação para fechar ou ocultar o painel
            whitePanel.setVisible(false);  // Ou pode usar galleryPanel.remove(whitePanel);
            galleryPanel.repaint();
            galleryPanel.revalidate();
            ExibirEvents();
        });

        // Adiciona o botão de fechar no painel branco
        whitePanel.setLayout(null);
        whitePanel.add(closeButton);

        // Adiciona o whitePanel à galleryPanel
        galleryPanel.setLayout(null);
        galleryPanel.add(whitePanel);
        galleryPanel.repaint();
        galleryPanel.revalidate();
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
        button.addActionListener(e -> showMainPanel(new JPanel()));
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
    public void ExibirEvents() {
        var evt = EventController.getInstance();
        var events = evt.getAllEvents();

        galleryPanel.removeAll();
        
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, events.size());
        int x = 160;
        int y = 30;

        for (int i = start; i < end; i++) {
            var event = events.get(i);
            EventPanel panel = new EventPanel(
                event.getTitle(),
                event.getAddress().getEstado() + ", " + event.getAddress().getCidade(),
                event.getDate(),
                event.getImagePath(),
                event.getId(),
                this
            );
            panel.setBounds(x, y, 300, 240);
            galleryPanel.add(panel);
            x += 400;
        }

        // Botão Anterior
        JButton btnPrev = new JButton("");
        ImageIcon icon = new ImageIcon(getClass().getResource("/setaAnterior.png"));
//        ImageIcon icon = new ImageIcon("C:/Users/User/Downloads/setaAnterior.png");
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnPrev.setIcon(new ImageIcon(scaled));
        btnPrev.setBounds(80, 130, 40, 40);
//        btnPrev.setBorder(null);
        btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrev.setEnabled(currentPage > 0);
        btnPrev.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                ExibirEvents();
            }
        });
        galleryPanel.add(btnPrev);
//
//        // Botão Próximo
        JButton btnNext = new JButton("");
        btnNext.setBounds(1290, 130, 40, 40);
        ImageIcon icon02 = new ImageIcon(getClass().getResource("/setaProxima.png"));
//      ImageIcon icon = new ImageIcon("C:/Users/User/Downloads/setaAnterior.png");
        Image scaled02 = icon02.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnNext.setIcon(new ImageIcon(scaled02));
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setEnabled((currentPage + 1) * pageSize < events.size());
        btnNext.addActionListener(e -> {
            currentPage++;
            ExibirEvents();
        });
        galleryPanel.add(btnNext);

        galleryPanel.revalidate();
        galleryPanel.repaint();
    }
}