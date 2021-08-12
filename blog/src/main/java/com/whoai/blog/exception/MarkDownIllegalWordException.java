package com.whoai.blog.exception;

public class MarkDownIllegalWordException extends RuntimeException {

    private static final long serialVersionUID = 6792992951869966785L;

    public MarkDownIllegalWordException() {
    }

    public MarkDownIllegalWordException(String message) {
        super(message);
    }

    public MarkDownIllegalWordException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarkDownIllegalWordException(Throwable cause) {
        super(cause);
    }
}
