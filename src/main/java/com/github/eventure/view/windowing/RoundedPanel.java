package com.github.eventure.view.windowing;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private boolean enableBlurring;

    public RoundedPanel(int radius) {
        cornerRadius = radius;
        enableBlurring = false;
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        var graphics = (Graphics2D) g;

        // For better edge rendering
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        renderGraphics(graphics, width - 1, height - 1, arcs.width, arcs.height);
    }


    private void renderGraphics(Graphics2D g, int width, int height, int arcWidth, int arcHeight) {
        // Draw background
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, width - 1, height - 1, arcWidth, arcHeight);

        // Draw foreground
        g.setColor(getForeground());
        g.fillRoundRect(0, 0, width - 1, height - 1, arcWidth, arcHeight);

    }

    // TODO: Use this for background blurring
    protected BufferedImage applyGaussianBlur(BufferedImage i) {
        float[] blurMatrix = {
            1f / 273f,  4f / 273f,  6f / 273f,  4f / 273f,  1f / 273f,
            4f / 273f, 16f / 273f, 24f / 273f, 16f / 273f,  4f / 273f,
            6f / 273f, 24f / 273f, 36f / 273f, 24f / 273f,  6f / 273f,
            4f / 273f, 16f / 273f, 24f / 273f, 16f / 273f,  4f / 273f,
            1f / 273f,  4f / 273f,  6f / 273f,  4f / 273f,  1f / 273f
        };

        var k = new Kernel(5, 5, blurMatrix);
        var conv = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);

        var blurredImage = new BufferedImage(i.getWidth(), i.getHeight(), i.getType());
        conv.filter(i, blurredImage);
        return blurredImage;
    }
}
