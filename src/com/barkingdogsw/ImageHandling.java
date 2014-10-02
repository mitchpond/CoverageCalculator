package com.barkingdogsw;

import javafx.scene.image.*;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderWriterSpi;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public static void countColor(String p){
        //to be implemented
    }
}
