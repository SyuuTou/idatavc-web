package com.idatavc.web.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class PdfToImage {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        try {
            String sourceDir = "/Users/zhhu/Downloads/2016 中国大文娱产业升级报告.pdf";
            String destinationDir = "/Users/zhhu/Downloads/img/";
            File sourceFile = new File(sourceDir);
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
            }
            if (sourceFile.exists()) {
                PDDocument document = PDDocument.load(sourceFile);
                @SuppressWarnings("unchecked")
                Iterator<PDPage> list = document.getDocumentCatalog().getPages().iterator();

                String fileName = sourceFile.getName().replace(".pdf", "");
                int pageNumber = 1;
                while (list.hasNext()) {
                    PDPage page = list.next();
                    BufferedImage image = ImageIO.read(page.getContents());
                    File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".png");
                    ImageIO.write(image, "png", outputfile);
                    pageNumber++;
                }
                document.close();
                System.out.println("Image saved at -> " + destinationFile.getAbsolutePath());
            } else {
                System.err.println(sourceFile.getName() + " File does not exist");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
