package com.lfs.web.util;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
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

    @SneakyThrows
    public static String getBase64() {
        String url = "C:\\Users\\liaofushen\\Pictures\\1024.png";
        //String url = "C:\\Users\\liaofushen\\Pictures\\punk2221.png";
        String picBase64 = ImageUtil.getPicBase64(new File(url), 1920, 1080);
        //System.out.println(picBase64);
        return picBase64;
    }

    public static void main(String args[]) throws Exception {

        File file = new File("C:/PdfBox_Examples/sample.pdf");

        PDDocument doc = PDDocument.load(file);

        while (doc.getPages().getCount() > 0) {
            doc.getPages().remove(0);
        }

        for (int i = 0; i < 3; ++i) {

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ImageUtil.decode(getBase64()));

            PDImageXObject pdImage = JPEGFactory.createFromImage(doc, ImageIO.read(byteArrayInputStream));

            //Creating PDImageXObject object
            // PDImageXObject pdImage = PDImageXObject.createFromFile(
            //         String.format("C:/PdfBox_Examples/%s.png", i), doc);

            doc.addPage(new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight())));
            //doc.save(file);

            //creating the PDPageContentStream object
            PDPageContentStream contents = new PDPageContentStream(doc, doc.getPage(i));

            //Drawing the image in the PDF document
            contents.drawImage(pdImage, 0, 0);

            //Closing the PDPageContentStream object
            contents.close();
        }

        //Saving the document
        doc.save(file);

        System.out.println("Image inserted");

        //Closing the document
        doc.close();

    }
}
