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

    public static String getImgUrl(MultipartFile file, String category) {
        /**
         * 调用图片上传工具类，上传图片
         */
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        String imgUrl = category + "/";
        String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, imgUrl);
        System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
        String url = "https://rerouter.oss-cn-hangzhou.aliyuncs.com/" + FOLDER + imgUrl + file.getOriginalFilename();
        System.out.println("上传图片的地址url：" + url);
        return url;
    }

}
