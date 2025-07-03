package com.github.eventure.controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GeradorCertificado {

    public static void gerar(String nomePessoa, String nomeEvento) {
        try {
           
        	BufferedImage modelo = ImageIO.read(
        		    GeradorCertificado.class.getClassLoader().getResourceAsStream("ModeloCertificado.png")
        		);
            Graphics2D g = modelo.createGraphics();

            
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 58));
            drawCentralizado(g, nomePessoa, modelo.getWidth(), 690); 

            g.setColor(new Color(22, 64, 92));
            g.setFont(new Font("SansSerif", Font.BOLD, 35));
            drawCentralizado(g, nomeEvento, modelo.getWidth(), 1080);

            g.dispose();
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("Certificado_" + nomePessoa + ".png"));
            int opcao = fileChooser.showSaveDialog(null);

            if (opcao == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                ImageIO.write(modelo, "png", arquivo);
                JOptionPane.showMessageDialog(null, "Certificado salvo com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private static void drawCentralizado(Graphics2D g, String texto, int larguraImagem, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (larguraImagem - fm.stringWidth(texto)) / 2;
        g.drawString(texto, x, y);
    }
}
