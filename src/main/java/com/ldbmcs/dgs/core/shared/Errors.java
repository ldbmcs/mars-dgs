package com.ldbmcs.dgs.core.shared;


import com.ldbmcs.dgs.common.exceptions.ServiceException;
import com.ldbmcs.dgs.common.exceptions.UnauthorizedException;

public final class Errors {
    private Errors() {
    }

    public static final String ERROR_UNAUTHORIZED = "error.unauthorized";
    public static final String ERROR_NOT_REGISTERED = "error.not_registered";
    public static final String ERROR_INVALID_CREDENTIALS = "error.invalid_credentials";
    public static final String INTERNAL_ERROR = "error.internal_error";

    public static ServiceException create(String code) {
        return new ServiceException(code);
    }

    public static UnauthorizedException unauthorized() {
        return new UnauthorizedException(ERROR_UNAUTHORIZED);
    }
}
