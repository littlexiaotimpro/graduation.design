package com.whoai.blog.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.whoai.blog.bean.OSSClientConstantsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 上传图片信息至OSS对象存储中
 * 代码稍有修改
 * 主要代码参考：https://blog.csdn.net/u014079773/article/details/66968718
 *
 * @deprecated
 */
public class OSSClientUtil {

    //log日志
    private static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
    //阿里云API的内或外网域名
    private static final String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static final String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static final String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static final String BUCKET_NAME;
    //阿里云API的文件夹名称
    private static final String FOLDER;

    //初始化属性
    static {
        ENDPOINT = OSSClientConstantsTemplate.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstantsTemplate.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstantsTemplate.ACCESS_KEY_SECRET;
        BUCKET_NAME = OSSClientConstantsTemplate.BUCKET_NAME;
        FOLDER = OSSClientConstantsTemplate.FOLDER;
    }

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient OSS连接
     */
    public static String createBucketName(OSSClient ossClient) {
        //存储空间
        if (!ossClient.doesBucketExist(BUCKET_NAME)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(BUCKET_NAME);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return BUCKET_NAME;
    }

    /**
     * 删除存储空间 buckName
     *
     * @param ossClient oss对象
     */
    public static void deleteBucket(OSSClient ossClient) {
        ossClient.deleteBucket(BUCKET_NAME);
        logger.info("删除" + BUCKET_NAME + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient oss连接
     * @param folder    指定文件夹
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String folder) {
        //文件夹名
        //判断文件夹是否存在，不存在则创建
        String key = FOLDER + folder;
        if (!ossClient.doesObjectExist(BUCKET_NAME, key)) {
            //创建文件夹
            ossClient.putObject(BUCKET_NAME, key, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(BUCKET_NAME, key);
            return object.getKey();
        }
        return folder;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient oss连接
     * @param folder    指定文件夹
     * @param key       Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String folder, String key) {
        ossClient.deleteObject(BUCKET_NAME, FOLDER + folder + key);
        logger.info("删除" + BUCKET_NAME + "下的文件" + FOLDER + folder + key + "成功");
    }

    /**
     * 上传图片至OSS
     *
     * @param ossClient oss连接
     * @param file      上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param category  分类文件夹名 如"MCU/"
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObject2OSS(OSSClient ossClient, MultipartFile file, String category) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = file.getInputStream();//new FileInputStream(file);
            //文件名
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            //文件大小
            long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, FOLDER + category + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".md".equalsIgnoreCase(fileExtension)) {
            return "text/x-markdown";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

}
