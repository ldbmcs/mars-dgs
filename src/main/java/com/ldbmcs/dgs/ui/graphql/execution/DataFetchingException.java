package com.ldbmcs.dgs.ui.graphql.execution;

import com.ldbmcs.dgs.common.exceptions.ServiceException;
import com.ldbmcs.dgs.common.exceptions.ServiceWithDetailException;
import com.ldbmcs.dgs.core.shared.Errors;
import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.dao.DataAccessException;

import static graphql.Assert.assertNotNull;


@Getter
public class DataFetchingException implements GraphQLError {
    private final String message;
    private final List<Object> path;
    private final Throwable exception;
    private final List<SourceLocation> locations;
    private final Map<String, Object> extensions;
    private final String code;

    public DataFetchingException(ResultPath path, Throwable exception, SourceLocation sourceLocation) {
        this.code = mkCode(exception);
        this.path = assertNotNull(path).toList();
        this.exception = assertNotNull(exception);
        this.locations = Collections.singletonList(sourceLocation);
        this.extensions = mkExtensions(exception);
        this.message = mkMessage(exception);
    }

    private String mkMessage(Throwable exception) {
        if (exception instanceof DataAccessException) {
            return null;
        }
        return exception.getMessage();
    }

    private String mkCode(Throwable exception) {
        if (exception instanceof ServiceException) {
            return ((ServiceException) exception).getCode();
        } else if (exception instanceof DataAccessException) {
            return Errors.INTERNAL_ERROR;
        }
        return null;
    }

    private Map<String, Object> mkExtensions(Throwable exception) {
        Map<String, Object> es = new LinkedHashMap<>();
        if (code != null) {
            es.put("code", code);
        }
        if (exception instanceof GraphQLError) {
            Map<String, Object> map = ((GraphQLError) exception).getExtensions();
            if (map != null) {
                es.putAll(map);
            }
        }
        if (exception instanceof ServiceWithDetailException) {
            es.put("details", ((ServiceWithDetailException) exception).getDetails());
        }
        return es;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
