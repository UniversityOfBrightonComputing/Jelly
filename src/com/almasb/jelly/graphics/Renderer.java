package com.almasb.jelly.graphics;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.almasb.jelly.GameResources;

public class Renderer {
    public static BufferedImage createGraphics(String text) {

        if (text.isEmpty())
            return GameResources.IMG_NULL;

        BufferedImage img = new BufferedImage(10, 10,
                GameResources.IMG_NULL.getType());

        Graphics2D g = img.createGraphics();
        Font font = new Font("Neuropol", Font.BOLD, 20);
        FontMetrics fm = g.getFontMetrics(font);

        img = new BufferedImage(fm.stringWidth(text), fm.getHeight(),
                GameResources.IMG_NULL.getType());
        g = img.createGraphics();

        g.setFont(font);
        g.drawString(text, 0, 15);
        g.dispose();

        return img;
    }

    public static BufferedImage createGraphics(BufferedImage image, int times) {

        if (times <= 0)
            return GameResources.IMG_NULL;

        BufferedImage img = new BufferedImage(image.getWidth() * times,
                image.getHeight(), GameResources.IMG_NULL.getType());

        Graphics2D g = img.createGraphics();
        for (int i = 0; i < times; i++) {
            g.drawImage(image, i * image.getWidth(), 0, null);
        }
        g.dispose();

        return img;
    }
}
