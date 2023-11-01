package com.ldbmcs.dgs.common.exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public class ServiceException extends RuntimeException {
    private String code;
    private Object[] arguments;

    public ServiceException(@NotNull String code, @NotNull String message, @Nullable Object... arguments) {
        super(message);
        this.code = code;
        this.arguments = arguments;
    }

    public ServiceException(@NotNull String code, @Nullable Object... arguments) {
        super(code);
        this.code = code;
        this.arguments = arguments;
    }

    public ServiceException(@NotNull String code, @Nullable Throwable cause, @Nullable Object... arguments) {
        super(cause);
        this.code = code;
        this.arguments = arguments;
    }

    @NotNull
    public String getCode() {
        return code;
    }

    @Nullable
    public Object[] getArguments() {
        return arguments;
    }

    public Object getFirstArgument() {
        return arguments != null && arguments.length > 0 ? arguments[0] : null;
    }
}
