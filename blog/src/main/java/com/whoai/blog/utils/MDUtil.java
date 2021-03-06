package com.whoai.blog.utils;

import org.pegdown.PegDownProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * MarkDown 文件内容的敏感词校验
 */
public class MDUtil {

    /**
     * markdown语法转化html
     */
    public static String changeMDToHtml(String fileUrl){
        try (InputStream is = new URL(fileUrl).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
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
     */
    public static boolean checkMarkdown(MultipartFile file){
        // 排除图片格式的文件
        try (InputStream is = file.getInputStream()) {
            String filename = file.getOriginalFilename();
            //文件的后缀名
            assert filename != null;
            String fileExtension = filename.substring(filename.lastIndexOf("."));
            if (".jpeg".equalsIgnoreCase(fileExtension)
                    || ".jpg".equalsIgnoreCase(fileExtension)
                    || ".png".equalsIgnoreCase(fileExtension)) {
                return false;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return SensitiveWordsValidate.isContainSensitiveWord(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
