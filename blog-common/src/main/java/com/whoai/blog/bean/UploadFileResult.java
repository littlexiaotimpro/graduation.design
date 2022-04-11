package com.whoai.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResult implements Serializable {
    private static final long serialVersionUID = -3807593939827020860L;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
