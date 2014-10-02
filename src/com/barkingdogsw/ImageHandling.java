package com.barkingdogsw;

import javafx.scene.image.*;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderWriterSpi;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Mitch Pond on 10/2/2014.
 */
public class ImageHandling {

    public static BufferedImage getImage(String p){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bi != null) return bi;
        else return null;
    }

    public static byte[] getImageBytes(BufferedImage img){
        return ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
    }

    public static void countColors(BufferedImage img){
        HashMap colorMap = new HashMap();
        int width = img.getWidth();
        int height = img.getHeight();
        int count = 0;
        String colorToFind = "ffe62b29";
        for (int w = 0; w < width; w++){
            for (int h = 0; h < height; h++){
                String out = Integer.toHexString(img.getRGB(w, h));
                if (out.equals(colorToFind)) count++;
                colorMap.putIfAbsent(out,null);
            }
        }
        System.out.println("Width: "+width);
        System.out.println("Height: "+height);
        System.out.println("Found: "+count);
        System.out.println("Total pixels: "+width*height);
        System.out.println("Percent: "+((float)count/(width*height))*100);
        System.out.println(colorMap);

    }
}
