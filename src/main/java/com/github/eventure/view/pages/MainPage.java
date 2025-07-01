package com.github.eventure.view.pages;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import com.github.eventure.view.components.CommunityPanel;
import com.github.eventure.view.components.ConfirmarPresencaPanel;
import com.github.eventure.view.components.CreateEventPanel;
import com.github.eventure.view.components.DisplayEvent;
import com.github.eventure.view.components.EventPanelEdit;
import com.github.eventure.view.components.EventPanelPresenca;
import com.github.eventure.view.components.EventRequestPanel;
import com.github.eventure.view.components.EventRequestsContainer;
import com.github.eventure.view.components.ProfilePage;


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
        galleryPanel.setLayout(null); // hbox layout #F8F8F8
//        galleryPanel.setBackground(new Color(0x330065)); // roxo
        galleryPanel.setBackground(new Color(0xF2F0F7));
//        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setBounds(SIDEBAR_COLLAPSED_WIDTH, 0, frame.getWidth() - SIDEBAR_COLLAPSED_WIDTH,
                frame.getWidth() - topbar.getHeight());
        layeredPane.add(galleryPanel, JLayeredPane.DEFAULT_LAYER);

        // Logo Topbar
        ImageIcon icon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        JLabel logo = new JLabel(icon);
        var leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.add(logo);
        topbar.add(leftPanel, BorderLayout.WEST);

        add(topbar, BorderLayout.NORTH);

        ExibirEvents();

        // Barra de pesquisa
        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false); 
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Campo de pesquisa
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

        // Placeholder e ação
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

        // Botões da barra superior à direita
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);
        // Botão Criar Evento
        JButton createEventButton = new JButton("Criar Evento") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = getHeight();

                // Fundo personalizado
                g2.setColor(new Color(0x330065)); // FUNDO desejado (roxo escuro, por exemplo)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Texto centralizado
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
                JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado."); // esse erro não é para ser
                                                                                     // exibido!
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
                        return; // cancelou o cadastro
                    }
                } else {
                    return; // não quis se tornar organizador
                }
            }

            // Continua para criação do evento
            var createEventPanel = new CreateEventPanel(user,this);
            showMainPanel(createEventPanel, 0);
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
            CommunityPanel communityPanel = new CommunityPanel(this);
            showMainPanel(communityPanel, 0);
           // ExibirEvents();
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
        bellButton.addActionListener(e -> {
            var evt = EventController.getInstance();
            var eventClassification = EventClassification.GASTRONOMY;

            var img = new ImageController();
            String path = img.selecionarImagem();
            evt.createEvent(0, "feira de gastronomia", "uma feira cheia de delicias", eventClassification, "20/02/2021","21/03/2022",
                    "15:30", "16:00", path, "12760000", "São paulo", "lavrinhas", "capela do jacu",
                    "geraldo nogueira de sa", "100", "casa",Visibilidade.PRIVADO);
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
            ProfilePage profilePage = new ProfilePage(this);
            showMainPanel(profilePage, 1);
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
                  JOptionPane.showMessageDialog(null, "Erro: Nenhum usuário logado."); // esse erro não é para ser
                                                                                       // exibido!
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
                          return; // cancelou o cadastro
                      }
                  } else {
                      return; // não quis se tornar organizador
                  }
              }

              // Continua para criação do evento
              var createEventPanel = new CreateEventPanel(user,this);
              showMainPanel(createEventPanel, 0);
        });
        configurarBotaoSidebar(btnCreateEventSB);

        ImageIcon sbeditEventIcn = new ImageIcon(getClass().getResource("/Sidebar/EditEventSB.png"));
        JButton btnEditEventSB = new JButton(sbeditEventIcn);
        btnEditEventSB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditEventSB.addActionListener(e -> {
            var editPanel = new EventPanelEdit(this);
            showMainPanel(editPanel, 0);
        });
        configurarBotaoSidebar(btnEditEventSB);

        ImageIcon sbconsultEventIcn = new ImageIcon(getClass().getResource("/Sidebar/ConsultEventSB.png"));
        JButton btnConsultEventSB = new JButton(sbconsultEventIcn);
        configurarBotaoSidebar(btnConsultEventSB);
        btnConsultEventSB.addActionListener(e -> {
        	var eventPanelForConsult = new EventPanelForConsult(this);
        	showMainPanel(eventPanelForConsult, 0);
        });

        ImageIcon sbpresenceIcn = new ImageIcon(getClass().getResource("/Sidebar/PresenceSB.png"));
        JButton btnPresenceSB = new JButton(sbpresenceIcn);
        configurarBotaoSidebar(btnPresenceSB);
        btnPresenceSB.addActionListener(e -> {
        	var eventPanelPresenca = new EventPanelPresenca(this);
        	showMainPanel(eventPanelPresenca, 0);
        });

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

    // Exibe todos os eventos com paginação
    public void ExibirEvents() {
        var events = EventController.getInstance().getAllEvents();
        ExibirEvents(events);
    }

    // Exibe os eventos da lista passada (pode ser todos, pode ser filtrados)
    public void ExibirEvents(List<Event> events) {
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
                    this);
            panel.setBounds(x, y, 300, 240);
            galleryPanel.add(panel);
            x += 400;
        }

        // Botão Anterior
        JButton btnPrev = new JButton("");
        ImageIcon icon = new ImageIcon(getClass().getResource("/setacinza.png"));
        Image scaled = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnPrev.setIcon(new ImageIcon(scaled));
        btnPrev.setBounds(80, 130, 40, 40);
        btnPrev.setBorder(null);
        btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrev.setEnabled(currentPage > 0);
        btnPrev.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                ExibirEvents(events);
            }
        });
        galleryPanel.add(btnPrev);

        // Botão Próximo
        JButton btnNext = new JButton("");
        btnNext.setBounds(1290, 130, 40, 40);
        ImageIcon icon02 = new ImageIcon(getClass().getResource("/setacinzaPos.png"));
        Image scaled02 = icon02.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        btnNext.setIcon(new ImageIcon(scaled02));
        btnNext.setBorder(null);
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setEnabled((currentPage + 1) * pageSize < events.size());
        btnNext.addActionListener(e -> {
            currentPage++;
            ExibirEvents(events);
        });
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

//    this.setBackground(bg);
    galleryPanel.setBackground(bg);
    topbar.setBackground(new Color(0x9F96B0));
    sidebar.setBackground(new Color(0x9F96B0));
}



}