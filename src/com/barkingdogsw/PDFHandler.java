package com.barkingdogsw;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Mitch Pond on 10/6/2014.
 */
public class PDFHandler {

    public static BufferedImage renderPDF(String path){
        int pageNo = 0;
        try {
            System.out.println("Loading PDF...");
            PDDocument doc = PDDocument.load(path);
            PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(pageNo);
            BufferedImage pageImage = page.convertToImage();
            doc.close();
            return pageImage;
        } catch (IOException e) {
            System.err.println("Error accessing file: "+path);
            e.printStackTrace();
        }
        return null;
    }
}
