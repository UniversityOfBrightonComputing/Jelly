package com.almasb.jelly.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GraphicUtils {
    /**
     * Flips an image
     * 
     * @param img
     *            - the specified image to be drawn. This method does nothing if
     *            img is null. dx1 - the x coordinate of the first corner of the
     *            destination rectangle. dy1 - the y coordinate of the first
     *            corner of the destination rectangle. dx2 - the x coordinate of
     *            the second corner of the destination rectangle. dy2 - the y
     *            coordinate of the second corner of the destination rectangle.
     *            sx1 - the x coordinate of the first corner of the source
     *            rectangle. sy1 - the y coordinate of the first corner of the
     *            source rectangle. sx2 - the x coordinate of the second corner
     *            of the source rectangle. sy2 - the y coordinate of the second
     *            corner of the source rectangle. observer - object to be
     *            notified as more of the image is scaled and converted.
     * 
     * @return - horizontally flipped image
     * @author - http://sanjaal.com/java/tag/flip-bufferedimage-in-java/
     */
    public static BufferedImage flipImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();

        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }
}
