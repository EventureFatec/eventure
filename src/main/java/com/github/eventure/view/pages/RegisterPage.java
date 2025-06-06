package com.github.eventure.view.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.github.eventure.controllers.UserController;
import com.github.eventure.view.MainFrame;

public class RegisterPage extends JPanel {
    private JTextField campoEmail;
    private JTextField campoNome;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JPasswordField campoRepetirSenha;
    private MainFrame mainFrame;
    private UserController userController = UserController.getInstance();

    public RegisterPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));
        this.setLayout(new GridBagLayout()); // para centralizar a janela
        initComponents();
    }

    private void initComponents() {
        // painel branco que será a janela centralizada
        JPanel windowPanel = new JPanel(new BorderLayout(0, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(180, 180, 180, 100)); // sombra
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.dispose();
            }
        };
        windowPanel.setOpaque(false);
        windowPanel.setPreferredSize(new Dimension(800, 520));
        windowPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Logo no topo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        var logoIcon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        logoLabel.setIcon(logoIcon);
        windowPanel.add(logoLabel, BorderLayout.NORTH);

        // painel central com os campos
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(label("Nome completo:"), gbc);
        gbc.gridy++;
        campoNome = styledTextField();
        formPanel.add(campoNome, gbc);

        gbc.gridy++;
        formPanel.add(label("E-mail:"), gbc);
        gbc.gridy++;
        campoEmail = styledTextField();
        formPanel.add(campoEmail, gbc);

        gbc.gridy++;
        formPanel.add(label("Nome de usuário:"), gbc);
        gbc.gridy++;
        campoUsuario = styledTextField();
        formPanel.add(campoUsuario, gbc);

        gbc.gridy++;
        formPanel.add(label("Senha e confirmação:"), gbc);
        gbc.gridy++;
        JPanel senhaPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        senhaPanel.setOpaque(false);
        campoSenha = styledPasswordField();
        campoSenha.setToolTipText("Senha");
        senhaPanel.add(campoSenha);

        campoRepetirSenha = styledPasswordField();
        campoRepetirSenha.setToolTipText("Repetir senha");
        senhaPanel.add(campoRepetirSenha);
        formPanel.add(senhaPanel, gbc);

        windowPanel.add(formPanel, BorderLayout.CENTER);

        // painel de botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        var botaoCadastrar = createGradientButton("Cadastrar");

        var botaoEntrar = createGrayButton("Já possui conta? Entrar");

        buttonsPanel.add(botaoEntrar);
        buttonsPanel.add(botaoCadastrar);

        windowPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.add(windowPanel, new GridBagConstraints());

        // ações
        botaoCadastrar.addActionListener(e -> {
            var name = campoNome.getText().trim();
            var email = campoEmail.getText().trim();
            var username = campoUsuario.getText().trim();
            var password = new String(campoSenha.getPassword());
            var password2 = new String(campoRepetirSenha.getPassword());

            botaoCadastrarClicado(name, email, username, password, password2);
        });

        botaoEntrar.addActionListener(e -> botaoEntrarClicado());
    }

    private JLabel label(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setForeground(new Color(0x330065));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField styledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(400, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x330065), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    private JPasswordField styledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(190, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x330065), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return field;
    }

    private void botaoCadastrarClicado(String name, String email, String username, String password, String password2) {
        if (password.equals(password2) && !name.isEmpty() && !email.isEmpty() && !username.isEmpty()
                && !password.isEmpty() && !password2.isEmpty()) {
            boolean success = userController.createUser(name, username, password, email);
            if (success) {
                campoNome.setText("");
                campoEmail.setText("");
                campoUsuario.setText("");
                campoSenha.setText("");
                campoRepetirSenha.setText("");
                mainFrame.showPanel("login");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Preencha os campos corretamente e confirme as senhas iguais.");
        }
    }

    private void botaoEntrarClicado() {
        campoNome.setText("");
        campoEmail.setText("");
        campoUsuario.setText("");
        campoSenha.setText("");
        campoRepetirSenha.setText("");
        mainFrame.showPanel("login");
    }

    private JButton createGradientButton(String text) {
        JButton button = new JButton(text) {
            private boolean hover = false;

            {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setFocusPainted(false);
                setContentAreaFilled(false);
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 20));
                setPreferredSize(new Dimension(180, 40));

                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color colorStart = new Color(0x330065);
                Color colorEnd = new Color(0x550099);

                if (hover) {
                    colorStart = colorStart.brighter();
                    colorEnd = colorEnd.brighter();
                }

                GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                var fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();

                g2.setColor(getForeground());
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0x220050));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        return button;
    }

    private JButton createGrayButton(String text) {
        JButton button = new JButton(text) {
            private boolean hover = false;

            {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setFocusPainted(false);
                setContentAreaFilled(false);
                setForeground(Color.DARK_GRAY);
                setFont(new Font("Segoe UI", Font.BOLD, 12));
                setPreferredSize(new Dimension(180, 40));

                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color colorStart = new Color(200, 200, 200); // cinza claro
                Color colorEnd = new Color(160, 160, 160); // cinza médio

                if (hover) {
                    colorStart = colorStart.brighter();
                    colorEnd = colorEnd.brighter();
                }

                GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                var fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();

                g2.setColor(getForeground());
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(120, 120, 120)); // cinza escuro
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        return button;
    }

}
