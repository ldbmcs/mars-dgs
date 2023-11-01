package com.ldbmcs.dgs.common.exceptions;

public class ForbiddenException extends ServiceException {
    public ForbiddenException(String code, Object... arguments) {
        super(code, arguments);
    }

    public ForbiddenException(String code, Throwable cause, Object... arguments) {
        super(code, cause, arguments);
    }
}
