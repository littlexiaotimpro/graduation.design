package cn.hc.xiaosi.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

/**
 * @ClassName ArticleReviewUtil
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/5/715:07
 */
public class ArticleReviewUtil {

    private static Set<String> words;

    private static Map<Object, Object> sensitiveWordsMap;

    private static final String END_FLAG = "end";

    static {
        try {
            ArticleReviewUtil.words = readTxtByLine();
            initSensitiveWordsMap(ArticleReviewUtil.words);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 敏感词库
     *
     * @return
     * @throws FileNotFoundException
     */
    public static Set<String> readTxtByLine() throws FileNotFoundException {
        Set<String> keyWordSet = new HashSet<String>();
        File file = ResourceUtils.getFile("classpath:static/dictionary.txt");
        //文件流是否存在
        if (!file.exists()) {
            return keyWordSet;
        }
        BufferedReader reader = null;
        String temp = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
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
     *
     * @param text
     * @return
     */
    public static boolean isContainSensitiveWord(String text) {
        Set<String> sensitiveWords = getSensitiveWords(text, MatchType.MIN_MATCH);
        if (sensitiveWords != null && sensitiveWords.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 统计其中的敏感词
     *
     * @param text
     * @param matchType
     * @return
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
     *
     * @param text
     * @param startIndex
     * @param matchType
     * @return
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
     *
     * @param sensitiveWords
     */
    private static void initSensitiveWordsMap(Set<String> sensitiveWords) {
        if (sensitiveWords == null || sensitiveWords.isEmpty()) {
            throw new IllegalArgumentException("Senditive words must not be empty!");
        }
        sensitiveWordsMap = new HashMap<>(sensitiveWords.size());
        String currentWord;
        Map<Object, Object> currentMap;
        Map<Object, Object> subMap;
        Iterator<String> iterator = sensitiveWords.iterator();
        while (iterator.hasNext()) {
            currentWord = iterator.next();
            if (currentWord == null || currentWord.trim().length() < 2) {  //敏感词长度必须大于等于2
                continue;
            }
            currentMap = sensitiveWordsMap;
            for (int i = 0; i < currentWord.length(); i++) {
                char c = currentWord.charAt(i);
                subMap = (Map<Object, Object>) currentMap.get(c);
                if (subMap == null) {
                    subMap = new HashMap<>();
                    currentMap.put(c, subMap);
                    currentMap = subMap;
                } else {
                    currentMap = subMap;
                }
                if (i == currentWord.length() - 1) {
                    //如果是最后一个字符，则put一个结束标志，这里只需要保存key就行了，value为null可以节省空间。
                    //如果不是最后一个字符，则不需要存这个结束标志，同样也是为了节省空间。
                    currentMap.put(END_FLAG, null);
                }
            }
        }
    }

}
