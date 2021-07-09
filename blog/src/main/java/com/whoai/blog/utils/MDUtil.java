package com.whoai.blog.utils;

import org.pegdown.PegDownProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @ClassName MDUtil
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/617:57
 */
public class MDUtil {

    /**
     * markdown语法转化html
     *
     * @param fileUrl
     * @return
     * @throws IOException
     */
    public static String changeMDToHtml(String fileUrl) throws IOException {
        try (InputStream is = new URL(fileUrl).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
            return pdp.markdownToHtml(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "无法解析MD文件";
    }

    /**
     * 验证文章中的铭感词
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean checkMarkdown(MultipartFile file) throws IOException {
        // 排除图片格式的文件
        try (InputStream is = file.getInputStream()) {
            if ("image/jpeg".equals(OSSClientUtil.getContentType(file.getOriginalFilename()))) {
                return false;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return ArticleReviewUtil.isContainSensitiveWord(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
