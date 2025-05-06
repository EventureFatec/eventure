package com.github.eventure.view.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
// import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.eventure.controllers.UserController;
import com.github.eventure.controllers.PasswordController;
import com.github.eventure.view.MainFrame;

public class RegisterPage extends JPanel {
	private JTextField campoEmail;
	private JTextField campoNome;
	private JTextField campoUsuario;
	private JPasswordField campoSenha;
	private JPasswordField campoRepetirSenha;
	private MainFrame mainFrame;
    private UserController userController = new UserController();
    public RegisterPage(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
            this.setPreferredSize(new Dimension(1280, 720));
            this.setBackground(new Color(0x330065));
            this.setLayout(null); 
//            this.addMouseMotionListener(new MouseMotionAdapter() {
//                @Override
//                public void mouseMoved(MouseEvent e) {
//                    System.out.println("Mouse position: " + e.getX() + ", " + e.getY());
//                }
//            });
            initComponents();
            var label1 = new JLabel();
//            var icon = new ImageIcon("C:/Users/User/Downloads/CADASTRO.png");
            var icon = new ImageIcon(getClass().getResource("/CADASTRO02.png"));
            label1.setIcon(icon);
            
            // Define posição e tamanho do label manualmente
//            label1.setBounds(50, 10, icon.getIconWidth(), icon.getIconHeight());
            
            label1.setBounds(83, 24, 1200, 720);
            this.add(label1);
    }

    private void initComponents() {
    	 
         campoEmail = new JTextField();
         campoEmail.setBorder(null);
         campoEmail.setBounds(295, 307, 450, 30); 

         campoNome = new JTextField();
         campoNome.setBorder(null);
         campoNome.setBounds(295, 239, 450, 30);
         
         campoUsuario = new JTextField();
         campoUsuario.setBorder(null);
         campoUsuario.setBounds(295, 376, 450, 30);
         
         campoSenha = new JPasswordField();
         campoSenha.setBorder(null);
         campoSenha.setBounds(295, 446, 200, 30);
         
         campoRepetirSenha = new JPasswordField();
         campoRepetirSenha.setBorder(null);
         campoRepetirSenha.setBounds(549, 446, 200, 30);
         
         var botao = new JButton("");
         botao.setBounds(392, 495, 250, 40);
         botao.setContentAreaFilled(false);
         botao.setBorderPainted(false);
         botao.setFocusPainted(false);
         botao.setOpaque(false);
         
         var botaoEntrar = new JButton("");
         botaoEntrar.setBounds(475, 584, 85, 40);
         botaoEntrar.setContentAreaFilled(false);
         botaoEntrar.setBorderPainted(false);
         botaoEntrar.setFocusPainted(false);
         
         JLabel efeitoPressionado = new JLabel();
         efeitoPressionado.setBounds(402, 500, 230, 30);
         efeitoPressionado.setBackground(new Color(0, 0, 0, 50)); 
         efeitoPressionado.setOpaque(true);
         efeitoPressionado.setVisible(false); 
         
         JLabel efeitoPressionado2 = new JLabel();
         efeitoPressionado2.setBounds(476, 587, 81, 35);
         efeitoPressionado2.setBackground(new Color(0, 0, 0, 50)); 
         efeitoPressionado2.setOpaque(true);
         efeitoPressionado2.setVisible(false);
         
         botao.addMouseListener(new java.awt.event.MouseAdapter() {
             @Override
             public void mousePressed(java.awt.event.MouseEvent e) {
                efeitoPressionado.setVisible(true);
             }

             @Override
             public void mouseReleased(java.awt.event.MouseEvent e) {
           	  efeitoPressionado.setVisible(false);
             }
         });
         
         botaoEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
             @Override
             public void mousePressed(java.awt.event.MouseEvent e) {
                efeitoPressionado2.setVisible(true);
             }

             @Override
             public void mouseReleased(java.awt.event.MouseEvent e) {
           	  efeitoPressionado2.setVisible(false);
             }
         });
         
         this.add(efeitoPressionado);
         this.add(efeitoPressionado2);
         this.add(campoEmail);
         this.add(campoNome);
         this.add(campoUsuario);
         this.add(campoSenha);
         this.add(campoRepetirSenha);
         this.add(botao);
         this.add(botaoEntrar);
         
         botao.addActionListener(e -> {
            var name = campoNome.getText();
            var email = campoEmail.getText();
            var username = campoUsuario.getText();
            var password = new String(campoSenha.getPassword());
            var password2 = new String(campoRepetirSenha.getPassword());

            botaoCadastrarClicado(name, email, username, password, password2);
        });
         botaoEntrar.addActionListener(e -> botaoEntrarClicado());
    }
    
private void botaoCadastrarClicado(String name, String email, String username, String password, String password2) {
    if (password.equals(password2) && !name.isEmpty() && !email.isEmpty() && !username.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            userController.createUser(name, username, password, email);
        } 
        else {JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");}
}
    
    private void botaoEntrarClicado() {
    	System.out.println("botao para logar usuario");
    	mainFrame.showPanel("login");
    }

}
