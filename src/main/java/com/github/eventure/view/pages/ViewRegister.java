package com.github.eventure.view.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewRegister extends JPanel {
    public ViewRegister() {
        
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));
        this.setLayout(null);        
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse está em: " + e.getX() + ", " + e.getY());
            }
        });
        
        var label1 = new JLabel();
        var icon = new ImageIcon("C:/Users/User/Downloads/CADASTRO.png");
        label1.setIcon(icon);
        
        // Define posição e tamanho do label manualmente
        label1.setBounds(50, 10, icon.getIconWidth(), icon.getIconHeight());

        this.add(label1);
        
        initComponents();
    }

    private void initComponents() {
      var campoEmail = new JTextField();
      campoEmail.setBorder(null);
      campoEmail.setBounds(261, 293, 450, 30); 
      var campoNome = new JTextField();
      campoNome.setBorder(null);
      campoNome.setBounds(261, 225, 450, 30);
//      campoEmail.setPreferredSize(new Dimension(300, 30)); 
//      campoEmail.setMinimumSize(new Dimension(300, 30));  
//      campoEmail.setMaximumSize(new Dimension(300, 30));   

      this.add(campoEmail);
      this.add(campoNome);
    }
}
