package com.lfs.web.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

/**
 * PdfUtil
 * <p>
 * https://www.it1352.com/OnLineTutorial/pdfbox/pdfbox_overview.html
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/13
 * @modify 2022/6/13
 */
public class PdfUtil {

    public static void main(String args[]) throws Exception {

        //Loading an existing document
        PDDocument doc = new PDDocument();

        File file = new File("C:/PdfBox_Examples/sample.pdf");
        //doc.save(file);

        doc = PDDocument.load(file);

        if (doc.getPages().getCount() < 1) {
            doc.addPage(new PDPage());
            doc.save(file);
        }
        //Retrieving the page
        PDPage page = doc.getPage(0);

        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:/PdfBox_Examples/punk2221.png", doc);

        //creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page);

        //Drawing the image in the PDF document
        contents.drawImage(pdImage, 70, 250);

        System.out.println("Image inserted");

        //Closing the PDPageContentStream object
        contents.close();

        //Saving the document
        doc.save("C:/PdfBox_Examples/sample.pdf");

        //Closing the document
        doc.close();

    }
}
