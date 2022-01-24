package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class ImageToUnicodeConverter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double maxRatio;

    public ImageToUnicodeConverter(int width, int height, double maxRatio) {
        this.width = width;
        this.height = height;
        this.maxRatio = maxRatio;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        TextColorSchema schema = new CodeSchema();
        int initialWidth = img.getWidth();
        int initialHeight = img.getHeight();
        int newWidth;
        int newHeight;
        double ratio = (double) initialWidth / initialHeight;
        if ((initialWidth > initialHeight) && (initialWidth > width)) {
            newWidth = initialWidth / (initialWidth / width);
            newHeight = initialHeight / (initialWidth / width);
        } else if (initialHeight > height) {
            newHeight = initialHeight / (initialHeight / height);
            newWidth = initialWidth / (initialHeight / height);
        } else {
            newWidth = img.getWidth();
            newHeight = img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        StringBuilder builder = new StringBuilder();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                builder.append(c).append(c);
            }
            builder.append("\n");
        }
        if ((ratio > maxRatio) || (ratio < (1 / maxRatio))) {
            throw new BadImageSizeException(ratio, maxRatio);
        } else {
            return builder.toString();
        }
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}