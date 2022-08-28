package com.lfs.test.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

/**
 * ImageUtil
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/6/13
 * @modify 2022/6/13
 */
@Slf4j
public class ImageUtil {
    public static String getPicBase64(String imageUrl, int toWidth, int toHeight)
            throws IOException {
        long st = System.currentTimeMillis();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Thumbnails.of(new URL(imageUrl))
                .size(toWidth, toHeight)
                .outputFormat("png")
                .toOutputStream(out);
        log.info("getPicBase64 covert png and resize cost={}", System.currentTimeMillis() - st);

        return bufferedImageToBase64(out);
    }

    public static String getPicBase64(File file, int toWidth, int toHeight)
            throws IOException {
        long st = System.currentTimeMillis();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Thumbnails.of(file)
                .size(toWidth, toHeight)
                .outputFormat("png")
                .toOutputStream(out);
        log.info("getPicBase64 covert png and resize cost={}", System.currentTimeMillis() - st);

        return bufferedImageToBase64(out);
    }

    /**
     * BufferedImage 编码转换为 base64
     *
     * @return
     */
    private static String bufferedImageToBase64(ByteArrayOutputStream out) {
        String pngBase64 = Base64.getEncoder().encodeToString(out.toByteArray());
        // Base64Utils.decodeFromString()
        return pngBase64;
        //return StringUtils.isBlank(pngBase64) ? "" : "data:image/png;base64," + pngBase64;
    }

    /**
     * BufferedImage 编码转换为 base64
     *
     * @return
     */
    public static byte[] decode(String in) {
        return Base64.getDecoder().decode(in);
        //return Base64.getDecoder().decode(in.replaceFirst("data:image/png;base64,", ""));
    }

    public static void main(String[] args) throws IOException {
        String url = "C:\\Users\\liaofushen\\Pictures\\image.png";
        String picBase64 = getPicBase64(new File(url), 250, 250);
        System.out.println(picBase64);
        System.out.println(picBase64.getBytes().length);
        System.out.println((picBase64.getBytes().length / 1024.0) + "KB");
    }
}
