package com.barkingdogsw;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public static Set countColors(BufferedImage img){
        Map<String, Float> colorMap = new HashMap<String, Float>();
        Set results = new HashSet();
        int width = img.getWidth();
        int height = img.getHeight();

        for (int w = 0; w < width; w++){
            for (int h = 0; h < height; h++){
                String out = Integer.toHexString(img.getRGB(w, h));
                out = "#"+out.substring(2);
                Float colorCount = colorMap.putIfAbsent(out, 1f);
                if (colorCount != null) colorMap.put(out, colorCount+1f);
            }
        }
/*
        System.out.println("Width: "+width);
        System.out.println("Height: "+height);
        System.out.println("Total pixels: "+width*height);
        System.out.println(colorMap);
*/
        for (Map.Entry<String,Float> entry : colorMap.entrySet()){
            float pct = (entry.getValue()/(width*height))*100;
            entry.setValue(pct);
            results.add(entry);
        }
        return results;

    }


}
