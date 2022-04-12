package com.whoai.blog.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.ImmutableMap;
import com.whoai.blog.constant.Dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典项枚举类 Json格式化，用于解决前端只能看到字符串的问题
 * <p>
 * ps: 枚举类的 toString 无法覆写
 *
 * @date 2022/2/24
 * @see Dictionary
 */
public class DictionaryListJsonSerializer extends JsonSerializer<List<Dictionary>> {
    @Override
    public void serialize(List<Dictionary> dictionaries, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dictionaries != null && !dictionaries.isEmpty()) {
            Map<Integer, String> d = new HashMap<>();
            for (Dictionary dictionary : dictionaries) {
                d.put(dictionary.getCode(), dictionary.getValue());
            }
            jsonGenerator.writeObject(d);
        } else {
            jsonGenerator.writeString("");
        }
    }
}
