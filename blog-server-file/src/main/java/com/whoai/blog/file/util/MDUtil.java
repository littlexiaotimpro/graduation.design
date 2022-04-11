package com.whoai.blog.file.util;

import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * MarkDown 文件转 html
 * MarkDown 文件内容的敏感词校验
 */
public class MDUtil {

    /**
     * markdown语法转化html
     */
    public static String changeMDToHtml(String fileUrl) {
        try (InputStream is = new URL(fileUrl).openStream();
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader rd = new BufferedReader(reader)) {
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
     * 解析 markdown 文件，转为 html
     *
     * @param resource 资源文件
     * @return html
     */
    public static String convertMDToHtml(Resource resource) {
        try (InputStream is = resource.getURL().openStream();
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader rd = new BufferedReader(reader)) {
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
    public static boolean checkMarkdown(MultipartFile file) {
        // 排除图片格式的文件
        try (InputStream is = file.getInputStream();
             InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader rd = new BufferedReader(in)) {
            // 文件类型
            final String contentType = file.getContentType();
            assert contentType != null;
            if (!contentType.startsWith("text")) {
                return false;
            }
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

    /**
     * 验证文本内容中的敏感词汇，存在敏感词
     * - 禁止该文件上传（采用此方案）
     * - 替换文本敏感内容后上传
     */
    private static class SensitiveWordsValidate {

        private static final Logger logger = LoggerFactory.getLogger(SensitiveWordsValidate.class);

        /**
         * 从文本中读取的敏感词集合
         */
        private static Set<String> words;

        /**
         * 按规则设定的敏感词匹配集合
         */
        private static Map<Object, Object> sensitiveWordsMap;

        /**
         * 匹配规则的结束标识
         */
        private static final String END_FLAG = "end";

        /**
         * 敏感词文件所在的本地地址
         */
        private static final String DICTIONARY_LOCATION = "classpath:static/dictionary.txt";

        static {
            try {
                SensitiveWordsValidate.words = readTxtByLine();
                initSensitiveWordsMap(SensitiveWordsValidate.words);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * 敏感词库
         */
        public static Set<String> readTxtByLine() throws FileNotFoundException {
            Set<String> keyWordSet = new HashSet<>();
            File file = ResourceUtils.getFile(DICTIONARY_LOCATION);
            //文件流是否存在
            if (!file.exists()) {
                return keyWordSet;
            }
            // 逐行读取文件中的内容
            String temp;
            try (final FileInputStream in = new FileInputStream(file);
                 final InputStreamReader in1 = new InputStreamReader(in, StandardCharsets.UTF_8);
                 final BufferedReader reader = new BufferedReader(in1)) {
                // 逐行读取文件内容（一行一个词）
                while ((temp = reader.readLine()) != null) {
                    keyWordSet.add(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return keyWordSet;
        }

        /**
         * 初始化铭感词库
         */
        private static void initSensitiveWordsMap(Set<String> sensitiveWords) {
            if (sensitiveWords == null || sensitiveWords.isEmpty()) {
                logger.warn("Sensitive words is empty!");
                sensitiveWordsMap = new HashMap<>(0);
                return;
            }
            sensitiveWordsMap = new HashMap<>(sensitiveWords.size());
            String currentWord;
            Map<Object, Object> currentMap;
            Map<Object, Object> subMap;
            for (String sensitiveWord : sensitiveWords) {
                currentWord = sensitiveWord;
                //敏感词长度必须大于等于2
                if (currentWord == null || currentWord.trim().length() < 2) {
                    continue;
                }
                currentMap = sensitiveWordsMap;
                /*
                 * 敏感词初始化规则：以 “敏感词”，“敏词” 为例
                 * 不能以一个完整的词作为敏感词库的匹配项，
                 * 最终结果：
                 * {
                 *  "敏":{
                 *      "感":{
                 *          "词":{
                 *              "end":null
                 *              }
                 *          },
                 *      "词":{
                 *          "end":null
                 *          }
                 *      }
                 * }
                 */
                for (int i = 0; i < currentWord.length(); i++) {
                    char c = currentWord.charAt(i);
                    subMap = (Map<Object, Object>) currentMap.get(c);
                    if (subMap == null) {
                        subMap = new HashMap<>();
                        currentMap.put(c, subMap);
                    }
                    currentMap = subMap;
                    if (i == currentWord.length() - 1) {
                        // 结束标识位
                        currentMap.put(END_FLAG, null);
                    }
                }
            }
        }

        /**
         * 最小匹配，判断文本中是否含有敏感词
         */
        public static boolean isContainSensitiveWord(String text) {
            Set<String> sensitiveWords = getSensitiveWords(text, MatchType.MIN_MATCH);
            logger.info("敏感词：{}", sensitiveWords);
            return sensitiveWords.size() > 0;
        }

        /**
         * 最大匹配，获取所有的敏感词
         */
        public static Set<String> findSensitiveWords(String text) {
            Set<String> sensitiveWords = getSensitiveWords(text, MatchType.MAX_MATCH);
            logger.info("敏感词：{}", sensitiveWords);
            return sensitiveWords;
        }

        /**
         * 统计其中的敏感词
         */
        public static Set<String> getSensitiveWords(String text, MatchType matchType) {
            logger.info("敏感词匹配规则：{}", matchType.desc);
            Set<String> sensitiveWords = new HashSet<>();
            if (text == null || text.trim().length() == 0) {
                logger.warn("The input text must not be empty.");
                return sensitiveWords;
            }
            for (int i = 0; i < text.length(); i++) {
                int sensitiveWordLength = getSensitiveWordLength(text, i, matchType);
                if (sensitiveWordLength > 0) {
                    String sensitiveWord = text.substring(i, i + sensitiveWordLength);
                    sensitiveWords.add(sensitiveWord);
                    if (matchType == MatchType.MIN_MATCH) {
                        break;
                    }
                    i = i + sensitiveWordLength - 1;
                }
            }
            return sensitiveWords;
        }

        /**
         * 获取敏感词数目
         */
        public static int getSensitiveWordLength(String text, int startIndex, MatchType matchType) {
            if (text == null || text.trim().length() == 0) {
                logger.warn("The input text must not be empty.");
                return 0;
            }
            char currentChar;
            Map<Object, Object> currentMap = sensitiveWordsMap;
            int wordLength = 0;
            boolean endFlag = false;
            for (int i = startIndex; i < text.length(); i++) {
                currentChar = text.charAt(i);
                Map<Object, Object> subMap = (Map<Object, Object>) currentMap.get(currentChar);
                if (subMap == null) {
                    break;
                } else {
                    wordLength++;
                    if (subMap.containsKey(END_FLAG)) {
                        endFlag = true;
                        if (matchType == MatchType.MIN_MATCH) {
                            break;
                        } else {
                            currentMap = subMap;
                        }
                    } else {
                        currentMap = subMap;
                    }
                }
            }
            if (!endFlag) {
                wordLength = 0;
            }
            return wordLength;
        }

        enum MatchType {

            MIN_MATCH("最小匹配规则"), MAX_MATCH("最大匹配规则");

            String desc;

            MatchType(String desc) {
                this.desc = desc;
            }
        }
    }

}
