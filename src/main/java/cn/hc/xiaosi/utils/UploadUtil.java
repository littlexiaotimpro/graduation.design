package cn.hc.xiaosi.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import static cn.hc.xiaosi.bean.OSSClientConstants.BACKET_NAME;
import static cn.hc.xiaosi.bean.OSSClientConstants.FOLDER;

/**
 * @ClassName UploadUtil
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/1922:32
 */
public class UploadUtil {

    public static String getFileUrl(MultipartFile file, String category) {
        /**
         * 调用文件上传工具类，上传文件
         */
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        String fileUrl = category + "/";
        String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, fileUrl);
        System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
        String url = "https://rerouter.oss-cn-hangzhou.aliyuncs.com/" + FOLDER + fileUrl + file.getOriginalFilename();
        System.out.println("上传文件的地址url：" + url);
        return url;
    }

}
