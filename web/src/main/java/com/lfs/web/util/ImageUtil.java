package com.lfs.web.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

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

    /**
     * BufferedImage 编码转换为 base64
     *
     * @return
     */
    private static String bufferedImageToBase64(ByteArrayOutputStream out) {
        String pngBase64 = Base64Utils.encodeToString(out.toByteArray());
        ;
        return StringUtils.isBlank(pngBase64) ? "" : "data:image/png;base64," + pngBase64;
    }
}
