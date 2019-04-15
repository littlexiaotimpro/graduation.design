package cn.hc.xiaosi.utils;

import org.pegdown.PegDownProcessor;

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

    public static String changeMDToHtml(String fileUrl) throws IOException {
        InputStream is = new URL(fileUrl).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
            String htmlContent = pdp.markdownToHtml(jsonText);
            return htmlContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return "无法解析MD文件";
    }

}
