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
    private RootPanel rootPanel;

    public RoundedPanel(int radius, boolean blur) {
        cornerRadius = radius;
        enableBlurring = blur;
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

        // Blurring
        if (enableBlurring) {
            // Derive root panel when it does not exist
            if (rootPanel == null) {
                deriveRootPanel();
                System.out.println("Derived root panel");
            }

            // Capture the component
            Point locationOnScreen = getLocationOnScreen();
            BufferedImage bgImage = captureBackground(locationOnScreen, width, height);

            if (bgImage != null) {
                var blurredImage = applyGaussianBlur(bgImage);

                // Set the clip
                var roundedClip = new RoundRectangle2D.Float(0, 0, width, height, arcs.width, arcs.height);
                graphics.setClip(roundedClip);

                // Draw the blurred background
                graphics.drawImage(blurredImage, 0, 0, null);

                // Remove the clip
                graphics.setClip(null);
            }
        }

        renderGraphics(graphics, width - 1, height - 1, arcs.width, arcs.height);
    }


    private void deriveRootPanel() {
        var p = (Component) this;
        while (rootPanel == null) {
            p = p.getParent();
            System.out.println(p);
            if (p.getClass().equals(RootPanel.class)) {
                rootPanel = (RootPanel) p;
            }
        }
    }

    private BufferedImage captureBackground(Point p, int width, int height) {
        var combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var graphics = combinedImage.createGraphics();

        // Get all components in each layer
        for (int i = JLayeredPane.DEFAULT_LAYER; i <= JLayeredPane.MODAL_LAYER; i++) {
            for (Component c: rootPanel.getComponentsInLayer(i)) {
                c.paint(graphics);
            }
        }
        graphics.dispose();

        return combinedImage;
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
