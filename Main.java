package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.ImageToUnicodeConverter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new ImageToUnicodeConverter(300, 300, 4);

        GServer server = new GServer(converter);
        server.start();
    }
}