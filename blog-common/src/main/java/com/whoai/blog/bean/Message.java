package com.whoai.blog.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName Message
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/2/2312:51
 */
@Accessors(chain = true)
@Data
public class Message {
    private int code;
    private String msg;
}
