package com.github.eventure.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
import com.github.eventure.view.MainFrame;
import com.github.eventure.view.components.CreateEventPanel;
import com.github.eventure.view.components.DisplayEvent;
import com.github.eventure.view.components.EditEventPanel;
import com.github.eventure.view.components.EventPanelEdit;
import com.github.eventure.view.components.ProfilePage;
import com.github.eventure.view.components.DisplayEvent0;

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
        galleryPanel.setLayout(null); // hbox layout 
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
            User user = Session.getLoggedUser();

            if (user == null) {
            JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado."); // esse erro não é para ser exibido!
            return;
        }

        if (user.getCpf() == null) {
          int opcao = JOptionPane.showConfirmDialog(null,
                "Deseja se tornar um organizador?", "Confirmação", JOptionPane.YES_NO_OPTION);

             if (opcao == JOptionPane.YES_OPTION) {
                JTextField cpfField = new JTextField(15);
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Digite seu CPF:"));
                panel.add(cpfField);

                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Cadastro de CPF", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText().trim();
                if (!UserController.getInstance().validateCpf(cpf) || !cpf.isEmpty()) {
                    Session.getLoggedUser().setCpf(cpf);
                    JOptionPane.showMessageDialog(null, "Você agora é um organizador!");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF inválido!");
                    return;
                }
            } else {
                return; // cancelou o cadastro
            }
        } else {
            return; // não quis se tornar organizador
        }
    }

    // Continua para criação do evento
    var createEventPanel = new CreateEventPanel(user);
    showMainPanel(createEventPanel,0);
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
            ProfilePage profilePage = new ProfilePage();
            showMainPanel(profilePage,1);
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
        btnInicio.addActionListener(e -> {
            galleryPanel.removeAll();
            galleryPanel.setLayout(null); // Volta ao estado original se necessário
            ExibirEvents();
            galleryPanel.revalidate();
            galleryPanel.repaint();
        });
        configurarBotaoSidebar(btnInicio);

        ImageIcon sbprofileIcon = new ImageIcon(getClass().getResource("/Sidebar/Profile.png"));
        JButton btnPerfil = new JButton(sbprofileIcon);
        configurarBotaoSidebar(btnPerfil);
        btnPerfil.addActionListener(e -> {
            var profilePage = new ProfilePage();
            showMainPanel(profilePage,1);
        });

        ImageIcon sbsettingsIcon = new ImageIcon(getClass().getResource("/Sidebar/Settings.png"));
        JButton btnConfig = new JButton(sbsettingsIcon);
        configurarBotaoSidebar(btnConfig);
        btnConfig.addActionListener(e ->{
        	// parametro é idDoEventos
        	var s = new DisplayEvent0(1);
        	showMainPanel(s, 1);
        });

        ImageIcon sbcreateEventIcn = new ImageIcon(getClass().getResource("/Sidebar/CreateEventSB.png"));
        JButton btnCreateEventSB = new JButton(sbcreateEventIcn);
        btnCreateEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateEventSB.addActionListener(e -> { 
        	var user = Session.getLoggedUser();
            var createEventPanel = new CreateEventPanel(user);
            showMainPanel(createEventPanel,0);
        });
        configurarBotaoSidebar(btnCreateEventSB);

        ImageIcon sbeditEventIcn = new ImageIcon(getClass().getResource("/Sidebar/EditEventSB.png"));
        JButton btnEditEventSB = new JButton(sbeditEventIcn);
        btnEditEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditEventSB.addActionListener(e -> { 
            var editPanel = new EventPanelEdit(this);
            showMainPanel(editPanel,0);
        });
        configurarBotaoSidebar(btnEditEventSB);

        ImageIcon sbconsultEventIcn = new ImageIcon(getClass().getResource("/Sidebar/ConsultEventSB.png"));
        JButton btnConsultEventSB = new JButton(sbconsultEventIcn);
        configurarBotaoSidebar(btnConsultEventSB);

        ImageIcon sbpresenceIcn = new ImageIcon(getClass().getResource("/Sidebar/PresenceSB.png"));
        JButton btnPresenceSB = new JButton(sbpresenceIcn);
        configurarBotaoSidebar(btnPresenceSB);

        ImageIcon sbhelpCenterIcn = new ImageIcon(getClass().getResource("/Sidebar/HelpCenterSB.png"));
        JButton btnHelpCenterSB = new JButton(sbhelpCenterIcn);
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
    private void configurarBotaoFechar(JButton botao) {
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setOpaque(true);
        botao.setBackground(new Color(0x990000));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("SansSerif", Font.BOLD, 12));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public void showMainPanel(JPanel contentPanel, int modo) {
        galleryPanel.removeAll();

        if (modo == 1) {
            // Modo BoxLayout (como na branch Profile Page)
            galleryPanel.setLayout(new GridBagLayout()); // Centraliza o conteúdo

            JPanel whitePanel = new JPanel(new BorderLayout());
            whitePanel.setBackground(new Color(0xe5d8fd));

            Dimension preferredSize = contentPanel.getPreferredSize();
            int width = preferredSize.width + 60;
            int height = preferredSize.height + 40;
            whitePanel.setPreferredSize(new Dimension(width, height));

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.setOpaque(false);

            JButton closeButton = new JButton("X");
            configurarBotaoFechar(closeButton);

            closeButton.addActionListener(e -> {
                galleryPanel.removeAll();
                galleryPanel.setLayout(null);
                ExibirEvents();
                galleryPanel.revalidate();
                galleryPanel.repaint();
            });

            topPanel.add(closeButton);
            whitePanel.add(topPanel, BorderLayout.NORTH);
            whitePanel.add(contentPanel, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            galleryPanel.add(whitePanel, gbc);
        } else {
            // Modo layout nulo (como na branch Carousel)
            galleryPanel.setLayout(null);

            JPanel whitePanel = contentPanel;
            whitePanel.setBackground(new Color(0xe5d8fd));
            whitePanel.setSize(1130, 590);

            int margemEsquerda = SIDEBAR_COLLAPSED_WIDTH + 30;
            int margemTopo = 30;
            whitePanel.setLocation(margemEsquerda, margemTopo);

            whitePanel.setLayout(null);

            JButton closeButton = new JButton("X");
            configurarBotaoFechar(closeButton);
            closeButton.setBounds(whitePanel.getWidth() - 40, 10, 30, 30);

            closeButton.addActionListener(e -> {
                whitePanel.setVisible(false);
                galleryPanel.repaint();
                galleryPanel.revalidate();
                ExibirEvents();
            });
            whitePanel.add(closeButton);
            galleryPanel.add(whitePanel);
        }

        galleryPanel.revalidate();
        galleryPanel.repaint();
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