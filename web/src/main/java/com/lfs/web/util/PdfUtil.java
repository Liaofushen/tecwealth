package com.lfs.web.util;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.aspectj.util.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
@Slf4j
public class PdfUtil {

    public static byte[] toPdfBytes(List<String> base64s) {

        PDDocument pdDocument = PdfUtil.toPdfDoc(base64s);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            pdDocument.save(output);
        } catch (Exception e) {
            log.warn("Fail to save PDDocument e={}", e.getMessage(), e);
        }
        return output.toByteArray();
    }

    public static PDDocument toPdfDoc(List<String> base64s) {
        PDDocument doc = new PDDocument();

        int page = base64s == null ? 0 : base64s.size();

        for (int i = 0; i < page; ++i) {
            try {
                byte[] imageBytes = ImageUtil.decode(base64s.get(i));
                BufferedImage bfImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                // 图片是先转存JPG格式再贴到pdf，转化后的质量为0.75F
                PDImageXObject pdImage = JPEGFactory.createFromImage(doc, bfImage, 0.75F, 72);

                // 按图片尺寸定pdf页大小
                doc.addPage(new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight())));

                PDPageContentStream contents = new PDPageContentStream(doc, doc.getPage(i));
                contents.drawImage(pdImage, 0, 0);
                contents.close();

            } catch (Exception e) {
                log.warn("Fail in cvtPdf i={} e={}", i, e.getMessage(), e);
            }
        }
        return doc;
    }

    @SneakyThrows
    public static String getBase64() {
        String url = "C:\\Users\\liaofushen\\Pictures\\1024.png";
        //String url = "C:\\Users\\liaofushen\\Pictures\\punk2221.png";
        String picBase64 = ImageUtil.getPicBase64(new File(url), 1920, 1080);
        //System.out.println(picBase64);
        return picBase64;
    }

    public static void main(String args[]) throws Exception {

        ArrayList<String> base64s = Lists.newArrayList(getBase64(), getBase64(), getBase64());

        PDDocument doc = toPdfDoc(base64s);

        File file = new File("C:/PdfBox_Examples/sample_7.pdf");
        doc.save(file);
        doc.close();

        byte[] bytes = toPdfBytes(base64s);

        byte[] bytes2 = FileUtil.readAsByteArray(file);

        System.out.println(bytes.length);
        System.out.println(bytes2.length);

        System.out.println("Image inserted");

    }
}
