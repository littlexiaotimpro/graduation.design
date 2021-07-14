package com.whoai.blog.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SensitiveWordsValidate {

    private static Set<String> words;

    private static Map<Object, Object> sensitiveWordsMap;

    private static final String END_FLAG = "end";

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
        File file = ResourceUtils.getFile("classpath:static/dictionary.txt");
        //文件流是否存在
        if (!file.exists()) {
            return keyWordSet;
        }
        BufferedReader reader = null;
        String temp;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            while ((temp = reader.readLine()) != null) {
                keyWordSet.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return keyWordSet;
    }

    public enum MatchType {

        MIN_MATCH("最小匹配规则"), MAX_MATCH("最大匹配规则");

        String desc;

        MatchType(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 最小匹配，判断文本中是否含有敏感词
     */
    public static boolean isContainSensitiveWord(String text) {
        Set<String> sensitiveWords = getSensitiveWords(text, MatchType.MIN_MATCH);
        return sensitiveWords.size() > 0;
    }

    /**
     * 统计其中的敏感词
     */
    public static Set<String> getSensitiveWords(String text, MatchType matchType) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException("The input text must not be empty.");
        }
        Set<String> sensitiveWords = new HashSet<>();
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
            throw new IllegalArgumentException("The input text must not be empty.");
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

    /**
     * 初始化铭感词库
     */
    private static void initSensitiveWordsMap(Set<String> sensitiveWords) {
        if (sensitiveWords == null || sensitiveWords.isEmpty()) {
            throw new IllegalArgumentException("Sensitive words must not be empty!");
        }
        sensitiveWordsMap = new HashMap<>(sensitiveWords.size());
        String currentWord;
        Map<Object, Object> currentMap;
        Map<Object, Object> subMap;
        for (String sensitiveWord : sensitiveWords) {
            currentWord = sensitiveWord;
            if (currentWord == null || currentWord.trim().length() < 2) {  //敏感词长度必须大于等于2
                continue;
            }
            currentMap = sensitiveWordsMap;
            for (int i = 0; i < currentWord.length(); i++) {
                char c = currentWord.charAt(i);
                subMap = (Map<Object, Object>) currentMap.get(c);
                if (subMap == null) {
                    subMap = new HashMap<Object, Object>();
                    currentMap.put(c, subMap);
                }
                currentMap = subMap;
                if (i == currentWord.length() - 1) {
                    //如果是最后一个字符，则put一个结束标志，这里只需要保存key就行了，value为null可以节省空间。
                    //如果不是最后一个字符，则不需要存这个结束标志，同样也是为了节省空间。
                    currentMap.put(END_FLAG, null);
                }
            }
        }
    }

}
