package com.whoai.blog.exception;

public class ResourcesNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 581556886553926339L;

    public ResourcesNotFoundException() {
        super();
    }

    public ResourcesNotFoundException(String message) {
        super(message);
    }

    public ResourcesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcesNotFoundException(Throwable cause) {
        super(cause);
    }
}
