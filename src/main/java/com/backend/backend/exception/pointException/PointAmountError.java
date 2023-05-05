package com.backend.backend.exception.pointException;

public class PointAmountError extends RuntimeException{
    public PointAmountError() {
        super();
    }

    public PointAmountError(String message) {
        super(message);
    }

    public PointAmountError(String message, Throwable cause) {
        super(message, cause);
    }

    public PointAmountError(Throwable cause) {
        super(cause);
    }

    protected PointAmountError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
