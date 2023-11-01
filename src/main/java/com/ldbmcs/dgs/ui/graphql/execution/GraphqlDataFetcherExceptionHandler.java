package com.ldbmcs.dgs.ui.graphql.execution;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Component
public class GraphqlDataFetcherExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters parameters) {
        Throwable exception = unrollException(parameters.getException());
        DataFetchingException error = buildError(parameters, exception);
        return CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult().error(error).build());
    }

    private DataFetchingException buildError(DataFetcherExceptionHandlerParameters parameters, Throwable exception) {
        SourceLocation sourceLocation = parameters.getSourceLocation();
        ResultPath path = parameters.getPath();
        return new DataFetchingException(path, exception, sourceLocation);
    }

    private Throwable unrollException(Throwable exception) {
        if (exception instanceof CompletionException) {
            return exception.getCause();
        }
        return exception;
    }
}
