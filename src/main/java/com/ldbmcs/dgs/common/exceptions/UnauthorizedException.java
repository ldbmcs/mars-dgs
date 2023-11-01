package com.ldbmcs.dgs.common.exceptions;

public class UnauthorizedException extends ServiceException {
    public UnauthorizedException(String code, Object... arguments) {
        super(code, arguments);
    }

    public UnauthorizedException(String code, Throwable cause, Object... arguments) {
        super(code, cause, arguments);
    }
}
