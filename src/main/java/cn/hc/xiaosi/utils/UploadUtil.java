package cn.hc.xiaosi.utils;

import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.LogService;
import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static cn.hc.xiaosi.bean.OSSClientConstants.BACKET_NAME;
import static cn.hc.xiaosi.bean.OSSClientConstants.FOLDER;

/**
 * @ClassName UploadUtil
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/3/1922:32
 */
@Slf4j
public class UploadUtil {

    public static String getFileUrl(MultipartFile file, String category, LogService logService, HttpServletRequest request) {
        /**
         * 调用文件上传工具类，上传文件
         */
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        //文件名
        String fileName = file.getOriginalFilename();
        String fileUrl = category + "/";
        String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, fileUrl);
        System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
        String url = "https://rerouter.oss-cn-hangzhou.aliyuncs.com/" + FOLDER + fileUrl + fileName;
        System.out.println("上传文件的地址url：" + url);
        String operator = JWTUtil.parseCookies(request);
        if (operator == null) {
            return null;
        } else {
            boolean debug = log.isDebugEnabled();
            LogBean logBean = new LogBean();
            log.info("上传文件");
            if (debug) {
                log.debug("管理员[{}]上传文件，文件名称为：[{}]", operator, fileName);
            }
            logBean.setOperation("文件上传").setOperator(operator).setContent("管理员" + operator + "上传文件信息，文件名称为：" + fileName);
            logService.saveLog(logBean);
            return url;
        }
    }

}
