package com.whoai.blog.dict;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.ImmutableMap;
import com.whoai.blog.constant.Dictionary;

import java.io.IOException;

/**
 * 字典项枚举类 Json格式化，用于解决前端只能看到字符串的问题
 * <p>
 * ps: 枚举类的 toString 无法覆写
 *
 * @date 2022/2/24
 * @see Dictionary
 */
public class DictionaryJsonSerializer extends JsonSerializer<Dictionary> {
    @Override
    public void serialize(Dictionary dictionary, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (dictionary != null) {
            jsonGenerator.writeObject(ImmutableMap.of(dictionary.getCode(), dictionary.getValue()));
        } else {
            jsonGenerator.writeString("");
        }
    }
}
