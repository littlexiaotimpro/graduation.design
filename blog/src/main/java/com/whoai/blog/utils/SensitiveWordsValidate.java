package com.whoai.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 验证文本内容中的敏感词汇，存在敏感词
 * - 禁止该文件上传（采用此方案）
 * - 替换文本敏感内容后上传
 */
public class SensitiveWordsValidate {

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
