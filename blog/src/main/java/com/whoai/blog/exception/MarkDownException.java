package com.whoai.blog.exception;

public class MarkDownException extends RuntimeException {

    private static final long serialVersionUID = 6792992951869966785L;

    public MarkDownException() {
    }

    public MarkDownException(String message) {
        super(message);
    }

    public MarkDownException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarkDownException(Throwable cause) {
        super(cause);
    }
}
