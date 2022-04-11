package com.whoai.blog.feign;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.bean.UploadFileResult;
import com.whoai.blog.feign.impl.FileServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(value = "${feign.rpc-service}", fallbackFactory = FileServiceFallbackFactory.class)
public interface FileService {

    @PostMapping(value = "/file/upload")
    ResponseResult<UploadFileResult> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request);

    @PostMapping(value = "/file/multiple/upload")
    ResponseResult<List<UploadFileResult>> multipleUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request);

    @GetMapping("/file/delete/{fileName:.+}")
    ResponseResult<Boolean> deleteFile(@PathVariable String fileName, HttpServletRequest request);

    @GetMapping(value = "/file/preview/{fileName:.+}")
    ResponseResult<String> previewFile(@PathVariable String fileName, HttpServletRequest request);
}
