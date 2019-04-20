package cn.hc.xiaosi.bean;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName JWTResult
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/4/209:52
 */
@Accessors(chain = true)
@Data
public class JWTResult {

    private int errCode;

    private boolean success;

    private Claims claims;

}
