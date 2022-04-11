package com.whoai.blog.feign.impl;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.bean.UploadFileResult;
import com.whoai.blog.feign.FileService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件服务的回调工厂
 *
 * @since 2022/4/11
 */
@Component
@Slf4j
public class FileServiceFallbackFactory implements FallbackFactory<FileService> {
    @Override
    public FileService create(Throwable cause) {
        log.warn("远程调用服务时发生异常", cause);
        return new FileService() {
            @Override
            public ResponseResult<UploadFileResult> upload(MultipartFile file, HttpServletRequest request) {
                return ResponseResult.fail("上传文件失败，请稍后重试");
            }

            @Override
            public ResponseResult<List<UploadFileResult>> multipleUpload(MultipartFile[] files, HttpServletRequest request) {
                return ResponseResult.fail("多上传文件失败，请稍后重试");
            }

            @Override
            public ResponseResult<Boolean> deleteFile(String fileName, HttpServletRequest request) {
                return ResponseResult.fail("删除文件失败，请稍后重试");
            }

            @Override
            public ResponseResult<String> previewFile(String fileName, HttpServletRequest request) {
                return ResponseResult.fail("文件预览失败，请稍后重试");
            }
        };
    }
}
