package cn.hc.xiaosi.utils;

import cn.hc.xiaosi.entity.LogBean;
import cn.hc.xiaosi.service.LogService;
import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

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

    public static String getFileUrl(MultipartFile file, String category, LogService logService, HttpServletRequest request) throws IOException {
        /**
         * 调用文件上传工具类，上传文件
         */
        String operator = JWTUtil.parseCookies(request);
        boolean debug = log.isDebugEnabled();
        LogBean logBean = new LogBean();
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
                logBean.setOperation("文件上传").setOperator(operator).setContent("管理员" + operator + "上传文件失败，因为" + fileName + "文件中含有大量敏感词汇。");
                logService.saveLog(logBean);
                return null;
            } else {
                OSSClient ossClient = OSSClientUtil.getOSSClient();
                String fileUrl = category + "/";
                String md5key = OSSClientUtil.uploadObject2OSS(ossClient, file, BACKET_NAME, FOLDER, fileUrl);
                System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
                String url = "https://rerouter.oss-cn-hangzhou.aliyuncs.com/" + FOLDER + fileUrl + fileName;
                System.out.println("上传文件的地址url：" + url);
                if (debug) {
                    log.debug("管理员[{}]上传文件，文件名称为：[{}]", operator, fileName);
                }
                logBean.setOperation("文件上传").setOperator(operator).setContent("管理员" + operator + "上传文件信息，文件名称为：" + fileName);
                logService.saveLog(logBean);
                return url;
            }
        }
    }

}
