package com.whoai.blog.file.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.file.service.FileService;
import com.whoai.blog.bean.UploadFileResult;
import com.whoai.blog.file.util.MDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api("文件上传/下载/预览")
@RestController
@RequestMapping(value = "/blog/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("单个文件上传")
    @PostMapping(value = "/upload")
    public ResponseResult<UploadFileResult> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = fileService.storeFile(file);

        // 文件下载地址: 当前服务器地址 + 文件下载路径 + 文件名称
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(fileName)
                .toUriString();

        UploadFileResult uploadFileResult = new UploadFileResult(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        return ResponseResult.success(uploadFileResult, "单个文件上传");
    }

    @ApiOperation("批量文件上传")
    @PostMapping(value = "/multiple/upload")
    public ResponseResult<List<UploadFileResult>> multipleUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        List<UploadFileResult> uploadFileResults = Arrays.stream(files)
                .map(file -> {
                    ResponseResult<UploadFileResult> upload = upload(file, request);
                    return upload.getBody();
                })
                .collect(Collectors.toList());
        return ResponseResult.success(uploadFileResults, "批量文件上传");
    }

    @ApiOperation("文件删除")
    @GetMapping("/delete/{fileName:.+}")
    public ResponseResult<Boolean> deleteFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);
        boolean delete = false;
        try {
            delete = resource.getFile().delete();
        } catch (IOException e) {
            logger.warn("删除文件失败", e);
        }
        return ResponseResult.success(delete, "删除文件");
    }

    @ApiOperation("文件下载")
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @ApiOperation("文件预览")
    @GetMapping("/preview/{fileName:.+}")
    public ResponseResult<String> previewFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);
        String html = MDUtil.convertMDToHtml(resource);
        return ResponseResult.success(html, "预览文件");
    }

}