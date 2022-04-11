package com.whoai.blog.file.util;

import com.aliyun.oss.OSSClient;
import com.whoai.blog.bean.OSSClientConstantsTemplate;
import com.whoai.blog.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传工具类
 * 去除 OSS 存储相关逻辑，上传文件至本地服务地址
 *
 * @deprecated
 */
@Slf4j
public class UploadUtil {

    public static String getFileUrl(MultipartFile file, String category, HttpServletRequest request) throws IOException {
        String operator = JWTUtil.parseCookies(request);
        boolean debug = log.isDebugEnabled();
        //文件名
        String fileName = file.getOriginalFilename();
        log.info("上传文件");
        if (operator == null) {
            return null;
        } else {
            if (MDUtil.checkMarkdown(file)) {
                if (debug) {
                    log.debug("管理员[{}]上传文件，文件名称为：[{}]", operator, fileName);
                }
                return null;
            } else {
                OSSClient ossClient = OSSClientUtil.getOSSClient();
                String fileUrl = category + "/";
                String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, fileUrl);
                System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
                String url = "https://rerouter.oss-cn-hangzhou.aliyuncs.com/" + OSSClientConstantsTemplate.FOLDER + fileUrl + fileName;
                System.out.println("上传文件的地址url：" + url);
                if (debug) {
                    log.debug("管理员[{}]上传文件，文件名称为：[{}]", operator, fileName);
                }
                return url;
            }
        }
    }

}
