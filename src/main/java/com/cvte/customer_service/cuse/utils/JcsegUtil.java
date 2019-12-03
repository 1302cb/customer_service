package com.cvte.customer_service.cuse.utils;

import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 实现分词的工具类
 *
 * @author chenbo
 * @Date 2019/11/29 5:53 下午
 */
public class JcsegUtil {
    private static JcsegTaskConfig config;
    private static ISegment seg = null;
    private static boolean inited;

    /**
     * 初始化分词器
     *
     * @throws JcsegException
     */
    private static void init() {
        JcsegTaskConfig config = new JcsegTaskConfig();
        ADictionary dic = DictionaryFactory.createDefaultDictionary(config);
        try {
            //使用复杂模式
            seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE,
                    new Object[]{config, dic});
        } catch (JcsegException e) {
            e.printStackTrace();
        }
        inited = true;
    }

    /**
     * 得到分词后的关键词列表
     *
     * @param fullText
     * @return
     * @throws IOException
     * @throws JcsegException
     */
    public static synchronized List<String> segment(String fullText) throws IOException,
            JcsegException {
        if (!inited) {
            init();
        }
        IWord word = null;
        seg.reset(new StringReader(fullText));
        List<String> ret = new ArrayList<>();
        Set<String> ml = new HashSet<>();
        while ((word = seg.next()) != null) {
            // 去掉长度小于2的词
            if (word.getLength() < 2) {
                continue;
            }
            ml.add(word.getValue());
        }
        ret.addAll(ml);
        return ret;
    }

    /**
     * 拼接切分好的关键词
     *
     * @param values
     * @return
     */
    public static String spliceJcsegArray(List<String> values) {
        String[] words = {"什么", "是什么", "怎么", "为什么"};
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        boolean splitFlag = false;
        String param = "";
        if (values == null) {
            return null;
        }

        for (int i = 0; i < values.size(); i++) {
            if (wordList.contains(values.get(i))) {
                continue;
            }
            if (!splitFlag) {
                splitFlag = true;
            } else {
                param += " ";
            }
            param += values.get(i);
        }
        return param;
    }
}
