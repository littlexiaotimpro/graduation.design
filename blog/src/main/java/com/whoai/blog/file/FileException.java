package com.whoai.blog.file;

/**
 * 文件操作异常
 */
public class FileException extends RuntimeException {
    private static final long serialVersionUID = -5055632055943299623L;

    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
