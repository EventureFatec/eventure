package com.github.eventure.view.pages;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.GeradorCertificado;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.ImageController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Event;
import com.github.eventure.model.EventClassification;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
import com.github.eventure.model.Visibilidade;
import com.github.eventure.view.MainFrame;
import com.github.eventure.view.components.SettingsPanel;
import com.github.eventure.view.components.ThemeManager;
import com.github.eventure.view.components.AllEventCertificadoPanel;
import com.github.eventure.view.components.CommunityAllPanel;
import com.github.eventure.view.components.CommunityPanel;
import com.github.eventure.view.components.ConfirmarPresencaPanel;
import com.github.eventure.view.components.CreateEventPanel;
import com.github.eventure.view.components.DeleteAllEventPanel;
import com.github.eventure.view.components.DisplayAllEvents;
import com.github.eventure.view.components.DisplayEvent;
import com.github.eventure.view.components.EventPanelEdit;
import com.github.eventure.view.components.EventPanelPresenca;
import com.github.eventure.view.components.EventRequestPanel;
import com.github.eventure.view.components.EventRequestsContainer;
import com.github.eventure.view.components.ProfilePage;
import com.github.eventure.view.components.SettingsPanel;
import com.github.eventure.view.components.ThemeManager;


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
    private int currentPageTodos = 0;
    private int currentPageMeus = 0;
    private final int pageSizeEvent = 3;

    public MainPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        add(layeredPane, BorderLayout.CENTER);

        topbar = new JPanel();
        topbar.setLayout(new BorderLayout());
        topbar.setBackground(new Color(0xe5d8fd));
        topbar.setPreferredSize(new Dimension(0, 48));

        galleryPanel = new JPanel();
        galleryPanel.setLayout(null);
        galleryPanel.setBackground(new Color(0xF2F0F7));
        galleryPanel.setBounds(SIDEBAR_COLLAPSED_WIDTH, 0, frame.getWidth() - SIDEBAR_COLLAPSED_WIDTH,
                frame.getWidth() - topbar.getHeight());
        layeredPane.add(galleryPanel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon icon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        JLabel logo = new JLabel(icon);
        var leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.add(logo);
        topbar.add(leftPanel, BorderLayout.WEST);

        add(topbar, BorderLayout.NORTH);

        ExibirEvents();

        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false); 
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextField searchField = new JTextField("Pesquisar...") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0xBDB2D2));
                int arc = getHeight();
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(180, 180, 180));
                g2.setStroke(new BasicStroke(1));
                int arc = getHeight();
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };

        Dimension tamanhoCampo = new Dimension(550, 40);
        searchField.setOpaque(false);
        searchField.setPreferredSize(tamanhoCampo);
        searchField.setMaximumSize(tamanhoCampo);
        searchField.setMinimumSize(tamanhoCampo);
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Pesquisar...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Pesquisar...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        searchField.addActionListener(e -> {
            String text = searchField.getText();
            if (!text.isEmpty() && !text.equals("Pesquisar...")) {
                List<Event> filteredEvents = EventController.getInstance().findEventsByTitleContaining(text);
                currentPage = 0;
                ExibirEvents(filteredEvents);
            } else {
                currentPage = 0;
                ExibirEvents();
            }
        });

        
        searchPanel.add(Box.createHorizontalGlue()); 
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalGlue());
        topbar.add(searchPanel, BorderLayout.CENTER);

        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);
        JButton createEventButton = new JButton("Criar Evento") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = getHeight();
                g2.setColor(new Color(0x330065)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                g2.setFont(getFont());
                g2.setColor(Color.WHITE);
                g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0x330065)); 
                g2.setStroke(new BasicStroke(1));
                int arc = getHeight();
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };

        createEventButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        createEventButton.setFocusPainted(false);
        createEventButton.setBorderPainted(false);
        createEventButton.setContentAreaFilled(false);
        createEventButton.setOpaque(false);
        createEventButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        createEventButton.addActionListener(e -> {
            User user = Session.getLoggedUser();

            if (user == null) {
                JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado.");                                                               
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
                        if (UserController.getInstance().validateCpf(cpf) && !cpf.isEmpty()) {
                            Session.getLoggedUser().setCpf(cpf);
                            JOptionPane.showMessageDialog(null, "Você agora é um organizador!");
                        } else {
                            JOptionPane.showMessageDialog(null, "CPF inválido!");
                            return;
                        }
                    } else {
                        return; 
                    }
                } else {
                    return; 
                }
            }
            var createEventPanel = new CreateEventPanel(user,this);
            showMainPanel(createEventPanel, 0);
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
            CommunityPanel communityPanel = new CommunityPanel(this);
            showMainPanel(communityPanel, 0);
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
        	var deleteEventPanel = new DeleteAllEventPanel(this);
        	showMainPanel(deleteEventPanel, 0);
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
            ProfilePage profilePage = new ProfilePage(this);
            showMainPanel(profilePage, 1);
        });
        rightButtonsPanel.add(profileButton);

        topbar.add(rightButtonsPanel, BorderLayout.EAST);
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0xe5d8fd));
        sidebar.setBounds(0, 0, SIDEBAR_COLLAPSED_WIDTH, 1080);


        ImageIcon sbhomeIcon = new ImageIcon(getClass().getResource("/Sidebar/HomeButton.png"));
        JButton btnInicio = new JButton(sbhomeIcon);
        btnInicio.addActionListener(e -> {
            galleryPanel.removeAll();
            galleryPanel.setLayout(null); 
            ExibirEvents();
            galleryPanel.revalidate();
            galleryPanel.repaint();
        });
        configurarBotaoSidebar(btnInicio);

        ImageIcon sbprofileIcon = new ImageIcon(getClass().getResource("/Sidebar/Profile.png"));
        JButton btnPerfil = new JButton(sbprofileIcon);
        configurarBotaoSidebar(btnPerfil);
        btnPerfil.addActionListener(e -> {
            var profilePage = new ProfilePage(this);
            showMainPanel(profilePage, 1);
        });

        ImageIcon sbsettingsIcon = new ImageIcon(getClass().getResource("/Sidebar/Settings.png"));
        JButton btnConfig = new JButton(sbsettingsIcon);
        configurarBotaoSidebar(btnConfig);
        btnConfig.addActionListener(e -> showSettingsDialog());

        ImageIcon sbcreateEventIcn = new ImageIcon(getClass().getResource("/Sidebar/CreateEventSB.png"));
        JButton btnCreateEventSB = new JButton(sbcreateEventIcn);
        btnCreateEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateEventSB.addActionListener(e -> {
        	  User user = Session.getLoggedUser();

              if (user == null) {
                  JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado.");
                                                                                       
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
                          if (UserController.getInstance().validateCpf(cpf) && !cpf.isEmpty()) {
                              Session.getLoggedUser().setCpf(cpf);
                              JOptionPane.showMessageDialog(null, "Você agora é um organizador!");
                          } else {
                              JOptionPane.showMessageDialog(null, "CPF inválido!");
                              return;
                          }
                      } else {
                          return; 
                      }
                  } else {
                      return;
                  }
              }
              var createEventPanel = new CreateEventPanel(user,this);
              showMainPanel(createEventPanel, 0);
        });
        configurarBotaoSidebar(btnCreateEventSB);

        ImageIcon sbeditEventIcn = new ImageIcon(getClass().getResource("/Sidebar/EditEventSB.png"));
        JButton btnEditEventSB = new JButton(sbeditEventIcn);
        btnEditEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditEventSB.addActionListener(e -> {
        	var userController = UserController.getInstance();
        	User user = userController.findUserById(IdController.getIdUser());
        	if(user.getMyEventsList().size() == 0)
        	{
        		int result = JOptionPane.showConfirmDialog(null, "Você não tem nenhum evento deseja criar um ?","Confirmação",JOptionPane.YES_NO_OPTION);
        		if(result == JOptionPane.YES_OPTION)
        		{
        			btnCreateEventSB.doClick();
        		}else
        		{
        			return;
        		}
        	}else {
            var editPanel = new EventPanelEdit(this);
            showMainPanel(editPanel, 0);
        	}
        });
        configurarBotaoSidebar(btnEditEventSB);
        
        ImageIcon sbDeleteEventIcn = new ImageIcon(getClass().getResource("/Sidebar/excluireventos.png"));
        JButton btnDeleteEventSB = new JButton(sbDeleteEventIcn);
        btnDeleteEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeleteEventSB.addActionListener(e -> {
        	var userController = UserController.getInstance();
        	User user = userController.findUserById(IdController.getIdUser());
        	if(user.getMyEventsList().size() == 0)
        	{
        		int result = JOptionPane.showConfirmDialog(null, "Você não tem nenhum evento deseja criar um ?","Confirmação",JOptionPane.YES_NO_OPTION);
        		if(result == JOptionPane.YES_OPTION)
        		{
        			btnCreateEventSB.doClick();
        		}else
        		{
        			return;
        		}
        	}else {
            var deletePanel = new DeleteAllEventPanel(this);
            showMainPanel(deletePanel, 0);
        	}
        });
        configurarBotaoSidebar(btnDeleteEventSB);
        
        ImageIcon sbTodosEventos = new ImageIcon(getClass().getResource("/Sidebar/todosEventos1.png"));
        JButton sbBtnTodosEventos = new JButton(sbTodosEventos);
        configurarBotaoSidebar(sbBtnTodosEventos);
        sbBtnTodosEventos.addActionListener(e ->{
        	var Allevent = new DisplayAllEvents(this);
            showMainPanel(Allevent, 0);
        });

        

        ImageIcon sbconsultEventIcn = new ImageIcon(getClass().getResource("/Sidebar/ConsultEventSB.png"));
        JButton btnConsultEventSB = new JButton(sbconsultEventIcn);
        configurarBotaoSidebar(btnConsultEventSB);
        btnConsultEventSB.addActionListener(e -> {
        	var userController = UserController.getInstance();
        	User user = userController.findUserById(IdController.getIdUser());
        	if(user.getMyEventsList().size() == 0)
        	{
        		int result = JOptionPane.showConfirmDialog(null, "Você não tem nenhum evento para consultar deseja criar um ?","Confirmação",JOptionPane.YES_NO_OPTION);
        		if(result == JOptionPane.YES_OPTION)
        		{
        			btnCreateEventSB.doClick();
        		}else
        		{
        			return;
        		}
        	}else {
        	var eventPanelForConsult = new EventPanelForConsult(this);
        	showMainPanel(eventPanelForConsult, 0);
        	}
        });

        ImageIcon sbpresenceIcn = new ImageIcon(getClass().getResource("/Sidebar/PresenceSB.png"));
        JButton btnPresenceSB = new JButton(sbpresenceIcn);
        configurarBotaoSidebar(btnPresenceSB);
        btnPresenceSB.addActionListener(e -> {
        	var userController = UserController.getInstance();
        	User user = userController.findUserById(IdController.getIdUser());
        	if(user.getMyEventsList().size() == 0)
        	{
        		int result = JOptionPane.showConfirmDialog(null, "Você não tem nenhum evento deseja criar um ?","Confirmação",JOptionPane.YES_NO_OPTION);
        		if(result == JOptionPane.YES_OPTION)
        		{
        			btnCreateEventSB.doClick();
        		}else
        		{
        			return;
        		}
        	 }else
        	  {
        		var eventPanelPresenca = new EventPanelPresenca(this);
            	showMainPanel(eventPanelPresenca, 0);
        	  }
        });
        
        ImageIcon sbSolicitacoes = new ImageIcon(getClass().getResource("/Sidebar/solicitacoes.png"));
        JButton sbBtnSolicitacoes = new JButton(sbSolicitacoes);
        configurarBotaoSidebar(sbBtnSolicitacoes);
        sbBtnSolicitacoes.addActionListener(e ->{
        	var eventController = EventController.getInstance();
        	if(!eventController.haEventosPrivadosComRequisicoesPendentes(IdController.getIdUser()))
        	{
                JOptionPane.showMessageDialog(null, "Nenhuma requisição no momento");
        	}else
        	 {
                EventRequestsContainer erc = new EventRequestsContainer();
                showMainPanel(erc, 1);	
        	 }
        });

        ImageIcon sbComunidades = new ImageIcon(getClass().getResource("/Sidebar/Comunidades.png"));
        JButton btnComunidades = new JButton(sbComunidades);
        configurarBotaoSidebar(btnComunidades);
        btnComunidades.addActionListener(e ->{
        	CommunityAllPanel communityAllPanel = new CommunityAllPanel();
        	showMainPanel(communityAllPanel, 1);
        });
        
        ImageIcon sbEmitirCertificado = new ImageIcon(getClass().getResource("/Sidebar/emitircertificado.png"));
        JButton btnEmitirCertificado = new JButton(sbEmitirCertificado);
        configurarBotaoSidebar(btnEmitirCertificado);
        btnEmitirCertificado.addActionListener(e -> {
        	var userController = UserController.getInstance();
        	var user = userController.findUserById(IdController.getIdUser());
        	if(user.getParticipaoConfirmada().size() > 0)
        	{
        	var allEventCerticado = new AllEventCertificadoPanel(this);
        	showMainPanel(allEventCerticado, 0);
        	}else
        	{
        		JOptionPane.showMessageDialog(null,"Nenhum certificado para ser emitido");
        	}
        });

        ImageIcon sbSair = new ImageIcon(getClass().getResource("/Sidebar/sair.png"));
        JButton btnSair = new JButton(sbSair);
        configurarBotaoSidebar(btnSair);
        btnSair.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Deseja sair da conta?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Session.logout();
                Container parent = this.getParent();
                if (frame instanceof MainFrame mainFrame) {
                    mainFrame.showPanel("welcome");
                }
                if (this != null) {
                    this.closePanel();
                }
            }
        });

        
        JPanel separator = new JPanel();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        separator.setBackground(new Color(0xCCCCCC));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel separator2 = new JPanel();
        separator2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        separator2.setBackground(new Color(0xCCCCCC));
        separator2.setAlignmentX(Component.LEFT_ALIGNMENT);

        
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
        sidebar.add(btnDeleteEventSB);
        sidebar.add(sbBtnTodosEventos);
        sidebar.add(btnPresenceSB);
        sidebar.add(sbBtnSolicitacoes);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(separator2);
        sidebar.add(Box.createVerticalStrut(12));
        sidebar.add(btnComunidades);
        sidebar.add(sbBtnSolicitacoes);
        sidebar.add(btnEmitirCertificado);
        sidebar.add(btnSair);

        
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
                btnDeleteEventSB.setVisible(true);
                sbBtnTodosEventos.setVisible(true);
                btnPresenceSB.setVisible(true);
                sbBtnSolicitacoes.setVisible(true);
                separator2.setVisible(true);
                btnComunidades.setVisible(true);
                btnEmitirCertificado.setVisible(true);
                btnSair.setVisible(true);
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
                        btnDeleteEventSB.setVisible(true);
                        sbBtnTodosEventos.setVisible(true);
                        btnPresenceSB.setVisible(true);
                        sbBtnSolicitacoes.setVisible(true);
                        separator2.setVisible(true);
                        btnComunidades.setVisible(true);
                        btnEmitirCertificado.setVisible(true);
                        btnSair.setVisible(true);
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
            galleryPanel.setLayout(new GridBagLayout());

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
    public void ExibirEvents()
    {
    	removerLabels();
    	ExibirEventsTodos();
    	ExibirMeusEventos();
    }

    public void ExibirEventsTodos() {
        var events = EventController.getInstance().getAllEvents();
        ExibirEvents(events);
    }

    public void ExibirEvents(List<Event> events) {
       
        for (Component comp : galleryPanel.getComponents()) {
            if ("eventoGeral".equals(comp.getName())) {
                galleryPanel.remove(comp);
            }
        }

        int start = currentPageTodos * pageSize;
        int end = Math.min(start + pageSize, events.size());
        int x = 160;
        int y = 60;

        for (int i = start; i < end; i++) {
            var event = events.get(i);
            EventPanel panel = new EventPanel(
                    event.getTitle(),
                    event.getAddress().getEstado() + ", " + event.getAddress().getCidade(),
                    event.getDate(),
                    event.getImagePath(),
                    event.getId(),
                    this);
            panel.setBounds(x, y, 300, 240);
            panel.setName("eventoGeral");
            galleryPanel.add(panel);
            x += 400;
        }
        JLabel todosEventos = new JLabel("Todos Eventos");
        todosEventos.setFont(new Font("SansSerif", Font.BOLD, 20));
        todosEventos.setBounds(160, 20, 300, 30);
        todosEventos.setForeground(new Color(0x2e1a47));
        todosEventos.setName("labelTodos");
        galleryPanel.add(todosEventos);
        
        JButton btnPrev = new JButton("");
        ImageIcon icon = new ImageIcon(getClass().getResource("/setacinza.png"));
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnPrev.setIcon(new ImageIcon(scaled));
        btnPrev.setBounds(80, 130, 40, 40);
        btnPrev.setBorder(null);
        btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrev.setEnabled(currentPageTodos > 0);
        btnPrev.addActionListener(e -> {
            if (currentPageTodos > 0) {
                currentPageTodos--;
                ExibirEvents(events);
            }
        });
        btnPrev.setName("eventoGeral");
        galleryPanel.add(btnPrev);

        JButton btnNext = new JButton("");
        btnNext.setBounds(1290, 130, 40, 40);
        ImageIcon icon02 = new ImageIcon(getClass().getResource("/setacinzaPos.png"));
        Image scaled02 = icon02.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnNext.setIcon(new ImageIcon(scaled02));
        btnNext.setBorder(null);
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setEnabled((currentPageTodos + 1) * pageSize < events.size());
        btnNext.addActionListener(e -> {
            currentPageTodos++;
            ExibirEvents(events);
        });
        btnNext.setName("eventoGeral");
        galleryPanel.add(btnNext);

        galleryPanel.revalidate();
        galleryPanel.repaint();
    }
    public void ExibirMeusEventos() {
        int idUser = IdController.getIdUser();
        User user = UserController.getInstance().findUserById(idUser);
        List<Integer> idEventos = user.getEventsList();

        List<Event> todosEventos = EventController.getInstance().getAllEvents();
        List<Event> meusEventos = new ArrayList<>();

        for (Event evento : todosEventos) {
            if (idEventos.contains(evento.getId())) {
                meusEventos.add(evento);
            }
        }

        ExibirMeusEventos(meusEventos);
    }

    public void ExibirMeusEventos(List<Event> events) {
        for (Component comp : galleryPanel.getComponents()) {
            if ("meuEvento".equals(comp.getName())) {
                galleryPanel.remove(comp);
            }
        }

        int start = currentPageMeus * pageSize;
        int end = Math.min(start + pageSize, events.size());
        int x = 160;
        int y = 390; 

        for (int i = start; i < end; i++) {
            var event = events.get(i);
            EventPanel panel = new EventPanel(
                    event.getTitle(),
                    event.getAddress().getEstado() + ", " + event.getAddress().getCidade(),
                    event.getDate(),
                    event.getImagePath(),
                    event.getId(),
                    this);
            panel.setBounds(x, y, 300, 240);
            panel.setName("meuEvento");
            galleryPanel.add(panel);
            x += 400;
        }
        
        JLabel meusEventos = new JLabel("Meus Eventos");
        meusEventos.setFont(new Font("SansSerif", Font.BOLD, 20));
        meusEventos.setBounds(160, 350, 300, 30);
        meusEventos.setForeground(new Color(0x2e1a47));
        meusEventos.setName("labelMeus");
        galleryPanel.add(meusEventos);

        JButton btnPrev = new JButton("");
        ImageIcon icon = new ImageIcon(getClass().getResource("/setacinza.png"));
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnPrev.setIcon(new ImageIcon(scaled));
        btnPrev.setBounds(80, y + 100, 40, 40);
        btnPrev.setBorder(null);
        btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrev.setEnabled(currentPageMeus > 0);
        btnPrev.addActionListener(e -> {
            if (currentPageMeus > 0) {
                currentPageMeus--;
                ExibirMeusEventos(events);
            }
        });
        btnPrev.setName("meuEvento");
        galleryPanel.add(btnPrev);

        JButton btnNext = new JButton("");
        btnNext.setBounds(1290, y + 100, 40, 40);
        ImageIcon icon02 = new ImageIcon(getClass().getResource("/setacinzaPos.png"));
        Image scaled02 = icon02.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnNext.setIcon(new ImageIcon(scaled02));
        btnNext.setBorder(null);
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setEnabled((currentPageMeus + 1) * pageSize < events.size());
        btnNext.addActionListener(e -> {
            currentPageMeus++;
            ExibirMeusEventos(events);
        });
        btnNext.setName("meuEvento");
        galleryPanel.add(btnNext);

        galleryPanel.revalidate();
        galleryPanel.repaint();
    }
    public void closePanel() {
        galleryPanel.removeAll();    
        galleryPanel.setLayout(null);  
        ExibirEvents();                
        galleryPanel.revalidate();
        galleryPanel.repaint();
    }
    private void removerLabels() {
        for (Component comp : galleryPanel.getComponents()) {
            String name = comp.getName();
            if (name != null && (name.equals("labelTodos") || name.equals("labelMeus"))) {
                galleryPanel.remove(comp);
            }
        }
    }
    private void showSettingsDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Configurações", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        SettingsPanel settingsPanel = new SettingsPanel(() -> {
            applyTheme();
            dialog.dispose();
        });

        dialog.setContentPane(settingsPanel);
        dialog.setVisible(true);
    }

    private void applyTheme() {
        Color bg = ThemeManager.getBackgroundColor();

        galleryPanel.setBackground(bg);
        topbar.setBackground(new Color(0x9F96B0));
        sidebar.setBackground(new Color(0x9F96B0));
    }

}