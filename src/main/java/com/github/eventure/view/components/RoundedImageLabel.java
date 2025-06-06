package com.github.eventure.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class RoundedImageLabel extends JLabel {
    private Image image;

    public RoundedImageLabel(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(500, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int arc = 40; // Raio da borda

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cria uma máscara arredondada
        Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setClip(clip);

        // Desenha a imagem dentro da máscara
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        g2.dispose();
    }
}
